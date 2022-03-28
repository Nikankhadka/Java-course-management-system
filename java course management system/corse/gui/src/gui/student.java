
package gui;
import java.awt.Color;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import net.proteanit.sql.DbUtils;



public class student extends mysqlfunctions {
    JFrame stpage= new JFrame("Student Dashboard");
    ImageIcon stimage=new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\adminicon.png");
    
    
    JPanel header=new JPanel();
    JPanel body=new JPanel();
    //options for admins 
    JButton courses=  new JButton(new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\course.png"));
    JButton mycourse=new JButton(new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\course.png"));
    JButton result=new JButton(new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\result.png"));


 
    
     
     int cour=0;
     int ncour=0;
     
     
     
       
    JPanel coursedisplay=new JPanel();
    JPanel cdheaderpanel=new JPanel();
    JButton forward=new JButton();
    JButton backward=new JButton();
    
     //for studetn to enroll into modules 
    JPanel modinfo=new JPanel();
     JTable jt=new JTable();
     JScrollPane scrollpane=new JScrollPane(jt);
     String studentcourse="";
    
      String id="";
    
     
   //result panel 
    JPanel resultpanel=new JPanel();
    JTable jt1=new JTable();
    JScrollPane scrollpane1=new JScrollPane(jt1);
    JButton confirm=new JButton("Confirm");  
    
    
     public student(String id){
    this.id=id;
    System.out.println("yo chai student id ho hai"+this.id);
} 
   void   adminframe(){
        this.stpage.setSize(620,700);
        this.stpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  this.adpage.getContentPane().setBackground(new Color(142, 249, 157));
         this.stpage.setResizable(false); 
        this.stpage.setIconImage(stimage.getImage());
        this.stpage.setVisible(true);
        this.stpage.setLayout(null);
        
        
        //headerpanel
        this.stpage.add(header);
        this.headerpanel();
       
        this.stpage.add(body);
        bodypanel();
        
       
        
    }
   
   
   void headerpanel(){
       this.header.setBounds(0,0,105,700);
       this.header.setBackground(new Color(108, 223, 158));
       this.header.setLayout(null);
       this.header.add(courses);
       bcourses();
     
       
        this.header.add(mycourse);
        mycourse();
     this.header.add(result);
     result();
     
     
       
   }
    
  void bodypanel(){
      this.body.setBounds(106,0,600,700);
      this.body.setBackground(new Color(75, 179, 137));
      this.body.setLayout(null);
      
       body.add(coursedisplay);
       displaypanel();
        body.add(modinfo);
         modinfo();
         
       body.add(resultpanel);  
       resultpanel();
         
     
  }
  
  
  
  
  
    
    void displaypanel(){ //panel for module information
       coursedisplay.setBounds(10,55,485,600);
       coursedisplay.setBackground(new Color(75, 179, 137));
       
      coursedisplay.setLayout(new GridLayout(1,1));
      coursedisplay.setVisible(false);
      
      body.add(cdheaderpanel);  //addingg header panel here because both are connected 
      
       coursedata();
     
    }
    
    
   
    
    
    
       //get course iinformation from the course table and store it in array
   void coursedata() {
        
         
         super.courselist();
        
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
           cname.setText("<html>"+courselist.get(cour)+"<br>"+"course credit:"+coursecredit.get(cour)+"</html>");
           cname.setFont(new Font("calibri",Font.PLAIN,15));
          //module info
           coursemodules(courselist.get(cour));
           
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
        
       
     
        ResultSet rs=db.stmt.executeQuery("select * from "+coursename+"");  
        String module="";
        
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
     
         modules.setText("<html>"+module+"</html>");
        modules.setFont(new Font("serif",Font.PLAIN,14));
       
         coursedisplay.add(modules);
        }
       catch(Exception e){
            System.out.print("erororo"+e);
           
        }
    }

 
    void bcourses(){
        this.courses.setBounds(0,30,105,65);
        this.courses.setFocusable(false); //removes annoying box around text
        this.courses.setText("courses..");
         this.courses.setFont(new Font("serif",Font.PLAIN,14));
        this.courses.setBackground(new Color(128, 243, 193));
        
        //text align of button
        this.courses.setHorizontalTextPosition(SwingConstants.CENTER);
        this.courses.setVerticalTextPosition(SwingConstants.BOTTOM);
       courses.addActionListener(e->{
           cdheaderpanel.setVisible(true);
           coursedisplay.setVisible(true);
           resultpanel.setVisible(false);
           modinfo.setVisible(false);
       });
       
     
        
    }
    
    void modinfo(){
         modinfo.setBounds(20,40,460,580);
         modinfo.setBackground(new Color(108, 223, 158));
         modinfo.setLayout(null);
         modinfo.setVisible(false);
     
     
     
    
     JLabel head=new JLabel("Student ID:"+id);
     modinfo.add(head);
     head.setBounds(140,35,350,50);
     head.setFont(new Font("serif",Font.BOLD,18));
     
     
     ///////////////////////
     JLabel mod1=new JLabel("Available Modules:");
     modinfo.add(mod1);
     mod1.setBounds(50,90,350,50);
     mod1.setFont(new Font("serif",Font.BOLD,17));
     
     
     
     //modules to display for combo box;
    
     JComboBox mod2=new JComboBox();
      modinfo.add(mod2);
     mod2.setBounds(200,98,170,35);
     
     super.modulelist.clear();
     mod2.removeAllItems();
    String coursetable=super.getmodules("studentmodules",id);
    studentcourse=coursetable;   
     for(String cr111:super.modulelist){
         mod2.addItem(cr111);
                
     }
     
     JLabel coursename=new JLabel();
     modinfo.add(coursename);
     coursename.setBounds(110,2,300,40);
     coursename.setText("Student Course:  "+studentcourse);
      coursename.setFont(new Font("serif",Font.BOLD,18));
     
     
     
     
     modinfo.add(scrollpane);
     scrollpane.setBounds(55,250,350,300);
     
     //need two butttons for works 
     JButton teach=new JButton("Teacher");
     modinfo.add(teach);
     teach.setBounds(110,168,110,35);
     teach.addActionListener(e->{
         String modulet=String.valueOf(mod2.getSelectedItem());
         getmoduleteacher(modulet);
     });
     
     
     
     
      JButton enroll=new JButton("Enroll");
     modinfo.add(enroll);
     enroll.setBounds(230,168,110,35);
     enroll.addActionListener(e->{
         String mc=String.valueOf(mod2.getSelectedItem());
         enrollstudent(id,mc,studentcourse);
     });
     
     
     
     
    }
    
    
    
    void getmoduleteacher(String modules){
        try{
            
             mysqlfunctions db=new mysqlfunctions();
           
            ResultSet rs=db.stmt.executeQuery("select  teacher_id from teacher where (module_1='"+modules+"' || module_2='"+modules+"' ||module_3='"+modules+"' ||module_4='"+modules+"')");  
             jt.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(Exception e){
            System.out.println("erororo"+e);
        }
    }
    
    
    
    
    
    void mycourse(){
        this.mycourse.setBounds(0,100,105,65);
        this.mycourse.setFocusable(false); //removes annoying box around text
        this.mycourse.setText("Mycourse");
         this.mycourse.setFont(new Font("serif",Font.PLAIN,14));
        this.mycourse.setBackground(new Color(128, 243, 193));
       
        //text align of button
        this.mycourse.setHorizontalTextPosition(SwingConstants.CENTER);
        this.mycourse.setVerticalTextPosition(SwingConstants.BOTTOM);
       mycourse.addActionListener(e->{
           
            cdheaderpanel.setVisible(false);
           coursedisplay.setVisible(false);
           resultpanel.setVisible(false);
           modinfo.setVisible(true);
       });
     
    }
        
        
      void resultpanel(){
        resultpanel.setBounds(20,40,460,580);
       resultpanel.setBackground(new Color(108, 223, 158));
        resultpanel.setLayout(null);
        resultpanel.setVisible(false);
        
        JLabel title1=new JLabel();
        resultpanel.add(title1);
        title1.setBounds(70,30,400,50);
         title1.setText("HERALD COLLEGE KATHMANDU");
         title1.setFont(new Font("serif",Font.BOLD,20));
        
         
         String table=super.getmodules("studentmodules",id);
         JLabel title2=new JLabel();
        resultpanel.add(title2);
        title2.setBounds(90,70,400,50);
         title2.setText("RESULT OF:"+table);
         title2.setFont(new Font("serif",Font.BOLD,20));
         
         
        JLabel name=new JLabel();
         resultpanel.add(name);
       name.setBounds(120,110,250,45);
        name.setText("Student Name:  "+id);
        name.setFont(new Font("serif",Font.BOLD,17));
        
       
        resultpanel.add(scrollpane1);
        scrollpane1.setBounds(0,190,460,100);
        jt1.setDefaultEditor(Object.class, null);   
         
       String result111= displaysmarks(table,id);
       
       JLabel result11=new JLabel();
         resultpanel.add(result11);
      result11.setBounds(160,320,250,45);
      result11.setText("Result Status:  "+result111);
       result11.setFont(new Font("serif",Font.BOLD,17));
        
       
                                                                                                                                       
       resultpanel.add(confirm);
       confirm.setBounds(175,390,120,35);
       confirm.addActionListener(e->{
          super.studentconfirm(id);
       });
       
       
       
       
    }
       
       
      

       
    
    void result(){
        result.setBounds(0,170,105,65);
        this.result.setFocusable(false); //removes annoying box around text
        this.result.setText("Result");
         this.result.setFont(new Font("serif",Font.PLAIN,14));
        this.result.setBackground(new Color(128, 243, 193));
       
        //text align of button
        this.result.setHorizontalTextPosition(SwingConstants.CENTER);
        this.result.setVerticalTextPosition(SwingConstants.BOTTOM);
        this.result.addActionListener(e->{
            String table=super.getmodules("studentmodules",id);
            String result111= displaysmarks(table,id);
            if(result111==null||result111.equals("nomarks")){
                cdheaderpanel.setVisible(false);
                 coursedisplay.setVisible(false);
                 modinfo.setVisible(false);
               
               resultpanel.removeAll();
               resultpanel.setVisible(true);
                
            }
            else{
                cdheaderpanel.setVisible(false);
                 coursedisplay.setVisible(false);
                 modinfo.setVisible(false);
                resultpanel.setVisible(true);
            }
        });
       
    }
    

    
       
 
      
      
      //gets marks from result column of a particular student diectly from student table course 
      String displaysmarks(String table,String id){
        try{
            mysqlfunctions db=new mysqlfunctions();
           
            ResultSet rs=db.stmt.executeQuery("select * from "+table+" where Student_id='"+id+"' ");  
             jt1.setModel(DbUtils.resultSetToTableModel(rs));
           
             ResultSet rs1=db.stmt.executeQuery("select result from result where student_id='"+id+"' "); 
             if(rs1.next()){
                 String result=rs1.getString("result");
                 System.out.println("resuklt"+result);
                 return result;
             }
            
        }catch(Exception e){
             
            System.out.println("eororo"+e);
          
        }
        return null;
    }
       
          
    
    
    
    
    
    
    
    
    

    
}
