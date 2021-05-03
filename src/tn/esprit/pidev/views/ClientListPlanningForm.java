package tn.esprit.pidev.views;

import com.codename1.components.MultiButton;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Planning;
import tn.esprit.pidev.services.PlanningService;

import java.util.ArrayList;

public class ClientListPlanningForm extends Form {
    Form current;
    PlanningService planningService = new PlanningService();
    ArrayList<Planning> planningArrayList = new ArrayList<>();

    public ClientListPlanningForm(Form previous){
        current = this;
        setTitle("Planning List");
        setLayout(BoxLayout.y());
        planningArrayList = planningService.showAll();
        Container container = new Container(BoxLayout.y());
        container.setScrollableY(true);
        for(Planning planning : planningArrayList){
            MultiButton multiButton = new MultiButton(planning.getTitreEvent()+"");
            multiButton.setTextLine2(planning.getTypeEvent());
            multiButton.setTextLine3(planning.getNomSalle());
            multiButton.setUIID(planning.getId() + "");
            multiButton.addActionListener(l -> new ClientShowPlanning(current, planning).show());
            container.add(multiButton);
        }
        add(container);
        //Back Button
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
