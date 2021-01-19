package com.towerccc.ledpanelhost;

import com.towerccc.tutorial.Vector3;

import java.io.IOException;

public class IntegrationTestRunner {
    public static void main(String[] args) {
        TestConnection();
    }

    public static void TestConnection(){
        try(ArduinoComm comm = new ArduinoComm("COM3"))
        {
            // comm.HelloWorld();
            // comm.WaitForFetchFrom("abcdefghij".getBytes());
            byte[] buffer = new byte[8*32*3];
            for(int i = 0; i < buffer.length; i++)
                buffer[i] = (byte) (i % 256);
            comm.WaitForFetchFrom(buffer);
        } catch (PortConnectionException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
