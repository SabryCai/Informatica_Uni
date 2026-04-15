package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.SortedIterableContainer;

public interface SortedSequence<Data extends Comparable<? super Data>> extends Sequence<Data>, SortedIterableContainer<Data>{
  @Override
  default boolean Exists(Data dat) {
    if (dat == null) {
      return false;
    }
    if (IsEmpty()) {
      return false;
    }
    
    long left = 0;
    long right = Size().ToLong() - 1;
    
    while (left <= right) {
      long mid = left + (right - left) / 2;
      Data midVal = GetAt(Natural.Of(mid));
      
      if (midVal == null) {
        return false;
      }
      
      int cmp = midVal.compareTo(dat);
      
      if (cmp == 0) {
        return true;
      } else if (cmp < 0) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    
    return false;
  }
  
  @Override
  default Natural Search(Data dat) {
    if (dat == null) {
      return null;
    }
    if (IsEmpty()) {
      return null;
    }
    
    long left = 0;
    long right = Size().ToLong() - 1;
    
    while (left <= right) {
      long mid = left + (right - left) / 2;
      Data midVal = GetAt(Natural.Of(mid));
      
      if (midVal == null) {
        return null;
      }
      
      int cmp = midVal.compareTo(dat);
      
      if (cmp == 0) {
        return Natural.Of(mid);
      } else if (cmp < 0) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    
    return null;
  }
}