package BankMananagment;

import java.util.ArrayList;
import java.util.Scanner;

public class Authorize {
	 
		 static Scanner sc= new Scanner(System.in);
		 static String pass="Asd";
		 static ArrayList<BankAccount_1 > ListAccount = new ArrayList<BankAccount_1 >(); 
		 static boolean isPrime=true;
		 
		 public static void main(String[] args) {
		           UserPassValid();
			}
		 
		 public static void UserPassValid() {
			 
			 System.out.println("Enter Staff Username");
			 String inUsername = sc.next();
			 System.out.println("Enter Pasword");
			 String inPassword=sc.next();
			
	         if(inPassword.length()==3) {
	            int n=0;
	            
	         for(char x:inPassword.toCharArray()) {
	        	
	        	 if(x !=pass.charAt(n)) {
	        		  isPrime=false;
	        		  }
	        	    n++;     
	        	 }
	           
	         }
	         
	         else {
	        	 System.err.println("Access Denied. Please try again");
	        	  UserPassValid();
	         }
	         
	        	 if(isPrime==true && inUsername.equals("Amjad")) {
	        		 System.out.println("Login successful");
	        		 mainMenu();
	        	 }
	        	 else{
	        		 System.err.println("Login Access denied: Password or Username is incorrect. \n Please try again \n");
	        		 UserPassValid();
	        	 }
	         
	         
	         }
		 
		 
		 public static void mainMenu() {
			 System.out.println("------------Select Action Number-------------");
			 System.out.println("1.Create a new customer");
			 System.out.println("2.View Bank Account details");
			 System.out.println("3.Logout");
			 System.out.println("0.Close");
			int a= sc.nextInt();      //used to decide the function to be done
			 
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
			 BankAccount_1 account=new BankAccount_1();
			 
			 
			 System.out.println("------------New User----------------");
			 
			 System.out.println("Enter Customer Name");
			 account.CustomerName =sc.next();
			 
			 System.out.println("Enter Customer Password");
			 account.CustomerPassword=sc.next();
			 
			 System.out.println("Enter Account Number(should contain 4 digits)");
			 account.AccountNumber=sc.nextInt();
			 
			 System.out.println("Enter Account Balance ");
			 account.AccountBalance= sc.nextDouble();
			 
			 System.out.println("\n"+"Customer Name:"+account.CustomerName);
			 System.out.println("Customer Password:"+account.CustomerPassword);
			 System.out.println("Account Number:"+ account.AccountNumber);
			 System.out.println("Account Balance"+ account.AccountBalance);
			 ListAccount.add(account);
			 mainMenu();
			 
		 }
		 
	public static void viewBankDetails() {
		   boolean prime=false;
		   System.out.println("Enter Customer Name");
		    String checkName=sc.next();
		    System.out.println("Enter Account Number");
		    int checkNumber=sc.nextInt();
		    
		    
		    for(BankAccount_1 acc:ListAccount) {
		    	
		    	if(acc.AccountNumber==checkNumber && acc.CustomerName.equals(checkName) ) {
		    		 System.out.println("Customer Name:"+acc.CustomerName);
					 System.out.println("Customer Password:"+acc.CustomerPassword);
					 System.out.println("Account Number:"+ acc.AccountNumber);
					 System.out.println("Account Balance"+ acc.AccountBalance +"\n" );
					 prime=true;
		    		}
		         
	        }	
		    
		    if(!prime) {
		    	System.err.println("Customer Name or Account Number is Incorrect..Please re enter");
		    	viewBankDetails();
		    }

		    mainMenu();
      }



}
