public class Admin extends User {
    private String accesskey;

    public Admin(String fname, String lname, String id, String email, String password, String accesskey) {
        super(fname, lname, id, email, password);
        this.accesskey = accesskey;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }
}
