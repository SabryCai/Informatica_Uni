package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

abstract public class CircularVectorBase<Data> extends VectorBase<Data> {

  protected long start = 0L;

  protected CircularVectorBase(Data[] array, long start) {//CircularVectorBase
    super(array);
    if (start < 0 || (array.length > 0 && start >= array.length)) {
      throw new IllegalArgumentException("Invalid start index: " + start);
    }
    this.start = start;
  }

  //Clear
  @Override
  public void Clear() {
    for (int i = 0; i < arr.length; i++) {
      arr[i] = null;
    }
    start = 0;
  }

  //Realloc
  @Override
  public void Realloc(Natural capacity) {
    if (capacity == null) {
      throw new NullPointerException("Capacity cannot be null!");
    }
    long newCapacity = capacity.ToLong();
    if (newCapacity < 0) {
      throw new IllegalArgumentException("Capacity cannot be negative!");
    }
    
    @SuppressWarnings("unchecked")
    Data[] newArr = (Data[]) new Object[(int)newCapacity];
    
    long size = Size().ToLong();
    long copySize = Math.min(newCapacity, size);
    
    for (long i = 0; i < copySize; i++) {
      long physicalIndex = (start + i) % arr.length;
      newArr[(int)i] = arr[(int)physicalIndex];
    }
    
    arr = newArr;
    start = 0;
  }

  //GetAt
  @Override
  public Data GetAt(Natural index) {
    if (index == null) {
      throw new NullPointerException("Index cannot be null!");
    }
    long idx = index.ToLong();
    if (idx < 0 || idx >= Size().ToLong()) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size());
    }
    long physicalIndex = (start + idx) % arr.length;
    return arr[(int)physicalIndex];
  }

  //SetAt
  @Override
  public void SetAt(Data dat, Natural index) {
    if (index == null) {
      throw new NullPointerException("Index cannot be null!");
    }
    long idx = index.ToLong();
    if (idx < 0 || idx >= Size().ToLong()) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size());
    }
    long physicalIndex = (start + idx) % arr.length;
    arr[(int)physicalIndex] = dat;
  }

  //ArrayAlloc
  @Override
  protected void ArrayAlloc(Natural capacity) {
    if (capacity == null) {
      throw new NullPointerException("Capacity cannot be null!");
    }
    long cap = capacity.ToLong();
    if (cap < 0) {
      throw new IllegalArgumentException("Capacity cannot be negative!");
    }
    
    @SuppressWarnings("unchecked")
    Data[] newArr = (Data[]) new Object[(int)cap];
    arr = newArr;
    start = 0;
  }
}