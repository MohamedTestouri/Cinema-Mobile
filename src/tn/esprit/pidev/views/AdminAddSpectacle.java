package tn.esprit.pidev.views;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import tn.esprit.pidev.entities.Planning;
import tn.esprit.pidev.entities.Spectacle;

import java.util.Date;

public class AdminAddSpectacle extends Form {
    public AdminAddSpectacle(Form previous) {
        setTitle("Add a Show");
        setLayout(BoxLayout.y());
        TextField titreTextField= new TextField("", "Titre");
        TextField genreTextField = new TextField("","Genre");
        TextField imageTextField= new TextField("", "Image");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(new Date());
        Button addButton = new Button("Add Planning");
        addButton.addActionListener(l->{
            if((titreTextField.getText().length()==0)||(genreTextField.getText().length()==0)){
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            }else {
             //  Spectacle spectacle = new Spectacle(titreTextField.getText(), datePicker.getDate(), genreTextField.getText(), imageTextField.getText());
                System.out.println(datePicker.getDate());
                previous.showBack();
            }
        });
        addAll(titreTextField, genreTextField, imageTextField, datePicker, addButton);
//Back Button
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
