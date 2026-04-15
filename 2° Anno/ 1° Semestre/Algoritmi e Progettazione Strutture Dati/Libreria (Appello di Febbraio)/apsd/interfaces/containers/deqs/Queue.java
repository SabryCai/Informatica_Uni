package apsd.interfaces.containers.deqs;

import apsd.interfaces.containers.base.ClearableContainer;
import apsd.interfaces.containers.base.InsertableContainer;

public interface Queue<Data> extends ClearableContainer, InsertableContainer<Data> {
  
  Data Head();
  
  void Dequeue();
  
  void Enqueue(Data dat);
  
  default Data HeadNDequeue() {
    Data dat = Head();
    Dequeue();
    return dat;
  }
  
  @Override
  default void Clear() {
    while (!IsEmpty()) {
      Dequeue();
    }
  }
  
  @Override
  default boolean Insert(Data dat) {
    Enqueue(dat);
    return true;
  }
}