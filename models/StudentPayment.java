package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentPayment extends DatabaseClass
{
//////////////////////////////////////////////////////////////////////////////////
	private IntegerProperty StudentID;

	public void setStudentID(Integer value) 
	{
		StudentIDProperty().set(value);
	}

	public Integer getStudentID() 
	{
		return StudentIDProperty().get();
	}

	public IntegerProperty StudentIDProperty() 
	{
		if (StudentID == null)
			StudentID = new SimpleIntegerProperty(this, "StudentID");
		return StudentID;
	}
//////////////////////////////////////////////////////////////////////////////////
	private StringProperty StudentName;

	public void setStudentName(String value)
	{
		StudentNameProperty().set(value);
	}

	public String getStudentName() {
		return StudentNameProperty().get();
	}

	public StringProperty StudentNameProperty() {
		if (StudentName == null)
			StudentName = new SimpleStringProperty(this, "StudentName");
		return StudentName;
	}
//////////////////////////////////////////////////////////////////////////////////
	private StringProperty StudentClassName;

	public void setStudentClassName(String value)
	{
		StudentClassNameProperty().set(value);
	}

	public String getStudentClassName() {
		return StudentClassNameProperty().get();
	}

	public StringProperty StudentClassNameProperty() {
		if (StudentClassName == null)
			StudentClassName = new SimpleStringProperty(this, "StudentClassName");
		return StudentClassName;
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

	// ////////////////////////////////////////////////////////////////////////////////
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
}
