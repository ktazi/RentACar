package RentACar.Model;

public class Retour {
    private Vehicule vehicule;
    private String dateRes;
    private Integer retard;
    private String dommage;
    private Double prixDommage;

    public Retour( Vehicule v, String dateRes, Integer retard, String dommage, Double prixDommage) {
        this.vehicule = v;
        this.dateRes = dateRes;
        this.retard = retard;
        this.dommage = dommage;
        this.prixDommage = prixDommage;
    }

    public Vehicule getV() {
        return vehicule;
    }

    public String getDateRes() {
        return dateRes;
    }

    public Integer getRetard() {
        return retard;
    }

    public String getDommage() {
        return dommage;
    }

    public Double getPrixDommage() {
        return prixDommage;
    }

    @Override
    public String toString() {
        return "Retour{" +
                "v=" + vehicule +
                ", dateRes='" + dateRes + '\'' +
                ", retard=" + retard +
                ", dommage='" + dommage + '\'' +
                ", prixDommage=" + prixDommage +
                '}';
    }
}
