package tn.esprit.pidev.views;

import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import tn.esprit.pidev.entities.Planning;
import tn.esprit.pidev.services.PlanningService;
import tn.esprit.pidev.services.SalleService;
import tn.esprit.pidev.services.SpectacleService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AdminAddPlanning extends Form {

    SpectacleService spectacleService = new SpectacleService();
    SalleService salleService = new SalleService();
    PlanningService planningService = new PlanningService();

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

        //PICKER TYPE
        Picker typePicker = new Picker();
        typePicker.setType(Display.PICKER_TYPE_STRINGS);
        typePicker.setStrings("Spectacle", "Film");
        typePicker.setSelectedString("Spectacle");

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
            Planning planning = new Planning();
            try {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(datePicker.getDate() + ""));
                planning.setDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE)).getTime()));
                planning.setHeureDebut(new java.sql.Time(new SimpleDateFormat("HH:mm").parse(heureDebutPicker.getTime() / 60 + ":" + heureDebutPicker.getTime() % 60).getTime()));
                planning.setHeureFin(new java.sql.Time(new SimpleDateFormat("HH:mm").parse(heureFinPicker.getTime() / 60 + ":" + heureFinPicker.getTime() % 60).getTime()));
                planning.setNomSalle(sallePicker.getSelectedString());
                planning.setTypeEvent(typePicker.getSelectedString());
                planning.setTitreEvent(titrePicker.getSelectedString());
                  planningService.addPlanning(planning);
                 previous.showBack();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        addAll(titrePicker, typePicker, sallePicker, datePicker, heureDebutPicker, heureFinPicker, addButton);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
