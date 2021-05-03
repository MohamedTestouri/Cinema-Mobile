package tn.esprit.pidev.views;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import tn.esprit.pidev.entities.Planning;
import tn.esprit.pidev.services.SalleService;
import tn.esprit.pidev.services.SpectacleService;

import java.util.ArrayList;
import java.util.Date;

public class AdminAddPlanning extends Form {

    SpectacleService spectacleService = new SpectacleService();
    SalleService salleService = new SalleService();

    public AdminAddPlanning(Form previous) {
        setTitle("Add a Planning");
        setLayout(BoxLayout.y());
        // FILL THE PICKER WITH TITLES
        ArrayList<String> titreDataArrayList = new ArrayList<>();
        for (int i = 0; i < spectacleService.showAll().size(); i++) {
            titreDataArrayList.add(spectacleService.showAll().get(i).getTitre());
        }
        String[] titreData = new String[titreDataArrayList.size()];
        titreDataArrayList.toArray(titreData);
        Picker titrePicker = new Picker();
        titrePicker.setType(Display.PICKER_TYPE_STRINGS);
        titrePicker.setStrings(titreData);
        titrePicker.setSelectedString(titreData[0]); //GET THE FIRST ELEMENT

        TextField typeTextField = new TextField("", "Type Event");

        // FILL THE PICKER WITH SALLE NAME
        String[] salleNameData = new String[salleService.showAll().size()];
        salleService.showAll().toArray(salleNameData);
        Picker sallePicker = new Picker();
        sallePicker.setType(Display.PICKER_TYPE_STRINGS);
        sallePicker.setStrings(salleNameData);
        sallePicker.setSelectedString(salleNameData[0]); //GET THE FIRST ELEMENT

        //DATE PICKER
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(new Date());
        //TIME PICKER
        Picker heureDebutPicker = new Picker();
        heureDebutPicker.setType(Display.PICKER_TYPE_TIME);
        heureDebutPicker.setTime(10 * 60);
        //TIME PICKER
        Picker heureFinPicker = new Picker();
        heureFinPicker.setType(Display.PICKER_TYPE_TIME);
        heureFinPicker.setTime(10 * 60);
        Button addButton = new Button("Add Planning");
        addButton.addActionListener(l -> {
            if (typeTextField.getText().length() == 0) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                //  Spectacle spectacle = new Spectacle(titreTextField.getText(), datePicker.getDate(), genreTextField.getText(), imageTextField.getText());
                //  System.out.println(datePicker.getDate());
                previous.showBack();
            }
        });
        addAll(titrePicker, typeTextField, sallePicker, datePicker, heureDebutPicker, heureFinPicker, addButton);

//Back Button
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
