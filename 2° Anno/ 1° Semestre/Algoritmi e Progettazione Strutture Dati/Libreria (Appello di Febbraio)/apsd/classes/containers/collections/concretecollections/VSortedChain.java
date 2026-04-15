package apsd.classes.containers.collections.concretecollections;

import apsd.classes.containers.collections.concretecollections.bases.VChainBase;
import apsd.classes.utilities.Natural;
import apsd.interfaces.containers.base.TraversableContainer;
import apsd.interfaces.containers.collections.SortedChain;
import apsd.interfaces.containers.sequences.DynVector;
import apsd.interfaces.containers.sequences.MutableSequence;

/** Object: Concrete sorted chain implementation via (dynamic circular) vector. */
public class VSortedChain<Data extends Comparable<? super Data>> extends VChainBase<Data> implements SortedChain<Data> {

  public VSortedChain() {
    super();
  }

  public VSortedChain(VSortedChain<Data> chn) {
    this();
    if (chn == null) {
      throw new NullPointerException("SortedChain cannot be null!");
    }
    chn.TraverseForward(dat -> {
      Insert(dat);
      return false;
    });
  }

  public VSortedChain(TraversableContainer<Data> con) {
    this();
    if (con == null) {
      throw new NullPointerException("Container cannot be null!");
    }
    con.TraverseForward(dat -> {
      Insert(dat);
      return false;
    });
  }

  protected VSortedChain(DynVector<Data> vec) {
    super(vec);
  }

  @Override
  protected VChainBase<Data> NewChain(DynVector<Data> vec) {
    return new VSortedChain<>(vec);
  }

  /* ************************************************************************ */
  /* Override specific member functions from InsertableContainer              */
  /* ************************************************************************ */

  @Override
  public boolean Insert(Data dat) {
    long size = vec.Size().ToLong();

    if (size == 0) {
      vec.InsertLast(dat);
      return true;
    }

    for (long i = 0; i < size; i++) {
      Data current = vec.GetAt(Natural.Of(i));
      if (dat == null || current == null || dat.compareTo(current) < 0) {
        vec.InsertAt(dat, Natural.Of(i));
        return true;
      }
    }

    vec.InsertLast(dat);
    return true;
  }

  /* ************************************************************************ */
  /* Override specific member functions from Chain                            */
  /* ************************************************************************ */

  @SuppressWarnings("unchecked")
  @Override
  public MutableSequence<Data> SubSequence(Natural from, Natural to) {
    return (MutableSequence<Data>) NewChain((DynVector<Data>) vec.SubSequence(from, to));
  }
}