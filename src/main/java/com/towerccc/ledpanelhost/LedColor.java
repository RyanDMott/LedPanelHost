package com.towerccc.ledpanelhost;

public interface LedColor {
    // This value must be the same for all instances of a given type.
    int GetBytesPerPixel();
    byte GetByte(int index);
}
