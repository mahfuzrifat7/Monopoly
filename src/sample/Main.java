package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.rmi.registry.Registry;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("OpeningWindow.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OpeningWindow.fxml"));
        Region region = loader.load();
        Group group = new Group(region);
        StackPane pane = new StackPane();
        pane.getChildren().add(group);

        /*URL resource = getClass().getResource("F:\\java\\PolyMono\\Happy - Upbeat.mp3");
        MediaPlayer a =new MediaPlayer(new Media(resource.toString()));
        a.setCycleCount(MediaPlayer.INDEFINITE);

        a.play();*/

        String musicfile = "src/Happy - Upbeat.mp3";
        Media sound = new Media(new File(musicfile).toURI().toString());
        MediaPlayer mediaplayer = new MediaPlayer(sound);
        mediaplayer.play();
        mediaplayer.setCycleCount(1000);

        stage.setTitle("Hello World");
        stage.getIcons().add(new Image("file:GameIcon.png"));

        Scene scene = new Scene(pane);
        group.scaleXProperty().bind(scene.widthProperty().divide(1280));
        group.scaleYProperty().bind(scene.heightProperty().divide(720));
        stage.setScene(scene);
        stage.show();


        /*Parent root = FXMLLoader.load(getClass().getResource("OpeningWindow.fxml"));
        stage.setTitle("Hello World");
        stage.getIcons().add(new Image("file:GameIcon.png"));
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();*/
    }

    public static void main(String[] args) {

        launch(args);

    }
}