package apsd.classes.containers.sequences.abstractbases;

import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.sequences.Vector;

abstract public class VectorBase<Data> implements Vector<Data> {

  protected Data[] arr;

  protected VectorBase(Data[] array) throws NullPointerException {
    if (array == null) {
      throw new NullPointerException("Array cannot be null!");
    }
    this.arr = array;
  }

  protected abstract VectorBase<Data> NewVector(Data[] array);

  @SuppressWarnings("unchecked")
  protected void ArrayAlloc(Natural newsize) {
    long size = newsize.ToLong();
    if (size >= Integer.MAX_VALUE) { throw new ArithmeticException("Overflow: size cannot exceed Integer.MAX_VALUE!"); }
    arr = (Data[]) new Object[(int) size];
  }
  
  public abstract Natural Size();

  public abstract void Clear();

  public Natural Capacity() {
    return Natural.Of(arr.length);
  }

  public MutableForwardIterator<Data> FIterator() {
    return new VectorForwardIterator();
  }

  public MutableBackwardIterator<Data> BIterator() {
    return new VectorBackwardIterator();
  }

  @SuppressWarnings("unchecked")
  public MutableSequence<Data> SubSequence(Natural from, Natural to) {
    if (from == null || to == null) {
      throw new NullPointerException("Indices cannot be null!");
    }
    long fromIdx = from.ToLong();
    long toIdx = to.ToLong();
    long size = Size().ToLong();
    
    if (fromIdx < 0 || toIdx > size || fromIdx > toIdx) {
      throw new IndexOutOfBoundsException("Invalid range: [" + fromIdx + ", " + toIdx + ") for size " + size);
    }
    
    long newSize = toIdx - fromIdx;
    
    Data[] newArr = (Data[]) new Object[(int)newSize];
    
    for (long i = 0; i < newSize; i++) {
      newArr[(int)i] = GetAt(Natural.Of(fromIdx + i));
    }
    
    return (MutableSequence<Data>) NewVector(newArr);
  }

  public abstract Data GetAt(Natural index);

  public abstract void SetAt(Data dat, Natural index);

  protected class VectorForwardIterator implements MutableForwardIterator<Data> {
    
    private long currentIndex;
    private long size;

    public VectorForwardIterator() {
      this.currentIndex = 0;
      this.size = Size().ToLong();
    }

    @Override
    public boolean IsValid() {
      return currentIndex < size;
    }

    @Override
    public void Reset() {
      currentIndex = 0;
      size = Size().ToLong();
    }

    @Override
    public Data GetCurrent() {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      return GetAt(Natural.Of(currentIndex));
    }

    @Override
    public void SetCurrent(Data dat) {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      SetAt(dat, Natural.Of(currentIndex));
    }

    @Override
    public Data DataNNext() {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      Data data = GetCurrent();
      currentIndex++;
      return data;
    }
  }

  protected class VectorBackwardIterator implements MutableBackwardIterator<Data> {
    
    private long currentIndex;

    public VectorBackwardIterator() {
      this.currentIndex = Size().ToLong() - 1;
    }

    @Override
    public boolean IsValid() {
      return currentIndex >= 0;
    }

    @Override
    public void Reset() {
      currentIndex = Size().ToLong() - 1;
    }

    @Override
    public Data GetCurrent() {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      return GetAt(Natural.Of(currentIndex));
    }

    @Override
    public void SetCurrent(Data dat) {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      SetAt(dat, Natural.Of(currentIndex));
    }

    @Override
    public Data DataNPrev() {
      if (!IsValid()) {
        throw new IndexOutOfBoundsException("Iterator is not valid!");
      }
      Data data = GetCurrent();
      currentIndex--;
      return data;
    }
  }
}