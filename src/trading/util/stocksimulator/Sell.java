package trading.util.stocksimulator;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * The Class Sell.
 * 
 * @author Subash Janarthanan
 */
public class Sell extends TradeLedger {

	/** The df. */
	DecimalFormat df = new DecimalFormat("#.00");

	/**
	 * Instantiates a new sell.
	 *
	 * @param account the ac
	 * @param order_ID 
	 */
	public Sell(Account account, int order_ID) {
		super(account);

		try {
			// validation on stock symbol
			Scanner user_input = new Scanner(System.in);

			System.out.print("Enter stock symbol you would like to trade: ");
			symbol = user_input.nextLine();

			int stockIndex = account.find(symbol, StockUtilities.stocks, "");

			if (stockIndex == -1) {
				System.out.println("The respective stock is not available for trading.");

			} else {
				OrderBook stockTrade = StockUtilities.stocks[stockIndex];

				System.out.print("Enter the number of shares you would like to Sell: ");
				quantityNew = user_input.nextInt();

				if (availableShares(stockTrade, quantityNew)) {
					double totalPrice = stockTrade.getPrice() * quantityNew;
					stockTrade.setType("sell");
					stockTrade.updateSharesLeft(quantityNew, "sell");

					if (quantityNew == stockTrade.getQuantity()) {
						account.deleteStock(stockIndex, stockTrade, order_ID);
					} else {
						account.reduceStock(stockIndex, quantityNew, order_ID);
					}
					account.updateCash(totalPrice, "sell"); 

				} else {
					System.out.println("Sorry you don't have that many shares.");
				}
			}

		} catch (Exception ex) {
			System.out.println("Error occured while making a trade !!!!");
		}

	}

	/**
	 * Available shares.
	 *
	 * @param orderBook        the s
	 * @param quantity the quantity
	 * @return true, if successful
	 */
	@Override
	public boolean availableShares(OrderBook orderBook, int quantity) {

		if (orderBook.getType().equals("buy")) {
			return (quantity + orderBook.getSharesLeft()) <= orderBook.getTotalShares();
		} else {
			return false;
		}
	}

}
