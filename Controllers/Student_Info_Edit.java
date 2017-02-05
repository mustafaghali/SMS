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
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import databaseUtilities.DBUtil;
import wizards.Wizard;
import models.Student;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Student_Info_Edit extends Controlled_Screen implements Initializable
{
	int selected_id;
    Student student;
    List<String> types;
	List<String> elements;
	//temp Url
			String ImageUrl;
			 String ClassID ;
			 
    public void clear()
    {
    	ImageUrl = null;
    	NewStudentClass.setValue(null);
    	NewStudentImage.setImage(Wizard.getDefaultStudentImage());
    	NewStudentName.clear();
    	New_BDate.setValue(null);
    	NewStudentEmail.clear();
    	NewPhoneNumber.clear();
    	NewFatherPhoneNumber.clear();
    	NewStudyingFees.clear();
    	NewFatherSeetsFees.clear();
    	NewTranspFees.clear();
    	NewSummerCourseFees.clear();
    	NewTranspName.setValue(null);
    	NewStudentType.setValue(null);
    	NewHeathStatus.clear();
    	NewStudentAddress.clear();
    	choosePhoto.setVisible(true);
    	specialization.setVisible(false);
    	specializationLabel.setVisible(false);
    	specialization.setValue(null);
    }
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
/////////////////////////////////////////////////////////////////////////////////////
		EventHandler<Event> EditStudent = new EventHandler<Event>()
				{
			
			        @Override
			         public void handle(Event arg0) 
			        {
			        	 if(NewStudentName.getText().trim().length()==0)
	        	           {
	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم الطالب فارغا!").message("الرجاء إدخال اسم الطالب").showError();
//	        	   			NewStudentAddress.setStyle();
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
					           .message("الرجاء اختيار الفصل").showError();
			                   return;
		                   }
	       				
////////////////////////////////////////////////
                              //construct the update query
	       				String Q = "update student set Name=?,B_Date=?,phone_number=?,father_phone_number=?,Address=?,Email=?,studying_fees=?,father_seats_fees=?,summer_course_fees=?,transp_fees=?,Student_type=?,Health_status=?,Class_ID=?,transp_ID=?,specialization=?,ImageURL=? where id="
	       						+selected_id;
	       				types=new ArrayList<>();
	   			     elements = new ArrayList<>();
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
//					      System.out.println(elements.get(elements.size()-1));
//					      System.out.println(types.get(types.size()-1));
					      
					   // check the student class if he is a third year student ask for specialization
	        	           String ClassID = Wizard.getClassID(NewStudentClass.getValue());
	        	           //initialize specialization 
	       				String Specialization = null;
	       				//check if the user moved the student to the third class
	       				if (Wizard.getClassLevel(Integer.parseInt(ClassID)).equals(
	    						"الصف الثالث")&& !student.getClassLevel().equals("الصف الثالث"))
	    					Specialization = Wizard
	    							.chooseDialog(Wizard.specializations, "",
	    									"يجب تحديد تخصص الطالب",
	    									"الرجاء إدخال تخصص الطالب");
	       				else if(student.getClassLevel().equals("الصف الثالث")&& !Wizard.getClassLevel(Integer.parseInt(ClassID)).equals(
	    						"الصف الثالث"))
	       				{
	       					Specialization= null;
						}
	       				else
	       				{
	       					Specialization= specialization.getValue();
	       				}
	       				{
					     if(Specialization==null)
					      {// that means he is in the first/ second class
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
	       				}
					     
					     if(ImageUrl!=null)
					    	 {					    	    
					    	 String Imagefile=null;
					    	    try 
					    	    {
					    	    	if(student.getImageURL()==null) // No previous images were chosen -> make new name
					    	         Imagefile= ("1"+new Date()+".jpg").replace(":","-").replace(" ","");
					    	    	else 
					    	    	{ //maintain the same name 
					    	    		Imagefile=student.getImageURL();
					    	    	}
					    	    	BufferedImage inputImage=ImageIO.read(new File(ImageUrl));
									System.out.println(Wizard.myJarPath()+"/resources/"+Wizard.getFileName(ImageUrl));
							         //replaces the old image if exists 
									ImageIO.write(inputImage,"jpg",new File(Wizard.myJarPath()+"/resources/"+Imagefile));
					    	    } 
					    	    catch (IOException e) 
					    	    {
									System.out.println(e.getMessage()+"error copying the image to the root directory class student Info Edit");

					    	    }				
					    	      elements.add(Imagefile);
								  types.add(Wizard.String);
					    	 } 
					     else
					     {
					     // if not changed maintain the old image
					     elements.add(student.getImageURL());
						  types.add(Wizard.String);
					     }
					 }
	   			Boolean executed = DBUtil.excecuteUpdate(Q,elements,types);
				if (executed)
				{
					Dialogs.create()
							.owner(null)
							.title("رسالة إشعار")
							.masthead(null)
							.message("تمت عملية التعديل بنجاح")
							.showInformation();
		   			myController.setScreen("Student_Info","");
				}
				else
					Dialogs.create()
							.owner(null)
							.title("رسالة خطأ")
							.masthead("لم يتم تعديل بيانات الطالب")
							.message(
									"الرجاء التأكد من صحة البيانات المدحلة والتأكد من أن الاسم لم يستخدم مسبقا")
							.showWarning();
			        }
		        };
		ConfirmEditImage.setOnMouseClicked(EditStudent);
		ConfirmEditLabel.setOnMouseClicked(EditStudent);
///////////////////////////////////////////////////////////////////////////////////////
		// image chooser event handler
		EventHandler<Event> ChoosingImageEvent = new EventHandler<Event>() 
				{
			@Override
			public void handle(Event arg0)
			{
				ImageUrl = Wizard.Imagechooser(NewStudentImage);
				if (ImageUrl != null)
					choosePhoto.setVisible(false);
				// System.out.println(ImageUrl);
			}
		};
		NewStudentImage.setOnMouseClicked(ChoosingImageEvent);
		choosePhoto.setOnMouseClicked(ChoosingImageEvent);
///////////////////////////////////////////////////////////////////////////////////
		// // fill the Classes combonboxes
		NewStudentClass.setItems(Wizard.getStudentClasses());
///////////////////////////////////////////////////////////////////////////////////////
		// // fill the transporations comboboxes
		NewTranspName.setItems(Wizard.getTransporations());
///////////////////////////////////////////////////////////////////////////////////////
		// fill the student type combombox
		NewStudentType.setItems(Wizard.getStudentTypes());
/////////////////////////////////////////////////////////////////////////////////////////
		// fill the student type combombox
		specialization.setItems(Wizard.getSpecializations());
///////////////////////////////////////////////////////////////////////////////////////
		//this is to maintain a certain date format 
		New_BDate.setConverter(Wizard.DatePickerConverter());
	}
	
	@Override
	public void initialize_onshow() 
	{
		clear();
		selected_id = Wizard.global_selected_id;

		student = new Wizard().getStudentObject(selected_id);
		NewStudentName.setText(student.getName());
	      if (student.getB_Date() != null)
			New_BDate.setValue(Wizard.ToLocalDate(student.getB_Date()));
		if (student.getphone_number() != null)
			NewPhoneNumber.setText(student.getphone_number());
		if (student.getEmail_Address() != null)
			NewStudentEmail.setText(student.getEmail_Address());
		if (student.getClassName() != null)
			NewStudentClass.setValue(student.getClassName());
		if (student.getfather_phone_number() != null)
			NewFatherPhoneNumber.setText(student.getfather_phone_number());
		if (student.getfather_seets_fees() != null)
			NewFatherSeetsFees.setText(student.getfather_seets_fees()
					.toString());
		if (student.getaddress() != null)
			NewStudentAddress.setText(student.getaddress());
		if (student.getStudying_fees() != null)
			NewStudyingFees.setText(student.getStudying_fees().toString());
			//System.out.println(student.getClassLevel());
		if(student.getClassLevel().equals("الصف الثالث"))
		{
	    	specialization.setVisible(true);
			specializationLabel.setVisible(true);
			if (student.getspecialization() != null)
				specialization.setValue(student.getspecialization());
		}
		if (student.getsummer_course_fees() != null)
		    NewSummerCourseFees.setText(student.getsummer_course_fees().toString());
		if (student.gettransp_fees() != null)
			NewTranspFees.setText(student.gettransp_fees().toString());
		if (student.getTransp_Name() != null)
			NewTranspName.setValue(student.getTransp_Name());
		if (student.getStudent_type() != null)
			NewStudentType.setValue(student.getStudent_type());
		if (student.getHealth_Status() != null)
			NewHeathStatus.setText(student.getHealth_Status());
			if (student.getaddress() != null)
			NewStudentAddress.setText(student.getaddress());
		if(student.getImageURL()!=null)
		{
			//System.out.println(student.getImageURL());
			String temp=Wizard.myJarPath()+"/resources/"+student.getImageURL();
			ImageUrl=temp;
		    //System.out.println(temp);
		    Image image=null;
		    try 
		    {
				BufferedImage bufferedImage = ImageIO.read(new File(temp));
				image = SwingFXUtils.toFXImage(bufferedImage, null);
			}
		    catch (IOException e) 
		    {
		    	System.out.println(e.getMessage()+"error setting the student image edit class");
			}
			NewStudentImage.setImage(image);
			image=null;
		}
		else
		{
			NewStudentImage.setImage(Wizard.getDefaultStudentImage());
		}
		//System.out.println("../"+student.getImageURL());
	}

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
	private Label  ConfirmEditLabel;
	@FXML
	private ImageView ConfirmEditImage;
	@FXML
	private ComboBox<String> specialization;
	@FXML
	private Label specializationLabel;
	
}
