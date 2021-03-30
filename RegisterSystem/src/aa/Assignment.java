package aa;

import java.util.HashMap;
import java.util.Map;

public class Assignment {
	
	private String id = "";
	private int points = 0;
	private String description = "";
	
	private Map<String, Integer> grade;
	
	public Assignment() {
		// TODO Auto-generated constructor stub
		grade = new HashMap<String, Integer>();
	}
	
	//
	public void grading(String studentID, int score) {
		grade.put(studentID, score);
	}
	
	
	//
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int pointes) {
		this.points = pointes;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "";
		if(this instanceof EssayAssignment) {
			str = "essay";
		}else {
			str = "choice";
		}
		
		return id + "," + points + "," + str + "," + description;
	}
}
