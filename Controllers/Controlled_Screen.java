package Controllers;


import wizards.Wizard;
import com.sun.prism.impl.Disposer.Record;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import models.DatabaseClass;
import models.GeneralPayment;
import models.StudentPayment;
import models.TeacherPayment;
import models.WorkerPayment;
import application.MainClass;

public class Controlled_Screen 
{
	int selectedIndex;
	
	String nextScreen;

	// keep copy of main object
	public MainClass Main;
	
	//keep copy of Screen controller
	ScreenController myController;
	
	
	
	//temp observable copy
	ObservableList<DatabaseClass> resultcopy;
	ObservableList<GeneralPayment> GeneralPaymentresult;
	ObservableList<StudentPayment> StudentPaymentresult;
	ObservableList<TeacherPayment> TeacherPaymentresult;
	ObservableList<WorkerPayment> WorkerPaymentresult;
 

	

	
	//temp query string
	public static String query;
///////////////////////////////////////////////////////////////////////////////////
public void initialize_onshow()
{

}
///////////////////////////////////////////////////////////////////////////////////
public void clear()
{}	
///////////////////////////////////////////////////////////////////////////////////
	 class  TransitionEvent implements EventHandler<MouseEvent> 
	{		 
	    @Override
	    public void handle(MouseEvent t)
	    {
	        TableCell c = (TableCell) t.getSource();
	        selectedIndex = c.getIndex();
	        Wizard.global_selected_id=get_index_id(selectedIndex);
	        //System.out.println(selectedIndex);
	        myController.setScreen(nextScreen,"");
	    } 
	}
/////////////////////////////////////////////////////////////
	 class  GeneralPaymentTransitionEvent implements EventHandler<MouseEvent> 
	{		 
	    @Override
	    public void handle(MouseEvent t)
	    {
	        TableCell c = (TableCell) t.getSource();
	        selectedIndex = c.getIndex();
	        Wizard.global_selected_generalPayment = GeneralPaymentresult.get(selectedIndex);
			nextScreen="GeneralPayment_Info";
	        myController.setScreen(nextScreen,"");
	    } 
	}
/////////////////////////////////////////////////////////////

	 class  StudentPaymentTransitionEvent implements EventHandler<MouseEvent> 
		{		 
		    @Override
		    public void handle(MouseEvent t)
		    {
		        TableCell c = (TableCell) t.getSource();
		        selectedIndex = c.getIndex();
		        Wizard.global_selected_StudentPayment= StudentPaymentresult.get(selectedIndex);
				nextScreen="Student_finance_Info";
		        myController.setScreen(nextScreen,"");
		    } 
		}
/////////////////////////////////////////////////////////////

	 class  TeacherPaymentTransitionEvent implements EventHandler<MouseEvent> 
		{		 
		    @Override
		    public void handle(MouseEvent t)
		    {
		        TableCell c = (TableCell) t.getSource();
		        selectedIndex = c.getIndex();
		        Wizard.global_selected_TeacherPayment= TeacherPaymentresult.get(selectedIndex);
				nextScreen="Teacher_finance_Info";
		        myController.setScreen(nextScreen,"");
		    } 
		}
/////////////////////////////////////////////////////////////
	 class  WorkerPaymentTransitionEvent implements EventHandler<MouseEvent> 
		{		 
		    @Override
		    public void handle(MouseEvent t)
		    {
		        TableCell c = (TableCell) t.getSource();
		        selectedIndex = c.getIndex();
		        Wizard.global_selected_WorkerPayment = WorkerPaymentresult.get(selectedIndex);
				nextScreen="Worker_finance_Info";
		        myController.setScreen(nextScreen,"");
		    } 
		}
/////////////////////////////////////////////////////////////

	 Callback<TableColumn, TableCell> GeneralPaymentTransitionEventFactory =new Callback<TableColumn, TableCell>()
			 {
	    @Override
	    public TableCell call(TableColumn p)
	    {
	        MyStringTableCell cell = new MyStringTableCell();
	        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new GeneralPaymentTransitionEvent());
	        return cell;
	    }
	}; 
