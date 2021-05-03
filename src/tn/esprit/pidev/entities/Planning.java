package tn.esprit.pidev.entities;

import java.sql.Date;
import java.sql.Time;

public class Planning {
    private int id;
    private Date date;
    private Time heureDebut;
    private Time heureFin;
    private String typeEvent;
    private String titreEvent;
    private String nomSalle;

    public Planning() {
    }

    public Planning(int id, String typeEvent, String titreEvent, String nomSalle) {
        this.id = id;
        this.typeEvent = typeEvent;
        this.titreEvent = titreEvent;
        this.nomSalle = nomSalle;
    }

    public Planning(int id, Date date, Time heureDebut, Time heureFin, String typeEvent, String titreEvent, String nomSalle) {
        this.id = id;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.typeEvent = typeEvent;
        this.titreEvent = titreEvent;
        this.nomSalle = nomSalle;
    }

    public Planning(Date date, Time heureDebut, Time heureFin, String typeEvent, String titreEvent, String nomSalle) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.typeEvent = typeEvent;
        this.titreEvent = titreEvent;
        this.nomSalle = nomSalle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Time heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Time getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Time heureFin) {
        this.heureFin = heureFin;
    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }

    public String getTitreEvent() {
        return titreEvent;
    }

    public void setTitreEvent(String titreEvent) {
        this.titreEvent = titreEvent;
    }

    public String getNomSalle() {
        return nomSalle;
    }

    public void setNomSalle(String nomSalle) {
        this.nomSalle = nomSalle;
    }
}
