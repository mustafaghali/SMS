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


public class StudentPayment_Info extends Controlled_Screen implements Initializable 
{
	 List<String> types;
	 List<String> elements;
		
    StudentPayment StudentPaymentObject;
    
    public void clear()
    {
    	
 	  Type.setText("لاتوجد بيانات للعرض");
 	  Date.setText("لاتوجد بيانات للعرض");
 	  StudentName.setText("لاتوجد بيانات للعرض");
 	  Level.setText("لاتوجد بيانات للعرض");    
 	  Class.setText("لاتوجد بيانات للعرض"); 
 	  Value.setText("لاتوجد بيانات للعرض");  
 	  Details.clear();
    }
 
    public void clearOnEdit()
    {
    	NewClass.setValue(null);
    	NewName.setValue(null);
    	NewDate.setValue(null);
    	NewType.setValue(null);
    	NewDetails.clear();
    	NewValue.clear();
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
		NewDate.setConverter(Wizard.DatePickerConverter());
////////////////////////////////////////////////////////////////////////////////////////
//	//delete StudentPayment info 
		EventHandler<Event> DeleteStudentPayment = new EventHandler<Event>()
				{
			
			        @Override
			         public void handle(Event arg0) 
			        {	
			        	Action response = Dialogs.create()
	        	        .owner(null)
	        	        .title("رسالة تأكيد")
	        	        .masthead("")
	        	        .message("هل أنت متأكد من أنك تريد مسح  بيانات هذه الدفعية ؟")
	        	        .actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
	        	        .showConfirm();

	        	if (response == Dialog.ACTION_YES)
	        	{
	        		String Q = "delete from  student_transactions where Type = ? and the_date = ? and Value = ? and student_ID = ?";

					types = new ArrayList<>();
					elements = new ArrayList<>();
	        		elements.add(Wizard.StudentPaymentTypeConverter(StudentPaymentObject.gettype()));
					types.add(Wizard.String);
					
					elements.add(StudentPaymentObject.getdate());
					types.add(Wizard.Date);
					
					elements.add(StudentPaymentObject.getValue().toString());
					types.add(Wizard.Double);
					
					elements.add(StudentPaymentObject.getStudentID().toString());
					types.add(Wizard.Integer);
					
	        		Boolean executed=DBUtil.excecuteUpdate(Q,elements,types);
	        		if(executed)
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
		        DeletePaymentImage.setOnMouseClicked(DeleteStudentPayment);
		        DeleteResultLabel.setOnMouseClicked(DeleteStudentPayment);
////////////////////////////////////////////////////////////////////////////////////////
		        EventHandler<Event> EditStudentPayment = new EventHandler<Event>() 
						{
					        @Override
				             	public void handle(Event arg0) 
				                      	{
					        	   if (NewName.getValue() == null)
			        	             {
			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  اسم الطالب  فارغا!").message("الرجاء  تحديد اسم الطالب ").showError();
				        	             return;
			        	             }
			        	             else if (NewName.getValue().toString().trim().length()==0)
			        	             {
			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  اسم الطالب  فارغا!").message("الرجاء  تحديد اسم الطالب ").showError();
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
						String Q = "update  Student_transactions set Student_ID = ? , the_date = ? , type = ? , Value = ?,Notes = ? "
								+ "where Student_ID = ? and the_date = ? and  type = ? and Value = ? ";
						
			             types=new ArrayList<>();
		   			     elements = new ArrayList<>();
		   			     
		   			     StudentPayment tempPayment = new StudentPayment();
		   			// then prepare the statment by filling the elements and
							// types
		   			     {	
		   			    	
		   			    	 elements.add(Wizard.getStudentID(NewName.getValue()));
							 types.add(Wizard.Integer);
							 tempPayment.setStudentID(Integer.parseInt(Wizard.getStudentID(NewName.getValue())));
							 tempPayment.setStudentName(NewName.getValue());
							 Student tempstudent  = Wizard.getStudentObject(tempPayment.getStudentID());
							 tempPayment.setStudentClassName(Wizard.getStudentClassName(tempstudent.getClass_ID()));
							 
							 elements.add(NewDate.getValue().toString().trim());
							 types.add(Wizard.Date);
							 tempPayment.setdate(NewDate.getValue().toString().trim());
							 
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
								elements.add(Wizard.StudentPaymentTypeConverter(NewType.getValue()));
							   types.add(Wizard.String);
								 tempPayment.settype(NewType.getValue());

							  }
							  
							 elements.add(NewValue.getText().trim());
							 types.add(Wizard.Double);
							 tempPayment.setValue(Double.parseDouble(NewValue.getText().trim()));

							 
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
							 
						    elements.add(StudentPaymentObject.getStudentID().toString());
						    types.add(Wizard.Integer);

						    
							elements.add(StudentPaymentObject.getdate());
							types.add(Wizard.Date);
							   
							elements.add(Wizard.StudentPaymentTypeConverter(StudentPaymentObject.gettype()));
							types.add(Wizard.String);
							
							elements.add(StudentPaymentObject.getValue().toString());
							types.add(Wizard.Double);
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
							Wizard.global_selected_StudentPayment = tempPayment;
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
				ConfirmEditImage.setOnMouseClicked(EditStudentPayment);
				ConfirmEditLabel.setOnMouseClicked(EditStudentPayment);
///////////////////////////////////////////////////////////////////////////////////////
				EventHandler<Event> PrintStudentPayment = new EventHandler<Event>()
						{
					
					        @Override
					         public void handle(Event arg0) 
					        {	
					        	Wizard.CreateStudentPaymentReport(StudentPaymentObject);
					        }
						};
                 PrintImage.setOnMouseClicked(PrintStudentPayment);
                 PrintLabel.setOnMouseClicked(PrintStudentPayment);
				
	}
	
	@Override
	public void initialize_onshow() 
	{
//////////////////////////////////////////////////////////////////////////////////////////////
		NewType.setItems(Wizard.STudentPaymentsTypesforComboBox);
	    NewClass.setItems(Wizard.getStudentClassesForCombobox());
	    
		clear();
		clearOnEdit();
		StudentPaymentObject = Wizard.global_selected_StudentPayment;
		
		    if(StudentPaymentObject.gettype()!=null)
			Type.setText(StudentPaymentObject.gettype());
		    NewType.setValue(StudentPaymentObject.gettype());
		    StudentName.setText(StudentPaymentObject.getStudentName());
			Class.setText(StudentPaymentObject.getStudentClassName());
			NewClass.setValue(StudentPaymentObject.getStudentClassName());
		    NewName.setItems(Wizard.getStudentsperClassForCombobox(Wizard.getClassID(StudentPaymentObject.getStudentClassName())));
		    NewName.setValue(StudentPaymentObject.getStudentName());
			Level.setText(Wizard.getClassLevel(Integer.parseInt(Wizard.getClassID(StudentPaymentObject.getStudentClassName()))));
			Date.setText(StudentPaymentObject.getdate());
			NewDate.setValue(Wizard.ToLocalDate(StudentPaymentObject.getdate()));
			Value.setText(StudentPaymentObject.getValue().toString());
			NewValue.setText(StudentPaymentObject.getValue().toString());
			if(StudentPaymentObject.getNotes()!=null)
			{
				Details.setText(StudentPaymentObject.getNotes());
				NewDetails.setText(StudentPaymentObject.getNotes());
			}
			
		}
	
//////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private ComboBox<String> NewType;

    @FXML
    private DatePicker NewDate;

    @FXML
    private ImageView DeletePaymentImage;

    @FXML
    private Label ConfirmEditLabel;

    @FXML
    private Label DeleteResultLabel;

    @FXML
    private Label Date;

    @FXML
    private Label Type;

    @FXML
    private TextArea Details;

    @FXML
    private Label Value;

    @FXML
    private Label Level;

    @FXML
    private Label Class;

    @FXML
    private ComboBox<String> NewName;

    @FXML
    private TextField NewValue;

    @FXML
    private ImageView ConfirmEditImage;

    @FXML
    private ComboBox<String> NewClass;

    @FXML
    private Label StudentName;

    @FXML
    private TextArea NewDetails;
    
    @FXML
    private Label PrintLabel;
    
    @FXML
    private  ImageView PrintImage;
    
//////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private void handlNewStudentPaymentAction()
    {
      String selectedItem = NewClass.getSelectionModel().getSelectedItem();
      if(selectedItem == null)
      {
    	  NewName.setValue(null);
    	  NewName.setItems(null);
    	  NewName.setPromptText("الرجاء تحديد  فصل الطالب أولا");
      }
      else
      {
    	  NewName.setItems(Wizard.getStudentsperClassForCombobox(Wizard.getClassID(selectedItem)));
    	  NewName.setPromptText("");
      }
    }
}
