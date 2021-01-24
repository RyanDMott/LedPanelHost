package com.towerccc.ledpanelhost;

import purejavacomm.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class ArduinoComm implements AutoCloseable {
    private SerialPort port;
    private OutputStream outputStream;
    private InputStream inputStream;

    final char STX = (char)2;
    final char ETX = (char)3;
    final char ENQ = (char)5;
    final char ACK = (char)6;
    final char NAK = (char)21;
    final char SYN = (char)22;

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
            port = (SerialPort) portId.open("LedPanelHost", 100);
        } catch (PortInUseException e) {
            throw new PortConnectionException(
                "Could not create connection to Arduino: Port " + portName + " already in use.",
                e
            );
        }

        try {
            port.setSerialPortParams(115200, 8, 1, SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {
            throw new PortConnectionException(
                    "Could not configure connection to Arduino.",
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

    public int WaitForFetchFrom(byte[] bytesOut){
        try{
            System.out.println("Flushing buffer");
            int available;
            while((available = inputStream.available()) > 0)
            {
                System.out.print(inputStream.read());
                System.out.print(available);
                System.out.print(",");
            }

            int b;

            System.out.println("Waiting.");
            while(true)
            {
                outputStream.write(SYN);
                if (inputStream.available() <= 0)
                    continue;
                b = inputStream.read();

                if (b == ENQ)
                    break;
                else
                    System.out.println(b);
            }


            outputStream.write(STX);

            outputStream.write(bytesOut);
            // inputStream.Write("abcdefghij");
            // inputStream.Write(STX.ToString() + "abcdefghij" + ETX.ToString());

            outputStream.write(ETX);

            System.out.println("Wrote");

            TimeUnit.MILLISECONDS.sleep(10);

            if (inputStream.available() <= 0)
            {
                System.out.println("No response");
            }
            else if ((b = inputStream.read()) == ACK)
            {
                System.out.println("Transmission successful");
                // System.out.println(inputStream.ReadLine());
            }
            else if (b == -1)
                System.out.println("Timed out waiting for acknowledge");
            else if (b == NAK)
            {
                System.out.println("Transmission complete but unsuccessful:");
                while(inputStream.available() > 0)
                    System.out.print((char)inputStream.read());
            }
            else
                System.out.println("Unexpected reply: " + b);

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public void HelloWorld() throws IOException {
        outputStream.write("abcd".getBytes());
        byte[] dataReceived = new byte[4];
        int back, i;
        for (i = 0; i < 4; i++){
            back = inputStream.read();
            if (back == -1)
                break;
            dataReceived[i] = (byte) back;
        }
        System.out.println(i);
        System.out.println(new String(dataReceived, StandardCharsets.UTF_8));
    }

    @Override
    public void close() {
        port.close();
    }
}