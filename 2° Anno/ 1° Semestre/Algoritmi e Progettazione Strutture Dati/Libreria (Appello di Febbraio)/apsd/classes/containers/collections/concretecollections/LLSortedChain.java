package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.LLChainBase;
import apsd.classes.containers.collections.concretecollections.bases.LLNode;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.iterators.ForwardIterator;
import apsd.classes.utilities.Box;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.sequences.MutableSequence;
import apsd.interfaces.containers.base.TraversableContainer;

public class LLSortedChain<Data extends Comparable<? super Data>> extends LLChainBase<Data> implements SortedChain<Data> {

  public LLSortedChain() {
    super();
  }

  public LLSortedChain(LLSortedChain<Data> chain) {
    super(chain);
  }

  public LLSortedChain(TraversableContainer<Data> container) {
    super();
    if (container == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    container.TraverseForward(dat -> {
      Insert(dat);
      return false;
    });
  }

  public LLSortedChain(long size, LLNode<Data> head, LLNode<Data> tail) {
    super(size, head, tail);
  }

  @Override
  protected LLChainBase<Data> NewChain(long size, LLNode<Data> head, LLNode<Data> tail) {
    return new LLSortedChain<>(size, head, tail);
  }

  @Override
  public boolean Insert(Data dat) {
    LLNode<Data> newNode = new LLNode<>(dat);

    if (headref.Get() == null) {
      headref.Set(newNode);
      tailref.Set(newNode);
      size.Increment();
      return true;
    }

    if (dat == null || headref.Get().Get() == null || dat.compareTo(headref.Get().Get()) < 0) {
      newNode.SetNext(headref.Get());
      headref.Set(newNode);
      size.Increment();
      return true;
    }

    LLNode<Data> current = headref.Get();
    while (current.GetNext().Get() != null) {
      Data nextData = current.GetNext().Get().Get();
      if (nextData == null || dat.compareTo(nextData) < 0) {
        newNode.SetNext(current.GetNext().Get());
        current.SetNext(newNode);
        size.Increment();
        return true;
      }
      current = current.GetNext().Get();
    }

    current.SetNext(newNode);
    tailref.Set(newNode);
    size.Increment();
    return true;
  }

  protected LLNode<Data> PredFind(Data dat) {
    if (dat == null || headref.Get() == null) {
      return null;
    }

    LLNode<Data> current = headref.Get();
    LLNode<Data> predecessor = null;

    while (current != null) {
      Data currentData = current.Get();
      if (currentData != null && currentData.compareTo(dat) == 0) {
        return predecessor;
      }
      if (currentData != null && currentData.compareTo(dat) > 0) {
        return predecessor;
      }
      predecessor = current;
      current = current.GetNext().Get();
    }

    return predecessor;
  }

  protected LLNode<Data> PredPredFind(Data dat) {
    if (dat == null || headref.Get() == null) {
      return null;
    }

    LLNode<Data> pred = PredFind(dat);
    
    if (pred == null) {
      return null;
    }

    if (pred == headref.Get()) {
      return null;
    }

    LLNode<Data> current = headref.Get();
    while (current != null && current.GetNext().Get() != pred) {
      current = current.GetNext().Get();
    }
    return current;
  }

  protected LLNode<Data> PredSuccFind(Data dat) {
    if (dat == null || headref.Get() == null) {
      return null;
    }

    LLNode<Data> current = headref.Get();
    LLNode<Data> pred = null;

    while (current != null) {
      Data currentData = current.Get();
      if (currentData != null && currentData.compareTo(dat) > 0) {
        return pred;
      }
      pred = current;
      current = current.GetNext().Get();
    }

    return pred;
  }

  @Override
  public MutableSequence<Data> SubSequence(Natural from, Natural to) {
    return (MutableSequence<Data>) super.SubSequence(from, to);
  }
}