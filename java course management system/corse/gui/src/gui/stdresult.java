package gui;
import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
public class stdresult {
	
		JFrame jrk = new JFrame();
		JPanel lodu= new JPanel();
		JPanel lalit = new JPanel();
		JLabel head=new JLabel();
		String result="pass";
                JTable jt=new JTable();
                JScrollPane scrollpane=new JScrollPane(jt);
                String id="123";
                String table="";
		void jrk() {
			jrk.setSize (800,700);
			jrk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jrk.setVisible(true);
	       jrk.setResizable(false);
	        jrk.setLayout(null);
	        
	        
	        jrk.add(lodu);
	        lodu();
		}	
		
		
		
		void lodu() {
			lodu.setBounds(0,5,900,655);
			lodu.setBackground( new Color (137,197,219) );   
			lodu.setLayout(null);
			lodu.setVisible(true);
			
			
			lodu.add(lalit);
			lalit();
                        table=getmodules(id);
                        displaysmarks(table,id);
                        
		}
		
		
		void lalit() {
			lalit.setBounds(90,30,600,600);
			lalit.setBackground(Color.	white);  
			lalit.setLayout(null);
			lalit.setVisible(true);
                        
                        
                        lalit.add(head);
                        head.setBounds(20,40,400,40);
                        head.setText("Result Status:"+result);
                        
                        
                        
                        lalit.add(scrollpane);
                        scrollpane.setBounds(40,110,500,200);
                        jt.setDefaultEditor(Object.class, null);  
                        
                        
		}
		
		
                
                
                
                
                
                
     String displaysmarks(String table,String id){
        try{
            mysqlfunctions db=new mysqlfunctions();
           
            ResultSet rs=db.stmt.executeQuery("select * from "+table+" where Student_id='"+id+"' ");  
             jt.setModel(DbUtils.resultSetToTableModel(rs));
           
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

     
     
     String getmodules(String id){
         try{
             
             ResultSet rs=stmt.executeQuery("select * from student where student_id='"+id+"'"); 
                  if(rs.next()){
                   String course=rs.getString("course");
                   int year=rs.getInt("year");
                      System.out.println("studnet id check bhayo hai");
                   ResultSet rs1=stmt.executeQuery("select * from "+course+" where year='"+year+"'"); 
                   while(rs1.next()){
                       System.out.println("eeidieiei");

                   }
                   String table=course+"_year"+year;
                   return table;
                  }
             
             
             
             
         }catch(Exception e){
             System.out.println("eroror"+e);
         }
     }
     
     
		public static void main(String[] args) {
	stdresult c = new stdresult();
	c.jrk();


		}

                
                
                
                
                
                
                
               
	}

