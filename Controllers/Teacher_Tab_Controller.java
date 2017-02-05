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
import wizards.Wizard;
import models.Student;
import models.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class Teacher_Tab_Controller extends Controlled_Screen implements Initializable 
   {
	
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
				   NewTeacherName.clear();
				   NewTeacherClass.setValue(null);
				   NewTeacherEmail.clear();
				   NewPhoneNumber.clear();
				   NewTranspFees.clear();
				   NewTranspName.setValue(null);
				   NewTeacherAddress.clear();
				   NewTeacherAlternatives.clear();
				   NewTeacherSalary.clear();
				   NewTeacherAddress.clear();
				   ImageUrl=null;
				   choosePhoto.setVisible(true);
				   NewTeacherImage.setImage(Wizard.getDefaultTeacherImage());

			}
			@Override
			public void initialize(URL location, ResourceBundle resources) 
			{
				nextScreen="Teacher_Info";
				ID.setCellValueFactory(new PropertyValueFactory<Teacher, Integer>("id"));
				ID.setCellFactory(integerCellFactory);
				Name.setCellValueFactory(new PropertyValueFactory<Teacher, String>("Name"));
				Name.setCellFactory(TransitionEventFactory);
				ClassName.setCellValueFactory(new PropertyValueFactory<Teacher, String>("ClassName"));
				ClassName.setCellFactory(TransitionEventFactory);
//////////////////// //////////////////////////////////////////////////////////////////////////////////////
                // fill the Classes combonboxes
	          	ObservableList ClassesNames = Wizard.getTeacherClasses();
		        ObservableList<String> copy = FXCollections.observableArrayList();
		        copy.addAll(ClassesNames);
		        SearchClasses.setItems(copy);
		        NewTeacherClass.setItems(ClassesNames);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
//				// fill the transporations comboboxes
                NewTranspName.setItems(Wizard.getTransporations());
/////////////////////////////////////////////////////////////////////////////////////////
                //image chooser event handler
                EventHandler<Event> imageChooser = new EventHandler<Event>() 
				{
			        @Override
		             	public void handle(Event arg0) 
		                      	{
		                      		ImageUrl = Wizard.Imagechooser(NewTeacherImage);
			                    	if (ImageUrl != null)
                   		 			choosePhoto.setVisible(false);
				                     //System.out.println(ImageUrl);
			                     }
		       };
		NewTeacherImage.setOnMouseClicked(imageChooser);
		choosePhoto.setOnMouseClicked(imageChooser);
/////////////////////////////////////////////////////////////////////////////////////////
		//add Teacher event handler
		EventHandler<Event> addTeacher = new EventHandler<Event>() 
				{
			        @Override
		             	public void handle(Event arg0) 
		                      	{
			        	           if(NewTeacherName.getText().trim().length()==0)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم المعلم فارغا!").message("الرجاء إدخال اسم المعلم").showError();
//			        	   			NewTeacherAddress.setStyle();
			        	             return;
			        	           }
			        	           if(NewTeacherClass.getValue()==null)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم  الشعبة فارغا!").message("الرجاء  اختيار الشعبة").showError();
			        	             return;
			        	           }
			        	           else if(NewTeacherClass.getValue().length()==0)
			        	           {
			        	        	   Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم  الشعبة فارغا!").message("الرجاء  اختيار الشعبة").showError();
					                   return;
				                   }
				 types=new ArrayList<>();
			     elements = new ArrayList<>();
//////////////////////////////////////////////////////////////////////////////////////////////
//				construct the insert query
				//12 prepared filed
				
				String Q = "insert into teachers (ID,Name,phone_number,Email,Salary,alternatives,transp_fees,transp_ID,Class_ID,Address,ImageURL)"
						+ " values (NULL,?,?,?,?,?,?,?,?,?,?)";
//				                (1,2,3,4,5,6,7,8,9,10,11,12,13,14)
				 {
					 // then prepare the statment by filling the elements and types
					 elements.add(NewTeacherName.getText().trim());
					 types.add(Wizard.String);
					 
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
					
					 if(NewTeacherEmail.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewTeacherEmail.getText().trim());
						 types.add(Wizard.String);
					 }
					 if(NewTeacherSalary.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewTeacherSalary.getText().trim());
						 types.add(Wizard.Double);
					 }
					 if(NewTeacherAlternatives.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewTeacherAlternatives.getText().trim());
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
					 
					 if(NewTeacherClass.getValue()==null)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else if(NewTeacherClass.getValue().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
							elements.add(Wizard.getTeacherClassID(NewTeacherClass.getValue()));
							types.add(Wizard.Integer);
					 }
					 
				     if(NewTeacherAddress.getText().length()==0)
					   {
						 elements.add("1");
						 types.add(Wizard.NULL);
					   }
					  else 
					   {
						 elements.add(NewTeacherAddress.getText().trim());
						 types.add(Wizard.String);
					   }
				     
				     if(ImageUrl!=null)
				    	 {
				    	 String Imagefile=null;
				    	    try 
				    	    {
				    	    	 Imagefile= ("1"+new Date()+".jpg").replace(":","-").replace(" ","");
								BufferedImage inputImage=ImageIO.read(new File(ImageUrl));
								System.out.println(Wizard.myJarPath()+"/resources/"+Imagefile);
								ImageIO.write(inputImage,"jpg",new File(Wizard.myJarPath()+"/resources/"+Imagefile));
								inputImage =null;
				    	    }
				    	    catch (IOException e) 
				    	    {
								System.out.println(e.getMessage()+"error copying the image to the root directory class Teacher tab");

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
									"تمت إضافة المعلم " + NewTeacherName.getText().trim()+" "
											+ "إلى قاعدة البيانات ")
							.showInformation();
					clear();

				}
				else
					Dialogs.create()
							.owner(null)
							.title("رسالة خطأ")
							.masthead("لم يتم إضافة بيانات المعلم")
							.message(
									"الرجاء التأكد من صحة البيانات المدحلة والتأكد من أن الاسم لم يستخدم مسبقا")
							.showWarning();
				ImageUrl=null;
				NewTeacherImage.setImage(Wizard.getDefaultTeacherImage());
				choosePhoto.setVisible(true);
				
			     }
		       };

		AddTeacherLabel.setOnMouseClicked(addTeacher);
		AddTeacherPhoto.setOnMouseClicked(addTeacher);
