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

public class Worker_Info_view extends Controlled_Screen implements Initializable 
{
	int selected_id;
    Worker workerObject;
    
    public void clear()
    {
 	   Worker_name.setText("لاتوجد بيانات للعرض");
 	   Worker_ID.setText("لاتوجد بيانات للعرض");
 	   WorkerImage.setImage(Wizard.getDefaultWorkerImage());
 	   Works_As.setText("لاتوجد بيانات للعرض");
    	Worker_phone.setText("لاتوجد بيانات للعرض");
         Worker_Address.setText("لاتوجد بيانات للعرض");
         alternatives.setText("لاتوجد بيانات للعرض");
         Salary.setText("لاتوجد بيانات للعرض");
         Transp_fees.setText("لاتوجد بيانات للعرض");
        Worker_Address.setText("لاتوجد بيانات للعرض");
        Transp_Name.setText("لاتوجد بيانات للعرض");  
        Notes.setText("لاتوجد بيانات للعرض");
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
////////////////////////////////////////////////////////////////////////////////////////
//	//delete Worker info 
		EventHandler<Event> DeleteWorker = new EventHandler<Event>()
				{
			
			        @Override
			         public void handle(Event arg0) 
			        {	
			        	Action response = Dialogs.create()
			        	        .owner(null)
			        	        .title("رسالة تأكيد")
			        	        .masthead("")
			        	        .message("هل أنت متأكد من أنك تريد مسح جميع بيانات هذا  العامل ؟")
			        	        .actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
			        	        .showConfirm();

			        	if (response == Dialog.ACTION_YES)
			        	{
			        		
			        		Boolean executed=DBUtil.excecuteUpdate("delete from workers where id ="+selected_id);
			        		if(executed)
			        		{
			        			Dialogs.create()
			        	        .owner(null)
			        	        .title("رسالة إشعار")
			        	        .message("تم حذف بيانات  العامل بنجاح")
			        	        .showInformation();
				        		myController.setScreen("Worker_Tab","");
				        		Wizard.global_selected_id=1;
				        		return;
			        		}
			        		else
			        		{
			        			Dialogs.create()
								.owner(null)
								.title("رسالة خطأ")
								.masthead("لم يتم حذف بيانات العامل")
								.message("الرجاء المحاولة في وقت لاحق")
								.showWarning();
			        		}
			        	}
			        }
		        };
		        DeleteWorkerImage.setOnMouseClicked(DeleteWorker);
		        DeleteWorkerLabel.setOnMouseClicked(DeleteWorker);
////////////////////////////////////////////////////////////////////////////////////////
		        WorkerImage.setOnMouseClicked(Wizard.openImageViewer);

	}
	
	@Override
	public void initialize_onshow() 
	{
		clear();
			selected_id = Wizard.global_selected_id;
			workerObject = Wizard.getWorkerObject(selected_id);
			if (workerObject==null)
				System.out.println("null Worker object"
						+ "");
			Worker_name.setText(workerObject.getName());
			Worker_ID.setText(workerObject.getid().toString());
			if (workerObject.getWorksAs()!=null)
				Works_As.setText(workerObject.getWorksAs());
			if (workerObject.getphone_number() != null)
				Worker_phone.setText(workerObject.getphone_number());
			if (workerObject.getaddress() != null)
				Worker_Address.setText(workerObject.getaddress());
			if (workerObject.gettransp_fees() != null)
				Transp_fees.setText(workerObject.gettransp_fees().toString());
			if (workerObject.getSalary() != null)
				Salary.setText(workerObject.getSalary().toString());
			if (workerObject.getalternatives() != null)
				alternatives.setText(workerObject.getalternatives().toString());
			if (workerObject.getTransp_Name() != null)
				Transp_Name.setText(workerObject.getTransp_Name());
			if (workerObject.getaddress() != null)
				Worker_Address.setText(workerObject.getaddress());
			if (workerObject.getNotes() != null)
				Notes.setText(workerObject.getNotes());
			if(workerObject.getImageURL()!=null)
			{
				System.out.println(workerObject.getImageURL());
				String temp=Wizard.myJarPath()+"/resources/"+workerObject.getImageURL();
			    Image image=null;
			    try 
			    {
			    	//show the associated image 
					BufferedImage bufferedImage = ImageIO.read(new File(temp));
					  image = SwingFXUtils.toFXImage(bufferedImage, null);
				}
			    catch (IOException e) 
			    {
			    	System.out.println(e.getMessage()+" error setting the Worker image ");
				}
				WorkerImage.setImage(image);
				
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
    private TextArea Worker_Address;
	@FXML
    private TextArea Notes;
	@FXML
    private Label Worker_ID;
    @FXML
    private Label Worker_phone;
    @FXML
    private Label DeleteWorkerLabel;
    @FXML
    private ImageView DeleteWorkerImage;
	@FXML
	private Label Worker_name;
	@FXML
    private Label Worker_Email;
    @FXML
    private Label alternatives;
    @FXML
    private Label Transp_Name;
    @FXML
    private Label Works_As;
    @FXML
	private ImageView WorkerImage ;
	@FXML
	public void EditCliked(Event event)
	{
		myController.setScreen("Worker_Info_Edit","");
	}
	
	
	
}
