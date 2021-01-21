package com.towerccc.ledpanelhost;

public class RgbColor implements LedColor {
    public byte Red;
    public byte Green;
    public byte Blue;

    public RgbColor(){
        Red = 0;
        Green = 0;
        Blue = 0;
    }

    public RgbColor(byte red, byte green, byte blue)
    {
        Red = red;
        Green = green;
        Blue = blue;
    }

    public RgbColor(int red, int green, int blue) {
        Red = (byte)red;
        Green = (byte)green;
        Blue = (byte)blue;
    }

    @Override
    public int GetBytesPerPixel() {
        return 3;
    }

    @Override
    public byte GetByte(int index) {
        if(index == 0)
            return Red;
        if(index == 1)
            return Green;
        if(index == 2)
            return Blue;
        else
            throw new ArrayIndexOutOfBoundsException(index);
    }
}
