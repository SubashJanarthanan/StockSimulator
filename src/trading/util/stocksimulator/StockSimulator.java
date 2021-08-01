package trading.util.stocksimulator;

import java.util.Scanner;

/**
 * The Class StockSimulator.
 * 
 * @author Subash Janarthanan
 */
public class StockSimulator {

	/** The user input. */
	private Scanner user_input = new Scanner(System.in);

	/** The account. */
	Account account;

	/**
	 * Instantiates a new stock simulator.
	 */
	public StockSimulator() {
		try {
			System.out.print("Enter an Account Name: ");
			String accountName = user_input.next();
			System.out.print("Enter Starting Money: \u00A3");
			int startingMoney = user_input.nextInt();
			account = new Account(accountName, startingMoney);
			StockUtilities.accounts.add(account);

			while (true) {
				// Entering TradingGateway for Buy/Sell
				TradingGateway.getInstance().showTradingMenu(account);
			}
		} catch (java.util.InputMismatchException ex) {
			System.out.println("Sorry you did not enter your starting money correctly. Please try again.");
			StockSimulator.main(null);
		}

	}

	/**
	 * Instantiates a new stock simulator.
	 *
	 * @param ac the ac
	 */
	public StockSimulator(Account ac) {
		this.account = ac;
		while (true) {
			TradingGateway.getInstance().showTradingMenu(ac);
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Scanner user_input = new Scanner(System.in);

		System.out.println("---------------------------------------------");
		System.out.println("Entering the Stock Simulator Application");
		System.out.println("---------------------------------------------");
		
		System.out.println("Log in time  :[" + StockUtilities.getCurrentTimeStamp() + "]");
		System.out.println();

		// loads the current stocks
		StockUtilities.readStocks();

		// loads the current accounts
		StockUtilities.readAccounts();

		// checks to see if the user has an account
		System.out.print("Are you an existing customer: <y/n> ");
		String answer = user_input.nextLine();

		if (answer.equalsIgnoreCase("y")) {
			System.out.print("Enter your account name: ");
			String accountName = user_input.nextLine();
			int accountIndex = StockUtilities.searchAccounts(accountName);

			if (accountIndex == -1) {
				System.out.println("Sorry, We coudn't find the account. Please set up an account");
				StockSimulator ss = new StockSimulator();
			} else {
				StockSimulator ss = new StockSimulator(StockUtilities.accounts.get(accountIndex));
			}
		} else {
			StockSimulator ss = new StockSimulator();
		}
	}

}
