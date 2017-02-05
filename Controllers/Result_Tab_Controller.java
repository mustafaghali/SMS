package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.dialog.Dialogs;

import databaseUtilities.DBUtil;
import databaseUtilities.JDBC;
import wizards.Wizard;
import models.Result;
import models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class Result_Tab_Controller extends Controlled_Screen implements Initializable 
   {
	  //temp observable list  
			ObservableList resultObservableList;			
			List<String> types;
			List<String> elements;
///////////////////////////////////////////////////////////////////////////////////////////
//			Search Control Elemenets
			String resultType =null;
			String DateValue=null;
			String ClassName = null;
			String Q1= "SELECT * FROM Results WHERE date = ?" ;
			String Q2= "SELECT * FROM Results WHERE type like ?";
			String Q3="SELECT * FROM Results WHERE Class_ID = ?";
///////////////////////////////////////////////////////////////////////////////////////////
			String ResultIndex = "1";

			@Override
			public void clear() 
			{
				New_Supervisor_2.setValue(null);
				NewSupervisor_1.setValue(null);
				NewClass.setValue(null);
				NewDate.setValue(null);
				NewExam_Precentage.clear();
				NewType.setValue(null);
				NewDate.setValue(null);
				
//			        NewDestination.clear();
//			        NewDriver.clear();
//			        NewFees.clear();
//			        newvehicle.setValue(null);
			}
			
			public  String getSearchQuery()
			{
				String query = null;
				if (DateValue != null && resultType!= null && ClassName!= null) // three
				// fields are given 
				{
					if(!(DateValue.length()== 0 || resultType.length() == 0 || ClassName.length()==0))
					{
					elements.add(DateValue);
					types.add(Wizard.Date);
					elements.add(Wizard.ResultTypeConverter(resultType));
					types.add(Wizard.LikeString);
					elements.add(Wizard.getClassID(ClassName));
					types.add(Wizard.Integer);

					query = Q1+" UNION "+Q2+" UNION "+Q3;
					return query ;
					}
				} 
			    if (DateValue!= null && resultType!= null)//date value + result type 
				{
					if(!(DateValue.length()== 0 || resultType.length() == 0))
					{
					elements.add(DateValue);
					types.add(Wizard.Date);
					elements.add(Wizard.ResultTypeConverter(resultType));
					types.add(Wizard.LikeString);
					query = Q1+" UNION "+Q2;
					return query ;
					}
				} 
			    if (resultType!= null && ClassName!= null) //temp2 + ClassName
				{
					if(!(resultType.length() == 0 || ClassName.length()==0))
					{
					elements.add(Wizard.ResultTypeConverter(resultType));
					types.add(Wizard.LikeString);
					elements.add(Wizard.getClassID(ClassName));
					types.add(Wizard.Integer);
					query = Q2+" UNION "+Q3;
					return query ;
					}
				} 
			    if (DateValue!= null && ClassName!= null) //temp1 + ClassName
				{
					if(!(DateValue.length()== 0 || ClassName.length()==0))
					{
					elements.add(DateValue);
					types.add(Wizard.Date);
					elements.add(Wizard.getClassID(ClassName));
					types.add(Wizard.Integer);
					query = Q1+" UNION "+Q3;
					return query ;
					}
				} 
			    if (DateValue!= null)//temp1 only
				{
					if(DateValue.length()!= 0)
					{
					elements.add(DateValue);
					types.add(Wizard.Date);
					query = Q1;
					return query ;
					}
				} 
			    if (resultType!= null) //temp2 only
				{
			    	if(resultType.length()!= 0)
					{
					elements.add(Wizard.ResultTypeConverter(resultType));
					types.add(Wizard.LikeString);
					query =Q2;
					return query ;
					}
				} 
				 if (ClassName!= null)  //ClassName only 
				{
					 if(ClassName.length()!= 0)
						{
					       elements.add(Wizard.getClassID(ClassName));
					       types.add(Wizard.Integer);
					       query = Q3;
					      return query ;
						}
				}
				
				return query;
			}
 
			@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
				super.initialize_onshow();
		nextScreen="Result_Info";
		IDColmun.setCellValueFactory(new PropertyValueFactory<Result, Integer>("id"));
		IDColmun.setCellFactory(integerCellFactory);
		TypeColmun.setCellValueFactory(new PropertyValueFactory<Result, String>("type"));
		TypeColmun.setCellFactory(TransitionEventFactory);
		LevelColmun.setCellValueFactory(new PropertyValueFactory<Result, String>("Level"));
		LevelColmun.setCellFactory(TransitionEventFactory);
		DateColmun.setCellValueFactory(new PropertyValueFactory<Result, String>("date"));
		DateColmun.setCellFactory(TransitionEventFactory);
		ClassNameColmun.setCellValueFactory(new PropertyValueFactory<Result, String>("ClassName"));
		ClassNameColmun.setCellFactory(TransitionEventFactory);  	
///////////////////////////////////////////////////////////////////////////////////////
		NewDate.setConverter(Wizard.DatePickerConverter());
		ResultsTypes.setItems(Wizard.ResultTypesForComboBox);
		NewType.setItems(Wizard.ResultTypesForComboBox);
		Classes.setItems(Wizard.getStudentClassesForCombobox());
		NewClass.setItems(Wizard.getStudentClassesForCombobox());
		NewSupervisor_1.setItems(Wizard.getTeacherNamesForComboBox());
		New_Supervisor_2.setItems(Wizard.getTeacherNamesForComboBox());
///////////////////////////////////////////////////////////////////////////////////////
		FillResultDataImage.setVisible(false);
		FillResultDataLabel.setVisible(false);
///////////////////////////////////////////////////////////////////////////////////////

		// search icon handler
		EventHandler<Event> SearchResult = new EventHandler<Event>()
		{
			@Override
			public void handle(Event arg0) 
			{
				 types = new ArrayList<String>();
				 elements = new ArrayList<String>();
				
				 resultType = (String) ResultsTypes.getValue();
				 if( Date.getValue() != null)
			     DateValue= Date.getValue().toString().trim();
			     ClassName = (String) Classes.getValue();
				
			     query = getSearchQuery();
			
				if (query==null) 
				{
					Dialogs.create()
							.owner(null)
							.title("إشعار")
							.message(
									"الرجاء أدخال بيانات حقل واحد على الأقل")
							.showWarning();
					return;
				}
				
				try {
				
					//System.out.println(query);
					resultObservableList = (ObservableList<Result>) JDBC.fill_Otabel(query,
							Wizard.Result, elements, types);
					if (resultObservableList.size()==0)
					{
						Dialogs.create()
		                .owner(null)
		                .title("نتيجة فارغة")
		                .message("لا توجد نتيجة توافق البيانات المدخلة الرجاء التأكد من صحة البيانات المدخلة")
		                .showWarning();
					}
					tabel.setItems(resultObservableList);
					resultcopy = resultObservableList;
				} 
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
					System.out.println("error retriving observable list in Result tab after search");
				}

			}
		};
		SearchIcon.setOnMouseClicked(SearchResult);
		SearchLabel.setOnMouseClicked(SearchResult);
