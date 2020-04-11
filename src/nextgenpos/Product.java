// Class that represents products.

package nextgenpos;

public class Product {
    private int id;
    private String barcode;
    private String name;
    private int vendorId;
    private double quantity;
    private double price;
    
	public Product(int id, String name, int vendorId, double quantity, double price) {
		this.id = id;
		this.barcode = name + "-" + vendorId;
		this.name = name;
		this.vendorId = vendorId;
		this.quantity = quantity;
		this.price = price;
	}
    
	public Product(String name, int vendorId, double quantity, double price) {
		this.barcode = name + "-" + vendorId;
		this.name = name;
		this.vendorId = vendorId;
		this.quantity = quantity;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public String toString(){
		return id + ", " + barcode + ", " + name + ", " + vendorId + ", " + quantity + ", " + price;
	}
}