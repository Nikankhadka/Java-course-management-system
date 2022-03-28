package gui;
import java.awt.Color;
import javax.swing.*;
//import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;





public class Gui implements loginpage {
    //important notes u can create object inside class but cant chnage its properties inside class 
    //props 
     JFrame lframe=new JFrame("Login page"); 
     ImageIcon image=new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\ficon.png");
     
//simple container like div 
     JPanel panel1=new JPanel();
     JPanel panel2=new JPanel();
     
//bgimg
    ImageIcon bgimage=new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\bgimg.png");
    JLabel bglabel=new JLabel();
     
     //user
     JLabel label1=new JLabel();
     JTextField user=new JTextField();
     //password
     JLabel label2=new JLabel();
     JPasswordField pass=new JPasswordField();
     
     
    //loginbutton
     JButton log=new JButton();
    
     //login page frame 
    
     
     JCheckBox spass=new JCheckBox("show password");
     JButton guestlogin=new JButton("Guest login");
    
    
    
     @Override
     public void loginframe(){
        
       
         
         //u can crreate obj inside the method and use it
       this.lframe.setSize(500,400); //gives size of the frame 
        this.lframe.setVisible(true);//setframe visible 
       this.lframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //exits out of apllication 
        this.lframe.setResizable(false); //frame can be resized
        this.lframe.getContentPane().setBackground(new Color(191, 249, 228)); //use new for rgb color 
        this.lframe.setIconImage(image.getImage());
        this.lframe.setLayout(null);
        
        
        
        //adding panels to frame
        this.lframe.add(panel1);
        panel1();
        
        
        this.lframe.add(panel2);
        panel12();
        
       
      }
     
     
     
     void panel1(){
         this.panel1.setBackground(new Color(191, 249, 228));
         this.panel1.setBounds(0,0,500,160);
        
         
        //bgimg with panel
         this.panel1.add(bglabel);
         this.bglabel.setIcon(bgimage);
         this.bglabel.setLayout(null);
         this.bglabel.setVisible(true);
         
         
         
     }
     void panel12 (){
          //panel1 simply gives properties to object
       this.panel2.setBackground(new Color(191, 249, 228));
       this.panel2.setBounds(40,175,400,190);
       this.panel2.setLayout(null);
        //here x=flows from lowest to highest in right
        //y==flows from top to bottom
        //1st gives width then 2nd gives height 
        
        
        //image
        //this.label1.setIcon(logmage.getImage());
        
        
        //code for labels inside the panel 
        this.panel2.add(label1);
        this.label1.setBounds(75,30,75,25);
       // this.label1.setVisible(true);
        this.label1.setText("Username:");
        
        //text feild for user 
        this.panel2.add(user);
        this.user.setBounds(145,30,140,30);
        this.user.setBackground(Color.LIGHT_GRAY);
        
        
        //password
        this.panel2.add(label2);
        
        this.label2.setBounds(75,75,75,25);
       // this.label2.setVisible(true);
        this.label2.setText("Password:");
        
        //pass
        this.panel2.add(pass);
        this.pass.setBounds(145,75,140,30);
        this.pass.setBackground(Color.LIGHT_GRAY);
        
        this.panel2.add(spass);
        seepass();
        
        //action and props of login button 
          //button
        this.panel2.add(log);
        this.log.setBounds(100,140,100,35);
        this.log.setText("Login");
         
        logbutton();
        
     }
          void seepass(){
            this.spass.setBounds(145,110,120,20);
        this.spass.setBackground(new Color(191, 249, 228));
        this.spass.setFocusable(false);
        spass.addActionListener(e->{
           
               if(spass.isSelected()){
                   pass.setEchoChar((char)0);
                   
               }
               else{
                   pass.setEchoChar('*');}    
           
       });
        
        
        
        panel2.add(guestlogin);
        guestlogin.setBounds(200,140,100,35);
        guestlogin();
        
  }
          
     void guestlogin(){
         guestlogin.addActionListener(e->{
              String usname=user.getText();
           if(usname.equals("admin")){
               adminpage adpage=new adminpage("admin");
               adpage.adminframe();
               //once admin frame opens the login frame is invisible
               lframe.dispose();
               
           }
         else if (usname.equals("teacher")){
              teacher t=new teacher("teacher");
             
              t.adminframe();
              
              //dispose this frame
              lframe.dispose();
              
          }
         else if (usname.equals("student")){
              student a=new student("student");
              a.adminframe();
              
              lframe.dispose();
              
          }
         else{
             JOptionPane.showMessageDialog(null,"invalid guest id","guest login failed",JOptionPane.ERROR_MESSAGE);
         }
         });
     }
          
          
          
          
          
          
       
     //button action
     void logbutton(){
        this.log.addActionListener(e->{
               //when button pressed get usernnaem and passowrd 
           String usname=user.getText();
           String password=String.valueOf(pass.getPassword());
           
           mysqlfunctions a=new mysqlfunctions();
           //call function to pass usernmae pass and get retrun login type
           String[] idframe=a.checklogin(usname, password);
           
          //check and open frame
          afterlogin(idframe[0],idframe[1]);
           
           
           
        });
         
     }
    
  //method to open frame after login 
     void afterlogin(String frtype ,String id){
          if(frtype.equals("admin")){
               adminpage adpage=new adminpage(id);
               adpage.adminframe();
               //once admin frame opens the login frame is invisible
               lframe.dispose();
               
           }
          else if (frtype.equals("teacher")){
              teacher t=new teacher(id);
             
              t.adminframe();
              
              //dispose this frame
              lframe.dispose();
              
          }
          else  if (frtype.equals("student")){
              student a=new student(id);
              a.adminframe();
              
              lframe.dispose();
              
          }
         
         
     }
        
    
     

  
}
