package apsd.classes.containers.collections.abstractcollections;

import apsd.classes.containers.collections.abstractcollections.bases.WOrderedSetBase;
import apsd.classes.containers.collections.concretecollections.VSortedChain;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;

public class WOrderedSet<Data extends Comparable<? super Data>> extends WOrderedSetBase<Data, SortedChain<Data>> {
  
  public WOrderedSet() {
    super();
  } 
  
  public WOrderedSet(SortedChain<Data> chn) {
    this();
    if (chn == null) {
      throw new NullPointerException("SortedChain cannot be null!");
    }
    chn.TraverseForward(dat -> {
      Insert(dat);
      return false;
    });
  }
  
  public WOrderedSet(TraversableContainer<Data> con) {
    this();
    if (con == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    con.TraverseForward(dat -> {
      Insert(dat);
      return false;
    });
  }
  
  public WOrderedSet(SortedChain<Data> chn, TraversableContainer<Data> con) {
    this();
    if (chn == null) {
      throw new NullPointerException("SortedChain cannot be null!");
    }
    if (con == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    chn.TraverseForward(dat -> {
      Insert(dat);
      return false;
    });
    con.TraverseForward(dat -> {
      Insert(dat);
      return false;
    });
  }
  
  @Override
  protected void ChainAlloc() {
    chn = new VSortedChain<>();
  }
}