///////////////////////////////////////////////////////////////////////////////////////
		// search icon handler
		EventHandler<Event> SearchTeacher = new EventHandler<Event>() 
				{

			@Override
			public void handle(Event arg0)
			{
				List<String> types = new ArrayList<String>();
				List<String> elements = new ArrayList<String>();
				String temp2 = Teacher_name.getText().trim();
				String temp1 = Teacher_ID.getText().trim();
				String temp3 = SearchClasses.getValue();
				// System.out.println(temp3);
				try {
					  if (temp1.length() != 0 && temp2.length() != 0
							&& temp3 != null) // three
					// fields
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						elements.add(temp2);
						types.add(Wizard.LikeString);
						elements.add(temp3);
						types.add(Wizard.String);

						query = "SELECT * FROM Teachers WHERE ID =? UNION SELECT * FROM Teachers WHERE Name like ? UNION SELECT * FROM Teacher WHERE Class_ID =(select ID from teachers_classes where name = ?)";
					}
					else if (temp1.length() != 0 && temp2.length() != 0)
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						elements.add(temp2);
						types.add(Wizard.LikeString);
						query = "SELECT * FROM Teachers WHERE ID =? UNION SELECT * FROM Teachers WHERE Name like ?";
					} 
					else if (temp2.length() != 0 && temp3 != null)
					{
						elements.add(temp2);
						types.add(Wizard.LikeString);
						elements.add(temp3);
						types.add(Wizard.String);
						query = "SELECT * FROM Teachers WHERE Name like ? UNION SELECT * FROM Teachers WHERE Class_ID =(select ID from teachers_classes where name = ?)";
					} 
					else if (temp1.length() != 0 && temp3 != null) 
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						elements.add(temp3);
						types.add(Wizard.String);
						query = "SELECT * FROM Teachers WHERE ID =?  UNION SELECT * FROM Teachers WHERE Class_ID =(select ID from teachers_classes where name = ?)";
					} 
					else if (temp1.length() != 0)
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						query = "SELECT * FROM Teachers WHERE ID =?";
					} 
					else if (temp2.length() != 0) 
					{
						elements.add(temp2);
						types.add(Wizard.LikeString);
						query = "SELECT * FROM Teachers WHERE Name like ?";
					} 
					else if (temp3 != null)
					{
						elements.add(temp3);
						types.add(Wizard.String);
						query = "SELECT * FROM Teachers WHERE Class_ID =(select ID from teachers_classes where name = ?)";
					}
					else
					{
						Dialogs.create()
								.owner(null)
								.title("إشعار")
								.message(
										"الرجاء أدخال بيانات حقل واحد على الأقل :)")
								.showWarning();
						return;
					}
					result = (ObservableList<Teacher>) JDBC.fill_Otabel(query,
							Wizard.Teacher, elements, types);
					if (result.size()==0)
					{
						Dialogs.create()
		                .owner(null)
		                .title("نتيجة فارغة")
		                .message("لا توجد نتيجة توافق البيانات المدخلة الرجاء التأكد من صحة البيانات المدخلة :)")
		                .showWarning();
					}
					tabel.setItems(result);
					resultcopy = result;
					// resultcopy.addAll(result);
				} 
				catch (Exception e)
				{
					System.out
							.println("error retriving observable list in Teacher tab after search");
				}

			}
		};
		SearchIcon.setOnMouseClicked(SearchTeacher);
		SearchLabel.setOnMouseClicked(SearchTeacher);
