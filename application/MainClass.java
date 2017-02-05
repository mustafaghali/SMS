package application;

import java.io.FileNotFoundException;
import net.sf.dynamicreports.report.exception.DRException;
import wizards.Wizard;
import Controllers.ScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainClass extends Application
{	
		//the connection flag
		public static boolean  connected = false ;
		//this is the primary stage used by different methods 
		public static  Stage primaryStage;
		// this is the border pane the main interface in the UI
		public BorderPane borderpane;
///////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public void start(Stage primaryStage)
	{
		
		this.primaryStage=primaryStage;
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		
//		primaryStage.set
//	    primaryStage.setHeight(500);
//	    primaryStage.setWidth(1200);
//	
	   Image SEMICOLONIcon = new Image(getClass().getResourceAsStream("/application/SE_.png"));
	   primaryStage.getIcons().add(SEMICOLONIcon);
	   
	   primaryStage.setResizable(false);
	   
//	   primaryStage.setOnCloseRequest(close_the_program);
	   
		try 
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Main_border.fxml"));
		      BorderPane pane = (BorderPane) loader.load();
		      borderpane=pane;
		      Controllers.Border_Control Border_controller =loader.getController();		      
		      ////////////////////////////////////
		     
		      borderpane.setOnMouseDragged(Wizard.dragStage);
		      
		      ////////////////////////////////////
		      ScreenController mainContainer = new ScreenController();
               mainContainer.setMain(this);
 		      Border_controller.myController = mainContainer;
            Border_controller.InnerBoarder.setOnMouseDragged(Wizard.dragStage);
/////////////////////////////////////////////////////////////////////////////////////////////////
		       mainContainer.loadScreen("Student_Tab","Student_Tab"); 
		       mainContainer.loadScreen("Teacher_Tab","Teacher_Tab"); 
		       mainContainer.loadScreen("Worker_Tab","Worker_Tab");
		       mainContainer.loadScreen("Results_Tab","Results_Tab");
		       mainContainer.loadScreen("Classes_Tab","Classes_Tab");
		       mainContainer.loadScreen("Transportation_Tab","Transportation_Tab");
		       mainContainer.loadScreen("Financials","Financials");
		       mainContainer.loadScreen("wep_view","wep_view");
		       mainContainer.loadScreen("aboutSMS","aboutSMS");
/////////////////////////////////////////////////////////////////////////////////////////////////
		       mainContainer.loadScreen("Student_Info","Student_Info");
		       mainContainer.loadScreen("Teacher_Info","Teacher_Info");
		       mainContainer.loadScreen("Worker_Info","Worker_Info");
		       mainContainer.loadScreen("Sclasses_Info","Sclasses_Info");
		       mainContainer.loadScreen("Transportation_Info","Transportation_Info");
		       mainContainer.loadScreen("Result_Info","Result_Info");
		       mainContainer.loadScreen("GeneralPayment_Info","GeneralPayment_Info");
		       mainContainer.loadScreen("Student_finance_Info","Student_finance_Info");
		       mainContainer.loadScreen("Teacher_finance_Info","Teacher_finance_Info");
		       mainContainer.loadScreen("Worker_finance_Info","Worker_finance_Info");
/////////////////////////////////////////////////////////////////////////////////////////////////
		       mainContainer.loadScreen("Student_Info_Edit","Student_Info_Edit");
		       mainContainer.loadScreen("Teacher_Info_Edit","Teacher_Info_Edit");
		       mainContainer.loadScreen("Worker_Info_Edit","Worker_Info_Edit");
		       mainContainer.loadScreen("Worker_Info_Edit","Worker_Info_Edit");
///////////////////////////////////////////////////////////////////////////////////////////////////
				Wizard.SystemLogin("", "");
				if (!Wizard.trueLogin)
				{
					System.exit(1);
				}
                mainContainer.setScreen("Student_Tab",""); 
		       Group root = new Group(); 
		       root.getChildren().addAll(mainContainer); 
		       ////////////////////////////////////
		      Scene scene = new Scene(pane);
		      primaryStage.setScene(scene);
		      Border_controller.InnerBoarder.setCenter(root);
		    //  pane.setCenter(root);
		      primaryStage.setTitle("SEMICOLON SCHOOL MANAGEMENT SYSTEM");
		      primaryStage.show();   
		} 
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) throws FileNotFoundException, DRException
	{
	      connected=Wizard.test_connection();
			launch(args);		
	}
}