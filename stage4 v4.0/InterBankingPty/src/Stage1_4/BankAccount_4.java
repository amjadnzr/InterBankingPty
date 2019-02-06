package Stage1_4;

/*
 * Project Done by completely by Nazar Ahmed Amjad
 * Course Work 1
 * Stage 4
 * PP2
 * Completed Date: 11/03/2018
 * Lines:
 * */

import java.io.*;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Scanner;

public class BankAccount_4 implements Serializable {
    // class that holds all Customer Information
    public String customerName;  //Used to save the customer namer
    public String customerPassword;   //stores customer password
    public String customerUsername; // This is used to view Bank Account details.....unique to every customer
    public int accountNumber;     //Stores Account Number
    public double accountBalance;   //Stores Account Balance
    public double annualInterestRate; //used to store the interest
    public int noOfActive;   //used to store no.of year active for instant adding

    private int num = 0;   //Used to limit the maximum bank Account create per customern for stage 2

    //no arg constructor
    //used for stage4
    public BankAccount_4() {

    }

    //arg constructor
    public BankAccount_4(String customerName, String customerPassword, int accountNumber, double accountBalance, String customerUsername, double annualInterestRate) {
        //inserting values for each variables of the object
        this.customerName = customerName;
        this.customerPassword = customerPassword;
        this.customerUsername = customerUsername;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.annualInterestRate = annualInterestRate;
    }

    @Override
    public String toString() {
        return
                "customerName= " + customerName + "\n" +
                        "customerPassword= " + customerPassword + "\n" +
                        "customerUsername= " + customerUsername + "\n" +
                        "accountNumber= " + accountNumber + "\n" +
                        "accountBalance= " + "$" + accountBalance + "\n" +
                        "annualInterestRate= " + annualInterestRate + "%";
        //not always used because this prints password
    }


}

// class that holds the main method and does all the sort if validation

class Authorize {

    static Scanner sc = new Scanner(System.in);  //static scanner used for all input
    static String pass = "Asd";   //uers password
    // The Password for the user to login
    static ArrayList<BankAccount_4> ListAccount = new ArrayList<BankAccount_4>();
    // Array list used to store the customer Object inputted
    static ArrayList<BankAccount_4> Listsp = new ArrayList<BankAccount_4>();
    //ArrayList used for file writting

    public static void main(String[] args) {
        try {
            dataPersistency(1, null);
            //When the program starts it reads the file first
        } catch (IOException e) {
            //e.printStackTrace();
        }
        userPassValid();
        //-------used for deciding  instant addition account and main menu
        boolean yes = false;
        do {
            //
            System.out.println("-----Enter to make Action----");
            System.out.println("1.View Main Menu");
            System.out.println("2.Instant adding Customer");
            int w = sc.nextInt();

            if (w == 1) {
                mainMenu();
                yes = true;
            } else if (w == 2) {
                yes = true;
                //....
                //this array list used to create instant accounts
                ArrayList<BankAccount_4> List = new ArrayList<BankAccount_4>();  //Record the instant add ones
                BankAccount_4 newInst;

                do {
                    newInst = enterAccountData();

                    if (newInst.accountNumber != 0) {
                        System.out.println("No of active years the account will exist");
                        newInst.noOfActive = sc.nextInt();
                        List.add(newInst);

                    }

                } while (newInst.accountNumber != 0);

                for (BankAccount_4 z : List) {
                    displayAccount(z);
                    System.out.println("\n---------Future Balances" + "--------\n");
                    computeIntrest(z, z.noOfActive);
                }


            } else {
                System.out.println("Invalid input");

            }
        } while (yes == false);
    }

