
package gui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java. util.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.*;
import javax.swing.JOptionPane;


public class mysqlfunctions {
    //basic object insitialization and coneection to databse 
 
    Connection con;
    Statement stmt;
    public mysqlfunctions(){  
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            con =DriverManager.getConnection("jdbc:mysql:///course","root","");    
            stmt=con.createStatement();  
            
           
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }  


 
   ArrayList<String> modulelist=new ArrayList<String>();
      //array list to store course names from database
     ArrayList<String> courselist=new ArrayList<String>();
     ArrayList<String> coursecredit=new ArrayList<String>();
    
     
     //array lsit for not available courses
      ArrayList<String> courselist1=new ArrayList<String>();
     ArrayList<String> coursecredit1=new ArrayList<String>();
    //imp counter to acess list and display info
   
    

    


  //method to check login and open admin teacher and studnet interface  
   public   String[] checklogin(String usname,String password){
     
      try{
         
          
           new mysqlfunctions();
        
        String qr="select * from adminlogin where username = '" + usname + "' and password = '" + password + "'";
        ResultSet rs=stmt.executeQuery(qr);  
        String[] idname=new String[2];
        //if data is returned true
        if(rs.next()){
             
           
            //getting login type 
            String logintype=rs.getString("type");
            System.out.println("type:  "+logintype);
            
            //retrun something according to login type and and use that return type to acess other frames 
            if(logintype.equals("admin")){
               idname[0]="admin";
               idname[1]=usname;
               return idname;
            }
            else if(logintype.equals("teacher")){
                idname[0]="teacher";
               idname[1]=usname;
               return idname;
            }
            else if(logintype.equals("student")){
                idname[0]="student";
               idname[1]=usname;
               return idname;
            }}
        else{
            JOptionPane.showMessageDialog(null,"INVALID ID/PASSWORD","login failed",JOptionPane.ERROR_MESSAGE);
            
        }
        }
       catch(Exception e){
            System.out.print("erororo"+e);
       }
      
      return null;
        
   }
   
   
  
 
    
   
   
  
 //insert course table 
   void insert(String cname,String ccredit,String cduration,String csemester,String avai){
         try{
            
           new mysqlfunctions();  
        //when executing below query only username is matched if available in the table or not 
        ResultSet rs=stmt.executeQuery("select * from corse where course_name = '" + cname + "' ");  
      
        // if the passed corsename matches it checks for next row thatis type if avalbel true 
        if(rs.next()){
            
            String corsename=rs.getString("course_name");
            System.out.println("type:  "+corsename);
            
           JOptionPane.showMessageDialog(null,"Course already exist","Course adition failed",JOptionPane.ERROR_MESSAGE);
            
            
        }
        else{
            //insert data into the table of database
          PreparedStatement stmt2= con.prepareStatement("insert into corse VALUES ('"+cname+"','"+ccredit+"','"+cduration+"','"+csemester+"','"+avai+"')");
          
          
          //create table with same course name insideted into courrse name with module column
          String table="CREATE TABLE "+cname+"("+"module_name VARCHAR(100),"+"module_code VARCHAR(15),"+"year INTEGER(5),"+"semester INTEGER(10))";
          PreparedStatement stmt3= con.prepareStatement(table); 
         
          
          if (stmt2.executeUpdate()==1){
              
              System.out.println("statement 2 chalyo");
             if(stmt3.executeUpdate()==0) {
              System.out.println("new course added/ table also created ");
              JOptionPane.showMessageDialog(null,"New course added/table created");
              
             }
                 
          }
         //create table of different year
          int year=Integer.parseInt(cduration);  
          for(int i=1;i<=year;i++)  {
               String yeartable="CREATE TABLE "+cname+"_year"+i+"("+"Student_id VARCHAR(100))";
               PreparedStatement stmt4= con.prepareStatement(yeartable); 
                   stmt4.executeUpdate();
                 
          }      
            
        }
        }
       catch(Exception e){
            System.out.print("erororo"+e);
           
        }
   }
   
