package wizards;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.ComponentPositionType;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

import org.controlsfx.control.ButtonBar;
import org.controlsfx.control.ButtonBar.ButtonType;
import org.controlsfx.control.InfoOverlay;
import org.controlsfx.control.NotificationPane;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import com.sun.javafx.binding.StringFormatter;

import databaseUtilities.DBUtil;
import databaseUtilities.JDBC;
import models.*;
import application.MainClass;

public class Wizard 
{
	static Connection conn = null;
	static java.sql.Statement stmt = null;
	public static boolean connected = false;
	public static final String String = "String";
	public static final String Integer = "Integer";
	public static final String Double = "Double";
	public static final String LikeString = "LikeString";
	public static final String Date = "Date";
	public static final String NULL = "NULL"; 
	public static final String Text = "Text"; 
	

	/////////////////////////////////////////////////
	public static final String Student = "Student";
	public static final String Teacher = "Teacher";
	public static final String TClass="TClass";
	public static final String SClass = "SClass";
	public static final String transporation = "transporation";
	public static final String Worker = "Worker";
	public static final String Result = "Result";
	public static final String Subject = "Subject";
	public static final String GeneralPayment = "GeneralPayment";
	public static final String StudentPayment = "StudentPayment";
	public static final String TeacherPayment = "TeacherPayment";
	public static final String WorkerPayment = "WorkerPayment";
	// ///////////////////////////////////////////////
	
//	   public static TableColumn Exam = createColumn(0,"الامتحان");
//	   public static TableColumn Classwork = createColumn(1,"أعمال السنة");
  // ///////////////////////////////////////////////

	public static final List <String> specializations =Arrays.asList("علمي","أدبي");
	public static final ObservableList<String> ResultTypes = FXCollections.observableArrayList("الفصل الدراسي الأول","الفصل الدراسي  الثاني","نتيجة شهرية");
	public static final ObservableList<String> ResultTypesForComboBox = FXCollections.observableArrayList("","الفصل الدراسي الأول","الفصل الدراسي  الثاني","نتيجة شهرية");
	public static final ObservableList<String> GeneralPaymentsOutcomeTypes = FXCollections.observableArrayList("","كهرباء","ماء","إيجار","قرض","طباعة",
			"أخرى");
	public static final ObservableList<String> GeneralPaymentsOutcomeTypesforComboBox = FXCollections.observableArrayList("","كهرباء","ماء","إيجار","قرض","طباعة",
			"أخرى");
	public static final ObservableList<String> GeneralPaymentsIncomeTypesforComboBox = FXCollections.observableArrayList("","قرض","أخرى");
	public static final ObservableList<String> PaymentsTypesforComboBox = FXCollections.observableArrayList("","تكاليف","إيرادات");
	public static final ObservableList<String> STudentPaymentsTypesforComboBox = FXCollections.observableArrayList("","رسوم دراسية","رسوم الكورس الصيفي","رسوم ترحيل","رسوم مجلس الاباء","أخرى");
	public static final ObservableList<String> TeacherPaymentsTypesforComboBox = FXCollections.observableArrayList("","دفعية راتب","علاوات","رسوم ترحيل","قرض","أخرى");
	public static final ObservableList<String> WorkerPaymentsTypesforComboBox = FXCollections.observableArrayList("","دفعية راتب","علاوات","قرض","أخرى");

	
	public static final List <String> choices =Arrays.asList("Yes","No");
	public static Image DefaultStudentImage=null;
	public static Image DefaultTeacherImage=null;
	public static Image DefaultWorkerImage=null;

	public static int global_selected_id=1;
	public static GeneralPayment global_selected_generalPayment= new GeneralPayment();
	public static StudentPayment global_selected_StudentPayment= new StudentPayment();
	public static TeacherPayment global_selected_TeacherPayment= new TeacherPayment();
	public static WorkerPayment global_selected_WorkerPayment= new WorkerPayment();
	
	
     public static String global_Image_Path = null;
     public static String phpMyAdminUrl = "http://localhost/phpmyadmin/";
     public static String SchoolName = "مدرسة الغالي  الثانوية الخاصة بنين";
     public static Image SchoolOfficialMark = null;
    
     
	public static int numberOfUpdatedRecords = 0;
	public static int LastGeneratedKey = 0;

	public static String myJarPath=null;
    public static ObservableList<String> StudentTypes;
    public static ObservableList<String> specializationslist;
    public static ObservableList<String> VehiclesTypes ;

    public static ObservableList<String> ClassesLevels;
//    public static ObservableList<String> ClassesNames;

    public static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static String defaultDatepattern ="dd/MM/yyyy";
    public static StringConverter<LocalDate> dateconverter ;
	// //////////////////////////////////////////////
	 public static boolean trueLogin = false; 
	 
		// //////////////////////////////////////////////
	   static int PaymentFieldHighet = 20;
	   static String PaymentHeader = "نظام الدفع الالكتروني"+"\n"+"إشعار إستلام نقدي";
		// //////////////////////////////////////////////

	  
	 public static StringConverter<LocalDate> DatePickerConverter ()
	 {
		 if(dateconverter==null)
		 {
			 dateconverter= new StringConverter<LocalDate>() 
				{
				     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(defaultDatepattern);

				     @Override 
				     public String toString(LocalDate date)
				     {
				         if (date != null) {
				             return dateFormatter.format(date);
				         } else {
				             return "";
				         }
				     }

				     @Override 
				     public LocalDate fromString(String string)
				     {
				         if (string != null && !string.isEmpty()) {
				             return LocalDate.parse(string, dateFormatter);
				         } else {
				             return null;
				         }
				     }
				 };
		 }
		 return dateconverter;
		 
	 }
	private static ObservableList<String> ClassesNames=null;
	 
	public static String UTF(String s)
	{
		try {
			return new String(s.getBytes(), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
///////////////////////////////////////////////////////////////////////////////

	public static class point 
	{
		int x;
		public int getX() 
		{
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		int y;
		public point (int x, int y)
		{
			this.x=x;
			this.y=y;
		}
		
	}
	
//	public static class Mark 
//	{
//	
//	
//	}

	public static point Globaltable_point ;
	
	public static String GlobalCellValue;
	///////////////////////////////////////////////////////////////////////////////

	public static String getStudentClassName(int id) 
	{
		// System.out.println(q);
		ResultSet rs = DBUtil.excecuteQuery("select name from classes where id ="+ id);
		String temp = null;
		try 
		{
			rs.next();
			temp = rs.getString("Name");
			rs.close();
		 } 
		catch (Exception e)
		{
			System.out.println("error getting student class name // wizard class");
		}
		return temp;
	}
///////////////////////////////////////////////////////////////////////////////

	public static String getTeacherClassName(int ClassId) 
	{
        if(ClassId == 0)
        	return "";
		 String temp="select name from  teachers_classes where id ="+ ClassId ;
		try
		{
			 ResultSet rs = DBUtil.excecuteQuery(temp);
          	rs.next();
    		temp = rs.getString("Name");
    		rs.close();
		} catch (Exception e) 
		{
			System.out.println("error getting teacher class name // wizard class");
			e.printStackTrace();
		}
		return temp;
	}
///////////////////////////////////////////////////////////////////////////////
//	public static String getTeacherClassAsName(int ClassId) 
//	{
//        if(ClassId == 0)
//        	return "";
//		 String temp="select name from  teachers_classes where id ="+ ClassId ;
//		try
//		{
//			 ResultSet rs = DBUtil.excecuteQuery(temp);
//          	rs.next();
//    		temp = rs.getString("Name");
//    		rs.close();
//		} catch (Exception e) 
//		{
//			System.out.println("error getting teacher class name // wizard class");
//			e.printStackTrace();
//		}
//		return temp;
//	}
/////////////////////////////////////////////////////////////////////////////////
	public static String getTeacherClass_ID (String TeacherId) 
	{
		// System.out.println(q);
        if(TeacherId.equals(""))
        	return "";
		 String temp="select * from  teachers where id ="+ TeacherId ;
		try
		{
			 ResultSet rs = DBUtil.excecuteQuery(temp);
          	rs.next();
    		temp = String.valueOf(rs.getInt("Class_ID"));
    		rs.close();
		} catch (Exception e) 
		{
			System.out.println("error getting teacher class ID form his ID // wizard class");
			e.printStackTrace();
		}
		return temp;

	}
///////////////////////////////////////////////////////////////////////////////
	

	public static String getClassID(String Name) 
	{
		// System.out.println(q);
		ResultSet rs = DBUtil.excecuteQuery("select ID from classes where Name like '"+ Name+"'");
		String temp = null;
		try {
			  rs.next();
			  temp= ((Integer)rs.getInt("ID")).toString();
			  rs.close();
			// System.out.println(temp);
		    } 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.err.println("Error getting class ID // wizard class");	
		}
		return temp;

	}
///////////////////////////////////////////////////////////////////////////////
	public static String getSubjectID(String Name,String Class_level) 
	{
		// System.out.println(q);
		ResultSet rs = DBUtil.excecuteQuery("select ID from subjects where Name like '"+ Name+"'"+"and Class_level = "+Class_level);
		String temp = null;
		try {
			rs.next();
			temp= ((Integer)rs.getInt("ID")).toString();
			  rs.close();

			// System.out.println(temp);
		    } 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.err.println(" error getting subject ID // wizard class");	
		}
		return temp;

	}
///////////////////////////////////////////////////////////////////////////////
	public static String getTeacherClassID(String Name) 
	{
		// id from name
		// System.out.println(q);
		ResultSet rs = DBUtil.excecuteQuery("select ID from teachers_classes where Name like '"+ Name+"'");
		String temp = null;
		try 
		{
			rs.next();
			temp= ((Integer)rs.getInt("ID")).toString();
			  rs.close();

			// System.out.println(temp);
		 } 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.err.println(" error getting Teacher class ID // wizard class");	
		}
		return temp;
	}
///////////////////////////////////////////////////////////////////////////////
	
	public static ObservableList getStudentTypes()
	{
		if(StudentTypes==null)
		{
			StudentTypes = FXCollections.observableArrayList();
			StudentTypes.add("نظامي");
			StudentTypes.add("اتحادي");
		}
		return StudentTypes;
	}
///////////////////////////////////////////////////////////////////////////////

	public static ObservableList getSpecializations()
	{
		if(specializationslist==null)
		{
			specializationslist = FXCollections.observableArrayList();
			specializationslist.add("علمي");
			specializationslist.add("أدبي");
		}
		return specializationslist;
	}
///////////////////////////////////////////////////////////////////////////////

	public static String getTeacherID(String TeacherName)   //Id from name 
	{
		String ID= null,Query="select id from teachers where name like'"+TeacherName+"'";
		
		try
		{
		    ResultSet rs = DBUtil.excecuteQuery(Query);
		    rs.next();
			ID= String.valueOf(rs.getInt("ID"));
			  rs.close();

//			System.out.println(ID);
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage()+"  error getting the teacher ID class wizard");
		}
		return ID;
	}
///////////////////////////////////////////////////////////////////////////////

