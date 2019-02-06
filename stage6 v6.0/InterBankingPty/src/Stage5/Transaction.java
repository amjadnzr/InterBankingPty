package Stage5;

import java.util.Scanner;

public class Transaction {
    //object composiiton used
     private  BankAccount obj1;
    //used to store first accounts balance
     private  BankAccount obj2;
    //used to store second accounts balance
    private double amount=0;
    static Scanner sc=new Scanner(System.in);
    //used to get inputs from the keyboard

    //getters and setters
    public BankAccount getObj1() {
        return obj1;
    }

    public void setObj1(BankAccount obj1) {
        this.obj1 = obj1;
    }

    public BankAccount getObj2() {
        return obj2;
    }

    public void setObj2(BankAccount obj2) {
        this.obj2 = obj2;
    }

    //method used for transfer
    public void transfer() {

        System.out.println("Amount to be transfer");
        double am=sc.nextDouble();
        //taking input amount
        this.amount=am;
        //if used here to see whether the sender account has the amount to be transfer
        if ((obj1.getAccountBalance() - amount< 0)) {
            System.err.println("Transaction not completed \n transfer amount not available in sender's account ");
                   amount=0; //this  is made zero cause if we redo error is caused due to th
        }else {
                //account1 has the amount to be transfer

                 //decreasing amount from account1
                 double lastBalance1= obj1.getAccountBalance()-amount;
                 obj1.setAccountBalance(lastBalance1);

                 //increasing amount from account2
                 double lastBalance2=obj2.getAccountBalance()+amount;
                 obj2.setAccountBalance(lastBalance2);
                 //transfer done!

                 //Account two above 100000 warning
                 if (obj2.getAccountBalance()>100000 ){
                     System.err.println("Warning!");
                     System.out.println("Transaction completed..... but account "+obj2.getAccountBalance()+" has exceeded it's maximum fund");
                 }

                //Account one below 10 warning
                 if (obj1.getAccountBalance()<10){
                 System.err.println("Warning!");
                 System.out.println("Transaction completed...but account "+obj2.getAccountNumber()+" has reached its minimum fund");

                 }

        }
                 //sender's transfer information
                 System.out.println("Sender's Account");
                 System.out.println("Account Number: "+obj1.getAccountNumber());
                 System.out.println("Account Balance: "+obj1.getAccountBalance());
                 //receiver's transfer information
                 System.out.println("\n Receiver's Account");
                 System.out.println("Account Number: "+obj2.getAccountNumber());
                 System.out.println("Account Balance: "+obj2.getAccountBalance());




    }
           //method used to undo transaction
            public void undoTransfer(){
             //mathematic for transaction
           obj1.setAccountBalance( obj1.getAccountBalance()+amount);
           obj2.setAccountBalance(obj2.getAccountBalance()-amount);
                //sender's information
                System.out.println("Sender's Account");
                System.out.println("Account Number: "+obj1.getAccountNumber());
                System.out.println("Account Balance: "+obj1.getAccountBalance());
                //receiver's information
                System.out.println("\n Receiver's Account");
                System.out.println("Account Number: "+obj2.getAccountNumber());
                System.out.println("Account Balance: "+obj2.getAccountBalance());


            }


}