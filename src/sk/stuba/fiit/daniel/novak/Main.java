package sk.stuba.fiit.daniel.novak;

import javafx.application.Application;
import javafx.stage.Stage;
import sk.stuba.fiit.daniel.novak.controller.MainScreenController;
import sk.stuba.fiit.daniel.novak.model.Context;
import sk.stuba.fiit.daniel.novak.view.MainScreen;

/**
 * Created by doriot on 10/23/15.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Context context = new Context(primaryStage);
        MainScreen mainScreen = new MainScreen(context);
        context.switchScene(mainScreen);
        MainScreenController.initAndStart(context, mainScreen);
    }

    public static void main(String args[]) {
        launch();
    }
}
