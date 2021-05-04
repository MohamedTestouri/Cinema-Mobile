package tn.esprit.pidev.views;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Planning;
import tn.esprit.pidev.services.PlanningService;

import java.util.ArrayList;

public class ClientOrderedListPlanningForm extends Form {
    Form current;
    PlanningService planningService = new PlanningService();
    ArrayList<Planning> planningArrayList = new ArrayList<>();

    public ClientOrderedListPlanningForm(Form previous) {
        current = this;
        setTitle("Planning List");
        setLayout(BoxLayout.y());

        planningArrayList = planningService.showOrdered();

        Button allButton = new Button("Display All");
        allButton.addActionListener(l -> new ClientListPlanningForm(null).show());
        add(allButton);

        showList();

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

    }

    private void showList() {
        for (Planning planning : planningArrayList) {
            MultiButton multiButton = new MultiButton();
            multiButton.setTextLine1(planning.getTitreEvent());
            multiButton.setTextLine2(planning.getTypeEvent());
            multiButton.setTextLine3(planning.getNomSalle());
            multiButton.setUIID(planning.getId() + "");
            multiButton.addActionListener(l -> new ClientShowPlanning(current, planning).show());
            // container.add(multiButton);
            add(multiButton);
        }
    }
}
