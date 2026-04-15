package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.InsertableAtSequence;
import apsd.interfaces.containers.sequences.MutableSequence;

public interface List<Data> extends MutableSequence<Data>, InsertableAtSequence<Data>, Chain<Data> {
  
  //SubList
  default List<Data> SubList(Natural from, Natural to) {
    return (List<Data>) SubSequence(from, to);
  }
  
  @Override
  default boolean Insert(Data dat) {
    InsertFirst(dat);
    return true;
  }
}