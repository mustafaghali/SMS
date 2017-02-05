package Controllers;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.controlsfx.dialog.Dialogs;

import databaseUtilities.DBUtil;
import databaseUtilities.JDBC;
import wizards.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.SClasses;
import models.Student;


public class Student_Tab_Control extends Controlled_Screen implements Initializable 
{
	// the query will be sent to the database
	// observable list will be hooked to the table view
	//static
	//temp observable list  
	
		ObservableList result;
		
		//temp Url
		String ImageUrl;
		
		List<String> types;
		List<String> elements;
		
	//global filechooser
		FileChooser fileChooser;
	@Override
	public void clear() 
	{
	   NewStudentName.clear();
	   New_BDate.setValue(null);
	   NewStudentClass.setValue(null);
	   NewStudentEmail.clear();
	   NewPhoneNumber.clear();
	   NewFatherPhoneNumber.clear();
	   NewStudyingFees.clear();
	   NewFatherSeetsFees.clear();
	   NewSummerCourseFees.clear();
	   NewTranspFees.clear();
	   NewTranspName.setValue(null);
	   NewHeathStatus.clear();
	   NewStudentAddress.clear();
	   ImageUrl=null;
	   choosePhoto.setVisible(true);
	   NewStudentImage.setImage(Wizard.getDefaultStudentImage());
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{    
//		Reload.setOnMouseClicked(Reload_clicked);
//		Back.setOnMouseClicked(Back_clicked);
////////////////////////////////////////////////////////////////////////////////
		nextScreen="Student_Info";
		super.initialize_onshow();
		
		ID.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
		ID.setCellFactory(integerCellFactory);
		Name.setCellValueFactory(new PropertyValueFactory<Student, String>("Name"));
		Name.setCellFactory(TransitionEventFactory);
		ClassName.setCellValueFactory(new PropertyValueFactory<Student, String>("ClassName"));
		ClassName.setCellFactory(TransitionEventFactory);
/////////////////////////////////////////////////////////////////////////////////////////
//		// fill the Classes combonboxes
		ObservableList ClassesNames;
		
			ClassesNames = Wizard.getStudentClasses();
			ObservableList<String> copy=FXCollections.observableArrayList();
			copy.addAll(ClassesNames);
			SearchClasses.setItems(copy);
			NewStudentClass.setItems(ClassesNames);
/////////////////////////////////////////////////////////////////////////////////////////
//		// fill the transporations comboboxes
                NewTranspName.setItems(Wizard.getTransporations());
/////////////////////////////////////////////////////////////////////////////////////////
                //fill the student type combombox
                NewStudentType.setItems(Wizard.getStudentTypes());
/////////////////////////////////////////////////////////////////////////////////////////
             //image chooser event handler
                EventHandler<Event> ChoosingImageEvent = new EventHandler<Event>() 
				{
			        @Override
		             	public void handle(Event arg0) 
		                      	{
		                      		ImageUrl = Wizard.Imagechooser(NewStudentImage);
			                    	if (ImageUrl != null)//image chosen
                   		 			choosePhoto.setVisible(false);
				                     //System.out.println(ImageUrl);
			                     }
		       };
		NewStudentImage.setOnMouseClicked(ChoosingImageEvent);
		choosePhoto.setOnMouseClicked(ChoosingImageEvent);
/////////////////////////////////////////////////////////////////////////////////////////
		//add student event handler
		EventHandler<Event> addStudent = new EventHandler<Event>() 
				{
			        @Override
		             	public void handle(Event arg0) 
		                      	{
			        	           if(NewStudentName.getText().trim().length()==0)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم الطالب فارغا!").message("الرجاء إدخال اسم الطالب").showError();
//			        	   			NewStudentAddress.setStyle();
			        	             return;
			        	           }
			        	           if(NewStudentClass.getValue()==null)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم  الفصل فارغا!").message("الرجاء اختيار الفصل").showError();
			        	             return;
			        	           }
			        	           else if(NewStudentClass.getValue().length()==0)
			        	           {
				                     	Dialogs.create().owner(null).title("رسالة خطأ")
							          .masthead("لايمكن ترك اسم  الفصل فارغا!")
							           .message("الرجاء إدخال اسم الفصل").showError();
					                   return;
				                   }
				// check the student class if he is a third year student ask for specialization
				String ClassID = Wizard.getClassID(NewStudentClass.getValue());
				String Specialization = null;
				
				 types=new ArrayList<>();
			     elements = new ArrayList<>();
			     
				// System.out.println(Wizard.getClassLevel(Integer.parseInt(ClassID)));
				if (Wizard.getClassLevel(Integer.parseInt(ClassID)).equals(
						"الصف الثالث"))
					Specialization = Wizard
							.chooseDialog(Wizard.specializations, "",
									"يجب تحديد تخصص الطالب",
									"الرجاء إدخال تخصص الطالب");
				////////////////////////////////////////////////
//				construct the insert query
				//12 prepared filed
				
				String Q = "insert into student (ID,Name,B_Date,phone_number,father_phone_number,Address,Email,studying_fees,father_seats_fees,summer_course_fees,transp_fees,Student_type,Health_status,Class_ID,transp_ID,specialization,ImageURL)"
						+ " values (NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//				                (1,2,3,4,5,6,7,8,9,10,11,12,13,14)
				 {
					 // then prepare the statment by filling the elements and types
					 elements.add(NewStudentName.getText().trim());
					 types.add(Wizard.String);
					 //elements.add(ClassID);
					 //types.add(Wizard.Integer);
					 if(New_BDate.getValue()==null)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(New_BDate.getValue().toString().trim());
						 types.add(Wizard.Date);
					 }
					 if(NewPhoneNumber.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewPhoneNumber.getText().trim());
						 types.add(Wizard.String);
					 }
					 if(NewFatherPhoneNumber.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewFatherPhoneNumber.getText().trim());
						 types.add(Wizard.String);
					 }
					 if(NewStudentAddress.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewStudentAddress.getText().trim());
						 types.add(Wizard.String);
					 }
					 if(NewStudentEmail.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewStudentEmail.getText().trim());
						 types.add(Wizard.String);
					 }
					 if(NewStudyingFees.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewStudyingFees.getText().trim());
						 types.add(Wizard.Double);
					 }
					 if(NewFatherSeetsFees.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewFatherSeetsFees.getText().trim());
						 types.add(Wizard.Double);
					 }
					
					 if(NewSummerCourseFees.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewSummerCourseFees.getText().trim());
						 types.add(Wizard.Double);
					 }
					 if(NewTranspFees.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewTranspFees.getText().trim());
						 types.add(Wizard.Double);
					 }
					 
					 if(NewStudentType.getValue()==null)
					 {
							elements.add("regular");
							 types.add(Wizard.String);
					 }
					 else if(NewStudentType.getValue().length()==0)
					 {
						 elements.add("regular");
						 types.add(Wizard.String);
					 }
					 else 
					 {
						String temp=NewStudentType.getValue();
						if(temp.equals("نظامي"))
							elements.add("regular");
						else
							elements.add("union");
					     types.add(Wizard.String);
					 }
					 if(NewHeathStatus.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewHeathStatus.getText().trim());
						 types.add(Wizard.String);
					 }
					 if(NewStudentClass.getValue()==null)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else if(NewStudentClass.getValue().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
							elements.add(Wizard.getClassID(NewStudentClass.getValue()));
							types.add(Wizard.Integer);
					 }
					 
				      if(NewTranspName.getValue()==null)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else if(NewTranspName.getValue().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 //System.out.println(Wizard.getTransspID(NewTranspName.getValue().toString()));
						 elements.add(Wizard.getTranspID(NewTranspName.getValue().toString()));
						 types.add(Wizard.Integer);
					 }