   //insert module method
   void insertmodules(String coursename,String module_name,String module_code,String year,String semester){
        try{
            
           new mysqlfunctions();
            DatabaseMetaData dbm=con.getMetaData();
        
        //when executing below query only username is matched if available in the table or not 
        ResultSet rs=dbm.getTables(null,null,coursename,null); 
        ResultSet rs1=stmt.executeQuery("select * from "+coursename+" where module_code = '" + module_code + "' ");  
       
        if(rs1.next()){
            JOptionPane.showMessageDialog(null,"Module already exist","module adition failed",JOptionPane.ERROR_MESSAGE);
        }
        else if(rs.next()){
            
            System.out.println("table exist hai kanxa");
            PreparedStatement stmt2= con.prepareStatement("insert into "+coursename+" VALUES ('"+module_name+"','"+module_code+"','"+year+"','"+semester+"')");
            
            if(stmt2.executeUpdate()==1){
               JOptionPane.showMessageDialog(null," module :"+module_name+"sucessfully added");
           }
           int yr=Integer.parseInt(year); 
           int value=0;
           PreparedStatement stmt3= con.prepareStatement("ALTER TABLE "+coursename+"_year"+yr+" ADD "+module_code+" VARCHAR(100) null ");
           if(stmt3.executeUpdate()==0){
               System.out.println("module code added in year table");
           }
           
        }
        else{
             JOptionPane.showMessageDialog(null,"course table doesnot exist","module adition failed",JOptionPane.ERROR_MESSAGE);
        }


        }
       catch(Exception e){
            System.out.print("erororo"+e);
           
       }
       
   }
   
   
    //delete remove restore
    void delcourse(String course,String action){
        try{
             mysqlfunctions db=new mysqlfunctions();
         ResultSet rs=db.stmt.executeQuery("select * from corse where course_name = '" + course + "' ");  
         if(rs.next()){
             int year=rs.getInt("Course_duration");
             
             if (action=="delete"){
                 //delete row from corse table 
                 String sql1="delete from corse where course_name='"+course+"'";
                 String sql2="DROP TABLE "+course+"";
                 PreparedStatement stmt2= db.con.prepareStatement(sql1);
                  PreparedStatement stmt3= db.con.prepareStatement(sql2);
                  
                    if(stmt2.executeUpdate()==1&&stmt3.executeUpdate()==1){
                  System.out.println("course infor and table both deleted hai");
              }
                  
                 for(int i=1;i<=year;i++ ){
                      String sql3="DROP TABLE "+course+"_year"+i+"";
                      PreparedStatement stmt4= db.con.prepareStatement(sql3);
                      
                  if(stmt4.executeUpdate()==1){
                      System.out.println("year table:"+i);
                  }
             }
                 JOptionPane.showMessageDialog(null," course succesfully deleted");
             
             }
             else if(action=="remove"){
                 String sql="update corse set available='no' where course_name='"+course+"'";
                  PreparedStatement stmt= db.con.prepareStatement(sql);
                  if(stmt.executeUpdate()==1){
                      JOptionPane.showMessageDialog(null," course succesfully made not available");
                  }
             }
             else if(action=="restore"){
                  String sql="update corse set available='yes' where course_name='"+course+"'";
                  PreparedStatement stmt= db.con.prepareStatement(sql);
                  if(stmt.executeUpdate()==1){
                      JOptionPane.showMessageDialog(null," curse succesfully restored");
                  }
             }
             
         }
         
         else{
             JOptionPane.showMessageDialog(null,"invalid course name","course delete failed",JOptionPane.ERROR_MESSAGE);
         }
        }
        
        catch(Exception e){
            System.out.println(e);
        }  
    }
  
   
   
  
    
    
 //edit courser name
     
