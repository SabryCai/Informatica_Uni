package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Stack;

/** Object: Wrapper stack implementation. */
public class WStack<Data> implements Stack<Data> {
  
  protected final List<Data> lst;
  
  public WStack() {
    this.lst = new VList<>();
  }
  
  public WStack(List<Data> lst) {
    if (lst == null) {
      throw new NullPointerException("List cannot be null!");
    }
    this.lst = new VList<>();
    lst.TraverseForward(dat -> {
      this.lst.InsertLast(dat);
      return false;
    });
  }
  
  public WStack(TraversableContainer<Data> con) {
    if (con == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    this.lst = new VList<>();
    con.TraverseForward(dat -> {
      this.lst.InsertLast(dat);
      return false;
    });
  }
  
  public WStack(List<Data> lst, TraversableContainer<Data> con) {
    if (lst == null) {
      throw new NullPointerException("List cannot be null!");
    }
    if (con == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    this.lst = new VList<>();
    lst.TraverseForward(dat -> {
      this.lst.InsertLast(dat);
      return false;
    });
    con.TraverseForward(dat -> {
      this.lst.InsertLast(dat);
      return false;
    });
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from Container                        */
  /* ************************************************************************ */
  
  @Override
  public Natural Size() {
    return lst.Size();
  }
  
  @Override
  public boolean IsEmpty() {
    return lst.IsEmpty();
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from ClearableContainer               */
  /* ************************************************************************ */
  
  @Override
  public void Clear() {
    lst.Clear();
  }
  
  /* ************************************************************************ */
  /* Override specific member functions from Stack                            */
  /* ************************************************************************ */
  
  @Override
  public Data Top() {
    if (IsEmpty()) {
      return null;
    }
    return lst.GetLast();
  }

  @Override
  public void Pop() {
    if (IsEmpty()) {
      return;
    }
    lst.RemoveLast();
  }

  @Override
  public Data TopNPop() {
    if (IsEmpty()) {
      return null;
    }
    return lst.LastNRemove();
  }
  
  @Override
  public void SwapTop(Data dat) {
    if (IsEmpty()) {
      return;
    }
    lst.SetLast(dat);
  }
  
  @Override
  public Data TopNSwap(Data dat) {
    if (IsEmpty()) {
      return null;
    }
    return lst.GetNSetLast(dat);
  }
  
  @Override
  public void Push(Data dat) {
    lst.InsertLast(dat);
  }
}