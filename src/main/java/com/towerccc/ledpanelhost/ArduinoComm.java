package com.towerccc.ledpanelhost;

import purejavacomm.NoSuchPortException;
import purejavacomm.PortInUseException;
import purejavacomm.SerialPort;
import purejavacomm.CommPortIdentifier;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.charset.StandardCharsets;

public class ArduinoComm implements AutoCloseable {
    private SerialPort port;
    private OutputStream outputStream;
    private InputStream inputStream;

    public ArduinoComm(String portName) throws PortConnectionException {
        CommPortIdentifier portId;
        try {
            portId = CommPortIdentifier.getPortIdentifier(portName);
        } catch (NoSuchPortException e) {
            throw new PortConnectionException(
                    "Could not create connection to Arduino: Port " + portName + " not found.",
                    e
            );
        }

        try {
            port = (SerialPort) portId.open("LedPanelHost", 5000);
        } catch (PortInUseException e) {
            throw new PortConnectionException(
                "Could not create connection to Arduino: Port " + portName + " already in use.",
                e
            );
        }

        try {
            outputStream = port.getOutputStream();
            inputStream = port.getInputStream();
        }catch (IOException e) {
            throw new PortConnectionException(
                    "Could not create connection to Arduino: stream creation failed:",
                    e
            );
        }

        if (outputStream == null || inputStream == null)
            throw new PortConnectionException(
                    "Could not create connection to Arduino: stream creation failed by returning null"
            );
    }

    public void HelloWorld() throws IOException {
        outputStream.write("abcd".getBytes());
        byte[] dataReceived = new byte[4];
        int count = inputStream.read(dataReceived, 0, 4);
        System.out.println(count);
        System.out.println(new String(dataReceived, StandardCharsets.UTF_8));
    }

    @Override
    public void close() {
        port.close();
    }
}