   void edcoursename(String oldc,String newc) {
       try{
           mysqlfunctions db=new mysqlfunctions();
            ResultSet rs=db.stmt.executeQuery("select * from corse where course_name = '" + oldc + "' ");  
      
        if(rs.next()){
           int year=rs.getInt("Course_duration");
            String sql="update corse set course_name='"+newc+"' where course_name='"+oldc+"'";
            String sql1="ALTER TABLE "+oldc+" RENAME To "+newc+"" ;  
            PreparedStatement stmt= db.con.prepareStatement(sql);
            PreparedStatement stmt1= db.con.prepareStatement(sql1);
            
           if(stmt.executeUpdate()==1 &&stmt1.executeUpdate()==0 ){
              System.out.println("coursename edit bhayo hai kanxa");
           }
           
           
          for (int i=1;i<=year;i++) {
              String sql2="ALTER TABLE "+oldc+"_year"+i+" RENAME To "+newc+"_year"+i+"" ;  
              PreparedStatement stmt2= db.con.prepareStatement(sql2);
              if(stmt2.executeUpdate()==0){
                  System.out.println("aruuuu year table ko ni name change bhayo hai");
              }
          } 
          
          //prepeare statment to change the course name in student table
           ResultSet rs1=db.stmt.executeQuery("select * from student");
           while(rs1.next()){
               String sql4="update student set course='"+newc+"' where course='"+oldc+"'";
               PreparedStatement stmt4=db.con.prepareStatement(sql4);
               if(stmt4.executeUpdate()==0){
                   System.out.println("student table ma pani coursename change bhayo hia");
               }
           }
            
             JOptionPane.showMessageDialog(null,"Course name succesfully edited ");
           
        }
        
        
        else{
            JOptionPane.showMessageDialog(null,"Course doesnot exist","Course edit failed",JOptionPane.ERROR_MESSAGE);
        }
  
           
       }
       catch(Exception e){
           System.out.println("ewrwer"+e);
       }
   }
    
   
   
   
   //edit module info
   void edmodulename(String oldm,String newm,String newmcode,String course){
       try{
           
           mysqlfunctions db=new mysqlfunctions();
           ResultSet rs=db.stmt.executeQuery("select * from "+course+" where module_name = '" + oldm + "' ");  
           if (rs.next()){
               int year=rs.getInt("year");
               String mcode=rs.getString("module_code");
                String sql="update "+course+" set module_name='"+newm+"',module_code='"+newmcode+"' where module_name='"+oldm+"'";
                String sql1="ALTER TABLE "+course+"_year"+year+" CHANGE "+mcode+"  "+newmcode+" VARCHAR(100) null";
                PreparedStatement stmt= db.con.prepareStatement(sql);
                 PreparedStatement stmt1= db.con.prepareStatement(sql1);
               
                if(stmt.executeUpdate()==1&&stmt1.executeUpdate()==0){
                   JOptionPane.showMessageDialog(null,"module name and code succesfully changed"); 
                }
             
               
           }
           else{
               JOptionPane.showMessageDialog(null,"module doesnot exist in the given course","module edit failed",JOptionPane.ERROR_MESSAGE); 
           }
           
       }
       catch(Exception e){
           System.out.println("erorroro"+e);
       }
       
   }
    


   
   
   
  //gets courselist from the databse and stores it in array   
    void courselist(){
        try{
         mysqlfunctions db=new mysqlfunctions();
        //get all data from table corse 
        ResultSet rs=db.stmt.executeQuery("select * from corse");  
         
      
        while(rs.next()){
           String cnames=rs.getString("course_name");
           String credit=rs.getString("course_credit");
           String available=rs.getString("available");
            System.out.println(available);
          if (courselist.contains(cnames) ||courselist1.contains(cnames)){
               System.out.println("the given course is already in arralist");
           }
           else{   
              if (available.equals("yes")){
                  System.out.println("if condition chalyo hai ");
            courselist.add(cnames);
            coursecredit.add(credit);
            
              }
              else if(available.equals("no")){
                  System.out.println("else condition chalyo hai ");
                  courselist1.add(cnames);
                  coursecredit1.add(credit);
              }
           }
            
        }
System.out.println(courselist.size());
    }
        catch(Exception e){
            System.out.println("sdfa"+e);
        }
    }
    
   
   
