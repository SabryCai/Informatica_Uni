package apsd.interfaces.containers.collections;

public interface OrderedChain<Data extends Comparable<? super Data>> extends Chain<Data>, OrderedSet<Data> {} // Must extend Chain and OrderedSet
