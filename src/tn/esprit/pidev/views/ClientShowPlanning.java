package tn.esprit.pidev.views;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Planning;


public class ClientShowPlanning extends Form {
    Form current;

    public ClientShowPlanning(Form previous, Planning planning) {
        current = this;
        setTitle("Planning Details");
        setLayout(BoxLayout.y());

        Label titreLabel = new Label("Titre: " + planning.getTitreEvent());
        Label typeLabel = new Label("Type: " + planning.getTypeEvent());
        Label salleLabel = new Label("Salle: " + planning.getNomSalle());

        Button buyButton = new Button("Buy Ticket");

        //ADD ALL COMPONENTS TO THE VIEW
        addAll(titreLabel, typeLabel, salleLabel, buyButton);

        // BACK BUTTON IN TOOLBAR
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