    //gets modules of given course and stres it in arraylist
  String  getmodules(String action,String tablename){
      try{
           if(action.equals("modules")){
                    ResultSet rs=stmt.executeQuery("select * from "+tablename+""); 
                   
                while(rs.next()){
                    
                    String modulename=rs.getString("module_name");
                    modulelist.add(modulename);
                }
                
                
               
        }
        else if(action.equals("tmodules")){
                  ResultSet rs=stmt.executeQuery("select * from teacher where teacher_id='"+tablename+"'"); 
                  if(rs.next()){
                    for(int i=1;i<=4;i++){
                        String modules=rs.getString("module_"+i+"");
                        modulelist.add(modules) ;
               
                    }
                  }
                
            
        }
        else if(action.equals("studentmodules")){
             ResultSet rs=stmt.executeQuery("select * from student where student_id='"+tablename+"'"); 
                  if(rs.next()){
                   String course=rs.getString("course");
                   int year=rs.getInt("year");
                      System.out.println("studnet id check bhayo hai");
                   ResultSet rs1=stmt.executeQuery("select * from "+course+" where year='"+year+"'"); 
                   while(rs1.next()){
                       
                       String modulecode=rs1.getString("module_code");
                       
                       modulelist.add(modulecode);
                   }
                   String table=course+"_year"+year;
                   return table;
                  }
        }
       
      }
      catch(Exception e){
          System.out.println("eororor"+e);
      }
      
      String emp="";
      return emp;
      
      
  }
    
   
    
  public void createacount(String name,String usname,String pass,String type,String course){
     try{
           mysqlfunctions db=new mysqlfunctions();
        
        //when executing below query only username is matched if available in the table or not 
        ResultSet rs=db.stmt.executeQuery("select * from adminlogin where username = '" + usname + "' ");  
      
        
        
        // if the passed username and password matches it checks for next row thatis type if avalbel true 
        if(rs.next()){
            
            String logintype=rs.getString("type");
            System.out.println("type:  "+logintype);
            
           JOptionPane.showMessageDialog(null,"Account already exist","Account creation failed",JOptionPane.ERROR_MESSAGE);
            
            
        }
        else{
            //insert data into the table of database
          PreparedStatement stmt2= db.con.prepareStatement("insert into adminlogin VALUES ('"+name+"','"+usname+"','"+pass+"','"+type+"')"); 
       
          if (stmt2.executeUpdate()==1){
              
              int styear=1;
              
               if (type.equals("student")){
            PreparedStatement stmt3= db.con.prepareStatement("insert into student VALUES ('"+usname+"','"+course+"','"+styear+"')"); 
            PreparedStatement stmt4= db.con.prepareStatement("insert into "+course+"_year"+1+" (Student_id) VALUES ('"+usname+"')"); 
            if(stmt3.executeUpdate()==1 &&stmt4.executeUpdate()==1){
                System.out.println("student detaiuls inserted into student tabkle and year table");
            }
        }
               
         if(type.equals("teacher")) {
             PreparedStatement stmt4= db.con.prepareStatement("insert into  `teacher`(`teacher_id`) VALUES ('"+usname+"')");
             if(stmt4.executeUpdate()==1){
                 System.out.println("teacher id inserted into teacher table hao");
             }
         }     
               
              
              JOptionPane.showMessageDialog(null,"New account created ");
             
              
              
          }
          
            
        }
        }
       catch(Exception e){
            System.out.print("erororo"+e);
           
           
           
        }
      
 }
  
  
  
