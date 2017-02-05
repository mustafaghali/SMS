package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Subject extends DatabaseClass
{
  public Subject (String s , int i)
  {
	  setName(s);
	  setid(i);
  }
  public Subject ()
  {
  }
  private StringProperty Class_level;
  public void setClass_level(String value)
  { Class_levelProperty().set(value); }
  public String getClass_level() 
  { return Class_levelProperty().get(); }
  public StringProperty Class_levelProperty() 
  { 
      if (Class_level == null) Class_level = new SimpleStringProperty(this, "Class_level");
      return Class_level; 
  }  
}
