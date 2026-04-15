package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

public interface InsertableAtSequence<Data> extends Sequence<Data> {
  void InsertAt(Data dat, Natural index);

  default void InsertFirst(Data dat) {
    InsertAt(dat, Natural.ZERO);
  }
  
  default void InsertLast(Data dat) {
    InsertAt(dat, Size());
  }
}