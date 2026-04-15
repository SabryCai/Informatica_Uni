package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

public interface BackwardIterator<Data> extends Iterator<Data> {  
  Data DataNPrev();
  
  default void Prev() {
    DataNPrev();
  }
  
  default void Prev(Natural steps) {
    if (steps == null) {
      return;
    }
    long n = steps.ToLong();
    for (long i = 0; i < n && IsValid(); i++) {
      Prev();
    }
  }
  
  default void Prev(long steps) {
    for (long i = 0; i < steps && IsValid(); i++) {
      Prev();
    }
  }
  
  default boolean ForEachBackward(Predicate<Data> fun) {
    if (fun == null) {
      return false;
    }
    while (IsValid()) {
      if (fun.Apply(DataNPrev())) {
        return true;
      }
    }
    return false;
  }
}