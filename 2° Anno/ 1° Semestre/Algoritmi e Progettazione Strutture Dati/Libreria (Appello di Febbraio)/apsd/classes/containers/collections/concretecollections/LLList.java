package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Box;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;

/** Object: Concrete list implementation on linked-list. */
public class LLList<Data> extends LLChainBase<Data> implements List<Data> {
  
  public LLList() {
    super();
  }
  
  public LLList(TraversableContainer<Data> con) {
    super(con);
  }
  
  public LLList(long size, LLNode<Data> head, LLNode<Data> tail) {
    super(size, head, tail);
  }
  
  @Override
  protected LLChainBase<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) {
    return new LLList<>(size, head, tail);
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */
  
  @Override
  public MutableForwardIterator<Data> FIterator() {
    return new LLMutableForwardIterator();
  }
  
  @Override
  public MutableBackwardIterator<Data> BIterator() {
    return new LLMutableBackwardIterator();
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */
  
  @Override
  public void SetAt(Data dat, Natural index) {
    long idx = ExcIfOutOfBound(index);
    LLNode<Data> current = headref.Get();
    for (long i = 0; i < idx && current != null; i++) {
      current = current.GetNext().Get();
    }
    if (current == null) {
      throw new IndexOutOfBoundsException("Index out of bounds!");
    }
    current.Set(dat);
  }
  
  @Override
  public MutableSequence<Data> SubSequence(Natural from, Natural to) {
    return (MutableSequence<Data>) super.SubSequence(from, to);
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */
  
  @Override
  public void InsertAt(Data dat, Natural index) {
    if (index == null) {
      throw new NullPointerException("Index cannot be null!");
    }
    long idx = index.ToLong();
    long currentSize = size.ToLong();
    
    if (idx > currentSize) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + currentSize + "!");
    }
    
    LLNode<Data> newNode = new LLNode<>(dat);
    
    if (idx == 0) {
      newNode.SetNext(headref.Get());
      headref.Set(newNode);
      if (tailref.Get() == null) {
        tailref.Set(newNode);
      }
    } else {
      LLNode<Data> prev = headref.Get();
      for (long i = 0; i < idx - 1 && prev != null; i++) {
        prev = prev.GetNext().Get();
      }
      if (prev == null) {
        throw new IndexOutOfBoundsException("Index out of bounds!");
      }
      newNode.SetNext(prev.GetNext().Get());
      prev.SetNext(newNode);
      if (newNode.GetNext().Get() == null) {
        tailref.Set(newNode);
      }
    }
    size.Increment();
  }
  
  /* ************************************************************************ */
  /* Inner mutable iterator classes                                           */
  /* ************************************************************************ */
  
  protected class LLMutableForwardIterator implements MutableForwardIterator<Data> {
    protected LLNode<Data> current = headref.Get();
    
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
    public void SetCurrent(Data dat) {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      current.Set(dat);
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
  
  protected class LLMutableBackwardIterator implements MutableBackwardIterator<Data> {
    protected LLNode<Data> current;
    
    protected LLMutableBackwardIterator() {
      current = tailref.Get();
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
    public void SetCurrent(Data dat) {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      current.Set(dat);
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
        LLNode<Data> prev = headref.Get();
        while (prev != null && prev.GetNext().Get() != current) {
          prev = prev.GetNext().Get();
        }
        current = prev;
      }
      return data;
    }
  }
}