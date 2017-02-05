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
import databaseUtilities.JDBC;
import wizards.Wizard;
import application.MainClass;
import models.Teacher;
import models.Worker;
import models.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.stage.FileChooser;

public class Worker_Tab_Controller extends Controlled_Screen implements Initializable 
   {
	  //temp observable list  
			ObservableList result;
			//temp Url
			String ImageUrl;
			
			List<String> types;
			List<String> elements;
			
		//global filechooser
			FileChooser fileChooser;
			
			@Override
			public void clear() 
			{
				NewWorkerName.clear();
				NewWorkerJob.clear();
				NewPhoneNumber.clear();
				NewWorkerSalary.clear();
				NewWorkerAlternatives.clear();
				NewTranspFees.clear();
				NewTranspName.setValue(null);
				NewWorkerAddress.clear();
				NewNotes.clear();
				ImageUrl=null;
				choosePhoto.setVisible(true);
				NewWorkerImage.setImage(Wizard.getDefaultWorkerImage());
			}
			
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		nextScreen="Worker_Info";
		ID.setCellValueFactory(new PropertyValueFactory<Worker, Integer>("id"));
		ID.setCellFactory(integerCellFactory);
		Name.setCellValueFactory(new PropertyValueFactory<Worker, String>("Name"));
		Name.setCellFactory(TransitionEventFactory);
		WorksAs.setCellValueFactory(new PropertyValueFactory<Worker, String>("WorksAs"));
		WorksAs.setCellFactory(TransitionEventFactory);
//////////////////////////////////////////////////////////////////////////////////////////////////////////
		 // fill the Jobs combonboxes
        SearchJobs.setItems(Wizard.getWorkersJobs());
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//// fill the transporations comboboxes
         NewTranspName.setItems(Wizard.getTransporations());
//////////////////////////////////////////////////////////////////////////////////////////////////////////
         //image chooser event handler
         EventHandler<Event> imageChooser = new EventHandler<Event>() 
			{
		        @Override
	             	public void handle(Event arg0) 
	                      	{
	                      		ImageUrl = Wizard.Imagechooser(NewWorkerImage);
		                    	if (ImageUrl != null)
            		 			choosePhoto.setVisible(false);
			                     //System.out.println(ImageUrl);
		                     }
	       };
	NewWorkerImage.setOnMouseClicked(imageChooser);
	choosePhoto.setOnMouseClicked(imageChooser);
///////////////////////////////////////////////////////////////////////////////////////
		// search icon handler
		EventHandler<Event> SearchWorker = new EventHandler<Event>()
		{

			@Override
			public void handle(Event arg0) 
			{
				List<String> types = new ArrayList<String>();
				List<String> elements = new ArrayList<String>();
				String temp1 = Worker_ID.getText().trim();
				String temp2 = Worker_name.getText().trim();
				String temp3 = SearchJobs.getValue();
				try {
					if (temp1.length() != 0 && temp2.length() != 0
							&& temp3 != null) // three
					// fields are given 
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						elements.add(temp2);
						types.add(Wizard.LikeString);
						elements.add(temp3);
						types.add(Wizard.String);

						query = "SELECT * FROM workers WHERE ID =? UNION SELECT * FROM workers WHERE Name like ? UNION SELECT * FROM workers WHERE works_as =?";
					} 
					else if (temp1.length() != 0 && temp2.length() != 0)//worker_ID + worker_name 
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						elements.add(temp2);
						types.add(Wizard.LikeString);
						query = "SELECT * FROM workers WHERE ID =? UNION SELECT * FROM workers WHERE Name like ?";
					} 
					else if (temp2.length() != 0 && temp3 != null) //worker_name + Job
					{
						elements.add(temp2);
						types.add(Wizard.LikeString);
						elements.add(temp3);
						types.add(Wizard.String);
						query = "SELECT * FROM workers WHERE Name like ? UNION SELECT * FROM workers WHERE works_as=?";
					} 
					else if (temp1.length() != 0 && temp3 != null) //Worker_ID + Job
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						elements.add(temp3);
						types.add(Wizard.String);
						query = "SELECT * FROM workers WHERE ID =?  UNION SELECT * FROM workers WHERE works_as=?";
					} 
					else if (temp1.length() != 0)//ID 
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						query = "SELECT * FROM workers WHERE ID =?";
					} 
					else if (temp2.length() != 0) //name
					{
						elements.add(temp2);
						types.add(Wizard.LikeString);
						query = "SELECT * FROM workers WHERE Name like ?";
					} 
					else if (temp3 != null)  //job
					{
						elements.add(temp3);
						types.add(Wizard.String);
						query = "SELECT * FROM workers WHERE works_as=?";
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
					result = (ObservableList<Worker>) JDBC.fill_Otabel(query,
							Wizard.Worker, elements, types);
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
					System.out.println("error retriving observable list in worker tab after search");
				}

			}
		};
		SearchIcon.setOnMouseClicked(SearchWorker);
		SearchLabel.setOnMouseClicked(SearchWorker);
