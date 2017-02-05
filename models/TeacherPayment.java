package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TeacherPayment extends DatabaseClass
{
//////////////////////////////////////////////////////////////////////////////////
	private IntegerProperty TeacherID;

	public void setTeacherID(Integer value) 
	{
		TeacherIDProperty().set(value);
	}

	public Integer getTeacherID() 
	{
		return TeacherIDProperty().get();
	}

	public IntegerProperty TeacherIDProperty() 
	{
		if (TeacherID == null)
			TeacherID = new SimpleIntegerProperty(this, "TeacherID");
		return TeacherID;
	}
//////////////////////////////////////////////////////////////////////////////////
	private StringProperty TeacherName;

	public void setTeacherName(String value)
	{
		TeacherNameProperty().set(value);
	}

	public String getTeacherName() {
		return TeacherNameProperty().get();
	}

	public StringProperty TeacherNameProperty() {
		if (TeacherName == null)
			TeacherName = new SimpleStringProperty(this, "TeacherName");
		return TeacherName;
	}
//////////////////////////////////////////////////////////////////////////////////
	private StringProperty TeacherClassName;

	public void setTeacherClassName(String value)
	{
		TeacherClassNameProperty().set(value);
	}

	public String getTeacherClassName() {
		return TeacherClassNameProperty().get();
	}

	public StringProperty TeacherClassNameProperty() {
		if (TeacherClassName == null)
			TeacherClassName = new SimpleStringProperty(this, "TeacherClassName");
		return TeacherClassName;
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
