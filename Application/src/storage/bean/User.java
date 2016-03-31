package storage.bean;

public class User {

    private long id;
    private String username;
    private String mail;
    private String password;
    private boolean account_activated;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAccount_activated() {
        return account_activated;
    }

    public void setAccount_activated(boolean account_activated) {
        this.account_activated = account_activated;
    }
}