///////////////////////////////////////////////////////////////////////////////////////
		//search all icon handler 
	    EventHandler<Event> FindAll = new EventHandler<Event>() 
	 		   {
			
			@Override
			public void handle(Event arg0) 
			{
				
				
					result = (ObservableList<Teacher>) JDBC.fill_Otabel("select * from teachers",Wizard.Teacher,null,null);
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
	      ShowAllImage.setOnMouseClicked(FindAll);
	      ShowAllLabel.setOnMouseClicked(FindAll);

	}//end of initialize method
///////////////////////////////////////////////////////////////////////////////////////

	@FXML
    private TableView<Teacher> tabel;
	@FXML
	private TableColumn ID;
	@FXML
	private TableColumn ClassName;
	@FXML
	private TableColumn Name;
	@FXML
	private TextField Teacher_ID;
	@FXML
	private TextField Teacher_name;
	@FXML
	private ComboBox<String> SearchClasses;
	@FXML
	private ImageView SearchIcon;
	@FXML
	private Label SearchLabel;
////////////////////////////////////////////////////////////////////////////////
	@FXML
	private Label choosePhoto;
	@FXML
	private TextField NewTeacherName;
	@FXML
    private TextField NewPhoneNumber;
	@FXML
	private TextField NewTeacherEmail;
	@FXML
	private ImageView AddTeacherPhoto;
	@FXML
    private Label AddTeacherLabel;
	@FXML
    private TextField NewTranspFees;
	@FXML
    private ComboBox<String> NewTeacherClass;
    @FXML
    private ImageView NewTeacherImage;
    @FXML
    private ComboBox<String> NewTranspName;
    @FXML
    private TextField NewTeacherAlternatives;
    @FXML
    private TextField NewTeacherSalary;
    @FXML
    private TextArea NewTeacherAddress;
    @FXML 
    private  ImageView ShowAllImage;
    @FXML 
    private Label ShowAllLabel;
	
   }
