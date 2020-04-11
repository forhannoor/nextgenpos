// Represents sales.

package nextgenpos;

public class Sale {

	private int id;
	private String description;
	private double amount;
	private String createdAt;
	
	public Sale(int id, String description, double amount, String createdAt) {
		this.id = id;
		this.description = description;
		this.amount = amount;
		this.createdAt = createdAt;
	}
	
	public Sale(String description, double amount) {
		this.description = description;
		this.amount = amount;
	}
	
	public Sale(double amount){
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}