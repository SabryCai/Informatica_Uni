package apsd.interfaces.containers.iterators;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Predicate;

public interface ForwardIterator<Data> extends Iterator<Data> {
  
  Data DataNNext();
  
  default void Next() {
    DataNNext();
  }
  
  default void Next(Natural steps) {
    if (steps == null) {
      return;
    }
    long n = steps.ToLong();
    for (long i = 0; i < n && IsValid(); i++) {
      Next();
    }
  }
  
  default void Next(long steps) {
    for (long i = 0; i < steps && IsValid(); i++) {
      Next();
    }
  }
  
  default boolean ForEachForward(Predicate<Data> fun) {
    if (fun == null) {
      return false;
    }
    while (IsValid()) {
      if (fun.Apply(DataNNext())) {
        return true;
      }
    }
    return false;
  }
}