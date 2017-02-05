package Controllers;


import java.net.URL;
import java.util.ResourceBundle;







import org.controlsfx.dialog.Dialogs;

import wizards.Wizard;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class wep_view extends Controlled_Screen implements Initializable 
{
	@FXML
	private WebView web_view;
	
	public static String url = Wizard.phpMyAdminUrl ; 
	
	 WebEngine web_Engine=null;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		 web_Engine = web_view.getEngine();
		
	}
	
	@Override
	public void initialize_onshow() 
	{
		 
		/* Service<Void> service = new Service<Void>() {
	         @Override
	             protected Task<Void> createTask() {
	                 return new Task<Void>() {
	                     @Override
	                     protected Void call()throws InterruptedException
	                     {
	                         updateMessage("جاري تحميل المحتوى");
	                                      updateProgress(0, 10);
	                                      for (int i = 0; i < 10; i++) 
	                                      {
	                                          Thread.sleep(300);
	                                          //DO BACKGROUND WORK HERE
	                                 		 web_Engine.load(url);

	                                          Platform.runLater(new Runnable ()
	                                          {
	                                              @Override
	                                              public void run()
	                                              {
	                                                   //DO LIVE SCENE GRAPH WORK HERE
	                                              }
	                                          });

	                                          updateProgress(i + 1, 10);
	                                          updateMessage("الرجاء الانتظار ريثما يتم التحميل");
	                                       }
	                       //pdateMessage("");
	                         return null;
	                     }
	               };
	             }
	         };
	         Dialogs.create()
            .owner(null)
            .title("Progress Dialog")
            .masthead("Doing The Thing")
            .showWorkerProgress(service);
	         service.start();*/
       	  web_Engine.load(url);


		
	}
	
	
	
}
