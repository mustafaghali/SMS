package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.dialog.Dialogs;

import databaseUtilities.DBUtil;
import databaseUtilities.JDBC;
import wizards.Wizard;
import Controllers.Controlled_Screen.MyStringTableCell;
import application.MainClass;
import models.SClasses;
import models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class SClasses_Tab_Controller extends Controlled_Screen implements Initializable 
   {
	  //temp observable list  
			ObservableList result;			
			List<String> types;
			List<String> elements;
			
			@Override
			public void clear() 
			{
				NewClassLevel.setValue(null);
				NewStudyingFees.clear();
				NewFatherSeetsFees.clear();
				NewSupervisor.setValue(null);
				NewClassName.clear();
				NewCapacity.clear();
				NewSummerCourseFees.clear();
			}
			
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		nextScreen="Sclasses_Info";
		ID.setCellValueFactory(new PropertyValueFactory<SClasses, Integer>("id"));
		ID.setCellFactory(integerCellFactory);
		Name.setCellValueFactory(new PropertyValueFactory<SClasses, String>("Name"));
		Name.setCellFactory(TransitionEventFactory);
    	ClassLevel.setCellValueFactory(new PropertyValueFactory<SClasses, String>("Level"));
		ClassLevel.setCellFactory(TransitionEventFactory);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		 // fill the Jobs combonboxes
        SearchLevels.setItems(Wizard.getClassesLevels());
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//// fill the transporations comboboxes
         NewClassLevel.setItems(Wizard.getClassesLevels());

///////////////////////////////////////////////////////////////////////////////////////
		// search icon handler
		EventHandler<Event> SearchClass = new EventHandler<Event>()
		{

			@Override
			public void handle(Event arg0) 
			{
				List<String> types = new ArrayList<String>();
				List<String> elements = new ArrayList<String>();
				String temp1 = Class_ID.getText().trim();
				String temp2 = Class_name.getText().trim();
				String temp3 = SearchLevels.getValue();
				temp3 = Wizard.LevelConverter(temp3);
				
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

						query = "SELECT * FROM classes WHERE ID =? UNION SELECT * FROM classes WHERE Name like ? UNION SELECT * FROM classes WHERE Level =?";
					} 
					else if (temp1.length() != 0 && temp2.length() != 0)//Class_ID + Class_name 
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						elements.add(temp2);
						types.add(Wizard.LikeString);
						query = "SELECT * FROM classes WHERE ID =? UNION SELECT * FROM classes WHERE Name like ?";
					} 
					else if (temp2.length() != 0 && temp3 != null) //Class_name + Level
					{
						elements.add(temp2);
						types.add(Wizard.LikeString);
						elements.add(temp3);
						types.add(Wizard.String);
						query = "SELECT * FROM classes WHERE Name like ? UNION SELECT * FROM classes WHERE Level=?";
					} 
					else if (temp1.length() != 0 && temp3 != null) //Class_ID + Level
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						elements.add(temp3);
						types.add(Wizard.String);
						query = "SELECT * FROM classes WHERE ID =?  UNION SELECT * FROM classes WHERE Level=?";
					} 
					else if (temp1.length() != 0)//ID only
					{
						elements.add(temp1);
						types.add(Wizard.Integer);
						query = "SELECT * FROM classes WHERE ID =?";
					} 
					else if (temp2.length() != 0) //name only
					{
						elements.add(temp2);
						types.add(Wizard.LikeString);
						query = "SELECT * FROM classes WHERE Name like ?";
					} 
					else if (temp3 != null)  //Level 
					{
						elements.add(temp3);
						types.add(Wizard.String);
						query = "SELECT * FROM classes WHERE Level=?";
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
					result = (ObservableList<SClasses>) JDBC.fill_Otabel(query,
							Wizard.SClass, elements, types);
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
					System.out.println("error retriving observable list in S_Classes tab after search");
				}

			}
		};
		SearchIcon.setOnMouseClicked(SearchClass);
		SearchLabel.setOnMouseClicked(SearchClass);
