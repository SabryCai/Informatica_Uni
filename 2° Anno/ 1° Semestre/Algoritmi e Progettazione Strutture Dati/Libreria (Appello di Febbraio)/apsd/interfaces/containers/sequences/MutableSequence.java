package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.MutableIterableContainer;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

public interface MutableSequence<Data> extends Sequence<Data>, MutableIterableContainer<Data> {
  
  default void SetAt(Data dat, Natural index) {
    long idx = ExcIfOutOfBound(index);
    MutableForwardIterator<Data> it = (MutableForwardIterator<Data>) FIterator();
    for (long i = 0; i < idx; i++) {
      it.Next();
    }
    it.SetCurrent(dat);
  }
  
  default Data GetNSetAt(Data dat, Natural index) {
    Data old = GetAt(index);
    SetAt(dat, index);
    return old;
  }
  
  default void SetFirst(Data dat) {
    SetAt(dat, Natural.ZERO);
  }
  
  default Data GetNSetFirst(Data dat) {
    return GetNSetAt(dat, Natural.ZERO);
  }
  
  default void SetLast(Data dat) {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    SetAt(dat, Size().Decrement());
  }
  
  default Data GetNSetLast(Data dat) {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    return GetNSetAt(dat, Size().Decrement());
  }
  
  default void Swap(Natural index1, Natural index2) {
    Data temp = GetAt(index1);
    SetAt(GetAt(index2), index1);
    SetAt(temp, index2);
  }
  
  MutableSequence<Data> SubSequence(Natural from, Natural to);
}