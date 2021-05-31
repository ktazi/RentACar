package RentACar.Model;

public class Location {
    private Client client;
    private Vehicule vehicule;
    private String date;
    private int nb;
    private String assurance;

    public Location(Client client, Vehicule vehicule, String date, int nb, String assurance) {
        this.client = client;
        this.vehicule = vehicule;
        this.date = date;
        this.nb = nb;
        this.assurance = assurance;
    }

    @Override
    public String toString() {
        return "Location{" +
                "client=" + client +
                ", vehicule=" + vehicule +
                ", date='" + date + '\'' +
                ", nb=" + nb +
                ", assurance='" + assurance + '\'' +
                '}';
    }
}
