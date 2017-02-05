package Controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.CommandLinksDialog;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import databaseUtilities.DBUtil;
import models.Student;
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

public class Student_Info_view extends Controlled_Screen implements Initializable 
{
       int selected_id;
       Student student;
       
    public void clear()
       {
//       	 ImageUrl = null;
        Student_name.setText("لاتوجد بيانات للعرض");
        Student_ID.setText("لاتوجد بيانات للعرض");
   	    StudentImage.setImage(Wizard.getDefaultStudentImage());
       	StudentClass.setText("لاتوجد بيانات للعرض");
       	Student_phone.setText("لاتوجد بيانات للعرض");
       	Student_B_date.setText("لاتوجد بيانات للعرض");
       	Student_Email.setText("لاتوجد بيانات للعرض");
       	StudentFatherPhone.setText("لاتوجد بيانات للعرض");
       	Student_fees.setText("لاتوجد بيانات للعرض");
       	StudentFatherSeetsfees.setText("لاتوجد بيانات للعرض");
       	Student_transp_fees.setText("لاتوجد بيانات للعرض");
       	Summer_Course_fees.setText("لاتوجد بيانات للعرض");
       	Student_Transp.setText("لاتوجد بيانات للعرض");
       	Student_type.setText("لاتوجد بيانات للعرض");
       	Student_Health_Status.setText("لاتوجد بيانات للعرض");
        Student_Level.setText("لاتوجد بيانات للعرض");
        Student_specialization.setText("لاتوجد بيانات للعرض");
        Student_supervisor.setText("لاتوجد بيانات للعرض");
        Student_type.setText("لاتوجد بيانات للعرض");
        Student_Health_Status.setText("لاتوجد بيانات للعرض");
        Student_Address.setText("لاتوجد بيانات للعرض");
       }
   	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
////////////////////////////////////////////////////////////////////////////////////////
//	//delete student info 
		EventHandler<Event> DeleteStudent = new EventHandler<Event>()
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
			        		
			        		Boolean executed=DBUtil.excecuteUpdate("delete from student where id ="+selected_id);
			        		if(executed)
			        		{
			        			Dialogs.create()
			        	        .owner(null)
			        	        .title("رسالة إشعار")
			        	        .message("تم حذف بيانات  الطالب بنجاح")
			        	        .showInformation();
				        		myController.setScreen("Student_Tab","");
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
		        DeleteStudentImage.setOnMouseClicked(DeleteStudent);
		        DeleteStudentLabel.setOnMouseClicked(DeleteStudent);
		        
		        StudentImage.setOnMouseClicked(Wizard.openImageViewer);
		        
		        
////////////////////////////////////////////////////////////////////////////////////////
		        
	}
	
	@Override
	public void initialize_onshow()
	{
		clear();
			selected_id = Wizard.global_selected_id;
			student = Wizard.getStudentObject(selected_id);
			if (student==null)
				System.out.println("null");
			Student_name.setText(student.getName());
			Student_ID.setText(student.getid().toString());
			if (student.getB_Date() != null)
				Student_B_date.setText(student.getB_Date());
			if (student.getphone_number() != null)
				Student_phone.setText(student.getphone_number());
			if (student.getClassLevel() != null)
				Student_Level.setText(student.getClassLevel());
			if (student.getEmail_Address() != null)
				Student_Email.setText(student.getEmail_Address());
			if (student.getClassName() != null)
				StudentClass.setText(student.getClassName());
			if (student.getfather_phone_number() != null)
				StudentFatherPhone.setText(student.getfather_phone_number());
			if (student.getfather_seets_fees() != null)
				StudentFatherSeetsfees.setText(student.getfather_seets_fees()
						.toString());
			if (student.getaddress() != null)
				Student_Address.setText(student.getaddress());
			if (student.getStudying_fees() != null)
				Student_fees.setText(student.getStudying_fees().toString());
			if (student.getspecialization() != null)
				Student_specialization.setText(student.getspecialization());
			if (student.getsummer_course_fees() != null)
			    Summer_Course_fees.setText(student.getsummer_course_fees().toString());
			if (student.gettransp_fees() != null)
				Student_transp_fees.setText(student.gettransp_fees().toString());
			if (student.getTransp_Name() != null)
				Student_Transp.setText(student.getTransp_Name());
			if (student.getSupervisorName() != null)
				Student_supervisor.setText(student.getSupervisorName());
			if (student.getStudent_type() != null)
				Student_type.setText(student.getStudent_type());
			if (student.getHealth_Status() != null)
				Student_Health_Status.setText(student.getHealth_Status());
			if (student.getaddress() != null)
				Student_Address.setText(student.getaddress());
			if(student.getImageURL()!=null)
			{
				//System.out.println(student.getImageURL());
				String temp=Wizard.myJarPath()+"/resources/"+student.getImageURL();
			    //System.out.println(temp);
			    Image image=null;
			    try 
			    {
					BufferedImage bufferedImage = ImageIO.read(new File(temp));
					  image = SwingFXUtils.toFXImage(bufferedImage, null);
				}
			    catch (IOException e) 
			    {
			    	System.out.println(e.getMessage()+" error setting the student image ");
				}
				StudentImage.setImage(image);	
				Wizard.global_Image_Path = temp;
			}
			else
			{
				Wizard.global_Image_Path = null;
			}
	}
	
	@FXML
	private Label Student_name;
	@FXML
	private Label Student_ID;
	@FXML
	private Label Student_B_date;
	@FXML
	private Label Student_phone ;
	@FXML
	private Label Student_Level;
	@FXML
	private Label Student_Email;
	@FXML
	private Label StudentClass;
	@FXML
	private Label StudentFatherPhone;
	@FXML
	private Label Student_fees;
	@FXML
	private Label StudentFatherSeetsfees;
	@FXML
	private Label Summer_Course_fees;
	@FXML
	private Label Student_transp_fees;
	@FXML
	private Label Student_specialization;
	@FXML
	private TextArea Student_Transp;
	@FXML
	private Label Student_supervisor;
	@FXML
	private Label Student_type;
	@FXML
	private TextArea Student_Health_Status;
	@FXML
	private TextArea Student_Address ;
	@FXML 
	private Label DeleteStudentLabel;
	@FXML
	private ImageView DeleteStudentImage;
	@FXML
	private ImageView StudentImage ;
	@FXML
	public void EditCliked(Event event)
	{
		myController.setScreen("Student_Info_Edit","");
	}
}
