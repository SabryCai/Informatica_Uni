package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;

/** Interface: ReallocableContainer che è espandibile e riducibile. */
public interface ResizableContainer extends ReallocableContainer { // Must extend ReallocableContainer

  double THRESHOLD_FACTOR = 2.0;

  @Override
  Natural Size();
  
  void Resize(Natural newSize);
  
  @Override
  default void Grow(Natural n) {//Where n is the dimension to grow
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
  
  @Override
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
  
  default void Expand() {
    Expand(Natural.ONE);
  }
  
  default void Expand(Natural n) {//Where n is the dimension to expand
    Natural newSize = Natural.Of(Size().ToLong() + n.ToLong());
    if (newSize.ToLong() > Capacity().ToLong()) Grow(n);
    Resize(newSize);
  }
  
  default void Reduce() {
    Reduce(Natural.ONE);
  }
  
  default void Reduce(Natural n) {//Where n is the dimension to reduce
    long currSize = Size().ToLong();
    long reduction = n.ToLong();
    
    if (reduction >= currSize) {
      Resize(Natural.ZERO);
    } else {
      Natural newSize = Natural.Of(currSize - reduction);
      Resize(newSize);
      
      long currCapacity = Capacity().ToLong();
      if (THRESHOLD_FACTOR * SHRINK_FACTOR * newSize.ToLong() <= currCapacity) {
        Shrink();
      }
    }
  }
}