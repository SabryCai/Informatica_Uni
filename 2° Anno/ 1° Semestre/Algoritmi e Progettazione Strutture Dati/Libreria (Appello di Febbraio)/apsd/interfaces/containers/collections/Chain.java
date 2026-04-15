package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.RemovableAtSequence;

public interface Chain<Data> extends Set<Data>, RemovableAtSequence<Data> {
  
  //Insert if absent
  default boolean InsertIfAbsent(Data dat) {
    if (!Exists(dat)) {
      return Insert(dat);
    }
    return false;
  }
  
  //Remove all occurrences
  default void RemoveOccurrences(Data dat) {
    while (Remove(dat)) {
    }
  }
  
  //SubChain
  default Chain<Data> SubChain(Natural from, Natural to) {
    return (Chain<Data>) SubSequence(from, to);
  }
  
  @Override
  default Natural Search(Data dat) {
      var itr = FIterator();
      long index = 0;
      while (itr.IsValid()) {
        Data current = itr.DataNNext();
        if ((current == null && dat == null) || (current != null && current.equals(dat))) {
          return Natural.Of(index);
        }
        index++;
      }
    return Size();
  }
}