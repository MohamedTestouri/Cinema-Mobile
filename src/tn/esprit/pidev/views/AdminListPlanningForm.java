package tn.esprit.pidev.views;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import tn.esprit.pidev.entities.Planning;
import tn.esprit.pidev.services.PlanningService;

import java.util.ArrayList;
import java.util.Collections;

public class AdminListPlanningForm extends Form {
    Form current;
    PlanningService planningService = new PlanningService();
    ArrayList<Planning> planningArrayList = new ArrayList<>();

    public AdminListPlanningForm(Form previous) {
        current = this;
        setTitle("Planning List");
        setLayout(BoxLayout.y());
        planningArrayList = planningService.showAll();
        Collections.reverse(planningArrayList);
        for (Planning planning : planningArrayList) {
            MultiButton multiButton = new MultiButton(planning.getTitreEvent() + "");
            multiButton.setTextLine4(planning.getTypeEvent());
            multiButton.setTextLine3(planning.getNomSalle());
            multiButton.setTextLine2(planning.getDate()+ "");
         //   multiButton.setTextLine5();
            multiButton.setUIID(planning.getId() + "");
            multiButton.addActionListener(l -> new AdminEditPlanning(current, planning).show());
add(multiButton);
        }


        //TOOLBAR BUTTONS
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        getToolbar().addCommandToOverflowMenu("Add", FontImage.createMaterial(FontImage.MATERIAL_ADD, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            new AdminAddPlanning(current).show();
        });
        getToolbar().addCommandToOverflowMenu("Refresh", FontImage.createMaterial(FontImage.MATERIAL_REFRESH, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            //  new AdminListSpectacleForm(current).show();
        });

    }
}
