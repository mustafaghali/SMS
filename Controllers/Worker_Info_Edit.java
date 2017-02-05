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
import models.Worker;
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

public class Worker_Info_Edit extends Controlled_Screen implements Initializable
{
	int selected_id;
    Worker worker;
    List<String> types;
	List<String> elements;
			String ImageUrl;
			
    public void clear()
    {
    	ImageUrl = null;
    	NewJob.clear();
    	NewWorkerImage.setImage(Wizard.getDefaultWorkerImage());
    	NewWorkerName.clear();
    	NewPhoneNumber.clear();
    	NewTranspFees.clear();
    	NewTranspName.setValue(null);
    	NewWorkerAddress.clear();
    	choosePhoto.setVisible(true);
    	Newalternatives.clear();
    	NewSalary.clear();
    	NewNotes.clear();
    }
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
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
				ImageUrl = Wizard.Imagechooser(NewWorkerImage);
				if (ImageUrl != null)
					
					choosePhoto.setVisible(false);
				// System.out.println(ImageUrl);
			}
		};
		NewWorkerImage.setOnMouseClicked(imageChooser);
		choosePhoto.setOnMouseClicked(imageChooser);
/////////////////////////////////////////////////////////////////////////////////////////
		//add Worker event handler
		EventHandler<Event> editWorker = new EventHandler<Event>() 
				{
			        @Override
		             	public void handle(Event arg0) 
		                      	{
			        	           if(NewWorkerName.getText().trim().length()==0)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم العامل فارغا!").message("الرجاء إدخال اسم العامل").showError();
//			        	   			NewWorkerAddress.setStyle();
			        	             return;
			        	           }
			        	           if(NewJob.getText().trim().length()==0)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead(" لايمكن ترك نوع العمل فارغا!").message("الرجاء  اختيار نوع العمل").showError();
			        	             return;
			        	           }
			        	           //now you can try to add the Worker 
				 types=new ArrayList<>();
			     elements = new ArrayList<>();
//////////////////////////////////////////////////////////////////////////////////////////////
//				construct the editing query
				//12 prepared filed
				
			     String Q = "update workers set Name=?,address=?,phone_number=?,works_as=?,salary=?,alternatives=?,transp_ID=?,transp_fees=?,notes=?,ImageURL=? where id="
    						+selected_id;//
//				                (1,2,3,4,5,6,7,8,9,10,11,12,13,14)
				 {
					 // then prepare the statement by filling the elements and types
					 elements.add(NewWorkerName.getText().trim());
					 types.add(Wizard.String);
					 if(NewWorkerAddress.getText().length()==0)
					   {
						 elements.add("1");
						 types.add(Wizard.NULL);
					   }
					  else 
					   {
						 elements.add(NewWorkerAddress.getText().trim());
						 types.add(Wizard.String);
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
					
					 if(NewJob.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewJob.getText().trim());
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
					
					 if(NewNotes.getText().length()==0)
					   {
						 elements.add("1");
						 types.add(Wizard.NULL);
					   }
					  else 
					   {
						 elements.add(NewNotes.getText().trim());
						 types.add(Wizard.String);
					   }
				     if(ImageUrl!=null)
				    	 { 
				    	 // image was chosen 
				    	 String Imagefile=null;
				    	    try 
				    	    {
				    	    	if(worker.getImageURL()==null) // No previous images were chosen -> make new name
				    	         Imagefile= ("1"+new Date()+".jpg").replace(":","-").replace(" ","");
				    	    	else 
				    	    	{
				    	    		//maintain the same name 
				    	    		Imagefile=worker.getImageURL();
				    	    	}
				    	    	BufferedImage inputImage=ImageIO.read(new File(ImageUrl));
								System.out.println(Wizard.myJarPath()+"/resources/"+Wizard.getFileName(ImageUrl));
						         //replaces the old image if exists 
								ImageIO.write(inputImage,"jpg",new File(Wizard.myJarPath()+"/resources/"+Imagefile));
				    	    }
				    	    catch (IOException e) 
				    	    {
								System.out.println(e.getMessage()+"error copying the image to the root directory class Worker Info Edit");

				    	    }				
				    	    elements.add(Imagefile);
							  types.add(Wizard.String);
				    	 }
				     else
				     {
				    	 // if not changed maintain the old one
					     elements.add(worker.getImageURL());
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
   			        myController.setScreen("Worker_Info","");

				}
				else
					Dialogs.create()
					.owner(null)
					.title("رسالة خطأ")
					.masthead("لم يتم تعديل بيانات العامل")
					.message(
							"الرجاء التأكد من صحة البيانات المدحلة والتأكد من أن الاسم لم يستخدم مسبقا")
					.showWarning();
			     }
		       };

		       ConfirmEditImage.setOnMouseClicked(editWorker);
			   ConfirmEditLabel.setOnMouseClicked(editWorker);
///////////////////////////////////////////////////////////////////////////////////////
	}
	
	@Override
	public void initialize_onshow() 
	{
		clear();
		selected_id = Wizard.global_selected_id;
		worker =Wizard.getWorkerObject(selected_id);
		NewWorkerName.setText(worker.getName());
		if (worker.getphone_number() != null)
			NewPhoneNumber.setText(worker.getphone_number());
		if (worker.getWorksAs() != null)
			NewJob.setText(worker.getWorksAs());
		if (worker.gettransp_fees() != null)
			NewTranspFees.setText(worker.gettransp_fees().toString());
		//Newalternatives
		if (worker.getalternatives() != null)
			Newalternatives.setText(worker.getalternatives().toString());
		//salary 
		if (worker.getSalary() != null)
			NewSalary.setText(worker.getSalary().toString());
		//
		if (worker.getTransp_Name() != null)
			NewTranspName.setValue(worker.getTransp_Name());
		if (worker.getaddress() != null)
			NewWorkerAddress.setText(worker.getaddress());
		if (worker.getNotes() != null)
			NewNotes.setText(worker.getNotes());
		if(worker.getImageURL()!=null)
		{
			System.out.println(worker.getImageURL());
			String temp=Wizard.myJarPath()+"/resources/"+worker.getImageURL();
		    Image image=null;
		    try 
		    {
				BufferedImage bufferedImage = ImageIO.read(new File(temp));
				  image = SwingFXUtils.toFXImage(bufferedImage, null);
			}
		    catch (IOException e) 
		    {
		    	System.out.println(e.getMessage()+" error setting the Worker image ");
			}
			NewWorkerImage.setImage(image);
			
		}
		else
		{
			NewWorkerImage.setImage(Wizard.getDefaultWorkerImage());
		}
		//System.out.println("../"+Worker.getImageURL());
	}

	 @FXML
	    private TextField NewWorkerName;

	    @FXML
	    private TextArea NewWorkerAddress;

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
	    private TextField NewJob;

	    @FXML
	    private ImageView NewWorkerImage;

	    @FXML
	    private ImageView ConfirmEditImage;

	    @FXML
	    private ComboBox<String> NewTranspName;
	    
	    @FXML
	    private TextArea NewNotes;
	
}