    public static void userPassValid() {

        boolean isPrime = true; // Used later for the final validation of user..only if true can log in
        System.out.println("--------Authorized Staff Login-----------\n");
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

        } else {
            System.err.print("Login Access denied: Password or Username is incorrect. \n Please try again \n");
            //reason for this to be print statement is that when login is denied the gap between line increase to 3
            userPassValid();
        }
        // Condition which decides whether the user can login or not below
        if (isPrime == true && inUsername.equals("Amjad")) {

            System.out.println("Login successful \n");  //itll go back to main method......
        } else {
            System.err.print("Login Access denied: Password or Username is incorrect. \n Please try again \n");
            //reason for this to be print statement is that when login is denied the gap between line increase to 3
            userPassValid();
        }

    }

    public static void mainMenu() {
        // Method Used to Create Customer
        System.out.println("------------Select Action Number-------------");
        System.out.println("1.Create a new customer");
        System.out.println("2.View Bank Account details");
        System.out.println("3.Create Bank Account for existing Customer ");
        System.out.println("4.View Customer Accounts");
        System.out.println("5.Check interest for existing years");
        System.out.println("6.Transfer Between two bank accounts of same customer");
        System.out.println("7.Forecasting bank account for the term");
        System.out.println("0.Close");
        int a = sc.nextInt(); // used to decide the function to be done
        //Main menu switch case
        switch (a) {
            case 1:
                System.out.println(enterAccountData());
                mainMenu();
                break;

            case 2:
                viewBankDetails();
                break;

            case 3:
                System.out.println(CheckExistCustomer());
                mainMenu();
                break;

            case 4:
                ViewCustomerAccounts();
                break;
            case 5:
                checkComputeInterset();
                break;
            case 6:
                Transaction();
                break;
            case 7:
                AutoDepositWithdraw();
                break;

            case 0:
                System.out.println("Terminating program");
                for (BankAccount_4 b : Listsp) {
                    try {
                        dataPersistency(0, b);
                    } catch (IOException e) {
                        // e.printStackTrace();
                    }
                }
                System.exit(0);
                break;

            default:
                System.err.println("Action Not Available please select action again");
                mainMenu();
                break;
        }

    }

    @SuppressWarnings("Duplicates")        // duplications done bellow to ignore it
    public static BankAccount_4 enterAccountData() {
        BankAccount_4 account = new BankAccount_4();   //stage 4 first point
        System.out.println("------------New Customer----------------");


        System.out.println("Enter Account Number(should contain 4 digits)");

        boolean thing;

        //make sure that no one has the same bank accunt number
        do {
            thing = true;
            account.accountNumber = sc.nextInt();
            for (BankAccount_4 check : ListAccount) {

                if (account.accountNumber == check.accountNumber) {
                    thing = false;
                }
            }

            if (thing == false) {
                System.err.println("Account Number is been used for another account:Re-enter Account Number");
            }

        } while (thing == false);

        //validation for Account Number
        while (account.accountNumber < 1000 || account.accountNumber > 9999 || account.accountNumber == 0) {
            if (account.accountNumber == 0) {
                return account;
            } else {
                System.err.println("Renter Account Number(should be between 1000 and 9999)");
                account.accountNumber = sc.nextInt();
            }
        }

        //Customer name input
        System.out.println("Enter Customer Name");
        account.customerName = sc.next();

        //customer password input
        System.out.println("Enter Customer Password");
        account.customerPassword = sc.next();


        String x = Integer.toString(account.accountNumber);
        account.customerUsername = account.customerName + x;
        //this is the username of the customer; its unique to the customer

        System.out.println("Enter Openning  Account Balance ");
        account.accountBalance = sc.nextDouble();

        //validation for account balance
        while (account.accountBalance < 0 || account.accountBalance > 100000) {
            if (account.accountNumber < 0) {
                System.err.println("Invalid Balance Entry: Please re enter");
                account.accountBalance = sc.nextDouble();
            } else {
                System.err.println("Maximum amount of deposit is 100000: Please re enter");
                account.accountBalance = sc.nextDouble();
            }

        }
        //Annual Interest input
        System.out.println("Enter the Annual Interest Rate");
        account.annualInterestRate = sc.nextDouble();

        //validation for rate
        while (0.01 > account.annualInterestRate || account.annualInterestRate > 15) {
            System.err.println("The interest ranges from 0.01% t0 15%: Re enter ");
            account.annualInterestRate = sc.nextDouble();
        }


        ListAccount.add(account);
        // adding object to the arraylist
        System.out.println("Do you want to save this specified  account(to save input Y/N)");
        char i = sc.next().charAt(0);
        if (i == 'y' || i == 'Y') {
            System.out.println("Account Succesfully Saved");
            Listsp.add(account);
        }

        return account;
    }
    //method used to view all accounts created from the main menu adding
    public static void viewBankDetails() {
        // method to view Account details
        boolean prime = false;
        // validate what to do if the account number or customer number is wrong
        System.out.println("\n ---------Search Customer------------");
        System.out.println("Enter Customer Name");
        String checkName = sc.next();
        System.out.println("Enter Account Number");
        int checkNumber = sc.nextInt();

        for (BankAccount_4 acc : ListAccount) {

            if (acc.accountNumber == checkNumber && acc.customerName.equals(checkName)) {

                displayAccount(acc);
                prime = true; // Prime is made true only if is satisfied
            }

        }

        if (!prime) {
            System.err.println("Customer Name or Account Number is Incorrect..Please re enter");
            viewBankDetails();
        }

        mainMenu();
    }


    //method for stage 4

    public static void displayAccount(BankAccount_4 acc) {
        System.out.println("Customer Name: " + acc.customerName);
        System.out.println("Customer Username: " + acc.customerUsername);
        System.out.println("Account Number: " + acc.accountNumber);
        System.out.println("Account Balance: " + "$" + acc.accountBalance);
        System.out.println("Annual Interest: " + acc.annualInterestRate + "%");
        //the reason for not using the tostring is to avoid print password
    }


