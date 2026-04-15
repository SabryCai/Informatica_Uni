package apsd.interfaces.containers.iterators;

/** Interface: Iteratore arbitrario. */
public interface Iterator<Data> {
  boolean IsValid();

  void Reset();

  Data GetCurrent();

}
