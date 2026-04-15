package apsd.classes.containers.sequences;

import apsd.classes.containers.sequences.abstractbases.CircularVectorBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.traits.Predicate;
import apsd.classes.utilities.Box;

public class CircularVector<Data> extends CircularVectorBase<Data> {

  public CircularVector() {
    this(0);
  }

  public CircularVector(long capacity) {
    super(createArray(capacity), 0);
  }

  public CircularVector(Natural capacity) {
    this(capacity != null ? capacity.ToLong() : 1);
  }

  public CircularVector(TraversableContainer<Data> container) {
    this(container != null ? container.Size().ToLong() : 1);
    if (container == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    final Box<Long> index = new Box<>(0L);
    container.TraverseForward(dat -> {
      long physicalIndex = (start + index.Get()) % arr.length;
      arr[(int)physicalIndex] = dat;
      index.Set(index.Get() + 1);
      return false;
    });
  }

  protected CircularVector(Data[] array, long start) {
    super(array, start);
  }

  @SuppressWarnings("unchecked")
  private static <Data> Data[] createArray(long capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Capacity cannot be negative!");
    }
    return (Data[]) new Object[(int)capacity];
  }

  @Override
  public Natural Size() {
    return Natural.Of(arr.length);
  }

  @Override
  public void Clear() {
    ArrayAlloc(new Natural(0));
    start = 0;
  }

  @Override
  protected CircularVector<Data> NewVector(Data[] array) {
    return new CircularVector<>(array, 0);
  }

  @Override
  public boolean TraverseForward(Predicate<Data> predicate) {
    if (predicate == null) {
      return false;
    }
    for (int i = 0; i < arr.length; i++) {
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
    for (int i = arr.length - 1; i >= 0; i--) {
      long physicalIndex = (start + i) % arr.length;
      if (predicate.Apply(arr[(int)physicalIndex])) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean Exists(Data dat) {
    for (int i = 0; i < arr.length; i++) {
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
    return arr.length == 0;
  }
}