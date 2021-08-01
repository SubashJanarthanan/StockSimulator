package trading.util.stocksimulator;

import java.io.Serializable;

/**
 * The Class OrderBook.
 * 
 * @author Subash Janarthanan
 */
public class OrderBook implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The description. */
	private final String symbol, description;
	
	/** The total shares. */
	private final int totalShares;
	
	/** The quantity. */
	private int sharesLeft, quantity;
	
	/** The type. */
	private String type;
	
	/** The current price. */
	private double currentPrice;

	/**
	 * Instantiates a new order book.
	 *
	 * @param symbol the symbol
	 * @param description the description
	 * @param currentPrice the current price
	 * @param totalShares the total shares
	 */
	public OrderBook(String symbol, String description, double currentPrice, int totalShares) {
		this.symbol = symbol;
		this.description = description;
		this.currentPrice = currentPrice;
		this.totalShares = totalShares;
		sharesLeft = totalShares;
		type = "stock";
	}


	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return currentPrice;
	}

	/**
	 * Sets the price.
	 *
	 * @param currentPrice the new price
	 */
	public void setPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets the shares left.
	 *
	 * @return the shares left
	 */
	public int getSharesLeft() {
		return sharesLeft;
	}

	/**
	 * Update shares left.
	 *
	 * @param quantity the quantity
	 */
	public void updateSharesLeft(int quantity) {
		sharesLeft -= quantity;
	}

	/**
	 * Update shares left.
	 *
	 * @param quantity the quantity
	 * @param transaction the transaction
	 */
	public void updateSharesLeft(int quantity, String transaction) {
		sharesLeft += quantity;
	}

	/**
	 * Gets the total shares.
	 *
	 * @return the total shares
	 */
	public int getTotalShares() {
		return totalShares;
	}

}
