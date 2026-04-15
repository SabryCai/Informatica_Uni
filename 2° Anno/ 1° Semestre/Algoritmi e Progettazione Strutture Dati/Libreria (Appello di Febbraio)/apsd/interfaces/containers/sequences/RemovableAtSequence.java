package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Natural;

public interface RemovableAtSequence<Data> extends Sequence<Data> {
  
  Data AtNRemove(Natural index);
  
  default void RemoveAt(Natural index) {
    AtNRemove(index);
  }
  
  default void RemoveFirst() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    AtNRemove(Natural.ZERO);
  }
  
  default Data FirstNRemove() {
    return AtNRemove(Natural.ZERO);
  }
  
  default void RemoveLast() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    AtNRemove(Size().Decrement());
  }
  
  default Data LastNRemove() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    return AtNRemove(Size().Decrement());
  }
}