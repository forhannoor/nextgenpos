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
    
    public SaleConduct(){
    	products = new ArrayList<Product>();
    	salesLineItems = new ArrayList<SalesLineItem>();
    }
    
    public void setProducts(ArrayList<Product> records){
    	for(Product p: records){
    		products.add(p);
    	}
    }

	public ArrayList<Product> getProducts() {
		return products;
	}

	public ArrayList<SalesLineItem> getSalesLineItems() {
		return salesLineItems;
	}
	
	public void clearSalesLineItems(){
		salesLineItems.clear();
	}
	
	public void addSalesLineItem(SalesLineItem sli){
		salesLineItems.add(sli);
	}

	public Discount getStrategy() {
		return strategy;
	}

	public void setStrategy(Discount strategy) {
		this.strategy = strategy;
	}
}