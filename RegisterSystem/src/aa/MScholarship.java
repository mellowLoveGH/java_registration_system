package aa;

public class MScholarship implements Scholarship {
	
	private String id = "";
	private String amount = "";
	private String rate = "";
	private String merit = "";
	
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
		return merit;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "merit scholarship, " + merit + ", " + benefit();
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

	public String getMerit() {
		return merit;
	}

	public void setMerit(String merit) {
		this.merit = merit;
	}
	
}
