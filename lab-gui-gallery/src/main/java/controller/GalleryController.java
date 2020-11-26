package controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.Gallery;
import model.Photo;

public class GalleryController {

    private Gallery galleryModel;

    @FXML
    private TextField imageNameField;

    @FXML
    private ImageView imageView;

    @FXML
    private ListView<Photo> imagesListView;

    @FXML
    public void initialize() {
        imagesListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Photo item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView photoIcon = new ImageView(item.getPhotoData());
                    photoIcon.setPreserveRatio(true);
                    photoIcon.setFitHeight(50);
                    setGraphic(photoIcon);
                }
            }
        });
        imagesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null)
                imageNameField.textProperty().unbindBidirectional(oldValue.nameProperty());
            bindSelectedPhoto(newValue);
        });
    }

    public void setModel(Gallery gallery) {
        this.galleryModel = gallery;
        imagesListView.getSelectionModel().select(0);
        imagesListView.setItems((ObservableList<Photo>) gallery.getPhotos());
    }

    private void bindSelectedPhoto(Photo selectedPhoto) {
        imageNameField.textProperty().bindBidirectional(selectedPhoto.nameProperty());
        imageView.imageProperty().bind(selectedPhoto.photoDataProperty());
    }
}

