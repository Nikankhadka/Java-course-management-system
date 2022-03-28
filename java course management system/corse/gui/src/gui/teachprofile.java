package gui;

import com.mysql.jdbc.PreparedStatement;
import java.awt.Color;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

public class teachprofile extends mysqlfunctions{

    JFrame tprofile = new JFrame("teacher profile");
    JPanel module = new JPanel();
   
    JButton back=new JButton("BACK");
    
   //to show teacher list
  
ArrayList<String> teacherlist = new ArrayList<String>();

ArrayList<String> modulelist = new ArrayList<String>();
    JComboBox teacherbox=new JComboBox();
      JComboBox coursebox=new JComboBox();
    JComboBox mcode1=new JComboBox();
    JButton code=new JButton(" E+modules");
     JButton teacherm=new JButton(" T:modules");
     JButton enroll=new JButton("Enroll");
     JButton remove=new JButton("Remove");
  
    
    
    
    void tprofile() {
        this.tprofile.setSize(650, 500);
        this.tprofile.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.tprofile.getContentPane().setBackground(new Color(108, 223, 158));
        this.tprofile.setResizable(false);
        this.tprofile.setVisible(true);
        this.tprofile.setLayout(null);

        tprofile.add(module);
        module();
       
        
        
        tprofile.add(back);
        back();
       
       
    }
    
    void back(){
        back.setBounds(10,5,70,35);
        back.setFocusable(false);
        back.addActionListener(e->{
            adminpage a=new adminpage("admin");
            a.adminframe();
            tprofile.dispose();
            
            
        });
    }
    
    
    
    

    void module() {
        getteacherlist("courseteacherlist");
        
        module.setBounds(0, 60, 650, 400);
        module.setBackground(new Color(110, 228, 224));
        module.setLayout(null);
        // module.setVisible(false);
        
        JLabel teacher= new JLabel("Select Teacher:");
        module.add(teacher);
        teacher.setBounds(150,60,150,35);
        teacher.setFont(new Font("serif", Font.PLAIN, 17));
        
        
        module.add(teacherbox);
        teacherbox.setBounds(260,62,160,35);
        
        JLabel mcode= new JLabel("Module name:");
        module.add(mcode);
        mcode.setBounds(150,180,150,35);
        mcode.setFont(new Font("serif", Font.PLAIN, 17));
        
        JLabel course =new JLabel("Course:");
        module.add(course);
        course.setBounds(150,120,150,35);
        course.setFont(new Font("serif", Font.PLAIN, 17));
        
        
        module.add(mcode1);
        mcode1.setBounds(260,182,240,35);
        
        
      
        module.add(coursebox);
        coursebox.setBounds(260,122,150,35);
        
        
        
      teachermodules();
      moduletoenroll();    
        
    
        
        
        
      
       
        module.add(enroll);
        enroll.setBounds(200,290,110,35);
        
        
        
        module.add(remove);
        remove.setBounds(320,290,110,35);
        
        
        
       enrollbtn();
       removebtn();
        
        
 
 
        
    }
    
    
    void teachermodules(){
          //button to display module incourse  basis 
        
        module.add(code);
        code.setBounds(420,122,100,35);
        code.setFocusable(false);
        code.addActionListener(e->{
            super.modulelist.clear();
             mcode1.removeAllItems();
              String course11=String.valueOf(coursebox.getSelectedItem());
             String call1= super.getmodules("modules", course11);
                 for(String t:super.modulelist){
                    mcode1.addItem(t);
                }
            });
           
    }
    
    
    
    
    
    
    void moduletoenroll(){
             
        module.add(teacherm);
      teacherm.setBounds(420,62,100,35);
       teacherm.setFocusable(false);
       teacherm.addActionListener(e->{
              super.modulelist.clear();
             mcode1.removeAllItems();
                
                 String teachername=String.valueOf(teacherbox.getSelectedItem());
                String call=super.getmodules("tmodules",teachername);
                 
                 for(String t:super.modulelist){
                    mcode1.addItem(t);
                }
            });
           
           
    }
    
    
    void enrollbtn(){
         //enroll  button action now 
        enroll.addActionListener(e->{
            String teacher1=String.valueOf(teacherbox.getSelectedItem());
            String modulecode=String.valueOf(mcode1.getSelectedItem());
            String course1=String.valueOf(coursebox.getSelectedItem());
        
            
            if(teacher1.equals("")||modulecode.equals("")||course1.equals("")){
                 JOptionPane.showMessageDialog(null,"feild is empty","enroll failed",JOptionPane.ERROR_MESSAGE);
            }
            else{
                enrollteach_modules(teacher1,modulecode,course1);
            }
        });
        
    }
    
    
    void removebtn(){
         //remove
          remove.addActionListener(e->{
            String teacher1=String.valueOf(teacherbox.getSelectedItem());
            String modulecode=String.valueOf(mcode1.getSelectedItem());
           
            
            if(teacher1.equals("")||modulecode.equals("")){
                 JOptionPane.showMessageDialog(null,"feild is empty","enroll failed",JOptionPane.ERROR_MESSAGE);
            }
            else{
                removeteachmodules(teacher1,modulecode);
            }
        });
    }
    
    
  
    
    
  
    
    
    void getteacherlist(String action){
        try{
            
        mysqlfunctions db =new mysqlfunctions();
        if(action.equals("courseteacherlist")){
            
            

                ResultSet rs=db.stmt.executeQuery("select * from teacher");  
           while(rs.next()){
               String teacher=rs.getString("teacher_id");
               teacherlist.add(teacher);
           }
           for(String t:teacherlist){
               teacherbox.addItem(t);
           }


           //for course list
          super.courselist();
           for(String t:super.courselist){
               
               coursebox.addItem(t);
           }
           for(String t:super.courselist1){
               coursebox.addItem(t);
           }
        
        }
        
       
       


        }
        catch(Exception e){
            System.out.println("erororo"+e);
        }
    }
    
    
    
    
   
 
    
    
  
}