/////////////////////////////////////////////////////////////
	 Callback<TableColumn, TableCell> GeneralPaymentTransitionEventFactoryForDoubles =new Callback<TableColumn, TableCell>()
			 { 
	    @Override
	    public TableCell call(TableColumn p)
	    {
	        MyDoubleTableCell cell = new MyDoubleTableCell();
	        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new GeneralPaymentTransitionEvent());
	        return cell;
	    }
	}; 
/////////////////////////////////////////////////////////////
	 Callback<TableColumn, TableCell> StudentPaymentTransitionEventFactory =new Callback<TableColumn, TableCell>()
			 { 
	    @Override
	    public TableCell call(TableColumn p)
	    {
	        MyStringTableCell cell = new MyStringTableCell();
	        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new StudentPaymentTransitionEvent());
	        return cell;
	    }
	}; 
/////////////////////////////////////////////////////////////
	 Callback<TableColumn, TableCell> TeacherPaymentTransitionEventFactory =new Callback<TableColumn, TableCell>()
			 { 
	    @Override
	    public TableCell call(TableColumn p)
	    {
			nextScreen="GeneralPayment_Info";
	        MyStringTableCell cell = new MyStringTableCell();
	        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new TeacherPaymentTransitionEvent());
	        return cell;
	    }
	}; 
/////////////////////////////////////////////////////////////
	 Callback<TableColumn, TableCell> TeacherPaymentTransitionEventFactoryForDoubles =new Callback<TableColumn, TableCell>()
			 { 
		 
	    @Override
	    public TableCell call(TableColumn p)
	    {
	        MyDoubleTableCell cell = new MyDoubleTableCell();
	        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new TeacherPaymentTransitionEvent());
	        return cell;
	    }
	}; 
/////////////////////////////////////////////////////////////
	 Callback<TableColumn, TableCell> WorkerPaymentTransitionEventFactory =new Callback<TableColumn, TableCell>()
			 { 
	    @Override
	    public TableCell call(TableColumn p)
	    {
	        MyStringTableCell cell = new MyStringTableCell();
	        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new WorkerPaymentTransitionEvent());
	        return cell;
	    }
	}; 
/////////////////////////////////////////////////////////////
	 Callback<TableColumn, TableCell> WorkerPaymentTransitionEventFactoryForDoubles =new Callback<TableColumn, TableCell>()
			 { 
	    @Override
	    public TableCell call(TableColumn p)
	    {
	        MyDoubleTableCell cell = new MyDoubleTableCell();
	        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new WorkerPaymentTransitionEvent());
	        return cell;
	    }
	}; 
/////////////////////////////////////////////////////////////	
	 Callback<TableColumn, TableCell> StudentPaymentTransitionEventFactoryForDouble =new Callback<TableColumn, TableCell>()
			 { 
	    @Override
	    public TableCell call(TableColumn p)
	    {
	        MyDoubleTableCell cell = new MyDoubleTableCell();
	        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new StudentPaymentTransitionEvent());
	        return cell;
	    }
	}; 
/////////////////////////////////////////////////////////////
	 
   Callback<TableColumn, TableCell> integerCellFactory= new Callback<TableColumn, TableCell>() 
			{
     @Override
       public TableCell call(TableColumn p)
      {
       	MyIntegerTableCell cell = new MyIntegerTableCell();
           cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new TransitionEvent());
             return cell;
       }
    };
///////////////////////////////////////////////////////////////////// 
 Callback<TableColumn, TableCell> TransitionEventFactory =new Callback<TableColumn, TableCell>()
		 {
    @Override
    public TableCell call(TableColumn p)
    {
        MyStringTableCell cell = new MyStringTableCell();
        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, new TransitionEvent());
        return cell;
    }
}; 
////////////////////////////////////////////////////////////////// 


  class MyStringTableCell extends TableCell<Record, String>
  {
	            	  
	                  @Override
	                  public void updateItem(String item, boolean empty)
	                  {
	                      super.updateItem(item, empty);
	                      setText(empty ? null : getString());
	                      setGraphic(null);
	                      
	                  }
	                  private String getString() {
	                      return getItem() == null ? "" : getItem().toString();
	                  }
                       }
	/////////////////////////////////////////////////////////////
 class MyIntegerTableCell 
	              extends TableCell<Record, Integer>
	              {
	              	
	                  @Override
	                  public void updateItem(Integer item, boolean empty) {
	                      super.updateItem(item, empty);
	                      setText(empty ? null : getString());
	                      setGraphic(null);
	                  }
	                  private String getString() {
	                      return getItem() == null ? "" : getItem().toString();
	                  }
	              }
