package tn.esprit.pidev.views;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import tn.esprit.pidev.entities.Planning;
import tn.esprit.pidev.entities.Spectacle;
import tn.esprit.pidev.services.SpectacleService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AdminAddSpectacle extends Form {
    SpectacleService spectacleService = new SpectacleService();

    public AdminAddSpectacle(Form previous) {
        setTitle("Add a Show");
        setLayout(BoxLayout.y());
        TextField titreTextField = new TextField("", "Titre");
        TextField genreTextField = new TextField("", "Genre");
        TextField imageTextField = new TextField("", "Image");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(new Date());
        Button addButton = new Button("Add Planning");
        addButton.addActionListener(l -> {
            if ((titreTextField.getText().length() == 0) || (genreTextField.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    java.util.Calendar calendar = java.util.Calendar.getInstance();
                    calendar.setTime(new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(datePicker.getDate() + ""));
                    java.sql.Date date = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(calendar.get(java.util.Calendar.YEAR) + "-" + (calendar.get(java.util.Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE)).getTime());
                    Spectacle spectacle = new Spectacle(titreTextField.getText(), date, genreTextField.getText(), imageTextField.getText());
                    spectacleService.addSpectacle(spectacle);
                    previous.showBack();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        addAll(titreTextField, genreTextField, imageTextField, datePicker, addButton);
//Back Button
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
