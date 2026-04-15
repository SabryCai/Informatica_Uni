package apsd.interfaces.containers.iterators;

/** Interface: Iteratore all'indietro mutabile. */
public interface MutableBackwardIterator<Data> extends BackwardIterator<Data>, MutableIterator<Data> {} // Must extend BackwardIterator and MutableIterator