package nextgenpos;

import java.util.ArrayList;

public class SaleConduct {
	private Discount strategy;
    private ArrayList<Product> products;
    private ArrayList<SalesLineItem> salesLineItems;
    
    public SaleConduct(Discount strategy){
    	this.strategy = strategy;
    	products = new ArrayList<Product>();
    	salesLineItems = new ArrayList<SalesLineItem>();
    }

	public ArrayList<Product> getProducts() {
		return products;
	}

	public ArrayList<SalesLineItem> getSalesLineItems() {
		return salesLineItems;
	}

	public Discount getStrategy() {
		return strategy;
	}

	public void setStrategy(Discount strategy) {
		this.strategy = strategy;
	}	
}