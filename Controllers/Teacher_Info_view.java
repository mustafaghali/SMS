package Controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import models.*;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import databaseUtilities.DBUtil;
import wizards.Wizard;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Teacher_Info_view extends Controlled_Screen implements Initializable 
{
	int selected_id;
    Teacher teacher;
    
    public void clear()
    {
//    	 ImageUrl = null;
 	   Teacher_name.setText("لاتوجد بيانات للعرض");
 	   Teacher_ID.setText("لاتوجد بيانات للعرض");
 	   TeacherImage.setImage(Wizard.getDefaultTeacherImage());
    	TeacherClass.setText("لاتوجد بيانات للعرض");
    	Teacher_phone.setText("لاتوجد بيانات للعرض");
    	Teacher_Email.setText("لاتوجد بيانات للعرض");
         Teacher_Address.setText("لاتوجد بيانات للعرض");
         alternatives.setText("لاتوجد بيانات للعرض");
         Salary.setText("لاتوجد بيانات للعرض");
         Transp_fees.setText("لاتوجد بيانات للعرض");
        Teacher_Address.setText("لاتوجد بيانات للعرض");
        Transp_Name.setText("لاتوجد بيانات للعرض");        
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
////////////////////////////////////////////////////////////////////////////////////////
//	//delete Teacher info 
		EventHandler<Event> DeleteTeacher = new EventHandler<Event>()
				{
			
			        @Override
			         public void handle(Event arg0) 
			        {	
			        	Action response = Dialogs.create()
			        	        .owner(null)
			        	        .title("رسالة تأكيد")
			        	        .masthead("")
			        	        .message("هل أنت متأكد من أنك تريد مسح جميع بيانات هذا الطالب ؟")
			        	        .actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
			        	        .showConfirm();

			        	if (response == Dialog.ACTION_YES)
			        	{
			        		
			        		Boolean executed=DBUtil.excecuteUpdate("delete from Teacher where id ="+selected_id);
			        		if(executed)
			        		{
			        			Dialogs.create()
			        	        .owner(null)
			        	        .title("رسالة إشعار")
			        	        .message("تم حذف بيانات  الطالب بنجاح")
			        	        .showInformation();
				        		myController.setScreen("Teacher_Tab","");
				        		Wizard.global_selected_id=1;
				        		return;
	
			        		}
			        		else
			        		{
			        			Dialogs.create()
								.owner(null)
								.title("رسالة خطأ")
								.masthead("لم يتم حذف بيانات الطالب")
								.message("الرجاء المحاولة في وقت لاحق")
								.showWarning();
			        		}
			        	}
			        }
		        };
		        DeleteTeacherImage.setOnMouseClicked(DeleteTeacher);
		        DeleteTeacherLabel.setOnMouseClicked(DeleteTeacher);
////////////////////////////////////////////////////////////////////////////////////////\
		        TeacherImage.setOnMouseClicked(Wizard.openImageViewer);

	}
	
	@Override
	public void initialize_onshow() 
	{
		clear();
			selected_id = Wizard.global_selected_id;
			//System.out.println(selected_id);
			teacher = Wizard.getTeacherObject(selected_id);
			if (teacher==null)
				System.out.println("null teacher object"
						+ "");
			Teacher_name.setText(teacher.getName());
			Teacher_ID.setText(teacher.getid().toString());
			if (teacher.getphone_number() != null)
				Teacher_phone.setText(teacher.getphone_number());
			if (teacher.getEmail_Address() != null)
				Teacher_Email.setText(teacher.getEmail_Address());
			if (teacher.getClassName() != null)
				TeacherClass.setText(teacher.getClassName());
			if (teacher.getaddress() != null)
				Teacher_Address.setText(teacher.getaddress());
			if (teacher.gettransp_fees() != null)
				Transp_fees.setText(teacher.gettransp_fees().toString());
			if (teacher.getSalary() != null)
				Salary.setText(teacher.getSalary().toString());
			if (teacher.getalternatives() != null)
				alternatives.setText(teacher.getalternatives().toString());
			if (teacher.getTransp_Name() != null)
				Transp_Name.setText(teacher.getTransp_Name());
			if (teacher.getaddress() != null)
				Teacher_Address.setText(teacher.getaddress());
			if(teacher.getImageURL()!=null)
			{
				System.out.println(teacher.getImageURL());
				String temp=Wizard.myJarPath()+"/resources/"+teacher.getImageURL();
			    //System.out.println(temp);
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
				TeacherImage.setImage(image);
				Wizard.global_Image_Path = temp;
			}
			else
			{
				Wizard.global_Image_Path = null;
			}
		}
	
	@FXML
    private Label Transp_fees;
	@FXML
    private Label Salary;
	@FXML
    private TextArea Teacher_Address;
	@FXML
    private Label Teacher_ID;
    @FXML
    private Label Teacher_phone;
    @FXML
    private Label DeleteTeacherLabel;
    @FXML
    private ImageView DeleteTeacherImage;
	@FXML
	private Label Teacher_name;
	@FXML
    private Label Teacher_Email;
    @FXML
    private Label alternatives;
    @FXML
    private TextArea Transp_Name;
    @FXML
    private Label TeacherClass;
    @FXML
	private ImageView TeacherImage ;
	@FXML
	public void EditCliked(Event event)
	{
		myController.setScreen("Teacher_Info_Edit","");
	}
	
	
	
}
