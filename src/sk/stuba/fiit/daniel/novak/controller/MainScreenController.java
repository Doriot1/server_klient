package sk.stuba.fiit.daniel.novak.controller;

import sk.stuba.fiit.daniel.novak.model.Context;
import sk.stuba.fiit.daniel.novak.view.ClientScreen;
import sk.stuba.fiit.daniel.novak.view.MainScreen;
import sk.stuba.fiit.daniel.novak.view.ServerScreen;

/**
 * Created by doriot on 10/25/15.
 */

public class MainScreenController {

    private ServerScreen serverScreen;
    private ClientScreen clientScreen;

    public MainScreenController (Context context, MainScreen mainScreen) {

        mainScreen.setMainScreenListener(new MainScreen.MainScreenListener() {
            @Override
            public void onButtonClient() {
                clientScreen = new ClientScreen();
                context.switchScene(clientScreen);
                new ClientScreenController(clientScreen, context);
            }

            @Override
            public void onButtonServer() {
                serverScreen = new ServerScreen();
                context.switchScene(serverScreen);
                new ServerScreenController(serverScreen, context);
            }
        });
    }

    public static void initAndStart(Context context, MainScreen mainScreen){
        new MainScreenController(context, mainScreen);
    }
}
