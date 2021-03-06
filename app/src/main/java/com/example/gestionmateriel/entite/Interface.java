package com.example.gestionmateriel.entite;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Interface {
    private int id;
    private String nom;
    private String mac;

    @JsonProperty("adresse")
    private AdresseIp adresseIp;

    @JsonProperty("typeif")
    private TypeInterface typeInterface;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public AdresseIp getAdresseIp() { return adresseIp; }

    public void setAdresseIp(AdresseIp adresseIp) { this.adresseIp = adresseIp; }

    public TypeInterface getTypeInterface() { return typeInterface; }

    public void setTypeInterface(TypeInterface typeInterface) { this.typeInterface = typeInterface; }
}
