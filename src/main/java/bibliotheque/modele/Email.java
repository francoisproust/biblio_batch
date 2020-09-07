package bibliotheque.modele;


import java.io.Serializable;

public class Email implements Serializable {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
