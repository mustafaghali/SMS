package Controllers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import models.*;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;

import databaseUtilities.DBUtil;
import databaseUtilities.JDBC;
import wizards.Wizard;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sclasses_Info_view extends Controlled_Screen implements Initializable 
{
	int selected_id;
    SClasses ClassObject;
    List<String> types;
	List<String> elements;
	
    public void clear()
    {
 	   Class_name.setText("لاتوجد بيانات للعرض");
 	   Class_ID.setText("لاتوجد بيانات للعرض");
 	     Supervisor.setText("لاتوجد بيانات للعرض");
 	     StudyingFees.setText("لاتوجد بيانات للعرض");
 	     FatherSeetsFees.setText("لاتوجد بيانات للعرض");
 	     SummerCourseFees.setText("لاتوجد بيانات للعرض");
 	     ClassNameShow.setText("لاتوجد بيانات للعرض");
 	     Capacity.setText("لاتوجد بيانات للعرض");
 	     Level.setText("لاتوجد بيانات للعرض");
 	     Students_Total_number.setText("لاتوجد بيانات للعرض");
 	     tabel.setItems(null);
    }
    
    public void clear_Edit_Tab()
    {
 	   NewClassName.clear();
 	   NewCapacity.clear();
 	   NewClassLevel.setValue(null);
 	   NewSupervisor.setValue(null);
 	   NewStudyingFees.clear();
 	   NewFatherSeetsFees.clear();
 	   NewSummerCourseFees.clear();
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		nextScreen = "Student_Info_Edit";
		ID.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
		ID.setCellFactory(integerCellFactory);
		Name.setCellValueFactory(new PropertyValueFactory<Student, String>("Name"));
		Name.setCellFactory(TransitionEventFactory);

////////////////////////////////////////////////////////////////////////////////////////
//	//delete Class info 
		EventHandler<Event> DeleteClass = new EventHandler<Event>()
				{
			
			        @Override
			         public void handle(Event arg0) 
			        {	
			        	Action response = Dialogs.create()
			        	        .owner(null)
			        	        .title("رسالة تأكيد")
			        	        .masthead("")
			        	        .message("هل أنت متأكد من أنك تريد مسح جميع بيانات هذا  العامل ؟")
			        	        .actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
			        	        .showConfirm();

			        	if (response == Dialog.ACTION_YES)
			        	{
			        		
			        		Boolean executed=DBUtil.excecuteUpdate("delete from classes where id ="+selected_id);
			        		if(executed)
			        		{
			        			Dialogs.create()
			        	        .owner(null)
			        	        .title("رسالة إشعار")
			        	        .message("تم حذف بيانات  الفصل بنجاح")
			        	        .showInformation();
				        		myController.setScreen("Classes_Tab","");
				        		Wizard.global_selected_id=1;
				        		return;
			        		}
			        		else
			        		{
			        			Dialogs.create()
								.owner(null)
								.title("رسالة خطأ")
								.masthead("لم يتم حذف بيانات الفصل")
								.message("الرجاء المحاولة في وقت لاحق")
								.showWarning();
			        		}
			        	}
			        }
		        };
		        DeleteClassImage.setOnMouseClicked(DeleteClass);
		        DeleteClassLabel.setOnMouseClicked(DeleteClass);
///////////////////////////////////////////////////////////////////////////////////////
		        //Edit class
		        EventHandler<Event> EditClass = new EventHandler<Event>() 
						{
					        @Override
				             	public void handle(Event arg0) 
				                      	{
					        	           if(NewClassName.getText().trim().length()==0)
					        	           {
					        	   			Dialogs.create().owner(null).title("رسالة خطأ").masthead("لايمكن ترك اسم  الفصل فارغا!").message("الرجاء إدخال  الاسم الجديد   للفصل ").showError();
//					        	   			NewWorkerAddress.setStyle();
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
//						construct the insert query
						//12 prepared filed
						
						String Q = "update classes set Name=?, Level=?,Capacity=?,Studying_fees=?,father_seats_fees=?,summer_course_fees=?,supervisor_ID=? "
								+ "where id="+selected_id;
			              types=new ArrayList<>();
		   			     elements = new ArrayList<>();
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
								 types.add(Wizard.String);
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
								 types.add(Wizard.String);
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
							.message("تمت عملية التعديل بنجاح")
							.showInformation();
							initialize_onshow();// to show the new data
						}
						else
						
								Dialogs.create()
										.owner(null)
										.title("رسالة خطأ")
										.masthead("لم يتم تعديل بيانات الفصل")
										.message(
												"الرجاء التأكد من صحة البيانات المدحلة والتأكد من أن الاسم لم يستخدم مسبقا")
										.showWarning();
					     }
				       };
				ConfirmEditImage.setOnMouseClicked(EditClass);
				ConfirmEditLabel.setOnMouseClicked(EditClass);
///////////////////////////////////////////////////////////////////////////////////////
////fill the transporations comboboxes
NewClassLevel.setItems(Wizard.getClassesLevels());
///////////////////////////////////////////////////////////////////////////////////////
	}
	
	@Override
	public void initialize_onshow() 
	{
		clear();
		clear_Edit_Tab();
			selected_id = Wizard.global_selected_id;
			ClassObject = Wizard.getSClassObject(selected_id);
			
			Class_name.setText(ClassObject.getName());
			ClassNameShow.setText(ClassObject.getName()+ " : ");
			Class_ID.setText(ClassObject.getid().toString());
			if (ClassObject.getLevel()!=null)
				Level.setText(Wizard.LevelConverter(ClassObject.getLevel()));
			if (ClassObject.getCapacity() != null)
				Capacity.setText(ClassObject.getCapacity().toString()+ "  طالب");
			if (ClassObject.getStudying_fees() != null)
				StudyingFees.setText(ClassObject.getStudying_fees().toString());
			if (ClassObject.getfather_seets_fees() != null)
				FatherSeetsFees.setText(ClassObject.getfather_seets_fees().toString());
			if (ClassObject.getsummer_course_fees() != null)
				SummerCourseFees.setText(ClassObject.getsummer_course_fees().toString());
			if (ClassObject.getSupervisorID() != null)
				Supervisor.setText(Wizard.getTeacherName(ClassObject.getSupervisorID()));
				Students_Total_number.setText(Wizard.TotalStudentsPerSClass(ClassObject.getid().toString())+"  طالب");
				ObservableList  result =(ObservableList<Student>) JDBC.fill_Otabel("Select * from Student where class_id="+selected_id,Wizard.Student,null,null);
				resultcopy=result;
				tabel.setItems(result);
				
/////////////////////////////////////////////////////////////////////////////////////////////////
				NewSupervisor.setItems(Wizard.getTeacherNames());

				NewClassName.setText(ClassObject.getName());
				NewCapacity.setText(ClassObject.getCapacity().toString());
				NewClassLevel.setValue(Wizard.LevelConverter(ClassObject.getLevel()));
				NewSummerCourseFees.setText(ClassObject.getsummer_course_fees().toString());
				NewStudyingFees.setText(ClassObject.getStudying_fees().toString());
				NewFatherSeetsFees.setText(ClassObject.getfather_seets_fees().toString());
				NewSupervisor.setValue(Wizard.getTeacherName(ClassObject.getSupervisorID()));
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////

	
	@FXML
    private TableColumn ID;
    @FXML
    private TableColumn Name;
    @FXML
    private TableView <Student>tabel;
    
	@FXML
    private Label Class_ID;
    @FXML
    private Label DeleteClassLabel;
    @FXML
    private ImageView DeleteClassImage;
	@FXML
	private Label Class_name;
	@FXML
	private Label Supervisor;
	@FXML
	private Label StudyingFees;
	@FXML
	private Label FatherSeetsFees;
	@FXML
	private Label SummerCourseFees;
	@FXML
	private Label ClassNameShow;
	@FXML
	private Label Capacity;
	@FXML
	private Label Level;
	@FXML
	private Label Students_Total_number;
/////////////////////////////////////////////////////////////////////////////////////////////////
	//edit tab elements 
	@FXML
    private TextField NewClassName;
	 @FXML
	 private TextField NewCapacity;
	 @FXML
	 private ComboBox <String>NewClassLevel;
	 @FXML
	 private TextField NewSummerCourseFees;
	 @FXML
	 private TextField NewStudyingFees;
	 @FXML
	 private TextField NewFatherSeetsFees;
	 @FXML
	 private ComboBox <String> NewSupervisor;
	 @FXML
	 private ImageView ConfirmEditImage;
	 @FXML
	 private Label ConfirmEditLabel;
/////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
}
