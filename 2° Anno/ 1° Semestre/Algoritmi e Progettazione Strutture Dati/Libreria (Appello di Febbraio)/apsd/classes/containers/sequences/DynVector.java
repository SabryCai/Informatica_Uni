package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.DynLinearVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.traits.Predicate;

public class DynVector<Data> extends DynLinearVectorBase<Data> {

  public DynVector() {
    this(1, 0);
  }

  //DynVector with initial capacity
  public DynVector(long capacity) {
    this(capacity, 0);
  }

  //DynVector with initial capacity
  public DynVector(Natural capacity) {
    this(capacity != null ? capacity.ToLong() : 1, 0);
  }

  //DynVector with initial capacity and size
  public DynVector(long capacity, long size) {
    super(createArray(capacity), size);
  }

  //DynVector with initial capacity and size
  public DynVector(Natural capacity, Natural size) {
    this(capacity != null ? capacity.ToLong() : 1, 
         size != null ? size.ToLong() : 0);
  }

  //DynVector from TraversableContainer
  public DynVector(TraversableContainer<Data> container) {
    this();
    if (container == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    container.TraverseForward(dat -> {
      InsertLast(dat);
      return false;
    });
  }

  //Protected DynVector
  protected DynVector(Data[] array, long size) {
    super(array, size);
  }

  @SuppressWarnings("unchecked")
  private static <Data> Data[] createArray(long capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Capacity cannot be negative!");
    }
    return (Data[]) new Object[(int)capacity];
  }

  @Override
  protected DynVector<Data> NewVector(Data[] array) {
    return new DynVector<>(array, array.length);
  }

  @Override
  public boolean TraverseForward(Predicate<Data> predicate) {
    if (predicate == null) {
      return false;
    }
    for (int i = 0; i < size; i++) {
      if (predicate.Apply(arr[i])) {
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
      if (predicate.Apply(arr[i])) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean Exists(Data dat) {
    for (int i = 0; i < size; i++) {
      if (arr[i] == null && dat == null) {
        return true;
      }
      if (arr[i] != null && arr[i].equals(dat)) {
        return true;
      }
    }
    return false;
  }

  public boolean IsEmpty() {
    return size == 0;
  }
}
