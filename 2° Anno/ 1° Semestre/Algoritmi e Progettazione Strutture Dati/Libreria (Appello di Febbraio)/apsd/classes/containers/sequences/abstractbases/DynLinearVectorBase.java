package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.sequences.DynVector;

abstract public class DynLinearVectorBase<Data> extends LinearVectorBase<Data> implements DynVector<Data> {

  protected long size = 0L;

  protected DynLinearVectorBase(Data[] array, long size) {//DynLinearVectorBase
    super(array);
    if (size < 0 || size > array.length) {
      throw new IllegalArgumentException("Invalid size: " + size);
    }
    this.size = size;
  }

  @Override
  public Natural Size() {
    return Natural.Of(size);
  }

  @Override
  public void Clear() {
    for (int i = 0; i < size; i++) {
      arr[i] = null;
    }
    size = 0;
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
    
    long copySize = Math.min(newCapacity, size);
    for (int i = 0; i < copySize; i++) {
      newArr[i] = arr[i];
    }
    
    arr = newArr;
    size = copySize;
  }

  //Resize
  public void Resize(Natural newSize) {
    if (newSize == null) {
      throw new NullPointerException("Size cannot be null!");
    }
    long newSizeVal = newSize.ToLong();
    if (newSizeVal < 0) {
      throw new IllegalArgumentException("Size cannot be negative!");
    }
    
    if (newSizeVal > arr.length) {
      throw new IllegalStateException("Cannot resize beyond capacity!");
    }
    
    if (newSizeVal < size) {
      for (long i = newSizeVal; i < size; i++) {
        arr[(int)i] = null;
      }
    }
    
    size = newSizeVal;
  }

  //Expand
  public void Expand(Natural n) {
    if (n == null) {//controllo della dimensione da espandere
      throw new NullPointerException("Natural cannot be null!");
    }
    long expansion = n.ToLong();
    if (expansion < 0) {
      throw new IllegalArgumentException("Expansion cannot be negative!");
    }
    
    long newSize = size + expansion;
    if (newSize >= arr.length) {
      long requiredCapacity = newSize;
      long currentCapacity = arr.length;
      long newCapacity = currentCapacity == 0 ? 1 : currentCapacity;
      
      while (newCapacity <= requiredCapacity) {
        newCapacity = newCapacity * 2;
        if (newCapacity <= 0) { // Overflow check
          newCapacity = requiredCapacity;
          break;
        }
      }
      
      Realloc(Natural.Of(newCapacity));
    }
    
    size = newSize;
  }

  public void Reduce(Natural n) {
    if (n == null) {//controllo della dimensione da ridurre
      throw new NullPointerException("Natural cannot be null!");
    }
    long reduction = n.ToLong();
    if (reduction < 0) {
      throw new IllegalArgumentException("Reduction cannot be negative!");
    }
    
    if (reduction >= size) {
      Clear();
    } else {
      long newSize = size - reduction;
      
      for (long i = newSize; i < size; i++) {
        arr[(int)i] = null;
      }
      
      size = newSize;
      
      long currentCapacity = arr.length;
      if (2.0 * 2.0 * newSize <= currentCapacity) {
        long newCapacity = (long)(currentCapacity / 2.0);
        if (newCapacity >= newSize && newCapacity < currentCapacity) {
          Realloc(Natural.Of(newCapacity));
        }
      }
    }
  }
}