	public static String getStudentID(String StudentName)   //Id from name 
	{
		String ID= null,Query="select * from student where name like'"+StudentName+"'";
		
		try
		{
		    ResultSet rs = DBUtil.excecuteQuery(Query);
		    rs.next();
			ID= String.valueOf(rs.getInt("ID"));
			  rs.close();
//			System.out.println(ID);
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage()+"  error getting the student ID class wizard");
		}
		return ID;
	}
///////////////////////////////////////////////////////////////////////////////
	public static String getWorkerID(String WorkerName)   //Id from name 
	{
		String ID= null,Query="select * from workers where name like'"+WorkerName+"'";
		
		try
		{
		    ResultSet rs = DBUtil.excecuteQuery(Query);
		    rs.next();
			ID= String.valueOf(rs.getInt("ID"));
//			System.out.println(ID);
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage()+"  error getting the worker ID class wizard");
		}
		return ID;
	}
///////////////////////////////////////////////////////////////////////////////

	

	public static ObservableList getClassesLevels()
	{
		if(ClassesLevels==null)
		{
			ClassesLevels = FXCollections.observableArrayList();
			ClassesLevels.add("");
			ClassesLevels.add("الأول");
			ClassesLevels.add("الثاني");
			ClassesLevels.add("الثالث");
		}
		return ClassesLevels;
	}
	
///////////////////////////////////////////////////////////////////////////////

	public static String getTranspID(String Name) 
	{
		// System.out.println(q);
		ResultSet rs = DBUtil.excecuteQuery("select ID from transporation where Destination like '"+ Name+"'");
		String temp = null;
		try {
			rs.next();
			temp= ((Integer)rs.getInt("ID")).toString();
			  rs.close();
			// System.out.println(temp);
		    } 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			System.err.println(" error getting transp ID // wizard class");	
		}
		return temp;

	}
///////////////////////////////////////////////////////////////////////////////

	public static String getTransp_Name(int id) 
	{
		String q="select Destination from transporation where ID ="+ id;
		ResultSet rs = DBUtil.excecuteQuery(q);
		//System.out.println(q);
		
		String temp = null;
		try {
			 rs.last();
				if (rs.getRow() == 0)
				{
					return "";
				}
			 //rs.next();
			 temp = rs.getString("Destination");
			  rs.close();
			// System.out.println(temp);
		} 
		catch (Exception e) 
		{
			System.out.println("error getting Transp_Name // wizard class  "+e.getMessage());
			
		}
		return temp;
	}