//				      System.out.println(elements.get(elements.size()-1));
//				      System.out.println(types.get(types.size()-1));

				     if(Specialization==null)
				      {
							 elements.add("Not yet");
							 types.add(Wizard.String);
					  }
					  else 
					  {
							 if(Specialization.compareTo("علمي")==0)
							 elements.add("scientific");
							 else 
								 elements.add("Literary");
							 types.add(Wizard.String);
					  }
				      
				     
				     if(ImageUrl!=null)
				    	 {
				    	 String Imagefile=null;
				    	    try 
				    	    {
				    	    	 Imagefile= ("1"+new Date()+".jpg").replace(":","-").replace(" ","");
								BufferedImage inputImage=ImageIO.read(new File(ImageUrl));
								//System.out.println(Wizard.myJarPath()+"/resources/"+Imagefile);
								ImageIO.write(inputImage,"jpg",new File(Wizard.myJarPath()+"/resources/"+Imagefile));
								inputImage =null;
				    	    }
				    	    catch (IOException e) 
				    	    {
								System.out.println(e.getMessage()+"error copying the image to the root directory class student tab");

							}
				    		
							  elements.add(Imagefile);
							  types.add(Wizard.String);
				    	 } 
		
						  else
						  {
							  elements.add("1");
								 types.add(Wizard.NULL);
						  }
				      
				 }
					Boolean executed = DBUtil.excecuteUpdate(Q,elements,types);
				if (executed)
				{
					Dialogs.create()
							.owner(null)
							.title("رسالة إشعار")
							.masthead(null)
							.message(
									"تمت إضافة الطالب " + NewStudentName.getText().trim()+" "
											+ "إلى قاعدة البيانات ")
							.showInformation();
					clear();
				}
				else
					Dialogs.create()
							.owner(null)
							.title("رسالة خطأ")
							.masthead("لم يتم إضافة بيانات الطالب")
							.message("الرجاء التأكد من صحة البيانات المدحلة والتأكد من أن الاسم لم يستخدم مسبقا")
							.showWarning();				
			     }
		       };
		       
		       AddStudentLabel.setOnMouseClicked(addStudent);
		       AddStudentPhoto.setOnMouseClicked(addStudent);
