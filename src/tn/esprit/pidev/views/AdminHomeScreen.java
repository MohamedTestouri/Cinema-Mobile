package tn.esprit.pidev.views;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

public class AdminHomeScreen extends Form {
    Form current;
    public AdminHomeScreen(){
        current=this;
        setTitle("Admin Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button listSpectacleButton = new Button("Shows");
        Button planningButton = new Button("Planning");

        listSpectacleButton.addActionListener(e-> new AdminListSpectacleForm(current).show());
        planningButton.addActionListener(e-> new AdminListPlanningForm(current).show());
        addAll(listSpectacleButton,planningButton);

        //SIDE MENU
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {

        });
        getToolbar().addCommandToLeftSideMenu("Shows", null, (evt) -> {
            new AdminListSpectacleForm(current).show();
        });
        getToolbar().addCommandToLeftSideMenu("Planning", null, (evt) -> {
            new AdminListPlanningForm(current).show();
        });
    }
}