///////////////////////////////////////////////////////////////////////////////////////
		EventHandler<Event> addClass = new EventHandler<Event>() 
				{
			        @Override
		             	public void handle(Event arg0) 
		                      	{
			        	           if(NewClassName.getText().trim().length()==0)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم  الفصل فارغا!").message("الرجاء إدخال اسم الفصل ").showError();
//			        	   			NewWorkerAddress.setStyle();
			        	             return;
			        	           }
			        	           if(NewClassLevel.getValue()==null)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("").message("يجب تحديد  مستوى الفصل").showError();
			        	             return;
			        	           }
			        	           else if(NewClassLevel.getValue().length()==0)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("").message("يجب تحديد  مستوى الفصل").showError();
			        	             return;
			        	           }
			        	           if(NewSupervisor.getValue()==null)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("").message("يجب تحديد  ذ مرشد الفصل").showError();
			        	             return;
			        	           }
			        	           else if(NewSupervisor.getValue().length()==0)
			        	           {
			        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("").message("يجب تحديد   مرشد الفصل").showError();
			        	             return;
			        	           }
				 types=new ArrayList<>();
			     elements = new ArrayList<>();
//////////////////////////////////////////////////////////////////////////////////////////////
			     
//				construct the insert query
				//12 prepared filed
				
				String Q = "insert into classes (ID,Name,Level,Capacity,Studying_fees,father_seats_fees,summer_course_fees,supervisor_ID)"
						+ " values (NULL,?,?,?,?,?,?,?)";
//				               
				 {
					 // then prepare the statment by filling the elements and types
					 elements.add(NewClassName.getText().trim());
					 types.add(Wizard.String);
/////////////////////////////////////////////////////////////////////////////////////////
					 if(NewClassLevel.getValue()==null)
					   {
						 elements.add("1");
						 types.add(Wizard.NULL);
					   }
					 else if(NewClassLevel.getValue().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					  else 
					   {
						 elements.add(Wizard.LevelConverter(NewClassLevel.getValue().trim()));
						 types.add(Wizard.Integer);
					   }
/////////////////////////////////////////////////////////////////////////////////////////
			     
					 if(NewCapacity.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewCapacity.getText().trim());
						 types.add(Wizard.Integer);
					 }
/////////////////////////////////////////////////////////////////////////////////////////

					 if(NewStudyingFees.getText().length()==0)
					   {
						 elements.add("1");
						 types.add(Wizard.NULL);
					   }
					  else 
					   {
						 elements.add(NewCapacity.getText().trim());
						 types.add(Wizard.Double);
					   }
/////////////////////////////////////////////////////////////////////////////////////////

					 if(NewFatherSeetsFees.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewFatherSeetsFees.getText().trim());
						 types.add(Wizard.Double);
					 }
/////////////////////////////////////////////////////////////////////////////////////////
					 if(NewSummerCourseFees.getText().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(NewSummerCourseFees.getText().trim());
						 types.add(Wizard.Double);
					 }
/////////////////////////////////////////////////////////////////////////////////////////
                    if(NewSupervisor.getValue()==null)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else if(NewSupervisor.getValue().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						 elements.add(Wizard.getTeacherID(NewSupervisor.getValue()));
						 types.add(Wizard.Integer);
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
									"  تمت إضافة الفصل " + NewClassName.getText().trim()+" "
											+ "إلى قاعدة البيانات ")
							.showInformation();
					clear();

				}
				else
					Dialogs.create()
							.owner(null)
							.title("رسالة خطأ")
							.masthead("لم يتم إضافة بيانات الفصل")
							.message(
									"الرجاء التأكد من صحة البيانات المدحلة والتأكد من أن الاسم لم يستخدم مسبقا")
							.showWarning();
			     }
		       };
		       
		AddClassLabel.setOnMouseClicked(addClass);
		AddClassPhoto.setOnMouseClicked(addClass);
// /////////////////////////////////////////////////////////////////////////////////////
		SubjectLevel.setItems(Wizard.getClassesLevels());
		SubjectLevel1.setItems(Wizard.getClassesLevels());
/////////////////////////////////////////////////////////////////////////////////////
		list1.setItems(Wizard.getSubjectsperLevel("1"));
		list2.setItems(Wizard.getSubjectsperLevel("2"));
		list3.setItems(Wizard.getSubjectsperLevel("3"));
