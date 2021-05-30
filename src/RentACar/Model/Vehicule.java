package RentACar.Model;

public class Vehicule {
    private String matricule;
    private String typeCarburant;
    private int kilometrage;
    private boolean located;
    private boolean boiteMan;
    private boolean clim;
    private String modele;

    @Override
    public String toString() {
        return  matricule +  modele + marque;
    }

    private String marque;
    private String categorie;

    public Vehicule(String matricule, String typeCarburant, int kilometrage, boolean located, boolean boiteMan, boolean clim, String modele, String marque, String categorie) {
        this.matricule = matricule;
        this.typeCarburant = typeCarburant;
        this.kilometrage = kilometrage;
        this.located = located;
        this.boiteMan = boiteMan;
        this.clim = clim;
        this.modele = modele;
        this.marque = marque;
        this.categorie = categorie;
    }
    public String getMatricule() {
        return matricule;
    }
    public String getTypeCarburant() {
        return typeCarburant;
    }
    public int getKilometrage() {
        return kilometrage;
    }
    public boolean isLocated() {
        return located;
    }
    public boolean isBoiteMan() {
        return boiteMan;
    }
    public boolean isClim() {
        return clim;
    }
    public String getModele() {
        return modele;
    }
    public String getMarque() {
        return marque;
    }
    public String getCategorie() {
        return categorie;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setTypeCarburant(String typeCarburant) {
        this.typeCarburant = typeCarburant;
    }

    public void setKilometrage(int kilometrage) {
        this.kilometrage = kilometrage;
    }

    public void setLocated(boolean located) {
        this.located = located;
    }

    public void setBoiteMan(boolean boiteMan) {
        this.boiteMan = boiteMan;
    }

    public void setClim(boolean clim) {
        this.clim = clim;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

}
