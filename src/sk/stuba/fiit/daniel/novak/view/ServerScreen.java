package sk.stuba.fiit.daniel.novak.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * Created by doriot on 10/25/15.
 */
public class ServerScreen extends GridPane {

    public TextArea textArea;
    private TextField port;
    private Label label1;
    private Label error;
    private Button start;
    private Button exit;


    private ServerScreenListener serverScreenListener;

    public ServerScreen() {
        init();
        add();
    }

    private void init() {
        for (int i = 0; i < 10; i++) {
            getColumnConstraints().add(new ColumnConstraints(80));
            getRowConstraints().add(new RowConstraints(50));
        }
        textArea = new TextArea();
        port = new TextField();

        label1 = new Label("Port number:");
        error = new Label("Error message: ");

        start = new Button("BIND");
        exit = new Button("Exit");

        start.setOnAction(e -> serverScreenListener.onButtonReceive());
        exit.setOnAction(e -> serverScreenListener.onButtonExit());
        textArea.setEditable(false);
    }

    private void add() {
        add(textArea, 0, 2, 6, 5);
        add(error, 0, 7, 6, 1);
        add(start, 0, 1);
        add(label1, 0, 0, 2, 1);
        add(port, 1, 0);
        add(exit, 5, 7);
    }

    public Button getStart() {
        return start;
    }

    public void setStart(Button start) {
        this.start = start;
    }

    public TextField getPort() {
        return port;
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public Label getError() {
        return error;
    }

    public void setError(Label error) {
        this.error = error;
    }

    public void setServerScreenListener(ServerScreenListener serverScreenListener) {
        this.serverScreenListener = serverScreenListener;
    }

    public interface ServerScreenListener {
        void onButtonReceive();
        void onButtonExit();
    }
}
