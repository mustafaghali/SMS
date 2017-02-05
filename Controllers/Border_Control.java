package Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import wizards.Wizard;
import application.MainClass;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Border_Control implements Initializable
{
	public ScreenController myController;
/////////////////////////////////////////////////////////////////////////////////////////
@FXML
public void Teacher_Tab_Clicked(ActionEvent event) 
{
myController.setScreen("Teacher_Tab","");
}
///////////////////////////////////////////////////////////////////////////////////////
@FXML
public void Student_Tab_Clicked(ActionEvent event)
{
myController.setScreen("Student_Tab","");
}
///////////////////////////////////////////////////////////////////////////////////////
@FXML
public void Worker_Tab_Clicked(ActionEvent event)
{
myController.setScreen("Worker_Tab", "");
}

// /////////////////////////////////////////////////////////////////////////////////////
@FXML
public void Sclass_Tab_Clicked(ActionEvent event) 
{
myController.setScreen("Classes_Tab", "");
}

// /////////////////////////////////////////////////////////////////////////////////////
@FXML
public void Transportation_Tab_Clicked(ActionEvent event) 
{
myController.setScreen("Transportation_Tab", "");
}

// /////////////////////////////////////////////////////////////////////////////////////
@FXML
public void Results_Tab_Clicked(ActionEvent event)
{
myController.setScreen("Results_Tab", "");
}

// /////////////////////////////////////////////////////////////////////////////////////
@FXML
public void Financials_Tab_Clicked(ActionEvent event)
{
myController.setScreen("Financials", "");
}
// /////////////////////////////////////////////////////////////////////////////////////
@FXML
public void Calender_Tab_Clicked(ActionEvent event)
{
myController.setScreen("Financials", "");
}
// /////////////////////////////////////////////////////////////////////////////////////
@FXML
public void Reports_Tab_Clicked(ActionEvent event)
{
myController.setScreen("Financials", "");
}
// /////////////////////////////////////////////////////////////////////////////////////
@FXML
public void Attendance_Tab_Clicked(ActionEvent event)
{
myController.setScreen("Financials", "");
}
// /////////////////////////////////////////////////////////////////////////////////////

@FXML
public void Reload(ActionEvent event)
{
myController.setScreen(myController.Curr_Screen, "");
}
///////////////////////////////////////////////////////////////////////////////////////

EventHandler<Event> Reload_clicked = new EventHandler<Event>() 
{
@Override
public void handle(Event arg0) 
{
myController.setScreen(myController.Curr_Screen, "");
}
};

EventHandler<Event> Back_clicked = new EventHandler<Event>() 
{
@Override
public void handle(Event arg0) 
{
	//System.out.println(myController.History);
	if (myController.History.size()!=1)
	{
		myController.History.pop();
		// to eleminate the current screen
		 myController.setScreen(myController.History.pop(),"");
	}
	
}
};
///////////////////////////////////////////////////////////////////////////////////////

@FXML
private TabPane MainTabPane;


public void setTab(int index)
{
   MainTabPane.getSelectionModel().select(index);
}

@FXML 
private ImageView Reload ;
@FXML 
private ImageView  Back;
@FXML 
private ImageView  exit;
@FXML 
private ImageView minimize;

@FXML 
private MenuBar MenuBar;
@FXML 
private  AnchorPane upperAnchorPane;

@FXML
private MenuItem aboutSEMICOLON;
@FXML
private MenuItem aboutSMS;
@FXML
private MenuItem ControlPanel;

	
	

	
	

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		Reload.setOnMouseClicked(Reload_clicked);
		Back.setOnMouseClicked(Back_clicked);
		minimize.setOnMouseClicked(Wizard.minimize);
		exit.setOnMouseClicked(Wizard.close_the_program);
		MenuBar.setOnMouseDragged(Wizard.dragStage);
		upperAnchorPane.setOnMouseDragged(Wizard.dragStage);
		
		aboutSMS.setOnAction(new EventHandler<ActionEvent>()
				{

					@Override
					public void handle(ActionEvent event)
					{
						 myController.setScreen("aboutSMS","");
					}
		});
		aboutSEMICOLON.setOnAction(new EventHandler<ActionEvent>()
				{

					@Override
					public void handle(ActionEvent event)
					{
						Controllers.wep_view.url = "http://semicolonltd.com";
						 myController.setScreen("wep_view","");
					}
		});
		
		ControlPanel.setOnAction(new EventHandler<ActionEvent>()
				{

			@Override
			public void handle(ActionEvent event)
			{
				Controllers.wep_view.url = Wizard.phpMyAdminUrl;
				 myController.setScreen("wep_view","");
			}
});
		
	}

	
	public MainClass Main;
	public void setMain(MainClass Main) 
	{
		this.Main=Main;
		
	}
	 
	@FXML
	public BorderPane InnerBoarder;
	
	

}