/////////////////////////////////////////////////////////////////////////////////////
		EventHandler<Event> addSubject = new EventHandler<Event>() 
				{

		         	@Override
		           	public void handle(Event event) 
		         	{
		         		if(Subject_name.getText().trim().length()==0)
		         		{
	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم  المادة فارغا!").message("الرجاء إدخال اسم المادة ").showError();
                            return;
		         		}
		         		if(SubjectLevel.getValue()==null)
		         		{
	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("!").message("الرجاء اختيار المستوى الداراسي للمادة").showError();
                            return;

		         		}
		         		else if(SubjectLevel.getValue().length()==0)
		         		{
	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("!").message("الرجاء اختيار المستوى الداراسي للمادة").showError();
                            return;
		         		}
/////////////////////////////////////////////////////////////////////////////////////
		         		String id =Wizard.LevelConverter(SubjectLevel.getValue()),
		         				name=Subject_name.getText().trim();
		         		String Q="insert into subjects (ID,Name,Class_level) values (Null,?,?)";
		         				 types=new ArrayList<>();
					             elements = new ArrayList<>();
					             {
					            	 elements.add(name);
					            	 types.add(Wizard.String);
					            	 elements.add(id);
					            	 types.add(Wizard.String);
					            	 
					             }
		         				
		         				;
		         		Boolean executed =DBUtil.excecuteUpdate(Q,elements,types);
						if (executed)
						{
							Dialogs.create()
									.owner(null)
									.title("رسالة إشعار")
									.masthead(null)
									.message(
											"  تمت إضافة المادة "
													+ "إلى قاعدة البيانات ")
									.showInformation();
							refreshSubjects();

						}
						else
							Dialogs.create()
									.owner(null)
									.title("رسالة خطأ")
									.masthead("لم يتم إضافة بيانات الفصل")
									.message(
											"الرجاء التأكد من صحة البيانات المدحلة ")
									.showWarning();
		         		
			         }
		        
		       };
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		AddSubjectImage.setOnMouseClicked(addSubject);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		EventHandler<Event> DeleteSubject = new EventHandler<Event>() 
				{

					@Override
					public void handle(Event event) 
					{
						if(Subject_name1.getText().trim().length()==0)
		         		{
	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("!").message("الرجاء تحديد اسم المادة التي تريد حذفها").showError();
                            return;
		         		}
		         		if(SubjectLevel1.getValue()==null)
		         		{
	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("!").message("الرجاء اختيار المستوى الداراسي للمادة").showError();
                            return;

		         		}
		         		else if(SubjectLevel1.getValue().length()==0)
		         		{
	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("!").message("الرجاء اختيار المستوى الداراسي للمادة").showError();
                            return;
		         		}
                        /////////////////////////////////////////////////////////////////////////////////////
		         		String id =Wizard.LevelConverter(SubjectLevel1.getValue()),
		         				name=Subject_name1.getText().trim();
		         		String Q="delete from subjects where name = ? and Class_level =?";
        				 types=new ArrayList<>();
			             elements = new ArrayList<>();
			             {
			            	 elements.add(name);
			            	 types.add(Wizard.String);
			            	 elements.add(id);
			            	 types.add(Wizard.String);
			             }
			             
			         	Boolean executed =DBUtil.excecuteUpdate(Q,elements,types);
						if (Wizard.numberOfUpdatedRecords!=0)
						{
							Dialogs.create()
									.owner(null)
									.title("رسالة إشعار")
									.masthead(null)
									.message(
											"  تمت حذف المادة " +name+" "
													+ "من قاعدة البيانات ")
									.showInformation();
							refreshSubjects();

						}
						else
							Dialogs.create()
									.owner(null)
									.title("رسالة خطأ")
									.masthead("لم يتم حذف المادة")
									.message(
											"الرجاء التأكد من صحة من تطايق الأسماء ")
									.showWarning();
					}
				};
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
				DeleteSubjectImage.setOnMouseClicked(DeleteSubject);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
 //Teacher Classes tab
          TeacherClassesList.setOnMouseClicked(new EventHandler<MouseEvent>() 
        		  {

					@Override
					public void handle(MouseEvent event )
					{
//						System.out.println("clicked on " + TeacherClassesList.getSelectionModel().getSelectedItem());
						TeachersList.setItems(Wizard.getTeachersperClass(Wizard.getTeacherClassID((TeacherClassesList.getSelectionModel().getSelectedItem().toString()))));
					}
		});
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
				 
