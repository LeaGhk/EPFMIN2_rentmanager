package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {
    private Long id;
    private Client client;
    private Vehicle vehicle;
    private LocalDate debut;
    private LocalDate fin;

    public Reservation(long id, Client client, Vehicle vehicle, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.client = client;
        this.vehicle = vehicle;
        this.debut = debut;
        this.fin = fin;
    }

    public long getId() {return id;}
    public Client getClient() {return client;}
    public Vehicle getVehicle() {return vehicle;}
    public LocalDate getDebut() {return debut;}
    public LocalDate getFin() {return fin;}

    public void setId(long id) {this.id = id;}
    public void setClient(Client client) {this.client = client;}
    public void setVehicle(Vehicle vehicle) {this.vehicle = vehicle;}
    public void setDebut(LocalDate debut) {this.debut = debut;}
    public void setFin(LocalDate fin) {this.fin = fin;}

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", client_id=" + client +
                ", vehicle_id=" + vehicle +
                ", debut=" + debut +
                ", fin=" + fin +
                '}';
    }
}
