package aa;

public class Offline implements CourseType {
	
	private String type = "offline";
	private String location = "";
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return type + "," + location;
	}
}
