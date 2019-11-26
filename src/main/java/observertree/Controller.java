package observertree;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.control.TextField;
import observabletree.AVLTree;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

    //31 StackPane
    @FXML
    private StackPane a_1;
    @FXML
    private StackPane b_0;
    @FXML
    private StackPane b_1;
    @FXML
    private StackPane c_0;
    @FXML
    private StackPane c_1;
    @FXML
    private StackPane c_2;
    @FXML
    private StackPane c_3;
    @FXML
    private StackPane d_0;
    @FXML
    private StackPane d_1;
    @FXML
    private StackPane d_2;
    @FXML
    private StackPane d_3;
    @FXML
    private StackPane d_4;
    @FXML
    private StackPane d_5;
    @FXML
    private StackPane d_6;
    @FXML
    private StackPane d_7;
    @FXML
    private StackPane e_0;
    @FXML
    private StackPane e_1;
    @FXML
    private StackPane e_2;
    @FXML
    private StackPane e_3;
    @FXML
    private StackPane e_4;
    @FXML
    private StackPane e_5;
    @FXML
    private StackPane e_6;
    @FXML
    private StackPane e_7;
    @FXML
    private StackPane e_8;
    @FXML
    private StackPane e_9;
    @FXML
    private StackPane e_10;
    @FXML
    private StackPane e_11;
    @FXML
    private StackPane e_12;
    @FXML
    private StackPane e_13;
    @FXML
    private StackPane e_14;
    @FXML
    private StackPane e_15;


    @FXML
    TextField inputbox;

    AVLTree<String> backtree = new AVLTree<>();

    // This runs before all other code and sets the observer for the observable tree
    @FXML
    void initialize() {
        backtree.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        clear();
        render();
    }

    // Reload the current FXML
    public void clear() {
        a_1.setVisible(false);
        b_0.setVisible(false);
        b_1.setVisible(false);
        c_0.setVisible(false);
        c_1.setVisible(false);
        c_2.setVisible(false);
        c_3.setVisible(false);
        d_0.setVisible(false);
        d_1.setVisible(false);
        d_2.setVisible(false);
        d_3.setVisible(false);
        d_4.setVisible(false);
        d_5.setVisible(false);
        d_6.setVisible(false);
        d_7.setVisible(false);
        e_0.setVisible(false);
        e_1.setVisible(false);
        e_2.setVisible(false);
        e_3.setVisible(false);
        e_4.setVisible(false);
        e_5.setVisible(false);
        e_6.setVisible(false);
        e_7.setVisible(false);
        e_8.setVisible(false);
        e_9.setVisible(false);
        e_10.setVisible(false);
        e_11.setVisible(false);
        e_12.setVisible(false);
        e_13.setVisible(false);
        e_14.setVisible(false);
        e_15.setVisible(false);
    }

    // Reload the current FXML and delete the tree vals
    @FXML
    public void handleClear() {
        backtree.clear();
        clear();
    }

    @FXML
    public void handleAdd() {
        backtree.add(inputbox.getText());
        inputbox.setText("");
    }

    @FXML
    public void handleDelete() {
        backtree.remove(inputbox.getText());
        inputbox.setText("");
    }

    private void render() {
    }
}
