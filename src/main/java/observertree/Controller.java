package observertree;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import observabletree.AVLTree;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
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
