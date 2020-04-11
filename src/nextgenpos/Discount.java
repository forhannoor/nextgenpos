// Interface for discount.

package nextgenpos;

public interface Discount {
	// Method to calculate price after discount (if any).
    double getTotal(double price);
    // Method to calculate discount amount (if any).
    double getDiscount(double price);
}