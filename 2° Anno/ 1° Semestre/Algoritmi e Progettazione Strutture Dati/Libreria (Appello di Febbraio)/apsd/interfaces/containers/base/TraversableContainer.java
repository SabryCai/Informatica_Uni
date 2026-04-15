package apsd.interfaces.containers.base;

import apsd.classes.utilities.Box;
import apsd.classes.utilities.MutableNatural;
import apsd.classes.utilities.Natural;
import apsd.interfaces.traits.Accumulator;
import apsd.interfaces.traits.Predicate;

public interface TraversableContainer<Data> extends MembershipContainer<Data> {

  @Override
  default Natural Size() {
    final MutableNatural count = new MutableNatural(0L);
    TraverseForward(dat -> {
      count.Increment();
      return false;
    });
    return count.ToNatural();
  }

  @Override
  default boolean Exists(Data dat) {
    final Box<Boolean> found = new Box<>(false);
    TraverseForward(current -> {
      if ((current == null && dat == null) || (current != null && current.equals(dat))) {
        found.Set(true);
        return true;
      }
      return false;
    });
    return found.Get();
  }

  //TraverseForward
  boolean TraverseForward(Predicate<Data> pred);
  
  //TraverseBackward
  boolean TraverseBackward(Predicate<Data> pred);
  
  default <Acc> Acc FoldForward(Accumulator<Data, Acc> fun, Acc ini) {
    final Box<Acc> acc = new Box<>(ini);
    if (fun != null)
      TraverseForward(dat -> {
        acc.Set(fun.Apply(dat, acc.Get()));
        return false;
      });
    return acc.Get();
  }
  
 default <Acc> Acc FoldBackward(Accumulator<Data, Acc> fun, Acc ini) {
    final Box<Acc> acc = new Box<>(ini);
    if (fun != null)
      TraverseBackward(dat -> {
        acc.Set(fun.Apply(dat, acc.Get()));
        return false;
      });
    return acc.Get();
  }
}