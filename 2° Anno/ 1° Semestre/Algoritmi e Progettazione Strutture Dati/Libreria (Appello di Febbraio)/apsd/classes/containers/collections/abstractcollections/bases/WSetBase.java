package apsd.classes.containers.collections.abstractcollections.bases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;
import apsd.interfaces.containers.collections.Set;
import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

/** Object: Abstract wrapper set base implementation via chain. */
abstract public class WSetBase<Data, Chn extends Chain<Data>> implements Set<Data> {

  protected Chn chn;

  protected WSetBase() {
    ChainAlloc();
  }

  protected abstract void ChainAlloc();

  @Override
  public Natural Size() {
    return chn.Size();
  }

  @Override
  public void Clear() {
    chn.Clear();
  }

  @Override
  public boolean Insert(Data dat) {
    if (!chn.Exists(dat)) {
      return chn.Insert(dat);
    }
    return false;
  }

  @Override
  public boolean Remove(Data dat) {
    return chn.Remove(dat);
  }

  @Override
  public ForwardIterator<Data> FIterator() {
    return chn.FIterator();
  }

  @Override
  public BackwardIterator<Data> BIterator() {
    return chn.BIterator();
  }

  @Override
  public boolean TraverseForward(Predicate<Data> predicate) {
    return chn.TraverseForward(predicate);
  }

  @Override
  public boolean TraverseBackward(Predicate<Data> predicate) {
    return chn.TraverseBackward(predicate);
  }

  @Override
  public boolean Filter(Predicate<Data> predicate) {
    return chn.Filter(predicate);
  }

  @Override
  public boolean Exists(Data dat) {
    return chn.Exists(dat);
  }

  @Override
  public void Union(Set<Data> set) {
    if (set == null) {
      return;
    }
    set.TraverseForward(dat -> {
      Insert(dat);
      return false;
    });
  }

  @Override
  public void Difference(Set<Data> set) {
    if (set == null) {
      return;
    }
    set.TraverseForward(dat -> {
      Remove(dat);
      return false;
    });
  }

  @Override
  public void Intersection(Set<Data> set) {
    if (set == null) {
      Clear();
      return;
    }
    ForwardIterator<Data> it = FIterator();
    while (it.IsValid()) {
      Data current = it.GetCurrent();
      if (!set.Exists(current)) {
        Remove(current);
        it.Reset();
      } else {
        it.Next();
      }
    }
  }
}