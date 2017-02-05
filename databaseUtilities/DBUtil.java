package databaseUtilities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import javax.swing.JOptionPane;
import org.controlsfx.dialog.Dialogs;
import wizards.Wizard;

 
public class DBUtil 
{	 
	private static final String username ="root";
	   private static final String password ="";
	   private static final String conn_path="jdbc:mysql://localhost/sms?useUnicode=yes&characterEncoding=UTF-8";	   
	  static Connection conn;
	  static Statement stmt;
	  static ResultSet rs;
	  static boolean executed=true;
	  static List<String> elements;
	  static List<String> types;
	  public static boolean LastIdCheck = false;
	 
	  private DBUtil ()
	  {
		  
	  }
	  
	  
	   public static PreparedStatement fill_pstatment(PreparedStatement pstmt)
	   {
		   
		   try {

			     String temp;
			   for (int i = 0; i < elements.size(); i++) 
			   {
				   elements.set(i, CheckSQLInjection(elements.get(i)));
				   temp=types.get(i);
				  
				   if(temp.equals(Wizard.String))
				   {
					   pstmt.setString(i+1,elements.get(i));
				   }
				   else if (temp.equals(Wizard.LikeString))
				   {
					   pstmt.setString(i+1,"%"+elements.get(i)+"%");
				   }
				   else if(temp.equals(Wizard.Integer))
				   {
					   pstmt.setInt(i+1,Integer.parseInt(elements.get(i)));
				   }
				   else if(temp.equals(Wizard.Double))
				   {
					   //System.out.println(Double.parseDouble(elements.get(i)));
						pstmt.setDouble(i+1, Double.parseDouble(elements.get(i)));
				   }
				   else if(temp.equals(Wizard.Date))
				   {
					   pstmt.setDate(i+1,Date.valueOf(elements.get(i)));
				   }
				   else if(temp.equals(Wizard.NULL))
				   {
					   pstmt.setNull(i+1,Types.NULL);
				   }
			   }
			   
		      } 
		   catch (Exception e)
		   {
			System.out.println("DButil fill pstatment error");
		   }
		   return pstmt;
		   
	   }
	   
	   
	   public static ResultSet excecuteQuery(String Q)
	   {
		  Q = CheckSQLInjection(Q);
		  // System.out.println(Q);
		   if (conn!=null)
		   {
			   try
			   {
		          stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			          rs=stmt.executeQuery(Q);			   } 
			   catch(Exception e)
			   {
				  System.out.println(e.getMessage() + "couldn't execute statment "+Q+"in class dbutil execute query");
			   }
		   }
		   else 
		   {
	        	System.out.println("cannot connect to the database");
	            JOptionPane.showMessageDialog(null,"تعذر الاتصال بالمخدم الرجاء المحاولة في وقت لاحق","رسالة خطأ" ,1);
	            System.exit(1);
		  }
		   return rs;
	   }
	  
	   public static Boolean excecuteUpdate(String Q)
	   {
			  Q = CheckSQLInjection(Q);

		   executed=true;
		  // System.out.println(Q);
		   if (conn!=null)
		   {
			   try
			   {
		          stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		          if(LastIdCheck)
		          {
		        	  Wizard.LastGeneratedKey = stmt.executeUpdate(Q,Statement.RETURN_GENERATED_KEYS);
		        	  LastIdCheck=false;
		          }
		          else
		          Wizard.numberOfUpdatedRecords= stmt.executeUpdate(Q);
		          
			   } 
			   catch(Exception e)
			   {
				  System.out.println(e.getMessage() + "couldn't execute update statment "+Q+"in class dbutil execute query");
				  executed=false;
			   }
		   }
		   else
		   {
	        	System.out.println("cannot connect to the database");
	            JOptionPane.showMessageDialog(null,"تعذر الاتصال بالمخدم الرجاء المحاولة في وقت لاحق","رسالة خطأ" ,1);
	            System.exit(1);
			  }
		   return executed;
	   }
	   
