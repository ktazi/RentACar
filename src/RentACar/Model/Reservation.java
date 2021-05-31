package RentACar.Model;

public class Reservation {
    private String dateRes;
    private int nbLoc;
    private Client c;
    private Vehicule v;

    public Reservation(String dateRes, int nbLoc, Client c, Vehicule v) {
        this.dateRes = dateRes;
        this.nbLoc = nbLoc;
        this.c = c;
        this.v = v;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "dateRes='" + dateRes + '\'' +
                ", nbLoc=" + nbLoc +
                ", c=" + c +
                ", v=" + v +
                '}';
    }

    public String getDateRes() {
        return dateRes;
    }

    public int getNbLoc() {
        return nbLoc;
    }

    public Client getC() {
        return c;
    }

    public Vehicule getV() {
        return v;
    }
}
