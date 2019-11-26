package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("AVL Tree");
        primaryStage.setScene(new Scene(root, 702, 483));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
