import com.fazecast.jSerialComm.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {

    static final int BAUD = 9600;
    static final int BUFF_SIZE = 200;
    static final int SLEEP = 1000;
    static final int TIMEOUT = 2000;

    public static void main(String[] args) throws IOException, InterruptedException {

        // Get port info from terminal
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter serial port: ");
        String PORT = scanner.nextLine();

        // Set up serial port and open streams
        SerialPort serialPort = SerialPort.getCommPort(PORT);
        serialPort.setBaudRate(BAUD);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, TIMEOUT, 0);
        serialPort.openPort();

        InputStream serialIn = serialPort.getInputStream();
        OutputStream serialOut = serialPort.getOutputStream();

        // Get message
        System.out.println("Message: ");
        String MESSAGE = scanner.nextLine();

        // Read arduino's first message
        byte[] readBuffer = new byte[BUFF_SIZE];
        if(serialIn.available() > 0) {
            int numRead = serialIn.read(readBuffer);
            System.out.println("Read " + numRead + " bytes: " + new String(readBuffer, 0, numRead));
        }

        // Send the message
        serialOut.write(MESSAGE.getBytes());
        serialOut.flush();
        Thread.sleep(SLEEP); // Wait for a response

        // Read response
        readBuffer = new byte[BUFF_SIZE];
        int numRead = serialIn.read(readBuffer);
        System.out.println("Read " + numRead + " bytes: " + new String(readBuffer, 0, numRead));
    }


}