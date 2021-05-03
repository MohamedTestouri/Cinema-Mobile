package tn.esprit.pidev.views;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Spectacle;
import tn.esprit.pidev.services.SpectacleService;

import java.util.ArrayList;

public class ClientListSpectacleForm extends Form {
    Form current;
    SpectacleService spectacleService = new SpectacleService();
    ArrayList<Spectacle> spectacleArrayList = new ArrayList<>();

    public ClientListSpectacleForm(Form previous) {
        current = this;
        setTitle("Spectacle List");
        setLayout(BoxLayout.y());
        spectacleArrayList = spectacleService.showAll();
        Container container = new Container(BoxLayout.y());
        container.setScrollableY(true);
        for (Spectacle spectacle : spectacleArrayList) {
            //LOAD IMAGE
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth / 3, deviceWidth / 4, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            Image image = URLImage.createToStorage(encImage, spectacle.getTitre() + spectacle.getId(), spectacle.getImage(), URLImage.RESIZE_SCALE);
            MultiButton multiButton = new MultiButton();
            multiButton.setTextLine1(spectacle.getTitre() + "");
            multiButton.setTextLine2(spectacle.getGenre() + "");
//multiButton.setTextLine3(spectacle.getDate());
            multiButton.setIcon(image);
            multiButton.setUIID(spectacle.getId() + "");
            multiButton.addActionListener(l -> new ClientShowSpectacle(current, spectacle).show());
           // container.add(imageViewer);
            container.add(multiButton);
        }
        add(container);
        //Back Button
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