///////////////////////////////////////////////////////////////////////////////////////
		EventHandler<Event> addWorker = new EventHandler<Event>() 
				{
			        @Override
		             	public void handle(Event arg0) 
		                      	{
			        	           if(NewWorkerName.getText().trim().length()==0)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم العامل فارغا!").message(" الرجاء إدخال اسم العامل ").showError();
//			        	   			NewWorkerAddress.setStyle();
			        	             return;
			        	           }
			        	           if(NewWorkerJob.getText().trim().length()==0)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم  الشعبة فارغا!").message("الرجاء  اختيار الشعبة").showError();
			        	             return;
			        	           }
			        	          
				 types=new ArrayList<>();
			     elements = new ArrayList<>();
//////////////////////////////////////////////////////////////////////////////////////////////
//				construct the insert query
				//12 prepared filed
				
				String Q = "insert into Workers (ID,Name,Address,phone_number,works_as,Salary,alternatives,transp_ID,transp_fees,notes,ImageURL)"
						+ " values (NULL,?,?,?,?,?,?,?,?,?,?)";
//				                (1,2,3,4,5,6,7,8,9,10,11,12,13,14)
				 {
					 // then prepare the statment by filling the elements and types
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
					
					 if(NewWorkerJob.getText().length()==0)
					   {
						 elements.add("1");
						 types.add(Wizard.NULL);
					   }
					  else 
					   {
						 elements.add(NewWorkerJob.getText().trim());
						 types.add(Wizard.String);
					   }
					 if(NewWorkerSalary.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewWorkerSalary.getText().trim());
						 types.add(Wizard.Double);
					 }
					 if(NewWorkerAlternatives.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewWorkerAlternatives.getText().trim());
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
				    	 String Imagefile=null;
				    	    try 
				    	    {
				    	    	 Imagefile= ("1"+new Date()+".jpg").replace(":","-").replace(" ","");
								BufferedImage inputImage=ImageIO.read(new File(ImageUrl));
								System.out.println(Wizard.myJarPath()+"/resources/"+Imagefile);
								ImageIO.write(inputImage,"jpg",new File(Wizard.myJarPath()+"/resources/"+Imagefile));
								inputImage =null;
				    	    }
				    	    catch (IOException e) 
				    	    {
								System.out.println(e.getMessage()+"error copying the image to the root directory class Worker tab");

				    	    }
							elements.add(Imagefile);
							  types.add(Wizard.String);
				    	 } 
		
						  else
						  {
							  elements.add("1");
								 types.add(Wizard.NULL);
						  }
				      
				 }
					Boolean executed = DBUtil.excecuteUpdate(Q,elements,types);
				if (executed)
				{
					Dialogs.create()
							.owner(null)
							.title("رسالة إشعار")
							.masthead(null)
							.message(
									"  تمت إضافة العامل" + NewWorkerName.getText().trim()+" "
											+ "إلى قاعدة البيانات ")
							.showInformation();
					clear();

				}
				else
					Dialogs.create()
							.owner(null)
							.title("رسالة خطأ")
							.masthead("لم يتم إضافة بيانات العامل")
							.message(
									"الرجاء التأكد من صحة البيانات المدحلة والتأكد من أن الاسم لم يستخدم مسبقا")
							.showWarning();
				ImageUrl=null;
				NewWorkerImage.setImage(Wizard.getDefaultWorkerImage());
				choosePhoto.setVisible(true);
				SearchJobs.setItems(Wizard.getWorkersJobs());
			     }
		       };

		AddWorkerLabel.setOnMouseClicked(addWorker);
		AddWorkerPhoto.setOnMouseClicked(addWorker);
///////////////////////////////////////////////////////////////////////////////////////
		//search all icon handler 
	    EventHandler<Event> FindAll = new EventHandler<Event>() 
	 		   {
			
			@Override
			public void handle(Event arg0) 
			{
				
				
					result = (ObservableList<Worker>) JDBC.fill_Otabel("select * from workers",Wizard.Worker,null,null);
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
	      ShowAllImage.setOnMouseClicked(FindAll);
	      ShowAllLabel.setOnMouseClicked(FindAll);

		}
	
	@Override
	public void initialize_onshow()
	{
//		//we have to refresh job every time a worker is added to the database 
		 SearchJobs.setItems(Wizard.getWorkersJobs());
	}
	
	@FXML
	private TableView<Worker> tabel;
	@FXML
	private TableColumn ID;
	@FXML
	private TableColumn WorksAs;
	@FXML
	private TableColumn Name;
	@FXML
	private TextField Worker_ID;
	@FXML
	private TextField Worker_name;
	@FXML
	private ComboBox<String> SearchJobs;
	@FXML
	private ImageView SearchIcon;
	@FXML
	private Label SearchLabel;
////////////////////////////////////////////////////////////////////////////////
	@FXML
	private Label choosePhoto;
	@FXML
    private TextField NewPhoneNumber;
	@FXML
    private TextField NewWorkerName;
	@FXML
    private Label AddWorkerLabel;
	@FXML
	private ImageView AddWorkerPhoto;
	@FXML
    private TextField NewTranspFees;
	@FXML
    private TextField NewWorkerJob;
    @FXML
    private ImageView NewWorkerImage;
    @FXML
    private ComboBox<String> NewTranspName;
    @FXML
    private TextField NewWorkerAlternatives;
    @FXML
    private TextField NewWorkerSalary;
    @FXML
    private TextArea NewWorkerAddress;
    @FXML
    private TextArea NewNotes;
    @FXML 
    private  ImageView ShowAllImage;
    @FXML 
    private Label ShowAllLabel;
////////////////////////////////////////////////////////////////////////////////////////
   }