// for instant account


    // used to validate and check whether customer exist
    public static BankAccount_4 CheckExistCustomer() {
        boolean seeCustomer = false;   //used to go back to the main menu if the customer doesnt exist
        BankAccount_4 obj = null; //used to pass as an argument to CreateAccount2 meth
        System.out.println("-----------Create New Account---------\n-");

        System.out.println("Enter Customer Username ");
        String eCustomerName = sc.next();  // Ecustomer= Existing Customer

        System.out.println("Enter Customer Password");
        String eCustomerPass = sc.next();

        for (BankAccount_4 customer : ListAccount) {

            if (eCustomerName.equals(customer.customerUsername) && eCustomerPass.equals(customer.customerPassword)) {

                seeCustomer = true;
                obj = customer;
            }


        }

        //check whether customer is available and move on or go back to menu down if statement
        if (seeCustomer) {
            return enterAccountData2(obj);
        } else {
            System.err.println("Process not completed: Customer doesn't exist \nReturning to Main menu......\n");
            return null;
        }


    }

    public static BankAccount_4 enterAccountData2(BankAccount_4 customer) {
        // customer is the customer who we have to create the account for
        System.out.println("Enter New Bank Account Number ");
        int Number;

        boolean th;

        do {
            th = true;
            Number = sc.nextInt();
            for (BankAccount_4 check : ListAccount) {

                if (Number == check.accountNumber) {
                    th = false;
                }
            }

            if (th == false) {
                System.err.println("Account Number is been used for another account:Re-enter Account Number");
            }

        } while (th == false);


        while (Number == customer.accountNumber || Number < 1000 || Number > 9999) {
            if (Number < 1000 || Number > 9999) {
                System.err.println("The Account Number should be in the range of 1000 to 9999");
                Number = sc.nextInt();
            }


        }


        System.out.println("Enter New Account Openning Balance");
        double Balance = sc.nextInt();

        //validation for account balance
        while (Balance < 0 || Balance > 100000) {
            if (Balance < 0) {
                System.err.println("Invalid Balance Entry: Please re enter");
                Balance = sc.nextDouble();
            } else {
                System.err.println("Maximum amount of deposit is 100000: Please re enter");
                Balance = sc.nextDouble();
            }
        }
       //input for rate
        System.out.println("Enter the Annual Interest Rate");
        double rate = sc.nextDouble();

        //validation for rate
        while (0.01 > rate || rate > 15) {
            System.err.println("The interest ranges from 0.01% t0 15%: Re enter ");
            rate = sc.nextDouble();
        }


        BankAccount_4 obj = new BankAccount_4(customer.customerName, customer.customerPassword, Number, Balance, customer.customerUsername, rate);

        ListAccount.add(obj);
        char i = sc.next().charAt(0);
        if (i == 'y' || i == 'Y') {
            Listsp.add(obj);
            System.out.println("Account Succesfully Saved");


        }

        return obj;

    }

    public static void ViewCustomerAccounts() {
        System.out.println("Enter Customer Username");
        String name = sc.next();
        boolean i = false;
        System.out.println("Enter Customer Password");
        String Password = sc.next();
        int noAc = 1;  //this is to number the accounts
        for (BankAccount_4 x : ListAccount) {
            if (name.equals(x.customerUsername) && Password.equals(x.customerPassword)) {
                if (noAc == 1) {
                    System.out.println("-------" + "Customer Name: " + x.customerName + "-------\n");
                }
                System.out.println("-----Account" + noAc + "-----");
                displayAccount(x);
                noAc++;
                i = true;
            }

        }
        if (i == false) {
            System.err.println("Username or Password Invalid");
        }

        mainMenu();
    }

    private static void Transaction() {
        //object used to determine the accounts involved in transaction
        BankAccount_4 obj1 = null;
        BankAccount_4 obj2 = null;
        //boolean used to justify that the account exist
        boolean happen1 = false;
        boolean happen2 = false;

        //input givers account number
        System.out.println("Account number of giver) ");
        int ac1 = sc.nextInt();
        //input takers account number
        System.out.println("Account number of taker");
        int ac2 = sc.nextInt();
        System.out.println("Amount to be Transfer in dollars");
        double Dollar = sc.nextDouble();
        //validation for amount being negative
        while(Dollar<0){
            System.err.println("Invalid Transfer amount : Re input amount");
            Dollar=sc.nextDouble();
        }
      //to make sure that the account numbers aren't equal
        if (ac1 != ac2) {
            for (BankAccount_4 y : ListAccount) {
                if (ac1 == y.accountNumber) {
                    obj1 = y;
                    happen1 = true;
                }

                if (ac2 == y.accountNumber) {
                    obj2 = y;
                    happen2 = true;
                }
            }
         //justification of whether account exist or not
            if (happen1 == true && happen2 == true) {
                AccountTransfer(obj1, obj2, Dollar);
            } else if (happen1 == true) {
                System.err.println("Account Number  not found \n Returning to main menu");
                mainMenu();
            } else {
                System.err.println("Account Number  not found  \nReturning to main menu.....");
                mainMenu();

            }

        } else {
            System.err.println("Error: Account Numbers entered are the same \nTransaction denied\nReturning to Main Menu....");
            mainMenu();
        }
    }

    //finding the object to compute interst
    public static void checkComputeInterset() {
        boolean check = false;     //to say whether theres a account with such account number
        System.out.println("Account Number");
        int number = sc.nextInt();


        for (BankAccount_4 qz : ListAccount) {
            if (number == qz.accountNumber) {
                check = true;
                computeIntrest(qz);
            }

        }

        if (!check) {
            System.err.println("Account not found" + "\n" + "Returning to menu....");
            mainMenu();

        }
    }
    //method used to compute interest
    private static void computeIntrest(BankAccount_4 b) {
        System.out.println("No.of years account expected to get interest");
        int yNo = sc.nextInt();
        double sBalance = b.accountBalance;
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        System.out.println("year" + "  " + "C Balance");
        for (int i = 1; i <= yNo; i++) {
            double cBalance = sBalance + Math.round((b.annualInterestRate / 100 * sBalance) * 100) / 100;
            System.out.println(year + "   " + cBalance);
            sBalance = cBalance;
            year++;

        }
        mainMenu();

    }

    //Used for Instant Add
    private static void computeIntrest(BankAccount_4 b, int a) {

        double sBalance = b.accountBalance;
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        System.out.println("year" + "  " + "C Balance");
        for (int i = 1; i <= a; i++) {
            double cBalance = sBalance + Math.round((b.annualInterestRate / 100 * sBalance) * 100) / 100;
            System.out.println(year + "   " + cBalance);
            sBalance = cBalance;
            year++;

        }

    }

    //Method used for transfering the amount
    public static void AccountTransfer(BankAccount_4 obj1, BankAccount_4 obj2, double Dollar) {
   //checking whether balance available in first account
        if (obj1.accountBalance - Dollar < 0) {
            System.err.println("Error: Amount to be transfered not available in account");
            mainMenu();
         //checking whether the account 1's balance willl go below 10 or not
        } else if (obj1.accountBalance - Dollar <= 10) {
            //Transfer Happening
            obj1.accountBalance -= Dollar;
            obj2.accountBalance += Dollar;
            System.out.println("Transaction Completed");

            //printing the out put of the transaction
            System.out.printf("Account Number: %d \n", obj1.accountNumber);
            System.out.println("Account Balance: " + obj1.accountBalance);

            System.out.printf("Account Number: %d \n", obj2.accountNumber);
            System.out.println("Account Balance: " + obj2.accountBalance);

            System.err.print("Warning:");
            System.out.println(obj1.accountNumber + " Account balance is less than 10");

            if (obj2.accountBalance >= 100000) {
                System.err.print("Warning");
                System.out.println(obj2.accountNumber + "has reached its maximum fund amount");
            }

            mainMenu();
        } else {
            //Transfer happening
            obj1.accountBalance -= Dollar;
            obj2.accountBalance += Dollar;
            System.out.println("Transaction Completed");

           //output of transaction being printed
            System.out.printf("Account Number: %d \n", obj1.accountNumber);
            System.out.println("Account Balance: " + obj1.accountBalance);

            System.out.printf("Account Number: %d \n", obj2.accountNumber);
            System.out.println("Account Balance: " + obj2.accountBalance);


            if (obj2.accountBalance >= 100000) {
                System.err.print("Warning");
                System.out.println(obj2.accountNumber + "has reached its maximum fund amount");
            }
            mainMenu();

        }


    }

    public static void AutoDepositWithdraw() {
        //This account Forecasts the values with the constant addition of auto deposit to it
        boolean AccCheck = false;
        BankAccount_4 obj = null;
        //used to see whether account exist and login validation
        boolean True = false;
        //used to stop the for after finding the object(stop oversearch)


        System.out.println("Customer Username");
        String userIn = sc.next();
        System.out.println("Customer Password");
        String userPass = sc.next();
        System.out.println("Enter Account Number to do auto transaction");
        int userAcc = sc.nextInt();


        //validating username,Password and Account Number
        for (BankAccount_4 acc : ListAccount) {
            if (userIn.equals(acc.customerUsername) && userPass.equals(acc.customerPassword) && userAcc == acc.accountNumber) {
                AccCheck = true;
                obj = acc;
            }


        }

        //confirming validation and moving on
        if (AccCheck) {
            //the happening of this method requirement

            //asking for the  inputs
            System.out.println("\n------Automatic--------");
            System.out.println("Add Automatic Customer Deposit amount");
            double amount1 = sc.nextDouble();
            System.out.println("Add Automatic Customer Withdraw amount");
            double amount2 = sc.nextDouble();


            ForeCast(obj, amount1, amount2);
        } else {
            System.err.println("Invalid Customer Username or Password or Account Number....\n Returning to main menu ");
            mainMenu();
        }

        mainMenu();

    }

    private static void ForeCast(BankAccount_4 obj, double dAmount, double wAmount) {
        double newBalance = 0;
        boolean yearChange = true;
        double oldBalance = obj.accountBalance;
        Calendar now = Calendar.getInstance();  //Takes the computer time
        int month = now.get(Calendar.MONTH) + 1;
        int year = now.get(Calendar.YEAR);
        System.out.printf("|%s     |%s   |%s     |%s          \n", "Year", "Month", "O.Balance", "C.Balance");
        System.out.println("--------------------------------------------------------------------------------------------------------------");

        for (int i = month; i <= month + 12; i++) {
            if (i <= 12) {
                newBalance = oldBalance + Math.round((oldBalance * obj.annualInterestRate / (12 * 100)) * 100) / 100 + dAmount - wAmount;

                System.out.printf("|%s     |%s      |%s          |%s          \n", year, i, oldBalance, newBalance);
            } else {
                if (yearChange) {

                    year++;
                    yearChange = false;  //this will let year go up by only one cause we are doing this for a year
                }
                newBalance = oldBalance + Math.round((oldBalance * obj.annualInterestRate / (12 * 100)) * 100) / 100 + dAmount - wAmount;
                System.out.printf("|%s     |%s      |%s          |%s          \n", year, i - 12, oldBalance, newBalance);
            }


            oldBalance = newBalance;

        }


    }

    private static void dataPersistency(int x, BankAccount_4 bank) throws IOException {
        if (x == 0) {
            //writting
            try {

                FileOutputStream f = new FileOutputStream(new File("E:\\amjad\\WorkShop\\InterBankingPty\\myObject.txt"));
                ObjectOutputStream o = new ObjectOutputStream(f);
                for (BankAccount_4 b : ListAccount) {
                    o.writeObject(b);
                }
                f.close();
                o.close();


            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
        } else if (x == 1) {
            //Reading
            try {
                FileInputStream f = new FileInputStream(new File("E:\\amjad\\WorkShop\\InterBankingPty\\myObject.txt"));
                ObjectInputStream i = new ObjectInputStream(f);

                while (f.available() != 0) {
                    bank = (BankAccount_4) i.readObject();
                    ListAccount.add(bank);
                }
            } catch (FileNotFoundException e) {
                //e.printStackTrace();
            } catch (IOException e) {
                //  e.printStackTrace();
            } catch (ClassNotFoundException e) {
                //  e.printStackTrace();
            }

        }

    }

}
















