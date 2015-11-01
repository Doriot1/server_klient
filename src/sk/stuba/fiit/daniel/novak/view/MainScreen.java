package sk.stuba.fiit.daniel.novak.view;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sk.stuba.fiit.daniel.novak.model.Context;

/**
 * Created by doriot on 10/23/15.
 */
public class MainScreen extends GridPane {

    private MainScreenListener mainScreenListener;

    private Label label;
    private Button button1;
    private Button button2;

    public MainScreen(Context context) {
        init();
        add();
    }

    private void init() {
        setPadding(new Insets(20));
        setGridLinesVisible(false);
        for (int i = 0; i < 10; i++) {
            getColumnConstraints().add(new ColumnConstraints(80));
            getRowConstraints().add(new RowConstraints(50));
        }
        button1 = new Button("Client");
        button2 = new Button("Server");
        label = new Label("Choose whether you are a Server or a Client");
        setHalignment(label, HPos.CENTER);

        button1.setOnAction(e -> mainScreenListener.onButtonClient());
        button2.setOnAction(e -> mainScreenListener.onButtonServer());

    }

    private void add() {

        add(label, 0, 0, 6, 1);
        add(button1, 1, 3);
        add(button2, 4, 3);

    }


    public void setMainScreenListener(MainScreenListener mainScreenListener) {
        this.mainScreenListener = mainScreenListener;
    }

    public interface MainScreenListener {
        void onButtonClient();

        void onButtonServer();
    }
}
