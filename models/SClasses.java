package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SClasses extends DatabaseClass
{
///////////////////////////////////////////////////////////////////////////
private DoubleProperty father_seets_fees;
public void setfather_seets_fees(Double value)
{ father_seets_feesProperty().set(value); }
public Double getfather_seets_fees() 
{ return father_seets_feesProperty().get(); }
public DoubleProperty father_seets_feesProperty() 
{ 
if ( father_seets_fees == null) father_seets_fees = new SimpleDoubleProperty(this,"father_seets_fees");
return father_seets_fees; 
}
//////////////////////////////////////////////////////////////////////////////////
private DoubleProperty summer_course_fees;
	public void setsummer_course_fees(Double value)
  { summer_course_feesProperty().set(value); }
  public Double getsummer_course_fees() 
  { return summer_course_feesProperty().get(); }
  public DoubleProperty summer_course_feesProperty() 
  { 
      if ( summer_course_fees == null) summer_course_fees = new SimpleDoubleProperty(this,"summer_course_fees");
      return summer_course_fees; 
  }
//////////////////////////////////////////////////////////////////////////////////
	private DoubleProperty Studying_fees;
	public void setStudying_fees(Double value)
  { Studying_feesProperty().set(value); }
  public Double getStudying_fees() 
  { return Studying_feesProperty().get(); }
  public DoubleProperty Studying_feesProperty() 
  { 
      if ( Studying_fees == null) Studying_fees = new SimpleDoubleProperty(this,"Studying_fees");
      return Studying_fees; 
  }
//////////////////////////////////////////////////////////////////////////////////
  private StringProperty Level;
  public void setLevel(String value)
  { LevelProperty().set(value); }
  public String getLevel() 
  { return LevelProperty().get(); }
  public StringProperty LevelProperty() 
  { 
      if (Level == null) Level = new SimpleStringProperty(this, "Level");
      return Level; 
  }
//////////////////////////////////////////////////////////////////////////////////
	private IntegerProperty Capacity;
	public void setCapacity(Integer value)
  { CapacityProperty().set(value); }
  public Integer getCapacity() 
  { return CapacityProperty().get(); }
  public IntegerProperty CapacityProperty() 
  { 
      if ( Capacity == null) Capacity = new SimpleIntegerProperty(this,"Capacity");
      return Capacity; 
  }
	 ////////////////////////////////////////////////////////////////////////////////
  private StringProperty ClassType;
  public void setClassType(String value)
  { ClassTypeProperty().set(value); }
  public String getClassType() 
  { return ClassTypeProperty().get(); }
  public StringProperty ClassTypeProperty() 
  { 
      if (ClassType == null) ClassType = new SimpleStringProperty(this, "ClassType");
      return ClassType; 
  }
//////////////////////////////////////////////////////////////////////////////////
	private IntegerProperty SupervisorID;
	public void setSupervisorID(Integer value)
{ SupervisorIDProperty().set(value); }
public Integer getSupervisorID() 
{ return SupervisorIDProperty().get(); }
public IntegerProperty SupervisorIDProperty() 
{ 
    if ( SupervisorID == null) SupervisorID = new SimpleIntegerProperty(this,"SupervisorID");
    return SupervisorID; 
}
	 ////////////////////////////////////////////////////////////////////////////////
  
}
