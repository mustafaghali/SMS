package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Teacher extends DatabaseClass
{
//////////////////////////////////////////////////////////////////////////////////////////
	public Teacher(int i , String s)
	{
		setName(s);
		setid(i);
	}
///////////////////////////////////////////////////////////////////////////////////////////
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

/////////////////////////////////////////////////////////////////////////////////
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
//////////////////////////////////////////////////////////////////////////////////////
private DoubleProperty Salary;
public void setSalary(Double value)
{ SalaryProperty().set(value); }
public Double getSalary() 
{ return SalaryProperty().get(); }
public DoubleProperty SalaryProperty() 
{ 
    if ( Salary == null) Salary = new SimpleDoubleProperty(this,"Salary");
    return Salary; 
}
//////////////////////////////////////////////////////////////////////////////////////
private DoubleProperty alternatives;
public void setalternatives(Double value)
{ alternativesProperty().set(value); }
public Double getalternatives() 
{ return alternativesProperty().get(); }
public DoubleProperty alternativesProperty() 
{ 
if ( alternatives == null) alternatives = new SimpleDoubleProperty(this,"alternatives");
return alternatives; 
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
////////////////////////////////////////////////////////////////////////////////////////////////
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
}
