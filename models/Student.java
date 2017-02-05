package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student extends DatabaseClass
{
//////////////////////////////////////////////////////////////////////////////////
	public Student (int id,String name)
	{
		setName(name);
		setid(id);
	}
///////////////////////////////////////////////////////////////////////////////////
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
    private StringProperty Email_Address;
    public void setEmail_Address(String value)
    { Email_AddressProperty().set(value); }
    public String getEmail_Address() 
    { return Email_AddressProperty().get(); }
    public StringProperty Email_AddressProperty() 
    { 
        if (Email_Address == null) Email_Address = new SimpleStringProperty(this, "Email_Address");
        return Email_Address; 
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
    private DoubleProperty transp_fees;
	public void settransp_fees(Double value)
    { transp_feesProperty().set(value); }
    public Double gettransp_fees() 
    { return transp_feesProperty().get(); }
    public DoubleProperty transp_feesProperty() 
    { 
        if ( transp_fees == null) transp_fees = new SimpleDoubleProperty(this,"transp_fees");
        return transp_fees; 
    }
////////////////////////////////////////////////////////////////////////////////
    private IntegerProperty Class_ID;
	public void setClass_ID(Integer value)
    { Class_IDProperty().set(value); }
    public Integer getClass_ID() 
    { return Class_IDProperty().get(); }
    public IntegerProperty Class_IDProperty() 
    { 
        if ( Class_ID == null) Class_ID = new SimpleIntegerProperty(this,"Class_ID");
        return Class_ID; 
    }
////////////////////////////////////////////////////////////////////////////////
    private IntegerProperty transp_ID;
	public void settransp_ID(Integer value)
    { transp_IDProperty().set(value); }
    public Integer gettransp_ID() 
    { return transp_IDProperty().get(); }
    public IntegerProperty transp_IDProperty() 
    { 
        if ( transp_ID == null) transp_ID = new SimpleIntegerProperty(this,"transp_ID");
        return transp_ID; 
    }
////////////////////////////////////////////////////////////////////////////////
    private StringProperty phone_number;
    public void setphone_number(String value)
    { phone_numberProperty().set(value); }
    public String getphone_number() 
    { return phone_numberProperty().get(); }
    public StringProperty phone_numberProperty() 
    { 
        if (phone_number == null) phone_number = new SimpleStringProperty(this, "phone_number");
        return phone_number; 
    }
//////////////////////////////////////////////////////////////////////////////////////
    private StringProperty father_phone_number;
    public void setfather_phone_number(String value)
    { father_phone_numberProperty().set(value); }
    public String getfather_phone_number() 
    { return father_phone_numberProperty().get(); }
    public StringProperty father_phone_numberProperty() 
    { 
        if (father_phone_number == null) father_phone_number = new SimpleStringProperty(this, "father_phone_number");
        return father_phone_number; 
    }
//////////////////////////////////////////////////////////////////////////////////////
    private StringProperty address;
    public void setaddress(String value)
    { addressProperty().set(value); }
    public String getaddress() 
    { return addressProperty().get(); }
    public StringProperty addressProperty() 
    { 
        if (address == null) address = new SimpleStringProperty(this, "address");
        return address; 
    }
//////////////////////////////////////////////////////////////////////////////////////
    private StringProperty B_Date;
    public void setB_Date(String value)
    { B_DateProperty().set(value); }
    public String getB_Date() 
    { return B_DateProperty().get(); }
    public StringProperty B_DateProperty() 
    { 
        if (B_Date == null) B_Date = new SimpleStringProperty(this, "B_Date");
        return B_Date; 
    }
//////////////////////////////////////////////////////////////////////////////////////
    private StringProperty Health_Status;
    public void setHealth_Status(String value)
    { Health_StatusProperty().set(value); }
    public String getHealth_Status() 
    { return Health_StatusProperty().get(); }
    public StringProperty Health_StatusProperty() 
    { 
        if (Health_Status == null) Health_Status = new SimpleStringProperty(this, "Health_Status");
        return Health_Status; 
    }
//////////////////////////////////////////////////////////////////////////////////////
    private StringProperty specialization;
    public void setspecialization(String value)
    { specializationProperty().set(value); }
    public String getspecialization() 
    { return specializationProperty().get(); }
    public StringProperty specializationProperty() 
    { 
        if (specialization == null) specialization = new SimpleStringProperty(this, "specialization");
        return specialization; 
    }
  
//////////////////////////////////////////////////////////////////////////////////////
    private StringProperty Student_type;
    public void setStudent_type(String value)
    { Student_typeProperty().set(value); }
    public String getStudent_type() 
    { return Student_typeProperty().get(); }
    public StringProperty Student_typeProperty() 
    { 
        if (Student_type == null) Student_type = new SimpleStringProperty(this, "Student_type");
        return Student_type; 
    }  
//////////////////////////////////////////////////////////////////////////////////////
    private StringProperty ClassName;
    public void setClassName(String value)
    { ClassNameProperty().set(value); }
    public String getClassName() 
    { return ClassNameProperty().get(); }
    public StringProperty ClassNameProperty() 
    { 
        if (ClassName == null) ClassName = new SimpleStringProperty(this, "ClassName");
        return ClassName; 
    }
//////////////////////////////////////////////////////////////////////////////////////
private StringProperty ClassLevel;
public void setClassLevel(String value)
{ ClassLevelProperty().set(value); }
public String getClassLevel() 
{ return ClassLevelProperty().get(); }
public StringProperty ClassLevelProperty() 
{ 
if (ClassLevel == null) ClassLevel = new SimpleStringProperty(this, "ClassLevel");
return ClassLevel; 
}
//////////////////////////////////////////////////////////////////////////////////////
private StringProperty Transp_Name;
public void setTransp_Name(String value)
{ Transp_NameProperty().set(value); }
public String getTransp_Name() 
{ return Transp_NameProperty().get(); }
public StringProperty Transp_NameProperty() 
{ 
if (Transp_Name == null) Transp_Name = new SimpleStringProperty(this, "Transp_Name");
return Transp_Name; 
}
//////////////////////////////////////////////////////////////////////////////////////
private StringProperty SupervisorName;
public void setSupervisorName(String value)
{ SupervisorNameProperty().set(value); }
public String getSupervisorName() 
{ return SupervisorNameProperty().get(); }
public StringProperty SupervisorNameProperty() 
{ 
if (SupervisorName == null) SupervisorName = new SimpleStringProperty(this, "SupervisorName");
return SupervisorName; 
}
}