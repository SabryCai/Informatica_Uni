package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.List;
import apsd.interfaces.containers.iterators.MutableBackwardIterator;
import apsd.interfaces.containers.iterators.MutableForwardIterator;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.MutableSequence;

/** Object: Concrete list implementation on (dynamic circular) vector. */
public class VList<Data> extends VChainBase<Data> implements List<Data> {

  public VList() {
    super();
  }

  public VList(TraversableContainer<Data> container) {
    super(container);
  }

  protected VList(DynVector<Data> vec) {
    super(vec);
  }

  @Override
  protected VChainBase<Data> NewChain(DynVector<Data> vec) {
    return new VList<>(vec);
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableIterableContainer         */
  /* ************************************************************************ */

  @Override
  public MutableForwardIterator<Data> FIterator() {
    return vec.FIterator();
  }

  @Override
  public MutableBackwardIterator<Data> BIterator() {
    return vec.BIterator();
  }

  /* ************************************************************************ */
  /* Override specific member functions from MutableSequence                  */
  /* ************************************************************************ */

  @Override
  public void SetAt(Data dat, Natural index) {
    vec.SetAt(dat, index);
  }

  @Override
  public void SetFirst(Data dat) {
    vec.SetFirst(dat);
  }

  @Override
  public void SetLast(Data dat) {
    vec.SetLast(dat);
  }

  @Override
  public MutableSequence<Data> SubSequence(Natural from, Natural to) {
    DynVector<Data> subVec = (DynVector<Data>) vec.SubSequence(from, to);
    return new VList<>(subVec);
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableAtSequence             */
  /* ************************************************************************ */

  @Override
  public void InsertAt(Data dat, Natural index) {
    vec.InsertAt(dat, index);
  }

  @Override
  public void InsertFirst(Data dat) {
    vec.InsertFirst(dat);
  }

  @Override
  public void InsertLast(Data dat) {
    vec.InsertLast(dat);
  }

  @Override
  public boolean Insert(Data dat) {
    vec.InsertFirst(dat);
    return true;
  }
}