/////////////////////////////////////////////////////////////////////////////////////////
//search icon handler 
		       EventHandler<Event> SearchStudent = new EventHandler<Event>() 
		    		   {
				
				@Override
				public void handle(Event arg0) 
				{
					List<String> types = new ArrayList<String>();
					List<String> elements = new ArrayList<String>();
					String temp2 = Student_name.getText().trim();
					String temp1 = Student_ID.getText().trim();
					String temp3 = SearchClasses.getValue();
//					System.out.println(temp3);
					try {
						if (temp1.length() != 0 && temp2.length() != 0 && temp3 != null) // three
																							// fields
						{
							elements.add(temp1);
							types.add(Wizard.Integer);
							elements.add(temp2);
							types.add(Wizard.LikeString);
							elements.add(temp3);
							types.add(Wizard.String);

							query = "SELECT * FROM student WHERE ID =? UNION SELECT * FROM student WHERE Name like ? UNION SELECT * FROM student WHERE Class_ID =(select ID from classes where name = ?)";
						} else if (temp1.length() != 0 && temp2.length() != 0) {
							elements.add(temp1);
							types.add(Wizard.Integer);
							elements.add(temp2);
							types.add(Wizard.LikeString);
							query = "SELECT * FROM student WHERE ID =? UNION SELECT * FROM student WHERE Name like ?";
						} else if (temp2.length() != 0 && temp3 != null) {
							elements.add(temp2);
							types.add(Wizard.LikeString);
							elements.add(temp3);
							types.add(Wizard.String);
							query = "SELECT * FROM student WHERE Name like ? UNION SELECT * FROM student WHERE Class_ID =(select ID from classes where name = ?)";

						} else if (temp1.length() != 0 && temp3 != null) {
							elements.add(temp1);
							types.add(Wizard.Integer);
							elements.add(temp3);
							types.add(Wizard.String);
							query = "SELECT * FROM student WHERE ID =?  UNION SELECT * FROM student WHERE Class_ID =(select ID from classes where name = ?)";
						} else if (temp1.length() != 0) {
							elements.add(temp1);
							types.add(Wizard.Integer);
							query = "SELECT * FROM student WHERE ID =?";
						} else if (temp2.length() != 0) {
							elements.add(temp2);
							types.add(Wizard.LikeString);
							query = "SELECT * FROM student WHERE Name like ?";
						} else if (temp3 != null) {
							elements.add(temp3);
							types.add(Wizard.String);
							query = "SELECT * FROM student WHERE Class_ID =(select ID from classes where name = ?)";
						} else

						{
							Dialogs.create().owner(null).title("إشعار")
									.message("الرجاء أدخال بيانات حقل واحد على الأقل :)")
									.showWarning();
							return;
						}
						result = (ObservableList<Student>) JDBC.fill_Otabel(query,Wizard.Student, elements, types);
						if (result.size()==0)
						{
							Dialogs.create()
			                .owner(null)
			                .title("نتيجة فارغة")
			                .message("لا توجد نتيجة توافق البيانات المدخلة الرجاء التأكد من صحة البيانات المدخلة :)")
			                .showWarning();
						}
						tabel.setItems(result);
						resultcopy=FXCollections.observableArrayList();
						resultcopy.addAll(result);
					} catch (Exception e) 
					{
						System.out.println("error retriving observable list in student tab after search");
					}

				}
			};
	  SearchIcon.setOnMouseClicked(SearchStudent);
	  SearchLabel.setOnMouseClicked(SearchStudent);