///////////////////////////////////////////////////////////////////////////////

	public static String getSupervisorName(int classId) 
	{
		// System.out.println(q);
		ResultSet rs = DBUtil.excecuteQuery("select name from teachers where id =(select supervisor_ID from classes where id="+classId+")");
		String temp = null;
		try {
			//check if empty
			rs.last();
			if(rs.getRow()==0)
			return temp;
			rs.beforeFirst();
			rs.next();
			temp = rs.getString("Name");
			rs.close();
			// System.out.println(temp);
		} 
		catch (Exception e) 
		{
			System.out.println("error getting SupervisorName // wizard class");
			e.printStackTrace();
			
		}
		return temp;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String getStudentName(int id) 
	{
		// System.out.println(q);
		ResultSet rs = DBUtil.excecuteQuery("select * from student where id = "+id);
		String temp = null;
		try {
			//check if empty
			rs.last();
			if(rs.getRow()==0)
			return temp;
			rs.beforeFirst();
			rs.next();
			temp = rs.getString("Name");
			  rs.close();
		} 
		catch (Exception e) 
		{
			System.out.println("error getting studentName // wizard class");
			e.printStackTrace();
			
		}
		return temp;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String getWorkerName(int id) 
	{
		// System.out.println(q);
		ResultSet rs = DBUtil.excecuteQuery("select * from workers where id = "+id);
		String temp = null;
		try {
			//check if empty
			rs.last();
			if(rs.getRow()==0)
			return temp;
			rs.beforeFirst();
			rs.next();
			temp = rs.getString("Name");
			  rs.close();
		} 
		catch (Exception e) 
		{
			System.out.println("error getting WorkerName // wizard class");
			e.printStackTrace();
			
		}
		return temp;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static String getTeacherName(int id) 
	{
		// System.out.println(q);
		ResultSet rs = DBUtil.excecuteQuery("select * from teachers where id = "+id);
		String temp = null;
		try {
			//check if empty
			rs.last();
			if(rs.getRow()==0)
			return temp;
			rs.beforeFirst();
			rs.next();
			temp = rs.getString("Name");
			  rs.close();
		} 
		catch (Exception e) 
		{
			System.out.println("error getting teacherName // wizard class");
			e.printStackTrace();
			
		}
		return temp;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	public static String getClassLevel(int id) 
	{
		// System.out.println(q);
		ResultSet rs = DBUtil.excecuteQuery("select Level from classes where id ="
				+ id);
		String temp = null;
		try 
		
		{
			rs.next();
			temp = rs.getString("Level");
			if(temp.equals("1"))
				temp="الأول";
			else if (temp.equals("2"))
				temp="الثاني";
			else if (temp.equals("3"))
				temp="الثالث";
			  rs.close();
			  }
		catch (Exception e)
		{
			System.out.println("error getting class Level // wizard class");
			
		}
		return temp;

	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// database connection_testing
	public static boolean test_connection() 
	{

		try
		{
			conn = DBUtil.get_Connection();
			connected = true;
			return connected;
		} catch (Exception c) 
		{
			System.out.println(c.getMessage() + "couldn't get connection ");
			// c.printStackTrace();
			return connected;

		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public  static Student getStudentObject(int id)
	{
		String temp;
		ResultSet rs = DBUtil.excecuteQuery("select * from Student where id ="+ id);
		Student student = null;
		try 
		{
			rs.next();
		    student = new Student(rs.getInt("ID"), rs.getString("Name"));
		    student.setEmail_Address(rs.getString("Email"));
			student.setaddress(rs.getString("Address"));
			student.setB_Date(rs.getString("B_Date"));
			student.setClass_ID(rs.getInt("Class_ID"));
			student.setfather_phone_number(rs.getString("father_phone_number"));
			student.setfather_seets_fees(rs.getDouble("father_seats_fees"));
			student.setsummer_course_fees(rs.getDouble("summer_course_fees"));
			student.settransp_fees(rs.getDouble("transp_fees"));
			student.setStudying_fees(rs.getDouble("studying_fees"));
			student.setHealth_Status(rs.getString("Health_status"));
			student.setphone_number(rs.getString("phone_number"));
			student.setClassName(Wizard.getStudentClassName(student.getClass_ID()));
			student.setClassLevel(Wizard.getClassLevel(student.getClass_ID()));
			student.settransp_ID(rs.getInt("transp_ID"));
			student.setTransp_Name(Wizard.getTransp_Name(student.gettransp_ID()));
			student.setSupervisorName(Wizard.getSupervisorName(student
					.getClass_ID()));
			temp = rs.getString("specialization");
			if(temp.equals("scientific"))
				student.setspecialization("علمي");
			else if(temp.equals("Literary"))
			    student.setspecialization("أدبي");
			else
			    student.setspecialization("ليس بعد");
			temp=rs.getString("Student_type");
			  if (temp.equals("regular"))
				  temp="نظامي";
			  else
			       temp="اتحادي";
			  student.setStudent_type(temp);
			  student.setImageURL(rs.getString("ImageURL"));
			  rs.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage()+"  error getting studentObject // wizard class");

		}
		return student;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static  Teacher getTeacherObject(int id)
	{
		String temp;
		ResultSet rs = DBUtil.excecuteQuery("select * from Teachers where id ="+ id);
		Teacher teacher = null;
		try 
		{
			rs.next();
			 teacher = new Teacher(rs.getInt("ID"),
					rs.getString("Name"));
			teacher.setaddress(rs.getString("Address"));
			teacher.setClass_ID(rs.getInt("Class_ID"));
			teacher.setphone_number(rs.getString("phone_number"));
			teacher.setClassName(Wizard.getTeacherClassName(teacher
					.getClass_ID()));
			int transp_Id=rs.getInt("transp_ID");
			teacher.settransp_ID(transp_Id);
			teacher.settransp_fees(rs.getDouble("transp_fees"));
			teacher.setSalary(rs.getDouble("Salary"));
			teacher.setalternatives(rs.getDouble("alternatives"));
			if(transp_Id!=0)
			{
				teacher.setTransp_Name(Wizard.getTransp_Name(transp_Id));
			}
		    teacher.setEmail_Address(rs.getString("Email"));	
		    teacher.setImageURL(rs.getString("ImageURL"));
			  rs.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage()+"  error getting TeacherObject // wizard class");

		}
		return teacher;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static  Worker getWorkerObject(int id)
	{
		String temp;
		ResultSet rs = DBUtil.excecuteQuery("select * from workers where id ="+ id);
		Worker worker = null;
		try 
		{
			rs.next();
			worker = new Worker(rs.getInt("ID"),
					rs.getString("Name"));
			worker.setaddress(rs.getString("address"));
			worker.setphone_number(rs.getString("phone_number"));
			int transp_Id=rs.getInt("transp_ID"); // we need to use it again 
			worker.settransp_ID(transp_Id);
			worker.settransp_fees(rs.getDouble("transp_fees"));
			worker.setSalary(rs.getDouble("Salary"));
			worker.setalternatives(rs.getDouble("alternatives"));
			worker.setWorksAs(rs.getString("works_as"));
			worker.setNotes(rs.getString("notes"));
			if(transp_Id!=0)
			{
				worker.setTransp_Name(Wizard.getTransp_Name(transp_Id));
			}
		    worker.setImageURL(rs.getString("ImageURL"));
			  rs.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage()+"  error getting Worker Object // wizard class");

		}
		return worker;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static  Transporation getTransportationObject(int id)
	
	{
		String temp;
		ResultSet rs = DBUtil.excecuteQuery("select * from transporation where id ="+ id);
		Transporation Transporter = null;
	try 
	{
		rs.next();
		Transporter = new Transporation();
		Transporter.setid(rs.getInt("ID"));
		Transporter.setDestination(rs.getString("Destination"));
		Transporter.setDriver_name(rs.getString("Driver_name"));
		Transporter.setdelivery_cost(rs.getDouble("delivery_cost"));
		Transporter.setvehicle_type(rs.getString("vehicle_type"));
		  rs.close();
	} 
	catch (Exception e) 
	{
		System.out.println("error filling Transportation object in wizard class "+e.getMessage());

	}
	return Transporter ;
	}
////////////////////////////////////////////////////////////////////////////////////////////

	public static  SClasses getSClassObject(int id)
	
	{
		String temp;
		ResultSet rs = DBUtil.excecuteQuery("select * from classes where id ="+ id);
		SClasses myclass = null;
	try 
	{
		rs.next();
		myclass = new SClasses();
		myclass.setid(rs.getInt("ID"));
		myclass.setName(rs.getString("Name"));
		myclass.setCapacity(rs.getInt("Capacity"));
		myclass.setfather_seets_fees(rs
				.getDouble("father_seats_fees"));
		myclass.setLevel(rs.getString("Level"));
		myclass.setStudying_fees(rs.getDouble("Studying_fees"));
		myclass.setsummer_course_fees(rs
				.getDouble("summer_course_fees"));
		myclass.setSupervisorID(rs.getInt("supervisor_ID"));
		  rs.close();
	} 
	catch (Exception e) 
	{
		System.out.println("error filling Class object in wizard class "+e.getMessage());

	}
	return myclass ;
	}
////////////////////////////////////////////////////////////////////////////////

public static  Result getResultObject(int id)
	
	{
		String temp;
		ResultSet rs = DBUtil.excecuteQuery("select * from results where id = "+ id);
		Result myResult = null;
	try 
	{
		rs.next();
		myResult = new Result();
		  myResult.setid(rs.getInt("ID"));
		  myResult.setClass_ID(rs.getInt("Class_ID"));
		  myResult.setdate(rs.getString("Date"));
		  myResult.setSupervisor_1(rs.getInt("supervisor_1"));
		  myResult.setSupervisor_2(rs.getInt("supervisor_2"));
		  myResult.setSemester_ID(rs.getInt("semster_ID"));
		  myResult.setSemester_ID(rs.getInt("ExamPrecentage"));
		  myResult.settype(ResultTypeConverter(rs.getString("Type")));
		  myResult.setClassName(Wizard.getStudentClassName(rs.getInt("Class_ID")));
		  myResult.setLevel(Wizard.getClassLevel(rs.getInt("Class_ID")));
		  rs.close();
	} 
	catch (Exception e) 
	{
		System.out.println("error filling Result object in wizard class "+e.getMessage());

	}
	return myResult ;
	}
////////////////////////////////////////////////////////////////////////////////
	public static ObservableList<String> getStudentClasses() 
	{
			ObservableList<SClasses> Names = (ObservableList<SClasses>) JDBC
				.fill_Otabel("select * from classes", Wizard.SClass, null, null);
			ObservableList Studentclasses = FXCollections.observableArrayList();
		for (Iterator iterator = Names.iterator(); iterator.hasNext();)
			//we need just names 
			Studentclasses.add(((SClasses) iterator.next()).getName());
		
			return Studentclasses;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static ObservableList<String> getStudentClassesForCombobox() 
	{
			ObservableList<SClasses> Names = (ObservableList<SClasses>) JDBC
				.fill_Otabel("select * from classes", Wizard.SClass, null, null);
			ObservableList Studentclasses = FXCollections.observableArrayList();
			Studentclasses.add("");
		for (Iterator iterator = Names.iterator(); iterator.hasNext();)
			//we need just names 
			Studentclasses.add(((SClasses) iterator.next()).getName());
		
			return Studentclasses;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	public static ObservableList<String> getWorkersJobs() 
	{
			ObservableList<Worker> Names = (ObservableList<Worker>) JDBC
				.fill_Otabel("select * from workers", Wizard.Worker, null, null);
			ObservableList jobs = FXCollections.observableArrayList();
			Worker tempworker;
		for (Iterator iterator = Names.iterator(); iterator.hasNext();)
			//we need just jobs names 
			jobs.add(((Worker) iterator.next()).getWorksAs());
			return jobs;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

public static ObservableList<String> getVehiclesTypes() 
{
	if (VehiclesTypes==null)
	{
       VehiclesTypes= FXCollections.observableArrayList();
       VehiclesTypes.add("حافلة كبيرة");
       VehiclesTypes.add("حافلة صغيره");
       VehiclesTypes.add("هايس");
       VehiclesTypes.add("ركشة");
       VehiclesTypes.add("أخرى");

	}
     return VehiclesTypes;
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static ObservableList<String> getTeacherClasses()
	{
			ObservableList<TClasses> Names = (ObservableList<TClasses>) JDBC
				.fill_Otabel("select * from teachers_classes", Wizard.TClass, null, null);
			ObservableList<String> Teacherclasses = FXCollections.observableArrayList();
		for (Iterator iterator = Names.iterator(); iterator.hasNext();)
			//we need just names 
			Teacherclasses.add(((TClasses) iterator.next()).getName());
			return Teacherclasses;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static ObservableList getTeacherNames()
	{
			ObservableList<Teacher> Names = (ObservableList<Teacher>) JDBC
				.fill_Otabel("select * from teachers", Wizard.Teacher, null, null);
			ObservableList<String> TeacherNames = FXCollections.observableArrayList();
		for (Iterator iterator = Names.iterator(); iterator.hasNext();)
			//we need just names 
			TeacherNames.add(( (Teacher) iterator.next()).getName());
			return TeacherNames;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static ObservableList getWorkersNames()
	{
			ObservableList<Teacher> Names = (ObservableList<Teacher>) JDBC
				.fill_Otabel("select * from Workers", Wizard.Worker, null, null);
			ObservableList<String> WorkkersNames = FXCollections.observableArrayList();
		for (Iterator iterator = Names.iterator(); iterator.hasNext();)
			//we need just names 
			WorkkersNames.add(( (Worker) iterator.next()).getName());
			return WorkkersNames;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static ObservableList getWorkersNamesForCombobox()
	{
			ObservableList<Teacher> Names = (ObservableList<Teacher>) JDBC
				.fill_Otabel("select * from Workers", Wizard.Worker, null, null);
			ObservableList<String> WorkkersNames = FXCollections.observableArrayList();
		for (Iterator iterator = Names.iterator(); iterator.hasNext();)
			//we need just names 
			WorkkersNames.add(( (Worker) iterator.next()).getName());
		    WorkkersNames.add(0,"");
			return WorkkersNames;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static ObservableList getTeacherNamesForComboBox()
	{
		ObservableList<Teacher> Names = (ObservableList<Teacher>) JDBC
				.fill_Otabel("select * from teachers", Wizard.Teacher, null,
						null);
		ObservableList<String> TeacherNames = FXCollections
				.observableArrayList();
		TeacherNames.add("");
		for (Iterator iterator = Names.iterator(); iterator.hasNext();)
			// we need just names
			TeacherNames.add(((Teacher) iterator.next()).getName());
		return TeacherNames;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static ObservableList<String> getTransporations()
	{
		ObservableList<SClasses> temp = (ObservableList<SClasses>) JDBC
				.fill_Otabel("select * from transporation", Wizard.transporation, null, null);
		ObservableList<String> Names = FXCollections.observableArrayList();
		for (Iterator iterator = temp.iterator(); iterator.hasNext();)
		{
			Transporation mytransp = (Transporation) iterator.next();
			Names.add(mytransp.getDestination());
		}
			return Names;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	  public static boolean isPhoto(File file)
	{
        String temp= file.getPath();
        if(temp.endsWith(".jpg")||temp.endsWith(".png")||temp.endsWith(".GIF")||temp.endsWith(".JPEG")||temp.endsWith(".JPG"))
           return true;
        else
			return false;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
   public static String Imagechooser(ImageView UploadImage)
	{
		String temp =null;
		FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("الرجاء اختيار الصورة");
		 //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		 File file = fileChooser.showOpenDialog(null);
		 //System.out.println(file.getPath());
		if(Wizard.isPhoto(file))
		{
			System.out.println("image was choosen from "+file.getPath());
			temp=file.getPath();
			 try {
	                BufferedImage bufferedImage = ImageIO.read(file);
	                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	                UploadImage.setImage(image);
	            } 
			 catch (IOException ex)
                  {
	              	// Logger.getLogger(JavaFXPixel.class.getName()).log(Level.SEVERE,
		            // null, ex);
		                 System.out.println(ex.getMessage()+ " error loading image student tab controller");
	              }
		}
		else
			Dialogs.create().owner(null).title("رسالة خطأ").masthead("!الملف الذي تم اختياره غير صالح").message("الرجاء التأكد من الاختيار الصحيح للصورة").showError();

         return temp;
	}

//   public static String changephoto(ImageView UploadImage)
//	{
//			System.out.println("image was choosen from "+file.getPath());
//			 try {
//	                BufferedImage bufferedImage = ImageIO.read(file);
//	                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//	                UploadImage.setImage(image);
//	            } 
//			 catch (IOException ex)
//                  {
//	              	// Logger.getLogger(JavaFXPixel.class.getName()).log(Level.SEVERE,
//		            // null, ex);
//		                 System.out.println(ex.getMessage()+ " error loading image student tab controller");
//	              }
//	}
////////////////////////////////////////////////////////////////////////////////
public static Image getDefaultStudentImage ()
{
	if (DefaultStudentImage==null)
		DefaultStudentImage= new Image("/resources/DefautStudent.png");
	   return DefaultStudentImage;
}
////////////////////////////////////////////////////////////////////////////////
public static Image getDefaultWorkerImage ()
{
	if (DefaultWorkerImage==null)
		DefaultWorkerImage= new Image("/resources/Worker.png");
	   return DefaultWorkerImage;
}
////////////////////////////////////////////////////////////////////////////////
public static Image getDefaultTeacherImage ()
{
	if (DefaultTeacherImage==null)
		DefaultTeacherImage= new Image("/resources/Teacher-male-icon.png");
	   return DefaultTeacherImage;
}
////////////////////////////////////////////////////////////////////////////////

public static Image getSchoolOfficialMark ()
{
	if (SchoolOfficialMark==null)
		SchoolOfficialMark= new Image("/resources/SchoolOfficialMark.png");
	   return SchoolOfficialMark;
}
////////////////////////////////////////////////////////////////////////////////
   public static String myJarPath()
   {
	   
	   if(myJarPath==null)
	   {
			 File jarPath=new File(MainClass.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			 String propertiesPath=jarPath.getParentFile().getAbsolutePath();
			 myJarPath=propertiesPath.replace("\\" ,"/");
	   }
	   return myJarPath;

   }
////////////////////////////////////////////////////////////////////////////////

   public static LocalDate ToLocalDate(String S)
   {
	   LocalDate myDate;
	   if(S.contains("/"))
	   myDate = LocalDate.parse(S,formatter1);
	   else
        myDate = LocalDate.parse(S,formatter2);
	   return myDate;
   }
////////////////////////////////////////////////////////////////////////////////
   public static String getFileName (String s)
   {
	   File file = new File(s);
	   return file.getName();
	   
   }
////////////////////////////////////////////////////////////////////////////////
 public static String chooseDialog(List list,String title,String message,String advise)
	{
		 return ( Dialogs.create()
        .owner(null)
        .title(title)
        .masthead(message)
        .message(advise)
        .showChoices(list)).get().toString();	
	}
////////////////////////////////////////////////////////////////////////////////
         public static String LevelConverter(String Level)
         {//converts the digit into string and vice versa 
        	 String result=null;
        	 if(Level==null )
        		 return "";
        	 else if(Level.length()==0)
        		 return "";
        	 else
        	 {
        	 if(Level.length()==1)
        	 {
        		 if(Level.equals("1"))
        			 result = "الأول";
        		 else if(Level.equals("2"))
        			 result = "الثاني";
        		 if (Level.equals("3"))
        			 result = "الثالث";
        		 
        	 }
        	 else
        	 {
        		 if(Level.equals("الأول"))
        			 result = "1";
        		 else if(Level.equals("الثاني"))
        			 result = "2";
        		 else
        			 result = "3";
        	 }
        	 }
        	 return result;
         }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public static String  TotalStudentsPerSClass(String Class_ID)
     {
    	 String temp = "SELECT count(*) AS total from student where Class_ID = "+Class_ID;
    	 ResultSet rs = DBUtil.excecuteQuery(temp);
    	 try 
    	 {
			rs.next();
			temp= String.valueOf(rs.getInt("total"));
			  rs.close();
			
		} catch (SQLException e)
    	 {
               System.out.println(e.getMessage()+ "error getting total number of students in class wizard");
			e.printStackTrace();
		}
    	 return temp;
    	 
    	 
     }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

     public static String  TotalStudentsPerTransporter(String transp_ID)
     {
    	 String temp = "SELECT count(*) AS total from student where transp_ID = "+transp_ID;
    	 ResultSet rs = DBUtil.excecuteQuery(temp);
    	 try 
    	 {
			rs.next();
			temp= String.valueOf(rs.getInt("total"));
			  rs.close();
		} 
    	 catch (SQLException e)
    	 {
               System.out.println(e.getMessage()+ "error getting total number of students per transporter in class wizard");
			e.printStackTrace();
		}
    	 return temp;	 
     }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public static  TableColumn<ObservableList<StringProperty>, String> createEditableColumn(final int columnIndex, String columnTitle,String WhatToDo)
     {
    	 //creat columns dynamically
    	    TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();
    	    String title;
    	    if (columnTitle == null || columnTitle.trim().length() == 0)
            {
    	      title = "Column " + (columnIndex + 1);
    	    }
    	    else 
    	    {
    	      title = columnTitle;
    	    }
    	    column.setText(title);
    	    column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<StringProperty>, String>, ObservableValue<String>>() 
    	    		{
    	              @Override
    	               public ObservableValue<String> call(
    	              CellDataFeatures<ObservableList<StringProperty>, String> cellDataFeatures)
    	              {
    	                  ObservableList<StringProperty> values = cellDataFeatures.getValue();
    	                  if (columnIndex >= values.size()) 
    	                   {
    	                      return new SimpleStringProperty("");
    	                   }
    	                  else 
    	                  {
    	                    return cellDataFeatures.getValue().get(columnIndex);
    	                  }
    	              }
    	               });
  	           column = set_editable(column,WhatToDo);
  	           column.setMinWidth(80);
//             column.getColumns().addAll(Exam,Classwork);
    	             return column;
    }

     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public static  TableColumn<ObservableList<StringProperty>, String> createColumn(final int columnIndex, String columnTitle)
     {
    	 //creat columns dynamically
    	    TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();
    	    String title;
    	    if (columnTitle == null || columnTitle.trim().length() == 0)
            {
    	      title = "Column " + (columnIndex + 1);
    	    }
    	    else 
    	    {
    	      title = columnTitle;
    	    }
    	    column.setText(title);
    	    column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<StringProperty>, String>, ObservableValue<String>>() 
    	    		{
    	              @Override
    	               public ObservableValue<String> call(
    	              CellDataFeatures<ObservableList<StringProperty>, String> cellDataFeatures)
    	              {
    	                  ObservableList<StringProperty> values = cellDataFeatures.getValue();
    	                  if (columnIndex >= values.size()) 
    	                   {
    	                      return new SimpleStringProperty("");
    	                   }
    	                  else 
    	                  {
    	                    return cellDataFeatures.getValue().get(columnIndex);
    	                  }
    	              }
    	               });
  	           column.setMinWidth(80);
//             column.getColumns().addAll(Exam,Classwork);
    	             return column;
    }
////////
     public static TableView  dynamic_table_filling (TableView table,ObservableList<String> headerValues,String WhatToDo)
     {
    	 for (int column = 0; column < headerValues.size(); column++)
    	 {
//    		 TableColumn temp = new TableColumn<>(headerValues.get(column)) ;
//    		 temp = set_editable(temp);
//    		 table.getColumns().add(temp);
           

               table.getColumns().add(createEditableColumn(column, headerValues.get(column),WhatToDo));
             
         }
    	 
    	 
    	 return table;
     }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static TableColumn set_editable(TableColumn column,String WhatToDo)
    {
    	column.setCellFactory(TextFieldTableCell.forTableColumn());
    	column.setOnEditCommit(
    		    new EventHandler<CellEditEvent>()
    		    {
    		        @Override
    		        public void handle(CellEditEvent t) 
    		        {
//    		            ((Result) t.getTableView().getItems().get(
//    		                t.getTablePosition().getRow())
//    		                ).setFirstName(t.getNewValue());
    		        	Globaltable_point= new point(t.getTablePosition().getRow(),t.getTablePosition().getColumn());
    		        	GlobalCellValue=t.getNewValue().toString();
    		        	System.out.println(t.getTablePosition().getRow());
    		        	System.out.println(t.getTablePosition().getColumn());
    		        	System.out.println(t.getNewValue().toString());
    		        	
    		        	//check different listeners modes 
    		        	{
    		        		if (WhatToDo.equals("Fill the Result the table"))
    		        			;
    		        			
    		        	}

    	    		              
    		        }
    		    }
    		);
    	return column;
    	
    }
public static String getGlobalCellValue() {
		return GlobalCellValue;
	}

	public static void setGlobalCellValue(String globalCellValue) {
		GlobalCellValue = globalCellValue;
	}

public static point getGlobaltable_point() {
		return Globaltable_point;
	}

	public static void setGlobaltable_point(point globaltable_point) {
		Globaltable_point = globaltable_point;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static ObservableList<String> getSubjectsperLevel(String s)
    {
    	ObservableList<String> Subjects ;
    	ObservableList<String> SubjectsNames= FXCollections.observableArrayList() ;
       String query= "select * from subjects where Class_level="+s;
       Subjects =(ObservableList<String>) JDBC.fill_Otabel(query, Wizard.Subject, null, null);
       for (Iterator iterator = Subjects.iterator(); iterator.hasNext();)
			//we need just names 
    	   SubjectsNames.add(( (Subject) iterator.next()).getName());
    	return SubjectsNames;
    	
    }
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
    public static ObservableList<String> getStudentsperClass(String ID)
    {
    	ObservableList<String> Students ;
    	ObservableList<String> StudentsNames= FXCollections.observableArrayList() ;
       String query= "select * from Student where Class_ID ="+ID;
       Students =(ObservableList<String>) JDBC.fill_Otabel(query, Wizard.Student, null, null);
       for (Iterator iterator = Students.iterator(); iterator.hasNext();)
			//we need just names 
    	   StudentsNames.add(( (Student) iterator.next()).getName());
    	return StudentsNames;
    	
    }
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static ObservableList<String> getStudentsperClassForCombobox(String ID)
    {
    	ObservableList<String> Students ;
    	ObservableList<String> StudentsNames= FXCollections.observableArrayList() ;
       String query= "select * from Student where Class_ID ="+ID;
       Students =(ObservableList<String>) JDBC.fill_Otabel(query, Wizard.Student, null, null);
       for (Iterator iterator = Students.iterator(); iterator.hasNext();)
			//we need just names 
    	   StudentsNames.add(( (Student) iterator.next()).getName());
       StudentsNames.add(0,"");
    	return StudentsNames;
    	
    }
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static ObservableList<String> getTeachersperClass(String ID)
    {
    	ObservableList<String> Teachers ;
    	ObservableList<String> TeachersNames= FXCollections.observableArrayList() ;
       String query= "select * from teachers where Class_ID ="+ID;
       Teachers =(ObservableList<String>) JDBC.fill_Otabel(query, Wizard.Teacher, null, null);
       for (Iterator iterator = Teachers.iterator(); iterator.hasNext();)
			//we need just names 
    	   TeachersNames.add(( (Teacher) iterator.next()).getName());
    	return TeachersNames;
    	
    }
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static String ResultTypeConverter (String s)
    {
    	if(s==null)
    		return "";
    			
    	String temp = null;
    	if(s.equals("First"))
    		temp = "الفصل الدراسي الأول";
    	else if(s.equals("Second"))
    		temp = "الفصل الدراسي  الثاني";
    	else if (s.equals("monthly"))
    		temp = "نتيجة شهرية";    		
    	else if(s.equals("الفصل الدراسي الأول"))
    		temp = "First";
    	else if (s.equals("الفصل الدراسي  الثاني"))
    		temp = "Second";
    	else if (s.equals("نتيجة شهرية"))
    		temp ="monthly";
    	return temp;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static String GeneralPaymentTypeConverter (String s)
    {
    	if(s==null)
    		return "";
    			
    	String temp = null;
    	if(s.equals("income"))
    		temp = "إيرادات";
    	else if(s.equals("outcome"))
    		temp = "تكاليف";
    	else if (s.equals("إيرادات"))
    		temp = "income";    		
    	else if(s.equals("تكاليف"))
    		temp = "outcome";
    	return temp;
    }
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static String GeneralPaymentPartyConverter (String s)
    {
    	if(s==null)
    		return "";
    			
    	String temp = null;
    	if(s.equals("Electricity"))
    		temp = "كهرباء";
    	else if(s.equals("Water"))
    		temp = "ماء";
    	else if (s.equals("Rental"))
    		temp = "إيجار";   
    	else if(s.equals("Loan"))
    		temp = "قرض";
    	else if (s.equals("Printing"))
    		temp = "طباعة";
    	else if (s.equals("Other"))
    		temp ="أخرى";
    	else if(s.equals("كهرباء"))
    		temp = "Electricity";
    	else if(s.equals("ماء"))
    		temp = "Water";
    	else if (s.equals("إيجار"))
    		temp = "Rental";   
    	else if(s.equals("قرض"))
    		temp = "Loan";
    	else if (s.equals("طباعة"))
    		temp ="Printing";
    	else if (s.equals("أخرى"))
    		temp ="Other";
    	return temp;
    }
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        public static ObservableList<StringProperty> getExamsOrderdedMarks(String ResultId,String StudentID,String Class_ID)
        {
        	ObservableList<StringProperty> OrderdedMarks = FXCollections.observableArrayList();
        	List<String> elements;
        	List<String> types;
        	

        	String classlevel =LevelConverter(getClassLevel(java.lang.Integer.parseInt(Class_ID)));
        	ObservableList<String> Subjects = getSubjectsperLevel(classlevel);
        	for (Iterator iterator = Subjects.iterator(); iterator.hasNext();) 
        	{
        		
        		String subjectname = (String) iterator.next();
	        	String Query = "Select * from result_link where result_ID = ? and student_ID = ? and subject_ID = ?";
	        	elements = new ArrayList<String>();
	        	types = new ArrayList<String>();
	        	elements.add(ResultId);
	        	types.add(Integer);
	        	elements.add(StudentID);
	        	types.add(Integer);
	        	elements.add(Wizard.getSubjectID(subjectname,classlevel));
	        	types.add(Integer);
	        	ResultSet rs = DBUtil.excecuteQuery(Query,elements,types);
	    		String temp = null;
	    		try 
	    		{
	    			//if(!rs.isLast())
	    			//check for empty result set
	    			rs.last();
	    			if(rs.getRow()==0)
	    			{
	    			    temp = "0"; 
	    			}
	    			    else 
	    			    {
	    			    	rs.beforeFirst();
	    		         	rs.next();
	    			       temp= ((Integer)rs.getInt("exam")).toString();
	    					 rs.close();
	    			    }
	    			// System.out.println(temp);
	    		 } 
	    		catch (Exception e)
	    		{
	    			System.out.println(e.getMessage());
	    			System.err.println(" error getting exam mark   // wizard class");	
	    		}
	    		
	    		//Total += java.lang.Integer.parseInt(temp);
	    		
	    		OrderdedMarks.add(new SimpleStringProperty(temp));
	    	
			}
        	return OrderdedMarks;
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public static ObservableList<StringProperty> getClassWorkOrderdedMarks(String ResultId,String StudentID,String Class_ID)
        {
        	ObservableList<StringProperty> OrderdedMarks = FXCollections.observableArrayList();
        	List<String> elements;
        	List<String> types;

        	String classlevel =LevelConverter(getClassLevel(java.lang.Integer.parseInt(Class_ID)));
        	ObservableList<String> Subjects = getSubjectsperLevel(classlevel);
        	for (Iterator iterator = Subjects.iterator(); iterator.hasNext();) 
        	{
        		String subjectname = (String) iterator.next();
	        	String Query = "Select * from result_link where result_ID = ? and student_ID = ? and subject_ID = ?";
	        	elements = new ArrayList<String>();
	        	types = new ArrayList<String>();
	        	elements.add(ResultId);
	        	types.add(Integer);
	        	elements.add(StudentID);
	        	types.add(Integer);
	        	elements.add(Wizard.getSubjectID(subjectname,classlevel));
	        	types.add(Integer);
	        	ResultSet rs = DBUtil.excecuteQuery(Query,elements,types);
	    		String temp = null;
	    		try 
	    		{
	    			//if(!rs.isLast())
	    			//check for empty result set
	    			rs.last();
	    			if(rs.getRow()==0)
//		    	       rs.last();
	    			    temp = "30"; 
	    			    else 
	    			    {
	    			    	rs.beforeFirst();
	    		         	rs.next();
	    			       temp= ((Integer)rs.getInt("class_work")).toString();
	    				   rs.close();
	    			    }
	    			// System.out.println(temp);
	    		 } 
	    		catch (Exception e)
	    		{
	    			System.out.println(e.getMessage());
	    			System.err.println(" error getting exam mark   // wizard class");	
	    		}
	    		OrderdedMarks.add(new SimpleStringProperty(temp));
			}
        	return OrderdedMarks;
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        public static TableView ClearTable(TableView Table)
        {
    	     //clear all the columns from the table

        	 while (Table.getColumns().size()!=0)
     	     {
      	    	Table.getColumns().remove(0);

     	     }
//     	     clear  all rows
//     	    Table.setItems(FXCollections.observableArrayList());
        	return Table;
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public static String StudentPaymentTypeConverter(String s)
		{
	    	String temp = null;

			if(s==null)
	    		return temp;
			
	    	if(s.equals("fees"))
	    		temp = "رسوم دراسية";
	    	else if(s.equals("رسوم دراسية"))
	    		temp = "fees";
	    	else if (s.equals("summer_course_fees"))
	    		temp = "رسوم الكورس الصيفي";   
	    	else if(s.equals("رسوم الكورس الصيفي"))
	    		temp = "summer_course_fees";
	    	else if (s.equals("transp_fees"))
	    		temp = "رسوم ترحيل";
	    	else if (s.equals("رسوم ترحيل"))
	    		temp ="transp_fees";
	    	else if(s.equals("father_seats_fees"))
	    		temp = "رسوم مجلس الاباء";
	    	else if(s.equals("رسوم مجلس الاباء"))
	    		temp = "father_seats_fees";
	    	else if (s.equals("Other"))
	    		temp = "أخرى";   
	    	else if(s.equals("أخرى"))
	    		temp = "Other";
	    	return temp;		}
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public static String TeacherPaymentTypeConverter(String s)
		{
	    	String temp = null;

			if(s==null)
	    		return temp;
	    	if(s.equals("salary"))
	    		temp = "دفعية راتب";
	    	else if(s.equals("دفعية راتب"))
	    		temp = "salary";
	    	else if (s.equals("alternatives"))
	    		temp = "علاوات";   
	    	else if(s.equals("علاوات"))
	    		temp = "alternatives";
	    	else if (s.equals("transp_fees"))
	    		temp = "رسوم ترحيل";
	    	else if (s.equals("رسوم ترحيل"))
	    		temp ="transp_fees";
	    	else if(s.equals("Loan"))
	    		temp = "قرض";
	    	else if(s.equals("قرض"))
	    		temp = "Loan";
	    	else if (s.equals("Other"))
	    		temp = "أخرى";   
	    	else if(s.equals("أخرى"))
	    		temp = "Other";
	    	return temp;		
	    	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		public static String WorkerPaymentTypeConverter(String s)
		{
	    	String temp = null;

			if(s==null)
	    		return temp;
	    	if(s.equals("salary"))
	    		temp = "دفعية راتب";
	    	else if(s.equals("دفعية راتب"))
	    		temp = "salary";
	    	else if (s.equals("alternatives"))
	    		temp = "علاوات";   
	    	else if(s.equals("علاوات"))
	    		temp = "alternatives";
	    	else if (s.equals("transp_fees"))
	    		temp = "رسوم ترحيل";
	    	else if (s.equals("رسوم ترحيل"))
	    		temp ="transp_fees";
	    	else if (s.equals("Other"))
	    		temp = "أخرى";   
	    	else if(s.equals("أخرى"))
	    		temp = "Other";
	    	return temp;		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       public static  boolean  SystemLogin (String userName,String passWord)
       {
    	   
		 final TextField username = new TextField();
		 final PasswordField password = new PasswordField();
		
		
		 Dialog dlg = new Dialog(null, "تسجيل الدخول");
		 dlg.setMasthead(" ");
//		 dlg.setMasthead("تسجيل الدخول للنظام                                                 ");
             dlg.setResizable(false);
           
		     dlg.setGraphic(new ImageView( new Image("/resources/Login.png")));
		    
		      

		     GridPane grid = new GridPane();
		    grid.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		    grid.setHgap(10);
		    grid.setVgap(10);
		    grid.setPadding(new Insets(0, 10, 0, 10));
//		    grid.setNodeOrientation(Orientation);
          
		    Label failed = new Label("لم يتم تسحيل الدخول الرجاء التحقق من صحة البيانات المدخلة");
		    failed.setVisible(false);
		    
		    username.setPromptText("اسم المستخدم");
		    password.setPromptText("كلمة المرور");
		    grid.add(new Label("اسم المستخدم :"), 0, 0);
		    
		    
		    grid.add(username, 1, 0);
		    
		    
		    grid.add(new Label("كلمة المرور:"), 0, 1);
		    grid.add(password,1, 1);
		    
		    
		    Button login = new Button("تسجيل الدخول") ;
		    
		    Button cancel = new Button("إلغاء") ;
		    grid.add(failed,1,2);
		    grid.add(login,0,3);
		    grid.add(cancel,1,3);
			
		
			 EventHandler<Event> loginAction = new EventHandler<Event>() 
						{
					        @Override
				             	public void handle(Event arg0) 
				                      	
					        {
					
					        	String q= "select * from users where name = ? and password = ? ";
					        	List<String> elements = FXCollections.observableArrayList();
					        	List<String> types = FXCollections.observableArrayList();
					        	
					            elements.add(username.getText().trim());
					            types.add(Wizard.String);
					            elements.add(password.getText().trim());
					            types.add(Wizard.String);
					            ResultSet rs =  DBUtil.excecuteQuery(q, elements, types);
					            try
					            {
									 rs.last();
									  if(rs.getRow()==0)
									  {
							            	System.out.println("Login failed");
							            	failed.setVisible(true);
									  }
									  else
									  {
										  System.out.println("Login success");
										  trueLogin = true;
										 // trueLogin = true;
										  dlg.hide();
										 // return;
									  }
								} catch (SQLException e) 
					            {
									// 
									e.printStackTrace();
								}
					          
					       	}
					        
				           	};
				           	
		    EventHandler<Event> logincancel = new EventHandler<Event>() 
									{
								        @Override
							             	public void handle(Event arg0) 
							                      	
								        {
								        	
								        	System.exit(1);
								       	}
								        
							           	};
	                    
				           	
		    
		    login.setOnMouseClicked(loginAction);
		    login.setOnKeyPressed(loginAction);
		    cancel.setOnMouseClicked(logincancel);
		    cancel.setOnKeyPressed(logincancel);

//		    login.setOnKeyPressed(loginAction);
//		    cancel.setOnKeyPressed(loginAction);

//		    ButtonBar.setType(actionLogin,ButtonType.OK_DONE);
//		    actionLogin.disabledProperty().set(true);

//		     Do some validation (using the Java 8 lambda syntax).
//		    username.textProperty().addListener((observable, oldValue, newValue) -> {
//		        actionLogin.disabledProperty().set(newValue.trim().isEmpty());
//		    });
		  
		   
		    dlg.setContent(grid);
		   // dlg.getActions().addAll(actionLogin, Dialog.ACTION_CANCEL);

		    // Request focus on the username field by default.
		    Platform.runLater(() -> username.requestFocus());

		    dlg.show();
		    
		    return true;
       }     
       
      
       
       public static EventHandler<MouseEvent> dragStage =  new EventHandler<MouseEvent>()
		  {
    	     class Delta { double x, y; } 
		      final Delta dragDelta = new Delta();
 	         @Override public void handle(MouseEvent mouseEvent) 
 	         {
 
 		        MainClass.primaryStage.setX(mouseEvent.getScreenX() + dragDelta.x);
 		       MainClass.primaryStage.setY(mouseEvent.getScreenY() + dragDelta.y);
 		  }
 		};
 		
 		public static EventHandler<MouseEvent> Exit =  new EventHandler<MouseEvent>()
 				  {
 		    	    
 		 	         @Override public void handle(MouseEvent mouseEvent) 
 		 	         {
 		 		         System.exit(1);
 		 		  }
 		 		};
 		 		
 		public static EventHandler<MouseEvent> minimize =  new EventHandler<MouseEvent>()
 		 				  {
 		 		    	    
 		 		 	         @Override public void handle(MouseEvent mouseEvent) 
 		 		 	         {
  		 				        MainClass.primaryStage.setIconified(true);
 		 		 	         }
 		 		 		};
 		 		 	
 		 		 		

      public static EventHandler<MouseEvent> close_the_program = new EventHandler<MouseEvent>()
	 		
 		 		 				{
 		 					
 		 					        @Override
 		 					         public void handle(MouseEvent arg0) 
 		 					        {	
 		 					        	org.controlsfx.control.action.Action response = Dialogs.create()
 		 					        	        .owner(null)
 		 					        	        .title("رسالة تأكيد")
 		 					        	        .masthead("")
 		 					        	        .message("هل تريد تأكيد الخروج ؟                      ")
 		 					        	        .actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
 		 					        	        .showConfirm();
 		 		
 		 					        	if (response == Dialog.ACTION_YES)
 		 					        	{
 		 					        		
 		 					               System.exit(1);
 		 					            }
 		 					 
 		 					  
 		 					        	}
 		 				        };
 		 				        
         public static EventHandler<MouseEvent> openImageViewer = new EventHandler<MouseEvent>()
 		 					 		
 		 		 		 				{
 		 		 					
 		 		 					        @Override
 		 		 					         public void handle(MouseEvent arg0) 
 		 		 					        {	
 		 		 					        	String fileName = "C:\\Users\\Alghali\\Desktop\\slide-82.png";
 		 		 					        	
 		 		 					        	if(global_Image_Path == null)
 		 		 					        	{
 		 		 					        		Dialogs.create()
 		 											.owner(null)
 		 											.title("إشعار")
 		 											.message(
 		 													"لم يتم تحديد الصورة الرجاء تحديد الصورة")
 		 											.showWarning();
 		 		 					        		return;
 		 		 					        	}
 		 		 					        	else if (global_Image_Path.length()==0)
 		 		 					        	{
 		 		 					        		Dialogs.create()
 		 											.owner(null)
 		 											.title("إشعار")
 		 											.message(
 		 													"لم يتم تحديد الصورة الرجاء تحديد الصورة")
 		 											.showWarning();
 		 		 					        		return;

 		 		 					        	}
 		 		 					 	      String [] commands = {"cmd.exe" , "/c", "start" , "\"DummyTitle\"", "\"" + global_Image_Path + "\""
 		 		 					 	        };
 		 		 					 	        Process p;
 		 		 					 			try
 		 		 					 			{
 		 		 					 				p = Runtime.getRuntime().exec(commands);
 		 		 					 				p.waitFor();
 		 		 					 			} catch (Exception e) 
 		 		 					 			{
 		 		 					 				e.printStackTrace();
 		 		 					 				System.out.println("couln't open the image in class wizard");
 		 		 					 			}
 		 		 					 	        }
 		 		 				        };
 		 		 				        
           public static void  CreateStudentPaymentReport(StudentPayment studentPayment) 
 		  {

               String Name = studentPayment.getStudentName(),
               ID = studentPayment.getStudentID().toString(),
               PaymentType = studentPayment.gettype(),
               PaymentDate = studentPayment.getdate(),
               StudentClass = studentPayment.getStudentClassName(),
               StudentLevel = Wizard.getClassLevel(java.lang.Integer.parseInt(Wizard.getClassID(studentPayment.getStudentClassName()))),
               PaymentValue = studentPayment.getValue().toString();
               ;
               
        	   JasperReportBuilder report = DynamicReports.report();
        	   
        	   
        	   StyleBuilder boldStyle = DynamicReports.stl.style().bold(); 

        	   StyleBuilder boldRightCentered =  DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT).setFontSize(12).setRightPadding(20).setBorder(DynamicReports.stl.pen1Point()).setTopPadding(5).setBottomPadding(5);
        	   StyleBuilder boldRightCenteredStyle = DynamicReports.stl.style(boldRightCentered);
        	   StyleBuilder boldRightCenteredWithShadow = DynamicReports.stl.style(boldRightCentered).setBackgroundColor(Color.lightGray);
        	  
        	   StyleBuilder boldWithoutCenter =  DynamicReports.stl.style(boldStyle).setFontSize(12).setBorder(DynamicReports.stl.pen1Point()).setTopPadding(5).setBottomPadding(5);

        	   
        	   StyleBuilder boldCenteredStyle = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER).setFontSize(16).setBorder(DynamicReports.stl.pen1Point());
        	   StyleBuilder boldCenteredStyleWithShadow = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER).setBackgroundColor(Color.lightGray).setFontSize(14).setBorder(DynamicReports.stl.pen1Point()).setPadding(3);
        	   

        	   report.title(DynamicReports.cmp.text(SchoolName).setStyle(boldCenteredStyle).setHeight(40));
        	   report.addTitle(
        			   
        			   DynamicReports.cmp.verticalList().add
        			   (
        			   DynamicReports.cmp.text(PaymentHeader).setStyle(boldCenteredStyleWithShadow).setFixedHeight(PaymentFieldHighet)
        			  ,DynamicReports.cmp.text("اسم الطالب : " + Name).setStyle(boldRightCenteredStyle).setFixedHeight(PaymentFieldHighet)
        			  ,DynamicReports.cmp.text("الرقم التسلسلي : " + ID).setStyle(boldRightCenteredWithShadow).setFixedHeight(PaymentFieldHighet)
        			  ,DynamicReports.cmp.text("الصف : " + StudentLevel).setStyle(boldRightCenteredStyle).setFixedHeight(PaymentFieldHighet)
        			  ,DynamicReports.cmp.text("الفصل  : " + StudentClass).setStyle(boldRightCenteredWithShadow).setFixedHeight(PaymentFieldHighet)
        			  ,DynamicReports.cmp.text("نوع الدفعية : " + PaymentType).setStyle(boldRightCenteredStyle).setFixedHeight(PaymentFieldHighet)
        			  ,DynamicReports.cmp.text("تاريخ الدفعية : " + PaymentDate).setStyle(boldRightCenteredWithShadow).setFixedHeight(PaymentFieldHighet)
        			  ,DynamicReports.cmp.text("قيمة الدفعية : " + PaymentValue + " جنيه سوداني").setStyle(boldRightCenteredStyle).setFixedHeight(PaymentFieldHighet)
        			  ,DynamicReports.cmp.text("التوقيع :").setStyle(boldWithoutCenter.setLeftPadding(100)).setFixedHeight(PaymentFieldHighet)
        			  ,DynamicReports.cmp.image(myJarPath()+"/resources/SE_.png").setStyle(boldWithoutCenter.setLeftPadding(100)).setFixedHeight(80)
        		       ).setStyle(DynamicReports.stl.style(boldStyle).setVerticalAlignment(VerticalAlignment.MIDDLE))
        			  ) ;

        	  // report.toPdf(new FileOutputStream(new File(Wizard.myJarPath()+"/Reports/outputiii.pdf")));
               report.setReportName(Wizard.SchoolName);

        	   try 
 		       {
 		    	   report.show(false);
				
			    } catch (Exception e) 
 		       {
			    	e.printStackTrace();
			   }
        	  // report.print();
 		  }
      
           //CreateTeacherPaymentReport
           //CreateWorkerPaymentReport
           public static void  CreateTeacherPaymentReport(TeacherPayment teacherPayment) 
  		  {

                String Name = teacherPayment.getTeacherName(),
                ID = teacherPayment.getTeacherID().toString(),
                PaymentType = teacherPayment.gettype(),
                PaymentDate =  teacherPayment.getdate(),
                TeacherClass =  getTeacherClassName(java.lang.Integer.parseInt(getTeacherClass_ID(teacherPayment.getTeacherID().toString()))),
                PaymentValue = teacherPayment.getValue().toString();
                ;
                
         	   JasperReportBuilder report = DynamicReports.report();
         	   
         	   
         	   StyleBuilder boldStyle = DynamicReports.stl.style().bold(); 

         	   StyleBuilder boldRightCentered =  DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT).setFontSize(12).setRightPadding(20).setBorder(DynamicReports.stl.pen1Point()).setTopPadding(5).setBottomPadding(5);
         	   StyleBuilder boldRightCenteredStyle = DynamicReports.stl.style(boldRightCentered);
         	   StyleBuilder boldRightCenteredWithShadow = DynamicReports.stl.style(boldRightCentered).setBackgroundColor(Color.lightGray);
         	  
         	   StyleBuilder boldWithoutCenter =  DynamicReports.stl.style(boldStyle).setFontSize(12).setBorder(DynamicReports.stl.pen1Point()).setTopPadding(5).setBottomPadding(5);

         	   
         	   StyleBuilder boldCenteredStyle = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER).setFontSize(16).setBorder(DynamicReports.stl.pen1Point());
         	   StyleBuilder boldCenteredStyleWithShadow = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER).setBackgroundColor(Color.lightGray).setFontSize(14).setBorder(DynamicReports.stl.pen1Point()).setPadding(3);
         	   

         	   report.title(DynamicReports.cmp.text(SchoolName).setStyle(boldCenteredStyle).setHeight(40));
         	   report.addTitle(
         			   
         			   DynamicReports.cmp.verticalList().add
         			   (
         			   DynamicReports.cmp.text(PaymentHeader).setStyle(boldCenteredStyleWithShadow).setFixedHeight(PaymentFieldHighet)
         			  ,DynamicReports.cmp.text("اسم المعلم : " + Name).setStyle(boldRightCenteredStyle).setFixedHeight(PaymentFieldHighet)
         			  ,DynamicReports.cmp.text("الرقم التسلسلي : " + ID).setStyle(boldRightCenteredWithShadow).setFixedHeight(PaymentFieldHighet)
         			  ,DynamicReports.cmp.text("الشعبة  : " + TeacherClass).setStyle(boldRightCenteredStyle).setFixedHeight(PaymentFieldHighet)
         			  ,DynamicReports.cmp.text("نوع الدفعية : " + PaymentType).setStyle(boldRightCenteredWithShadow).setFixedHeight(PaymentFieldHighet)
         			  ,DynamicReports.cmp.text("تاريخ الدفعية : " + PaymentDate).setStyle(boldRightCenteredStyle).setFixedHeight(PaymentFieldHighet)
         			  ,DynamicReports.cmp.text("قيمة الدفعية : " + PaymentValue + " جنيه سوداني").setStyle(boldRightCenteredWithShadow).setFixedHeight(PaymentFieldHighet)
         			  ,DynamicReports.cmp.text("التوقيع :").setStyle(boldWithoutCenter.setLeftPadding(100)).setFixedHeight(PaymentFieldHighet)
         			  ,DynamicReports.cmp.image(myJarPath()+"/resources/SE_.png").setStyle(boldWithoutCenter.setLeftPadding(100)).setFixedHeight(80)
         		       ).setStyle(DynamicReports.stl.style(boldStyle).setVerticalAlignment(VerticalAlignment.MIDDLE))
         			  ) ;

         	  // report.toPdf(new FileOutputStream(new File(Wizard.myJarPath()+"/Reports/outputiii.pdf")));
               report.setReportName(Wizard.SchoolName);

         	   try 
  		       {
  		    	   report.show(false);
 				
 			    } catch (Exception e) 
  		       {
 			    	e.printStackTrace();
 			   }
         	  // report.print();
  		  }
       
           
           public static void  CreateWorkerPaymentReport(WorkerPayment workerPayment) 
   		  {

                 String Name = workerPayment.getWorkerName(),
                 ID = workerPayment.getWorkerID().toString(),
                 PaymentType = workerPayment.gettype(),
                 PaymentDate = workerPayment.getdate(),
                 PaymentValue = workerPayment.getValue().toString();
                 ;
                 
          	   JasperReportBuilder report = DynamicReports.report();
          	   
          	   
          	   StyleBuilder boldStyle = DynamicReports.stl.style().bold(); 

          	   StyleBuilder boldRightCentered =  DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT).setFontSize(12).setRightPadding(20).setBorder(DynamicReports.stl.pen1Point()).setTopPadding(5).setBottomPadding(5);
          	   StyleBuilder boldRightCenteredStyle = DynamicReports.stl.style(boldRightCentered);
          	   StyleBuilder boldRightCenteredWithShadow = DynamicReports.stl.style(boldRightCentered).setBackgroundColor(Color.lightGray);
          	  
          	   StyleBuilder boldWithoutCenter =  DynamicReports.stl.style(boldStyle).setFontSize(12).setBorder(DynamicReports.stl.pen1Point()).setTopPadding(5).setBottomPadding(5);

          	   
          	   StyleBuilder boldCenteredStyle = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER).setFontSize(16).setBorder(DynamicReports.stl.pen1Point());
          	   StyleBuilder boldCenteredStyleWithShadow = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER).setBackgroundColor(Color.lightGray).setFontSize(14).setBorder(DynamicReports.stl.pen1Point()).setPadding(3);
          	   

          	   report.title(DynamicReports.cmp.text(SchoolName).setStyle(boldCenteredStyle).setHeight(40));
          	   report.addTitle(
          			   
          			   DynamicReports.cmp.verticalList().add
          			   (
          			   DynamicReports.cmp.text(PaymentHeader).setStyle(boldCenteredStyleWithShadow).setFixedHeight(PaymentFieldHighet)
          			  ,DynamicReports.cmp.text("اسم العامل : " + Name).setStyle(boldRightCenteredStyle).setFixedHeight(PaymentFieldHighet)
          			  ,DynamicReports.cmp.text("الرقم التسلسلي : " + ID).setStyle(boldRightCenteredWithShadow).setFixedHeight(PaymentFieldHighet)
          			  ,DynamicReports.cmp.text("نوع الدفعية : " + PaymentType).setStyle(boldRightCenteredStyle).setFixedHeight(PaymentFieldHighet)
          			  ,DynamicReports.cmp.text("تاريخ الدفعية : " + PaymentDate).setStyle(boldRightCenteredWithShadow).setFixedHeight(PaymentFieldHighet)
          			  ,DynamicReports.cmp.text("قيمة الدفعية : " + PaymentValue + " جنيه سوداني").setStyle(boldRightCenteredStyle).setFixedHeight(PaymentFieldHighet)
          			  ,DynamicReports.cmp.text("التوقيع :").setStyle(boldWithoutCenter.setLeftPadding(100)).setFixedHeight(PaymentFieldHighet)
          			  ,DynamicReports.cmp.image(myJarPath()+"/resources/SE_.png").setStyle(boldWithoutCenter.setLeftPadding(100)).setFixedHeight(80)
          		       ).setStyle(DynamicReports.stl.style(boldStyle).setVerticalAlignment(VerticalAlignment.MIDDLE))
          			  ) ;

          	  // report.toPdf(new FileOutputStream(new File(Wizard.myJarPath()+"/Reports/outputiii.pdf")));
               report.setReportName(Wizard.SchoolName);

          	   try 
   		       {
   		    	   report.show(false);
  				
  			    } catch (Exception e) 
   		       {
  			    	e.printStackTrace();
  			   }
          	  // report.print();
   		  }
        
           public static void  CreateGeneralPaymentReport(GeneralPayment generalPayment) 
    		  {

                  String Party = generalPayment.getParty(),
                  PaymentType = generalPayment.gettype(),
                  PaymentDate = generalPayment.getdate(),
                  PaymentValue = generalPayment.getValue().toString();
                  ;
                  
           	   JasperReportBuilder report = DynamicReports.report();
           	   
           	   
           	   StyleBuilder boldStyle = DynamicReports.stl.style().bold(); 

           	   StyleBuilder boldRightCentered =  DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT).setFontSize(12).setRightPadding(20).setBorder(DynamicReports.stl.pen1Point()).setTopPadding(5).setBottomPadding(5);
           	   StyleBuilder boldRightCenteredStyle = DynamicReports.stl.style(boldRightCentered);
           	   StyleBuilder boldRightCenteredWithShadow = DynamicReports.stl.style(boldRightCentered).setBackgroundColor(Color.lightGray);
           	  
           	   StyleBuilder boldWithoutCenter =  DynamicReports.stl.style(boldStyle).setFontSize(12).setBorder(DynamicReports.stl.pen1Point()).setTopPadding(5).setBottomPadding(5);

           	   
           	   StyleBuilder boldCenteredStyle = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER).setFontSize(16).setBorder(DynamicReports.stl.pen1Point());
           	   StyleBuilder boldCenteredStyleWithShadow = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER).setBackgroundColor(Color.lightGray).setFontSize(14).setBorder(DynamicReports.stl.pen1Point()).setPadding(3);
           	   

           	   report.title(DynamicReports.cmp.text(SchoolName).setStyle(boldCenteredStyle).setHeight(40));
           	   report.addTitle(
           			   
           			   DynamicReports.cmp.verticalList().add
           			   (
           			   DynamicReports.cmp.text(PaymentHeader).setStyle(boldCenteredStyleWithShadow).setFixedHeight(PaymentFieldHighet)
           			  ,DynamicReports.cmp.text("نوع الدفعية : " +  PaymentType ).setStyle(boldRightCenteredStyle).setFixedHeight(PaymentFieldHighet)
           			  ,DynamicReports.cmp.text("الجهة المانحة \\المستقبلة  : " + Party).setStyle(boldRightCenteredWithShadow).setFixedHeight(PaymentFieldHighet)
           			  ,DynamicReports.cmp.text("تاريخ الدفعية : " + PaymentDate).setStyle(boldRightCenteredStyle).setFixedHeight(PaymentFieldHighet)
           			  ,DynamicReports.cmp.text("قيمة الدفعية : " + PaymentValue + " جنيه سوداني").setStyle(boldRightCenteredWithShadow).setFixedHeight(PaymentFieldHighet)
           			  ,DynamicReports.cmp.text("التوقيع :").setStyle(boldWithoutCenter.setLeftPadding(100)).setFixedHeight(PaymentFieldHighet)
           			  ,DynamicReports.cmp.image(myJarPath()+"/resources/SE_.png").setStyle(boldWithoutCenter.setLeftPadding(100)).setFixedHeight(80)
           		       ).setStyle(DynamicReports.stl.style(boldStyle).setVerticalAlignment(VerticalAlignment.MIDDLE))
           			  ) ;

           	  // report.toPdf(new FileOutputStream(new File(Wizard.myJarPath()+"/Reports/outputiii.pdf")));
               report.setReportName(Wizard.SchoolName);
                  try 
    		       {
    		    	   report.show(false);
   				
   			    } catch (Exception e) 
    		       {
   			    	e.printStackTrace();
   			   }
           	  // report.print();
    		  }
         
           
}
