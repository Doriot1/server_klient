package sk.stuba.fiit.daniel.novak.controller;

import sk.stuba.fiit.daniel.novak.model.Server;
import sk.stuba.fiit.daniel.novak.view.ServerScreen;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

/**
 * Created by doriot on 10/27/15.
 * fragmenty - 1 - 65508
 * typy fragmentov: keepAlive, message, file, received, resend,
 */
public class ServerScreenController {

    private Server server;
    private DatagramSocket datagramSocket;
    private ServerThread serverThread;
    private ServerScreen serverScreen;

    public ServerScreenController(ServerScreen serverScreen) {
        this.serverScreen = serverScreen;
        server = new Server();

        serverScreen.setServerScreenListener(() -> {
            if (server.isBound()) {
                server.setBound(false);
                serverScreen.getStart().setText("BIND");
                serverThread.interrupt();
                server.setOpened(false);
                datagramSocket.close();
            } else {
                try {
                    server.setPort(Integer.parseInt(serverScreen.getPort().getText()));
                    server.setBound(true);
                    serverScreen.getStart().setText("UNBIND");
                    serverThread = new ServerThread();
                    serverThread.start();
                } catch (NumberFormatException e) {
                    serverScreen.getError().setText("Error message: NumberFormatException, please fill out the port number.");
                }
            }
        });
    }

    private class ServerThread extends Thread {

        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (!server.getIsOpened()) {
                    openSocket();
                    server.setOpened(true);
                }
                DatagramPacket datagramPacket = new DatagramPacket(server.getBuffer(), server.getBuffer().length);

                try {
                    datagramSocket.receive(datagramPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                server.setBuffer(datagramPacket.getData());
                String sprava = new String(server.getBuffer());
                serverScreen.getTextArea().appendText(sprava);
                Arrays.fill(server.getBuffer(), (byte) 0);
            }
        }
    }

    public void openSocket() {
        try {
            this.datagramSocket = new DatagramSocket(server.getPort());
            server.setOpened(true);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
