package sk.stuba.fiit.daniel.novak.controller;

import sk.stuba.fiit.daniel.novak.model.Context;
import sk.stuba.fiit.daniel.novak.model.Server;
import sk.stuba.fiit.daniel.novak.view.MainScreen;
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

    public ServerScreenController(ServerScreen serverScreen, Context context) {
        this.serverScreen = serverScreen;
        server = new Server();

        serverScreen.setServerScreenListener(new ServerScreen.ServerScreenListener() {
            @Override
            public void onButtonReceive() {
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
                        serverScreen.getError().setText("Error message: ");
                        serverThread = new ServerThread();
                        serverThread.start();
                    } catch (NumberFormatException e) {
                        serverScreen.getError().setText("Error message: NumberFormatException, please fill out the port number.");
                    }
                }
            }

            @Override
            public void onButtonExit() {

                if (serverScreen.getStart().getText().equals("BIND")) {
                    MainScreen mainScreen = new MainScreen(context);
                    context.switchScene(mainScreen);
                    new MainScreenController(context, mainScreen);
                } else
                    serverScreen.getError().setText("Error message: Please unbind first before exiting.");
            }

        });
    }

    private class ServerThread extends Thread {

        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                if (!server.getIsOpened()) {
                    openSocket();
                }

                DatagramPacket datagramPacket = new DatagramPacket(server.getBuffer(), server.getBuffer().length);

                try {
                    datagramSocket.receive(datagramPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte[] head = new byte[8];


                server.setBuffer(datagramPacket.getData());

                System.arraycopy(server.getBuffer(), 0, head, 0, 8);

                int[] pole = server.fromByteArray(head);
                //System.out.println(pole[0] + " " + " " + pole[1]);

                if(pole[0] == 1){
                    System.out.println("Prisiel packet typu MESSAGE.");
                    if(pole[1] == 0){
                        System.out.println("Tato sprava bola mensia ako velkost fragmentu.\n");
                    }
                }

                String sprava = new String(server.getBuffer());
                serverScreen.getTextArea().appendText(sprava);
                serverScreen.getTextArea().appendText("\n");
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
