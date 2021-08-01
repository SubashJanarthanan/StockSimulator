package trading.util.stocksimulator;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class TradingGateway.
 * 
 * @author Subash Janarthanan
 */
public class TradingGateway {

	/** The trading gateway. */
	private static TradingGateway tradingGateway;
	
	private static final AtomicInteger count = new AtomicInteger(0); 
	
	private static int ORDER_ID = 0;

	/** The user input. */
	private static Scanner user_input = new Scanner(System.in);

	/**
	 * Instantiates a new trading gateway.
	 */
	private TradingGateway() {

	}

	/**
	 * Gets the single instance of TradingGateway.
	 *
	 * @return single instance of TradingGateway
	 */
	public static TradingGateway getInstance() {
		if (tradingGateway == null) {
			synchronized (TradingGateway.class) {
				if (tradingGateway == null) {
					tradingGateway = new TradingGateway();
				}
			}
		}

		return tradingGateway;
	}

	/**
	 * Show trading menu.
	 *
	 * @param account the account
	 */
	public void showTradingMenu(Account account) {

		// Print the main menu of the trading here to the user
		System.out.println("\nSelect an option: \n" + " 1. Search Available Stocks\n" + " 2. Trade stock\n"
				+ " 3. Exit\n");
		int selection;
		try {
			selection = user_input.nextInt();
		} catch (InputMismatchException ex) {
			selection = 5;
		}
		user_input.nextLine(); 

		switch (selection) {
		case 1:
			StockUtilities.searchStock(account);
			break;
		case 2:
			decideTransaction(account);
			break;
		case 3:
			exit();
			break;
		default:
			System.out.println("Invalid selection. Try Again.");
			break;
		}
	}

	/**
	 * Decide transaction.
	 *
	 * @param account the ac
	 */
	public void decideTransaction(Account account) {
		System.out.println("\nSelect an option: \n" + " 1. Buy\n" + " 2. Sell\n");
		int selection;
		try {
			selection = user_input.nextInt();
		} catch (InputMismatchException ex) {
			selection = 5;
		}
		user_input.nextLine(); // stops input skipping

		switch (selection) {
		case 1:
			ORDER_ID = count.incrementAndGet(); 
			new Buy(account, ORDER_ID);
			break;
		case 2:
			ORDER_ID = count.incrementAndGet();
			new Sell(account, ORDER_ID);
			break;
		default:
			System.out.println("Invalid Selection. Please wait !.. Going back to the main menu..."); {
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				Logger.getLogger(StockSimulator.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
			break;
		}
	}

	/**
	 * Exit.
	 */
	public void exit() {
		System.out.println("Exiting the application ...");
		System.exit(0);
	}

}
