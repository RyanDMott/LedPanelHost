package com.towerccc.ledpanelhost;

// https://docs.oracle.com/javase/8/docs/api/javax/imageio/package-summary.html

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.concurrent.TimeUnit;

import java.awt.image.ColorModel;
import java.io.File;
import java.io.IOException;

public class IntegrationTestRunner {
    public static void main(String[] args) {
        TestConnection();
    }

    public static void TestConnection(){
        BufferedImage image;
        try {
             image = ImageIO.read(new File("C:\\Users\\ryan\\source\\repos\\LedPanelHost\\data\\panel_scan_check.png"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        RgbColor color = new RgbColor();

        LedPanelWriter<RgbColor> panel = new LedPanelWriter<>(8,32, color);
        // panel.SetPixel(0, 1, new RgbColor(9, 0, 0));
        // ColorModel colorModel = image.getColorModel();
        try (ArduinoComm comm = new ArduinoComm("COM3")) {
            for (int shift = 0*8*32; shift >= 0 ; shift--) {
                for (int row = 0; row < image.getHeight(); row++) {
                    for (int column = 0; column < image.getWidth(); column++) {
                        Color colorBuiltin = new Color(image.getRGB(column, row));
                        color.Red = (byte) colorBuiltin.getRed();
                        color.Blue = (byte) colorBuiltin.getBlue();
                        color.Green = (byte) colorBuiltin.getGreen();
                        panel.SetPixel((7 - row) % 8, (31 - column + shift) % 32, color);
                    }
                }
                // comm.HelloWorld();
                // comm.WaitForFetchFrom("abcdefghij".getBytes());
                // byte[] buffer = new byte[8*32*3];
                // for(int i = 0; i < buffer.length; i++)
                //    buffer[i] = (byte) (i % 256);
                comm.WaitForFetchFrom(panel.GetDataSnapshot());

                TimeUnit.MILLISECONDS.sleep(250);
            }
        } catch (PortConnectionException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
