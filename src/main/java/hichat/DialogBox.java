package hichat;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;


public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    public DialogBox(String s, Image i, boolean isUser, boolean isError) {
        text = new Label(s);
        displayPicture = new ImageView(i);

        // Styling chat bubbles
        text.setWrapText(true);
        text.setPadding(new Insets(10));
        text.setStyle("-fx-font-size: 14px; -fx-font-family: 'Segoe UI';");

        // Set background colors
        if (isError) {
            text.setBackground(new Background(new BackgroundFill(Color.LIGHTCORAL, new CornerRadii(10), Insets.EMPTY)));
        } else if (isUser) {
            text.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
        } else {
            text.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY)));
        }

        // Set image properties
        displayPicture.setFitWidth(40);
        displayPicture.setFitHeight(40);
        displayPicture.setStyle("-fx-background-radius: 50;");

        if (isUser) {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        } else {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(displayPicture, text);
        }
    }

    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i, true, false);
    }

    public static DialogBox getDukeDialog(String s, Image i) {
        return new DialogBox(s, i, false, false);
    }

    public static DialogBox getErrorDialog(String s, Image i) {
        return new DialogBox(s, i, false, true);
    }
}