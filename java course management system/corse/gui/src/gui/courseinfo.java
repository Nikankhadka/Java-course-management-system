
package gui;


import java.awt.Color;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



public class courseinfo extends mysqlfunctions{
    JFrame cinfo=new JFrame("course info:");
    ImageIcon adimage=new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\courses.png");
    JLabel title=new JLabel("<html>course <br>options:</html>");
  
  
     
    
     int cour=0;
     int ncour=0;
     
     
     
    JPanel totalbody=new JPanel();//whole frame
     JPanel body=new JPanel();//frame which will contain displaye panels 
     
  
    //option button 
    JButton info=new JButton(new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\courses.png"));
    JButton addcourse=new JButton(new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\addcourse.png"));
    JButton delcourse=new JButton(new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\delcourse.png"));
    JButton editcourse=new JButton(new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\removecourse.png"));
    JButton back=new JButton("Back");
   
    //panels for each specfic button
    
    
    JPanel coursedisplay=new JPanel();
    JPanel cdheaderpanel=new JPanel();
    JButton forward=new JButton();
    JButton backward=new JButton();
    
    
    
    
    //panels for display of each button and also include new panels 
    JPanel butdisplay=new JPanel(); //panel which displays the button 
    
    /////// forcourse addd
    JPanel addcorpanel=new JPanel();
        JTextField name=new JTextField();
          JTextField ccredit=new JTextField();
          String ctime[]={"1","2","3","4","5","6","7","8"};
          JComboBox cduration=new JComboBox(ctime);
            String csem[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
           JComboBox seme=new JComboBox(csem);
           String op[]={"yes","no"};
           JComboBox available=new JComboBox(op);
     JButton adcourse=new JButton("Addcourse:");
     
     
     //for moduel of course adddd
     JTextField mmname=new JTextField();
      JTextField mmcode=new JTextField();
      String ctime1[]={"1","2","3","4","5","6","7","8"};
        JComboBox mmduration=new  JComboBox(ctime1);
        String csem1[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
         JComboBox mmseme=new  JComboBox(csem1);
          JComboBox course11=new JComboBox();
     JButton admodule=new JButton("Addmodule:");
     
     
     
     ////////foe edit corpanel
    JPanel delcorpanel=new JPanel();
    JComboBox cname2=new JComboBox();

    /////
    JPanel editcorpanel=new JPanel();
    JButton edit=new JButton("EDIT COURSE");
    JComboBox old=new JComboBox();
    JTextField newcourse=new JTextField();
    
    //for module
    JTextField oldmodule1=new JTextField();
    JTextField newmodule1=new JTextField();
    JTextField modulecode1=new JTextField();
    JButton edmodule=new JButton("EDIT MODULE");
    JComboBox coursebox=new JComboBox();
    
    ////delte removce restore
     JButton delete=new JButton("DELETE");
     JButton remove=new JButton("REMOVE");
 
    
    void courseinfoframe(){
        this.cinfo.setSize(620,700);
       
        this.cinfo.setResizable(false);
        this.cinfo.setVisible(true);
        this.cinfo.setLayout( null);
        this.cinfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.cinfo.setIconImage(adimage.getImage());
        
        //totoal body is the panel which covers entire frame
        this.cinfo.add(totalbody);
        totalbody.setSize(620,700);
        totalbody.setBackground (new Color(108, 223, 158));
        totalbody.setLayout(null);
        
        this.totalbody.add(title);
        title.setBounds(0,0,120,50);
        this.title.setFont(new Font("serif",Font.PLAIN,18));
        
        
        //coursebutton
         super.courselist();
        this.totalbody.add(info);
        course();
        this.totalbody.add(addcourse);
        addcourse();
        this.totalbody.add(delcourse);
        deletecourse();
        this.totalbody.add(editcourse);
        editcourse();
        this.totalbody.add(back);
        back();
        
        this.totalbody.add(body);
        body();
        
    }
    void body(){
        body.setBounds(105,0,515,700);
        this.body.setBackground(new Color(75, 179, 137));
        this.body.setLayout(null);
      // coursedata();
       
       //panels for diff button
       
       
       body.add(addcorpanel);
       addcorpanel();
       
       body.add(delcorpanel);
       delcorpanel();
      body.add(editcorpanel);
       editcorpanel();
       
       body.add(coursedisplay);
       displaypanel();
       
       
                                                                                                     
    }
    
    
    void displaypanel(){ //panel for module information
       coursedisplay.setBounds(10,55,485,600);
       coursedisplay.setBackground(new Color(75, 179, 137));
       
      coursedisplay.setLayout(new GridLayout(1,1));
      coursedisplay.setVisible(false);
      
      body.add(cdheaderpanel);  //addingg header panel here because both are connected 
      
       coursedata();
     
    }
    
    

       
   void coursedata() {
      
       
           //for header panel hai contIns course details
           cdheaderpanel.setBounds(10,3,480,45);
           cdheaderpanel.setBackground(new Color(75, 179, 137));
           cdheaderpanel.setLayout(null);
           cdheaderpanel.setVisible(false);
           
           //add buttons inside header panel 
           cdheaderpanel.add(forward);
           forward.setBounds(395,5,60,35);
           forward.setText(">>>");
           
           forward.setFocusable(false);
           cdheaderpanel.add(backward);
           backward.setBounds(10,5,60,35);
           backward.setText("<<<");
          backward.setFocusable(false);
        
           JLabel cname=new JLabel();
           cdheaderpanel.add(cname);
           
           cname.setBounds(170,0,200,45);
           cname.setText("<html>"+super.courselist.get(cour)+"<br>"+"course credit:"+super.coursecredit.get(cour)+"</html>");
           cname.setFont(new Font("calibri",Font.PLAIN,15));
          //module info
           coursemodules(super.courselist.get(cour));
           
           //when forward and backward button are pressed 
           forward.addActionListener(e->{
              if(cour>=super.courselist.size()&&ncour<super.courselist1.size()){
                  
                  cname.setText("<html>"+super.courselist1.get(ncour)+"(Not Available)"+"<br>"+"course credit:"+super.coursecredit1.get(ncour)+"</html>");
                    coursedisplay.removeAll(); //cleans module panel 
                   coursemodules(super.courselist1.get(ncour));
                  ncour=ncour+1;
                  
              }
              else if (cour<super.courselist.size()){
             
                cour=cour+1;
                if (cour<super.courselist.size()){
                        cname.setText("<html>"+super.courselist.get(cour)+"<br>"+"course credit:"+super.coursecredit.get(cour)+"</html>");
               coursedisplay.removeAll();
               coursemodules(super.courselist.get(cour));
                }
                else{
                   
                    System.out.println("out of index");
                }
           }
           });
           
            backward.addActionListener(e->{
            if(cour>=super.courselist.size() &&ncour>0){
                
                    ncour=ncour-1;
                     cname.setText("<html>"+super.courselist1.get(ncour)+"(Not Available)"+"<br>"+"course credit:"+super.coursecredit1.get(ncour)+"</html>");
                    coursedisplay.removeAll();
                   coursemodules(super.courselist1.get(ncour));
                    
                
            }
            else{
                
                if(cour>0){
                
                 cour=cour-1;
              
               cname.setText("<html>"+super.courselist.get(cour)+"<br>"+"course credit:"+super.coursecredit.get(cour)+"</html>");
               coursedisplay.removeAll();
               coursemodules(super.courselist.get(cour));
           }}
            });
                    }
        

   
   

 void coursemodules(String coursename){
       JLabel modules=new JLabel();
       System.out.println("coursemodules functrion chai call bhayo hai");
           try{
            mysqlfunctions db=new mysqlfunctions();
        //when executing below query only username is matched if available in the table or not 
       
     
        ResultSet rs=db.stmt.executeQuery("select * from "+coursename+"");  
        String module="";
        // if the passed corsename matches it checks for next row thatis type if avalbel true 
        int year1=0;
        while(rs.next()){
            
            if(rs.getInt("year")==year1){
                 module+=rs.getString("module_code")+":  "+rs.getString("module_name")+"<br>";
            }
            else{
                String year="year:"+rs.getString("year");
                module+="<h2>"+year+"</h2>"+rs.getString("module_code")+":  "+rs.getString("module_name")+"<br>";
            }

            year1=rs.getInt("year");
        }
      //  System.out.println(module);
         courseinfo a=new courseinfo();
         modules.setText("<html>"+module+"</html>");
        modules.setFont(new Font("serif",Font.PLAIN,14));
        // modules.setVisible(true);
         coursedisplay.add(modules);
        }
       catch(Exception e){
            System.out.print("erororo"+e);
           
        }
         
   }
   
        
    
    void course(){
         this.info.setBounds(0,60,100,85);
        this.info.setFocusable(false); //removes annoying box around text
        this.info.setText("course");
         this.info.setFont(new Font("serif",Font.PLAIN,14));
        this.info.setBackground(new Color(128, 243, 193));
       
        //text align of button
        this.info.setHorizontalTextPosition(SwingConstants.CENTER);
        this.info.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        //actioon 
           this.info.addActionListener(e-> {
           editcorpanel.setVisible(false);
           addcorpanel.setVisible(false);
           delcorpanel.setVisible(false);
           coursedisplay.setVisible(true); //module display
           cdheaderpanel.setVisible(true);  //course heading display

           
           });
        }
    
    
    void addcourse(){
         this.addcourse.setBounds(0,148,100,70);
        this.addcourse.setFocusable(false); //removes annoying box around text
        this.addcourse.setText("addcourse");
         this.addcourse.setFont(new Font("serif",Font.PLAIN,14));
        this.addcourse.setBackground(new Color(128, 243, 193));
        //text align of button
        this.addcourse.setHorizontalTextPosition(SwingConstants.CENTER);
        this.addcourse.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        //action
         
           this.addcourse.addActionListener(e->{
             cdheaderpanel.setVisible(false);
             coursedisplay.setVisible(false);  
             delcorpanel.setVisible(false);
             editcorpanel.setVisible(false);
            addcorpanel.setVisible(true);
           });   
    }
    
   void addcorpanel(){
       addcorpanel.setBounds(10,30,480,600);
       addcorpanel.setBackground(new Color(75, 179, 137));
       addcorpanel.setLayout(null);
       addcorpanel.setVisible(false);
       JLabel head11=new JLabel("ADD COURSE/MODULE:");
       addcorpanel.add(head11);
       head11.setBounds(120,20,200,30);
       head11.setFont(new Font("serif",Font.BOLD,17));
       //cretae new panels inside respectively
       JPanel course=new JPanel();
       addcorpanel.add(course);
       course.setBounds(10,100,225,400);
       course.setBackground(new Color(108, 223, 158));
       course.setLayout(null);
       
       //name
      JLabel corname=new JLabel("Coursename:");
      course.add(corname);
      corname.setBounds(5,25,80,20);
      
  
      course.add(name);
      name.setBounds(88,25,115,30);
       
      //credit
        JLabel credit=new JLabel("Coursecredit:");
      course.add(credit);
      credit.setBounds(5,85,80,20);
      
    
            course.add(ccredit);
      ccredit.setBounds(88,85,115,30);
       
      //year or duration
       JLabel duration=new JLabel("Duration:");
      course.add(duration);
      duration.setBounds(5,140,80,20);
         course.add(cduration);
      cduration.setBounds(88,140,115,30);
       
       
      //semester
       JLabel sem=new JLabel("Semester:");
      course.add(sem);
     sem.setBounds(5,190,80,20);
       course.add(seme);
      seme.setBounds(88,190,115,30);
       
      //avavav
      JLabel avvv=new JLabel("Available:");
      course.add(avvv);
      avvv.setBounds(5,240,80,20);
      course.add(available);
      available.setBounds(88,240,115,30);
     
      //confirm button
     
      course.add(adcourse);
      adcourse.setBounds(60,300,100,30);
      
     //button action to add course
      addcoursebtn();
      
      
      //module panel inside add course 
       JPanel module=new JPanel();
       addcorpanel.add(module);
       module.setBounds(245,100,225,400);
       module.setBackground(new Color(108, 223, 158));
       module.setLayout(null);
       
       //////
         //name
      JLabel mname=new JLabel("Modulename:");
      module.add(mname);
      mname.setBounds(5,25,80,20);
      
      module.add(mmname);
      mmname.setBounds(88,25,115,30);
       
      //credit
        JLabel mcode=new JLabel("ModuleCode:");
      module.add(mcode);
      mcode.setBounds(5,85,80,20);
      
     
            module.add(mmcode);
      mmcode.setBounds(88,85,115,30);
       
       
      //year or duration
       
       JLabel mduration=new JLabel("Year:");
      module.add(mduration);
      mduration.setBounds(5,140,80,20);
      
    
            module.add(mmduration);
      mmduration.setBounds(88,140,115,30);
       
       
      //semester
       JLabel msem=new JLabel("Semester:");
      module.add(msem);
     msem.setBounds(5,190,80,20);
      
     
            module.add(mmseme);
      mmseme.setBounds(88,190,115,30);
       
      JLabel course1=new JLabel("course:");
      module.add(course1);
      course1.setBounds(5,240,80,20);
      
      
      
   ///////////////////////   same thing combo box displys courtse for both available and unvaialabel
      module.add(course11);
      course11.setBounds(88,240,115,30);
    
      for(String cl:courselist){
          course11.addItem(cl);
      }
      for(String cl:courselist1){
          course11.addItem(cl);
      }
      
      
      //confirm button
      
      module.add(admodule);
      admodule.setBounds(60,300,100,30);
      
      addmodulebtn();
       
       
   }
   // action of adcourse button inside the adcourse panel
   void addcoursebtn(){
          adcourse.addActionListener(e->{
          
               String cname=name.getText();
               String  credit=ccredit.getText();
               String duration=String.valueOf(cduration.getSelectedItem());
               String semester=String.valueOf(seme.getSelectedItem());
               String avai=String.valueOf(available.getSelectedItem());
               //if inputs are empty
               if(cname.equals("")||credit.equals("") ||duration.equals("")||semester.equals("")||avai.equals("")){
               JOptionPane.showMessageDialog(null,"given filed are  empty","course creation failed",JOptionPane.ERROR_MESSAGE);
          }
           
           //check if the inputs are empty or not
            else
               {
             
               super.insert(cname,credit,duration,semester,avai);
           }
            });
   }
   
   void addmodulebtn(){
       admodule.addActionListener(e->{
       String coursename=String.valueOf(course11.getSelectedItem());
       String module_name=mmname.getText();
       String module_code=mmcode.getText();
       String year=String.valueOf(mmduration.getSelectedItem());
       String semester=String.valueOf(mmseme.getSelectedItem());
       
       if(coursename.equals("")||module_name.equals("") ||module_code.equals("")||year.equals("")||semester.equals("")){
               JOptionPane.showMessageDialog(null,"given filed are  empty","moduleaddition failed",JOptionPane.ERROR_MESSAGE);
          }
           
           //check if the inputs are empty or not
            else
               {
          
            super.insertmodules(coursename,module_name,module_code,year,semester);
                   
           }
           
           
   });
   }
   
   
   
    void deletecourse(){
         this.delcourse.setBounds(0,222,100,70);
        this.delcourse.setFocusable(false); //removes annoying box around text
        this.delcourse.setText("del/remove");
         this.delcourse.setFont(new Font("serif",Font.PLAIN,14));
        this.delcourse.setBackground(new Color(128, 243, 193));
       //text align of button
        this.delcourse.setHorizontalTextPosition(SwingConstants.CENTER);
        this.delcourse.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        //action
        delcourse.addActionListener(e->{
            coursedisplay.setVisible(false);
            addcorpanel.setVisible(false);
            cdheaderpanel.setVisible(false);
            editcorpanel.setVisible(false);
            delcorpanel.setVisible(true);
        });
        
    }
    
    
    void delcorpanel(){
        delcorpanel.setBounds(50,100,400,450);
        delcorpanel.setBackground(new Color(108, 223, 158));
        delcorpanel.setLayout(null);
        delcorpanel.setVisible(false);
        
        //for contents 
         JLabel head=new JLabel("DELETE/REMOVE/RESTORE");
        delcorpanel.add(head);
        head.setBounds(80,0,400,40);
        head.setFont(new Font("serif",Font.BOLD,17));
       
       JLabel cname=new JLabel("course name:");
       delcorpanel.add(cname);
       cname.setBounds(60,60,100,40);
       cname.setFont(new Font("serif",Font.PLAIN,16));
       
       
       //give available course info
       
       delcorpanel.add(cname2);
       cname2.setBounds(160,63,140,35);
        for(String cr:courselist){
            cname2.addItem(cr);
        }
        for(String cr:courselist1){
            cname2.addItem(cr);
        }
       
       
       
       
       
       //buttoons
         
        deletecoursebtn();
        removecourse();
        restorecourse();
       
        
      
  
   
 
    }
    
    
    
    
    void deletecoursebtn(){
           delcorpanel.add(delete);
       delete.setBounds(80,200,110,35);
       
       delete.addActionListener(e->{
         int confirm=JOptionPane.showConfirmDialog(delcorpanel,"Are you sure? ");  
         if(confirm==JOptionPane.YES_OPTION){  
           String course=String.valueOf(cname2.getSelectedItem());
           String action="delete";
           
           if(course.equals("")){
                JOptionPane.showMessageDialog(null,"given filed are  empty","course delete failed",JOptionPane.ERROR_MESSAGE);
           }
           else{
              super.delcourse(course,action);
               System.out.println("course deleted hai");
           }
           
           
         } });
    }
    
   void removecourse(){
        delcorpanel.add(remove);
       remove.setBounds(200,200,110,35);
        remove.addActionListener(e->{
           String course=String.valueOf(cname2.getSelectedItem());
           String action="remove";
           
           if(course.equals("")){
                JOptionPane.showMessageDialog(null,"given filed are  empty","course delete failed",JOptionPane.ERROR_MESSAGE);
           }
           else{
               super.delcourse(course,action);
               System.out.println("course removed hai");
           }
           
           
       });
   }
  void restorecourse(){
      JButton restore=new JButton("RESTORE");
       delcorpanel.add(restore);
       restore.setBounds(145,245,110,35);
        
       restore.addActionListener(e->{
           String course=String.valueOf(cname2.getSelectedItem());
           String action="restore";
           
           if(course.equals("")){
                JOptionPane.showMessageDialog(null,"given filed are  empty","course delete failed",JOptionPane.ERROR_MESSAGE);
           }
           else{
               super.delcourse(course,action);
               System.out.println("course restored  hai");
           }
           
           
       });
  }
  
  
  
  
    void editcorpanel(){
        
        
        editcorpanel.setBounds(15,35,470,600);
        editcorpanel.setBackground(new Color(108, 223, 158));
        editcorpanel.setLayout(null);
        editcorpanel.setVisible(false);
        
        JLabel courname=new JLabel("EDIT COURSE NAME:");
        editcorpanel.add(courname);
        courname.setBounds(140,20,200,40);
        courname.setFont(new Font("serif",Font.BOLD,17));
        
        JLabel cname1=new JLabel("Old Course Name:");
        editcorpanel.add(cname1);
        cname1.setBounds(90,60,150,40);
        
        JLabel cname2=new JLabel("New Course Name:");
        editcorpanel.add(cname2);
        cname2.setBounds(90,110,150,40);
    
        ///////////////////////////
        editcorpanel.add(old);
        old.setBounds(210,63,140,35);
        for(String cl:courselist){
            old.addItem(cl);
        }
        for(String cl:courselist1){
            old.addItem(cl);
        }
        
        
        
        
        editcorpanel.add(newcourse);
        newcourse.setBounds(210,113,140,35);
        
        
        editcorpanel.add(edit);
        edit.setBounds(155,190,140,35);
        
        
       editcoursebtn();
        
        
         
        
        //now for edit module 
        JLabel editmodule=new JLabel("EDIT MODULE INFO:");
        editcorpanel.add(editmodule);
        editmodule.setBounds(140,260,200,40);
        editmodule.setFont(new Font("serif",Font.BOLD,17));
        
        
        JLabel oldmodule=new JLabel("Old Module Name:");
        editcorpanel.add(oldmodule);
        oldmodule.setBounds(90,310,200,40);
        
        JLabel newmodule=new JLabel("New Module Name:");
        editcorpanel.add(newmodule);
        newmodule.setBounds(90,360,200,40);
        
        JLabel modulecode=new JLabel("New Module Code:");
        editcorpanel.add(modulecode);
        modulecode.setBounds(90,410,200,40);
        
        
        JLabel courses=new JLabel("course:");
        editcorpanel.add(courses);
        courses.setBounds(90,460,140,35);
        
        
      editcorpanel.add(oldmodule1);
      oldmodule1.setBounds(220,313,140,35);
      
          editcorpanel.add(newmodule1);
      newmodule1.setBounds(220,363,140,35);
      
        editcorpanel.add(modulecode1);
      modulecode1.setBounds(220,413,140,35);
    
      
      //use combo box in add module to give option while adding module
     
      editcorpanel.add(coursebox);
      coursebox.setBounds(220,462,140,35);
      
      for(String cour:courselist){
          coursebox.addItem(cour);
      }
      for(String cour:courselist1){
        coursebox.addItem(cour);
    }
      
      
 ///////////////////////////// 
      
      editcorpanel.add(edmodule);
      edmodule.setBounds(155,550,140,35);
      
        
      editmodulebtn();
    
      
      
      
    }
    
    public void editcoursebtn(){
         //edit course button actions 
        edit.addActionListener(e->{
            String oldcourse=String.valueOf(old.getSelectedItem());
            String newc=newcourse.getText();
            
            if(oldcourse.equals("") || newc.equals("")){
                  JOptionPane.showMessageDialog(null,"given filed are  empty","course edit failed",JOptionPane.ERROR_MESSAGE);
            }
            else{
                super.edcoursename(oldcourse,newc);
            }
            
        });
    }
    
    
    
    
     public void editmodulebtn(){
         //  edit module name and code hai aba 
      edmodule.addActionListener(e->{
          String om=oldmodule1.getText();
          String nm=newmodule1.getText();
          String mcode=modulecode1.getText();
          String mcourse=String.valueOf(coursebox.getSelectedItem());
          
          
          if (om.equals("")||nm.equals("")||mcode.equals("")||mcourse.equals("")){
                JOptionPane.showMessageDialog(null,"given fields is empty","module edit failed",JOptionPane.ERROR_MESSAGE);
          }
          else{
              super.edmodulename(om,nm,mcode,mcourse);
          }
          
      });
      
     }
    
    
    
    
    
    void editcourse(){
          this.editcourse.setBounds(0,295,100,70);
        this.editcourse.setFocusable(false); //removes annoying box around text
        this.editcourse.setText("editcourse");
         this.editcourse.setFont(new Font("serif",Font.PLAIN,14));
        this.editcourse.setBackground(new Color(128, 243, 193));
        //text align of button
        this.editcourse.setHorizontalTextPosition(SwingConstants.CENTER);
        this.editcourse.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        editcourse.addActionListener(e->{
            coursedisplay.setVisible(false);
            addcorpanel.setVisible(false);
            cdheaderpanel.setVisible(false);
            delcorpanel.setVisible(false);
            editcorpanel.setVisible(true);
        });
    }
    


    
     void back(){
           back.setBounds(12,625,80,30);
           back.setBackground(new Color(128, 243, 193));
           back.setFocusable(false);
           back.addActionListener(e-> {
              adminpage a1=new adminpage("admin");
              a1.adminframe();
             
              cinfo.dispose();
              
        });
       }
        
    
    
    
    
    
    
    
    public static void main (String[]args){
        courseinfo cor=new courseinfo();
        cor.courseinfoframe();
        
        
    }
    
}
