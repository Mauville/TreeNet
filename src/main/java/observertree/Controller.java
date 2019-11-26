package observertree;

import javafx.fxml.FXML;

import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
    @Override

    public void update(Observable o, Object arg) {
        clear();
       render();
    }

    @FXML
    public void clear() {
    }

    private void render() {
    }
}
