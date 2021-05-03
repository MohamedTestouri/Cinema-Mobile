package tn.esprit.pidev.views;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import tn.esprit.pidev.entities.Spectacle;
import tn.esprit.pidev.services.SpectacleService;

import java.util.Date;

public class AdminEditSpectacle extends Form {
    SpectacleService spectacleService = new SpectacleService();
    public AdminEditSpectacle(Form previous, Spectacle spectacle) {
        System.out.println(spectacle.getId()+"");
        setTitle("Edit The Show");
        setLayout(BoxLayout.y());
        TextField titreTextField= new TextField(spectacle.getTitre(), "Titre");
        TextField genreTextField = new TextField(spectacle.getGenre(),"Genre");
        TextField imageTextField= new TextField(spectacle.getImage(), "Image");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(spectacle.getDate());
        Button editButton = new Button("Edit The Show");
        Button deleteButton = new Button("Delete The Show");
        editButton.addActionListener(l->{
            if((titreTextField.getText().length()==0)||(genreTextField.getText().length()==0)){
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            }else {
               // Spectacle spectacle = new Spectacle(titreTextField.getText(), datePicker.getDate(), genreTextField.getText(), imageTextField.getText());
                spectacle.setTitre(titreTextField.getText());
                spectacle.setGenre(genreTextField.getText());
                spectacle.setImage(imageTextField.getText());
                spectacle.setDate((java.sql.Date) datePicker.getDate());

                previous.showBack();
            }
        });
        deleteButton.addActionListener(l->{
            // get id and use spectacleService
            spectacleService.deleteSpectacle(spectacle.getId());
            previous.showBack();
        });
        addAll(titreTextField, genreTextField, imageTextField, datePicker, editButton, deleteButton);
//Back Button
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
