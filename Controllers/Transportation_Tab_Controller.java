package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.dialog.Dialogs;

import databaseUtilities.DBUtil;
import databaseUtilities.JDBC;
import wizards.Wizard;
import models.Student;
import models.Transporation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class Transportation_Tab_Controller extends Controlled_Screen implements Initializable 
   {
	  //temp observable list  
			ObservableList result;			
			List<String> types;
			List<String> elements;
			
			@Override
			public void clear() 
			{
			        NewDestination.clear();
			        NewDriver.clear();
			        NewFees.clear();
			        newvehicle.setValue(null);
			}
			
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		nextScreen="Transportation_Info";
		ID.setCellValueFactory(new PropertyValueFactory<Transporation, Integer>("id"));
		ID.setCellFactory(integerCellFactory);
		Destination_Name.setCellValueFactory(new PropertyValueFactory<Transporation, String>("Destination"));
		Destination_Name.setCellFactory(TransitionEventFactory);
    	Driver_name.setCellValueFactory(new PropertyValueFactory<Transporation, String>("Driver_name"));
    	Driver_name.setCellFactory(TransitionEventFactory);
///////////////////////////////////////////////////////////////////////////////////////
    	newvehicle.setItems(Wizard.getVehiclesTypes());
///////////////////////////////////////////////////////////////////////////////////////

		// search icon handler
		EventHandler<Event> SearchTransporation = new EventHandler<Event>()
		{

			@Override
			public void handle(Event arg0) 
			{
				List<String> types = new ArrayList<String>();
				List<String> elements = new ArrayList<String>();
				String temp1 =Transportation_ID.getText().trim();
				String temp2 = Destination.getText().trim();
				String temp3 = Driver.getText().trim();				
				try {
					if (temp1.length() != 0 && temp2.length() != 0
							&& temp3.length() !=0) // three
					// fields are given 
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						elements.add(temp2);
						types.add(Wizard.LikeString);
						elements.add(temp3);
						types.add(Wizard.LikeString);

						query = "SELECT * FROM transporation WHERE ID =? UNION SELECT * FROM transporation WHERE Destination like ? UNION SELECT * FROM transporation WHERE Driver_name like ?";
					} 
					else if (temp1.length() != 0 && temp2.length() != 0)//ID + Destination 
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						elements.add(temp2);
						types.add(Wizard.LikeString);
						query = "SELECT * FROM transporation WHERE ID =? UNION SELECT * FROM transporation WHERE Destination like ?";
					} 
					else if (temp2.length() != 0 && temp3.length() !=0) //Destination + Driver_name
					{
						elements.add(temp2);
						types.add(Wizard.LikeString);
						elements.add(temp3);
						types.add(Wizard.LikeString);
						query = "SELECT * FROM transporation WHERE Destination like ? UNION SELECT * FROM transporation WHERE Driver_name like?";
					} 
					else if (temp1.length() != 0 && temp3.length() !=0) //ID + Driver_name
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						elements.add(temp3);
						types.add(Wizard.LikeString);
						query = "SELECT * FROM transporation WHERE ID =?  UNION SELECT * FROM transporation WHERE Driver_name like ?";
					} 
					else if (temp1.length() != 0)//ID only
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						query = "SELECT * FROM transporation WHERE ID =?";
					} 
					else if (temp2.length() != 0) //Destination only
					{
						elements.add(temp2);
						types.add(Wizard.LikeString);
						query = "SELECT * FROM transporation WHERE Destination like ?";
					} 
					else if (temp3.length() !=0)  //Driver_name only 
					{
						elements.add(temp3);
						types.add(Wizard.LikeString);
						query = "SELECT * FROM transporation WHERE Driver_name like ?";
					}
					else 
					{
						Dialogs.create()
								.owner(null)
								.title("إشعار")
								.message(
										"الرجاء أدخال بيانات حقل واحد على الأقل :)")
								.showWarning();
						return;
					}
					result = (ObservableList<Transporation>) JDBC.fill_Otabel(query,
							Wizard.transporation, elements, types);
					if (result.size()==0)
					{
						Dialogs.create()
		                .owner(null)
		                .title("نتيجة فارغة")
		                .message("لا توجد نتيجة توافق البيانات المدخلة الرجاء التأكد من صحة البيانات المدخلة :)")
		                .showWarning();
					}
					tabel.setItems(result);
					resultcopy = result;
				} 
				catch (Exception e) 
				{
					System.out.println("error retriving observable list in transporation tab after search");
				}

			}
		};
		SearchIcon.setOnMouseClicked(SearchTransporation);
		SearchLabel.setOnMouseClicked(SearchTransporation);
/////////////////////////////////////////////////////////////////////////////////////
		EventHandler<Event> addTransoprtation = new EventHandler<Event>() 
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
//				construct the insert query
				
				String Q = "insert into transporation (ID,Destination,Driver_name,delivery_cost,vehicle_type)"
						+ " values (NULL,?,?,?,?)";
//				               
				 {
					 // then prepare the statment by filling the elements and types
					 elements.add(NewDestination.getText().trim());
					 types.add(Wizard.String);
/////////////////////////////////////////////////////////////////////////////////////////
					 if(NewDriver.getText().length()==0)
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
					 else  if(newvehicle.getValue().trim().length()==0)
					   {
						 elements.add("1");
						 types.add(Wizard.NULL);
					   }
					  else 
					   {
						 elements.add(newvehicle.getValue().trim());
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
							.message(
									"  تمت إضافة الترحيل " + NewDestination.getText().trim()+" "
											+ "إلى قاعدة البيانات ")
							.showInformation();
					clear();

				}
				else
					Dialogs.create()
							.owner(null)
							.title("رسالة خطأ")
							.masthead("لم يتم إضافة بيانات الترحيل")
							.message(
									"الرجاء التأكد من صحة البيانات المدحلة والتأكد من أن الاسم لم يستخدم مسبقا")
							.showWarning();
			     }
		       };
		       
		AddTransportationLabel.setOnMouseClicked(addTransoprtation);
		AddTransportationPhoto.setOnMouseClicked(addTransoprtation);
/////////////////////////////////////////////////////////////////////////////////////
	    EventHandler<Event> FindAll = new EventHandler<Event>() 
	 		   {
			
			@Override
			public void handle(Event arg0) 
			{
				
				
					result = (ObservableList<Transporation>) JDBC.fill_Otabel("select * from transporation",Wizard.transporation,null,null);
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
//		//we have to refresh  teachers list every time a teacher is added to the database 
	}
	
	@FXML
    private TextField Destination;
	@FXML
	private TextField Transportation_ID;
	@FXML
    private TextField Driver;
	@FXML
	private TableView tabel;
    @FXML
	private TableColumn Destination_Name;
    @FXML
    private TableColumn ID;
    @FXML
    private TableColumn Driver_name;
    @FXML
    private ImageView SearchIcon;
    @FXML
    private Label SearchLabel;

/////////////////////////////////////////////////////////////////////////////////
    @FXML
    private TextField NewDestination;
    @FXML
    private TextField NewDriver;
    @FXML
    private TextField NewFees;
    @FXML
    private ComboBox<String> newvehicle;
    @FXML
    private Label AddTransportationLabel;
    @FXML
    private ImageView AddTransportationPhoto;
    @FXML 
    private  ImageView ShowAllImage;
    @FXML 
    private Label ShowAllLabel;
////////////////////////////////////////////////////////////////////////////////////////
   }
