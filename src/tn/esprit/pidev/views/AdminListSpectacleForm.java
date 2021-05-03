package tn.esprit.pidev.views;

import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Spectacle;
import tn.esprit.pidev.services.SpectacleService;

import java.util.ArrayList;

public class AdminListSpectacleForm extends Form {
    Form current;
    SpectacleService spectacleService = new SpectacleService();
    ArrayList<Spectacle> spectacleArrayList = new ArrayList<>();

    public AdminListSpectacleForm(Form previous) {
        current = this;
        setTitle("Show List");
        setLayout(BoxLayout.y());
        spectacleArrayList = spectacleService.showAll();
        Container container = new Container(BoxLayout.y());
        container.setScrollableY(true);
        for (Spectacle spectacle : spectacleArrayList) {
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth / 3, deviceWidth / 4, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            Image image = URLImage.createToStorage(encImage, spectacle.getTitre() + spectacle.getId(), spectacle.getImage(), URLImage.RESIZE_SCALE_TO_FILL);
            MultiButton multiButton = new MultiButton(spectacle.getTitre() + "");
            multiButton.setTextLine2(spectacle.getGenre() + "");
//multiButton.setTextLine3(spectacle.getDate());
            multiButton.setIcon(image);
            multiButton.setUIID(spectacle.getId() + "");
            multiButton.addActionListener(l -> new AdminEditSpectacle(current, spectacle).show());
            container.add(multiButton);

        }

        addAll( container);

        //TOOLBAR BUTTONS
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        getToolbar().addCommandToOverflowMenu("Add", null, (evt) -> {
            new AdminAddSpectacle(current).show();
        });
        getToolbar().addCommandToOverflowMenu("Refresh", null, (evt) -> {
          //  new AdminListSpectacleForm(current).show();
        });
    }
}