//////////////////////////////////////////////////////////////// 
 class MyDoubleTableCell 
 extends TableCell<Record, Double>
 {
 	
     @Override
     public void updateItem(Double item, boolean empty) {
         super.updateItem(item, empty);
         setText(empty ? null : getString());
         setGraphic(null);
     }
     private String getString() {
         return getItem() == null ? "" : getItem().toString();
     }
 }
////////////////////////////////////////////////////////////////

 void setMain(MainClass Main) 
	{
		this.Main = Main;

	}
	
	public void setScreenParent(ScreenController screenparent) 
	{
		myController = screenparent;
	}
/////////////////////////////////////////////////////////////////////////////////////////
//	@FXML
//	public void Teacher_Tab_Clicked(ActionEvent event) 
//	{
//		myController.setScreen("Teacher_Tab","");
//	}
/////////////////////////////////////////////////////////////////////////////////////////
//	 @FXML
//		public void Student_Tab_Clicked(ActionEvent event)
//		{
//	     myController.setScreen("Student_Tab","");
//	     }
/////////////////////////////////////////////////////////////////////////////////////////
//	@FXML
//	public void Worker_Tab_Clicked(ActionEvent event) {
//		myController.setScreen("Worker_Tab", "");
//	}
//
//	// /////////////////////////////////////////////////////////////////////////////////////
//	@FXML
//	public void Sclass_Tab_Clicked(ActionEvent event) {
//		myController.setScreen("Classes_Tab", "");
//	}
//
//	// /////////////////////////////////////////////////////////////////////////////////////
//	@FXML
//	public void Transportation_Tab_Clicked(ActionEvent event) {
//		myController.setScreen("Transportation_Tab", "");
//	}
//
//	// /////////////////////////////////////////////////////////////////////////////////////
//	@FXML
//	public void Results_Tab_Clicked(ActionEvent event)
//	{
//		myController.setScreen("Results_Tab", "");
//	}
//
//	// /////////////////////////////////////////////////////////////////////////////////////
//	@FXML
//	public void Financials_Tab_Clicked(ActionEvent event)
//	{
//		myController.setScreen("Financials", "");
//	}
//	// /////////////////////////////////////////////////////////////////////////////////////
//		@FXML
//		public void Calender_Tab_Clicked(ActionEvent event)
//		{
//			//myController.setScreen("Financials", "");
//		}
//		// /////////////////////////////////////////////////////////////////////////////////////
//		@FXML
//		public void Reports_Tab_Clicked(ActionEvent event)
//		{
//			//myController.setScreen("Financials", "");
//		}
//		// /////////////////////////////////////////////////////////////////////////////////////
//		@FXML
//		public void Attendance_Tab_Clicked(ActionEvent event)
//		{
//			//myController.setScreen("Financials", "");
//		}
//		// /////////////////////////////////////////////////////////////////////////////////////
//
	@FXML
	private void Reload(ActionEvent event)
	{
		myController.setScreen(myController.Curr_Screen, "");
	}
	
	
	EventHandler<Event> reload = new EventHandler<Event>() 
			{
		
		@Override
		public void handle(Event event)
		{
			myController.setScreen(myController.Curr_Screen, "");
		}
	};
//// /////////////////////////////////////////////////////////////////////////////////////
//    
//	EventHandler<Event> Reload_clicked = new EventHandler<Event>() 
//			{
//		        @Override
//	             	public void handle(Event arg0) 
//	                      	{
//		        	          myController.setScreen(myController.Curr_Screen, "");
//	                      	}
//			};
//			
//			EventHandler<Event> Back_clicked = new EventHandler<Event>() 
//					{
//				        @Override
//			             	public void handle(Event arg0) 
//			                      	{
//				        	          myController.setScreen(myController.prev_Screen,"");
//			                      	}
//					};
//// /////////////////////////////////////////////////////////////////////////////////////

	@FXML
	private TabPane MainTabPane;

	public int get_index_id(int index) 
	{
		return resultcopy.get(index).getid();
	}

	public void setTab(int index)
	{
		MainTabPane.getSelectionModel().select(index);
	}

}


