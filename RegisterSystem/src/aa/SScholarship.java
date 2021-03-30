package aa;

public class SScholarship implements Scholarship {
	
	private String id = "";
	private String amount = "";
	private String rate = "";
	private String status = "";
	
	@Override
	public String benefit() {
		// TODO Auto-generated method stub
		if(!amount.equals("")) {
			return amount;
		}
		
		return rate;
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return status;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "status scholarship, " + status + ", " + benefit();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
