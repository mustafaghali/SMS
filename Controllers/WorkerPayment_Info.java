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
import wizards.Wizard;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class WorkerPayment_Info extends Controlled_Screen implements Initializable 
{
    WorkerPayment WorkerPaymentObject;
    List<String> types;
    List<String> elements;
    
    public void clear()
    {
    	
 	  Type.setText("لاتوجد بيانات للعرض");
 	  Date.setText("لاتوجد بيانات للعرض");
 	  Name.setText("لاتوجد بيانات للعرض");
 	  Job.setText("لاتوجد بيانات للعرض"); 
 	  Value.setText("لاتوجد بيانات للعرض");  
 	  Details.clear();
    }
    public void clearOnEdit()
    {
    	NewName.setValue(null);
    	NewDate.setValue(null);
    	NewType.setValue(null);
    	NewDetails.clear();
    	NewValue.clear();
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
	    NewType.setItems(Wizard.WorkerPaymentsTypesforComboBox);
		NewDate.setConverter(Wizard.DatePickerConverter());
////////////////////////////////////////////////////////////////////////////////////////
//	//delete WorkerPayment info 
		EventHandler<Event> DeleteWorkerPayment = new EventHandler<Event>()
				{
			
	        @Override
	         public void handle(Event arg0) 
	        {		Action response = Dialogs.create()
	        .owner(null)
	        .title("رسالة تأكيد")
	        .masthead("")
	        .message("هل أنت متأكد من أنك تريد مسح  بيانات هذه الدفعية ؟")
	        .actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
	        .showConfirm();

	if (response == Dialog.ACTION_YES)
	{
		String Q = "delete from  Workers_transactions where Type = ? and the_date = ? and Value = ? and Worker_ID = ?";

		types = new ArrayList<>();
		elements = new ArrayList<>();
		elements.add(Wizard.WorkerPaymentTypeConverter(WorkerPaymentObject.gettype()));
		types.add(Wizard.String);
		
		elements.add(WorkerPaymentObject.getdate());
		types.add(Wizard.Date);
		
		elements.add(WorkerPaymentObject.getValue().toString());
		types.add(Wizard.Double);
		
		elements.add(WorkerPaymentObject.getWorkerID().toString());
		types.add(Wizard.Integer);
		
		Boolean executed=DBUtil.excecuteUpdate(Q,elements,types);
		if(Wizard.numberOfUpdatedRecords !=0)
		{
			Dialogs.create()
	        .owner(null)
	        .title("رسالة إشعار")
	        .message("تم حذف بيانات  الدفعية بنجاح")
	        .showInformation();
    		myController.setScreen("Financials","");
    		return;
		}
		else
		{
			Dialogs.create()
			.owner(null)
			.title("رسالة خطأ")
			.masthead("لم يتم حذف بيانات الدفعية")
			.message("الرجاء المحاولة في وقت لاحق")
			.showWarning();
		}
	}
	        }
				};
		        DeleteWorkerImage.setOnMouseClicked(DeleteWorkerPayment);
		        DeleteWorkerLabel.setOnMouseClicked(DeleteWorkerPayment);
////////////////////////////////////////////////////////////////////////////////////////
		        EventHandler<Event> EditWorkerPayment = new EventHandler<Event>() 
						{
					        @Override
				             	public void handle(Event arg0) 
				                      	{
					        	   if (NewName.getValue() == null)
			        	             {
			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  اسم العامل  فارغا!").message("الرجاء  تحديد اسم العامل ").showError();
				        	             return;
			        	             }
			        	             else if (NewName.getValue().toString().trim().length()==0)
			        	             {
			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  اسم العامل  فارغا!").message("الرجاء  تحديد اسم العامل ").showError();
				        	             return; 
			        	             }
              					   if (NewDate.getValue() == null)
			        	             {
			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  تاريخ الدفعية  فارغا!").message("الرجاء  تحديد تاريخ الدفعية ").showError();
				        	             return;
			        	             }
			        	             else if (NewDate.getValue().toString().trim().length()==0)
			        	             {
			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  تاريخ الدفعية  فارغا!").message("الرجاء  تحديد الدفعية الإصدار ").showError();
				        	             return; 
			        	             }
              					 if (NewValue.getText().trim().length() == 0)
  			        	         {
  			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  قيمة الدفعية  فارغة!").message("الرجاء  تحديد قيمة الدفعية ").showError();
  				        	             return;
  			        	         }
              				
  			        	          types=new ArrayList<>();
	                    		  elements = new ArrayList<>();
		//////////////////////////////////////////////////////////////////////////////////////////////
//						construct the update query								
						String Q = "update  Workers_transactions set Worker_ID = ? , the_date = ? , Value = ? , type = ? , Notes = ? "
								+ "where Worker_ID = ? and the_date = ? and   Value = ? and type = ?";
						
			             types=new ArrayList<>();
		   			     elements = new ArrayList<>();
		   			     
		   			     WorkerPayment tempPayment = new WorkerPayment();
		   			// then prepare the statment by filling the elements and
							// types
		   			     {	
		   			    	
		   			    	 elements.add(Wizard.getWorkerID(NewName.getValue()));
							 types.add(Wizard.Integer);
							 tempPayment.setWorkerID(Integer.parseInt(Wizard.getWorkerID(NewName.getValue())));
							 tempPayment.setWorkerName(NewName.getValue());
							 Worker tempworker  = Wizard.getWorkerObject(tempPayment.getWorkerID());
							 tempPayment.setJobType(tempworker.getWorksAs());
							 
							 elements.add(NewDate.getValue().toString().trim());
							 types.add(Wizard.Date);
							 tempPayment.setdate(NewDate.getValue().toString().trim());
							 
							 
							  
							 elements.add(NewValue.getText().trim());
							 types.add(Wizard.Double);
							 tempPayment.setValue(Double.parseDouble(NewValue.getText().trim()));

							 if(NewType.getValue()==null)
							   {
								 elements.add("1");
								 types.add(Wizard.NULL);
								 tempPayment.settype("Other");
							   }
							 else if(NewType.getValue().length()==0)
							 {
								 elements.add("1");
								 types.add(Wizard.NULL);
								 tempPayment.settype("Other");
							 }
							  else 
							  {
								elements.add(Wizard.WorkerPaymentTypeConverter(NewType.getValue()));
							   types.add(Wizard.String);
								 tempPayment.settype(NewType.getValue());

							  }
							 
							  if(NewDetails.getText().trim().length()==0)
							 {
								 elements.add("1");
								 types.add(Wizard.NULL);
								 tempPayment.setNotes(null);

							 }
							 else 
							 {
								elements.add(NewDetails.getText().trim());
							   types.add(Wizard.String);
							   tempPayment.setNotes(NewDetails.getText().trim());
							 }
							
						// where clause elements 
							 
						    elements.add(WorkerPaymentObject.getWorkerID().toString());
						    types.add(Wizard.Integer);

						    
							elements.add(WorkerPaymentObject.getdate());
							types.add(Wizard.Date);
							   
							
							elements.add(WorkerPaymentObject.getValue().toString());
							types.add(Wizard.Double);
							
							elements.add(Wizard.WorkerPaymentTypeConverter(WorkerPaymentObject.gettype()));
							types.add(Wizard.String);
						}
		   			     
							Boolean executed = DBUtil.excecuteUpdate(Q,elements,types);
						if (Wizard.numberOfUpdatedRecords!=0)
						{
							Dialogs.create()
							.owner(null)
							.title("رسالة إشعار")
							.masthead(null)
							.message("تمت عملية التعديل بنجاح")
							.showInformation();
							Wizard.global_selected_WorkerPayment = tempPayment;
							clearOnEdit();
							initialize_onshow();// to show the new data
							
						}
						else
						
							Dialogs.create()
							.owner(null)
							.title("رسالة خطأ")
							.masthead("لم يتم تعديل بيانات الدفعية")
							.message(
									"الرجاء التأكد من صحة البيانات المدحلة أو التغيير في تفاصيل النتيجة لتفادي تطابق الدفعيات")
							.showWarning();
					     }
				       };
///////////////////////////////////////////////////////////////////////////////////////
				ConfirmEditImage.setOnMouseClicked(EditWorkerPayment);
				ConfirmEditLabel.setOnMouseClicked(EditWorkerPayment);
///////////////////////////////////////////////////////////////////////////////////////

				EventHandler<Event> PrintWorkerPayment = new EventHandler<Event>()
						{
					
					        @Override
					         public void handle(Event arg0) 
					        {	
					        	Wizard.CreateWorkerPaymentReport(WorkerPaymentObject);
					        }
						};
///////////////////////////////////////////////////////////////////////////////////////
                 PrintImage.setOnMouseClicked(PrintWorkerPayment);
                 PrintLabel.setOnMouseClicked(PrintWorkerPayment);
///////////////////////////////////////////////////////////////////////////////////////

	}
	
	@Override
	public void initialize_onshow() 
	{
        NewType.setItems(Wizard.WorkerPaymentsTypesforComboBox);
	    //NewName.setItems(Wizard.getWorkersNames());

		clear();
		clearOnEdit();
		WorkerPaymentObject = Wizard.global_selected_WorkerPayment;
		
		    if(WorkerPaymentObject.gettype()!=null)
			Type.setText(WorkerPaymentObject.gettype());
		    NewType.setValue(WorkerPaymentObject.gettype());
		    Name.setText(WorkerPaymentObject.getWorkerName());
            Job.setText(WorkerPaymentObject.getJobType());	
            NewName.setValue(WorkerPaymentObject.getWorkerName());
			Date.setText(WorkerPaymentObject.getdate());
			NewDate.setValue(Wizard.ToLocalDate(WorkerPaymentObject.getdate()));
			Value.setText(WorkerPaymentObject.getValue().toString());
			NewValue.setText(WorkerPaymentObject.getValue().toString());
			if(WorkerPaymentObject.getNotes()!=null)
			{
				Details.setText(WorkerPaymentObject.getNotes());
				NewDetails.setText(WorkerPaymentObject.getNotes());
			}
			
		}
	
//////////////////////////////////////////////////////////////////////////////////////////////

	@FXML
    private ComboBox<String> NewType;

    @FXML
    private DatePicker NewDate;

    @FXML
    private ImageView DeleteWorkerImage;

    @FXML
    private Label ConfirmEditLabel;

    @FXML
    private Label Date;

    @FXML
    private Label Name;

    @FXML
    private Label DeleteWorkerLabel;

    @FXML
    private Label Type;

    @FXML
    private TextArea Details;

    @FXML
    private Label Value;

    @FXML
    private Label Job;

    @FXML
    private ComboBox<String> NewName;

    @FXML
    private TextField NewValue;

    @FXML
    private ImageView ConfirmEditImage;
    @FXML
    private TextArea NewDetails;
    @FXML
    private Label PrintLabel;
    @FXML
    private  ImageView PrintImage;
 //////////////////////////////////////////////////////////////////////////////////////////////
}
