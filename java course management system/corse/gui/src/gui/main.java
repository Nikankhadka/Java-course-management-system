
package gui;

public class main {
    
    
    //basic main method to executte gui program 
    //since without database the data is not accurate and some may not work
    //use guestlogin button to login as guest to check out UI  
    //(admin, student,teacher) user id to guest login no password required
    
    public void openloginframe(){
        Gui a =new Gui();
        a.loginframe();
    }
    
    
    
    
    
    
    public static void main(String[] args) {
      main b=new main();
      b.openloginframe();
    }
}

