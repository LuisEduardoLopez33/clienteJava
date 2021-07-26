package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Scene scene;
    private static Stage primaryStage;
    public static Stage secondStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        try {

            scene = new Scene(loadFXML("registroUser"));

            primaryStage.setScene(scene);
            primaryStage.setTitle("Login");
          //  primaryStage.setResizable(false);
            primaryStage.show();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void setFXML(String fxml, String title){
        try {
            scene.setRoot(loadFXML(fxml));
            primaryStage.sizeToScene();
            primaryStage.centerOnScreen();
            primaryStage.setTitle(title);
        } catch(Exception e){
            e.printStackTrace();
        }

    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/"+ fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void newStage(String fxml, String title) {
        try {
            secondStage = new Stage();
            Scene scene = new Scene(loadFXML(fxml));
            secondStage.setScene(scene);
            secondStage.setTitle(title);
            secondStage.initOwner(primaryStage);
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.centerOnScreen();
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}

