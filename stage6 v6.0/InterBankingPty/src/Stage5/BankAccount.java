package Stage5;

import java.util.Calendar;
import java.util.Scanner;

public class BankAccount {

    private int accountNumber;    //stores account number
    private  double accountBalance;  //stores account balance
    private static final double INTERESTRATE = 0.03; //stores fixed interest rate
    static Transaction trans;    //used for calling Transaction class
    private  static  char[] setPass={'A','s','d'};


    //Setter's and getter's for the program
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    //to string used to print account details
    @Override
    public String  toString() {
        return
                "accountNumber=" + accountNumber +"\n"+
                "accountBalance="+"$" + accountBalance +"\n";
    }

    static Scanner sc=new Scanner(System.in);   //static scanner used for all inputs of the program
    static BankAccount obj[]; //used to sotre reference of bank array to make sure that there are no customers with same account number
    static int j;  //variable used to store how many accounts are created
    /*  j is used for 2 functions in the program
    (1)Denoted: when the for loop is happening used to know how many elements in the array
    (2)Denoted:when the for loop is ended to know the index of the last element
    */
    //main method
    public static void main(String[] args) {
            login();
        System.out.println("\n======Welcome:Please Create Accounts First=======");
        System.out.println("Note:Maximum Account Can be Created is 10");
            BankAccount[] bank = new BankAccount[10];
            //creating bank array to store Bank account objects


            //for loop used to make sure doesnt get out of bound exception
            for (int i = 0; i < bank.length; i++) {
                //entering the method where bank account objects are created and assigning them to arrays
                System.out.println("----Account" + (i + 1) + "----");
                bank[i] = enterAccount();
                j = i + 1;//at this moment stored to see how many elements are in it (1)
                obj = bank;  //referencing obj to bank
                // if this if is satisfied it means that already 10 bank accounts are created
                if (i == 9) {
                    System.out.println("Maximum Bank Account added");
                    j = i; //sotres the index of last element (2)
                    break; //breaks through the for loop
                }
                //user decision
                System.out.println("Do you want to continue adding accounts(y/n)");
                char a = sc.next().charAt(0);
                if (a == 'n' || a == 'N') {
                    j = i; //saves the last entered bank account objects index(2)
                    break; //breaks through the array
                }

            }
            System.out.println("Accounts Successfully added" + "\n");
            //display account called as soon as object adding is completed


            int next = 1; //1 cause i made input 0 as an exit
            while (next != 0) {
                //menu
                System.out.println("---------Select Option--------- ");
                System.out.println("1.Display Details");
                System.out.println("2.Compute Interest");
                System.out.println("3.Transferring");
                System.out.println("0.Exit");
                next = sc.nextInt();
               //switch case for menu
                switch (next) {
                    case 1:
                        displayAccount(bank, j);
                        break;

                    case 2:
                        //After displaying it prints the interest of each month
                        System.out.println("Enter the Number of years the accounts liable for interest ");
                        int yr = sc.nextInt();
                        //the while loops doesn't enter when value entered for yr is not acceptable
                        while (yr <= 1 || yr > 40) {
                            System.out.println("Re-enter Number of Years: it should be in the range of 1 to 40");
                            yr = sc.nextInt();
                        }
                        //goes to the compute interest method to print all
                        computeInterest(bank, j, yr);
                        break;
                    case 3:
                        Transferring(bank);
                        break;

                }
            }

    }


    //login method
    private static void login() {
        boolean d = true;
       //the boolean variable that decides the login

        System.out.println("Enter Username");
        String user = sc.next();
        System.out.println("Enter Password");
        String pass = sc.next();

        //username validation
        if (!(user.equals("Amjad"))) {
            System.err.println("Invalid Username");
            login();    //recursion

        }

      //password validation
        if (pass.toCharArray().length == setPass.length) {
            int i=0;
            for (char paa:pass.toCharArray()){
                if (paa!=setPass[i]){
                    System.err.println("Invalid Password");
                    login();  //recursion
                }
                i++;   //makes the travesing through the array
            }


        }else{
            System.err.println("Password Invalid");
            //invalid password when pass 3 isnt the length
        }
    }
    //the method used to enter account details

