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
            comm.HelloWorld();
        } catch (PortConnectionException | IOException e) {
            e.printStackTrace();
        }
    }
}
