package tn.esprit.pidev.views;

import com.codename1.components.ImageViewer;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Spectacle;

public class ClientShowSpectacle extends Form {
    Form current;
    public ClientShowSpectacle(Form previous, Spectacle spectacle) {
        current = this;
        setTitle("Show Details");
        setLayout(BoxLayout.y());


        //Label right = new Label("Right", icon);
        //right.setTextPosition(Component.RIGHT);
        Label titreLabel = new Label("Titre: "+spectacle.getTitre());
        Label genreLabel = new Label("Genre: "+spectacle.getGenre());
       // Label date = new Label("Date: " + spectacle.getDate());

        //DISPLAY IMAGE
        String url = "http://awoiaf.westeros.org/images/thumb/9/93/AGameOfThrones.jpg/300px-AGameOfThrones.jpg";
        int deviceWidth = Display.getInstance().getDisplayWidth();
        Image placeholder = Image.createImage(deviceWidth, deviceWidth / 2, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        Image image = URLImage.createToStorage(encImage, spectacle.getTitre() + spectacle.getId(), url, URLImage.RESIZE_SCALE);
        ImageViewer imageViewer = new ImageViewer();
        imageViewer.setImage(image);

        //ADD ALL COMPONENTS TO THE VIEW
        addAll(imageViewer, titreLabel , genreLabel);

        // BACK BUTTON IN TOOLBAR
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
