
package gui;
import java.sql.PreparedStatement;
import java.awt.Color;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



public class account extends mysqlfunctions   {
   
      JFrame account1=new JFrame("Create account/Delete account");
      JLabel name=new JLabel("Name:");
      JTextField name1=new JTextField();
             
      JLabel username=new JLabel("User ID:");
      JLabel password=new JLabel("Password:");
      JLabel type=new JLabel("Account Type:");
       JTextField user=new JTextField();
       JPasswordField pass=new JPasswordField(); 
      JTextField typeac =new JTextField();
       JButton click=new JButton("Create Account");
      JCheckBox spass=new JCheckBox("show password");
      JRadioButton student=new JRadioButton ("student");
       JRadioButton teacher=new JRadioButton ("teacher");
        
      
        ButtonGroup group=new ButtonGroup();
        
        JPanel corseop=new JPanel();
        JButton back=new JButton("back");
      
        
        //course options made here 
        
      
       
       JComboBox coursebox =new JComboBox();
       JButton delete=new JButton("Delete Account");
      
        
       public account(){ //conm
      
        account1.setSize(400,500);
        account1.setVisible(true);
        account1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        account1.getContentPane().setBackground(new Color(108, 223, 158));
        account1.setLayout(null);
        account1.setResizable(false);
        //simply add user textfield passowrd and type
        
        account1.add(name);
        name.setBounds(50,0,100,50);
        
        account1.add(name1);
        name1.setBounds(120,10,150,30);
        
        account1.add(username);
        this.username.setBounds(50,50,100,50);
        
        //check box to make password visisble 
        account1.add(spass);
        seepass();
        
        account1.add(user);
        this.user.setBounds(120,55,150,30);


        account1.add(password);
        this.password.setBounds(50,95,100,50);


        account1.add(pass);
        this.pass.setBounds(120,100,150,30);
 
       account1.add(type);
       this.type.setBounds(30,180,100,40);


//       account1.add(typeac);
//       this.typeac.setBounds(120,185,150,30);
    
       account1.add(click);
       this.click.setBounds(50,370,130,30);
       this.click.setFocusable(false);
      createaccount();
       
     
       deleteaccount();
       
       
       //check box for account 
       group.add(student);
        group.add(teacher);
         
          account1.add(student);
         account1.add(teacher);
         //combo box for available courses
           super.courselist();
            for(String course:super.courselist){
               coursebox.addItem(course);
           }
         account1.add(coursebox);
         acounttype();
         
        //back button
        account1.add(back);
        back();
         
         
        //courseoption
      
    }
       
       
       
       
      
        
       
       //method which will make password visible
    private  void seepass(){
            this.spass.setBounds(120,140,120,20);
        this.spass.setBackground(new Color(108, 223, 158));
        this.spass.setFocusable(false);
        spass.addActionListener(e->{

               if(spass.isSelected()){
                   pass.setEchoChar((char)0);
                   
               }
               else{
                   pass.setEchoChar('*');
               }
                 
           
     } );
  }
   private  void acounttype(){
           this.student.setBounds(120,190,120,20);
        this.student.setBackground(new Color(108, 223, 158));
        this.student.setFocusable(false);
        
      this.teacher.setBounds(120,205,120,20);
        this.teacher.setBackground(new Color(108, 223, 158));
        this.teacher.setFocusable(false);
     
           
        //combobox 
        coursebox.setBounds(120,240,130,35);
       }

       
       
  
    
  void createaccount(){
          this.click.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
       
               String name=name1.getText();
          
           String password=String.valueOf(pass.getPassword());
           String course=String.valueOf(coursebox.getSelectedItem());
            String usname=user.getText();
           String type="";
            //since we used check box need to take input according to check box
           if(student.isSelected()){
               type="student";
           }
           else if(teacher.isSelected()){
               type="teacher";
           }
      
            if(usname.equals("")||password.equals("") ||type.equals("")||name.equals("")){
               JOptionPane.showMessageDialog(null,"username/password/type is empty","Account creation failed",JOptionPane.ERROR_MESSAGE);
          }
           
           //check if the inputs are empty or not
            else {
               
                    if ((type.equals("student") ||type.equals("teacher")) || type.equals("admin")){
                    //while creating acount also check if the acount already exist or not
               createacount(name,usname,password,type,course);
               
           }
            }
           
           }
        });
  }
  
 
 
 
 void deleteaccount(){
       account1.add(delete);
       delete.setBounds(185,370,130,30);
       delete.setFocusable(false);
       
       delete.addActionListener(e->{
           
    
        
         int confirm=JOptionPane.showConfirmDialog(account1,"Are you sure? ");  
         if(confirm==JOptionPane.YES_OPTION){  
             
           
            String usname=user.getText();
          
           
          if (usname.equals("")){
              JOptionPane.showMessageDialog(null,"enter user id to delete acount  ","Account delete failed",JOptionPane.ERROR_MESSAGE);
          }
          else{
              super.deleteacount(usname);
          }}
           
           
       });
       
 }
 
 
 
 
 
 
 
 
 
 
 
    
     void back(){
           back.setBounds(20,420,80,30);
           back.setFocusable(false);
           back.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               
               account1.dispose();
              adminpage a1=new adminpage("admin");
              a1.adminframe();
              }
        });
       }
    
      
  

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    

}
