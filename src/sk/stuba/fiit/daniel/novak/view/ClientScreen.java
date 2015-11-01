package sk.stuba.fiit.daniel.novak.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Created by doriot on 10/25/15.
 */
public class ClientScreen extends GridPane {

    private ClientScreenListener clientScreenListener;

    private TextField dstIP1;
    private TextField fragment;
    private TextField port;
    private TextArea message;

    private Label labelFragment;
    private Label labelIP;
    private Label labelPort;
    private Label error;

    private Button button1;
    private Button button2;

    public ClientScreen() {
        init();
        add();
    }


    private void init() {
        setGridLinesVisible(false);
        dstIP1 = new TextField();

        fragment = new TextField("65508");
        message = new TextArea();
        port = new TextField("9001");

        labelIP = new Label("IP");
        labelPort = new Label("PORT");
        labelFragment = new Label("Fragment Size");
        error = new Label("Error message: ");

        button1 = new Button("Connect");
        button2 = new Button("SEND");

        button1.setOnAction(e -> clientScreenListener.onButtonBind());
        button2.setOnAction(e -> clientScreenListener.onButtonSend());
    }

    private void add() {
        add(dstIP1, 1, 1);

        add(fragment, 3, 3);

        add(labelIP, 1, 0);
        add(port, 1, 3);
        add(labelPort, 1, 2);
        add(labelFragment, 3, 2);
        add(error, 1, 11, 6, 1);

        add(button1, 1, 4);
        add(button2, 1, 9);

        add(message, 1, 5, 3, 3);
    }

    public TextField getDstIP1() {
        return dstIP1;
    }

    public TextField getPort() {
        return port;
    }

    public TextArea getMessage() {
        return message;
    }

    public TextField getFragment() {
        return fragment;
    }

    public Button getButton1() {
        return button1;
    }

    public void setButton1(Button button1) {
        this.button1 = button1;
    }

    public Label getError() {
        return error;
    }

    public void setError(Label error) {
        this.error = error;
    }

    public void setClientScreenListener(ClientScreenListener clientScreenListener) {
        this.clientScreenListener = clientScreenListener;
    }

    public interface ClientScreenListener {
        void onButtonBind();
        void onButtonSend();
    }
}