	  public static ResultSet excecuteQuery(String Q,List<String> elements1,List<String> types1)
	   {
		  
		   elements = elements1;
		   types = types1;
		   if (conn!=null)
		   {
			   try
			   {
				   PreparedStatement pstmt=conn.prepareStatement(Q,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				  //System.out.println(pstmt);
				   pstmt=fill_pstatment(pstmt);
			       rs=pstmt.executeQuery();	
			   } 
			   catch(Exception e)
			   {
				  System.out.println(e.getMessage());
				  
			   }
		   }
		   else
		   {
			   {
		        	System.out.println("cannot connect to the database");
		            JOptionPane.showMessageDialog(null,"تعذر الاتصال بالمخدم الرجاء المحاولة في وقت لاحق","رسالة خطأ" ,1);
		            System.exit(1);
				  }
		   }
		   return rs;
	   }
	
	  public static Boolean excecuteUpdate(String Q,List<String> elements1,List<String> types1)
	   {
		  executed=true;
		   elements = elements1;
		   types = types1;
		   if (conn!=null)
		   {
			   try
			   {
				   if(LastIdCheck)
			          {
					    PreparedStatement pstmt=conn.prepareStatement(Q,new String[]{"ID"});
					    pstmt=fill_pstatment(pstmt);
			        	  pstmt.executeUpdate();
			        	  ResultSet rs = pstmt.getGeneratedKeys();
			        	  if (rs != null && rs.next()) 
			        	  {
                            Wizard.LastGeneratedKey = rs.getInt(1);
			        	   }
			        	  rs.close();
			        	  LastIdCheck=false;
			          }
			          else
			          {
			        	  PreparedStatement pstmt=conn.prepareStatement(Q,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
						   pstmt=fill_pstatment(pstmt);
						 //  System.out.println(pstmt);
				          Wizard.numberOfUpdatedRecords=pstmt.executeUpdate();
				         // System.out.println(Wizard.numberOfUpdatedRecords);
			          }
			   } 
			   catch(Exception e)
			   {
				  System.out.println(e.getMessage());
				 
				  executed=false;
			   }
		   }
		   else 
		   {
	        	System.out.println("cannot connect to the database");
	            JOptionPane.showMessageDialog(null,"تعذر الاتصال بالمخدم الرجاء المحاولة في وقت لاحق","رسالة خطأ" ,1);
	            System.exit(1);
			  }
		   return executed;
	   }

public static Connection get_Connection() 
	   { 
	         try
	         {
				Class.forName("com.mysql.jdbc.Driver");
			 } 
	         catch (ClassNotFoundException e)
	         {
 	   			//Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم  الفصل فارغا!").message("الرجاء اختيار الفصل").showError();
	        	 System.out.println(e.getMessage()+" error in connection DButil");
			 }
	         if (conn==null)
	         {
	        	  try
	        	  {
				 	conn = DriverManager.getConnection(conn_path,username,password);
				 	  if (conn==null)
				 	  {
				 		  
				 	  }
				  } catch (SQLException e) 
	        	  {
					  {
//						  Dialogs.create()
//					        .owner(null)
//					        .title("Exception Dialog")
//					        .masthead("Look, an Exception Dialog")
//					        .message("Ooops, there was an exception!")
//					        .showException(e);
//						  
			        	System.out.println("cannot connect to the database");
			            JOptionPane.showMessageDialog(null,"تعذر الاتصال بالمخدم الرجاء المحاولة في وقت لاحق","رسالة خطأ" ,1);
			            System.exit(1);
					  }
//			        	Dialogs.create()
//						.owner(null)
//						.title("رسالة خطأ")
//						.masthead("لم يتم الاتصال بالمخدم")
//						.message("الرجاء المحاولة في وقت لاحق")
//						.showWarning();
      	   			//Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم  الفصل فارغا!").message("الرجاء اختيار الفصل").showError();
					System.out.println(e.getMessage()+"error in connection DButil");
				  }
	         }
	         return conn;
	   }

    public static  String  CheckSQLInjection (String Q)
    {
             //Q = Q.replaceAll("-", "");
    	 return Q;
    }

}
