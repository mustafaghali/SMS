package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WorkerPayment extends DatabaseClass
{
//////////////////////////////////////////////////////////////////////////////////
	private IntegerProperty WorkerID;

	public void setWorkerID(Integer value) 
	{
		WorkerIDProperty().set(value);
	}

	public Integer getWorkerID() 
	{
		return WorkerIDProperty().get();
	}

	public IntegerProperty WorkerIDProperty() 
	{
		if (WorkerID == null)
			WorkerID = new SimpleIntegerProperty(this, "WorkerID");
		return WorkerID;
	}
//////////////////////////////////////////////////////////////////////////////////
	private StringProperty WorkerName;

	public void setWorkerName(String value)
	{
		WorkerNameProperty().set(value);
	}

	public String getWorkerName() {
		return WorkerNameProperty().get();
	}

	public StringProperty WorkerNameProperty() {
		if (WorkerName == null)
			WorkerName = new SimpleStringProperty(this, "WorkerName");
		return WorkerName;
	}
//////////////////////////////////////////////////////////////////////////////////
    private StringProperty date;
    public void setdate(String value)
    { dateProperty().set(value); }
    public String getdate() 
    { return dateProperty().get(); }
    public StringProperty dateProperty() 
    { 
        if (date == null) date = new SimpleStringProperty(this, "date");
        return date; 
    }
//////////////////////////////////////////////////////////////////////////////////
    private StringProperty type;
    public void settype(String value)
    { typeProperty().set(value); }
    public String gettype() 
    { return typeProperty().get(); }
    public StringProperty typeProperty() 
    { 
        if (type == null) type = new SimpleStringProperty(this, "type");
        return type; 
    }  
//////////////////////////////////////////////////////////////////////////////////
    private DoubleProperty Value;

  	public void setValue(Double value) 
  	{
  		ValueProperty().set(value);
  	}

  	public Double getValue() 
  	{
  		return ValueProperty().get();
  	}

  	public DoubleProperty ValueProperty() 
  	{
  		if (Value == null)
  			Value = new SimpleDoubleProperty(this, "Value");
  		return Value;
  	}

////////////////////////////////////////////////////////////////////////////////
	private StringProperty Notes;

	public void setNotes(String value)
	{
		NotesProperty().set(value);
	}

	public String getNotes() {
		return NotesProperty().get();
	}

	public StringProperty NotesProperty() {
		if (Notes == null)
			Notes = new SimpleStringProperty(this, "Notes");
		return Notes;
	}
 ////////////////////////////////////////////////////////////////////////////////
	private StringProperty JobType;
	public void setJobType(String value)
	{ JobTypeProperty().set(value); }
	public String getJobType() 
	{ return JobTypeProperty().get(); }
	public StringProperty JobTypeProperty() 
	{ 
	if (JobType == null) JobType = new SimpleStringProperty(this, "JobType");
	return JobType; 
	}
}
