package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DatabaseClass
{
	///////////////////////////////////////////////////////////////////////////////////
	private String ImageURL;
	public void setImageURL(String s)
	{
		ImageURL=s;
	}
	public String getImageURL()
	{
		return ImageURL;
	}
//////////////////////////////////////////////////////////////////////////////////

	private IntegerProperty id;
	public void setid(Integer value)
    { idProperty().set(value); }
    public Integer getid() 
    { return idProperty().get(); }
    public IntegerProperty idProperty() 
    { 
        if ( id == null) id = new SimpleIntegerProperty(this,"id");
        return id; 
    }
////////////////////////////////////////////////////////////////////////////////
	private StringProperty Name;
    public void setName(String value)
    { NameProperty().set(value); }
    public String getName() 
    { return NameProperty().get(); }
    public StringProperty NameProperty() 
    { 
        if (Name == null) Name = new SimpleStringProperty(this, "Name");
        return Name; 
    }
////////////////////////////////////////////////////////////////////////////////
}
