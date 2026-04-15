package apsd.interfaces.containers.base;

import apsd.classes.utilities.Natural;

public interface Container {
  
  //Size
  abstract Natural Size();
 
  //IsEmpty
  default boolean IsEmpty() {
    return Size().IsZero();
  }
}