//				TeacherClassesList.setCellFactory(SelectIndexFactory);
				TeacherClassesList.setItems(Wizard.getTeacherClasses());
				TeacherClasses.setItems(Wizard.getTeacherClasses());
				TeacherClasses1.setItems(Wizard.getTeacherClasses());
               TeachersNames.setItems(Wizard.getTeacherNames());
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
           	EventHandler<Event> addTeacherClass = new EventHandler<Event>() 
    				{

    		         	@Override
    		           	public void handle(Event event) 
    		         	{
    		         		if(NewTeacherClass.getText().trim().length()==0)
    		         		{
    	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم  الشعبة فارغا!").message("الرجاء إدخال اسم الشعبة ").showError();
                                return;
    		         		}
    		         		boolean executed = DBUtil.excecuteUpdate("insert into teachers_classes values(null,'"+NewTeacherClass.getText().trim()+"')");
    						if (executed)
    						{
    							Dialogs.create()
    									.owner(null)
    									.title("رسالة إشعار")
    									.masthead(null)
    									.message(
    											"  تمت إضافة الشعبة "
    													+ "إلى قاعدة البيانات ")
    									.showInformation();
    							refreshTeacherClasses();

    						}
    						else
    							Dialogs.create()
    									.owner(null)
    									.title("رسالة خطأ")
    									.masthead("لم يتم إضافة بيانات الشعبة")
    									.message(
    											"الرجاء التأكد من صحة البيانات المدحلة ")
    									.showWarning();
    			         }
    				};
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
AddTeacherClassImage.setOnMouseClicked(addTeacherClass);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    			   	EventHandler<Event>DeleteTeacherClass = new EventHandler<Event>() 
    	    				{

    	    		         	@Override
    	    		           	public void handle(Event event) 
    	    		         	{
    	    		         		if(TeacherClasses1.getValue()==null)
    	    		         		{
    	    	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("!").message("الرجاء  تحديد اسم الشعبة").showError();
    	                                return;
    	    		         		}
    	    		         		else if (TeacherClasses1.getValue().trim().length()==0)
    	    		         		{
    	    	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("!").message("الرجاء تحديد تحديد اسم الشعبة").showError();
    	                                return;
    	    		         		}
    	    		         		boolean executed = DBUtil.excecuteUpdate("delete from teachers_classes where name ='"+TeacherClasses1.getValue().trim()+"'");
    	    		         		if (executed)
    	    						{
    	    							Dialogs.create()
    	    									.owner(null)
    	    									.title("رسالة إشعار")
    	    									.masthead(null)
    	    									.message(
    	    											"  تمت حذف الشعبة " + TeacherClasses1.getValue().trim()+" "
    	    													+ "من قاعدة البيانات ")
    	    									.showInformation();
    	    							refreshTeacherClasses();

    	    						}
    	    						else
    	    							Dialogs.create()
    	    									.owner(null)
    	    									.title("رسالة خطأ")
    	    									.masthead("لم يتم حذف الشعبة")
    	    									.message(
    	    											"الرجاء التأكد  من تطايق الأسماء ")
    	    									.showWarning();
    	    		         		       TeachersNames.setItems(null);
    	    					}
    	    				};
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	      			   	EventHandler<Event> UpdateTeacherClass = new EventHandler<Event>() 
    	    	    				{

    	    	    		         	@Override
    	    	    		           	public void handle(Event event) 
    	    	    		         	{
    	    	    		         		if(TeacherClasses.getValue()==null)
    	    	    		         		{
    	    	    	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("!").message("الرجاء  تحديد اسم الشعبة").showError();
    	    	                                return;
    	    	    		         		}
    	    	    		         		else if (TeacherClasses.getValue().length()==0)
    	    	    		         		{
    	    	    	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("!").message("الرجاء تحديد تحديد اسم الشعبة").showError();
    	    	                                return;
    	    	    		         		}
    	    	    		         		if(TeachersNames.getValue()==null)
    	    	    		         		{
    	    	    	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("!").message("الرجاء  تحديد اسم المعلم").showError();
    	    	                                return;
    	    	    		         		}
    	    	    		         		else if (TeachersNames.getValue().length()==0)
    	    	    		         		{
    	    	    	        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("!").message("الرجاء تحديد تحديد اسم المعلم").showError();
    	    	                                return;
    	    	    		         		}
    	    	    		         		
    	    	    		         		String Q = "update teachers set Class_ID =? where name =?";
//    	    	    		         		System.out.println(Q);
    	    	    		         		elements  = new ArrayList<String>();
    	    	    		         		types = new ArrayList<String>();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	    	    		         		elements.add(Wizard.getTeacherClassID(TeacherClasses.getValue()));
    	    	    		         		types.add(Wizard.String);
    	    	    		         		elements.add(TeachersNames.getValue());
    	    	    		         		types.add(Wizard.String);
    	    	    		         		boolean executed = DBUtil.excecuteUpdate(Q,elements,types);
    	    	    		         		if (executed)
    	    	    						{
    	    	    							Dialogs.create()
    	    	    									.owner(null)
    	    	    									.title("رسالة إشعار")
    	    	    									.masthead(null)
    	    	    									.message(
    	    	    											"  تم نقل المعلم " +TeachersNames.getValue() +"إلى شعبة "+TeacherClasses.getValue())
    	    	    									.showInformation();
    	    	    							TeachersList.setItems(Wizard.getTeachersperClass(Wizard.getTeacherClassID((TeacherClassesList.getSelectionModel().getSelectedItem().toString()))));
                                                TeacherClassLabel.setText("لشعبة "+TeacherClassesList.getSelectionModel().getSelectedItem().toString() );

    	    	    						}
    	    	    						else
    	    	    							Dialogs.create().owner(null)
    	    	    									.title("رسالة خطأ")
    	    	    									.masthead("لم تتم عملية النقل ")
    	    	    									.message(
    	    	    											"الرجاء المحاولة في وقت لاحق")
    	    	    									.showWarning();
    	    	    		         	}
    	    	    		         	};
    	    	    		         	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	 ConfirmEditImage.setOnMouseClicked(UpdateTeacherClass);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
 	
    	   	EventHandler<Event> addNewTeacher = new EventHandler<Event>() 
    				{

    		         	@Override
    		           	public void handle(Event event) 
    		         	{
    		         		myController.setScreen("Teacher_Tab","1");
    		         	}
    		        };
    		        AddnewTeacherLabel.setOnMouseClicked(addNewTeacher);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
    		        EventHandler<Event> FindAll = new EventHandler<Event>() 
    		      		   {
    		     		
    		     		@Override
    		     		public void handle(Event arg0) 
    		     		{
    		     				result = (ObservableList<SClasses>) JDBC.fill_Otabel("select * from classes",Wizard.SClass,null,null);
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////

       DeleteTeacherClassImage.setOnMouseClicked(DeleteTeacherClass);
    	    		
               
}
	public void refreshTeacherClasses() 
	{
		TeacherClassesList.setItems(Wizard.getTeacherClasses());
		TeacherClasses.setItems(Wizard.getTeacherClasses());
		TeacherClasses1.setItems(Wizard.getTeacherClasses());

	}
	public void refreshTeacherslist() 
	{
		TeachersList.setItems(Wizard.getTeachersperClass(""));
	}
	

	public void refreshSubjects() 
	{
		list1.setItems(Wizard.getSubjectsperLevel("1"));
		list2.setItems(Wizard.getSubjectsperLevel("2"));
		list3.setItems(Wizard.getSubjectsperLevel("3"));
	}
	
	@Override
	public void initialize_onshow()
	{
//		//we have to refresh  teachers list every time a teacher is added to the database 
		NewSupervisor.setItems(Wizard.getTeacherNames());
	}
	
    @FXML
    private TextField NewStudyingFees;

    @FXML
    private TextField Class_ID;

    @FXML
    private ComboBox <String> SearchLevels;

    @FXML
    private TextField NewFatherSeetsFees;

    @FXML
    private TableView tabel;

    @FXML
    private TableColumn Name;

    @FXML
    private ComboBox <String>NewClassLevel;

    @FXML
    private ComboBox <String> NewSupervisor;

    @FXML
    private ImageView AddClassPhoto;

    @FXML
    private ImageView SearchIcon;

    @FXML
    private TextField Class_name;
    @FXML
    private Label AddClassLabel;
    @FXML
    private TableColumn ID;
    @FXML
    private TableColumn ClassLevel;
    @FXML
    private TextField NewClassName;
    @FXML
    private Label SearchLabel;

    @FXML
    private TextField NewCapacity;

    @FXML
    private TextField NewSummerCourseFees;

    
////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private ListView list1;
    @FXML
    private ListView list2;
    @FXML
    private ListView list3;
    @FXML
    private ImageView DeleteSubjectImage;
     @FXML
    private ImageView AddSubjectImage;
    @FXML
    private ComboBox<String> SubjectLevel;
    @FXML
    private ComboBox<String> SubjectLevel1;
    @FXML
    private  TextField Subject_name;
    @FXML
    private TextField Subject_name1;
////////////////////////////////////////////////////////////////////////////////////////
  @FXML
  private ImageView AddTeacherClassImage;
  @FXML
  private TextField NewTeacherClass;
  
  @FXML
  private ImageView DeleteTeacherClassImage;
  @FXML
  private ListView TeacherClassesList;
  @FXML
  private ListView TeachersList;
  @FXML
  private Label TeacherClassLabel;
  @FXML
  private Label AddnewTeacherLabel;
  @FXML
  private ComboBox<String> TeacherClasses;
  @FXML
  private ComboBox<String> TeacherClasses1;
  @FXML
  private ComboBox<String> TeachersNames;
  @FXML
  private ImageView ConfirmEditImage;
  @FXML 
  private  ImageView ShowAllImage;
  @FXML 
  private Label ShowAllLabel;

   }
