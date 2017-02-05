package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import models.*;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import databaseUtilities.DBUtil;
import databaseUtilities.JDBC;
import wizards.Wizard;
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

public class Transportation_Info extends Controlled_Screen implements Initializable 
{
	int selected_id;
    Transporation Transpoter;
    List<String> types;
	List<String> elements;
	
    public void clear()
    {
    	Destination.setText("لاتوجد بيانات للعرض");
    	Transportation_ID.setText("لاتوجد بيانات للعرض");
 	     Driver.setText("لاتوجد بيانات للعرض");
 	     TransportationFees.setText("لاتوجد بيانات للعرض");
 	     vehicleType.setText("لاتوجد بيانات للعرض");
 	     Students_Total_number.setText("لاتوجد بيانات للعرض");
 	     tabel.setItems(null);
    }
    
    public void clear_Edit_Tab()
    {
 	   NewDestination.clear();
 	   NewDriver.clear();
 	   NewFees.clear();
 	   newvehicle.setValue(null);
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		nextScreen = "Student_Info_Edit";
		ID.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
		ID.setCellFactory(integerCellFactory);
		Name.setCellValueFactory(new PropertyValueFactory<Student, String>("Name"));
		Name.setCellFactory(TransitionEventFactory);
		StudentLevel.setCellValueFactory(new PropertyValueFactory<Student, String>("ClassLevel"));
		StudentLevel.setCellFactory(TransitionEventFactory);
		Student_class.setCellValueFactory(new PropertyValueFactory<Student, String>("ClassName"));
		Student_class.setCellFactory(TransitionEventFactory);
////////////////////////////////////////////////////////////////////////////////////////
    	newvehicle.setItems(Wizard.getVehiclesTypes());
////////////////////////////////////////////////////////////////////////////////////////

//	//delete Class info 
		EventHandler<Event> DeleteTransportation = new EventHandler<Event>()
				{
			
			        @Override
			         public void handle(Event arg0) 
			        {	
			        	Action response = Dialogs.create()
			        	        .owner(null)
			        	        .title("رسالة تأكيد")
			        	        .masthead("")
			        	        .message("هل أنت متأكد من أنك تريد مسح جميع بيانات هذا   الترحيل ؟")
			        	        .actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
			        	        .showConfirm();

			        	if (response == Dialog.ACTION_YES)
			        	{
			        		
			        		Boolean executed=DBUtil.excecuteUpdate("delete from transporation where id ="+selected_id);
			        		if(executed)
			        		{
			        			Dialogs.create()
			        	        .owner(null)
			        	        .title("رسالة إشعار")
			        	        .message("تم حذف بيانات  الترحيل  بنجاح")
			        	        .showInformation();
				        		myController.setScreen("Transportation_Tab","");
				        		Wizard.global_selected_id=1;
				        		return;
			        		}
			        		else
			        		{
			        			Dialogs.create()
								.owner(null)
								.title("رسالة خطأ")
								.masthead("لم يتم حذف بيانات الترحيل")
								.message("الرجاء المحاولة في وقت لاحق")
								.showWarning();
			        		}
			        	}
			        }
		        };
		        DeleteTransportationImage.setOnMouseClicked(DeleteTransportation);
		        DeleteTransportationLabel.setOnMouseClicked(DeleteTransportation);
///////////////////////////////////////////////////////////////////////////////////////
		        //Edit class
		        EventHandler<Event> EditClass = new EventHandler<Event>() 
						{
					        @Override
				             	public void handle(Event arg0) 
				                      	{
					        	if(NewDestination.getText().trim().length()==0)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  وجهة الترحيل فارغة!").message("الرجاء إدخال اسم الفصل ").showError();
//			        	   			NewWorkerAddress.setStyle();
			        	             return;
			        	           }			
						 types=new ArrayList<>();
					     elements = new ArrayList<>();
		//////////////////////////////////////////////////////////////////////////////////////////////
//						construct the insert query
						//12 prepared filed
						
						String Q = "update transporation set Destination=?, Driver_name=?,delivery_cost=?,vehicle_type=? "
								+ "where id="+selected_id;
			              types=new ArrayList<>();
		   			     elements = new ArrayList<>();
						 {
							 // then prepare the statment by filling the elements and types
							 elements.add(NewDestination.getText().trim());
							 types.add(Wizard.String);
/////////////////////////////////////////////////////////////////////////////////////////
							 if(NewDriver.getText().trim().length()==0)
							   {
								 elements.add("1");
								 types.add(Wizard.NULL);
							   }
							 else 
							   {
								 elements.add(NewDriver.getText().trim());
								 types.add(Wizard.String);
							   }
/////////////////////////////////////////////////////////////////////////////////////////
							 if(NewFees.getText().length()==0)
							   {
								 elements.add("1");
								 types.add(Wizard.NULL);
							   }
							  else 
							   {
								 elements.add(NewFees.getText().trim());
								 types.add(Wizard.Double);
							   }
/////////////////////////////////////////////////////////////////////////////////////////
		                    if(newvehicle.getValue()==null)
							 {
								 elements.add("1");
								 types.add(Wizard.NULL);
							 }
							 else if(newvehicle.getValue().length()==0)
							 {
								 elements.add("1");
								 types.add(Wizard.NULL);
							 }
							 else 
							 {
								 elements.add(newvehicle.getValue());
								 types.add(Wizard.String);
							 }
/////////////////////////////////////////////////////////////////////////////////////////

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
							initialize_onshow();// to show the new data
						}
						else
						
								Dialogs.create()
										.owner(null)
										.title("رسالة خطأ")
										.masthead("لم يتم تعديل بيانات الترحيل")
										.message(
												"الرجاء التأكد من صحة البيانات المدحلة والتأكد من أن الاسم لم يستخدم مسبقا")
										.showWarning();
					     }
				       };
				ConfirmEditImage.setOnMouseClicked(EditClass);
				ConfirmEditLabel.setOnMouseClicked(EditClass);
///////////////////////////////////////////////////////////////////////////////////////
	}
	
	@Override
	public void initialize_onshow() 
	{
		clear();
		clear_Edit_Tab();
			selected_id = Wizard.global_selected_id;
			Transpoter = Wizard.getTransportationObject(selected_id);
			
			Destination.setText(Transpoter.getDestination());
			Transportation_ID.setText(Transpoter.getid().toString());
			if (Transpoter.getvehicle_type()!=null)
				vehicleType.setText(Transpoter.getvehicle_type());
			if (Transpoter.getDriver_name() != null)
				Driver.setText(Transpoter.getDriver_name());
			if (Transpoter.getdelivery_cost() != null)
				TransportationFees.setText(Transpoter.getdelivery_cost().toString());
				Students_Total_number.setText(Wizard.TotalStudentsPerTransporter(Transpoter.getid().toString()));
				ObservableList  result =(ObservableList<Student>) JDBC.fill_Otabel("Select * from Student where transp_ID="+selected_id,Wizard.Student,null,null);
				resultcopy=result;
				tabel.setItems(result);
/////////////////////////////////////////////////////////////////////////////////////////////////
				NewDestination.setText(Transpoter.getDestination());
				NewFees.setText(Transpoter.getdelivery_cost().toString());
				NewDriver.setText(Transpoter.getDriver_name());
				newvehicle.setValue(Transpoter.getvehicle_type());
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////

	
    @FXML
    private Label Destination;

    @FXML
    private ImageView DeleteTransportationImage;
    @FXML
    private Label DeleteTransportationLabel;
    @FXML
    private Label ConfirmEditLabel;
    @FXML
    private Label Transportation_ID;
    @FXML
    private Label TransportationFees;
    @FXML
    private Label vehicleType;
    @FXML
    private Label Driver;
/////////////////////////////////////////////////////////////////////////////////////////
//   Edit pane 
    @FXML
    private TextArea NewDestination;
    @FXML
    private TextField NewFees;
    @FXML
    private ComboBox<String> newvehicle;
    @FXML
    private TableView tabel;
    @FXML
    private TableColumn ID;
    @FXML
    private TableColumn  Name;
    @FXML
    private TableColumn StudentLevel;
    @FXML
    private TableColumn Student_class;
    @FXML
    private ImageView ConfirmEditImage;
    @FXML
    private Label Students_Total_number;
    @FXML
    private TextField NewDriver;

/////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
}
