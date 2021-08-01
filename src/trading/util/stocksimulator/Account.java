package trading.util.stocksimulator;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The Class Account.
 * 
 * @author Subash Janarthanan
 */
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	/** The account name. */
	private final String accountName;

	/** The cash. */
	private double accountValue, cash;

	/** The held stocks. */
	public static ArrayList<OrderBook> heldStocks = new ArrayList<>();

	/** The df. */
	DecimalFormat df = new DecimalFormat("#.00");

	/**
	 * Instantiates a new account.
	 *
	 * @param accountName the account name
	 * @param cash        the cash
	 */
	public Account(String accountName, double cash) {
		this.accountName = accountName;
		this.cash = cash;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return accountName;
	}

	/**
	 * Gets the cash.
	 *
	 * @return the cash
	 */
	public double getCash() {
		return cash;
	}

	/**
	 * Update cash.
	 *
	 * @param cash  the cash
	 * @param trade the trade
	 */
	public void updateCash(double cash, String trade) {
		switch (trade) {
		case "buy":
			this.cash -= cash;
			break;
		case "sell":
			this.cash += cash;
			break;
		default:
			System.out.println("Error, trade required.");
			break;
		}
	}

	/**
	 * Adds the stock.
	 *
	 * @param s       the s
	 * @param orderId
	 * @param quantityNew 
	 */
	public void addStock(OrderBook s, int orderId, int index, int quantityNew) {
		if (index == -1) {
			heldStocks.add(s);
			System.out.println("[" +StockUtilities.getCurrentTimeStamp() + "] Order with ID " + orderId + " added: "
					+ s.getSymbol() + " Buy " + s.getQuantity() + " @" + s.getPrice());
		} else {
			System.out.println("[" +StockUtilities.getCurrentTimeStamp() + "] Order with ID " + orderId + " added: "
					+ s.getSymbol() + " Buy " + quantityNew + " @" + s.getPrice());
		}
	}

	/**
	 * Delete stock.
	 *
	 * @param index the index
	 * @param stockTrade 
	 * @param order_ID 
	 */
	public void deleteStock(int index, OrderBook stockTrade, int order_ID) {
		heldStocks.remove(index);
		System.out.println("[" +StockUtilities.getCurrentTimeStamp() + "] Order with ID " + order_ID + " added: "
				+ stockTrade.getSymbol() + " Sell " + stockTrade.getQuantity() + " @" + stockTrade.getPrice());
	}

	/**
	 * Reduce stock.
	 *
	 * @param index    the index
	 * @param quantity the quantity
	 * @param order_ID 
	 */
	public void reduceStock(int index, int quantity, int order_ID) {
		OrderBook stock = heldStocks.get(index);
		stock.setQuantity((stock.getQuantity() - quantity));
		System.out.println("[" +StockUtilities.getCurrentTimeStamp() + "] Order with ID " + order_ID + " added: "
				+ stock.getSymbol() + " Sell " + stock.getQuantity() + " @" + stock.getPrice());
	}

	/**
	 * Gets the account value.
	 *
	 * @return the account value
	 */
	public double getAccountValue() {
		double totalValue = 0;
		for (OrderBook s : heldStocks) {
			totalValue += (s.getPrice() * s.getQuantity());
		}

		return totalValue + cash;
	}

	/**
	 * Gets the held stocks.
	 *
	 * @return the held stocks
	 */
	public static ArrayList<OrderBook> getHeldStocks() {
		return heldStocks;
	}

	/**
	 * Find.
	 *
	 * @param symbol the symbol
	 * @param stocks the stocks
	 * @param type   the type
	 * @return the int
	 */
	public int find(String symbol, OrderBook[] stocks, String type) {
		for (int i = 0; i < stocks.length; i++) {
			if ((stocks[i].getSymbol().equals(symbol)) && !(stocks[i].getType().equals(type))) {
				return i;
			}
		}

		return -1;

	}
}
