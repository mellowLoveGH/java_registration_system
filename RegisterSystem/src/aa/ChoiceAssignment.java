package aa;

import java.util.HashMap;
import java.util.Map;

public class ChoiceAssignment extends Assignment {
	
	private Map<String, char[]> submission = new HashMap<String,char[]>();
	
	public void submit(String studentID, char[] choice) {
		submission.put(studentID, choice);		
	}

	public Map<String, char[]> getSubmission() {
		return submission;
	}

	public void setSubmission(Map<String, char[]> submission) {
		this.submission = submission;
	}
}
