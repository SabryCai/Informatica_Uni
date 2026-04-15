package apsd.interfaces.containers.base;

public interface RemovableContainer<Data> extends Container {
  
  boolean Remove(Data dat);
  
  //RemoveAll
  default boolean RemoveAll(TraversableContainer<Data> container) {
    if (container == null) {
      return false;
    }
    
    final boolean[] removed = {false};
    container.TraverseForward(dat -> {
      if (Remove(dat)) {
        removed[0] = true;
      }
      return false;
    });
    
    return removed[0];
  }
  
  //RemoveSome
  default boolean RemoveSome(TraversableContainer<Data> container) {
    return RemoveAll(container);
  }
}