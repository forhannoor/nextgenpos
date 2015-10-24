package nextgenpos;

import java.util.ArrayList;

public class SaleConduct {
	private Discount strategy;
	private double vat;
    private ArrayList<Product> products;
    private ArrayList<SalesLineItem> salesLineItems;
    
    public SaleConduct(Discount strategy, double vat){
    	this.strategy = strategy;
    	this.vat = vat;
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

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}
}