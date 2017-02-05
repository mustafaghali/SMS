package Controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.dialog.Dialogs;

import databaseUtilities.DBUtil;
import databaseUtilities.JDBC;
import wizards.Wizard;
import models.GeneralPayment;
import models.Result;
import models.StudentPayment;
import models.TeacherPayment;
import models.WorkerPayment;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class Finance_Controller extends Controlled_Screen implements Initializable 
   {
	  //temp observable list  
			ObservableList resultObservableList;			
			List<String> types;
			List<String> elements;

			String GeneralPaymentTypeString = null;
			String GeneralPaymentDateString = null;
			String GeneralPaymentPartyString = null;
			String Qa1= "SELECT * FROM general_transactions WHERE date = ?" ;
			String Qa2= "SELECT * FROM general_transactions WHERE type like ?";
			String Qa3="SELECT * FROM general_transactions WHERE party like ?";
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            String StudentPaymentTypeString = null;
			String StudentPaymentDateString = null;
			String StudentPaymentIDString = null;
			String Qb1= "SELECT * FROM student_transactions WHERE the_date = ?" ;
			String Qb2= "SELECT * FROM student_transactions WHERE type = ?";
			String Qb3="SELECT * FROM student_transactions WHERE student_ID = ?";
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			   String TeacherPaymentTypeString = null;
				String TeacherPaymentDateString = null;
				String TeacherPaymentIDString = null;
				String Qc1= "SELECT * FROM teacher_transactions WHERE the_date = ?" ;
				String Qc2= "SELECT * FROM teacher_transactions WHERE type like ?";
				String Qc3="SELECT * FROM teacher_transactions WHERE teacher_ID = ?";
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				   String WorkerPaymentTypeString = null;
					String WorkerPaymentDateString = null;
					String WorkerPaymentIDString = null;
					String Qd1= "SELECT * FROM Workers_transactions WHERE the_date = ?" ;
					String Qd2= "SELECT * FROM Workers_transactions WHERE type like ?";
					String Qd3="SELECT * FROM Workers_transactions WHERE Worker_ID = ?";
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//					public void triggerGeneralPaymentSearch() 
//					{
//						 types = new ArrayList<String>();
//						 elements = new ArrayList<String>();
//							
//							
//							 if( GeneralPaymentDate.getValue() != null)
//								 GeneralPaymentDateString = GeneralPaymentDate.getValue().toString().trim();
//							      GeneralPaymentPartyString = GeneralPaymentParty.getValue();
//							      GeneralPaymentTypeString = GeneralPaymentType.getValue();
//
//							      query = getSearchGeneralPaymentQuery();
//							      
//							  	if (query==null) 
//								{
//									Dialogs.create()
//											.owner(null)
//											.title("إشعار")
//											.message(
//													"الرجاء أدخال بيانات حقل واحد على الأقل")
//											.showWarning();
//									resultObservableList=  FXCollections.observableArrayList();
//		                           GeneralPaymentTable.setItems(resultObservableList);
//
//									return;
//								}
//								
//								try {
//								
//									//System.out.println(query);
//									resultObservableList = (ObservableList<GeneralPayment>) JDBC.fill_Otabel(query,
//											Wizard.GeneralPayment, elements, types);
//									if (resultObservableList.size()==0)
//									{
//										Dialogs.create()
//						                .owner(null)
//						                .title("نتيجة فارغة")
//						                .message("لا توجد نتيجة توافق البيانات المدخلة الرجاء التأكد من صحة البيانات المدخلة")
//						                .showWarning();
//										resultObservableList=  FXCollections.observableArrayList();
//				                         GeneralPaymentTable.setItems(resultObservableList);
//									}
//									GeneralPaymentTable.setItems(resultObservableList);
//									GeneralPaymentresult = resultObservableList;
//								} 
//								catch (Exception e) 
//								{
//									System.out.println(e.getMessage());
//									System.out.println("error retriving observable list in Result tab after GeneralPayment search");
//								}
//					}
//						
//					public void triggerTeacherPaymentSearch() 
//    				{
//    					 types = new ArrayList<String>();
//    					 elements = new ArrayList<String>();
//    						
//    						
//    						 if( TeacherPaymentDate.getValue() != null)
//    							 TeacherPaymentDateString = TeacherPaymentDate.getValue().toString().trim();
//    						 if(TeacherName.getValue() !=null)
//    						 {
//    							 if (!TeacherName.getValue().equals(""))
//    						      TeacherPaymentIDString = Wizard.getTeacherID(TeacherName.getValue());
//    						 }
//    						      TeacherPaymentTypeString = TeacherPaymentType.getValue();
//
//    						      query = getSearchTeacherPaymentQuery();
//    						      
//    						    if (query==null) 
//    							{
//    						    	Dialogs.create()
//									.owner(null)
//									.title("إشعار")
//									.message(
//											"الرجاء أدخال بيانات حقل واحد على الأقل")
//									.showWarning();
//  								resultObservableList=  FXCollections.observableArrayList();
//                                TeacherTable.setItems(resultObservableList);
//    								return;
//    							}
//    							
//    							try {
//    							
//    								//System.out.println(query);
//    								resultObservableList = (ObservableList<TeacherPayment>) JDBC.fill_Otabel(query,
//    										Wizard.TeacherPayment, elements, types);
//    								if (resultObservableList.size()==0)
//    								{
//    									Dialogs.create()
//    					                .owner(null)
//    					                .title("نتيجة فارغة")
//    					                .message("لا توجد نتيجة توافق البيانات المدخلة الرجاء التأكد من صحة البيانات المدخلة")
//    					                .showWarning();
//    									resultObservableList = FXCollections.observableArrayList();
//                                      TeacherTable.setItems(resultObservableList);
//
//    								}
//    								else
//    								{
//    								TeacherTable.setItems(resultObservableList);
//    								TeacherPaymentresult = resultObservableList;
//    							} 
//    							}
//    							catch (Exception e) 
//    							{
//    								System.out.println(e.getMessage());
//    								System.out.println("error retriving observable list in Result tab after TeacherPayment search");
//    							}
//    				}
//    				
//					public void triggerStudentPaymentSearch() 
//    				{
//    					 types = new ArrayList<String>();
//    					 elements = new ArrayList<String>();
//    						
//    						
//    						 if( StudentPaymentDate.getValue() != null)
//    							 StudentPaymentDateString = StudentPaymentDate.getValue().toString().trim();
//    						 if(StudentName.getValue() !=null)
//    						 {
//    							 if (!StudentName.getValue().equals(""))
//    						      StudentPaymentIDString = Wizard.getStudentID(StudentName.getValue());
//    						 }
//    						      StudentPaymentTypeString = StudentPaymentType.getValue();
//
//    						      query = getSearchStudentPaymentQuery();
//    						      
//    						    if (query==null) 
//    							{
//    						    	Dialogs.create()
//									.owner(null)
//									.title("إشعار")
//									.message(
//											"الرجاء أدخال بيانات حقل واحد على الأقل")
//									.showWarning();
//  								resultObservableList=  FXCollections.observableArrayList();
//                                StudentTable.setItems(resultObservableList);
//    								return;
//    							}
//    							
//    							try {
//    							
//    								//System.out.println(query);
//    								resultObservableList = (ObservableList<StudentPayment>) JDBC.fill_Otabel(query,
//    										Wizard.StudentPayment, elements, types);
//    								if (resultObservableList.size()==0)
//    								{
//    									Dialogs.create()
//    					                .owner(null)
//    					                .title("نتيجة فارغة")
//    					                .message("لا توجد نتيجة توافق البيانات المدخلة الرجاء التأكد من صحة البيانات المدخلة");
//    									resultObservableList = FXCollections.observableArrayList();
//                                      StudentTable.setItems(resultObservableList);
//
//    								}
//    								else
//    								{
//    								StudentTable.setItems(resultObservableList);
//    								StudentPaymentresult = resultObservableList;
//    								}
//    							} 
//    							catch (Exception e) 
//    							{
//    								System.out.println(e.getMessage());
//    								System.out.println("error retriving observable list in Result tab after StudentPayment search");
//    							}
//    				}
//    				
//					public void triggerWorkerPaymentSearch() 
//    				{
//    					 types = new ArrayList<String>();
//    					 elements = new ArrayList<String>();
//    						
//    						
//    						 if( WorkerPaymentDate.getValue() != null)
//    							 WorkerPaymentDateString = WorkerPaymentDate.getValue().toString().trim();
//    						 if(WorkerName.getValue() !=null)
//    						 {
//    							 if (!WorkerName.getValue().equals(""))
//    						      WorkerPaymentIDString = Wizard.getWorkerID(WorkerName.getValue());
//    						 }
//    						      WorkerPaymentTypeString = WorkerPaymentType.getValue();
//
//    						      query = getSearchWorkerPaymentQuery();
//    						      
//    						    if (query==null) 
//    							{
//    						    	Dialogs.create()
//									.owner(null)
//									.title("إشعار")
//									.message(
//											"الرجاء أدخال بيانات حقل واحد على الأقل")
//									.showWarning();
//  								resultObservableList=  FXCollections.observableArrayList();
//                                WorkerTable.setItems(resultObservableList);
//    								return;
//    							}
//    							
//    							try {
//    							
//    								//System.out.println(query);
//    								resultObservableList = (ObservableList<WorkerPayment>) JDBC.fill_Otabel(query,
//    										Wizard.WorkerPayment, elements, types);
//    								if (resultObservableList.size()==0)
//    								{
//    									Dialogs.create()
//    					                .owner(null)
//    					                .title("نتيجة فارغة")
//    					                .message("لا توجد نتيجة توافق البيانات المدخلة الرجاء التأكد من صحة البيانات المدخلة");
//    									resultObservableList = FXCollections.observableArrayList();
//                                      WorkerTable.setItems(resultObservableList);
//
//    								}
//    								else
//    								{
//    								WorkerTable.setItems(resultObservableList);
//    								WorkerPaymentresult = resultObservableList;
//    							    } 
//    							}
//    							catch (Exception e) 
//    							{
//    								System.out.println(e.getMessage());
//    								System.out.println("error retriving observable list in Result tab after WorkerPayment search");
//    							}
//    				}
//					
					
			@Override
			public void clear() 
			{	
				    StudentTable.setItems(FXCollections.observableArrayList());
				    TeacherTable.setItems(FXCollections.observableArrayList());
				    WorkerTable.setItems(FXCollections.observableArrayList());
				    GeneralPaymentTable.setItems(FXCollections.observableArrayList());
//			        NewDestination.clear();
//			        NewDriver.clear();
//			        NewFees.clear();
//			        newvehicle.setValue(null);
			}
			
			private void clearStudentAddition() 
			{
              NewStudentName.setValue(null);
              NewStudentPaymentDate.setValue(null);
              NewStudentPaymentNotes.clear();
              NewStudentPaymentType.setValue(null);
              NewStudentPaymentVlaue.clear();
			}
			
			private void clearTeacherAddition() 
			{
              NewTeacherName.setValue(null);
              NewTeacherPaymentDate.setValue(null);
              NewTeacherPaymentNotes.clear();
              NewTeacherPaymentType.setValue(null);
              NewTeacherPaymentVlaue.clear();
			}
			
			private void clearWorkerAddition() 
			{
              NewWorkerName.setValue(null);
              NewWorkerPaymentDate.setValue(null);
              NewWorkerPaymentNotes.clear();
              NewWorkerPaymentType.setValue(null);
              NewWorkerPaymentVlaue.clear();
			}
				
			private void clearGeneralPaymentAddition() 
			{
              NewGeneralPaymentDate.setValue(null);
              NewParty.setValue(null);
              NewGeneralPaymentNotes.clear();
              NewGeneralPaymentType.setValue(null);
              NewGeneralPaymentVlaue.clear();
			}
			
			
			public  String getSearchGeneralPaymentQuery()
			{
				query = null;
				if (GeneralPaymentDateString != null && GeneralPaymentTypeString!= null && GeneralPaymentPartyString!= null) 
				{
					if(!(GeneralPaymentDateString.length()== 0 || GeneralPaymentTypeString.length() == 0 || GeneralPaymentPartyString.length()==0))
					{
					  elements.add(GeneralPaymentDateString);
					  types.add(Wizard.Date);
					  elements.add(Wizard.GeneralPaymentPartyConverter(GeneralPaymentPartyString));
					  types.add(Wizard.LikeString);

					query = Qa1+" UNION "+Qa3;
					return query ;
					}
				} 
			    if (GeneralPaymentDateString!= null && GeneralPaymentTypeString!= null)
			    {
					if(!(GeneralPaymentDateString.length()== 0 || GeneralPaymentTypeString.length() == 0))
					{
					elements.add(GeneralPaymentDateString);
					types.add(Wizard.Date);
					elements.add(Wizard.GeneralPaymentTypeConverter(GeneralPaymentTypeString));
					types.add(Wizard.LikeString);
					query = Qa1+" UNION "+Qa2;
					return query ;
					}
				} 
			    if (GeneralPaymentTypeString!= null && GeneralPaymentPartyString!= null) 
				{
					if(!(GeneralPaymentTypeString.length() == 0 || GeneralPaymentPartyString.length()==0))
					{
					
					elements.add(Wizard.GeneralPaymentPartyConverter(GeneralPaymentPartyString));
					types.add(Wizard.LikeString);
					query = Qa3;
					return query ;
					}
				} 
			    if (GeneralPaymentDateString!= null && GeneralPaymentPartyString!= null)
				{
					if(!(GeneralPaymentDateString.length()== 0 || GeneralPaymentPartyString.length()==0))
					{
							elements.add(GeneralPaymentDateString);
							 types.add(Wizard.Date);
							 elements.add(Wizard.GeneralPaymentPartyConverter(GeneralPaymentPartyString));
						     types.add(Wizard.LikeString);
							query = Qa1 + " UNION " + Qa3;
							return query;
					}
				} 
			    if (GeneralPaymentDateString!= null)
				{
					if(GeneralPaymentDateString.length()!= 0)
					{
					elements.add(GeneralPaymentDateString);
					types.add(Wizard.Date);
					query = Qa1;
					return query ;
					}
				} 
			    if (GeneralPaymentTypeString!= null) //temp2 only
				{
			    	if(GeneralPaymentTypeString.length()!= 0)
					{
					elements.add(Wizard.GeneralPaymentTypeConverter(GeneralPaymentTypeString));
					types.add(Wizard.LikeString);
					query =Qa2;
					return query ;
					}
				} 
				 if (GeneralPaymentPartyString!= null)  //GeneralPaymentPartyString only 
				{
					 if(GeneralPaymentPartyString.length()!= 0)
						{
					       elements.add(Wizard.GeneralPaymentPartyConverter(GeneralPaymentPartyString));
					       types.add(Wizard.LikeString);
					       query = Qa3;
					      return query ;
						}
				}
				return query;
			}
/////////////////////////////////////////////////////////////////////////////////////
			public  String getSearchTeacherPaymentQuery()
			{
				query = null;
				if (TeacherPaymentDateString != null && TeacherPaymentTypeString!= null && TeacherPaymentIDString!= null) 
				{
					if(!(TeacherPaymentDateString.length()== 0 || TeacherPaymentTypeString.length() == 0 || TeacherPaymentIDString.length()==0))
					{
					  elements.add(TeacherPaymentDateString);
					  types.add(Wizard.Date);
					  elements.add(Wizard.TeacherPaymentTypeConverter(TeacherPaymentTypeString));
					  types.add(Wizard.LikeString);
					  elements.add(TeacherPaymentIDString);
					  types.add(Wizard.Integer);

					query = Qc1+" UNION "+Qc2+" UNION "+Qc3;
					return query ;
					}
				} 
			    if (TeacherPaymentDateString!= null && TeacherPaymentTypeString!= null)
			    {
					if(!(TeacherPaymentDateString.length()== 0 || TeacherPaymentTypeString.length() == 0))
					{
					elements.add(TeacherPaymentDateString);
					types.add(Wizard.Date);
					elements.add(Wizard.TeacherPaymentTypeConverter(TeacherPaymentTypeString));
					types.add(Wizard.LikeString);
					query = Qc1+" UNION "+Qc2;
					return query ;
					}
				} 
			    if (TeacherPaymentTypeString!= null && TeacherPaymentIDString!= null) 
				{
					if(!(TeacherPaymentTypeString.length() == 0 || TeacherPaymentIDString.length()==0))
					{
					elements.add(Wizard.TeacherPaymentTypeConverter(TeacherPaymentTypeString));
					types.add(Wizard.LikeString);
					elements.add(TeacherPaymentIDString);
					types.add(Wizard.Integer);
					query = Qc2+" UNION "+Qc3;
					return query ;
					}
				} 
			    if (TeacherPaymentDateString!= null && TeacherPaymentIDString!= null)
				{
					if(!(TeacherPaymentDateString.length()== 0 || TeacherPaymentIDString.length()==0))
					{
							elements.add(TeacherPaymentDateString);
							 types.add(Wizard.Date);
							 elements.add(TeacherPaymentIDString);
						     types.add(Wizard.Integer);
							query = Qc1 + " UNION " + Qc3;
							return query;
					}
				} 
			    if (TeacherPaymentDateString!= null)
				{
					if(TeacherPaymentDateString.length()!= 0)
					{
					elements.add(TeacherPaymentDateString);
					types.add(Wizard.Date);
					query = Qc1;
					return query ;
					}
				} 
			    if (TeacherPaymentTypeString!= null) //temp2 only
				{
			    	if(TeacherPaymentTypeString.length()!= 0)
					{
					elements.add(Wizard.TeacherPaymentTypeConverter(TeacherPaymentTypeString));
					types.add(Wizard.LikeString);
					query =Qc2;
					return query ;
					}
				} 
				 if (TeacherPaymentIDString!= null)  //TeacherPaymentIDString only 
				{
					 if(TeacherPaymentIDString.length()!= 0)
						{
					       elements.add(TeacherPaymentIDString);
					       types.add(Wizard.Integer);
					       query = Qc3;
					       
					      return query ;
						}
				}
				return query;
			}
/////////////////////////////////////////////////////////////////////////////////////
			public  String getSearchStudentPaymentQuery()
			{
				query = null;
				if (StudentPaymentDateString != null && StudentPaymentTypeString!= null && StudentPaymentIDString!= null) 
				{
					if(!(StudentPaymentDateString.length()== 0 || StudentPaymentTypeString.length() == 0 || StudentPaymentIDString.length()==0))
					{
					  elements.add(StudentPaymentDateString);
					  types.add(Wizard.Date);
					  elements.add(Wizard.StudentPaymentTypeConverter(StudentPaymentTypeString));
					  types.add(Wizard.String);
					  elements.add(StudentPaymentIDString);
					  types.add(Wizard.Integer);

					query = Qb1+" UNION "+Qb2+" UNION "+Qb3;
					return query ;
					}
				} 
			    if (StudentPaymentDateString!= null && StudentPaymentTypeString!= null)
			    {
					if(!(StudentPaymentDateString.length()== 0 || StudentPaymentTypeString.length() == 0))
					{
					elements.add(StudentPaymentDateString);
					types.add(Wizard.Date);
					elements.add(Wizard.StudentPaymentTypeConverter(StudentPaymentTypeString));
					types.add(Wizard.String);
					query = Qb1+" UNION "+Qb2;
					return query ;
					}
				} 
			    if (StudentPaymentTypeString!= null && StudentPaymentIDString!= null) 
				{
					if(!(StudentPaymentTypeString.length() == 0 || StudentPaymentIDString.length()==0))
					{
					elements.add(Wizard.StudentPaymentTypeConverter(StudentPaymentTypeString));
					types.add(Wizard.String);
					elements.add(StudentPaymentIDString);
					types.add(Wizard.Integer);
					query = Qb2+" UNION "+Qb3;
					return query ;
					}
				} 
			    if (StudentPaymentDateString!= null && StudentPaymentIDString!= null)
				{
					if(!(StudentPaymentDateString.length()== 0 || StudentPaymentIDString.length()==0))
					{
							elements.add(StudentPaymentDateString);
							 types.add(Wizard.Date);
							 elements.add(StudentPaymentIDString);
						     types.add(Wizard.Integer);
							query = Qb1 + " UNION " + Qb3;
							return query;
					}
				} 
			    if (StudentPaymentDateString!= null)
				{
					if(StudentPaymentDateString.length()!= 0)
					{
					elements.add(StudentPaymentDateString);
					types.add(Wizard.Date);
					query = Qb1;
					return query ;
					}
				} 
			    if (StudentPaymentTypeString!= null) //temp2 only
				{
			    	if(StudentPaymentTypeString.length()!= 0)
					{
					elements.add(Wizard.StudentPaymentTypeConverter(StudentPaymentTypeString));
					types.add(Wizard.String);
					query =Qb2;
					return query ;
					}
				} 
				 if (StudentPaymentIDString!= null)  //StudentPaymentIDString only 
				{
					 if(StudentPaymentIDString.length()!= 0)
						{
					       elements.add(StudentPaymentIDString);
					       types.add(Wizard.Integer);
					       query = Qb3;
					      return query ;
						}
				}
				return query;
			}
/////////////////////////////////////////////////////////////////////////////////////
			public  String getSearchWorkerPaymentQuery()
			{
				query = null;
				if (WorkerPaymentDateString != null && WorkerPaymentTypeString!= null && WorkerPaymentIDString!= null) 
				{
					if(!(WorkerPaymentDateString.length()== 0 || WorkerPaymentTypeString.length() == 0 || WorkerPaymentIDString.length()==0))
					{
					  elements.add(WorkerPaymentDateString);
					  types.add(Wizard.Date);
					  elements.add(Wizard.WorkerPaymentTypeConverter(WorkerPaymentTypeString));
					  types.add(Wizard.LikeString);
					  elements.add(WorkerPaymentIDString);
					  types.add(Wizard.Integer);

					query = Qd1+" UNION "+Qd2+" UNION "+Qd3;
					return query ;
					}
				} 
			    if (WorkerPaymentDateString!= null && WorkerPaymentTypeString!= null)
			    {
					if(!(WorkerPaymentDateString.length()== 0 || WorkerPaymentTypeString.length() == 0))
					{
					elements.add(WorkerPaymentDateString);
					types.add(Wizard.Date);
					elements.add(Wizard.WorkerPaymentTypeConverter(WorkerPaymentTypeString));
					types.add(Wizard.LikeString);
					query = Qd1+" UNION "+Qd2;
					return query ;
					}
				} 
			    if (WorkerPaymentTypeString!= null && WorkerPaymentIDString!= null) 
				{
					if(!(WorkerPaymentTypeString.length() == 0 || WorkerPaymentIDString.length()==0))
					{
					elements.add(Wizard.WorkerPaymentTypeConverter(WorkerPaymentTypeString));
					types.add(Wizard.LikeString);
					elements.add(WorkerPaymentIDString);
					types.add(Wizard.Integer);
					query = Qd2+" UNION "+Qd3;
					return query ;
					}
				} 
			    if (WorkerPaymentDateString!= null && WorkerPaymentIDString!= null)
				{
					if(!(WorkerPaymentDateString.length()== 0 || WorkerPaymentIDString.length()==0))
					{
							elements.add(WorkerPaymentDateString);
							 types.add(Wizard.Date);
							 elements.add(WorkerPaymentIDString);
						     types.add(Wizard.Integer);
							query = Qd1 + " UNION " + Qd3;
							return query;
					}
				} 
			    if (WorkerPaymentDateString!= null)
				{
					if(WorkerPaymentDateString.length()!= 0)
					{
					elements.add(WorkerPaymentDateString);
					types.add(Wizard.Date);
					query = Qd1;
					return query ;
					}
				} 
			    if (WorkerPaymentTypeString!= null) //temp2 only
				{
			    	if(WorkerPaymentTypeString.length()!= 0)
					{
					elements.add(Wizard.WorkerPaymentTypeConverter(WorkerPaymentTypeString));
					types.add(Wizard.LikeString);
					query =Qd2;
					return query ;
					}
				} 
				 if (WorkerPaymentIDString!= null)  //WorkerPaymentIDString only 
				{
					 if(WorkerPaymentIDString.length()!= 0)
						{
					       elements.add(WorkerPaymentIDString);
					       types.add(Wizard.Integer);
					       query = Qd3;
					      return query ;
						}
				}
				return query;
			}
			
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
            GeneralPaymentDate.setConverter(Wizard.DatePickerConverter ());
            NewGeneralPaymentDate.setConverter(Wizard.DatePickerConverter ());
            NewStudentPaymentDate.setConverter(Wizard.DatePickerConverter ());
            StudentPaymentDate.setConverter(Wizard.DatePickerConverter ());
            NewTeacherPaymentDate.setConverter(Wizard.DatePickerConverter ());
            TeacherPaymentDate.setConverter(Wizard.DatePickerConverter ());
/////////////////////////////////////////////////////////////////////////////////////
            GeneralPaymentTypeColumn.setCellValueFactory(new PropertyValueFactory<GeneralPayment, String>("type"));
            GeneralPaymentTypeColumn.setCellFactory(GeneralPaymentTransitionEventFactory);
            PartyColumn.setCellValueFactory(new PropertyValueFactory<GeneralPayment, String>("Party"));
            PartyColumn.setCellFactory(GeneralPaymentTransitionEventFactory);
            GeneralValueColumn.setCellValueFactory(new PropertyValueFactory<GeneralPayment,Double>("Value"));
            GeneralValueColumn.setCellFactory(GeneralPaymentTransitionEventFactoryForDoubles);
            GeneralPaymentDateColumn.setCellValueFactory(new PropertyValueFactory<GeneralPayment, String>("date"));
            GeneralPaymentDateColumn.setCellFactory(GeneralPaymentTransitionEventFactory);
/////////////////////////////////////////////////////////////////////////////////////
            StudentNameColumn.setCellValueFactory(new PropertyValueFactory<StudentPayment, String>("StudentName"));
            StudentNameColumn.setCellFactory(StudentPaymentTransitionEventFactory);
            StudentClassColumn.setCellValueFactory(new PropertyValueFactory<StudentPayment, String>("StudentClassName"));
            StudentClassColumn.setCellFactory(StudentPaymentTransitionEventFactory);
            StudentValueColumn.setCellValueFactory(new PropertyValueFactory<StudentPayment, Double>("Value"));
            StudentValueColumn.setCellFactory(StudentPaymentTransitionEventFactoryForDouble);
            StudentPaymentDateColumn.setCellValueFactory(new PropertyValueFactory<StudentPayment, String>("date"));
            StudentPaymentDateColumn.setCellFactory(StudentPaymentTransitionEventFactory);
            StudentPaymentTypeColumn.setCellValueFactory(new PropertyValueFactory<StudentPayment, String>("type"));
            StudentPaymentTypeColumn.setCellFactory(StudentPaymentTransitionEventFactory);
/////////////////////////////////////////////////////////////////////////////////////
            TeacherNameColumn.setCellValueFactory(new PropertyValueFactory<TeacherPayment, String>("TeacherName"));
            TeacherNameColumn.setCellFactory(TeacherPaymentTransitionEventFactory);
            TeacherClassColumn.setCellValueFactory(new PropertyValueFactory<TeacherPayment, String>("TeacherClassName"));
            TeacherClassColumn.setCellFactory(TeacherPaymentTransitionEventFactory);
            TeacherValueColumn.setCellValueFactory(new PropertyValueFactory<TeacherPayment, Double>("Value"));
            TeacherValueColumn.setCellFactory(TeacherPaymentTransitionEventFactoryForDoubles);
            TeacherPaymentDateColumn.setCellValueFactory(new PropertyValueFactory<TeacherPayment, String>("date"));
            TeacherPaymentDateColumn.setCellFactory(TeacherPaymentTransitionEventFactory);
            TeacherPaymentTypeColumn.setCellValueFactory(new PropertyValueFactory<TeacherPayment, String>("type"));
            TeacherPaymentTypeColumn.setCellFactory(TeacherPaymentTransitionEventFactory);
/////////////////////////////////////////////////////////////////////////////////////
            WorkerNameColumn.setCellValueFactory(new PropertyValueFactory<WorkerPayment, String>("WorkerName"));
            WorkerNameColumn.setCellFactory(WorkerPaymentTransitionEventFactory);
            WorkerValueColumn.setCellValueFactory(new PropertyValueFactory<WorkerPayment, Double>("Value"));
            WorkerValueColumn.setCellFactory(WorkerPaymentTransitionEventFactoryForDoubles);
            WorkerPaymentDateColumn.setCellValueFactory(new PropertyValueFactory<WorkerPayment, String>("date"));
            WorkerPaymentDateColumn.setCellFactory(WorkerPaymentTransitionEventFactory);
            WorkerPaymentTypeColumn.setCellValueFactory(new PropertyValueFactory<WorkerPayment, String>("type"));
            WorkerPaymentTypeColumn.setCellFactory(WorkerPaymentTransitionEventFactory);
            WorkerJobColumn.setCellValueFactory(new PropertyValueFactory<WorkerPayment, String>("JobType"));
            WorkerJobColumn.setCellFactory(WorkerPaymentTransitionEventFactory);
/////////////////////////////////////////////////////////////////////////////////////
		// search general Payment icon handler
		EventHandler<Event> SearchGeneralPayment = new EventHandler<Event>()
		{
			@Override
			public void handle(Event arg0) 
			{
				 types = new ArrayList<String>();
				 elements = new ArrayList<String>();
					
					
					 if( GeneralPaymentDate.getValue() != null)
						 GeneralPaymentDateString = GeneralPaymentDate.getValue().toString().trim();
					      GeneralPaymentPartyString = GeneralPaymentParty.getValue();
					      GeneralPaymentTypeString = GeneralPaymentType.getValue();

					      query = getSearchGeneralPaymentQuery();
					      
					  	if (query==null) 
						{
							Dialogs.create()
									.owner(null)
									.title("إشعار")
									.message(
											"الرجاء أدخال بيانات حقل واحد على الأقل")
									.showWarning();
							resultObservableList=  FXCollections.observableArrayList();
                           GeneralPaymentTable.setItems(resultObservableList);

							return;
						}
						
						try {
						
							//System.out.println(query);
							resultObservableList = (ObservableList<GeneralPayment>) JDBC.fill_Otabel(query,
									Wizard.GeneralPayment, elements, types);
							if (resultObservableList.size()==0)
							{
								Dialogs.create()
				                .owner(null)
				                .title("نتيجة فارغة")
				                .message("لا توجد نتيجة توافق البيانات المدخلة الرجاء التأكد من صحة البيانات المدخلة")
				                .showWarning();
								resultObservableList=  FXCollections.observableArrayList();
		                         GeneralPaymentTable.setItems(resultObservableList);
							}
							GeneralPaymentTable.setItems(resultObservableList);
							GeneralPaymentresult = resultObservableList;
						} 
						catch (Exception e) 
						{
							System.out.println(e.getMessage());
							System.out.println("error retriving observable list in Result tab after GeneralPayment search");
						}
			}
				
		};
          SearchGeneralPaymentIcon.setOnMouseClicked(SearchGeneralPayment);
/////////////////////////////////////////////////////////////////////////////////////
          EventHandler<Event> SearchTeacherPaymentHandler = new EventHandler<Event>()
        			{
        				@Override
        				public void handle(Event arg0) 
        				{
        					 types = new ArrayList<String>();
        					 elements = new ArrayList<String>();
        						
        						
        						 if( TeacherPaymentDate.getValue() != null)
        							 TeacherPaymentDateString = TeacherPaymentDate.getValue().toString().trim();
        						 if(TeacherName.getValue() !=null)
        						 {
        							 if (!TeacherName.getValue().equals(""))
        						      TeacherPaymentIDString = Wizard.getTeacherID(TeacherName.getValue());
        							 else 
        								 TeacherPaymentIDString = "";
        						 }
        						      TeacherPaymentTypeString = TeacherPaymentType.getValue();

        						      query = getSearchTeacherPaymentQuery();
        						      
        						    if (query==null) 
        							{
        						    	Dialogs.create()
    									.owner(null)
    									.title("إشعار")
    									.message(
    											"الرجاء أدخال بيانات حقل واحد على الأقل")
    									.showWarning();
      								resultObservableList=  FXCollections.observableArrayList();
                                    TeacherTable.setItems(resultObservableList);
        								return;
        							}
        							
        							try {
        							
        								//System.out.println(query);
        								resultObservableList = (ObservableList<TeacherPayment>) JDBC.fill_Otabel(query,
        										Wizard.TeacherPayment, elements, types);
        								if (resultObservableList.size()==0)
        								{
        									Dialogs.create()
        					                .owner(null)
        					                .title("نتيجة فارغة")
        					                .message("لا توجد نتيجة توافق البيانات المدخلة الرجاء التأكد من صحة البيانات المدخلة")
        					                .showWarning();
        									resultObservableList = FXCollections.observableArrayList();
                                          TeacherTable.setItems(resultObservableList);

        								}
        								else
        								{
        								TeacherTable.setItems(resultObservableList);
        								TeacherPaymentresult = resultObservableList;
        							} 
        							}
        							catch (Exception e) 
        							{
        								System.out.println(e.getMessage());
        								System.out.println("error retriving observable list in Result tab after TeacherPayment search");
        							}
        				}
        					
        			};
          	          SearchTeacherPayment.setOnMouseClicked(SearchTeacherPaymentHandler);
          	          
/////////////////////////////////////////////////////////////////////////////////////
          	        EventHandler<Event> SearchStudentPaymentHandler = new EventHandler<Event>()
                			{
                				@Override
                				public void handle(Event arg0) 
                				{
                					 types = new ArrayList<String>();
                					 elements = new ArrayList<String>();
                						
                						
                						 if( StudentPaymentDate.getValue() != null)
                							 StudentPaymentDateString = StudentPaymentDate.getValue().toString().trim();
                						 if(StudentName.getValue() !=null)
                						 {
                							  if (!StudentName.getValue().equals(""))
                						      StudentPaymentIDString = Wizard.getStudentID(StudentName.getValue());
                							  else 
                								  StudentPaymentIDString ="";
                						 }
                						    StudentPaymentTypeString = StudentPaymentType.getValue();

                						      query = getSearchStudentPaymentQuery();
//                						      System.out.println(query);
//                						      System.out.println(types);
//                						      System.out.println(elements);
                						    if (query==null) 
                							{
                						    	Dialogs.create()
            									.owner(null)
            									.title("إشعار")
            									.message(
            											"الرجاء أدخال بيانات حقل واحد على الأقل عدا بيانات فصل الطالب")
            									.showWarning();
              								resultObservableList=  FXCollections.observableArrayList();
                                            StudentTable.setItems(resultObservableList);
                								return;
                							}
                							
                							try {
                							
                								//System.out.println(query);
                								resultObservableList = (ObservableList<StudentPayment>) JDBC.fill_Otabel(query,
                										Wizard.StudentPayment, elements, types);
                								if (resultObservableList.size()==0)
                								{
                									Dialogs.create()
                					                .owner(null)
                					                .title("نتيجة فارغة")
                					                .message("لا توجد نتيجة توافق البيانات المدخلة الرجاء التأكد من صحة البيانات المدخلة");
                									resultObservableList = FXCollections.observableArrayList();
                                                  StudentTable.setItems(resultObservableList);

                								}
                								else
                								{
                								StudentTable.setItems(resultObservableList);
                								StudentPaymentresult = resultObservableList;
                								}
                							} 
                							catch (Exception e) 
                							{
                								System.out.println(e.getMessage());
                								System.out.println("error retriving observable list in Result tab after StudentPayment search");
                							}
                				}
                					
                			};
                  	          SearchStudentPayment.setOnMouseClicked(SearchStudentPaymentHandler);
/////////////////////////////////////////////////////////////////////////////////////
                  	        EventHandler<Event> SearchWorkerPaymentHandler = new EventHandler<Event>()
                        			{
                        				@Override
                        				public void handle(Event arg0) 
                        				{
                        					 types = new ArrayList<String>();
                        					 elements = new ArrayList<String>();
                        						
                        						
                        						 if( WorkerPaymentDate.getValue() != null)
                        							 WorkerPaymentDateString = WorkerPaymentDate.getValue().toString().trim();
                        						 if(WorkerName.getValue() !=null)
                        						 {
                        							 if (!WorkerName.getValue().equals(""))
                        						      WorkerPaymentIDString = Wizard.getWorkerID(WorkerName.getValue());
                        							 else 
                        								 WorkerPaymentIDString = "" ;
                        						 }
                        						      WorkerPaymentTypeString = WorkerPaymentType.getValue();

                        						      query = getSearchWorkerPaymentQuery();
                        						      
                        						    if (query==null) 
                        							{
                        						    	Dialogs.create()
                    									.owner(null)
                    									.title("إشعار")
                    									.message(
                    											"الرجاء أدخال بيانات حقل واحد على الأقل")
                    									.showWarning();
                      								resultObservableList=  FXCollections.observableArrayList();
                                                    WorkerTable.setItems(resultObservableList);
                        								return;
                        							}
                        							
                        							try {
                        							
                        								//System.out.println(query);
                        								resultObservableList = (ObservableList<WorkerPayment>) JDBC.fill_Otabel(query,
                        										Wizard.WorkerPayment, elements, types);
                        								if (resultObservableList.size()==0)
                        								{
                        									Dialogs.create()
                        					                .owner(null)
                        					                .title("نتيجة فارغة")
                        					                .message("لا توجد نتيجة توافق البيانات المدخلة الرجاء التأكد من صحة البيانات المدخلة");
                        									resultObservableList = FXCollections.observableArrayList();
                                                          WorkerTable.setItems(resultObservableList);

                        								}
                        								else
                        								{
                        								WorkerTable.setItems(resultObservableList);
                        								WorkerPaymentresult = resultObservableList;
                        							    } 
                        							}
                        							catch (Exception e) 
                        							{
                        								System.out.println(e.getMessage());
                        								System.out.println("error retriving observable list in Result tab after WorkerPayment search");
                        							}
                        				}
                        					
                        			};
                      SearchWorkerPayment.setOnMouseClicked(SearchWorkerPaymentHandler);
/////////////////////////////////////////////////////////////////////////////////////
           	        EventHandler<Event> AddStudentPaymentHandler = new EventHandler<Event>()
                			{
                				@Override
                				public void handle(Event arg0) 
                				{
                					   if (NewStudentName.getValue() == null)
  			        	             {
  			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  اسم الطالب  فارغا!").message("الرجاء  تحديد اسم الطالب ").showError();
  				        	             return;
  			        	             }
  			        	             else if (NewStudentName.getValue().toString().trim().length()==0)
  			        	             {
  			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  اسم الطالب  فارغا!").message("الرجاء  تحديد اسم الطالب ").showError();
  				        	             return; 
  			        	             }
                					   if (NewStudentPaymentDate.getValue() == null)
  			        	             {
  			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  تاريخ الدفعية  فارغا!").message("الرجاء  تحديد تاريخ الدفعية ").showError();
  				        	             return;
  			        	             }
  			        	             else if (NewStudentPaymentDate.getValue().toString().trim().length()==0)
  			        	             {
  			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  تاريخ الدفعية  فارغا!").message("الرجاء  تحديد الدفعية الإصدار ").showError();
  				        	             return; 
  			        	             }
                					 if (NewStudentPaymentVlaue.getText().trim().length() == 0)
    			        	         {
    			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  قيمة الدفعية  فارغة!").message("الرجاء  تحديد قيمة الدفعية ").showError();
    				        	             return;
    			        	         }
                				
    			        	          types=new ArrayList<>();
	                    		     elements = new ArrayList<>();
////////////////////////////////////////////////////////////////////////////////////////////////
	     
//				construct the insert query
				
				String Q = "insert into student_transactions (student_ID,the_date,type,value,Notes)"
						+ " values (?,?,?,?,?)";
//				               
				 {
					 // then prepare the statment by filling the elements and types
					 elements.add(Wizard.getStudentID(NewStudentName.getValue()));
					 types.add(Wizard.Integer);
					 elements.add(NewStudentPaymentDate.getValue().toString().trim());
					 types.add(Wizard.Date);
					
					  if(NewStudentPaymentType.getValue()==null)
					   {
						 elements.add("1");
						 types.add(Wizard.NULL);
					   }
					 else if(NewStudentPaymentType.getValue().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					  else 
					  {
						elements.add(Wizard.StudentPaymentTypeConverter(NewStudentPaymentType.getValue()));
					   types.add(Wizard.String);
					  }
					  
					 elements.add(NewStudentPaymentVlaue.getText().trim());
					 types.add(Wizard.Double);
					 
					  if(NewStudentPaymentNotes.getText().trim().length()==0)
					 {
						 elements.add("1");
						 types.add(Wizard.NULL);
					 }
					 else 
					 {
						elements.add(NewStudentPaymentNotes.getText().trim());
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
								.message(
										"تمت إضافة  الدفعية  إلى قاعدة البيانات")
								.showInformation();
						clearStudentAddition();
					}
					else
						Dialogs.create()
								.owner(null)
								.title("رسالة خطأ")
								.masthead("لم يتم إضافة بيانات الدفعية")
								.message(
										"الرجاء التأكد من صحة البيانات والتأكد أنه لم يتم إضافة دفعية بنفس المعلومات لنفس التاريخ ")
								.showWarning();	 
			     }
                			};
          AddStudentPayment.setOnMouseClicked(AddStudentPaymentHandler);
/////////////////////////////////////////////////////////////////////////////////////
	        EventHandler<Event> AddTeacherPaymentHandler = new EventHandler<Event>()
        			{
        				@Override
        				public void handle(Event arg0) 
        				{
        					   if (NewTeacherName.getValue() == null)
		        	             {
		        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  اسم المعلم  فارغا!").message("الرجاء  تحديد اسم المعلم").showError();
			        	             return;
		        	             }
		        	             else if (NewTeacherName.getValue().toString().trim().length()==0)
		        	             {
		        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  اسم المعلم  فارغا!").message("الرجاء  تحديد اسم المعلم").showError();
			        	             return; 
		        	             }
        					   if (NewTeacherPaymentDate.getValue() == null)
		        	             {
		        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  تاريخ الدفعية  فارغا!").message("الرجاء  تحديد تاريخ الدفعية ").showError();
			        	             return;
		        	             }
		        	             else if (NewTeacherPaymentDate.getValue().toString().trim().length()==0)
		        	             {
			        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  تاريخ الدفعية  فارغا!").message("الرجاء  تحديد تاريخ الدفعية ").showError();
			        	             return; 
		        	             }
        					 if (NewTeacherPaymentVlaue.getText().trim().length() == 0)
		        	         {
	        	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  قيمة الدفعية  فارغة!").message("الرجاء  تحديد قيمة الدفعية ").showError();
			        	             return;
		        	         }
        				
		        	          types=new ArrayList<>();
                		     elements = new ArrayList<>();
////////////////////////////////////////////////////////////////////////////////////////////////
 
//		construct the insert query
		
		String Q = "insert into Teacher_transactions (Teacher_ID,the_date,value,type,Notes)"
				+ " values (?,?,?,?,?)";
//		               
		 {
			 // then prepare the statment by filling the elements and types
			 elements.add(Wizard.getTeacherID(NewTeacherName.getValue()));
			 types.add(Wizard.Integer);
			 elements.add(NewTeacherPaymentDate.getValue().toString().trim());
			 types.add(Wizard.Date);
			 
			 elements.add(NewTeacherPaymentVlaue.getText().trim());
			 types.add(Wizard.Double);
			 
			 if(NewTeacherPaymentType.getValue()==null)
			   {
				 elements.add("1");
				 types.add(Wizard.NULL);
			   }
			 else if(NewTeacherPaymentType.getValue().length()==0)
			 {
				 elements.add("1");
				 types.add(Wizard.NULL);
			 }
			  else 
			  {
				elements.add(Wizard.TeacherPaymentTypeConverter(NewTeacherPaymentType.getValue()));
			   types.add(Wizard.String);
			  }
			  
			 
			  if(NewTeacherPaymentNotes.getText().trim().length()==0)
			 {
				 elements.add("1");
				 types.add(Wizard.NULL);
			 }
			 else 
			 {
				elements.add(NewTeacherPaymentNotes.getText().trim());
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
						.message(
								"تمت إضافة  الدفعية  إلى قاعدة البيانات")
						.showInformation();
				clearTeacherAddition();
			}
			else
				Dialogs.create()
						.owner(null)
						.title("رسالة خطأ")
						.masthead("لم يتم إضافة بيانات الدفعية")
						.message(
								"الرجاء التأكد من صحة البيانات والتأكد أنه لم يتم إضافة دفعية بنفس المعلومات لنفس التاريخ ")
						.showWarning();	 
	     }
        			};
  AddTeacherPayment.setOnMouseClicked(AddTeacherPaymentHandler);
////////////////////////////////////////////////////////////////////////////////////////////////
    EventHandler<Event> AddWorkerPaymentHandler = new EventHandler<Event>()
			{
				@Override
				public void handle(Event arg0) 
				{
					   if (NewWorkerName.getValue() == null)
      	             {
      	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  اسم العامل  فارغا!").message("الرجاء  تحديد اسم العامل").showError();
	        	             return;
      	             }
      	             else if (NewWorkerName.getValue().toString().trim().length()==0)
      	             {
      	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  اسم العامل  فارغا!").message("الرجاء  تحديد اسم العامل").showError();
	        	             return; 
      	             }
					   if (NewWorkerPaymentDate.getValue() == null)
      	             {
      	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  تاريخ الدفعية  فارغا!").message("الرجاء  تحديد تاريخ الدفعية ").showError();
	        	             return;
      	             }
      	             else if (NewWorkerPaymentDate.getValue().toString().trim().length()==0)
      	             {
    	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  تاريخ الدفعية  فارغا!").message("الرجاء  تحديد تاريخ الدفعية ").showError();
	        	             return; 
      	             }
					 if (NewWorkerPaymentVlaue.getText().trim().length() == 0)
        	         {
    	            	 Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  قيمة الدفعية  فارغة!").message("الرجاء  تحديد قيمة الدفعية ").showError();
	        	             return;
        	         }
				
        	          types=new ArrayList<>();
        		     elements = new ArrayList<>();
////////////////////////////////////////////////////////////////////////////////////////////////

//construct the insert query

String Q = "insert into Workers_transactions (Worker_ID,the_date,value,type,Notes)"
		+ " values (?,?,?,?,?)";
//               
 {
	 // then prepare the statment by filling the elements and types
	 elements.add(Wizard.getWorkerID(NewWorkerName.getValue()));
	 types.add(Wizard.Integer);
	 elements.add(NewWorkerPaymentDate.getValue().toString().trim());
	 types.add(Wizard.Date);
	
	 elements.add(NewWorkerPaymentVlaue.getText().trim());
	 types.add(Wizard.Double);
	 
	 if(NewWorkerPaymentType.getValue()==null)
	   {
		 elements.add("1");
		 types.add(Wizard.NULL);
	   }
	 else if(NewWorkerPaymentType.getValue().length()==0)
	 {
		 elements.add("1");
		 types.add(Wizard.NULL);
	 }
	  else 
	  {
		elements.add(Wizard.WorkerPaymentTypeConverter(NewWorkerPaymentType.getValue()));
	   types.add(Wizard.String);
	  }
	 
	  if(NewWorkerPaymentNotes.getText().trim().length()==0)
	 {
		 elements.add("1");
		 types.add(Wizard.NULL);
	 }
	 else 
	 {
		elements.add(NewWorkerPaymentNotes.getText().trim());
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
				.message(
						"تمت إضافة  الدفعية  إلى قاعدة البيانات")
				.showInformation();
		clearWorkerAddition();
	}
	else
		Dialogs.create()
				.owner(null)
				.title("رسالة خطأ")
				.masthead("لم يتم إضافة بيانات الدفعية")
				.message(
						"الرجاء التأكد من صحة البيانات والتأكد أنه لم يتم إضافة دفعية بنفس المعلومات لنفس التاريخ")
				.showWarning();	 
 }
			};
AddWorkerPayment.setOnMouseClicked(AddWorkerPaymentHandler);
////////////////////////////////////////////////////////////////////////////////////////////////
		EventHandler<Event> AddGeneralPaymentHandler = new EventHandler<Event>() 
				{
			@Override
			public void handle(Event arg0)
			{
				if (NewParty.getValue() == null) 
				{
					Dialogs.create().owner(null).title("رسالة خطأ")
							.masthead("!")
							.message("الرجاء  تحديد طرف الدفعية").showError();
					return;
				} 
				else if (NewParty.getValue().toString().trim().length() == 0)
				{
					Dialogs.create().owner(null).title("رسالة خطأ")
					.masthead("!")
					.message("الرجاء  تحديد طرف الدفعية").showError();
					return;
				}
				if (NewGeneralPaymentDate.getValue() == null)
				{
					Dialogs.create().owner(null).title("رسالة خطأ")
							.masthead("لايمكن ترك  تاريخ الدفعية  فارغا!")
							.message("الرجاء  تحديد تاريخ الدفعية")
							.showError();
					return;
				} 
				else if (NewGeneralPaymentDate.getValue().toString().trim()
						.length() == 0)
				{
					Dialogs.create().owner(null).title("رسالة خطأ")
							.masthead("لايمكن ترك  تاريخ الدفعية  فارغا!")
							.message("الرجاء  تحديد تاريخ الدفعية ")
							.showError();
					return;
				}
				if (NewGeneralPaymentVlaue.getText().trim().length() == 0)
				{
					Dialogs.create().owner(null).title("رسالة خطأ")
							.masthead("لايمكن ترك  قيمة الدفعية  فارغة!")
							.message("الرجاء  تحديد قيمة الدفعية").showError();
					return;
				}
				

				types = new ArrayList<>();
				elements = new ArrayList<>();
				// //////////////////////////////////////////////////////////////////////////////////////////////

				// construct the insert query

				String Q = "insert into General_transactions (type,Party,Date,value,Notes)"
						+ " values (?,?,?,?,?)";
				//
				{
					// then prepare the statment by filling the elements and
					// types
					
					if (NewGeneralPaymentType.getValue() == null) 
					{
						elements.add("1");
						types.add(Wizard.NULL);
					} else if (NewGeneralPaymentType.getValue().length() == 0)
					{
						elements.add("1");
						types.add(Wizard.NULL);
					} else
					{
						elements.add(Wizard
								.GeneralPaymentTypeConverter(NewGeneralPaymentType
										.getValue()));
						types.add(Wizard.String);
					}
					
					elements.add(Wizard.GeneralPaymentPartyConverter(NewParty.getValue()));
					types.add(Wizard.String);
					
					elements.add(NewGeneralPaymentDate.getValue().toString().trim());
					types.add(Wizard.Date);

					elements.add(NewGeneralPaymentVlaue.getText().trim());
					types.add(Wizard.Double);
					
					if (NewGeneralPaymentNotes.getText().trim().length() == 0) 
					{
						elements.add("1");
						types.add(Wizard.NULL);
					} 
					else
                    {
						elements.add(NewGeneralPaymentNotes.getText().trim());
						types.add(Wizard.String);
					}
				}
				Boolean executed = DBUtil.excecuteUpdate(Q, elements, types);
				if (executed)
				{
					Dialogs.create().owner(null).title("رسالة إشعار")
							.masthead(null)
							.message("تمت إضافة  الدفعية  إلى قاعدة البيانات")
							.showInformation();
					clearGeneralPaymentAddition();
				}
				else
					Dialogs.create()
							.owner(null)
							.title("رسالة خطأ")
							.masthead("لم يتم إضافة بيانات الدفعية")
							.message("الرجاء التأكد من صحة البيانات والتأكد أنه لم يتم إضافة دفعية بنفس المعلومات لنفس التاريخ")
							.showWarning();
			}
		};
		AddGeneralPayment.setOnMouseClicked(AddGeneralPaymentHandler);
/////////////////////////////////////////////////////////////////////////////////////

		  EventHandler<Event> FindAllGeneral = new EventHandler<Event>() 
		 		   {
				
				@Override
				public void handle(Event arg0) 
				{
					
					
					resultObservableList= (ObservableList<GeneralPayment>) JDBC.fill_Otabel("select * from general_transactions",Wizard.GeneralPayment,null,null);
						if (resultObservableList.size()==0)
						{
							Dialogs.create()
			                .owner(null)
			                .title("نتيجة فارغة")
			                .message("لا توجد أي بيانات في هذا الحقل")
			                .showWarning();
						}
						GeneralPaymentTable.setItems(resultObservableList);
						GeneralPaymentresult = resultObservableList;
				}
			};
		/////////////////////////////////////////////////////////////////////////////////////////
		        ShowAllGeneralImage.setOnMouseClicked(FindAllGeneral);
		        ShowAllGeneralLabel.setOnMouseClicked(FindAllGeneral);
		        

				  EventHandler<Event> FindAllStudentPayments = new EventHandler<Event>() 
				 		   {
						
						@Override
						public void handle(Event arg0) 
						{
							
							
							resultObservableList= (ObservableList<StudentPayment>) JDBC.fill_Otabel("select * from student_transactions",Wizard.StudentPayment,null,null);
								if (resultObservableList.size()==0)
								{
									Dialogs.create()
					                .owner(null)
					                .title("نتيجة فارغة")
					                .message("لا توجد أي بيانات في هذا الحقل")
					                .showWarning();
								}
								StudentTable.setItems(resultObservableList);
								StudentPaymentresult = resultObservableList;
						  }
				    	};
/////////////////////////////////////////////////////////////////////////////////////////
				        ShowAllStudentsPaymentsImage.setOnMouseClicked(FindAllStudentPayments);
				        ShowAllStudentsPaymentsLabel.setOnMouseClicked(FindAllStudentPayments);


						  EventHandler<Event> FindAllTeacherPayments = new EventHandler<Event>() 
						 		   {
								
								@Override
								public void handle(Event arg0) 
								{
									
									
									resultObservableList= (ObservableList<TeacherPayment>) JDBC.fill_Otabel("select * from teacher_transactions",Wizard.TeacherPayment,null,null);
										if (resultObservableList.size()==0)
										{
											Dialogs.create()
							                .owner(null)
							                .title("نتيجة فارغة")
							                .message("لا توجد أي بيانات في هذا الحقل")
							                .showWarning();
										}
										TeacherTable.setItems(resultObservableList);
										TeacherPaymentresult = resultObservableList;
								}
							};
		/////////////////////////////////////////////////////////////////////////////////////////
						        ShowAllTeachersPaymentsImage.setOnMouseClicked(FindAllTeacherPayments);
						        ShowAllTeachersPaymentsLabel.setOnMouseClicked(FindAllTeacherPayments);
		/////////////////////////////////////////////////////////////////////////////////////////

								  EventHandler<Event> FindAllWorkerPayments = new EventHandler<Event>() 
								 		   {
										
										@Override
										public void handle(Event arg0) 
										{
											
											
											resultObservableList= (ObservableList<WorkerPayment>) JDBC.fill_Otabel("select * from workers_transactions",Wizard.WorkerPayment,null,null);
												if (resultObservableList.size()==0)
												{
													Dialogs.create()
									                .owner(null)
									                .title("نتيجة فارغة")
									                .message("لا توجد أي بيانات في هذا الحقل")
									                .showWarning();
												}
												WorkerTable.setItems(resultObservableList);
												WorkerPaymentresult = resultObservableList;
										}
									};
	/////////////////////////////////////////////////////////////////////////////////////////
								        ShowAllWorkersPaymentsImage.setOnMouseClicked(FindAllWorkerPayments);
								        ShowAllWorkerPaymentsLabel.setOnMouseClicked(FindAllWorkerPayments);
	}
	
	@Override
	public void initialize_onshow()
	{
	    clear();	
//		intialize comboboxes
		// ///////////////////////////////////////////////////////////////////////////////////
		NewGeneralPaymentType.setItems(Wizard.PaymentsTypesforComboBox);
		NewParty.setItems(Wizard.GeneralPaymentsOutcomeTypes);
		GeneralPaymentType.setItems(Wizard.PaymentsTypesforComboBox);
		GeneralPaymentParty.setItems(Wizard.GeneralPaymentsOutcomeTypes);
		NewStudentClass.setItems(Wizard.getStudentClasses());
		StudentClass.setItems(Wizard.getStudentClasses());
		// NewStudentName
		// StudentName
		NewStudentPaymentType.setItems(Wizard.STudentPaymentsTypesforComboBox);
		StudentPaymentType.setItems(Wizard.STudentPaymentsTypesforComboBox);
		NewTeacherName.setItems(Wizard.getTeacherNamesForComboBox());
		TeacherName.setItems(Wizard.getTeacherNamesForComboBox());
		NewTeacherPaymentType.setItems(Wizard.TeacherPaymentsTypesforComboBox);
		TeacherPaymentType.setItems(Wizard.TeacherPaymentsTypesforComboBox);
		WorkerName.setItems(Wizard.getWorkersNamesForCombobox());
		NewWorkerName.setItems(Wizard.getWorkersNamesForCombobox());
		WorkerPaymentType.setItems(Wizard.WorkerPaymentsTypesforComboBox);
		NewWorkerPaymentType.setItems(Wizard.WorkerPaymentsTypesforComboBox);
		
	}
/////////////////////////////////////////////////////////////////////////////////////
	//search Worker elements
	@FXML
	private ImageView SearchWorkerPayment;
	@FXML
	private DatePicker WorkerPaymentDate;
	@FXML
	private ComboBox<String> WorkerPaymentType;
	@FXML
	private ComboBox<String> WorkerName;
     //worker Table elements
	@FXML
	private TableView WorkerTable;
	@FXML
    private TableColumn WorkerJobColumn;
	@FXML
    private TableColumn WorkerPaymentTypeColumn;
	@FXML
    private TableColumn WorkerPaymentDateColumn;
	@FXML
    private TableColumn WorkerNameColumn;
	@FXML
	 private TableColumn WorkerValueColumn;
	
	//new worker payment elements
	 @FXML
	 private TextField NewWorkerPaymentVlaue;
	 @FXML
	 private ComboBox<String> NewWorkerPaymentType;
	 @FXML
	    private TextArea NewWorkerPaymentNotes;
	 @FXML
	    private DatePicker NewWorkerPaymentDate;
	 @FXML
	    private ComboBox<String> NewWorkerName;
	 @FXML
	 private ImageView AddWorkerPayment;
	 @FXML
	    private ImageView ShowAllWorkersPaymentsImage;
	    @FXML
	    private  Label ShowAllWorkerPaymentsLabel;
/////////////////////////////////////////////////////////////////////////////////////
	 //Search Teacher payment elements
	 @FXML
	private ComboBox<String> TeacherPaymentType;
	@FXML
	private ComboBox<String> TeacherName;
	@FXML
	private DatePicker TeacherPaymentDate;
    @FXML
    private ImageView SearchTeacherPayment;
	 // Teacher table elements
    @FXML
    private TableView<String> TeacherTable;
    @FXML
    private TableColumn TeacherClassColumn;
    @FXML
    private TableColumn TeacherNameColumn;
    @FXML
    private TableColumn TeacherPaymentDateColumn;
    @FXML
    private TableColumn TeacherValueColumn;
    @FXML
    private TableColumn TeacherPaymentTypeColumn;
    @FXML
    private ImageView AddTeacherPayment;
    // new teacher payment elements 
	@FXML
    private TextField NewTeacherPaymentVlaue;
    @FXML
    private DatePicker NewTeacherPaymentDate;
    @FXML
    private ComboBox<String> NewTeacherName;
    @FXML
    private ComboBox<String> NewTeacherPaymentType;
    @FXML
    private TextArea NewTeacherPaymentNotes;
    @FXML
    private ImageView ShowAllTeachersPaymentsImage;
    @FXML
    private  Label ShowAllTeachersPaymentsLabel;
/////////////////////////////////////////////////////////////////////////////////////
   //student search payment elements 
    @FXML
    private ComboBox<String> StudentClass;
    @FXML
    private ImageView SearchStudentPayment;
    @FXML
    private DatePicker StudentPaymentDate;
    @FXML
    private ComboBox<String> StudentName;
    @FXML
    private ComboBox<String> StudentPaymentType;
    //student table elements
    @FXML
    private TableView<String> StudentTable;
    @FXML
    private TableColumn StudentClassColumn;
    @FXML
    private TableColumn StudentValueColumn;
    @FXML
    private TableColumn StudentNameColumn;
    @FXML
    private TableColumn StudentPaymentTypeColumn;
    @FXML
    private TableColumn StudentPaymentDateColumn;
    //student new payment elements 
    @FXML
    private ComboBox<String> NewStudentPaymentType;
    @FXML
    private TextArea NewStudentPaymentNotes;
    @FXML
    private DatePicker NewStudentPaymentDate;
    @FXML
    private TextField NewStudentPaymentVlaue;
    @FXML
    private ComboBox<String> NewStudentName;
    @FXML
    private ComboBox<String> NewStudentClass;
    @FXML
    private ImageView AddStudentPayment;    
    @FXML
    private Label ShowAllStudentsPaymentsLabel;
    @FXML
    private ImageView ShowAllStudentsPaymentsImage;
/////////////////////////////////////////////////////////////////////////////////////
    //search general payment elements 
    @FXML
    private ImageView SearchGeneralPaymentIcon;
    @FXML
    private ComboBox<String> GeneralPaymentParty;
    @FXML
    private DatePicker GeneralPaymentDate;
    @FXML
    private ComboBox<String> GeneralPaymentType;
    
    // general payments table elements 
    @FXML
    private TableView GeneralPaymentTable;
    @FXML
    private TableColumn GeneralValueColumn;
    @FXML
    private TableColumn GeneralPaymentTypeColumn;
    @FXML
    private TableColumn GeneralPaymentDateColumn;
    @FXML
    private TableColumn PartyColumn;
    // new general payment elements 
    @FXML
    private DatePicker NewGeneralPaymentDate;
    @FXML
    private TextArea NewGeneralPaymentNotes;
    @FXML
    private TextField NewGeneralPaymentVlaue;
    @FXML
    private ComboBox<String> NewGeneralPaymentType;
    @FXML
    private ComboBox<String> NewParty;
    @FXML
    private ImageView AddGeneralPayment;
    @FXML
    private Label ShowAllGeneralLabel;
    @FXML
    private ImageView ShowAllGeneralImage;
/////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private void handleNewGeneralPaymentTypeAction()
    {
      String selectedItem = NewGeneralPaymentType.getSelectionModel().getSelectedItem();
      if(selectedItem == null)
      {
    	  NewParty.setValue(null);
    	  NewParty.setItems(null);
    	  NewParty.setPromptText("الرجاء تحديد  نوع الدفعية أولا");
    	  
      }
      else if (selectedItem.equals(""))
      {
    	  NewParty.setValue(null);
    	  NewParty.setItems(null);
    	  NewParty.setPromptText("الرجاء تحديد  نوع الدفعية أولا");
      }
      else
      {
    	  if(selectedItem.equals(Wizard.PaymentsTypesforComboBox.get(1)))
    	  {
    		  // expenses were selected 
    		  NewParty.setItems(Wizard.GeneralPaymentsOutcomeTypesforComboBox);
        	  NewParty.setPromptText("");

    	  }
    	  else
    	  {
    		  NewParty.setItems(Wizard.GeneralPaymentsIncomeTypesforComboBox);
        	  NewParty.setPromptText("");

    	  }
      }
    }
    
    @FXML
    private void handleGeneralPaymentTypeAction()
    {
      String selectedItem = GeneralPaymentType.getSelectionModel().getSelectedItem();
      if(selectedItem == null)
      {
    	  GeneralPaymentParty.setValue(null);
    	  GeneralPaymentParty.setItems(null);
    	  GeneralPaymentParty.setPromptText("الرجاء تحديد  نوع الدفعية أولا");
      }
      else if (selectedItem.equals(""))
      {
    	  GeneralPaymentParty.setValue(null);
    	  GeneralPaymentParty.setItems(null);
    	  GeneralPaymentParty.setPromptText("الرجاء  تحديد نوع  الدفعية أولا");
      }
      else
      {
    	  if(selectedItem.equals(Wizard.PaymentsTypesforComboBox.get(1)))
    	  {
    		  // expenses were selected 
    		  GeneralPaymentParty.setItems(Wizard.GeneralPaymentsOutcomeTypesforComboBox);
        	  GeneralPaymentParty.setPromptText("");

    	  }
    	  else
    	  {
    		  GeneralPaymentParty.setItems(Wizard.GeneralPaymentsIncomeTypesforComboBox);
        	  GeneralPaymentParty.setPromptText("");

    	  }
      }
    }
    
    @FXML
    private void handlNewStudentPaymentAction()
    {
      String selectedItem = NewStudentClass.getSelectionModel().getSelectedItem();
      if(selectedItem == null)
      {
    	  NewStudentName.setValue(null);
    	  NewStudentName.setItems(null);
    	  NewStudentName.setPromptText("الرجاء تحديد  فصل الطالب أولا");
      }
      else
      {
    	  NewStudentName.setItems(Wizard.getStudentsperClassForCombobox(Wizard.getClassID(selectedItem)));
    	  NewStudentName.setPromptText("");
      }
    }
    
    @FXML
    private void handlStudentPaymentAction()
    {
      String selectedItem = StudentClass.getSelectionModel().getSelectedItem();
      if(selectedItem == null)
      {
    	  StudentName.setValue(null);
    	  StudentName.setItems(null);
    	  StudentName.setPromptText("الرجاء تحديد  فصل الطالب أولا");
      }
      else
      {
    	  StudentName.setItems(Wizard.getStudentsperClassForCombobox(Wizard.getClassID(selectedItem)));
    	  StudentName.setPromptText("");
      }
    }
}