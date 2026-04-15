package apsd.interfaces.containers.collections;

import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.sequences.SortedSequence;
import apsd.interfaces.containers.iterators.ForwardIterator;

public interface SortedChain<Data extends Comparable<? super Data>> extends OrderedChain<Data>, SortedSequence<Data> {
  
  //Operations of Search
  default Natural SearchPredecessor(Data dat) {
    if (dat == null || IsEmpty()) {
      return null;
    }
    
    long left = 0;
    long right = Size().ToLong() - 1;
    long insertPos = 0;
    
    while (left <= right) {
      long mid = left + (right - left) / 2;
      Data midVal = GetAt(Natural.Of(mid));
      
      if (midVal == null) {
        return null;
      }
      
      int cmp = midVal.compareTo(dat);
      
      if (cmp < 0) {
        insertPos = mid + 1;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    
    if (insertPos == 0) {
      return null;
    }
    return Natural.Of(insertPos - 1);
  }
  
  default Natural SearchSuccessor(Data dat) {
    if (dat == null || IsEmpty()) {
      return null;
    }
    
    long left = 0;
    long right = Size().ToLong() - 1;
    long insertPos = Size().ToLong();
    
    while (left <= right) {
      long mid = left + (right - left) / 2;
      Data midVal = GetAt(Natural.Of(mid));
      
      if (midVal == null) {
        return null;
      }
      
      int cmp = midVal.compareTo(dat);
      
      if (cmp > 0) {
        insertPos = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    
    if (insertPos >= Size().ToLong()) {
      return null;
    }
    return Natural.Of(insertPos);
  }
  
  default void Intersection(SortedChain<Data> chain) {
    if (chain == null || chain.IsEmpty()) {
      Clear();
      return;
    }
    
    ForwardIterator<Data> it = FIterator();
    while (it.IsValid()) {
      Data current = it.GetCurrent();
      if (!chain.Exists(current)) {
        Remove(current);
        it.Reset();
      } else {
        it.Next();
      }
    }
  }
  
  @Override
  default Natural Search(Data dat) {
    if (dat == null || IsEmpty()) {
      return null;
    }
    
    long left = 0;
    long right = Size().ToLong() - 1;
    
    while (left <= right) {
      long mid = left + (right - left) / 2;
      Data midVal = GetAt(Natural.Of(mid));
      
      if (midVal == null) {
        return null;
      }
      
      int cmp = midVal.compareTo(dat);
      
      if (cmp == 0) {
        return Natural.Of(mid);
      } else if (cmp < 0) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    
    return null;
  }
  
  //Min & Max Operations
  @Override
  default Data Min() {
    if (IsEmpty()) {
      return null;
    }
    return GetFirst();
  }
  
  @Override
  default Data Max() {
    if (IsEmpty()) {
      return null;
    }
    return GetLast();
  }
  
  @Override
  default void RemoveMin() {
    RemoveFirst();
  }
  
  @Override
  default void RemoveMax() {
    RemoveLast();
  }
  
  @Override
  default Data MinNRemove() {
    return FirstNRemove();
  }
  
  @Override
  default Data MaxNRemove() {
    return LastNRemove();
  }
  
  //Predecessor & Successor Operations
  @Override
  default Data Predecessor(Data dat) {
    Natural predPos = SearchPredecessor(dat);
    if (predPos == null) {
      return null;
    }
    return GetAt(predPos);
  }
  
  @Override
  default Data Successor(Data dat) {
    Natural succPos = SearchSuccessor(dat);
    if (succPos == null) {
      return null;
    }
    return GetAt(succPos);
  }
  
  @Override
  default void RemovePredecessor(Data dat) {
    Natural predPos = SearchPredecessor(dat);
    if (predPos != null) {
      RemoveAt(predPos);
    }
  }
  
  @Override
  default void RemoveSuccessor(Data dat) {
    Natural succPos = SearchSuccessor(dat);
    if (succPos != null) {
      RemoveAt(succPos);
    }
  }
  
  @Override
  default Data PredecessorNRemove(Data dat) {
    Data pred = Predecessor(dat);
    if (pred != null) {
      RemovePredecessor(dat);
    }
    return pred;
  }
  
  @Override
  default Data SuccessorNRemove(Data dat) {
    Data succ = Successor(dat);
    if (succ != null) {
      RemoveSuccessor(dat);
    }
    return succ;
  }
}