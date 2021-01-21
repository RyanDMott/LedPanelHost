package com.towerccc.ledpanelhost;

import java.util.function.Consumer;

public class LedPanelWriter<T extends LedColor> {
    private int width;
    private int height;
    private byte[] buffer;
    private int bytesPerPixel;

    public LedPanelWriter( int panelHeight, int panelWidth, T initialColor) {
        // Assumes columns zig-zag.
        width = panelWidth;
        height = panelHeight;
        bytesPerPixel = initialColor.GetBytesPerPixel();

        buffer = new byte[width * height * bytesPerPixel];
        Fill(initialColor);
    }

    public void Fill(T color)
    {
        for (int i = 0; i < buffer.length; i++)
            buffer[i] = color.GetByte(i % bytesPerPixel);
    }

    public void SetPixel(int row, int column, T color){
        if (row < 0 || height <= row)
            throw new ArrayIndexOutOfBoundsException(row);

        if (column < 0 || width <= column)
            throw new ArrayIndexOutOfBoundsException(column);

        boolean isBackwardsColumn = (column % 2) == 1;

        int pixelIndex;

        if (isBackwardsColumn)
            // jump over an extra column and count backwards,
            // which means backing up one right away
            // so you don't start in the next column.
            pixelIndex = height * (column + 1) - 1 - row;
        else
            // jump over one column at a time
            pixelIndex = height * column + row;

        for (int byteIndex = 0; byteIndex < bytesPerPixel; byteIndex++)
            buffer[pixelIndex * bytesPerPixel + byteIndex] = color.GetByte(byteIndex);
    }

    public byte[] GetDataSnapshot(){
        // PERFORMANCE
        return buffer.clone();
    }
}
