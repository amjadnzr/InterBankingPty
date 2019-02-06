package BankMananagment;

/*
 * Project Done by Amjad
 * Course Work 1 
 * Stage 2
 * PP2
 * */

import java.util.ArrayList;

import java.util.Scanner;

public class BankAccount_1_2 {
    // class that holds all Customer Information
    public String CustomerName;

    public String CustomerPassword;
    public String CustomerUsername; // This is used to view Bank Account details.....unique to every customer
    public int AccountNumber;
    public double AccountBalance;
    private int num = 0;   //Used to limit the maximum bank Account create per customern for stage 2

    //constructor
    public BankAccount_1_2(String CustomerName, String CustomerPassword, int AccountNumber, double AccountBalance,String CustomerUsername) {
        this.CustomerName = CustomerName;
        this.CustomerPassword = CustomerPassword;
        this.AccountNumber = AccountNumber;
        this.AccountBalance = AccountBalance;
        this.CustomerUsername=CustomerUsername;

    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

// class that holds the main method and does all the sort if validation

class Authorize {

    static Scanner sc = new Scanner(System.in);
    static String pass = "Asd";
    // The Password for the user to login
    static ArrayList<BankAccount_1_2> ListAccount = new ArrayList<BankAccount_1_2>();

    // Array list used to store the customer Object


    public static void main(String[] args) {
        UserPassValid();
    }

    public static void UserPassValid() {

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
            UserPassValid();
        }
        // Condition which decides whether the user can login or not below
        if (isPrime == true && inUsername.equals("Amjad")) {

            System.out.println("Login successful");
            mainMenu();
        } else {
            System.err.print("Login Access denied: Password or Username is incorrect. \n Please try again \n");
            //reason for this to be print statement is that when login is denied the gap between line increase to 3
            UserPassValid();
        }

    }

    public static void mainMenu() {
        // Method Used to Create Customer
        System.out.println("------------Select Action Number-------------");
        System.out.println("1.Create a new customer");
        System.out.println("2.View Bank Account details");
        System.out.println("3.Create Bank Account for existing Customer ");
        System.out.println("4.View Customer Accounts");
        System.out.println("5.Transfer Between two bank accounts of same customer");
        System.out.println("6.Logout");
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
                CheckExistAccount2();
                break;

            case 4:
                ViewCustomerAccounts();
                break;

            case 5:
                Transaction();
                break;
            case 6:
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

        System.out.println("------------New Customer----------------");

        System.out.println("Enter Customer Name");
        String CustomerName = sc.next();


        System.out.println("Enter Customer Password");
        String CustomerPassword = sc.next();

        System.out.println("Enter Account Number(should contain 4 digits)");
        int AccountNumber;
        boolean thing;

       do {
           thing=true;
           AccountNumber = sc.nextInt();
          for (BankAccount_1_2 check:ListAccount){

              if (AccountNumber==check.AccountNumber){
                  thing=false;
              }
          }

          if (thing==false){
              System.err.println("Account Number is been used for another account:Re-enter Account Number");
          }

       }while (thing==false);







        //validation for Account Number
        while (AccountNumber < 1000 || AccountNumber > 9999) {
            System.err.println("Renter Account Number(should be between 1000 and 9999)");
            AccountNumber = sc.nextInt();
        }



            String x=Integer.toString(AccountNumber);
            String Username= CustomerName+x;
            //this is the username of the customer; its unique to the customer

        System.out.println("Enter Openning  Account Balance ");
        double AccountBalance = sc.nextDouble();

        //validation for account balance
        while (AccountBalance < 0) {
            System.err.println("Invalid Balance Entry: Please re enter");
            AccountBalance = sc.nextDouble();
        }


        BankAccount_1_2 account = new BankAccount_1_2(CustomerName, CustomerPassword, AccountNumber, AccountBalance,Username);

        System.out.println("\n" +"Customer Username: "+Username);
        System.out.println( "Customer Name: " + CustomerName);
        System.out.println("Customer Password: " + CustomerPassword);
        System.out.println("Account Number: " + AccountNumber);
        System.out.println("Account Balance: " + AccountBalance);
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

        for (BankAccount_1_2 acc : ListAccount) {

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

    // used to validate and check whether customer exist
    public static void CheckExistAccount2() {
        boolean seeCustomer = false;   //used to go back to the main menu if the customer doesnt exist
        BankAccount_1_2 obj = null; //used to pass as an argument to CreateAccount2 meth
        System.out.println("-----------Create New Account---------\n-");

        System.out.println("Enter Customer Username ");
        String EcustomerName = sc.next();  // Ecustomer= Existing Customer

        System.out.println("Enter Customer Password");
        String EcustomerPass = sc.next();

        for (BankAccount_1_2 customer : ListAccount) {

            if (EcustomerName.equals(customer.CustomerUsername) && EcustomerPass.equals(customer.CustomerPassword)) {
                if (customer.getNum() == 0) {
                    // the inside if is to validate whether we have created a 2nd ac already coz stage2
                    seeCustomer = true;
                    obj = customer;
                } else {
                    System.err.println("A Customer can only have 2 bank accounts for this Stage2 \nReturning to Main Menu...");
                    mainMenu();
                }
            }

        }

        //check whether customer is available and move on or go back to menu down if statement
        if (seeCustomer) {
            CreateAccount2(obj);
        } else {
            System.err.println("Process not completed: Customer doesn't exist \nReturning to Main menu......\n");
            mainMenu();
        }


    }

    public static void CreateAccount2(BankAccount_1_2 customer) {
        // customer is the customer who we have to create the account for
        System.out.println("Enter New Bank Account Number ");
        int Number;

        boolean th;

        do {
            th=true;
            Number = sc.nextInt();
            for (BankAccount_1_2 check:ListAccount){

                if (Number==check.AccountNumber){
                    th=false;
                }
            }

            if (th==false){
                System.err.println("Account Number is been used for another account:Re-enter Account Number");
            }

        }while (th==false);


        while (Number == customer.AccountNumber || Number < 1000 || Number > 9999) {
            if (Number < 1000 || Number > 9999) {
                System.err.println("The Account Number should be in the range of 1000 to 9999");
                 Number= sc.nextInt();
            }


            }


        System.out.println("Enter New Account Openning Balance");
        double Balance = sc.nextInt();

        //validation for account balance
        while (Balance < 0) {
            System.err.println("Invalid Balance Entry: Please re enter");
            Balance = sc.nextDouble();
        }

        customer.setNum(1); //next time when user tries to create it will not let him cause this is one

        BankAccount_1_2 obj = new BankAccount_1_2(customer.CustomerName, customer.CustomerPassword, Number, Balance,customer.CustomerUsername);
        obj.setNum(2);  //this object is also set 1 because the for loop will look for the first one it gets
        ListAccount.add(obj);

        System.out.println("Account Created Successfully \n");
        System.out.println("Customer Name: " + obj.CustomerName);
        System.out.println("Account Number: " + obj.AccountNumber);
        System.out.println("Account Balance: " + obj.AccountBalance + "\n");


        mainMenu();
    }

    public static void ViewCustomerAccounts() {
        System.out.println("Enter Customer Username");
        String name = sc.next();
        boolean i=false;
        System.out.println("Enter Customer Password");
        String Password = sc.next();

        for (BankAccount_1_2 x : ListAccount) {
            if (name.equals(x.CustomerUsername) && Password.equals(x.CustomerPassword)) {
                System.out.println("-------" + x.CustomerName + "-------\n");
                System.out.println("-----Account-----");
                System.out.println("Account Number: " + x.AccountNumber);
                System.out.println("Account Balance: " + x.AccountBalance + "\n");
                i=true;
            }

        }
     if(i==false){
         System.err.println("Username or Password Invalid");
     }

        mainMenu();
    }

    private static void Transaction() {
        BankAccount_1_2 obj1 = null;
        BankAccount_1_2 obj2 = null;
        boolean happen1 = false;
        boolean happen2 = false;


        System.out.println("Account number of giver) ");
        int ac1 = sc.nextInt();

        System.out.println("Account number of taker");
        int ac2 = sc.nextInt();
        System.out.println("Amount to be Transfer in dollars");
        double Dollar = sc.nextDouble();
//to make sure that the account numbers aren't equal
        if (ac1 != ac2) {
            for (BankAccount_1_2 y : ListAccount) {
                if (ac1 == y.AccountNumber) {
                    obj1 = y;
                    happen1 = true;
                }

                if (ac2 == y.AccountNumber) {
                    obj2 = y;
                    happen2 = true;
                }
            }

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
            System.exit(0);     //********************************************
        }
    }


    public static void AccountTransfer(BankAccount_1_2 obj1, BankAccount_1_2 obj2, double Dollar) {

        if (obj1.AccountBalance - Dollar < 0) {
            System.err.println("Error: Amount to be transfered not available in account");
            System.exit(0); //**********************

        } else if (obj1.AccountBalance - Dollar <= 10) {

            obj1.AccountBalance -= Dollar;
            obj2.AccountBalance += Dollar;
            System.out.println("Transaction Completed");


            System.out.printf("Account Number: %d \n", obj1.AccountNumber);
            System.out.println("Account Balance: " + obj1.AccountBalance);

            System.out.printf("Account Number: %d \n", obj2.AccountNumber);
            System.out.println("Account Balance: " + obj2.AccountBalance);

            System.err.print("Warning:");
            System.out.println(obj1.AccountNumber + " Account balance is less than 10");

            if (obj2.AccountBalance >= 100000) {
                System.err.print("Warning");
                System.out.println(obj2.AccountNumber + "has reached its maximum fund amount");
            }

            System.exit(0); //**********************8
        } else {
            obj1.AccountBalance -= Dollar;
            obj2.AccountBalance += Dollar;
            System.out.println("Transaction Completed");


            System.out.printf("Account Number: %d \n", obj1.AccountNumber);
            System.out.println("Account Balance: " + obj1.AccountBalance);

            System.out.printf("Account Number: %d \n", obj2.AccountNumber);
            System.out.println("Account Balance: " + obj2.AccountBalance);


            if (obj2.AccountBalance >= 100000) {
                System.err.print("Warning");
                System.out.println(obj2.AccountNumber + "has reached its maximum fund amount");
            }
    System.exit(0); //*************

        }


    }
}

















