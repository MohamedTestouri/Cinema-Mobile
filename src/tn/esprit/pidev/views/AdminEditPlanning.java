package tn.esprit.pidev.views;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.spinner.TimeSpinner;
import tn.esprit.pidev.entities.Planning;
import tn.esprit.pidev.services.PlanningService;
import tn.esprit.pidev.services.SalleService;
import tn.esprit.pidev.services.SpectacleService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdminEditPlanning extends Form {
    SpectacleService spectacleService = new SpectacleService();
    SalleService salleService = new SalleService();
    PlanningService planningService = new PlanningService();

    public AdminEditPlanning(Form previous, Planning planning) {
        setTitle("Edit The Planning");
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

        TextField typeTextField = new TextField(planning.getTypeEvent(), "Type Event");

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
        datePicker.setDate(planning.getDate());
        //TIME PICKER
        Picker heureDebutPicker = new Picker();
        heureDebutPicker.setType(Display.PICKER_TYPE_TIME);

        heureDebutPicker.setTime(Integer.parseInt(planning.getHeureDebut().toString().substring(0, 2)), Integer.parseInt(planning.getHeureDebut().toString().substring(3, 5)));
        //TIME PICKER
        Picker heureFinPicker = new Picker();
        heureFinPicker.setType(Display.PICKER_TYPE_TIME);
        heureFinPicker.setTime(Integer.parseInt(planning.getHeureFin().toString().substring(0, 2)), Integer.parseInt(planning.getHeureFin().toString().substring(3, 5)));
        Button editButton = new Button("Edit The Planning");
        Button deleteButton = new Button("Delete The Planning");
        editButton.addActionListener(l -> {
            if (typeTextField.getText().length() == 0) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    planning.setDate((java.sql.Date) datePicker.getDate());
                    planning.setHeureDebut(new java.sql.Time(new SimpleDateFormat("HH:mm").parse(heureDebutPicker.getTime() / 60 + ":" + heureDebutPicker.getTime() % 60).getTime()));
                    planning.setHeureFin(new java.sql.Time(new SimpleDateFormat("HH:mm").parse(heureFinPicker.getTime() / 60 + ":" + heureFinPicker.getTime() % 60).getTime()));
                    planning.setNomSalle(sallePicker.getSelectedString());
                    planning.setTypeEvent(typeTextField.getText());
                    planning.setTitreEvent(titrePicker.getSelectedString());
                    previous.showBack();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        deleteButton.addActionListener(l -> {
            // get id and use spectacleService
            planningService.deletePlanning(planning.getId());
            previous.showBack();
        });
        addAll(titrePicker, typeTextField, sallePicker, datePicker, heureDebutPicker, heureFinPicker, editButton, deleteButton);


//Back Button
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
