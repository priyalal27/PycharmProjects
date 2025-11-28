package EventManagement.com.emc.entities;

public class Participants extends EMBase{

    private String email;
    private boolean checkedin;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCheckedin() {
        return checkedin;
    }

    public void setCheckedin(boolean checkedin) {
        this.checkedin = checkedin;
    }
}
