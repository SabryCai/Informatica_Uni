package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.base.RemovableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

public interface Collection<Data> extends ClearableContainer, InsertableContainer<Data>, RemovableContainer<Data>, IterableContainer<Data>{ // Must extend ClearableContainer, InsertableContainer, RemovableContainer, and IterableContainer
  
  //Filter
  default boolean Filter(Predicate<Data> fun) {
    Natural oldsize = Size();
    if (fun != null) {
      ForwardIterator<Data> itr = FIterator();
      while (itr.IsValid()) {
        Data dat = itr.GetCurrent();
        if (fun.Apply(dat)) {
          itr.Next();
        } else {
          Remove(dat);
          itr.Reset();
        }
      }
    }
    return !Size().equals(oldsize);
  }

  @Override
  default void Clear() {
    while (!IsEmpty()) {
      ForwardIterator<Data> itr = FIterator();
      if (itr.IsValid()) {
        Remove(itr.GetCurrent());
      }
    }
  }
}
