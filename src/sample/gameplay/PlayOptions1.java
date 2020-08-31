package sample.gameplay;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.OpeningWindow;

import java.io.IOException;

public class PlayOptions1 {

    static int time;

    public static int getTime() {
        return time;
    }

    public void classicClicked(ActionEvent event) throws IOException {

        time = 0;

        OpeningWindow.getgc().getOutToServer().println("classic");
        OpeningWindow.getgc().getOutToServer().flush();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/gameplay/PlayOptions2.fxml"));
        Region region = loader.load();
        Group group = new Group(region);
        StackPane pane = new StackPane();
        pane.getChildren().add(group);

        Scene scene = new Scene(pane);
        group.scaleXProperty().bind(scene.widthProperty().divide(1280));
        group.scaleYProperty().bind(scene.heightProperty().divide(720));
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void qmClicked(ActionEvent event) throws IOException {

        time = 1;

        OpeningWindow.getgc().getOutToServer().println("classic");
        OpeningWindow.getgc().getOutToServer().flush();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/gameplay/PlayOptions2.fxml"));
        Region region = loader.load();
        Group group = new Group(region);
        StackPane pane = new StackPane();
        pane.getChildren().add(group);

        Scene scene = new Scene(pane);
        group.scaleXProperty().bind(scene.widthProperty().divide(1280));
        group.scaleYProperty().bind(scene.heightProperty().divide(720));
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    public void backbuttonClicked(ActionEvent event) throws IOException
    {

        OpeningWindow.getgc().getOutToServer().println("quick match");
        OpeningWindow.getgc().getOutToServer().flush();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/OpeningWindow.fxml"));
        Region region = loader.load();
        Group group = new Group(region);
        StackPane pane = new StackPane();
        pane.getChildren().add(group);

        Scene scene = new Scene(pane);
        group.scaleXProperty().bind(scene.widthProperty().divide(1280));
        group.scaleYProperty().bind(scene.heightProperty().divide(720));
        Stage stage = (Stage) ( (Node)event.getSource() ).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
