package databaseUtilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.controlsfx.dialog.Dialogs;

import wizards.Wizard;
import models.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class JDBC 
{
	//this takes the type of the observable list and returns 
	//this function takes a query and fill the result set to the the specified collection 
   public static Observable fill_Otabel(String Q,String who,List<String> elements,List<String> types) 
   {
	   String temp;
	   ResultSet rs;
	   //obtain the result set
	   if(elements==null ||elements.size()==0)
	   {
	       rs = DBUtil.excecuteQuery(Q);  
	   }
	   else
	   {
		   rs= DBUtil.excecuteQuery(Q,elements,types); 
	   }
	   Observable  result =FXCollections.observableArrayList();
	   try 
	   {
		   //System.out.println(Q);
		   rs.last();
			if (rs.getRow() == 0)
			{
//			   JOptionPane.showMessageDialog(null,"لا يوجد طالب يوافق البيانات المدخلة الرجاء التأكد من صحة البيانات المدخلة","نتيجة فارغة",JOptionPane.ERROR_MESSAGE);
			return result;
			} 
			rs.beforeFirst();
	     } 
	   catch (Exception e)
	   {
		   System.out.println(e.getMessage()+"  error constructing the result set either its null or connection faild class JDBC");
	   }
	   
	   //create observable list
	  
	   switch (who) 
	   {
///////////////////////////////////////////////////////////////////////////////////////////
        	case Wizard.Student:
        	{
        		try
        		{
		    	  ObservableList<Student> students = FXCollections.observableArrayList();
				  while (rs.next())
			   { 
					Student student = new Student(rs.getInt("ID"),
							rs.getString("Name"));
				
					student.setaddress(rs.getString("Address"));
					student.setB_Date(rs.getString("B_Date"));
					student.setClass_ID(rs.getInt("Class_ID"));
					student.setfather_phone_number(rs
							.getString("father_phone_number"));
					student.setfather_seets_fees(rs
							.getDouble("father_seats_fees"));
					student.setHealth_Status(rs.getString("Health_status"));
					student.setphone_number(rs.getString("phone_number"));
					student.setClassName(Wizard.getStudentClassName(student
							.getClass_ID()));
					student.setClassLevel(Wizard.getClassLevel(student
							.getClass_ID()));
					int transp_Id=rs.getInt("transp_ID");
					//System.out.print(transp_Id);
					student.settransp_ID(transp_Id);
					student.setsummer_course_fees(rs
							.getDouble("summer_course_fees"));
					student.setStudying_fees(rs.getDouble("studying_fees"));
					student.settransp_fees(rs.getDouble("transp_fees"));
				//System.out.println(Wizard.getTransp_Name(transp_Id));
					if(transp_Id!=0)
					{
						student.setTransp_Name(Wizard.getTransp_Name(transp_Id));
					}
					
					student.setSupervisorName(Wizard.getTeacherName(student
							.getClass_ID()));
					temp = rs.getString("specialization");
					if (temp.equals("scientific"))
						student.setspecialization("علمي");
					else if (temp.equals("Literary"))
						student.setspecialization("أدبي");
					else
						student.setspecialization("ليس بعد");
					temp = rs.getString("Student_type");
					if (temp.equals("regular"))
						temp = "نظامي";
					else
						temp = "اتحادي";
					student.setStudent_type(temp);
				    student.setEmail_Address(rs.getString("Email"));	
				    student.setImageURL(rs.getString("ImageURL"));
				    students.add(student);
			   }	
				  result=students; 
        		} catch (Exception e) 
        		{
        			System.out.println("error filling student set in JDBC class "+e.getMessage());
				}
		      break;
        	}
///////////////////////////////////////////////////////////////////////////////////////////
        	case Wizard.Teacher:
        	{
        		try
        		{
		    	  ObservableList<Teacher> Teachers = FXCollections.observableArrayList();
				  while (rs.next())
			   { 
					Teacher Teacher = new Teacher(rs.getInt("ID"),
							rs.getString("Name"));
				
					Teacher.setaddress(rs.getString("Address"));
					Teacher.setClass_ID(rs.getInt("Class_ID"));
					Teacher.setphone_number(rs.getString("phone_number"));
					Teacher.setClassName(Wizard.getTeacherClassName(Teacher
							.getClass_ID()));
					int transp_Id=rs.getInt("transp_ID");// we need to use it again
					Teacher.settransp_ID(transp_Id);
					Teacher.settransp_fees(rs.getDouble("transp_fees"));
					Teacher.setSalary(rs.getDouble("Salary"));
					Teacher.setalternatives(rs.getDouble("alternatives"));
					if(transp_Id!=0)
					{
						Teacher.setTransp_Name(Wizard.getTransp_Name(transp_Id));
					}
				    Teacher.setEmail_Address(rs.getString("Email"));	
				    Teacher.setImageURL(rs.getString("ImageURL"));
				    Teachers.add(Teacher);
			   }	
				  result=Teachers; 
        		} catch (Exception e) 
        		{
        			System.out.println("error filling Teacher set in JDBC class "+e.getMessage());
				}
		      break;
        	}
///////////////////////////////////////////////////////////////////////////////////////////
        	case Wizard.Worker:
        	{
        		try
        		{
		    	  ObservableList<Worker> workers = FXCollections.observableArrayList();
				  while (rs.next())
			   { 
					Worker worker = new Worker(rs.getInt("ID"),
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
				    workers.add(worker);
			   }	
				  result=workers; 
        		} catch (Exception e) 
        		{
        			System.out.println("error filling worker set in JDBC class "+e.getMessage());
				}
		      break;
        	}  	
///////////////////////////////////////////////////////////////////////////////////////////
        	case Wizard.SClass:
        	{
        		try 
        		{
        		//System.out.println("in");
   		    	  ObservableList<SClasses> classes = FXCollections.observableArrayList();
   		    	SClasses myclass =new SClasses();
//   				myclass.setName("");
//   				  classes.add(myclass);
   				  while (rs.next())
                   {
					// System.out.println(rs.getString("Name"));
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
					classes.add(myclass);

				}
				// System.out.println(classes.get(0).getName());
				result = classes;
        		} 
        		catch (Exception e) 
        		{
        			System.out.println("error filling Classes set in JDBC class "+e.getMessage());

				}
				break;
			}
///////////////////////////////////////////////////////////////////////////////////////////
        	case Wizard.TClass:
        	{
        		try 
        		{
        			ObservableList<TClasses> classes = FXCollections.observableArrayList();
        		    	TClasses myclass =new TClasses("",1);
        				 // classes.add(myclass);
        				  while (rs.next())
                          {
       					       myclass = new TClasses(rs.getString("Name"),rs.getInt("ID"));
       						classes.add(myclass);
       						//System.out.println(myclass.getName());
        				  }
        				  result = classes;
        				  
        		} 
        		catch (Exception e) 
        		{
        			System.out.println("error filling Teacher Classes set in JDBC class "+e.getMessage());
				}        		
        		break;
        	}
///////////////////////////////////////////////////////////////////////////////////////////
		case Wizard.Subject:
		{
			try {
				ObservableList<Subject> subjects = FXCollections
						.observableArrayList();
				Subject mysubject;
				while (rs.next()) 
				{
					mysubject = new Subject(rs.getString("Name"),
							rs.getInt("ID"));
					mysubject.setClass_level(rs.getString("Class_level"));
					subjects.add(mysubject);

				}
				result = subjects;

			} catch (Exception e)
			{
				System.out
						.println("error filling subject set in JDBC class "
								+ e.getMessage());
			}
			break;
		}
///////////////////////////////////////////////////////////////////////////////////////////
			case Wizard.transporation: 
			{ 
				try
				{
				ObservableList<Transporation> Transporations = FXCollections.observableArrayList();
		    	Transporation mytransp =new Transporation(0,"");
		    	//Transporations.add(mytransp);
		    	  while (rs.next())
                  {
		    		  mytransp= new Transporation();
		    		  mytransp.setid(rs.getInt("ID"));
		    		  mytransp.setDestination(rs.getString("Destination"));
		    		  mytransp.setDriver_name(rs.getString("Driver_name"));
		    		  mytransp.setvehicle_type(rs.getString("vehicle_type"));
		    		  Transporations.add(mytransp);
                  }
		    	result=Transporations;
				}
		    	catch (Exception e)
		    	{
        			System.out.println("error filling transporations set in JDBC class "+e.getMessage());
		    	}
				
				break;
			}
///////////////////////////////////////////////////////////////////////////////////////////
			case Wizard.Result: 
			{ 
				try
				{
				ObservableList<Result> Results = FXCollections.observableArrayList();
				Result myResult ;
		    	  while (rs.next())
                  {
		    		  myResult= new Result();
		    		  myResult.setid(rs.getInt("ID"));
		    		  myResult.setClass_ID(rs.getInt("Class_ID"));
		    		  myResult.setdate(rs.getString("Date"));
		    		  myResult.setSupervisor_1(rs.getInt("supervisor_1"));
		    		  myResult.setSupervisor_2(rs.getInt("supervisor_2"));
		    		  myResult.setSemester_ID(rs.getInt("semster_ID"));
		    		  myResult.setSemester_ID(rs.getInt("ExamPrecentage"));
		    		  myResult.settype(Wizard.ResultTypeConverter(rs.getString("Type")));
		    		  myResult.setClassName(Wizard.getStudentClassName(rs.getInt("Class_ID")));
		    		  myResult.setLevel(Wizard.getClassLevel(rs.getInt("Class_ID")));
		    		  Results.add(myResult);
                  }
		    	result=Results;
				}
		    	catch (Exception e)
		    	{
        			System.out.println("error filling Results set in JDBC class "+e.getMessage());
		    	}
				
				break;
			}
///////////////////////////////////////////////////////////////////////////////////////////
			case Wizard.GeneralPayment: 
			{ 
				try
				{
				ObservableList<GeneralPayment> GeneralPayments = FXCollections.observableArrayList();
				GeneralPayment myPayment ;
		    	  while (rs.next())
                  {
		    		  myPayment= new GeneralPayment();
		    		  myPayment.settype(Wizard.GeneralPaymentTypeConverter(rs.getString("Type")));
		    		  myPayment.setdate(rs.getString("Date"));
		    		  myPayment.setParty(Wizard.GeneralPaymentPartyConverter(rs.getString("Party")));
		    		  myPayment.setValue(rs.getDouble("Value"));
		    		  myPayment.setNotes(rs.getString("Notes"));
		    		  GeneralPayments.add(myPayment);
                  }
		    	result=GeneralPayments;
				}
		    	catch (Exception e)
		    	{
        			System.out.println("error filling GeneralPayments set in JDBC class "+e.getMessage());
		    	}
				
				break;
			}
///////////////////////////////////////////////////////////////////////////////////////////
			case Wizard.StudentPayment: 
			{ 
				try
				{
				  ObservableList<StudentPayment> StudentPayments = FXCollections.observableArrayList();
				   StudentPayment myPayment ;
		    	  while (rs.next())
                  {
		    		  myPayment= new StudentPayment();
		    		  myPayment.settype(Wizard.StudentPaymentTypeConverter(rs.getString("type")));
		    		  myPayment.setdate(rs.getString("the_date"));
		    		  myPayment.setStudentID(rs.getInt("student_ID"));
		    		  Student tempstudent  = Wizard.getStudentObject(myPayment.getStudentID());
		    		  myPayment.setStudentName(tempstudent.getName());
		    		  myPayment.setStudentClassName(Wizard.getStudentClassName(tempstudent.getClass_ID()));
		    		  myPayment.setValue(rs.getDouble("value"));
		    		  myPayment.setNotes(rs.getString("Notes"));
		    		  StudentPayments.add(myPayment);
                  }
		    	result=StudentPayments;
				}
		    	catch (Exception e)
		    	{
        			System.out.println("error filling StudentPayment set in JDBC class "+e.getMessage());
		    	}
				
				break;
			}
///////////////////////////////////////////////////////////////////////////////////////////
    		case Wizard.TeacherPayment: 
			{ 
				try
				{
				  ObservableList<TeacherPayment> TeacherPayments = FXCollections.observableArrayList();
				   TeacherPayment myPayment ;
		    	  while (rs.next())
                  {
		    		  myPayment= new TeacherPayment();
		    		  myPayment.settype(Wizard.TeacherPaymentTypeConverter(rs.getString("type")));
		    		  myPayment.setdate(rs.getString("the_date"));
		    		  myPayment.setTeacherID(rs.getInt("teacher_ID"));
		    		  myPayment.setTeacherName(Wizard.getTeacherName(rs.getInt("teacher_ID")));
		    		  myPayment.setTeacherClassName(Wizard.getTeacherClassName(Integer.parseInt(Wizard.getTeacherClass_ID(myPayment.getTeacherID().toString()))));
		    		  myPayment.setValue(rs.getDouble("value"));
		    		  myPayment.setNotes(rs.getString("Notes"));
		    		  TeacherPayments.add(myPayment);
                  }
		    	result=TeacherPayments;
				}
		    	catch (Exception e)
		    	{
        			System.out.println("error filling TeacherPayment set in JDBC class "+e.getMessage());
		    	}
				
				break;
			}
///////////////////////////////////////////////////////////////////////////////////////////
    		case Wizard.WorkerPayment: 
			{ 
				try
				{
				  ObservableList<WorkerPayment> WorkerPayments = FXCollections.observableArrayList();
				  WorkerPayment myPayment ;
		    	  while (rs.next())
                  {
		    		  myPayment= new WorkerPayment();
		    		  myPayment.settype(Wizard.WorkerPaymentTypeConverter(rs.getString("type")));
		    		  myPayment.setdate(rs.getString("the_date"));
		    		  myPayment.setWorkerID(rs.getInt("worker_ID"));
		    		  Worker TempWorker = Wizard.getWorkerObject(myPayment.getWorkerID());
		    		  myPayment.setWorkerName(TempWorker.getName());
		    		  myPayment.setJobType(TempWorker.getWorksAs());
		    		  myPayment.setValue(rs.getDouble("value"));
		    		  myPayment.setNotes(rs.getString("Notes"));
		    		  WorkerPayments.add(myPayment);
                  }
		    	result=WorkerPayments;
				}
		    	catch (Exception e)
		    	{
        			System.out.println("error filling WorkerPayment set in JDBC class "+e.getMessage());
		    	}
				
				break;
			}

        	default:
        	{
        	}
	         }//end of switch 
	   try 
	   {
		rs.close();
	    } 
	   catch (SQLException e) 
	   {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return result;  
   }
}
