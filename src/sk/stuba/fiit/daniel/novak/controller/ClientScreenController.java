package sk.stuba.fiit.daniel.novak.controller;

import sk.stuba.fiit.daniel.novak.model.Client;
import sk.stuba.fiit.daniel.novak.model.Context;
import sk.stuba.fiit.daniel.novak.view.ClientScreen;
import sk.stuba.fiit.daniel.novak.view.MainScreen;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

/**
 * Created by doriot on 10/25/15.
 */
public class ClientScreenController {
    private Client client;

    public ClientScreenController(ClientScreen clientScreen, Context context) {

        client = new Client();

        clientScreen.setClientScreenListener(new ClientScreen.ClientScreenListener() {

            @Override
            public void onButtonBind() {
                if (client.isBound()) {
                    client.setBound(false);
                    clientScreen.getButton1().setText("Connect");
                    client.getDatagramSocket().close();
                } else {

                    try {
                        client.setDatagramSocket(new DatagramSocket());
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }

                    try {
                        client.setAddr(clientScreen.getDstIP1().getText());
                        client.setPort(Integer.parseInt(clientScreen.getPort().getText()));
                        client.setFragment(Integer.parseInt(clientScreen.getFragment().getText()));
                    } catch (NumberFormatException e) {
                        clientScreen.getError().setText("Error message: NumberFormatException, fill out all fields.");
                        return;
                    }
                    try {
                        client.setAddress(InetAddress.getByName(client.getAddr()));
                        client.setBound(true);
                        clientScreen.getButton1().setText("Disconnect");
                    } catch (UnknownHostException e) {
                        clientScreen.getError().setText("Error message: UnknownHostException, wrong IP format.");
                        throw new IllegalArgumentException();
                    }

                }
            }

            @Override
            public void onButtonSend() {
                if (clientScreen.getMessage().getText().equals(""))
                    return;
                if (clientScreen.getButton1().getText().equals("Disconnect")) {
                    client.setBuffer(clientScreen.getMessage().getText().getBytes());
                    send();
                    Arrays.fill(client.getBuffer(), (byte) 0);
                } else
                    clientScreen.getError().setText("Error message: Please connect to server first.");

            }

            @Override
            public void onButtonExit() {
                if (clientScreen.getButton1().getText().equals("Connect")) {
                    MainScreen mainScreen = new MainScreen(context);
                    context.switchScene(mainScreen);
                    new MainScreenController(context, mainScreen);
                }
                else
                    clientScreen.getError().setText("Error message: Please disconnect first before exiting.");
            }
        });
    }

    public void send() {

        if (client.getBuffer().length <= client.getFragmentSize()) {
            client.setDatagramPacket(new DatagramPacket(client.getBuffer(), client.getBuffer().length, client.getAddress(), client.getPort()));
            socket();

            // FRAGMENTACIA
        } else if (client.getBuffer().length > client.getFragmentSize()) {
            byte[] temp = new byte[client.getFragmentSize()];
            int i = 0;
            do {
                System.arraycopy(client.getBuffer(), i * client.getFragmentSize(), temp, 0, client.getFragmentSize());
                client.setDatagramPacket(new DatagramPacket(temp, client.getFragmentSize(), client.getAddress(), client.getPort()));
                socket();
                i++;
            } while (client.getBuffer().length > (client.getFragmentSize() * (i + 1)));

            Arrays.fill(temp, (byte) 0);
            System.arraycopy(client.getBuffer(), i * client.getFragmentSize(), temp, 0, client.getBuffer().length - (i * client.getFragmentSize()));
            client.setDatagramPacket(new DatagramPacket(temp, client.getFragmentSize(), client.getAddress(), client.getPort()));
            socket();
            Arrays.fill(temp, (byte) 0);
        }
    }

    private void socket() {
        try {
            client.getDatagramSocket().send(client.getDatagramPacket());
            System.out.println("packet sent");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
