package sample.gameplay;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.OpeningWindow;

import java.io.IOException;

public class SignUp {

    @FXML
    TextField nam, pw1, pw2;

    public void registerClicked(ActionEvent event) throws IOException
        {
            String un = nam.getText();
            String p1 = pw1.getText();
            String p2 = pw2.getText();

            if (un == null || p1 == null || p2 == null) {

            } else {
                if (!p1.equals(p2)) {

                } else {
                    OpeningWindow.getgc().getOutToServer().println(un + "###@###" + p1);
                    OpeningWindow.getgc().getOutToServer().flush();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/gameplay/PlayOptions1.fxml"));
                    Region region = loader.load();
                    Group group = new Group(region);
                    StackPane pane = new StackPane();
                    pane.getChildren().add(group);

                    Scene scene = new Scene(pane);
                    group.scaleXProperty().bind(scene.widthProperty().divide(1280));
                    group.scaleYProperty().bind(scene.heightProperty().divide(720));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
            }
        }


}
