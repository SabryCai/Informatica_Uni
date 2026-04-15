package apsd.interfaces.containers.collections;

public interface OrderedList<Data extends Comparable<? super Data>> extends List<Data>, OrderedChain<Data> {}