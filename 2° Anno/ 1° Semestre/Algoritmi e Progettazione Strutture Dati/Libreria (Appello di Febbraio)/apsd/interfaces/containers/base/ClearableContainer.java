package apsd.interfaces.containers.base;

import apsd.interfaces.traits.Clearable;

/** Interface: Container che è anche Clearable. */
public interface ClearableContainer extends Container, Clearable {} // Must extend Container, Clearable