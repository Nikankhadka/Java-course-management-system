

package gui;
import java.awt.Color;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;
import net.proteanit.sql.DbUtils;
import javax.swing.event.*; 
public class stuprofile extends mysqlfunctions{
    JFrame f1=new JFrame("Student profile");
    
    
  
    JComboBox course1= new JComboBox();
    String years[]={"1","2","3","4","5","6","7","8"};
    JComboBox yearbox= new JComboBox(years);
     ArrayList<String> modulelist=new ArrayList<String>();

      JTable jt=new JTable();
      
    JScrollPane scrollpane = new JScrollPane(jt);
    JPanel display=new JPanel();
    
    JButton pass=new JButton("Upgrade Student");
    JButton publish=new JButton("Publish Result");
    JButton update =new JButton("Update Result");
    String c1="";
    int y1;
    
    
    void frame(){
         this.f1.setSize(700, 600);
        this.f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.f1.getContentPane().setBackground(new Color(108, 223, 158));
        this.f1.setResizable(false);
        this.f1.setVisible(true);
        this.f1.setLayout(null);
        
        
        JLabel cr=new JLabel("course List:");
        f1.add(cr);
        cr.setFont(new Font("serif", Font.PLAIN, 17));
        cr.setBounds(160,5,140,35);
        
        
        
        JLabel cr1=new JLabel("Select year:");
        f1.add(cr1);
        cr1.setFont(new Font("serif", Font.PLAIN, 17));
        cr1.setBounds(160,45,140,35);
        
        
        
        //get course info using teacherpfoile methods
        f1.add(course1);
        course1.setBounds(270,5,200,35);
        super.courselist();
        for(String cr2:super.courselist){
           course1.addItem(cr2);
      }
       for(String cr2:super.courselist1){
           course1.addItem(cr2);
       }

         f1.add(yearbox);
        yearbox.setBounds(270,45,200,35);
      
       
       
           
        display();
         getinfobutton();
        updateyear();    
        updateresult();
        publishbutton();
        back();
       
    }
    
    
    
    
    
    
    
    
    
    
    
    
    private void getinfobutton(){
         //jbuttons for course and year info to get student and theri marks 
       JButton course2=new JButton("Get info:");
       f1.add(course2);
       course2.setBounds(280,90,140,35);
       course2.setFocusable(false);
        course2.addActionListener(e->{
            String course=String.valueOf(course1.getSelectedItem());
            String yr=String.valueOf(yearbox.getSelectedItem());
            int yr1=Integer.parseInt(yr);
           c1=course;
           y1=yr1;
             displaystudent(course,yr1);
             
        });
        
    }
    
    

    
    
    void updateyear(){
             
        //////pass button 
        f1.add(pass);
        pass.setBounds(130,480,140,35);
        pass.setFocusable(false);
        pass.addActionListener(e->{
             String[] idresult=checkpassfail();
           String id=idresult[0];
           String result=idresult[1]; 
           
       super.publishupdate(id,result,"pass",y1,c1);
        
        
  });
   
        
        
        
        
    }
    
    
    
    void publishbutton(){
           //publsish
             f1.add(publish);
        publish.setBounds(270,480,140,35);
        publish.setFocusable(false);
        publish.addActionListener(e->{
           String[] idresult=checkpassfail();
           String id=idresult[0];
           String result=idresult[1]; 
           if(result.equals("nomarks")){
               JOptionPane.showMessageDialog(null,"marks not inserted by tacher","result publish failed",JOptionPane.ERROR_MESSAGE);
           }else{
               super.publishupdate(id,result,"publish",y1,c1);
           }
           
  });
        
    }
    
    
    void updateresult(){
         
        f1.add(update);
        update.setBounds(410,480,140,35);
        update.setFocusable(false);
        update.addActionListener(e->{
              String[] idresult=checkpassfail();
           String id=idresult[0];
           String result=idresult[1]; 
            super.publishupdate(id,result,"update",y1,c1);
        });
    }
    
    
   
    
    
    
   
    
String[] checkpassfail(){
    
    String[] idresult=new String[2];
    String m2="";
     int row =jt.getSelectedRow();
        // String module=jt.getColumnName(1);
        int col=jt.getColumnCount()-1;
     
        int passrequirement=col/2;
         String id=(String)jt.getValueAt(row,0);
         int subpassed=0;
         
         
         for(int i=1;i<=col;i++){
         String m=String.valueOf(jt.getValueAt(row, i));
         int marks=Integer.parseInt(m);
         if(marks==1){
             m2="nomarks";
             break;
         }
         }
         
         
         if(m2.equals("")){
             
             for(int i=1;i<=col;i++){
                  String m=String.valueOf(jt.getValueAt(row, i));
                  int marks=Integer.parseInt(m);
                  
                  
                  
                  if(marks>1){
                 if(marks>=40){
             
                  subpassed++;
              }
              else{
                      System.out.println("subject failed count");
              }
              
              
         
                  }}
         if(subpassed>=passrequirement){
             idresult[0]=id;
               idresult[1]="pass";
               return idresult;
         }
         else{
             idresult[0]=id;
               idresult[1]="fail";
               return idresult;
         }
         
             
             
         }
             
             
              
         else{
               idresult[0]=id;
             idresult[1]="nomarks";
             return idresult;
         }   
         
    
}
    
    
    
    
    
    
  





    void display(){
        f1.add(display);
         display.setBounds(0, 130, 700, 320);
        display.setBackground(new Color(108, 223, 158));
        display.setLayout(null);

 
        scrollpane.setBounds(13,10,660,300);
        display.add(scrollpane);
        jt.setDefaultEditor(Object.class, null);   
    }
    
    
    
 
    
    void displaystudent(String course,int year){
        try{
            mysqlfunctions db=new mysqlfunctions();
           
            ResultSet rs=db.stmt.executeQuery("select * from "+course+"_year"+year+"");  
             jt.setModel(DbUtils.resultSetToTableModel(rs));
           
           
            
        }catch(Exception e){
             JOptionPane.showMessageDialog(null,"select valid course/year","no details to show",JOptionPane.ERROR_MESSAGE);
            System.out.println("eororo"+e);
          
        }
    }
    
    
    
    
    void back(){
        JButton back=new JButton("Back");
        f1.add(back);
        back.setBounds(10,520,80,35);
        back.addActionListener(e->{
            f1.dispose();
            adminpage a=new adminpage("admin");
            a.adminframe();
            
            
        });
    }
    

    
}
