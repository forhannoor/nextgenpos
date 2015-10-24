package nextgenpos;

public interface Discount {
    double getNetTotal(Sale s);
    double getNetTotal(Sale s, double vat);
    double getDiscount(Sale s);
}