package apsd.classes.containers.deqs;

import apsd.classes.containers.collections.concretecollections.VList;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.deqs.Queue;

/** Object: Wrapper queue implementation. */
public class WQueue<Data> implements Queue<Data> {
  
  protected final List<Data> lst;
  
  public WQueue() {
    this.lst = new VList<>();
  }
  
  public WQueue(List<Data> lst) {
    if (lst == null) {
      throw new NullPointerException("List cannot be null!");
    }
    this.lst = new VList<>();
    lst.TraverseForward(dat -> {
      this.lst.InsertLast(dat);
      return false;
    });
  }
  
  public WQueue(TraversableContainer<Data> con) {
    if (con == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    this.lst = new VList<>();
    con.TraverseForward(dat -> {
      this.lst.InsertLast(dat);
      return false;
    });
  }
  
  public WQueue(List<Data> lst, TraversableContainer<Data> con) {
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
  /* Override specific member functions from Queue                            */
  /* ************************************************************************ */
  
  @Override
  public Data Head() {
    if (IsEmpty()) {
      return null;
    }
    return lst.GetFirst();
  }

  @Override
  public void Dequeue() {
    if (IsEmpty()) {
      return;
    }
    lst.RemoveFirst();
  }

  @Override
  public Data HeadNDequeue() {
    if (IsEmpty()) {
      return null;
    }
    return lst.FirstNRemove();
  }
  
  @Override
  public void Enqueue(Data dat) {
    lst.InsertLast(dat);
  }
}