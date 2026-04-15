package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.LinearVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.traits.Predicate;
import apsd.classes.utilities.Box;

public class Vector<Data> extends LinearVectorBase<Data> {

  public Vector() {
    this(0);
  }

  //Vector long
  public Vector(long capacity) {
    super(createArray(capacity));
  }

  //Vector Natural
  public Vector(Natural capacity) {
    this(capacity != null ? capacity.ToLong() : 10);
  }

  //Vector TraversableContainer
  public Vector(TraversableContainer<Data> container) {
    this(container != null ? container.Size().ToLong() : 10);
    if (container == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    final Box<Long> index = new Box<>(0L);
    container.TraverseForward(dat -> {
      arr[(int)index.Get().longValue()] = dat;
      index.Set(index.Get() + 1);
      return false;
    });
  }

  //Protected Vector
  protected Vector(Data[] array) {
    super(array);
  }

  @SuppressWarnings("unchecked")
  private static <Data> Data[] createArray(long capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Capacity cannot be negative!");
    }
    return (Data[]) new Object[(int)capacity];
  }

  @Override
  public Natural Size() {//Size
    return Natural.Of(arr.length);
  }

  @Override
  public void Clear() {//Clear
    ArrayAlloc(new Natural(0));
  }

  @Override
  protected Vector<Data> NewVector(Data[] array) {
    return new Vector<>(array);
  }

  public boolean TraverseForward(Predicate<Data> predicate) {
    if (predicate == null) {
      return false;
    }
    for (int i = 0; i < arr.length; i++) {
      if (predicate.Apply(arr[i])) {
        return true;
      }
    }
    return false;
  }

  public boolean TraverseBackward(Predicate<Data> predicate) {
    if (predicate == null) {
      return false;
    }
    for (int i = arr.length - 1; i >= 0; i--) {
      if (predicate.Apply(arr[i])) {
        return true;
      }
    }
    return false;
  }

  public boolean Exists(Data dat) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == null && dat == null) {
        return true;
      }
      if (arr[i] != null && arr[i].equals(dat)) {
        return true;
      }
    }
    return false;
  }

  //IsEmpty
  public boolean IsEmpty() {
    return arr.length == 0;
  }
}