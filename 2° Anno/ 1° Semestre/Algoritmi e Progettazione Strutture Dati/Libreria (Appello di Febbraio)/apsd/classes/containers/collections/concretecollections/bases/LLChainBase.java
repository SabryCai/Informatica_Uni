package apsd.classes.containers.collections.concretecollections.bases;

import apsd.classes.containers.sequences.Vector;
import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;

abstract public class LLChainBase<Data> implements Chain<Data> {

  protected final MutableNatural size = new MutableNatural();
  protected final Box<LLNode<Data>> headref = new Box<>();
  protected final Box<LLNode<Data>> tailref = new Box<>();

  public LLChainBase() {

  }

  public LLChainBase(TraversableContainer<Data> con) {
    this();
    if (con == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    size.Assign(con.Size());
    final Box<Boolean> first = new Box<>(true);
    con.TraverseForward(dat -> {
      LLNode<Data> node = new LLNode<>(dat);
      if (first.Get()) {
        headref.Set(node);
        first.Set(false);
      } else {
        tailref.Get().SetNext(node);
      }
      tailref.Set(node);
      return false;
    });
  }

  public LLChainBase(long size, LLNode<Data> head, LLNode<Data> tail) {
    this.size.Assign(Natural.Of(size));
    this.headref.Set(head);
    this.tailref.Set(tail);
  }

  protected abstract LLChainBase<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail);

  public Natural Size() {
    return size.ToNatural();
  }

  public void Clear() {
    headref.Set(null);
    tailref.Set(null);
    size.Zero();
  }

  public boolean Remove(Data dat) {
    if (headref.Get() == null) {
      return false;
    }

    if (headref.Get().Get() == null && dat == null ||
        headref.Get().Get() != null && headref.Get().Get().equals(dat)) {
      headref.Set(headref.Get().GetNext().Get());
      if (headref.Get() == null) {
        tailref.Set(null);
      }
      size.Decrement();
      return true;
    }

    LLNode<Data> current = headref.Get();
    while (current.GetNext().Get() != null) {
      Data nextData = current.GetNext().Get().Get();
      if (nextData == null && dat == null ||
          nextData != null && nextData.equals(dat)) {
        LLNode<Data> toRemove = current.GetNext().Get();
        current.SetNext(toRemove.GetNext().Get());
        if (toRemove == tailref.Get()) {
          tailref.Set(current);
        }
        size.Decrement();
        return true;
      }
      current = current.GetNext().Get();
    }

    return false;
  }

  public ForwardIterator<Data> FIterator() {
    return new LLForwardIterator();
  }

  public BackwardIterator<Data> BIterator() {
    return new LLBackwardIterator();
  }

  protected MutableForwardIterator<Box<LLNode<Data>>> FRefIterator() {
    return new LLRefForwardIterator();
  }

  protected MutableBackwardIterator<Box<LLNode<Data>>> BRefIterator() {
    return new LLRefBackwardIterator();
  }

