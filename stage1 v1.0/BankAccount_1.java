package BankMananagment;

/*
 * Project Done by Amjad
 * Course Work 1 
 * Stage 1
 * PP2
 * */

import java.util.ArrayList;
import java.util.Scanner;

public class BankAccount_1 {
	// class that holds all Customer Information
	public int AccountNumber;
	public double AccountBalance;
	public String CustomerName;
	public String CustomerPassword;

}

// class that holds the main method and does all the sort if validation

class Authorize {

	static Scanner sc = new Scanner(System.in);
	static String pass = "Asd";
	// The Password for the user to login
	static ArrayList<BankAccount_1> ListAccount = new ArrayList<BankAccount_1>();

	// Array list used to store the customer Object
	public static void main(String[] args) {
		UserPassValid();
	}

	public static void UserPassValid() {

		boolean isPrime = true; // Used later for the final validation of user..only if true can log in
		System.out.println("Enter Staff Username");
		String inUsername = sc.next();
		System.out.println("Enter Pasword");
		String inPassword = sc.next();

		if (inPassword.length() == 3) {
			int n = 0;
			// n is used to check each character in the password String
			for (char x : inPassword.toCharArray()) {

				if (x != pass.charAt(n)) {

					isPrime = false;
					// If even one character is wrong isPrime is made false
				}
				n++; // to go to next character in the Password String
			}

		}

		else {
			System.err.println("Access Denied. Please try again");
			UserPassValid();
		}
		// Condition which decides whether the user can login or not below
		if (isPrime == true && inUsername.equals("Amjad")) {

			System.out.println("Login successful");
			mainMenu();
		} else {
			System.err.println("Login Access denied: Password or Username is incorrect. \n Please try again \n");
			UserPassValid();
		}

	}

	public static void mainMenu() {
		// Method Used to Create Customer
		System.out.println("------------Select Action Number-------------");
		System.out.println("1.Create a new customer");
		System.out.println("2.View Bank Account details");
		System.out.println("3.Logout");
		System.out.println("0.Close");
		int a = sc.nextInt(); // used to decide the function to be done

		switch (a) {
		case 1:
			CreateAccount();
			break;

		case 2:
			viewBankDetails();
			break;

		case 3:
			UserPassValid();
			break;
		case 0:
			break;
		default:
			System.err.println("Action Not Available please select action again");
			mainMenu();
			break;
		}

	}

	public static void CreateAccount() {
		BankAccount_1 account = new BankAccount_1();
		// creating customer object

		System.out.println("------------New Customer----------------");

		System.out.println("Enter Customer Name");
		account.CustomerName = sc.next();

		System.out.println("Enter Customer Password");
		account.CustomerPassword = sc.next();

		System.out.println("Enter Account Number(should contain 4 digits)");
		account.AccountNumber = sc.nextInt();

		System.out.println("Enter Account Balance ");
		account.AccountBalance = sc.nextDouble();

		System.out.println("\n" + "Customer Name:" + account.CustomerName);
		System.out.println("Customer Password:" + account.CustomerPassword);
		System.out.println("Account Number:" + account.AccountNumber);
		System.out.println("Account Balance" + account.AccountBalance);
		ListAccount.add(account); // adding object to the arraylist
		mainMenu();

	}

	public static void viewBankDetails() {
		// method to view Account details
		boolean prime = false;
		// validate what to do if the account number or customer number is wrong
		System.out.println("\n ---------Search Customer------------");
		System.out.println("Enter Customer Name");

		String checkName = sc.next();
		System.out.println("Enter Account Number");
		int checkNumber = sc.nextInt();

		for (BankAccount_1 acc : ListAccount) {

			if (acc.AccountNumber == checkNumber && acc.CustomerName.equals(checkName)) {
				System.out.println("Customer Name:" + acc.CustomerName);
				System.out.println("Account Number:" + acc.AccountNumber);
				System.out.println("Account Balance" + acc.AccountBalance + "\n");
				prime = true; // Prime is made true only if is satisfied
			}

		}

		if (!prime) {
			System.err.println("Customer Name or Account Number is Incorrect..Please re enter");
			viewBankDetails();
		}

		mainMenu();
	}

}
