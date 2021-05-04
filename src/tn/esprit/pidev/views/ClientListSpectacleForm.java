package tn.esprit.pidev.views;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import tn.esprit.pidev.entities.Spectacle;
import tn.esprit.pidev.services.SpectacleService;

import java.util.ArrayList;
import java.util.Collections;

public class ClientListSpectacleForm extends Form {
    Form current;
    SpectacleService spectacleService = new SpectacleService();
    ArrayList<Spectacle> spectacleArrayList = new ArrayList<>();

    public ClientListSpectacleForm(Form previous) {
        current = this;
        setTitle("Spectacle List");
        setLayout(BoxLayout.y());
        spectacleArrayList = spectacleService.showAll();
        Collections.reverse(spectacleArrayList);
        for (Spectacle spectacle : spectacleArrayList) {
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage(deviceWidth / 3, deviceWidth / 4, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            Image image = URLImage.createToStorage(encImage, spectacle.getTitre() + spectacle.getId(), spectacle.getImage(), URLImage.RESIZE_SCALE);
            MultiButton multiButton = new MultiButton();
            multiButton.setTextLine1(spectacle.getTitre() + "");
            multiButton.setTextLine2(spectacle.getGenre() + "");
            multiButton.setIcon(image);
            multiButton.setUIID(spectacle.getId() + "");
            multiButton.addActionListener(l -> new ClientShowSpectacle(current, spectacle).show());
            add(multiButton);
        }
        getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                            line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);

                }
                getContentPane().animateLayout(150);
            }
        }, 4);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ClientHomeScreen().showBack());
        getToolbar().addCommandToOverflowMenu("Date order", FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new ClientOrderedListSpectacleForm(null).show();
        });
    }


}
