package tn.esprit.pidev.views;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import tn.esprit.pidev.entities.Planning;
import tn.esprit.pidev.services.FilmService;
import tn.esprit.pidev.services.PlanningService;
import tn.esprit.pidev.services.SalleService;
import tn.esprit.pidev.services.SpectacleService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AdminEditPlanning extends Form {
    SpectacleService spectacleService = new SpectacleService();
    SalleService salleService = new SalleService();
    PlanningService planningService = new PlanningService();
    FilmService filmService = new FilmService();

    public AdminEditPlanning(Form previous, Planning planning) {
        setTitle("Edit The Planning");
        setLayout(BoxLayout.y());

//PICKER TYPE
        Picker typePicker = new Picker();
        typePicker.setType(Display.PICKER_TYPE_STRINGS);
        typePicker.setStrings("Spectacle", "Film");
        typePicker.setSelectedString("Spectacle");


        // FILL THE PICKER WITH TITLES
        ArrayList<String> titreDataArrayList = new ArrayList<>();
        String[] titreData = new String[0];
        if (typePicker.getSelectedString().equals("Spectacle")) {
            for (int i = 0; i < spectacleService.showAll().size(); i++) {
                titreDataArrayList.add(spectacleService.showAll().get(i).getTitre());
            }
            titreData = new String[titreDataArrayList.size()];
            titreDataArrayList.toArray(titreData);
        } else {
            titreData = new String[filmService.showAll().size()];
            filmService.showAll().toArray(titreData);
        }

        Picker titrePicker = new Picker();
        titrePicker.setType(Display.PICKER_TYPE_STRINGS);
        titrePicker.setStrings(titreData);
        titrePicker.setSelectedString(titreData[0]); //GET THE FIRST ELEMENT

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
            try {
               java.util.Calendar calendar = java.util.Calendar.getInstance();
                calendar.setTime(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(datePicker.getDate() + ""));
                planning.setDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(calendar.get(java.util.Calendar.YEAR) + "-" + (calendar.get(java.util.Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE)).getTime()));
                planning.setHeureDebut(new java.sql.Time(new SimpleDateFormat("HH:mm").parse(heureDebutPicker.getTime() / 60 + ":" + heureDebutPicker.getTime() % 60).getTime()));
                planning.setHeureFin(new java.sql.Time(new SimpleDateFormat("HH:mm").parse(heureFinPicker.getTime() / 60 + ":" + heureFinPicker.getTime() % 60).getTime()));
                planning.setNomSalle(sallePicker.getSelectedString());
                planning.setTypeEvent(typePicker.getSelectedString());
                planning.setTitreEvent(titrePicker.getSelectedString());
                planningService.editPlanning(planning);
                previous.showBack();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        });
        deleteButton.addActionListener(l -> {
            planningService.deletePlanning(planning.getId());
            previous.showBack();
        });
        addAll(titrePicker, typePicker, sallePicker, datePicker, heureDebutPicker, heureFinPicker, editButton, deleteButton);


//Back Button
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
