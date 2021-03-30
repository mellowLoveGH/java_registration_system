package aa;

import java.util.HashMap;
import java.util.Map;

public class EssayAssignment extends Assignment {
	
	private Map<String, String> submission = new HashMap<String,String>();
	
	public void submit(String studentID, String essay) {
		submission.put(studentID, essay);
	}

	public Map<String, String> getSubmission() {
		return submission;
	}

	public void setSubmission(Map<String, String> submission) {
		this.submission = submission;
	}
	
}
