

public class User {
	private String fname;
	private String lname;
	private String email;
	private String id;
	private String password;
	

	
	public User(String fname,String lname,String id,String email,String password) {
	        this.fname = fname;
	        this.email = email;
	        this.lname = lname;
	        this.id = id;
	        this.password = password;
	      
	}
		public String getfname() {
        return fname;
    }

    public void setfname(String fname) {
        this.fname = fname;
    }
    
    
    
    public String getlname() {
        return lname;
    }

    public void setlname(String lname) {
        this.lname = lname;
    }
    
    
    
    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }
    
    
    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }
    
    
    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }
    
    

 
 // Method to delete the user account
    public void cancelSubscription() {
        // Perform actions related to deleting the account
    	this.fname = null;
        this.email = null;
        this.lname = null;
        this.id = null;
        this.password = null;
        System.out.println("User account deleted successfully!");
        
}
    @Override
    public String toString() {
        return "User [fname=" + fname + ", lname=" + lname + ", email=" + email + ", id=" + id + ", password="
                + password + "]";
    }

}


