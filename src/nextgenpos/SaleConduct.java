package nextgenpos;

import java.util.ArrayList;

public class SaleConduct {
    public ArrayList<Product> products;
    public ArrayList<SalesLineItem> salesLineItems;
    
    public SaleConduct(){
    	products = new ArrayList<Product>();
    	salesLineItems = new ArrayList<SalesLineItem>();
    }

	public ArrayList<Product> getProducts() {
		return products;
	}

	public ArrayList<SalesLineItem> getSalesLineItems() {
		return salesLineItems;
	}    
}