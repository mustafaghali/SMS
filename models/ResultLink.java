package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
public class ResultLink extends DatabaseClass
{
//////////////////////////////////////////////////////////////////////////////////
	private IntegerProperty Student_ID;

	public void setStudent_ID(Integer value) {
		Student_IDProperty().set(value);
	}

	public Integer getStudent_ID() {
		return Student_IDProperty().get();
	}

	public IntegerProperty Student_IDProperty() {
		if (Student_ID == null)
			Student_ID = new SimpleIntegerProperty(this, "Student_ID");
		return Student_ID;
	}
//////////////////////////////////////////////////////////////////////////////////
    private IntegerProperty Result_ID;

	public void setResult_ID(Integer value) {
		Result_IDProperty().set(value);
	}

	public Integer getResult_ID() {
		return Result_IDProperty().get();
	}

	public IntegerProperty Result_IDProperty() {
		if (Result_ID == null)
			Result_ID = new SimpleIntegerProperty(this, "Result_ID");
		return Result_ID;
	}
//////////////////////////////////////////////////////////////////////////////////
	private IntegerProperty Subject_ID;

	public void setSubject_ID(Integer value) {
		Subject_IDProperty().set(value);
	}

	public Integer getSubject_ID() {
		return Subject_IDProperty().get();
	}

	public IntegerProperty Subject_IDProperty() {
		if (Subject_ID == null)
			Subject_ID = new SimpleIntegerProperty(this, "Subject_ID");
		return Subject_ID;
	}
//////////////////////////////////////////////////////////////////////////////////
	private IntegerProperty Exam;

	public void setExam(Integer value) {
		ExamProperty().set(value);
	}

	public Integer getExam() {
		return ExamProperty().get();
	}

	public IntegerProperty ExamProperty() {
		if (Exam == null)
			Exam = new SimpleIntegerProperty(this, "Exam");
		return Exam;
	}
////////////////////////////////////////////////////
	private IntegerProperty ClassWork;

	public void setClassWork(Integer value) {
		ClassWorkProperty().set(value);
	}

	public Integer getClassWork() {
		return ClassWorkProperty().get();
	}

	public IntegerProperty ClassWorkProperty() {
		if (ClassWork == null)
			ClassWork = new SimpleIntegerProperty(this, "ClassWork");
		return ClassWork;
	}
}
