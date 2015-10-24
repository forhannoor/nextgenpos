package nextgenpos;

public interface Discount {
    double getTotal(Sale s);
    double getDiscount(Sale s);
}