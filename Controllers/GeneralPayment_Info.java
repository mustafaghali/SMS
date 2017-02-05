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


public class GeneralPayment_Info extends Controlled_Screen implements Initializable 
{
    GeneralPayment GeneralPaymentObject;
    List<String> types;
	List<String> elements;

	
    public void clear()
    {
    	
 	  PaymentType.setText("لاتوجد بيانات للعرض");
 	  Date.setText("لاتوجد بيانات للعرض");
 	  Party.setText("لاتوجد بيانات للعرض");
 	  Details.clear();
 	  Value.setText("لاتوجد بيانات للعرض");       
    }
    public void clearOnEdit()
    {
    	NewType.setValue(null);
    	newparty.setValue(null);
    	NewDate.setValue(null);
    	NewDetails.clear();
    	NewValue.clear();
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
		NewDate.setConverter(Wizard.DatePickerConverter());
////////////////////////////////////////////////////////////////////////////////////////
//	//delete GeneralPayment info 
		EventHandler<Event> DeleteGeneralPayment = new EventHandler<Event>()
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
			        		String Q = "delete from  general_transactions where Type = ? and Party = ? and Date = ? and Value = ? and notes = ?";

							types = new ArrayList<>();
							elements = new ArrayList<>();
			        		elements.add(Wizard.GeneralPaymentTypeConverter(GeneralPaymentObject.gettype()));
							types.add(Wizard.String);
							elements.add(Wizard.GeneralPaymentPartyConverter(GeneralPaymentObject.getParty()));
							types.add(Wizard.String);
							elements.add(GeneralPaymentObject.getdate());
							types.add(Wizard.Date);
							elements.add(GeneralPaymentObject.getValue().toString());
							types.add(Wizard.Double);
							if(GeneralPaymentObject.getNotes() ==null)
							{
								elements.add("1");
								types.add(Wizard.NULL);
							} 
							else
							{
							 elements.add(GeneralPaymentObject.getNotes());
							 types.add(Wizard.String);
							}	
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
		        DeletePaymentImage.setOnMouseClicked(DeleteGeneralPayment);
		        DeletePaymentLabel.setOnMouseClicked(DeleteGeneralPayment);
////////////////////////////////////////////////////////////////////////////////////////
		        EventHandler<Event> EditGeneralPayment = new EventHandler<Event>() 
								{
							        @Override
						             	public void handle(Event arg0) 
						                      	{
							        	if (newparty.getValue() == null) 
										{
											Dialogs.create().owner(null).title("رسالة خطأ")
													.masthead("!")
													.message("الرجاء  تحديد طرف الدفعية").showError();
											return;
										} 
										else if (newparty.getValue().toString().trim().length() == 0)
										{
											Dialogs.create().owner(null).title("رسالة خطأ")
											.masthead("!")
											.message("الرجاء  تحديد طرف الدفعية").showError();
											return;
										}
										if (NewDate.getValue() == null)
										{
											Dialogs.create().owner(null).title("رسالة خطأ")
													.masthead("لايمكن ترك  تاريخ الدفعية  فارغا!")
													.message("الرجاء  تحديد تاريخ الدفعية")
													.showError();
											return;
										} 
										else if (NewDate.getValue().toString().trim()
												.length() == 0)
										{
											Dialogs.create().owner(null).title("رسالة خطأ")
													.masthead("لايمكن ترك  تاريخ الدفعية  فارغا!")
													.message("الرجاء  تحديد تاريخ الدفعية ")
													.showError();
											return;
										}
										if (NewValue.getText().trim().length() == 0)
										{
											Dialogs.create().owner(null).title("رسالة خطأ")
													.masthead("لايمكن ترك  قيمة الدفعية  فارغة!")
													.message("الرجاء  تحديد قيمة الدفعية").showError();
											return;
										}
										

										types = new ArrayList<>();
										elements = new ArrayList<>();
				//////////////////////////////////////////////////////////////////////////////////////////////
//								construct the update query								
								String Q = "update  general_transactions set Type = ? , Party = ? , Date = ?, Value = ?,Notes = ? "
										+ "where Type = ? and Party = ? and Date = ? and Value = ?   ";
								
					             types=new ArrayList<>();
				   			     elements = new ArrayList<>();
				   			     
				   			     GeneralPayment tempPayment = new GeneralPayment();
				   			// then prepare the statment by filling the elements and
									// types
				   			     {	
									if (NewType.getValue() == null) 
									{
										elements.add("1");
										types.add(Wizard.NULL);
										tempPayment.settype("إيرادات");

									}
									else if (NewType.getValue().length() == 0)
									{
										elements.add("1");
										types.add(Wizard.NULL);
										tempPayment.settype("إيرادات");

									}
									else
									{
										elements.add(Wizard.GeneralPaymentTypeConverter(NewType.getValue()));
										types.add(Wizard.String);
										tempPayment.settype(NewType.getValue());

									}
									
									elements.add(Wizard.GeneralPaymentPartyConverter(newparty.getValue()));
									types.add(Wizard.String);
									tempPayment.setParty(newparty.getValue());
									 
									elements.add(NewDate.getValue().toString().trim());
									types.add(Wizard.Date);
									tempPayment.setdate(NewDate.getValue().toString().trim());

									elements.add(NewValue.getText().trim());
									types.add(Wizard.Double);
									tempPayment.setValue(Double.parseDouble(NewValue.getText().trim()));
									
									if (NewDetails.getText().trim().length() == 0) 
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
									 
									elements.add(Wizard.GeneralPaymentTypeConverter(GeneralPaymentObject.gettype()));
									types.add(Wizard.String);
									elements.add(Wizard.GeneralPaymentPartyConverter(GeneralPaymentObject.getParty()));
									types.add(Wizard.String);
									elements.add(GeneralPaymentObject.getdate());
									types.add(Wizard.Date);
									elements.add(GeneralPaymentObject.getValue().toString());
									types.add(Wizard.Double);
									if(GeneralPaymentObject.getNotes() ==null)
									{
										elements.add("1");
										types.add(Wizard.NULL);
									} 
									else
									{
									 elements.add(GeneralPaymentObject.getNotes());
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
									Wizard.global_selected_generalPayment = tempPayment;
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
						ConfirmEditImage.setOnMouseClicked(EditGeneralPayment);
						ConfirmEditLabel.setOnMouseClicked(EditGeneralPayment);
						
///////////////////////////////////////////////////////////////////////////////////////

						EventHandler<Event> PrintGeneralPayment = new EventHandler<Event>()
								{
							
							        @Override
							         public void handle(Event arg0) 
							        {	
							        	Wizard.CreateGeneralPaymentReport(GeneralPaymentObject);
							        }
								};
		                 PrintImage.setOnMouseClicked(PrintGeneralPayment);
		                 PrintLabel.setOnMouseClicked(PrintGeneralPayment);
///////////////////////////////////////////////////////////////////////////////////////

	}
	
	
	
	@Override
	public void initialize_onshow() 
	{
//////////////////////////////////////////////////////////////////////////////////////////////
		NewType.setItems(Wizard.PaymentsTypesforComboBox);
		newparty.setItems(Wizard.GeneralPaymentsOutcomeTypes);
		clear();
		clearOnEdit();
		GeneralPaymentObject = Wizard.global_selected_generalPayment;
			PaymentType.setText(GeneralPaymentObject.gettype());
			NewType.setValue(GeneralPaymentObject.gettype());
			Party.setText(GeneralPaymentObject.getParty());
			newparty.setValue(GeneralPaymentObject.getParty());
			Date.setText(GeneralPaymentObject.getdate());
			NewDate.setValue(Wizard.ToLocalDate(GeneralPaymentObject.getdate()));
			Value.setText(GeneralPaymentObject.getValue().toString());
			NewValue.setText(GeneralPaymentObject.getValue().toString());
		    if(GeneralPaymentObject.getNotes()!=null)
		    {
				Details.setText(GeneralPaymentObject.getNotes());
				NewDetails.setText(GeneralPaymentObject.getNotes());
		    }
		}
	
	@FXML
    private DatePicker NewDate;

    @FXML
    private ImageView DeletePaymentImage;

    @FXML
    private Label ConfirmEditLabel;

    @FXML
    private Label Date;

    @FXML
    private ComboBox<String> NewType;

    @FXML
    private Label DeletePaymentLabel;

    @FXML
    private Label Party;

    @FXML
    private TextArea Details;

    @FXML
    private Label Value;

    @FXML
    private Label PaymentType;

    @FXML
    private ComboBox<String> newparty;

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
    
    
    @FXML
    private void handleNewGeneralPaymentTypeAction()
    {
      String selectedItem = NewType.getSelectionModel().getSelectedItem();
      if(selectedItem == null)
      {
    	  newparty.setValue(null);
    	  newparty.setItems(null);
    	  newparty.setPromptText("الرجاء تحديد  نوع الدفعية أولا");
    	  
      }
      else if (selectedItem.equals(""))
      {
    	  newparty.setValue(null);
    	  newparty.setItems(null);
    	  newparty.setPromptText("الرجاء تحديد  نوع الدفعية أولا");
      }
      else
      {
    	  if(selectedItem.equals(Wizard.PaymentsTypesforComboBox.get(1)))
    	  {
    		  // expenses were selected 
    		  newparty.setItems(Wizard.GeneralPaymentsOutcomeTypesforComboBox);
    		  newparty.setPromptText("");

    	  }
    	  else
    	  {
    		  newparty.setItems(Wizard.GeneralPaymentsIncomeTypesforComboBox);
    		  newparty.setPromptText("");

    	  }
      }
    }
    
	
}
