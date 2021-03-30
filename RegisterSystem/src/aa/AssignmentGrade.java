package aa;

public class AssignmentGrade {
	
	private String astID = "";
	private String stuID = "";
	private int grade;
	
	
	public String getAstID() {
		return astID;
	}
	public void setAstID(String astID) {
		this.astID = astID;
	}
	public String getStuID() {
		return stuID;
	}
	public void setStuID(String stuID) {
		this.stuID = stuID;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return astID + "," + stuID + "," + grade;
	}
}
