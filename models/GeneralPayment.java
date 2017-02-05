package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GeneralPayment extends DatabaseClass
{
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
    private StringProperty Party;
    public void setParty(String value)
    { PartyProperty().set(value); }
    public String getParty() 
    { return PartyProperty().get(); }
    public StringProperty PartyProperty() 
    { 
        if (Party == null) Party = new SimpleStringProperty(this, "Party");
        return Party; 
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
