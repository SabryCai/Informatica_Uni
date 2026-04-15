package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.ResizableContainer;

public interface DynVector<Data> extends ResizableContainer, InsertableAtSequence<Data>, RemovableAtSequence<Data>, Vector<Data> {
  
  @Override
  Natural Size();
  
  @Override
  default void InsertAt(Data dat, Natural index) {
    if (index == null) {
      return;
    }
    long idx = index.ToLong();
    long size = Size().ToLong();
    
    if (idx > size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + size + "!");
    }
    
    ShiftRight(index, Natural.ONE);
    
    SetAt(dat, index);
  }
  
  @Override
  default Data AtNRemove(Natural index) {
    Data removed = GetAt(index);
    ShiftLeft(index, Natural.ONE);
    return removed;
  }
  
  @Override
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
      
      for (long i = 0; i < len; i++) {
        Reduce();
      }
    }
  }
  
  @Override
  default void ShiftRight(Natural pos, Natural num) {
    if (pos == null || num == null) {
      return;
    }
    
    long idx = pos.ToLong();
    long size = Size().ToLong();
    
    if (idx < 0 || idx > size) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + size + "!");
    }
    
    long len = num.ToLong();
    
    if (len > 0) {
      for (long i = 0; i < len; i++) {
        Expand();
      }
      
      size = Size().ToLong();
      for (long i = size - 1; i >= idx + len; i--) {
        SetAt(GetAt(Natural.Of(i - len)), Natural.Of(i));
      }
      
      for (long i = idx; i < idx + len && i < size; i++) {
        SetAt(null, Natural.Of(i));
      }
    }
  }
  
  @Override
  default DynVector<Data> SubVector(Natural from, Natural to) {
    return (DynVector<Data>) SubSequence(from, to);
  }
}