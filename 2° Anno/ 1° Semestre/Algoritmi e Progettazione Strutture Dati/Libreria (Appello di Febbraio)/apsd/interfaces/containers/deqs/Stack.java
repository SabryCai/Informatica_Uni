package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Stack<Data> extends ClearableContainer, InsertableContainer<Data> {
  
  Data Top();

  void Pop();
  
  void Push(Data dat);
  
  default Data TopNPop() {
    Data dat = Top();
    Pop();
    return dat;
  }
  
  default void SwapTop(Data dat) {
    Pop();
    Push(dat);
  }
  
  default Data TopNSwap(Data dat) {
    Data old = Top();
    Pop();
    Push(dat);
    return old;
  }
  
  @Override
  default void Clear() {
    while (!IsEmpty()) {
      Pop();
    }
  }
  
  @Override
  default boolean Insert(Data dat) {
    Push(dat);
    return true;
  }
}