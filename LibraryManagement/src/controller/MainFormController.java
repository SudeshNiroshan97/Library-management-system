package controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    public ImageView imgBooks;
    public ImageView imgIssueBooks;
    public ImageView imgMembers;
    public ImageView imgHandleReturns;
    public AnchorPane anchorPainMainForm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(200), anchorPainMainForm);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

    }


    @FXML
    private void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
        }
    }

    @FXML
    private void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView icon = (ImageView) event.getSource();

            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }


    @FXML
    private void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent anchorPainMainForm = null;

            switch (icon.getId()) {
                case "imgBooks":
                    anchorPainMainForm = FXMLLoader.load(this.getClass().getResource("/view/AddBookForm.fxml"));
                    break;
                case "imgMembers":
                    anchorPainMainForm = FXMLLoader.load(this.getClass().getResource("/view/AddMemberForm.fxml"));
                    break;
                case "imgIssueBooks":
                    anchorPainMainForm = FXMLLoader.load(this.getClass().getResource("/view/IssueBookForm.fxml"));
                    break;
                case "imgHandleReturns":
                    anchorPainMainForm = FXMLLoader.load(this.getClass().getResource("/view/HandleReturns.fxml"));
                    break;
            }

            if (anchorPainMainForm != null) {
                Scene subScene = new Scene(anchorPainMainForm);
                Stage primaryStage = (Stage) this.anchorPainMainForm.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }
}
