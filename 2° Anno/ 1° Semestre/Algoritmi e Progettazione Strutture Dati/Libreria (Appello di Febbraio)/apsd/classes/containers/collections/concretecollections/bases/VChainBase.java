package apsd.classes.containers.collections.concretecollections.bases;

import apsd.classes.containers.sequences.DynCircularVector;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.Sequence;
import apsd.interfaces.traits.Predicate;

public abstract class VChainBase<Data> implements Chain<Data>{

  protected final DynVector<Data> vec;

  protected VChainBase() {
    this.vec = new DynCircularVector<>();
  }

  protected VChainBase(TraversableContainer<Data> container) {
    this();
    if (container == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    container.TraverseForward(dat -> {
      vec.InsertLast(dat);
      return false;
    });
  }

  protected VChainBase(DynVector<Data> vec) {
    if (vec == null) {
      throw new NullPointerException("Vector cannot be null!");
    }
    this.vec = vec;
  }

  protected abstract VChainBase<Data> NewChain(DynVector<Data> vec);

  public Natural Size() {
    return vec.Size();
  }

  public void Clear() {
    vec.Clear();
  }

  public boolean Remove(Data dat) {
    Natural index = vec.Search(dat);
    if (index == null) {
      return false;
    }
    vec.AtNRemove(index);
    return true;
  }

  public ForwardIterator<Data> FIterator() {
    return vec.FIterator();
  }

  public BackwardIterator<Data> BIterator() {
    return vec.BIterator();
  }

  public Data GetAt(Natural index) {
    return vec.GetAt(index);
  }

  public Data GetFirst() {
    return vec.GetFirst();
  }

  public Data GetLast() {
    return vec.GetLast();
  }

  public Sequence<Data> SubSequence(Natural from, Natural to) {
    DynVector<Data> subVec = (DynVector<Data>) vec.SubSequence(from, to);
    return (Sequence<Data>) NewChain(subVec);
  }

  public Data AtNRemove(Natural index) {
    return vec.AtNRemove(index);
  }

  public void RemoveFirst() {
    vec.RemoveFirst();
  }

  public Data FirstNRemove() {
    return vec.FirstNRemove();
  }

  public boolean Filter(Predicate<Data> predicate) {
    if (predicate == null) {
      return false;
    }
    long oldSize = vec.Size().ToLong();
    long i = 0;
    while (i < vec.Size().ToLong()) {
      if (!predicate.Apply(vec.GetAt(Natural.Of(i)))) {
        vec.AtNRemove(Natural.Of(i));
      } else {
        i++;
      }
    }
    return oldSize != vec.Size().ToLong();
  }

  public boolean TraverseForward(Predicate<Data> predicate) {
    return vec.TraverseForward(predicate);
  }

  public boolean TraverseBackward(Predicate<Data> predicate) {
    return vec.TraverseBackward(predicate);
  }

  public boolean Exists(Data dat) {
    return vec.Exists(dat);
  }

  public boolean IsEmpty() {
    return vec.IsEmpty();
  }

  public boolean IsInBound(Natural index) {
    return vec.IsInBound(index);
  }

  public long ExcIfOutOfBound(Natural num) {
    return vec.ExcIfOutOfBound(num);
  }
}