/////////////////////////////////////////////////////////////////////////////////////
		EventHandler<Event> addResult = new EventHandler<Event>() 
				{
			        @Override
		             	public void handle(Event arg0) 
		                      	{
			        	             if (NewDate.getValue() == null)
			        	             {
			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  تاريخ الإصدار  فارغا!").message("الرجاء  تحديد تاريخ الإصدار ").showError();
				        	             return;
			        	             }
			        	             else if (NewDate.getValue().toString().trim().length()==0)
			        	             {
			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  تاريخ الإصدار  فارغا!").message("الرجاء  تحديد تاريخ الإصدار ").showError();
				        	             return; 
			        	             }
			        	             if (NewClass.getValue() == null)
			        	             {
			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  اسم  الفصل  فارغا!").message("الرجاء  تحديد اسم الفصل ").showError();
				        	             return;
			        	             }
			        	             else if (NewClass.getValue().toString().length()==0)
			        	             {
			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  اسم  الفصل  فارغا!").message("الرجاء  تحديد اسم الفصل ").showError();
				        	             return; 
			        	             }		
	                    			 types=new ArrayList<>();
	                    		     elements = new ArrayList<>();
////////////////////////////////////////////////////////////////////////////////////////////////
	     
//				construct the insert query
				
				String Q = "insert into results (ID,Class_ID,Date,supervisor_1,supervisor_2,semster_ID,Type,ExamPrecentage)"
						+ " values (NULL,?,?,?,?,?,?,?)";
//				               
				 {
					 // then prepare the statment by filling the elements and types
					 elements.add(Wizard.getClassID(NewClass.getValue().toString()));
					 types.add(Wizard.Integer);
					 elements.add(NewDate.getValue().toString().trim());
					 types.add(Wizard.Date);
///////////////////////////////////////////////////////////////////////////////////////////
					 if(NewSupervisor_1.getValue()==null)
					   {
						 elements.add("1");
						 types.add(Wizard.NULL);
					   }
					 else if(NewSupervisor_1.getValue().toString().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					  else 
					  {
						 elements.add(Wizard.getTeacherID(NewSupervisor_1.getValue().toString()));
						 types.add(Wizard.Integer);
					  }
///////////////////////////////////////////////////////////////////////////////////////////
					 if(New_Supervisor_2.getValue()==null)
					   {
						 elements.add("1");
						 types.add(Wizard.NULL);
					   }
					 else if(New_Supervisor_2.getValue().toString().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					  else 
					  {
						 elements.add(Wizard.getTeacherID(New_Supervisor_2.getValue().toString()));
						 types.add(Wizard.Integer);
					  }
///////////////////////////////////////////////////////////////////////////////////////////
					 //semester _ID
					 elements.add("1");
					 types.add(Wizard.NULL);
///////////////////////////////////////////////////////////////////////////////////////////
					 if(NewType.getValue()==null)
					   {
						 elements.add("1");
						 types.add(Wizard.NULL);
					   }
					 else if(NewType.getValue().toString().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					  else 
					  {
						 elements.add(Wizard.ResultTypeConverter(NewType.getValue().toString()));
						 types.add(Wizard.String);
					  }
///////////////////////////////////////////////////////////////////////////////////////////
					 if(NewExam_Precentage.getText().trim().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL); 
					 }
					 
				     else
				     {
					 if((Integer.parseInt(NewExam_Precentage.getText().trim()))<= 0 && (Integer.parseInt(NewExam_Precentage.getText().trim()))>=100)
    	             {
    	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("قيمة النسبة التي أدخلتها غير صحيحة!").message("الرجاء إدخال قيمة بين الصفر والمائة").showError();
        	             return;
    	             }
					   elements.add(NewExam_Precentage.getText().trim());
					   types.add(Wizard.Integer); 
				     }
				 }
///////////////////////////////////////////////////////////////////////////////////////////
				 //we need to check the id of the last inserted record 
				 //so switch the check 
				 DBUtil.LastIdCheck = true;
					Boolean executed = DBUtil.excecuteUpdate(Q,elements,types);
				if (executed)
				{
					Dialogs.create()
							.owner(null)
							.title("رسالة إشعار")
							.masthead(null)
							.message(
									"تمت إضافة  النتيجة  إلى قاعدة البيانات لتعبئة تفاصيل النتيجة اضغط على الزر أدناه ")
							.showInformation();
					FillResultDataImage.setVisible(true);
					FillResultDataLabel.setVisible(true);
					ResultIndex = String.valueOf(Wizard.LastGeneratedKey);
					clear();
				}
				else
					Dialogs.create()
							.owner(null)
							.title("رسالة خطأ")
							.masthead("لم يتم إضافة بيانات النتيجة")
							.message(
									"الرجاء التأكد من صحة البيانات المدحلة")
							.showWarning();
		          	}// the end of the handle method
		       };
		       
		AddResultLabel.setOnMouseClicked(addResult);
		AddResultImage.setOnMouseClicked(addResult);
/////////////////////////////////////////////////////////////////////////////////////
	 	EventHandler<Event> FillResult = new EventHandler<Event>() 
				{
			        @Override
		             	public void handle(Event arg0) 
		                      	{
			        	           Wizard.global_selected_id =Integer.parseInt(ResultIndex) ;
			        	            myController.setScreen("Result_Info","1");
		                      	}
				};
		 FillResultDataImage.setOnMouseClicked(FillResult);
		 FillResultDataLabel.setOnMouseClicked(FillResult);
/////////////////////////////////////////////////////////////////////////////////////

		  EventHandler<Event> FindAll = new EventHandler<Event>() 
		 		   {
				
				@Override
				public void handle(Event arg0) 
				{
					
					
						ObservableList result = (ObservableList<Result>) JDBC.fill_Otabel("select * from Results",Wizard.Result,null,null);
						if (result.size()==0)
						{
							Dialogs.create()
			                .owner(null)
			                .title("نتيجة فارغة")
			                .message("لا توجد أي بيانات في هذا الحقل")
			                .showWarning();
						}
						tabel.setItems(result);
						resultcopy=FXCollections.observableArrayList();
						resultcopy.addAll(result);
				}
			};
		/////////////////////////////////////////////////////////////////////////////////////////
		        ShowAllImage.setOnMouseClicked(FindAll);
		        ShowAllLabel.setOnMouseClicked(FindAll);
	}
	
	@Override
	public void initialize_onshow()
	{
		FillResultDataImage.setVisible(false);
		FillResultDataLabel.setVisible(false);
//		//we have to refresh  teachers list every time a teacher is added to the database 
	}
	
	    @FXML
	    private TableColumn IDColmun;
	    @FXML
	    private ComboBox ResultsTypes;
	    @FXML
	    private TableColumn DateColmun;
	    @FXML
	    private TableView<?> tabel;
        @FXML
	    private DatePicker Date;
	    @FXML
	    private TableColumn TypeColmun;
	    @FXML
	    private TableColumn LevelColmun;
	    @FXML
	    private ImageView SearchIcon;
	    
	    @FXML
	    private Label SearchLabel;
	    @FXML
	    private TableColumn ClassNameColmun;
	    @FXML
	    private ComboBox Classes;

////////////////////////////////////////////////////////////////////////////////////////
	    @FXML
	    private DatePicker NewDate;
	    @FXML
	    private ComboBox<?> NewSupervisor_1;
	    @FXML
	    private ImageView AddResultImage;
	    @FXML
	    private Label AddResultLabel;
	    @FXML
	    private ComboBox NewType;
	    @FXML
	    private ComboBox New_Supervisor_2;
	    @FXML
	    private ImageView FillResultDataImage;
	    @FXML
	    private Label FillResultDataLabel;
	    @FXML
	    private ComboBox NewClass;
	    @FXML
	    private TextField NewExam_Precentage;
	    @FXML 
	    private  ImageView ShowAllImage;
	    @FXML 
	    private Label ShowAllLabel;
	    
	    
   }