/////////////////////////////////////////////////////////////////////////////////////////
	//search icon handler 
    EventHandler<Event> FindAll = new EventHandler<Event>() 
 		   {
		
		@Override
		public void handle(Event arg0) 
		{
			
			
				result = (ObservableList<Student>) JDBC.fill_Otabel("select * from student",Wizard.Student,null,null);
				if (result.size()==0)
				{
					Dialogs.create()
	                .owner(null)
	                .title("نتيجة فارغة")
	                .message("لا توجد أي بيانات في هذا الحقل")
	                .showWarning();
				}
				tabel.setItems(result);
				resultcopy=FXCollections.observableArrayList();
				resultcopy.addAll(result);
		}
	};
/////////////////////////////////////////////////////////////////////////////////////////
        ShowAllImage.setOnMouseClicked(FindAll);
        ShowAllLabel.setOnMouseClicked(FindAll);

	
	}
	@FXML
	private TableView<Student> tabel;
	@FXML
	private TableColumn ID;
	@FXML
	private TableColumn ClassName;
	@FXML
	private TableColumn Name;
	@FXML
	private TextField Student_ID;
	@FXML
	private TextField Student_name;
	@FXML
	private ComboBox<String> SearchClasses;
	@FXML
	private ImageView SearchIcon;
	@FXML
	private Label SearchLabel;
	
	
/////////////////////////////////////////////////////////////////////////////////////////
@FXML
private ComboBox<String> NewStudentClass;
@FXML
private ImageView NewStudentImage;
@FXML
private TextField NewStudentName;
@FXML
private DatePicker New_BDate;
@FXML
private TextField NewStudentEmail;
@FXML
private TextField NewPhoneNumber;
@FXML
private TextField NewFatherPhoneNumber;
@FXML
private TextField NewStudyingFees;
@FXML
private TextField NewFatherSeetsFees;
@FXML
private TextField NewTranspFees;
@FXML
private TextField NewSummerCourseFees;
@FXML
private ComboBox<String> NewTranspName ;
@FXML
private ComboBox<String> NewStudentType ;
@FXML
private TextArea NewHeathStatus;
@FXML
private TextArea NewStudentAddress;
@FXML
private Label choosePhoto;
@FXML
private Label  AddStudentLabel;
@FXML
private ImageView AddStudentPhoto;
@FXML 
private SplitPane verSplitPane;
@FXML 
private  ImageView ShowAllImage;
@FXML 
private Label ShowAllLabel;
/////////////////////////////////////////////////////////////////////////////////////////
}