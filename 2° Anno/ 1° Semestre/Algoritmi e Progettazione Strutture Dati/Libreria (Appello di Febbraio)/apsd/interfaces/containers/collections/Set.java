package apsd.interfaces.containers.collections;

import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

public interface Set<Data> extends Collection<Data> {
  
  // Set Operations
  default void Union(Set<Data> set) {
    if (set != null) {
      ForwardIterator<Data> it = set.FIterator();
      while (it.IsValid()) {
        Insert(it.GetCurrent());
        it.Next();
      }
    }
  }
  
  default void Difference(Set<Data> set) {
    if (set != null) {
      ForwardIterator<Data> it = set.FIterator();
      while (it.IsValid()) {
        Remove(it.GetCurrent());
        it.Next();
      }
    }
  }
  
  default void Intersection(Set<Data> set) {
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
  
  @Override
  default boolean IsEqual(IterableContainer<Data> container) {
    if (container == null) {
      return false;
    }
    if (!Size().equals(container.Size())) {
      return false;
    }
    ForwardIterator<Data> it = container.FIterator();
    while (it.IsValid()) {
      if (!Exists(it.GetCurrent())) {
        return false;
      }
      it.Next();
    }
    return true;
  }
}