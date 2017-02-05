package wizards;

import javafx.scene.control.TextField;

public class StringTextFiled extends TextField
{
 public StringTextFiled()
 {
//	 this.setPromptText("ادخل أحرف فقط");
 }
	@Override
		public void replaceText(int arg0, int arg1, String arg2) 
	{
		 if((arg2.matches("[A-Z]|[a-z]|[\u0600-\u06ff]|[\u0750-\u077f]|[\ufb50-\ufc3f]|[\ufe70-\ufefc]|[ ]"))||arg2.isEmpty())
			super.replaceText(arg0, arg1, arg2);
	}
	@Override
		public void replaceSelection(String arg0) 
	    {		   
			super.replaceSelection(arg0);
		}
	 
 }
