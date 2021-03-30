package aa;

public class Online implements CourseType {
	
	private String type = "online";
	private String url = "";
	
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return type + "," + url;
	}
}
