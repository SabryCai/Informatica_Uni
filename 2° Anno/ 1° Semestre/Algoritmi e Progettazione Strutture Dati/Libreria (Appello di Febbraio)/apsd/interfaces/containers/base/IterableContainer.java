package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.BackwardIterator;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.interfaces.traits.Predicate;

public interface IterableContainer<Data> extends TraversableContainer<Data> {
  
  ForwardIterator<Data> FIterator();
  
  BackwardIterator<Data> BIterator();
  
  @Override
  default boolean TraverseForward(Predicate<Data> predicate) {
    if (predicate == null) {
      return false;
    }
    ForwardIterator<Data> it = FIterator();
    while (it.IsValid()) {
      if (predicate.Apply(it.GetCurrent())) {
        return true;
      }
      it.Next();
    }
    return false;
  }
  
  @Override
  default boolean TraverseBackward(Predicate<Data> predicate) {
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
  
  default boolean IsEqual(IterableContainer<Data> container) {
    if (container == null) {
      return false;
    }
    
    if (!Size().equals(container.Size())) {
      return false;
    }
    
    ForwardIterator<Data> iterator1 = FIterator();
    ForwardIterator<Data> iterator2 = container.FIterator();
    
    while (iterator1.IsValid() && iterator2.IsValid()) {
      Data data1 = iterator1.GetCurrent();
      Data data2 = iterator2.GetCurrent();
      
      if (data1 == null && data2 == null) {
        iterator1.Next();
        iterator2.Next();
        continue;
      }
      
      if (data1 == null || data2 == null || !data1.equals(data2)) {
        return false;
      }
      
      iterator1.Next();
      iterator2.Next();
    }
    
    return true;
  }
}