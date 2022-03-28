
package gui;
import java.awt.Color;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;



public class teacher extends mysqlfunctions{
    JFrame trpage= new JFrame("Teacher Dashboard");
    ImageIcon trimage=new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\adminicon.png");
    
    
    JPanel header=new JPanel();
  
    
    
    JPanel body=new JPanel();
    //options for admins 
    JButton courses=  new JButton(new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\course.png"));
    JButton mycourse=new JButton(new ImageIcon("C:\\Users\\nikhi\\Desktop\\corse\\course.png"));
 


   

    
    //panel for teache and module info
    JPanel teacherp=new JPanel();
    JButton update=new JButton("update Marks:");
    JTable jt=new JTable();
    JScrollPane scrollpane = new JScrollPane(jt);
    
     String course="";
           int year=0;
    

String id="";
 public teacher(String id){
     this.id=id;
 }          
           
           
           
          
           
   void   adminframe(){
        this.trpage.setSize(620,700);
        this.trpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  this.adpage.getContentPane().setBackground(new Color(142, 249, 157));
         this.trpage.setResizable(false); 
        this.trpage.setIconImage(trimage.getImage());
        this.trpage.setVisible(true);
        this.trpage.setLayout(null);
        
        
        //headerpanel
        this.trpage.add(header);
        this.headerpanel();
        
        //body panel
        this.trpage.add(body);
        bodypanel();
        
        
    }
   
   
   void headerpanel(){
       this.header.setBounds(0,0,105,700);
       this.header.setBackground(new Color(108, 223, 158));
       this.header.setLayout(null);
       this.header.add(courses);
       
     
       
        this.header.add(mycourse);
        mycourse();
     
       
   }
    
  void bodypanel(){
      this.body.setBounds(105,0,620,700);
      this.body.setBackground(new Color(75, 179, 137));
      this.body.setLayout(null);
      
      
       
      
       
       
       body.add(teacherp);
       teacherpanel();
  }
    
 
  
    
    
    
  
    
    void mycourse(){
        this.mycourse.setBounds(0,100,105,65);
        this.mycourse.setFocusable(false); //removes annoying box around text
        this.mycourse.setText("Mymodule");
         this.mycourse.setFont(new Font("serif",Font.PLAIN,14));
        this.mycourse.setBackground(new Color(128, 243, 193));
       
        //text align of button
        this.mycourse.setHorizontalTextPosition(SwingConstants.CENTER);
        this.mycourse.setVerticalTextPosition(SwingConstants.BOTTOM);
       mycourse.addActionListener(e->{
          
           teacherp.setVisible(true);
       });
     
        
        
        
    }
    
    
    void teacherpanel(){
       teacherp.setBounds(20,40,460,580);
       teacherp.setBackground(new Color(75, 179, 137));
       
     teacherp.setLayout(null);
     teacherp.setVisible(false);
     
     
     
     JLabel top=new JLabel();
     teacherp.add(top);
     top.setBounds(40,5,350,50);
     
     top.setText("Welcome To Teacher Dashboard:"+id);
     top.setFont(new Font("serif",Font.BOLD,18));
     
     
     //for modules info
     JLabel mod=new JLabel("Alloted Modules:");
     teacherp.add(mod);
     mod.setBounds(60,60,350,50);
      mod.setFont(new Font("serif",Font.BOLD,17));
     
      //module box
      JComboBox modulbox=new JComboBox();
      teacherp.add(modulbox);
      modulbox.setBounds(195,68,170,35);
      super.getmodules("tmodules",id);
      for(String c:super.modulelist){
          modulbox.addItem(c);
      }
      
      
      
      
      JButton details=new JButton("Details");
      teacherp.add(details);
      details.setBounds(180,120,100,35);
      details.addActionListener(e->{
          String module=String.valueOf(modulbox.getSelectedItem());
          displaystunts(module);
      });
      
      
      //now create a table 
     teacherp.add(scrollpane);
     scrollpane.setBounds(10,180,440,300);
     
     
    updatemarksbtn();
   
    }
    
    
   
    
 
    
    
   void displaystunts(String module){
        try{
            mysqlfunctions db=new mysqlfunctions();
           super.courselist();
          
           for(String course1:super.courselist){
               ResultSet rs=db.stmt.executeQuery("select * from "+course1+" where module_code = '" + module + "'");
               if(rs.next()){
                   year=rs.getInt("year");
                   course=course1;
                   int value=1;
                   ResultSet rs1=db.stmt.executeQuery("select Student_id,"+module+" from "+course+"_year"+year+" where "+module+" >= '" + value + "'");  
           
                   jt.setModel(DbUtils.resultSetToTableModel(rs1));
                   break;
               }
               else{
                   System.out.println("module no matched in anyofthe course");
               }
           }
             //since we know course and year of the specific module
             
           
           
            
        }catch(Exception e){
             //JOptionPane.showMessageDialog(null,"select valid course/year","no details to show",JOptionPane.ERROR_MESSAGE);
            System.out.println("eororo"+e);
          
        }
    }
    
    
    
    public void updatemarksbtn(){
         //upate marks button to update 
    
     teacherp.add(update);
     update.setBounds(170,500,130,35);
     update.addActionListener(e->{
         int row =jt.getSelectedRow();
         String module=jt.getColumnName(1);
         String id=(String)jt.getValueAt(row,0);
         String upmarks=String.valueOf(jt.getValueAt(row, 1));
        super.updatemarks(id,module,upmarks,course,year);
         
     });
       
    }
    
    
    
    public static void main(String[] args) {
        teacher a=new teacher("nikan");
        a.adminframe();
    }
    
}