  void deleteacount(String id){
     try{
         mysqlfunctions db=new mysqlfunctions();
       
         ResultSet rs=db.stmt.executeQuery("select * from adminlogin where username = '" + id + "' "); 
       if(rs.next()){
            String type=rs.getString("type");
          
            
         if(type.equals("student")){
             ResultSet rs1=db.stmt.executeQuery("select * from student where student_id = '" + id + "' ");
             
            if(rs1.next()){
               String course=rs1.getString("course");
               int year=rs1.getInt("year");
               
                String sql="delete from adminlogin where username='"+id+"'";
                String sql1="delete from student where student_id='"+id+"'";
                String sql2="delete from "+course+"_year"+year+" where student_id='"+id+"'";
                PreparedStatement stmt= db.con.prepareStatement(sql);
                 PreparedStatement stmt1= db.con.prepareStatement(sql1);
                   PreparedStatement stmt2= db.con.prepareStatement(sql2);
                 if(stmt.executeUpdate()==1&&stmt1.executeUpdate()==1&&stmt2.executeUpdate()==1){
                   JOptionPane.showMessageDialog(null,"student account deleted");
                 }
       
                }
                else{
                      System.out.println("the given account doesnot have info");
                      }
           
          }
         else if(type.equals("teacher")){
              String sql="delete from adminlogin where username='"+id+"'";
                String sql1="delete from teacher where teacher_id='"+id+"'";
              
                PreparedStatement stmt= db.con.prepareStatement(sql);
                 PreparedStatement stmt1= db.con.prepareStatement(sql1);
             
                 if(stmt.executeUpdate()==1&&stmt1.executeUpdate()==1){
                   JOptionPane.showMessageDialog(null," teacher account deleted");
                 }
         }
            
             
         }
         
         else{
              JOptionPane.showMessageDialog(null,"invalid user id","Account delete failed",JOptionPane.ERROR_MESSAGE);
          
         }
          
         
     }
     catch(Exception e){
         System.out.println("erroror"+e);
     }
 }
 
    
    