  public Data GetAt(Natural index) {
    if (index == null) {
      throw new NullPointerException("Index cannot be null!");
    }
    long idx = index.ToLong();
    if (idx < 0 || idx >= size.ToLong()) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + size);
    }

    LLNode<Data> current = headref.Get();
    for (long i = 0; i < idx; i++) {
      current = current.GetNext().Get();
    }
    return current.Get();
  }

  public Data GetFirst() {
    if (headref.Get() == null) {
      throw new IndexOutOfBoundsException("Chain is empty!");
    }
    return headref.Get().Get();
  }

  public Data GetLast() {
    if (tailref.Get() == null) {
      throw new IndexOutOfBoundsException("Chain is empty!");
    }
    return tailref.Get().Get();
  }

  public Sequence<Data> SubSequence(Natural from, Natural to) {
    if (from == null || to == null) {
      throw new NullPointerException("Indices cannot be null!");
    }
    long fromIdx = from.ToLong();
    long toIdx = to.ToLong();
    long currentSize = size.ToLong();

    if (fromIdx < 0 || toIdx > currentSize || fromIdx > toIdx) {
      throw new IndexOutOfBoundsException("Invalid range: [" + fromIdx + ", " + toIdx + ")");
    }

    if (fromIdx == toIdx) {
      return (Sequence<Data>) NewChain(0, null, null);
    }

    LLNode<Data> current = headref.Get();
    for (long i = 0; i < fromIdx; i++) {
      current = current.GetNext().Get();
    }

    LLNode<Data> newHead = new LLNode<>(current.Get());
    LLNode<Data> newCurrent = newHead;
    LLNode<Data> newTail = newHead;

    for (long i = fromIdx + 1; i < toIdx; i++) {
      current = current.GetNext().Get();
      LLNode<Data> newNode = new LLNode<>(current.Get());
      newCurrent.SetNext(newNode);
      newCurrent = newNode;
      newTail = newNode;
    }

    return (Sequence<Data>) NewChain(toIdx - fromIdx, newHead, newTail);
  }

  public Data AtNRemove(Natural index) {
    if (index == null) {
      throw new NullPointerException("Index cannot be null!");
    }
    long idx = index.ToLong();
    if (idx < 0 || idx >= size.ToLong()) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + size);
    }

    if (idx == 0) {
      Data data = headref.Get().Get();
      headref.Set(headref.Get().GetNext().Get());
      if (headref.Get() == null) {
        tailref.Set(null);
      }
      size.Decrement();
      return data;
    }

    LLNode<Data> current = headref.Get();
    for (long i = 0; i < idx - 1; i++) {
      current = current.GetNext().Get();
    }

    Data data = current.GetNext().Get().Get();
    LLNode<Data> toRemove = current.GetNext().Get();
    current.SetNext(toRemove.GetNext().Get());

    if (toRemove == tailref.Get()) {
      tailref.Set(current);
    }

    size.Decrement();
    return data;
  }

  public void RemoveFirst() {
    if (headref.Get() == null) {
      throw new IndexOutOfBoundsException("Chain is empty!");
    }
    headref.Set(headref.Get().GetNext().Get());
    if (headref.Get() == null) {
      tailref.Set(null);
    }
    size.Decrement();
  }

  public Data FirstNRemove() {
    if (headref.Get() == null) {
      throw new IndexOutOfBoundsException("Chain is empty!");
    }
    Data data = headref.Get().Get();
    headref.Set(headref.Get().GetNext().Get());
    if (headref.Get() == null) {
      tailref.Set(null);
    }
    size.Decrement();
    return data;
  }

  public boolean Filter(Predicate<Data> predicate) {
    if (predicate == null) {
      return false;
    }

    long oldSize = size.ToLong();

    while (headref.Get() != null && !predicate.Apply(headref.Get().Get())) {
      headref.Set(headref.Get().GetNext().Get());
      size.Decrement();
    }

    if (headref.Get() == null) {
      tailref.Set(null);
      return oldSize != size.ToLong();
    }

    LLNode<Data> current = headref.Get();
    while (current.GetNext().Get() != null) {
      if (!predicate.Apply(current.GetNext().Get().Get())) {
        LLNode<Data> toRemove = current.GetNext().Get();
        current.SetNext(toRemove.GetNext().Get());
        if (toRemove == tailref.Get()) {
          tailref.Set(current);
        }
        size.Decrement();
      } else {
        current = current.GetNext().Get();
      }
    }

    return oldSize != size.ToLong();
  }

  protected class LLForwardIterator implements ForwardIterator<Data> {
    private LLNode<Data> current;

    public LLForwardIterator() {
      this.current = headref.Get();
    }

    @Override
    public boolean IsValid() {
      return current != null;
    }

    @Override
    public void Reset() {
      current = headref.Get();
    }

    @Override
    public Data GetCurrent() {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      return current.Get();
    }

    @Override
    public Data DataNNext() {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      Data data = current.Get();
      current = current.GetNext().Get();
      return data;
    }
  }

  protected class LLBackwardIterator implements BackwardIterator<Data> {
    private LLNode<Data> current;

    public LLBackwardIterator() {
      this.current = tailref.Get();
    }

    @Override
    public boolean IsValid() {
      return current != null;
    }

    @Override
    public void Reset() {
      current = tailref.Get();
    }

    @Override
    public Data GetCurrent() {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      return current.Get();
    }

    @Override
    public Data DataNPrev() {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      Data data = current.Get();
      
      if (current == headref.Get()) {
        current = null;
      } else {
        LLNode<Data> temp = headref.Get();
        while (temp.GetNext().Get() != current) {
          temp = temp.GetNext().Get();
        }
        current = temp;
      }
      
      return data;
    }
  }

  protected class LLRefForwardIterator implements MutableForwardIterator<Box<LLNode<Data>>> {
    private Box<LLNode<Data>> currentRef;

    public LLRefForwardIterator() {
      this.currentRef = headref;
    }

    @Override
    public boolean IsValid() {
      return currentRef.Get() != null;
    }

    @Override
    public void Reset() {
      currentRef = headref;
    }

    @Override
    public Box<LLNode<Data>> GetCurrent() {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      return currentRef;
    }

    @Override
    public void SetCurrent(Box<LLNode<Data>> nodeRef) {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      currentRef.Set(nodeRef.Get());
    }

    @Override
    public Box<LLNode<Data>> DataNNext() {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      Box<LLNode<Data>> result = currentRef;
      currentRef = currentRef.Get().GetNext();
      return result;
    }
  }

  protected class LLRefBackwardIterator implements MutableBackwardIterator<Box<LLNode<Data>>> {
    private Box<LLNode<Data>> currentRef;

    public LLRefBackwardIterator() {
      this.currentRef = tailref;
    }

    @Override
    public boolean IsValid() {
      return currentRef.Get() != null;
    }

    @Override
    public void Reset() {
      currentRef = tailref;
    }

    @Override
    public Box<LLNode<Data>> GetCurrent() {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      return currentRef;
    }

    @Override
    public void SetCurrent(Box<LLNode<Data>> nodeRef) {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      currentRef.Set(nodeRef.Get());
    }

    @Override
    public Box<LLNode<Data>> DataNPrev() {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      Box<LLNode<Data>> result = currentRef;
      
      if (currentRef.Get() == headref.Get()) {
        currentRef = new Box<>(null);
      } else {
        LLNode<Data> temp = headref.Get();
        while (temp.GetNext().Get() != currentRef.Get()) {
          temp = temp.GetNext().Get();
        }
        currentRef = new Box<>(temp);
      }
      
      return result;
    }
  }

  public boolean TraverseForward(Predicate<Data> predicate) {
    if (predicate == null) {
      return false;
    }
    LLNode<Data> current = headref.Get();
    while (current != null) {
      if (predicate.Apply(current.Get())) {
        return true;
      }
      current = current.GetNext().Get();
    }
    return false;
  }

  public boolean TraverseBackward(Predicate<Data> predicate) {
    if (predicate == null) {
      return false;
    }
    BackwardIterator<Data> it = BIterator();
    while (it.IsValid()) {
      if (predicate.Apply(it.GetCurrent())) {
        return true;
      }
      it.Prev();
    }
    return false;
  }

  public boolean Exists(Data dat) {
    LLNode<Data> current = headref.Get();
    while (current != null) {
      Data currentData = current.Get();
      if (currentData == null && dat == null ||
          currentData != null && currentData.equals(dat)) {
        return true;
      }
      current = current.GetNext().Get();
    }
    return false;
  }

  public boolean IsEmpty() {
    return size.IsZero();
  }

  public boolean IsInBound(Natural index) {
    return (index != null) && (index.ToLong() < size.ToLong());
  }

  public long ExcIfOutOfBound(Natural num) {
    if (num == null) {
      throw new NullPointerException("Natural number cannot be null!");
    }
    long idx = num.ToLong();
    if (idx >= size.ToLong()) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + size + "!");
    }
    return idx;
  }
}