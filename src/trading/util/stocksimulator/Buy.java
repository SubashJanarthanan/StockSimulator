package trading.util.stocksimulator;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * The Class Buy.
 * 
 * @author Subash Janarthanan
 */
public class Buy extends TradeLedger {

	/** The df. */
	DecimalFormat df = new DecimalFormat("#.00");

	/**
	 * Instantiates a new buy.
	 *
	 * @param account the ac
	 * @param orderId
	 */
	public Buy(Account account, int orderId) {
		super(account);

		try {
			Scanner user_input = new Scanner(System.in);

			System.out.print("Enter the Stock symbol you would like to trade: ");
			symbol = user_input.nextLine();

			int stockIndex = account.find(symbol, StockUtilities.stocks, "");

			if (stockIndex == -1) {
				System.out.println("The respective stock is not available for trading.");

			} else {
				OrderBook stockTrade = StockUtilities.stocks[stockIndex];

				System.out.print("Enter the number of shares you would like to Buy: ");
				quantityNew = user_input.nextInt();

				// validation on the quantity of the stock
				if (availableShares(stockTrade, quantityNew)) {
					double totalPrice = stockTrade.getPrice() * quantityNew;

					// make sure user has enough cash
					if (hasEnoughCash(totalPrice)) {

						int index = -1;
						if (Account.heldStocks.size() > 0) {
							for (int i = 0; i < Account.heldStocks.size(); i++) {
								OrderBook stock = Account.heldStocks.get(i);
								if (stock.getSymbol().equals(stockTrade.getSymbol())
										&& (stock.getType().equals(stockTrade.getType()))) {
									int quantity = stock.getQuantity() + quantityNew;
									stock.setQuantity(quantity);
									index = i;
								}
								else {
									stockTrade.setQuantity(quantityNew);
								}
							}
						} else {
							stockTrade.setQuantity(quantityNew);
						}

						// update the amount of shares left in the market for that stock
						stockTrade.updateSharesLeft(quantityNew);
						// set stock type
						stockTrade.setType("buy");
						account.addStock(stockTrade, orderId, index, quantityNew);

						// update user cash
						account.updateCash(totalPrice, "buy");
					} else {
						System.out.println("You do not have enough cash to make a trade.");
					}
				} else {
					System.out.println("Shares not avialable for the provided quantity.");
				}
			}

		} catch (Exception ex) {
			System.out.println("Error occured while making a trade !!!!");
		}

	}

}
