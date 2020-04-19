// Interface for discount.

package nextgenpos;

public interface Discount {
	// Calculates price after discount (if any).
    double getTotal(double price);
}