    void publishupdate(String id,String result,String action,int y1,String c1){
        try{
            mysqlfunctions db=new mysqlfunctions();
             ResultSet rs=stmt.executeQuery("select * from result where student_id = '" + id + "' "); 
                if(action.equals("publish")){
                    if(rs.next()){
                         JOptionPane.showMessageDialog(null,"srudent result already published","publish failed",JOptionPane.ERROR_MESSAGE); 
                    }
                    else{
                         PreparedStatement stmt2= con.prepareStatement("insert into result(student_id,result) VALUES ('"+id+"','"+result+"')");
            
                    if(stmt2.executeUpdate()==1){
                    JOptionPane.showMessageDialog(null," result of :"+id+"    sucessfully pusblished");
            
                    }
                    } }
                
                
               
              else if(action.equals("update")){
                   if(rs.next()){
                       String sql="update result set result='"+result+"' where student_id='"+id+"'";
                PreparedStatement stmt= db.con.prepareStatement(sql);
                if(stmt.executeUpdate()==1){
                    JOptionPane.showMessageDialog(null," result of :  "+id+" sucessfully updated");
                }
                   }else{
                        JOptionPane.showMessageDialog(null,"student result no published","result update failed",JOptionPane.ERROR_MESSAGE); 
                   }
               } 
              
              
              
              
              else if(action.equals("pass")){
                  if(rs.next()){
                      String resulth=rs.getString("result");
                      int confirmh=rs.getInt("confirm");
                     if(confirmh==1){
                         if(resulth.equals("pass")){
                             int y2=y1+1;
                             
                       
                        PreparedStatement stmt3= con.prepareStatement("delete from result where student_id='"+id+"'");
                        if(stmt3.executeUpdate()==1){
                            System.out.println("student deleted ");
                        }
                        //checking if next year table exist or not
                        String coursetable=c1+"_year"+y2;
                        DatabaseMetaData dbm=con.getMetaData();  
                        ResultSet rst=dbm.getTables(null,null,coursetable,null);
                        if(rst.next()){
                             String sql1="delete from "+c1+"_year"+y1+" where Student_id='"+id+"'";
                             PreparedStatement stmt2= db.con.prepareStatement(sql1);
                             String sql="update student set year='"+y2+"' where student_id='"+id+"'";
                             PreparedStatement stmt= db.con.prepareStatement(sql);
                             PreparedStatement stmt4= con.prepareStatement("insert into "+c1+"_year"+y2+"(student_id) VALUES ('"+id+"')");
                     
                     if(stmt.executeUpdate()==1 &&stmt2.executeUpdate()==1&&stmt4.executeUpdate()==1){
                          JOptionPane.showMessageDialog(null," year of :  "+id+" sucessfully updated");
                     }
                        }
                        //new course table of different year not avialabel
                        else{
                            //deletes student info from every where as it is going to graduate
                            deleteacount(id);
                            PreparedStatement stmtgr= con.prepareStatement("insert into graduate VALUES ('"+id+"','"+c1+"')");
                            if(stmtgr.executeUpdate()==1){
                            JOptionPane.showMessageDialog(null," student:  "+id+" sucessfully graduated the course");
                     }
                            
                        }
                    
                     
                     
                     
                     
                         }
                         
                         else if(resulth.equals("fail")){
                             int nn=0;
                          String sql="delete from "+c1+"_year"+y1+" where Student_id='"+id+"'";
                          PreparedStatement stmt= db.con.prepareStatement(sql);
                          PreparedStatement stmt1= con.prepareStatement("insert into "+c1+"_year"+y1+"(student_id) VALUES ('"+id+"')");
                          PreparedStatement stmt2= con.prepareStatement("delete from result where student_id='"+id+"'");
                           if(stmt.executeUpdate()==1 &&stmt1.executeUpdate()==1 &&stmt2.executeUpdate()==1){
                           JOptionPane.showMessageDialog(null," year of :  "+id+" sucessfully updated");
                     }
                     }
                         
                         
                     }
                     else{
                         JOptionPane.showMessageDialog(null,"student confirmation not provided","student detail update failed",JOptionPane.ERROR_MESSAGE); 
                     }
                  }
              }
               
            
            
            
          
            
        }catch(Exception e){
            System.out.println("eoeoeo"+e);
        }
    }
    
    
    
  
  
  
  //enroll teach to modules
    void enrollteach_modules(String teach,String module,String course){
        try{
            mysqlfunctions db=new mysqlfunctions();
            ResultSet rs=db.stmt.executeQuery("select * from "+course+" where module_name = '" + module + "' ");  
            if(rs.next()){
                String mcode=rs.getString("module_code");
              
               
                    ResultSet rs1=db.stmt.executeQuery("select * from teacher where teacher_id = '" + teach + "' ");  
                    
                    if(rs1.next()){
                       String execute="";
                       String match="";
                        for(int i=1;i<=4;i++){
                            String modules=rs1.getString("module_"+i+"");
                           
                            if(modules.equals(mcode)){
                                match="matchbhayo";
                                  JOptionPane.showMessageDialog(null,"teacher already enrolled in the course","enroll failed",JOptionPane.ERROR_MESSAGE);
                                break;
                        }
                        }
                        
                        
                       if(match.equals("")){
                            for(int i=1;i<=4;i++){
                            String modules=rs1.getString("module_"+i+"");
                                if(modules.equals("null")){
                               
                             String sql="update teacher set module_"+i+"='"+mcode+"' where teacher_id = '" + teach + "'";
                               java.sql.PreparedStatement stmt= db.con.prepareStatement(sql);
                               if(stmt.executeUpdate()==1){
                                   execute="executed";
                                    JOptionPane.showMessageDialog(null,"teacher enrolled in "+mcode+"");
                                   break;
                               }
                                    }
                                    }
                            }
                            
                            
                        if(execute.equals("")&&match.equals("")){
                            JOptionPane.showMessageDialog(null,"teacher packed","enroll failed",JOptionPane.ERROR_MESSAGE);
                        }
                            
                        }
                        
       
                
         
                
            }else{
               JOptionPane.showMessageDialog(null,"invalid module course name","enroll/ remove failed",JOptionPane.ERROR_MESSAGE);
            }
            
            
 
            
        }
        catch(Exception e){
            System.out.println("errerr"+e);
        }
    }
    
  
  
    
    
