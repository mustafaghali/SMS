package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transporation extends DatabaseClass
{
	public Transporation (int id,String s)
	{
		setid(id);
		setDestination(s);
	}
	public Transporation ()
	{}
//////////////////////////////////////////////////////////////////////////////////
	private DoubleProperty delivery_cost;
	public void setdelivery_cost(Double value)
  { delivery_costProperty().set(value); }
  public Double getdelivery_cost() 
  { return delivery_costProperty().get(); }
  public DoubleProperty delivery_costProperty() 
  { 
      if ( delivery_cost == null) delivery_cost = new SimpleDoubleProperty(this,"delivery_cost");
      return delivery_cost; 
  }
//////////////////////////////////////////////////////////////////////////////////
  private StringProperty Destination;
  public void setDestination(String value)
  { DestinationProperty().set(value); }
  public String getDestination() 
  { return DestinationProperty().get(); }
  public StringProperty DestinationProperty() 
  { 
      if (Destination == null) Destination = new SimpleStringProperty(this, "Destination");
      return Destination; 
  }
//////////////////////////////////////////////////////////////////////////////////
  private StringProperty Driver_name;
  public void setDriver_name(String value)
  { Driver_nameProperty().set(value); }
  public String getDriver_name() 
  { return Driver_nameProperty().get(); }
  public StringProperty Driver_nameProperty() 
  { 
      if (Driver_name == null) Driver_name = new SimpleStringProperty(this, "Driver_name");
      return Driver_name; 
  }
//////////////////////////////////////////////////////////////////////////////////
  private StringProperty vehicle_type;
  public void setvehicle_type(String value)
  { vehicle_typeProperty().set(value); }
  public String getvehicle_type() 
  { return vehicle_typeProperty().get(); }
  public StringProperty vehicle_typeProperty() 
  { 
      if (vehicle_type == null) vehicle_type = new SimpleStringProperty(this, "vehicle_type");
      return vehicle_type; 
  }
//////////////////////////////////////////////////////////////////////////////////

}
