package RentACar.Model;

public class Client {
    private String nom;
    private String prenom;
    private String email;
    private String rue;
    private String ville;
    private int codePostal;
    private String numTel;

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRue(String rue) {
        this.rue = rue;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }
    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public Client(String nom, String prenom, String email, String rue, String ville, int codePostal, String numTel, int id) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.numTel = numTel;
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getEmail() {
        return email;
    }
    public String getRue() {
        return rue;
    }
    public String getVille() {
        return ville;
    }
    public int getCodePostal() {
        return codePostal;
    }
    public String getNumTel() {
        return numTel;
    }

    public static class ClientBuilder{
        private String nom;
        private String prenom;
        private String email;
        private String rue;
        private String ville;
        private int codePostal;
        private String numTel;
        private int id;

        public ClientBuilder(){
            this.nom="";
            this.prenom="";
            this.email="";
            this.rue="";
            this.ville="";
            this.numTel="";
            this.codePostal=0;
            this.id=0;
        }

        public ClientBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }
        public ClientBuilder setId(int id) {
            this.id = id;
            return this;
        }
        public ClientBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public ClientBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ClientBuilder setRue(String rue) {
            this.rue = rue;
            return this;
        }

        public ClientBuilder setVille(String ville) {
            this.ville = ville;
            return this;
        }

        public ClientBuilder setCodePostal(int codePostal) {
            this.codePostal = codePostal;
            return this;
        }

        public ClientBuilder setNumTel(String numTel) {
            this.numTel = numTel;
            return this;
        }

        public Client build(){
            return new Client(this.nom, this.prenom, this.email, this.rue, this.ville, this.codePostal, this.numTel, this.id);
        }



    }

    @Override
    public String toString() {
        return  nom + ' ' + prenom + ' ' + id;
    }
}
