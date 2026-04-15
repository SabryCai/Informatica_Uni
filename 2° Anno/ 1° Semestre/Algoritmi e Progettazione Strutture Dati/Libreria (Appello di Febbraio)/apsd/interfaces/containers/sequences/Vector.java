package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ReallocableContainer;

public interface Vector<Data> extends ReallocableContainer, MutableSequence<Data> {
  
  @Override
  Natural Size();
  
  default void ShiftLeft(Natural pos) {
    ShiftLeft(pos, Natural.ONE);
  }
  
  default void ShiftLeft(Natural pos, Natural num) {
    if (pos == null || num == null) {
      return;
    }
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    
    len = Math.min(len, size - idx);
    
    if (len > 0) {
      for (long i = idx; i + len < size; i++) {
        SetAt(GetAt(Natural.Of(i + len)), Natural.Of(i));
      }
      for (long i = size - len; i < size; i++) {
        SetAt(null, Natural.Of(i));
      }
    }
  }
  
  default void ShiftFirstLeft() {
    if (!IsEmpty()) {
      ShiftLeft(Natural.ZERO, Natural.ONE);
    }
  }
  
  default void ShiftLastLeft() {
    if (!IsEmpty()) {
      ShiftLeft(Size().Decrement(), Natural.ONE);
    }
  }
  
  default void ShiftRight(Natural pos) {
    ShiftRight(pos, Natural.ONE);
  }
  
  default void ShiftRight(Natural pos, Natural num) {
    if (pos == null || num == null) {
      return;
    }
    long idx = ExcIfOutOfBound(pos);
    long size = Size().ToLong();
    long len = num.ToLong();
    
    len = Math.min(len, size - idx);
    
    if (len > 0) {
      for (long i = size - 1; i >= idx + len; i--) {
        SetAt(GetAt(Natural.Of(i - len)), Natural.Of(i));
      }
      for (long i = idx; i < idx + len && i < size; i++) {
        SetAt(null, Natural.Of(i));
      }
    }
  }
  
  default void ShiftFirstRight() {
    if (!IsEmpty()) {
      ShiftRight(Natural.ZERO, Natural.ONE);
    }
  }
  
  default void ShiftLastRight() {
    if (!IsEmpty()) {
      ShiftRight(Size().Decrement(), Natural.ONE);
    }
  }
  
  default Vector<Data> SubVector(Natural from, Natural to) {
    return (Vector<Data>) SubSequence(from, to);
  }
}