    //remove teach from modules
    
    void removeteachmodules(String teach,String module){
        
        try{
            mysqlfunctions db=new mysqlfunctions();
                     ResultSet rs1=db.stmt.executeQuery("select * from teacher where teacher_id = '" + teach + "' ");  
                     
                     
                     if(rs1.next()){
                         for(int i=1;i<=4;i++){
                           String modules=rs1.getString("module_"+i+"");
                           
                           if(module.equals("null")){
                               JOptionPane.showMessageDialog(null,"null module","enroll/ remove failed",JOptionPane.ERROR_MESSAGE);
                               break;
                           }
                           else if(module.equals(modules)){
                                 String sql="update teacher set module_"+i+"='null' where teacher_id = '" + teach + "'";
                               java.sql.PreparedStatement stmt= db.con.prepareStatement(sql);
                               if(stmt.executeUpdate()==1){
                                    JOptionPane.showMessageDialog(null,"teacher removed from "+module+"");
                                   break;
                               }
                           }
                         }
                     }
                 

            
        }catch(Exception e){
            System.out.println("ererer"+e);
        }
        
        
        
        
        
    }
    
    
   //student enroll into available modules 
    void enrollstudent(String id,String mcode,String studentcourse){
        try{
             mysqlfunctions db=new mysqlfunctions();
             
              ResultSet rs=db.stmt.executeQuery("select "+mcode+" from "+studentcourse+" where Student_id = '" + id + "' ");  
             if (rs.next()){
                 int enroll=rs.getInt(mcode);
                 if(enroll>=1){
                     JOptionPane.showMessageDialog(null,"already enrolled in the module","enrollfailed",JOptionPane.ERROR_MESSAGE);
                 }
                 else{
                      int val=1;
             String sql="update "+studentcourse+" set "+mcode+"='"+val+"' where Student_id = '" + id + "'";
             java.sql.PreparedStatement stmt= db.con.prepareStatement(sql);
            if(stmt.executeUpdate()==1){
                JOptionPane.showMessageDialog(null,"student enrolled in "+mcode+"");
            }
            else{
                System.out.println("not enrolled");
            }
                 }
             }
             
            
            
  
        }catch(Exception e){
            System.out.println("eororo"+e);
        }
    }
    
   
//provide confirmation by student that he has seen the result    
  public  void studentconfirm(String id){
        try{
            mysqlfunctions db=new mysqlfunctions();
          ResultSet rs=db.stmt.executeQuery("select * from result where Student_id='"+id+"' ");  
            if(rs.next()){
                int ccc=1;
                 String sql="update result set confirm='"+ccc+"' where Student_id='"+id+"'";
                 PreparedStatement stmt= db.con.prepareStatement(sql);
                 if(stmt.executeUpdate()==1){
                      JOptionPane.showMessageDialog(null,"result confirmed"); 
                 }
            }
            
            
            
        }catch(Exception e){
            System.out.println("eeororo"+e);
        }
    }  
      
      
    
    
    
  
 //insert marks to student byteacher 
    void updatemarks(String id,String module,String marks,String course,int year){
        try{
            
            int mark1=Integer.parseInt(marks);  
            mysqlfunctions db=new mysqlfunctions();
            String sql="update "+course+"_year"+year+" set "+module+"='"+mark1+"' where Student_id='"+id+"'";
                  PreparedStatement stmt= db.con.prepareStatement(sql);
                  if(stmt.executeUpdate()==1){
                      JOptionPane.showMessageDialog(null," student marks succesfully updated");
                  }
            
            
        
            
        }catch(Exception e){
            System.out.println("eroorro"+e);
        }
    }
    
 
    
    
    
    
    
    
}
