package trading.util.stocksimulator;

/**
 * The Class TradeLedger.
 * 
 * @author Subash Janarthanan
 */
public class TradeLedger {

	/** The symbol. */
	public String symbol;

	/** The quantity. */
	public int quantityNew;

	/** The account. */
	final Account account;

	/**
	 * Instantiates a new trade ledger.
	 *
	 * @param account the account
	 */
	public TradeLedger(Account account) {
		this.account = account;
	}

	/**
	 * Available shares.
	 *
	 * @param stock    the stock
	 * @param quantity the quantity
	 * @return true, if successful
	 */
	public boolean availableShares(OrderBook stock, int quantity) {
		return (quantity <= stock.getSharesLeft()) && (quantity > 0);
	}

	/**
	 * Checks for enough cash.
	 *
	 * @param total the total
	 * @return true, if successful
	 */
	public boolean hasEnoughCash(double total) {
		return total <= account.getCash();
	}
}
