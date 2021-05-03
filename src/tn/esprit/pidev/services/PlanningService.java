package tn.esprit.pidev.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import tn.esprit.pidev.entities.Planning;
import tn.esprit.pidev.utils.Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlanningService {
    public ArrayList<Planning> planningArrayList;

    public static PlanningService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public PlanningService() {
        req = new ConnectionRequest();
    }

    public static PlanningService getInstance() {
        if (instance == null) {
            instance = new PlanningService();
        }
        return instance;
    }


    public boolean addPlanning(Planning planning) {
        String url = Database.BASE_URL + "planning/api/add"; // Add Symfony URL here
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Planning> parsePlanning(String jsonText) {
        try {
            planningArrayList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> planningListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) planningListJson.get("root");
            for (Map<String, Object> obj : list) {
                Planning planning = new Planning((int) Float.parseFloat(obj.get("id").toString()),obj.get("typeEvent").toString() ,obj.get("titreEvent").toString(),obj.get("nomSalle").toString());
                planningArrayList.add(planning);
            }
        } catch (IOException ex) {
        }
        return planningArrayList;
    }
    public boolean deletePlanning(int id){
        String url = Database.BASE_URL  + "planning/api/delete/"+id ; // Add Symfony URL here
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public ArrayList<Planning> showAll() {
        String url = Database.BASE_URL + "planning/api/show"; // Add Symfony URL Here
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                planningArrayList = parsePlanning(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return planningArrayList;
    }

}
