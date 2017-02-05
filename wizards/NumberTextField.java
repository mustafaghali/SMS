package wizards;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField
{
	 public NumberTextField() 
	{
//		 this.setPromptText("ادخل أرقام فقط");
	}
	 @Override
		public void replaceText(int arg0, int arg1, String arg2) 
	{
		 if((arg2.matches("[0-9]"))||arg2.isEmpty())
			super.replaceText(arg0, arg1, arg2);
	}
	@Override
		public void replaceSelection(String arg0) 
	    {
		   
			// TODO Auto-generated method stub
			super.replaceSelection(arg0);
		}
		
	
}
