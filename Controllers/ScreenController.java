package Controllers;

import java.util.HashMap;
import java.util.Stack;

import application.MainClass;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ScreenController extends StackPane
{
	
	private HashMap<String, Node> screens = new HashMap<>(); 
	private HashMap<String, Controlled_Screen> Controllers = new HashMap<>(); 
	public MainClass Main;
	Controlled_Screen curr_Contoller;
	String Curr_Screen;
	String prev_Screen;
	
	public static  Stack<String> History = new Stack<>();
	
	public void setMain (MainClass Main)
	{
		this.Main=Main;
	}
	// holds screens to be displayed
	public ScreenController() 
	{
		super();
	}

	// add Screen to the collection
	
	 public void addScreen(String name, Node screen) 
	 { 
	       screens.put(name, screen); 
	 } 
	 
	 public void addController(String name, Controlled_Screen controlled_Screen) 
	 { 
		 Controllers.put(name, controlled_Screen); 
	 } 

	// returns the node with approciate name
	public Node getScreen(String name) 
	{
		return screens.get(name);
	}	
	public Controlled_Screen getContollers(String name) 
	{
		return Controllers.get(name);
	}	
	// load the fxml file, add the screen to the screens stack
	
	// finally inject the screen pane to the controller
	 Controlled_Screen MyControlledScreen;
	 
	  public boolean loadScreen(String name, String resource) 
	  {
		     try { 
		           FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/view/"+resource+".fxml"));
		            Parent loadScreen = (Parent) myLoader.load(); 
		             MyControlledScreen=((Controlled_Screen) myLoader.getController());
		             MyControlledScreen.setMain(Main);
		             MyControlledScreen.setScreenParent(this); 
		             addController(name,MyControlledScreen);
		            addScreen(name, loadScreen); 
		            return true; 
		         }
		     catch(Exception e)
		     { 
		       System.out.println(e.getMessage()); 
		       return false; 
		     } 
		   } 
	
	
	  public boolean setScreen(final String name,String TabIndex)
	 { 
	     if(screens.get(name) != null) 
	     { //screen loaded 
	       final DoubleProperty opacity = opacityProperty(); 

	       //Is there is more than one screen 
	       if(!getChildren().isEmpty())
	       { 
	    	   
	         Timeline fade = new Timeline
	        		 ( 
	           new KeyFrame(Duration.ZERO,new KeyValue(opacity,1.0))
	           , new KeyFrame(new Duration(500)
	           ,new EventHandler() 
	           {
	                 @Override
	                 public void handle(Event t) 
	                 { 
	                	 curr_Contoller = Controllers.get(name);
	                	    if (TabIndex.length()!=0)
	                	    {
	                	    	curr_Contoller.setTab(Integer.parseInt(TabIndex));
	                	    }
		                   curr_Contoller.initialize_onshow();
		                   
	                   //remove displayed screen 
//		                   prev_Screen = Curr_Screen;
		                   Curr_Screen = name;
		                   History.add(name);
                      //  System.out.println(History);
		               //    System.out.println("current : "+ Curr_Screen);
		                 //  System.out.println("previous : "+ prev_Screen);
		                 //  prev_Screen =;
	                   getChildren().remove(0); 
	                   
	                  
		                   
						
	                   //add new screen 
	                   getChildren().add(0, screens.get(name)); 
	                   
	                   Timeline fadeIn = new Timeline( 
	                       new KeyFrame(Duration.ZERO, 
	                              new KeyValue(opacity, 0.0)), 
	                       new KeyFrame(new Duration(1), 
	                              new KeyValue(opacity, 1.0))); 
	                   fadeIn.play(); 
	                 }
	               }, new KeyValue(opacity, 0.0))); 
	         fade.play(); 
	       } 
	       else
	       { 
	         //no one else been displayed, then just show 
	    	 // System.out.println("more than one");
	    	   
	         setOpacity(0.0); 
	         History.add(name);
             Curr_Screen = name;
//             prev_Screen =name;
	         getChildren().add(screens.get(name)); 
	         Timeline fadeIn = new Timeline( 
	             new KeyFrame(Duration.ZERO, 
	                          new KeyValue(opacity, 0.0)), 
	             new KeyFrame(new Duration(1), 
	                          new KeyValue(opacity, 1.0))); 
	         fadeIn.play(); 
	       } 
	       return true; 
	     }
	     else
	     { 
	    	 
	         System.out.println("screen hasn't been loaded!\n");
	         setScreen("Student_Tab","");
	         return false; 
	   } 
	}

	  public boolean unloadScreen(String name)
	 { 
	     if(screens.remove(name) == null)
	     { 
	       System.out.println("Screen didn't exist"); 
	       return false; 
	     } else { 
	       return true; 
	     } 
	   } 

}
