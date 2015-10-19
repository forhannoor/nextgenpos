package nextgenpos;

public class AbsoluteDiscountOverThresholdPricingStrategy implements SalePricingStrategy{
    private double discount;
    private double threshold;
    
    public AbsoluteDiscountOverThresholdPricingStrategy(double d, double t){
        discount = d;
        threshold = t;
    }
    
    public double getTotal(SaleConduct s){
        return (s.getTotal() > this.threshold) ? s.getTotal() - this.discount : s.getTotal();
    }
}