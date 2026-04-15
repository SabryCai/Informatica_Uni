package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;

abstract public class LinearVectorBase<Data> extends VectorBase<Data> {

  protected LinearVectorBase(Data[] array) {//LinearVectorBase
    super(array);
  }

  @Override
  public void Clear() {//Clear
    for (int i = 0; i < arr.length; i++) {
      arr[i] = null;
    }
  }

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
    
    long copySize = Math.min(newCapacity, arr.length);
    for (int i = 0; i < copySize; i++) {
      newArr[i] = arr[i];
    }
    
    arr = newArr;
  }

  @Override
  public Data GetAt(Natural index) {//GetAt
    if (index == null) {
      throw new NullPointerException("Index cannot be null!");
    }
    long idx = index.ToLong();
    if (idx < 0 || idx >= Size().ToLong()) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size());
    }
    return arr[(int)idx];
  }

  @Override
  public void SetAt(Data dat, Natural index) {
    if (index == null) {
      throw new NullPointerException("Index cannot be null!");
    }
    long idx = index.ToLong();
    if (idx < 0 || idx >= Size().ToLong()) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size());
    }
    arr[(int)idx] = dat;
  }
  
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
  }
}