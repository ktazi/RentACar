package RentACar.BDD;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class Input {

    public void chooseInput() throws FileNotFoundException, NoSuchAlgorithmException {

        Scanner in = new Scanner(new File("src/com/company/Input.txt"));
        String inputType = in.nextLine();
        String request;

        //TODO SQL INJECTION
        switch (inputType) {
            case "SignIn" -> {
                request = signIn(in);
                System.out.println(request);
            }
            case "AddClient" -> {
                request = addClient(in);
                System.out.println(request);
            }
            case "EditClient" -> {
                request = editClient(in);
                System.out.println(request);
            }
            case "DeleteClient" -> {
                request = deleteClient(in);
                System.out.println(request);
            }
            case "AddCar" -> {
                request = addCar(in);
                System.out.println(request);
            }
            case "EditCar" -> {
                request = editCar(in);
                System.out.println(request);
            }
            case "DeleteCar" -> {
                request = deleteCar(in);
                System.out.println(request);
            }
            case "FindClient" -> {
                request = findClient(in);
                System.out.println(request);
            }
            case "FindClientByNameSorted" -> {
                request = findClientByNameSorted(in);
                System.out.println(request);
            }
            case "ClientWithLocation" -> {
                request = "SELECT nom_client FROM client NATURAL JOIN loue";
                System.out.println(request);
            }
            case "ClientWithoutAnyLocation" -> {
                request = "SELECT c.nom_client FROM client c LEFT JOIN loue l ON c.id_clientWHERE";
            }
            case "ClientWithVehicule" -> {
                request = clientWitchVehicule(in);
                System.out.println(request);
            }
            case "FindCarByCategorie" -> {
                request = findCarByCategorie(in);
                System.out.println(request);
            }
            case "FindCarByBrand" -> {
                request = findCarByBrand(in);
                System.out.println(request);
            }
            case "FindCarInLocation" -> {
                request = "SELECT * FROM vehicule WHERE location = true";
                System.out.println(request);
            }
        }
    }

    public String signIn(Scanner in) throws NoSuchAlgorithmException {

        String login = in.nextLine();

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(in.nextLine().getBytes(StandardCharsets.UTF_8));
        String password = Base64.getEncoder().encodeToString(hash);

        return "SELECT * FROM employe WHERE loginEmploye = \"" + login + "\" and mdpEmploye = \"" + password + "\"";
    }

    //TODO Retrouver l'id client du dernier client pour le nouveau client
    public String addClient(Scanner in) {

        return "INSERT INTO client (idClient, nom_client, prenom_client, email_client, rue_client, ville_client, codePostal_client, numTel_client) VALUES ('" + 1 + "','" +
                in.nextLine() + "','" + in.nextLine() + "','" + in.nextLine() + "','" + in.nextLine() + "','" +
                in.nextLine() + "','" + in.nextLine()  + "','" + in.nextLine() + "');";
    }

    public String editClient(Scanner in) {
        String tempRequest = "UPDATE client SET ";
        return editConcat(tempRequest, in);
    }

    public String deleteClient(Scanner in) {
        return "DELETE FROM client WHERE idClient = '" + in.nextLine() + "'";
    }


    public String addCar(Scanner in) {
        return "INSERT INTO vehicules (matricule, kilometrage, location, boiteManuelle, climatise, typeCarburant, typeCategorie, idAgence, modele) VALUES ('" +
                in.nextLine() + "'," + in.nextLine() + "," + in.nextLine() + "," +
                in.nextLine() + "," + in.nextLine() + ",'" + in.nextLine() + "','" +
                in.nextLine() + "'," + in.nextLine()  + ",'" + in.nextLine() + "');";
    }

    public String editCar(Scanner in) {
        String tempRequest = "UPDATE vehicule SET ";
        return editConcat(tempRequest, in);
    }

    public String deleteCar(Scanner in) {
        return "DELETE FROM client WHERE matricule = '" + in.nextLine() + "'";
    }


    public String findClient(Scanner in) {
        return "SELECT id_client FROM client WHERE nom_client like '" + in.nextLine() + "%'";
    }

    public String findClientByNameSorted(Scanner in) {
        return "SELECT * FROM client WHERE nom_client like '" + in.nextLine() + "%' ORDER BY nom_client";
    }


    public String findCarByCategorie(Scanner in) {
        return "SELECT * FROM vehicule WHERE typeCategorie = '" + in.nextLine() + "' and location = false";
    }

    public String findCarByBrand(Scanner in) {
        return "SELECT * FROM vehicule NATURAL JOIN modele WHERE marque = '" + in.nextLine() + "'";
    }


    public String clientWitchVehicule(Scanner in) {
        return "SELECT nom_client FROM client NATURAL JOIN loue WHERE matricule = '" + in.nextLine() + "'";
    }

    public String editConcat(String tempRequest, Scanner in) {
        String input;
        int count = 0;

        while (in.hasNextLine()) {
            count++;
            input = in.nextLine();

            if (count % 2 == 1) {
                if (input.equals("idClient") || input.equals("matricule")){
                    tempRequest += " WHERE " + input + " = ";
                }
                else if (count == 1) {
                    tempRequest += input + " = ";
                }
                else {
                    tempRequest += ", " + input + " = ";
                }
            }
            else  {
                if (!in.hasNextLine()){
                    tempRequest += "'" + input + "';";
                }
                else if (input.equals("false") || input.equals("true")) {
                    tempRequest += input;
                }
                else {
                    tempRequest += "'" + input + "'";
                }
            }
        }

        return tempRequest;
    }
}

