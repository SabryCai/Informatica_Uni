package apsd.interfaces.containers.base;

import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;

public interface MutableIterableContainer<Data> extends IterableContainer<Data>{  
  @Override
  MutableForwardIterator<Data> FIterator();
  
  @Override
  MutableBackwardIterator<Data> BIterator();
}