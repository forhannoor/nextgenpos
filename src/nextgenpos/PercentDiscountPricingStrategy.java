package nextgenpos;

public class PercentDiscountPricingStrategy implements SalePricingStrategy {
    private double percentage;
    
    public PercentDiscountPricingStrategy(double p){
        percentage = p;
    }
    
    public double getTotal(SaleConduct s){
        return s.getTotal() * (100 - this.percentage) / 100;
    }
}