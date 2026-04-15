package apsd.classes.containers.collections.abstractcollections;

import apsd.classes.containers.collections.abstractcollections.bases.WSetBase;
import apsd.classes.containers.collections.concretecollections.VList;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.Chain;

public class WSet<Data> extends WSetBase<Data, Chain<Data>> {
  
  public WSet() {
    super();
  }
  
  public WSet(Chain<Data> chn) {
    this();
    if (chn == null) {
      throw new NullPointerException("Chain cannot be null!");
    }
    chn.TraverseForward(dat -> {
      Insert(dat);
      return false;
    });
  }
  
  public WSet(TraversableContainer<Data> con) {
    this();
    if (con == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    con.TraverseForward(dat -> {
      Insert(dat);
      return false;
    });
  }
  
  public WSet(Chain<Data> chn, TraversableContainer<Data> con) {
    this();
    if (chn == null) {
      throw new NullPointerException("Chain cannot be null!");
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
    chn = new VList<>();
  }
}