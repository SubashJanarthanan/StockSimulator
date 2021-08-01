package trading.util.stocksimulator;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Class StockUtilities.
 * 
 * @author Subash Janarthanan
 */
public class StockUtilities {

	/** The accounts. */
	static ArrayList<Account> accounts = new ArrayList<>();

	/** The stocks. */
	static OrderBook[] stocks;

	/** The df. */
	static DecimalFormat df = new DecimalFormat("#.00");

	/** The user input. */
	private static Scanner user_input = new Scanner(System.in);

	/** The Constant sdf2. */
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

	/**
	 * Gets the current time stamp.
	 *
	 * @return the current time stamp
	 */
	public static String getCurrentTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return sdf2.format(timestamp);
	}

	/**
	 * Read stocks.
	 */
	public static void readStocks() {
		OrderBook[] stocks1 = new OrderBook[6];

		List<String> stockNamesList = new ArrayList<>();
		stockNamesList.add("SBI");
		stockNamesList.add("YES");
		stockNamesList.add("AXIS");
		stockNamesList.add("SILVER");
		stockNamesList.add("GOLD");
		stockNamesList.add("UNION");

		List<Integer> stockPriceList = new ArrayList<>();
		stockPriceList.add(17);
		stockPriceList.add(56);
		stockPriceList.add(12);
		stockPriceList.add(45);
		stockPriceList.add(8);
		stockPriceList.add(10);
		for (int i = 0; i < stockNamesList.size(); i++) {
			OrderBook stock = new OrderBook(stockNamesList.get(i), "Banking stock", stockPriceList.get(i), 10000);
			stocks1[i] = stock;
		}
		stocks = stocks1;
	}

	/**
	 * Read accounts.
	 */
	public static void readAccounts() {
		List<String> accountName = new ArrayList<String>();
		accountName.add("Subash");
		accountName.add("Janarthanan");
		accountName.add("Hari");

		ArrayList<Account> accounts1 = new ArrayList<Account>();

		for (int i = 0; i < 2; i++) {
			Account account = new Account(accountName.get(i), 10000.50);
			accounts1.add(account);
		}
		accounts = accounts1;
	}

	/**
	 * Search accounts.
	 *
	 * @param accountName the account name
	 * @return the int
	 */
	public static int searchAccounts(String accountName) {
		int index = -1;
		for (int i = 0; i < accounts.size(); i++) {
			Account ac = accounts.get(i);
			if (accountName.equals(ac.getName())) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * Search stock.
	 *
	 * @param ac the ac
	 */
	public static void searchStock(Account ac) {
		System.out.println("Enter stock name: ");
		String stockSymbol = user_input.nextLine();

		// search stock name
		int position = ac.find(stockSymbol, stocks, "");
		// display stock and its characteristics
		if (position == -1) {
			System.out.println("The respective stock is not available for trading.");
		} else {
			// display stock symbol, description and current price
			System.out.println(stocks[position].getSymbol() + ", " + stocks[position].getDescription()
					+ " trading at: \u00A3" + df.format(stocks[position].getPrice()));
		}

	}

}
