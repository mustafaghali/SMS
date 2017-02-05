package models;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Worker extends DatabaseClass
{
//////////////////////////////////////////////////////////////////////////////////////////
public Worker(int i , String s)
{
setName(s);
setid(i);
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
private StringProperty WorksAs;
public void setWorksAs(String value)
{ WorksAsProperty().set(value); }
public String getWorksAs() 
{ return WorksAsProperty().get(); }
public StringProperty WorksAsProperty() 
{ 
if (WorksAs == null) WorksAs = new SimpleStringProperty(this, "WorksAs");
return WorksAs; 
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
////////////////////////////////////////////////////////////////////////////////////////////////
private StringProperty Notes;
public void setNotes(String value)
{ NotesProperty().set(value); }
public String getNotes() 
{ return NotesProperty().get(); }
public StringProperty NotesProperty() 
{ 
if (Notes == null) Notes = new SimpleStringProperty(this, "Notes");
return Notes; 
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

}
