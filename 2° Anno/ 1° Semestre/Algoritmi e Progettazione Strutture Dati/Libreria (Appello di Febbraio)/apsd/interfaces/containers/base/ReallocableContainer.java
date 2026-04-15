package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Reallocable;

public interface ReallocableContainer extends ClearableContainer, Reallocable{

  double GROW_FACTOR = 2.0;// FACTOR > 1 
  double SHRINK_FACTOR = 2.0;//FACTOR > 1

  //Capacity
  Natural Capacity();
  
  @Override
  default void Clear() {
    Realloc(Natural.ZERO);
  }
  
  default void Grow() {
    long newCapacity = (long)(Capacity().ToLong() * GROW_FACTOR);
    if (newCapacity > Capacity().ToLong()) {
      Realloc(Natural.Of(newCapacity));
    }
  }
    
  default void Grow(Natural n) {
    if (n == null) {
      return;
    }
    long requiredCapacity = Size().ToLong() + n.ToLong();
    long currCapacity = Capacity().ToLong();
    
    if (requiredCapacity > currCapacity) {
      long newCapacity = currCapacity;
      while (newCapacity < requiredCapacity) {
        newCapacity = (long)(newCapacity * GROW_FACTOR);
      }
      Realloc(Natural.Of(newCapacity));
    }
  }
  
  default void Shrink() {
    long currSize = Size().ToLong();
    long currCapacity = Capacity().ToLong();
    
    if (currSize * SHRINK_FACTOR * 2 <= currCapacity) {
      long newCapacity = (long)(currCapacity / SHRINK_FACTOR);
      if (newCapacity >= currSize && newCapacity < currCapacity) {
        Realloc(Natural.Of(newCapacity));
      }
    }
  }
  
  @Override
  Natural Size();
}