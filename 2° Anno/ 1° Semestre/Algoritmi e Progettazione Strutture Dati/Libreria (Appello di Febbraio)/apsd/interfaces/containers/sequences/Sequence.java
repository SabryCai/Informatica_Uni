package apsd.interfaces.containers.sequences;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.IterableContainer;
import apsd.interfaces.containers.iterators.ForwardIterator;

/** Interface: IterableContainer con supporto alla lettura e ricerca tramite posizione. */
public interface Sequence<Data> extends IterableContainer<Data> { // Must extend IterableContainer
  default Data GetAt(Natural index) {
    long idx = ExcIfOutOfBound(index);
    ForwardIterator<Data> it = FIterator();
    for (long i = 0; i < idx; i++) {
      it.Next();
    }
    return it.GetCurrent();
  }

  default Data GetFirst() {
    return GetAt(Natural.ZERO);
  }

  default Data GetLast() {
    if (IsEmpty()) {
      throw new IndexOutOfBoundsException("Sequence is empty!");
    }
    return GetAt(Size().Decrement());
  }

  default Natural Search(Data dat) {
    ForwardIterator<Data> it = FIterator();
    long index = 0;
    while (it.IsValid()) {
      Data current = it.GetCurrent();
      if (current == null && dat == null) {
        return Natural.Of(index);
      }
      if (current != null && current.equals(dat)) {
        return Natural.Of(index);
      }
      it.Next();
      index++;
    }
    return null;
  }

  default boolean IsInBound(Natural index) {
    return (index != null) && (index.ToLong() < Size().ToLong());
  }

  default long ExcIfOutOfBound(Natural num) {
    if (num == null) {
      throw new NullPointerException("Natural number cannot be null!");
    }
    long idx = num.ToLong();
    if (idx >= Size().ToLong()) {
      throw new IndexOutOfBoundsException("Index out of bounds: " + idx + "; Size: " + Size() + "!");
    }
    return idx;
  }

  Sequence<Data> SubSequence(Natural from, Natural to);
}