    //enter account data method
    public static BankAccount enterAccount(){

        //creation of bank account object
        BankAccount bank=new BankAccount();
        System.out.println("Enter Account Number");
         boolean thing; //used to confirm thst two accounts doesn't have the same number
        //do while loop used to confirm that no two accounts has the same number
        int accountNumber;

       //validation for not having the same account number
        do {
            thing = true;
            accountNumber=sc.nextInt();
            for (int i=0;i<j;i++) {

                if (accountNumber == obj[i].accountNumber) {
                    thing = false;
                }
            }

            if (thing == false) {
                System.err.println("Account Number is been used for another account:Re-enter Account Number");
            }

        } while (thing == false);
          //account number saved to the object
          bank.accountNumber=accountNumber;


        //Validation for account number between 1000 AND 9999
        while(bank.accountNumber<1000 || bank.accountNumber>9999){
            System.err.println("Re-enter Account Number: Account Number should be in the range of 1000 to 9999");
            bank.accountNumber=sc.nextInt();

        }




        System.out.println("Enter Account Balance");
        bank.accountBalance=sc.nextDouble();

        //Validation for account Balance between 0 AND 100000
        while(bank.accountBalance<0 || bank.accountBalance>100000){
            System.err.println("Re-enter Account Balance: Account Balance should be in the range of 0 to 100000");
            bank.accountBalance=sc.nextDouble();
        }

        //returning bank to save in the array
        return bank;

    }
     //method used to compute closing balances
    public static void computeInterest(BankAccount b[], int count, int y) {
        //creating calendar object
        Calendar now=Calendar.getInstance();
        double cb; //used for closing balance
        //for loop used to go through each and every object in the array(entered objects)
        for (int i = 0; i <= count; i++) {
            System.out.println("\n"+"Account Number :"+b[i].accountNumber);
            System.out.println("Year"+"     "+"closing balance");
            double ob=b[i].accountBalance;  //getting the opening balance from the object
            int year=now.get(Calendar.YEAR); //sotr year as int
            int yr=year+y;
           //prints the closing balance in a table format
            while(year<yr) {
                 cb=Math.round((ob+(ob*INTERESTRATE)));  //closing balance calculation
                System.out.println(year+"     "+cb);
                ob=cb;    //next years ob is the cb of this year
                year++;  // makes the chnage to do code for the following year
            }

        }
    }

    //used to print all details of the object
    public static  void displayAccount(BankAccount[] obj, int j){
        //used to travers the array
        for (int i=0;i<=j;i++){
            System.out.println("----Account"+(i+1)+"----");
            //(i+1) cause else its getting combined like i  aString
           System.out.println(obj[i]);
        }
     }

      //Method used for thransferring and involves the Transaction class
     public static void Transferring(BankAccount[] b){
        boolean check1;
        boolean check2;
        //creating a trans object of the Transaction Class
        trans=new Transaction();
         //validation and setting object for sender;s account
       do {
           check1=false;
           System.out.println("Enter Sender's Account Number");
           int ac1 = sc.nextInt();

           for (int i=0;i<=j;i++) {
               if (b[i].getAccountNumber() == ac1) {
                   trans.setObj1(b[i]);
                   check1 = true;
                   break;
               }
           }

           if (!check1) {
               System.err.println("Account doesn't exist");
           }
       }while (!check1);


         //validation and setting object for receiver's account
         do {
             check2=false;
             System.out.println("Enter Receivers's Account Number");
             int ac2 = sc.nextInt();

             for (int i=0;i<=j;i++) {
                 if (b[i].getAccountNumber() == ac2) {
                     trans.setObj2(b[i]);
                     check2 = true;
                     break;
                 }
             }

             if (!check2) {
                 System.err.println("Account doesn't exist");
             }

         }while (!check2);
           //checking whether both input entered are the same or not

         if (trans.getObj1()==trans.getObj2()){
             System.err.println("Can't transfer between the same accounts");
             Transferring(b);  //recursion
         }else {
                      trans.transfer();
                    //moving to transferring method
                }

         System.out.println("\n---Select an option----");
         System.out.println("1.Undo the Transaction");
         System.out.println("2.Redo another Transaction");
         System.out.println("3.Finish the Transaction");
         int z=sc.nextInt();
       //menu appearing after transaction
         switch(z){
             case 1:
                 trans.undoTransfer();
                 break;
             case 2:
                 Transferring(b);
                 break;
             default:
                 //Returns to main menu
                 break;

         }



     }

}

