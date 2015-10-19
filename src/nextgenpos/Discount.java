package nextgenpos;

public interface Discount {
    double getNetTotal(Sale s);
    double getDiscount(Sale s);
}