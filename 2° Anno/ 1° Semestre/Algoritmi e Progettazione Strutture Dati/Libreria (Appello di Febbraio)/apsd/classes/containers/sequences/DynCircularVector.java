package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynCircularVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.traits.Predicate;

public class DynCircularVector<Data> extends DynCircularVectorBase<Data> {

  public DynCircularVector() {
    this(1, 0, 0);
  }

  public DynCircularVector(long capacity) {
    this(capacity, 0, 0);
  }

  public DynCircularVector(Natural capacity) {
    this(capacity != null ? capacity.ToLong() : 1, 0, 0);
  }

  public DynCircularVector(long capacity, long size) {
    this(capacity, 0, size);
  }

  public DynCircularVector(Natural capacity, Natural size) {
    this(capacity != null ? capacity.ToLong() : 1, 
         0,
         size != null ? size.ToLong() : 0);
  }

  public DynCircularVector(TraversableContainer<Data> container) {
    this();
    if (container == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    container.TraverseForward(dat -> {
      InsertLast(dat);
      return false;
    });
  }

  public DynCircularVector(long capacity, long start, long size) {
    super(createArray(capacity), start, size);
  }

  protected DynCircularVector(Data[] array, long start, long size) {
    super(array, start, size);
  }

  @SuppressWarnings("unchecked")
  private static <Data> Data[] createArray(long capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Capacity cannot be negative!");
    }
    return (Data[]) new Object[(int)capacity];
  }

  @Override
  protected DynCircularVector<Data> NewVector(Data[] array) {
    return new DynCircularVector<>(array, 0, array.length);
  }

  @Override
  public boolean TraverseForward(Predicate<Data> predicate) {
    if (predicate == null) {
      return false;
    }
    for (int i = 0; i < size; i++) {
      long physicalIndex = (start + i) % arr.length;
      if (predicate.Apply(arr[(int)physicalIndex])) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean TraverseBackward(Predicate<Data> predicate) {
    if (predicate == null) {
      return false;
    }
    for (int i = (int)size - 1; i >= 0; i--) {
      long physicalIndex = (start + i) % arr.length;
      if (predicate.Apply(arr[(int)physicalIndex])) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean Exists(Data dat) {
    for (int i = 0; i < size; i++) {
      long physicalIndex = (start + i) % arr.length;
      Data current = arr[(int)physicalIndex];
      if (current == null && dat == null) {
        return true;
      }
      if (current != null && current.equals(dat)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean IsEmpty() {
    return size == 0;
  }
}