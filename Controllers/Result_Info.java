package Controllers;

import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import models.*;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;
import databaseUtilities.DBUtil;
import wizards.Wizard;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class Result_Info extends Controlled_Screen implements Initializable 
{
	int selected_id;
//    Result Transpoter;
    List<String> types;
	List<String> elements;
	 ObservableList<ObservableList<StringProperty>> allRows = FXCollections.observableArrayList();
	 ObservableList<ObservableList<StringProperty>> Classworks = FXCollections.observableArrayList();
	 XYChart.Series<String, Integer> Examsseries = new XYChart.Series<>();
	 XYChart.Series<String, Integer> ClassWorkseries = new XYChart.Series<>();
//	 List<Integer> SubjectsTotalMarks = new ArrayList<>();
     int [] SubjectsTotalExamMarks ;
     int [] SubjectsTotalClassWorkMarks ;
	 ObservableList<StringProperty> singleRow;
	 ObservableList<StringProperty> singleRowCopy;
	 ObservableList<String> allSubjects;
	 ObservableList<String> allSubjectsCopy;
	 Result ResultObject ;
	 ObservableList<String> studentsnames ;
	 int maxClassWork =30;
	 int maxExam = 100;
	 int sumOfExamMarks=0;
	 double studentPercentage = 0 ;
	
	 public void  createReport()
	 {
		  JasperReportBuilder report = DynamicReports.report();
      	   
      	   StyleBuilder boldStyle = DynamicReports.stl.style().bold(); 

      	   StyleBuilder boldRightCentered =  DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.RIGHT).setFontSize(12).setRightPadding(20).setBorder(DynamicReports.stl.pen1Point()).setTopPadding(5).setBottomPadding(5);
      	   StyleBuilder boldRightCenteredStyle = DynamicReports.stl.style(boldRightCentered);
      	   StyleBuilder boldRightCenteredWithShadow = DynamicReports.stl.style(boldRightCentered).setBackgroundColor(Color.lightGray);
      	  
      	   StyleBuilder boldWithoutCenter =  DynamicReports.stl.style(boldStyle).setFontSize(12).setBorder(DynamicReports.stl.pen1Point()).setTopPadding(5).setBottomPadding(5);

      	   
      	   StyleBuilder boldCenteredStyle = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER).setFontSize(12).setBorder(DynamicReports.stl.pen1Point());
      	   StyleBuilder CenteredStyle = DynamicReports.stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER);

      	   StyleBuilder boldCenteredStyleWithShadow = DynamicReports.stl.style(boldStyle).setHorizontalAlignment(HorizontalAlignment.CENTER).setBackgroundColor(Color.lightGray).setFontSize(14).setBorder(DynamicReports.stl.pen1Point()).setPadding(3);
      	  
        	 StyleBuilder columnTitleStyle  = DynamicReports.stl.style(boldCenteredStyle).setBorder(DynamicReports.stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

      	   
      	   report.title(DynamicReports.cmp.text(Wizard.SchoolName).setStyle(boldCenteredStyle).setHeight(40));
      	    
      	  ObservableList<Marks> SubjectMarks = FXCollections.observableArrayList() ;
      	 
			 SubjectMarks = FXCollections.observableArrayList();
			 
             //add student names column 
			

				for (int j = 0; j <allRows.size(); j++)
				{  
					//for each student j== rows
					 Marks mark = new Marks();
					 mark.setstudentName(allRows.get(j).get(0).get());

					 for (int k = 1; k <allRows.get(0).size()-2; k++)
			      	    {   
						  //for each subject k==columns start from to skip student subject field
						 
						  mark.set_subject(k, Integer.parseInt(allRows.get(j).get(k).get()));
			      	    }
					  SubjectMarks.add(mark);					
				}
				
          	  report.setDataSource(SubjectMarks);

                for (int i=allSubjects.size()-1; i>= 1; i--)
                {
                	
                	//the loop in reverse order because the columns are added from right to left
                	//skip student | subject field i = 1
                	  report.columns( DynamicReports.col.column(allSubjects.get(i),"subject"+i,DynamicReports.type.integerType()).setStyle(CenteredStyle));
                	  
				}
                
          	  report.columns( DynamicReports.col.column("الطالب | المادة","studentName",DynamicReports.type.stringType()));

                
                report.setColumnTitleStyle(columnTitleStyle);
                report.highlightDetailOddRows();
                report.setReportName(Wizard.SchoolName);
                
      	   try {
			report.show(false);
		} catch (DRException e) 
      	   {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		 
	 }
    public void clear()
    {
    	Type.setText("لاتوجد بيانات للعرض");
    	Result_ID.setText("لاتوجد بيانات للعرض");
 	     Date.setText("لاتوجد بيانات للعرض");
 	     //deletes  all columns 
       Exam_marks_table= Wizard.ClearTable(Exam_marks_table);
       ClassWork_table = Wizard.ClearTable(ClassWork_table);
       //deletes all rows 
//       allRows = FXCollections.observableArrayList();
//       Classworks = FXCollections.observableArrayList();
       
       while (!allRows.isEmpty())
       {
    	   allRows.remove(0);
    	   Classworks.remove(0);
       }
       
    }
    public void clear_Edit_Tab()
    {
 	 
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{ 
 
		ExamsTab.setOnSelectionChanged(reload);
		ClassWorkTab.setOnSelectionChanged(reload);
		ClassWorkTab.setTooltip(new Tooltip("توضيح درجات الطالب خلال أعمال السنة"));
		
		NewDate.setConverter(Wizard.DatePickerConverter());
		NewType.setItems(Wizard.ResultTypes);
		newSupervisor1.setItems(Wizard.getTeacherNames());
		newSupervisor2.setItems(Wizard.getTeacherNames());
		NewClass.setItems(Wizard.getStudentClasses());
		Exam_marks_table.setItems(allRows);
		ClassWork_table.setItems(Classworks);
		
////////////////////////////////////////////////////////////////////////////////////////
     //	//delete Result info 
		EventHandler<Event> DeleteResult = new EventHandler<Event>()
				{
			        @Override
			         public void handle(Event arg0) 
			        {	
			        	Action response = Dialogs.create()
			        	        .owner(null)
			        	        .title("رسالة تأكيد")
			        	        .masthead("")
			        	        .message("هل أنت متأكد من أنك تريد مسح جميع بيانات هذه النتيجة ؟")
			        	        .actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
			        	        .showConfirm();

			        	if (response == Dialog.ACTION_YES)
			        	{
			        		
			        		Boolean executed=DBUtil.excecuteUpdate("delete from Results where id ="+selected_id);
			        		if(executed)
			        		{
			        			Dialogs.create()
			        	        .owner(null)
			        	        .title("رسالة إشعار")
			        	        .message("تم حذف بيانات  النتيجة  بنجاح")
			        	        .showInformation();
				        		myController.setScreen("Results_Tab","");
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
		        DeleteResultImage.setOnMouseClicked(DeleteResult);
		        DeleteResultLabel.setOnMouseClicked(DeleteResult);
///////////////////////////////////////////////////////////////////////////////////////
//		        //Edit class
		        EventHandler<Event> EditResult = new EventHandler<Event>() 
						{
					        @Override
				             	public void handle(Event arg0) 
				                      	{
					        	         if(NewClass.getValue()==null)
					        	         {
						        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  اسم  الفصل فارغا!").message("الرجاء تحديد  اسم الفصل ").showError();
						        	             return;
						        	     }
					        	         else if(NewClass.getValue().trim().length()==0)
		        	        	           {
			        	             			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  وجهة الترحيل فارغا!").message("الرجاء إدخال اسم الفصل ").showError();
			        	                        return;
			        	                  }			
					        	         if(NewDate.getValue()==null)
		        	        	           {
			        	             			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  تاريخ الإصدار  فارغا!").message("الرجاء تحديد تاريخ الإصدار ").showError();
			        	                        return;
			        	                  }		
					        	         else if(NewDate.getValue().toString().trim().length()==0)
		        	        	           {
			        	             			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك  تاريخ الإصدار  فارغا!").message("الرجاء تحديد تاريخ الإصدار ").showError();
			        	                        return;
			        	                  }		
					        	          
						        types=new ArrayList<>();
					            elements = new ArrayList<>();
		//////////////////////////////////////////////////////////////////////////////////////////////
//						construct the insert query
						//12 prepared filed
						
						String Q = "update  results set Class_ID = ? , Date = ? , supervisor_1 = ?, supervisor_2 = ?,semster_ID = ? , Type = ?,ExamPrecentage = ? "
								+ "where id="+selected_id;
			              types=new ArrayList<>();
		   			     elements = new ArrayList<>();
						 {
							 // then prepare the statment by filling the elements and types
							 elements.add(Wizard.getClassID(NewClass.getValue()));
							 types.add(Wizard.Integer);
/////////////////////////////////////////////////////////////////////////////////////////
								 elements.add(NewDate.getValue().toString().trim());
								 types.add(Wizard.Date);
/////////////////////////////////////////////////////////////////////////////////////////
		                    if(newSupervisor1.getValue()==null)
							 {
								 elements.add("1");
								 types.add(Wizard.NULL);
							 }
							 else if(newSupervisor1.getValue().length()==0)
							 {
								 elements.add("1");
								 types.add(Wizard.NULL);
							 }
							 else 
							 {
								 elements.add(Wizard.getTeacherID(newSupervisor1.getValue()));
								 types.add(Wizard.Integer);
							 }
/////////////////////////////////////////////////////////////////////////////////////////
		                    if(newSupervisor2.getValue()==null)
							 {
								 elements.add("1");
								 types.add(Wizard.NULL);
							 }
							 else if(newSupervisor2.getValue().length()==0)
							 {
								 elements.add("1");
								 types.add(Wizard.NULL);
							 }
							 else 
							 {
								 elements.add(Wizard.getTeacherID(newSupervisor2.getValue()));
								 types.add(Wizard.Integer);
							 }
/////////////////////////////////////////////////////////////////////////////////////////
		                    elements.add("1");
							 types.add(Wizard.NULL);
							 
							 if(NewType.getValue()==null)
							   {
								 elements.add("1");
								 types.add(Wizard.NULL);
							   }
							 else if(NewType.getValue().length()==0)
							 {
								 elements.add("1");
								 types.add(Wizard.NULL);
							 }
							 else 
						   {
								 elements.add(Wizard.ResultTypeConverter(NewType.getValue().trim()));
								 types.add(Wizard.String);
						    }
							if(NewExamPrecentage.getText().trim().length()==0)
							{
								 elements.add("1");
								 types.add(Wizard.NULL);
							}
							else
							{
								 elements.add(NewExamPrecentage.getText().trim());
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
							.message("تمت عملية التعديل بنجاح")
							.showInformation();
							
							if(!ResultObject.getClass_ID().equals(Wizard.getClassID(NewClass.getValue())))
							{
								// delete all result links for specific result id when its class id is changed
								DBUtil.excecuteUpdate("delete from  result_link where result_ID = "+selected_id);
							}
							initialize_onshow();// to show the new data
						}
						else
						
								Dialogs.create()
										.owner(null)
										.title("رسالة خطأ")
										.masthead("لم يتم تعديل بيانات النتيجة")
										.message(
												"الرجاء التأكد من صحة البيانات المدحلة والتأكد من أن الاسم لم يستخدم مسبقا")
										.showWarning();
					     }
				       };
///////////////////////////////////////////////////////////////////////////////////////
				ConfirmEditImage.setOnMouseClicked(EditResult);
				ConfirmEditLabel.setOnMouseClicked(EditResult);
///////////////////////////////////////////////////////////////////////////////////////
				EventHandler<Event> PrintResult = new EventHandler<Event>()
						{
					
					        @Override
					         public void handle(Event arg0) 
					        {	
					        	 createReport();
					        }
						};
			     PrintImage.setOnMouseClicked(PrintResult);
			     PrintLabel.setOnMouseClicked(PrintResult);
	}
	
	@Override
	public void initialize_onshow() 
	{
		
		    clear();
		   // clear_Edit_Tab();
			selected_id = Wizard.global_selected_id;
		    ResultObject = Wizard.getResultObject(selected_id);
           Result_ID.setText(ResultObject.getid().toString());
           Type.setText(ResultObject.gettype());
           NewType.setValue(ResultObject.gettype());
           Date.setText(ResultObject.getdate());
           NewDate.setValue(Wizard.ToLocalDate(ResultObject.getdate()));
           if(ResultObject.getSupervisor_1()!=null)
           firstSupervisor.setText(Wizard.getTeacherName(ResultObject.getSupervisor_1()));
           newSupervisor1.setValue(Wizard.getTeacherName(ResultObject.getSupervisor_1()));
           if(ResultObject.getSupervisor_2()!=null)
           secondSupervisor.setText(Wizard.getTeacherName(ResultObject.getSupervisor_2()));
           newSupervisor2.setValue(Wizard.getTeacherName(ResultObject.getSupervisor_2()));
           Level.setText(Wizard.getClassLevel(ResultObject.getClass_ID()));
           NewClass.setValue(Wizard.getStudentClassName(ResultObject.getClass_ID()));
           Class.setText(Wizard.getStudentClassName(ResultObject.getClass_ID()));
           ExamPrecentage.setText(ResultObject.getExamPrecentage().toString());
           NewExamPrecentage.setText(ResultObject.getExamPrecentage().toString());
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
           allSubjects = Wizard.getSubjectsperLevel(Wizard.LevelConverter(Wizard.getClassLevel(ResultObject.getClass_ID())));
           allSubjects.add(0,"الطالب  | المادة");
		   Exam_marks_table = dynamic_table_filling(Exam_marks_table, allSubjects,"Fill Exam table");
		   ClassWork_table = dynamic_table_filling(ClassWork_table, allSubjects,"Fill ClassWork table");
	       //each row consist of the student name and the corresponding marks
			 studentsnames   = Wizard.getStudentsperClass(ResultObject.getClass_ID().toString());
	        //construct each row for the exam table
			 SubjectsTotalExamMarks = new int [allSubjects.size()];
			 SubjectsTotalClassWorkMarks = new int [allSubjects.size()];

	       for (Iterator iterator = studentsnames.iterator(); iterator.hasNext();)
	       {
	    	   sumOfExamMarks = 0;
	    	   studentPercentage = 0;
			 String studentname = (String) iterator.next();
			 singleRow = FXCollections.observableArrayList();
			 singleRow.add(new SimpleStringProperty(studentname));
			 
			 //fill the student name cell
			 //then construct each cell with corresponding exam mark
			 singleRow.addAll(Wizard.getExamsOrderdedMarks(String.valueOf(selected_id), Wizard.getStudentID(studentname), String.valueOf(ResultObject.getClass_ID())));
			 //compute the total 
			 for (int i = 1 ; i<singleRow.size(); i++)
			 {
				 sumOfExamMarks += Integer.parseInt((singleRow.get(i)).get());	
				  	//find subjects total Exam marks to be plotted on the chart
		    	 SubjectsTotalExamMarks [i-1]+= Integer.parseInt(singleRow.get(i).get());
			 }
			 
			studentPercentage = (double)((double)sumOfExamMarks/(double)(allSubjects.size()-1));// subtract 1 because we add student/subject field
			//trim the trailing numbers 
			
            String percentage = String.valueOf(studentPercentage);
            if (percentage.length()>5)
            {
            	percentage=percentage.substring(0,5);
            }
			singleRow.add(new SimpleStringProperty(String.valueOf(sumOfExamMarks)));
			singleRow.add(new SimpleStringProperty(percentage+"%"));

             System.out.println();
			 
             allRows.add(singleRow);
		     
			 singleRow = FXCollections.observableArrayList();
			 singleRow.add(new SimpleStringProperty(studentname)); 
			 //then construct each cell with corresponding classwork mark
			 singleRow.addAll(Wizard.getClassWorkOrderdedMarks(String.valueOf(selected_id), Wizard.getStudentID(studentname), String.valueOf(ResultObject.getClass_ID())));
		     Classworks.add(singleRow);
		     
		     
			   	//find subjects total ClassWork marks to be plotted on the chart
		     for (int i = 1; i < singleRow.size(); i++) 
		     {
		    	 //because each row start with student name you have to skip it
		    	 SubjectsTotalClassWorkMarks [i-1]+= Integer.parseInt(singleRow.get(i).get());
			 }
	       }
///////////////////////////////////////////////////////////////////////////////////////////////////////////// 
//charts initialization
	       //make copy to remove the first element (الطالب المادة)
	       allSubjectsCopy = FXCollections.observableArrayList();
	       allSubjectsCopy.addAll(allSubjects);
	       allSubjectsCopy.remove(0);
	       
	         //clear the chart
	   	      AvarageMarksChart.getData().removeAll(Examsseries);
	   	      AvarageMarksChart.getData().removeAll(ClassWorkseries);

	   	      
	   	      //prepare new series 
		     Examsseries = new XYChart.Series<>();
		     ClassWorkseries = new XYChart.Series<>();
		     
		   // Create a XYChart.Data object for each subject. Add it to the series.
		     if(studentsnames.size()!=0)
		     {
		     for (int i = 0; i < allSubjectsCopy.size(); i++)
		     {
		    	 //divide it over the total number 
		            Examsseries.getData().add(new XYChart.Data<>(allSubjectsCopy.get(i), SubjectsTotalExamMarks[i]/studentsnames.size()));
		            ClassWorkseries.getData().add(new XYChart.Data<>(allSubjectsCopy.get(i), SubjectsTotalClassWorkMarks[i]/studentsnames.size()));
		     }
		     }
		     
	       SubjectsAxis.setCategories(allSubjectsCopy);	 

	   	   //refresh the table with the new series  
	       AvarageMarksChart.getData().add(Examsseries);
	       Examsseries.setName("درجات الامتحانات");
	       AvarageMarksChart.getData().add(ClassWorkseries);
	       ClassWorkseries.setName("درجات أعمال الفصل");
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////
	public void  RefreshCharts()
	{
		 SubjectsTotalExamMarks = new int [allSubjects.size()];
		 SubjectsTotalClassWorkMarks = new int [allSubjects.size()];
		 for (Iterator iterator = studentsnames.iterator(); iterator.hasNext();)
	       {
			 String studentname = (String) iterator.next();
			 singleRow = FXCollections.observableArrayList();
			 singleRow.add(new SimpleStringProperty(studentname)); 
			 //fill the student name cell
			 //then construct each cell with corresponding exam mark
			 singleRow.addAll(Wizard.getExamsOrderdedMarks(String.valueOf(selected_id), Wizard.getStudentID(studentname), String.valueOf(ResultObject.getClass_ID())));
			  	//find subjects total Exam marks to be plotted on the chart
		     for (int i = 1; i < singleRow.size(); i++) 
		     {
		    	 //because each row start with student name you have to skip it
		    	 SubjectsTotalExamMarks [i-1]+= Integer.parseInt(singleRow.get(i).get());
			 }
			 singleRow = FXCollections.observableArrayList();
			 singleRow.add(new SimpleStringProperty(studentname)); 
			 //then construct each cell with corresponding classwork mark
			 singleRow.addAll(Wizard.getClassWorkOrderdedMarks(String.valueOf(selected_id), Wizard.getStudentID(studentname), String.valueOf(ResultObject.getClass_ID())));		     
			   	//find subjects total ClassWork marks to be plotted on the chart
		     for (int i = 1; i < singleRow.size(); i++) 
		     {
		    	 //because each row start with student name you have to skip it
		    	 SubjectsTotalClassWorkMarks [i-1]+= Integer.parseInt(singleRow.get(i).get());
			 }
	       }
		allSubjectsCopy = FXCollections.observableArrayList();
		allSubjectsCopy.addAll(allSubjects);
		allSubjectsCopy.remove(0);

		// clear the chart
		AvarageMarksChart.getData().removeAll(Examsseries);
		AvarageMarksChart.getData().removeAll(ClassWorkseries);

		// prepare new series
		Examsseries = new XYChart.Series<>();
		ClassWorkseries = new XYChart.Series<>();

		// Create a XYChart.Data object for each subject. Add it to the series.
		 if(studentsnames.size()!=0)
	     {
		for (int i = 0; i < allSubjectsCopy.size(); i++) 
		{
			// divide it over the total number
			Examsseries.getData().add(
					new XYChart.Data<>(allSubjectsCopy.get(i),
							SubjectsTotalExamMarks[i] / studentsnames.size()));
			ClassWorkseries.getData().add(
					new XYChart.Data<>(allSubjectsCopy.get(i),
							SubjectsTotalClassWorkMarks[i]
									/ studentsnames.size()));
		}
	     }

		SubjectsAxis.setCategories(allSubjectsCopy);

		// refresh the table with the new series
		AvarageMarksChart.getData().add(Examsseries);
		Examsseries.setName("درجات الامتحانات");
		AvarageMarksChart.getData().add(ClassWorkseries);
		ClassWorkseries.setName("درجات أعمال الفصل");


	}
	@FXML
    private Label Result_ID;
	@FXML
	private Label Date;
    @FXML
    private Label Type;
    @FXML
    private Label firstSupervisor;
    @FXML
    private Label secondSupervisor;
    @FXML
    private Label Level;
    @FXML
    private Label Class;
    @FXML
    private ImageView DeleteResultImage;
    @FXML
    private Label DeleteResultLabel;
    @FXML
    private Label ExamPrecentage;
   
/////////////////////////////////////////////////////////////////////////////////////////
//   Edit pane 
    @FXML
    private ComboBox<String> NewType;
    @FXML
    private DatePicker NewDate;
    @FXML
    private ComboBox<String> newSupervisor1;
    @FXML
    private ComboBox<String>  NewClass;
    @FXML
    private ComboBox<String> newSupervisor2;
    @FXML
    private TextField NewExamPrecentage;
    @FXML
    private Label ConfirmEditLabel;   
    @FXML
    private ImageView ConfirmEditImage;
/////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private TableView Exam_marks_table;
    @FXML
    private TableView ClassWork_table;
/////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private BarChart AvarageMarksChart;
    @FXML
    private CategoryAxis SubjectsAxis;
/////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Tab ExamsTab;
    @FXML
    private  Tab ClassWorkTab;
/////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private  ImageView PrintImage ;
    @FXML
    private Label PrintLabel;


	TableColumn<ObservableList<StringProperty>, String> createColumn
	(
			final int columnIndex, String columnTitle, String WhatToDo) 
			{
		// creat columns dynamically
		TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();
		String title;
		if (columnTitle == null || columnTitle.trim().length() == 0) {
			title = "Column " + (columnIndex + 1);
		} else {
			title = columnTitle;
		}
		column.setText(title);
		column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<StringProperty>, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(
					CellDataFeatures<ObservableList<StringProperty>, String> cellDataFeatures) {
				ObservableList<StringProperty> values = cellDataFeatures
						.getValue();
				if (columnIndex >= values.size()) {
					return new SimpleStringProperty("");
				} else {
					return cellDataFeatures.getValue().get(columnIndex);
				}
			}
		});
		if(!WhatToDo.equals("notEditable"))
		column = set_editable(column, WhatToDo);
		column.setMinWidth(80);
		// column.getColumns().addAll(Exam,Classwork);
		return column;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 TableView dynamic_table_filling(TableView table,ObservableList<String> headerValues, String WhatToDo) 
	 {
		 //generate tables dynamically
		for (int column = 0; column < headerValues.size(); column++) 
		{
			// TableColumn temp = new TableColumn<>(headerValues.get(column)) ;
			// temp = set_editable(temp);
			// table.getColumns().add(temp);
			table.getColumns().add(createColumn(column, headerValues.get(column), WhatToDo));
		}
		table.getColumns().add(createColumn(headerValues.size(),"المجموع", "notEditable"));
		table.getColumns().add(createColumn(headerValues.size()+1,"النسبة", "WhatToDo"));


		return table;
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 TableColumn set_editable(TableColumn column,String WhatToDo)
	    {
	    	column.setCellFactory(TextFieldTableCell.forTableColumn());
	    	column.setOnEditCommit(new EventHandler<CellEditEvent>()
	    		    {
	    		        @Override
	    		        public void handle(CellEditEvent t) 
	    		        {
//	    		            ((Result) t.getTableView().getItems().get(
//	    		                t.getTablePosition().getRow())
//	    		                ).setFirstName(t.getNewValue());
//	    		        	Globaltable_point= new point(t.getTablePosition().getRow(),t.getTablePosition().getColumn());
//	    		        	GlobalCellValue=t.getNewValue().toString();

	    		        	//check different listeners modes 
	    		        	{
	    		        		 int value = Integer.parseInt(t.getNewValue().toString());
	    		        		     String Subject_ID = Wizard.getSubjectID(allSubjects.get(t.getTablePosition().getColumn()),Wizard.LevelConverter(Wizard.getClassLevel(ResultObject.getClass_ID())));
	    		        	        String Student_ID = Wizard.getStudentID(studentsnames.get(t.getTablePosition().getRow()));
	    		        	        String Result_ID = String.valueOf(selected_id);
	    		        	        
	    		        	        elements = new ArrayList<>();
	    		        	        types = new ArrayList<>();
	    		        	        
	    		        	        elements.add(String.valueOf(value));
	    		        	        types.add(Wizard.Integer);
	    		        	        elements.add(Student_ID);
	    		        	        types.add(Wizard.Integer);
	    		        	        elements.add(Result_ID);
	    		        	        types.add(Wizard.Integer);
	    		        	        elements.add(Subject_ID);
	    		        	        types.add(Wizard.Integer);
	    		        	        
	    		        		if (WhatToDo.equals("Fill Exam table"))
	    		        		{
		    		        	      
		    		        	       if(!(value <= maxExam && value >=0))
		    		        	       {

					        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("").message("القيمة التي أدخلتها غير صحيحة أو خارج المدى ").showError();
					        	             return;
		    		        	       }
		    		        	        
		    		        	        String Q = "update result_link set exam = ? where student_ID = ? and result_ID = ? and Subject_ID = ?";
		    		        	   
		    		        	        boolean executed = DBUtil.excecuteUpdate(Q,elements,types);
		    		        	        //check if he has assigned value before 		    		        	        
		    		        	        if (Wizard.numberOfUpdatedRecords==0)
		    		        	        {
		    		        	        	Q = "insert into result_link (student_ID,result_ID,Subject_ID,exam,class_work) values (?,?,?,?,30)";
		    		        	        	  elements = new ArrayList<>();
				    		        	        types = new ArrayList<>();	
				    		        	        
				    		        	        elements.add(Student_ID);
				    		        	        types.add(Wizard.Integer);
				    		        	        elements.add(Result_ID);
				    		        	        types.add(Wizard.Integer);
				    		        	        elements.add(Subject_ID);
				    		        	        types.add(Wizard.Integer);
				    		        	        elements.add(String.valueOf(value));
				    		        	        types.add(Wizard.Integer);
		    		        	        	 executed = DBUtil.excecuteUpdate(Q,elements,types);
		    		        	        	if(!executed)
		    		        	        	{
						        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("").message("الم يتم تعديل القيمة الرجاء المحاولة في وقت لاحق").showError();
		    		        	        	}
		    		        	        	else
		    		        	        	{
		    		        	        		  RefreshCharts();
		    		        	        	}
		    		        	        }
//		    		        	        else
//		    		        	        {
//			    		        	        RefreshCharts();
//	
//		    		        	        }
		    		        	       
	    		        		}
	    		        		else 
	    		        		{
	    		        			 if(!(value <= maxClassWork && value >=0))
		    		        	       {

					        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("").message("القيمة التي أدخلتها غير صحيحة أو خارج المدى ").showError();
					        	             return;
		    		        	       }
	    		        			 String Q = "update result_link set class_work = ? where student_ID = ? and result_ID = ? and Subject_ID = ?";
		    		        	   
		    		        	        boolean executed = DBUtil.excecuteUpdate(Q,elements,types);
		    		        	        //check if he has assigned value before 
		    		        	        if (Wizard.numberOfUpdatedRecords==0)
		    		        	        {
		    		        	        	Q = "insert into result_link (student_ID,result_ID,Subject_ID,exam,class_work) values (?,?,?,0,?)";
		    		        	        	 elements = new ArrayList<>();
				    		        	        types = new ArrayList<>();
				    		        	     
				    		        	        elements.add(Student_ID);
				    		        	        types.add(Wizard.Integer);
				    		        	        elements.add(Result_ID);
				    		        	        types.add(Wizard.Integer);
				    		        	        elements.add(Subject_ID);
				    		        	        types.add(Wizard.Integer);
				    		        	        elements.add(String.valueOf(value));
				    		        	        types.add(Wizard.Integer);
		    		        	        	 executed = DBUtil.excecuteUpdate(Q,elements,types);
		    		        	        	if(!executed)
		    		        	        	{
						        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("").message("الم يتم تعديل القيمة الرجاء المحاولة في وقت لاحق").showError();

		    		        	        	}
		    		        	        }
	    		        		}
	    		        			
	    		        	}            
	    		        }
	    		    }
	    		);
	    	return column;
                 }
    }
