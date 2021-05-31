package RentACar.Controller;

import RentACar.Model.Client;
import RentACar.Model.Vehicule;
import RentACar.Vue.Interface.ClientResearchObserver;
import RentACar.Vue.Interface.VehiculeResearchObserver;

import java.util.ArrayList;

public class Session{

    private static volatile Session instance = null;
    private String username;
    private ArrayList<Vehicule> vehiculesEntry;
    private ArrayList<Client> clientEntry;
    private ClientResearchObserver obs;
    private VehiculeResearchObserver obsv;
    private Client cliSel;
    private Vehicule vehSel;


    private Session(){
        vehiculesEntry = new ArrayList<>();
        clientEntry = new ArrayList<>();
        obs = null;
        obsv = null;
        cliSel = null;
        vehSel = null;
    }



    public static Session getInstance(){
        if (Session.instance == null){
            synchronized (Session.class){
                if (Session.instance == null){
                    Session.instance = new Session();
                }
            }
        }
        return Session.instance;
    }
    public void setClientResearchObserver(ClientResearchObserver obs){
        this.obs = obs;
    }

    public void setVehiculeResearchObserver(VehiculeResearchObserver obsv){
        this.obsv = obsv;
    }

    public void notifyResearchClientObserver(Client c){
        if (obs != null)
            obs.notify(c);
    }

    public void notifyResearchVehiculeObserver(Vehicule v){
        if (obsv != null){
            obsv.notify(v);
        }
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void selCli(int i){
        if (!clientEntry.isEmpty())
            cliSel = clientEntry.get(i);
        else
            cliSel = null;
    }
    public Client getCli(){
        if (!clientEntry.isEmpty()){
            return cliSel;
        }
        return null;
    }

    //select a vehicule
    public void selVeh(int i){
        if (!vehiculesEntry.isEmpty())
            vehSel = vehiculesEntry.get(i);
        else
            vehSel = null;
    }
    //get vehicule selection
    public Vehicule getVeh(){
        if (!vehiculesEntry.isEmpty()){
            return vehSel;
        }
        return null;
    }
    public ArrayList<Vehicule> getVehiculeEntry(){
        return vehiculesEntry;
    }

    public ArrayList<Client> getClientEntry(){
        return clientEntry;
    }

}
