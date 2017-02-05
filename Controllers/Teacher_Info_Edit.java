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
import wizards.Wizard;
import models.Teacher;
import models.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Teacher_Info_Edit extends Controlled_Screen implements Initializable
{
	int selected_id;
    Teacher Teacher;
    List<String> types;
	List<String> elements;
	//temp Url
			String ImageUrl;
			
    public void clear()
    {
    	ImageUrl = null;
    	NewTeacherClass.setValue(null);
    	NewTeacherImage.setImage(Wizard.getDefaultTeacherImage());
    	NewTeacherName.clear();
    	NewTeacherEmail.clear();
    	NewPhoneNumber.clear();
    	NewTranspFees.clear();
    	NewTranspName.setValue(null);
    	NewTeacherAddress.clear();
    	choosePhoto.setVisible(true);
    	Newalternatives.clear();
    	NewSalary.clear();
    }
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
//////////////////////////////////////////////////////////////////////////////////////////////////////////
   // fill the Clas/ses combonboxes
   NewTeacherClass.setItems(Wizard.getTeacherClasses());
///////////////////////////////////////////////////////////////////////////////////////////////////////////
//	// fill the transporations comboboxes
   NewTranspName.setItems(Wizard.getTransporations());
/////////////////////////////////////////////////////////////////////////////////////////
		// image chooser event handler
		EventHandler<Event> imageChooser = new EventHandler<Event>() 
				{
			@Override
			public void handle(Event arg0)
			{
				ImageUrl = Wizard.Imagechooser(NewTeacherImage);
				if (ImageUrl != null)
					
					choosePhoto.setVisible(false);
				// System.out.println(ImageUrl);
			}
		};
		NewTeacherImage.setOnMouseClicked(imageChooser);
		choosePhoto.setOnMouseClicked(imageChooser);
/////////////////////////////////////////////////////////////////////////////////////////
		//add Teacher event handler
		EventHandler<Event> editTeacher = new EventHandler<Event>() 
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
			        	           //now you can try to add the teacher 
				 types=new ArrayList<>();
			     elements = new ArrayList<>();
//////////////////////////////////////////////////////////////////////////////////////////////
//				construct the editing query
				//12 prepared filed
				
			     String Q = "update teachers set Name=?,phone_number=?,Email=?,salary=?,alternatives=?,transp_fees=?,transp_ID=?,Class_ID=?,Address=?,ImageURL=? where id="
    						+selected_id;//
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
					 if(NewSalary.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewSalary.getText().trim());
						 types.add(Wizard.Double);
					 }
					 if(Newalternatives.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(Newalternatives.getText().trim());
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
				    	 // image was chosen 
				    	 String Imagefile=null;
				    	    try 
				    	    {
				    	    	if(Teacher.getImageURL()==null) // No previous images were chosen -> make new name
				    	         Imagefile= ("1"+new Date()+".jpg").replace(":","-").replace(" ","");
				    	    	else 
				    	    	{
				    	    		//maintain the same name 
				    	    		Imagefile=Teacher.getImageURL();
				    	    	}
				    	    	BufferedImage inputImage=ImageIO.read(new File(ImageUrl));
								System.out.println(Wizard.myJarPath()+"/resources/"+Wizard.getFileName(ImageUrl));
						         //replaces the old image if exists 
								ImageIO.write(inputImage,"jpg",new File(Wizard.myJarPath()+"/resources/"+Imagefile));
				    	    }
				    	    catch (IOException e) 
				    	    {
								System.out.println(e.getMessage()+"error copying the image to the root directory class Teacher Info Edit");

				    	    }				
				    	    elements.add(Imagefile);
							  types.add(Wizard.String);
				    	 }
				     else
				     {
				    	 // if not changed maintain the old one
					     elements.add(Teacher.getImageURL());
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
   			        myController.setScreen("Teacher_Info","");

				}
				else
					Dialogs.create()
					.owner(null)
					.title("رسالة خطأ")
					.masthead("لم يتم تعديل بيانات المعلم")
					.message(
							"الرجاء التأكد من صحة البيانات المدحلة والتأكد من أن الاسم لم يستخدم مسبقا")
					.showWarning();
			     }
		       };

		       ConfirmEditImage.setOnMouseClicked(editTeacher);
			   ConfirmEditLabel.setOnMouseClicked(editTeacher);
///////////////////////////////////////////////////////////////////////////////////////
	}
	
	@Override
	public void initialize_onshow() 
	{
		clear();
		selected_id = Wizard.global_selected_id;
		Teacher =Wizard.getTeacherObject(selected_id);
		NewTeacherName.setText(Teacher.getName());
//	      if (Teacher.getB_Date() != null)
//			Teacher_B_date.setText(Teacher.getB_Date());
		if (Teacher.getphone_number() != null)
			NewPhoneNumber.setText(Teacher.getphone_number());
		if (Teacher.getEmail_Address() != null)
			NewTeacherEmail.setText(Teacher.getEmail_Address());
		if (Teacher.getClassName() != null)
			NewTeacherClass.setValue(Teacher.getClassName());
		if (Teacher.getaddress() != null)
			NewTeacherAddress.setText(Teacher.getaddress());
		if (Teacher.gettransp_fees() != null)
			NewTranspFees.setText(Teacher.gettransp_fees().toString());
		//Newalternatives
		if (Teacher.getalternatives() != null)
			Newalternatives.setText(Teacher.getalternatives().toString());
		//salary 
		if (Teacher.getSalary() != null)
			NewSalary.setText(Teacher.getSalary().toString());
		//
		if (Teacher.getTransp_Name() != null)
			NewTranspName.setValue(Teacher.getTransp_Name());
		if(Teacher.getImageURL()!=null)
		{
			System.out.println(Teacher.getImageURL());
			String temp=Wizard.myJarPath()+"/resources/"+Teacher.getImageURL();
		    //System.out.println(" iiiiiiii"+temp);
		    Image image=null;
		    try 
		    {
				BufferedImage bufferedImage = ImageIO.read(new File(temp));
				  image = SwingFXUtils.toFXImage(bufferedImage, null);
			}
		    catch (IOException e) 
		    {
		    	System.out.println(e.getMessage()+" error setting the Teacher image ");
			}
			NewTeacherImage.setImage(image);
			
		}
		else
		{
			NewTeacherImage.setImage(Wizard.getDefaultTeacherImage());
		}
		//System.out.println("../"+Teacher.getImageURL());
	}

	 @FXML
	    private TextField NewTeacherName;

	    @FXML
	    private TextArea NewTeacherAddress;

	    @FXML
	    private TextField Newalternatives;

	    @FXML
	    private Label ConfirmEditLabel;

	    @FXML
	    private TextField NewSalary;

	    @FXML
	    private Label choosePhoto;

	    @FXML
	    private TextField NewPhoneNumber;
	    @FXML
	    private TextField NewTranspFees;

	    @FXML
	    private ComboBox<String> NewTeacherClass;

	    @FXML
	    private ImageView NewTeacherImage;

	    @FXML
	    private ImageView ConfirmEditImage;

	    @FXML
	    private ComboBox<String> NewTranspName;

	    @FXML
	    private TextField NewTeacherEmail;
	
}
