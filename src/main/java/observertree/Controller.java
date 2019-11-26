package observertree;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import observabletree.AVLTree;
import observabletree.Node;

import java.awt.*;
import java.util.ArrayList;
import javafx.scene.control.Button;
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

    // Method gets called on every initialization of the FXMl
//    @FXML
//    public initialize(){
//        AVLTree.setObserver(this);
//    }

    @Override

    public void update(Observable o, Object arg) {
        clear();
        render();
    }

    @FXML
    public void clear() {
    }

    @FXML
    public void handleAdd() {
//       AVLTree.add(inputbox.getText());
    }

    private void render() {
    }
}
