
package gui;
import java.awt.Color;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class adminpage {
    JFrame adpage= new JFrame("Admin page");
    ImageIcon adimage=new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\adminicon.png");
    
    
    JPanel header=new JPanel();
    JLabel title=new JLabel();
    
    
    JPanel body=new JPanel();
    //options for admins 
    JButton courses=  new JButton(new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\courses.png"));
   
 
    JButton cacount=new JButton(new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\caccount.png"));
    JButton sprofile=new JButton(new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\profile.png"));
   JButton tprofile=new JButton(new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\profile.png"));  



    //ceate object of otheclases to acess method
    courseinfo cour=new courseinfo();
   // Gui login=new Gui();
    
  
    public adminpage(String id){
        System.out.println("id print hunxa hai"+id);
    }
    
    
   void   adminframe(){
        this.adpage.setSize(650,500);
        this.adpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       this.adpage.getContentPane().setBackground(new Color(108, 223, 158));
         this.adpage.setResizable(false); 
        this.adpage.setIconImage(adimage.getImage());
        this.adpage.setVisible(true);
        this.adpage.setLayout(null);
        
        
        //headerpanel
        this.adpage.add(header);
        this.headerpanel();
        
        //body panel
        this.adpage.add(body);
        bodypanel();
      
    }
   
   
   void headerpanel(){
       this.header.setBounds(0,0,650,50);
       this.header.setBackground(new Color(108, 223, 158));
      
      //adding titiel 
       
        this.header.add(title);
        
        this.title.setFont(new Font("serif",Font.PLAIN,20));
       
        this.title.setText("Herald college Kathmandu");
   }
    
  void bodypanel(){
      this.body.setBounds(0,50,700,520);
      this.body.setBackground(new Color(108, 223, 158));
      this.body.setLayout(null);
      
      
      
       //adding button to frame 
        this.body.add(courses);
        bcourses();
        this.body.add(tprofile);
        bprofile();
      //  this.body.add(seting);
     //   bseting();
        this.body.add(cacount);
        cracount();
        this.body.add(sprofile);
        delacount();
     //   this.body.add(lout);
      //  logout();
        
  }
    
    
    void bcourses(){
        this.courses.setBounds(100,60,180,100);
        this.courses.setFocusable(false); //removes annoying box around text
        this.courses.setText("course Option>>");
         this.courses.setFont(new Font("serif",Font.PLAIN,18));
        this.courses.setBackground(new Color(75, 179, 137));
        
        //text align of button
        this.courses.setHorizontalTextPosition(SwingConstants.CENTER);
        this.courses.setVerticalTextPosition(SwingConstants.BOTTOM);
       //open new frame 
         this.courses.addActionListener(e->{
            //close frame of adminpage
               adpage.dispose();
               //open frame of course info
               cour.courseinfoframe();
               
             
        });
       
        
    }
    
    void bprofile(){
        this.tprofile.setBounds(330,60,180,100);
        this.tprofile.setFocusable(false); //removes annoying box around text
        this.tprofile.setText(" Teacher Profile >>");
         this.tprofile.setFont(new Font("serif",Font.PLAIN,18));
        this.tprofile.setBackground(new Color(75, 179, 137));
       
        //text align of button
        this.tprofile.setHorizontalTextPosition(SwingConstants.CENTER);
        this.tprofile.setVerticalTextPosition(SwingConstants.BOTTOM);
        tprofile.addActionListener(e->{
            teachprofile a =new teachprofile();
            a.tprofile();
            adpage.dispose();
            
            
            
        });
      
    }
 
    
    void cracount(){
         this.cacount.setBounds(100,200,180,100);
        this.cacount.setFocusable(false); //removes annoying box around text
        this.cacount.setText("New/Delete Account");
         this.cacount.setFont(new Font("serif",Font.PLAIN,16));
        this.cacount.setBackground(new Color(75, 179, 137));
        
        //text align of button
        this.cacount.setHorizontalTextPosition(SwingConstants.CENTER);
        this.cacount.setVerticalTextPosition(SwingConstants.BOTTOM);
       
        //ask user input fro new account in a frame
         this.cacount.addActionListener(e->{
           {
               //since i used constructor for the create acount page just insantitating is enough
               new account();
               
              adpage.dispose();
     
           }
        });

    }
   
        
    
    
    void delacount(){
          this.sprofile.setBounds(330,200,180,100);
        this.sprofile.setFocusable(false); //removes annoying box around text
        this.sprofile.setText("Student profile >>");
         this.sprofile.setFont(new Font("serif",Font.PLAIN,18));
        this.sprofile.setBackground(new Color(75, 179, 137));
        
        //text align of button
        this.sprofile.setHorizontalTextPosition(SwingConstants.CENTER);
        this.sprofile.setVerticalTextPosition(SwingConstants.BOTTOM);
    sprofile.addActionListener(e->{
        stuprofile a=new stuprofile();
        adpage.dispose();
        a.frame();
    });
        
      
    }
    

    
}
