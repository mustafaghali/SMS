package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Result extends DatabaseClass
{
//////////////////////////////////////////////////////////////////////////////////
	private IntegerProperty Class_ID;

	public void setClass_ID(Integer value) {
		Class_IDProperty().set(value);
	}

	public Integer getClass_ID() {
		return Class_IDProperty().get();
	}

	public IntegerProperty Class_IDProperty() {
		if (Class_ID == null)
			Class_ID = new SimpleIntegerProperty(this, "Class_ID");
		return Class_ID;
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
    private IntegerProperty Supervisor_1;

	public void setSupervisor_1(Integer value) {
		Supervisor_1Property().set(value);
	}

	public Integer getSupervisor_1() {
		return Supervisor_1Property().get();
	}

	public IntegerProperty Supervisor_1Property() {
		if (Supervisor_1 == null)
			Supervisor_1 = new SimpleIntegerProperty(this, "Supervisor_1");
		return Supervisor_1;
	}
//////////////////////////////////////////////////////////////////////////////////
	private IntegerProperty Supervisor_2;

	public void setSupervisor_2(Integer value)
	{
		Supervisor_2Property().set(value);
	}

	public Integer getSupervisor_2() {
		return Supervisor_2Property().get();
	}

	public IntegerProperty Supervisor_2Property() {
		if (Supervisor_2 == null)
			Supervisor_2 = new SimpleIntegerProperty(this, "Supervisor_2");
		return Supervisor_2;
	}
//////////////////////////////////////////////////////////////////////////////////
	private IntegerProperty Semester_ID;

	public void setSemester_ID(Integer value) {
		Semester_IDProperty().set(value);
	}

	public Integer getSemester_ID() {
		return Semester_IDProperty().get();
	}

	public IntegerProperty Semester_IDProperty() {
		if (Semester_ID == null)
			Semester_ID = new SimpleIntegerProperty(this, "Semester_ID");
		return Semester_ID;
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
    private IntegerProperty ExamPrecentage;

	public void setExamPrecentage(Integer value) {
		ExamPrecentageProperty().set(value);
	}

	public Integer getExamPrecentage() {
		return ExamPrecentageProperty().get();
	}

	public IntegerProperty ExamPrecentageProperty() 
	{
		if (ExamPrecentage == null)
			ExamPrecentage = new SimpleIntegerProperty(this, "ExamPrecentage");
		return ExamPrecentage;
	}

	// ////////////////////////////////////////////////////////////////////////////////
	private StringProperty ClassName;

	public void setClassName(String value)
	{
		ClassNameProperty().set(value);
	}

	public String getClassName() {
		return ClassNameProperty().get();
	}

	public StringProperty ClassNameProperty() {
		if (ClassName == null)
			ClassName = new SimpleStringProperty(this, "ClassName");
		return ClassName;
	}
	
 ////////////////////////////////////////////////////////////////////////////////
	private StringProperty Level;

	public void setLevel(String value) 
	{
		LevelProperty().set(value);
	}

	public String getLevel() {
		return LevelProperty().get();
	}

	public StringProperty LevelProperty() {
		if (Level == null)
			Level = new SimpleStringProperty(this, "Level");
		return Level;
	}
}
