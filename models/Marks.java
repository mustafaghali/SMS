package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Marks 
{
	public Marks()
	{}
	public Marks(int i, int value)
	{
		switch (i) 
		{
		  case 1:
		    {
		    	setsubject1(value);
		    	break;
		    }
		  case 2:
		    {
		    	setsubject2(value);
		    	break;
		    }
		  case 3:
		    {
		    	setsubject3(value);
		    	break;
		    }
		  case 4:
		    {
		    	setsubject4(value);
		    	break;
		    }
		  case 5:
		    {
		    	setsubject5(value);
		    	break;
		    }
		  case 6:
		    {
		    	setsubject6(value);
		    	break;
		    }
		  case 7:
		    {
		    	setsubject7(value);
		    	break;
		    }
		  case 8:
		    {
		    	setsubject8(value);
		    	break;
		    }
		  case 9:
		    {
		    	setsubject9(value);
		    	break;
		    }
		  case 10:
		    {
		    	setsubject10(value);
		    	break;
		    }
		  case 11:
		    {
		    	setsubject11(value);
		    	break;
		    }
		  case 12:
		    {
		    	setsubject12(value);
		    	break;
		    }
		  case 13:
		    {
		    	setsubject13(value);
		    	break;
		    }
		  case 14:
		    {
		    	setsubject14(value);
		    	break;
		    }
		  case 15:
		    {
		    	setsubject15(value);
		    	break;
		    }
		  case 16:
		    {
		    	setsubject16(value);
		    	break;
		    }
		  case 17:
		    {
		    	setsubject17(value);
		    	break;
		    } 
		    case 18:
		    {
		    	setsubject18(value);
		    	break;
		    }
		    case 19:
		    {
		    	setsubject19(value);
		    	break;
		    }
		    case 20:
		    {
		    	setsubject20(value);
		    	break;
		    }
		default:
			break;
		}
	}

	
	public void set_subject(int i, int value)
	{
		switch (i) 
		{
		  case 1:
		    {
		    	setsubject1(value);
		    	break;
		    }
		  case 2:
		    {
		    	setsubject2(value);
		    	break;
		    }
		  case 3:
		    {
		    	setsubject3(value);
		    	break;
		    }
		  case 4:
		    {
		    	setsubject4(value);
		    	break;
		    }
		  case 5:
		    {
		    	setsubject5(value);
		    	break;
		    }
		  case 6:
		    {
		    	setsubject6(value);
		    	break;
		    }
		  case 7:
		    {
		    	setsubject7(value);
		    	break;
		    }
		  case 8:
		    {
		    	setsubject8(value);
		    	break;
		    }
		  case 9:
		    {
		    	setsubject9(value);
		    	break;
		    }
		  case 10:
		    {
		    	setsubject10(value);
		    	break;
		    }
		  case 11:
		    {
		    	setsubject11(value);
		    	break;
		    }
		  case 12:
		    {
		    	setsubject12(value);
		    	break;
		    }
		  case 13:
		    {
		    	setsubject13(value);
		    	break;
		    }
		  case 14:
		    {
		    	setsubject14(value);
		    	break;
		    }
		  case 15:
		    {
		    	setsubject15(value);
		    	break;
		    }
		  case 16:
		    {
		    	setsubject16(value);
		    	break;
		    }
		  case 17:
		    {
		    	setsubject17(value);
		    	break;
		    } 
		    case 18:
		    {
		    	setsubject18(value);
		    	break;
		    }
		    case 19:
		    {
		    	setsubject19(value);
		    	break;
		    }
		    case 20:
		    {
		    	setsubject20(value);
		    	break;
		    }
		default:
			break;
		}
	}
	private IntegerProperty subject1;
	public void setsubject1(Integer value)
    { subject1Property().set(value); }
    public Integer getsubject1() 
    { return subject1Property().get(); }
    public IntegerProperty subject1Property() 
    { 
        if ( subject1 == null) subject1 = new SimpleIntegerProperty(this,"subject1");
        return subject1; 
    }
    
    private StringProperty studentName;
    public void setstudentName(String value)
    { studentNameProperty().set(value); }
    public String getstudentName() 
    { return studentNameProperty().get(); }
    public StringProperty studentNameProperty() 
    { 
        if (studentName == null) studentName = new SimpleStringProperty(this, "studentName");
        return studentName; 
    }
	private IntegerProperty subject2;
	public void setsubject2(Integer value)
    { subject2Property().set(value); }
    public Integer getsubject2() 
    { return subject2Property().get(); }
    public IntegerProperty subject2Property() 
    { 
        if ( subject2 == null) subject2 = new SimpleIntegerProperty(this,"subject2");
        return subject2; 
    }
    
	private IntegerProperty subject3;
	public void setsubject3(Integer value)
    { subject3Property().set(value); }
    public Integer getsubject3() 
    { return subject3Property().get(); }
    public IntegerProperty subject3Property() 
    { 
        if ( subject3 == null) subject3 = new SimpleIntegerProperty(this,"subject3");
        return subject3; 
    }
    
	private IntegerProperty subject4;
	public void setsubject4(Integer value)
    { subject4Property().set(value); }
    public Integer getsubject4() 
    { return subject4Property().get(); }
    public IntegerProperty subject4Property() 
    { 
        if ( subject4 == null) subject4 = new SimpleIntegerProperty(this,"subject4");
        return subject4; 
    }
    
	private IntegerProperty subject5;
	public void setsubject5(Integer value)
    { subject5Property().set(value); }
    public Integer getsubject5() 
    { return subject5Property().get(); }
    public IntegerProperty subject5Property() 
    { 
        if ( subject5 == null) subject5 = new SimpleIntegerProperty(this,"subject5");
        return subject5; 
    }
    
	private IntegerProperty subject6;
	public void setsubject6(Integer value)
    { subject6Property().set(value); }
    public Integer getsubject6() 
    { return subject6Property().get(); }
    public IntegerProperty subject6Property() 
    { 
        if ( subject6 == null) subject6 = new SimpleIntegerProperty(this,"subject6");
        return subject6; 
    }
    
	private IntegerProperty subject7;
	public void setsubject7(Integer value)
    { subject7Property().set(value); }
    public Integer getsubject7() 
    { return subject7Property().get(); }
    public IntegerProperty subject7Property() 
    { 
        if ( subject7 == null) subject7 = new SimpleIntegerProperty(this,"subject7");
        return subject7; 
    }
    
	private IntegerProperty subject8;
	public void setsubject8(Integer value)
    { subject8Property().set(value); }
    public Integer getsubject8() 
    { return subject8Property().get(); }
    public IntegerProperty subject8Property() 
    { 
        if ( subject8 == null) subject8 = new SimpleIntegerProperty(this,"subject8");
        return subject8; 
    }
    
	private IntegerProperty subject9;
	public void setsubject9(Integer value)
    { subject9Property().set(value); }
    public Integer getsubject9() 
    { return subject9Property().get(); }
    public IntegerProperty subject9Property() 
    { 
        if ( subject9 == null) subject9 = new SimpleIntegerProperty(this,"subject9");
        return subject9; 
    }
    
	private IntegerProperty subject10;
	public void setsubject10(Integer value)
    { subject10Property().set(value); }
    public Integer getsubject10() 
    { return subject10Property().get(); }
    public IntegerProperty subject10Property() 
    { 
        if ( subject10 == null) subject10 = new SimpleIntegerProperty(this,"subject10");
        return subject10; 
    }
    
	private IntegerProperty subject11;
	public void setsubject11(Integer value)
    { subject11Property().set(value); }
    public Integer getsubject11() 
    { return subject11Property().get(); }
    public IntegerProperty subject11Property() 
    { 
        if ( subject11 == null) subject11 = new SimpleIntegerProperty(this,"subject11");
        return subject11; 
    }
    
	private IntegerProperty subject12;
	public void setsubject12(Integer value)
    { subject12Property().set(value); }
    public Integer getsubject12() 
    { return subject12Property().get(); }
    public IntegerProperty subject12Property() 
    { 
        if ( subject12 == null) subject12 = new SimpleIntegerProperty(this,"subject12");
        return subject12; 
    }
    
	private IntegerProperty subject13;
	public void setsubject13(Integer value)
    { subject13Property().set(value); }
    public Integer getsubject13() 
    { return subject13Property().get(); }
    public IntegerProperty subject13Property() 
    { 
        if ( subject13 == null) subject13 = new SimpleIntegerProperty(this,"subject13");
        return subject13; 
    }
    
	private IntegerProperty subject14;
	public void setsubject14(Integer value)
    { subject14Property().set(value); }
    public Integer getsubject14() 
    { return subject14Property().get(); }
    public IntegerProperty subject14Property() 
    { 
        if ( subject14 == null) subject14 = new SimpleIntegerProperty(this,"subject14");
        return subject14; 
    }
    
	private IntegerProperty subject15;
	public void setsubject15(Integer value)
    { subject15Property().set(value); }
    public Integer getsubject15() 
    { return subject15Property().get(); }
    public IntegerProperty subject15Property() 
    { 
        if ( subject15 == null) subject15 = new SimpleIntegerProperty(this,"subject15");
        return subject15; 
    }
    
	private IntegerProperty subject16;
	public void setsubject16(Integer value)
    { subject16Property().set(value); }
    public Integer getsubject16() 
    { return subject16Property().get(); }
    public IntegerProperty subject16Property() 
    { 
        if ( subject16 == null) subject16 = new SimpleIntegerProperty(this,"subject16");
        return subject16; 
    }
    
	private IntegerProperty subject17;
	public void setsubject17(Integer value)
    { subject17Property().set(value); }
    public Integer getsubject17() 
    { return subject17Property().get(); }
    public IntegerProperty subject17Property() 
    { 
        if ( subject17 == null) subject17 = new SimpleIntegerProperty(this,"subject17");
        return subject17; 
    }
    
	private IntegerProperty subject18;
	public void setsubject18(Integer value)
    { subject18Property().set(value); }
    public Integer getsubject18() 
    { return subject18Property().get(); }
    public IntegerProperty subject18Property() 
    { 
        if ( subject18 == null) subject18 = new SimpleIntegerProperty(this,"subject18");
        return subject18; 
    }
    
	private IntegerProperty subject19;
	public void setsubject19(Integer value)
    { subject19Property().set(value); }
    public Integer getsubject19() 
    { return subject19Property().get(); }
    public IntegerProperty subject19Property() 
    { 
        if ( subject19 == null) subject19 = new SimpleIntegerProperty(this,"subject19");
        return subject19; 
    }
    
	private IntegerProperty subject20;
	public void setsubject20(Integer value)
    { subject20Property().set(value); }
    public Integer getsubject20() 
    { return subject20Property().get(); }
    public IntegerProperty subject20Property() 
    { 
        if ( subject20 == null) subject20 = new SimpleIntegerProperty(this,"subject20");
        return subject20; 
    }
	    
	    
	    private IntegerProperty classwork;
		public void setclasswork(Integer value)
	    { classworkProperty().set(value); }
	    public Integer getclasswork() 
	    { return classworkProperty().get(); }
	    public IntegerProperty classworkProperty() 
	    { 
	        if ( classwork == null) classwork = new SimpleIntegerProperty(this,"classwork");
	        return classwork; 
	    }


}
