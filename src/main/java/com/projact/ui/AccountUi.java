package com.projact.ui;

import static com.projact.MainDriver.scanner;
import com.projact.MainDriver;
import com.projact.model.Account;
import com.projact.service.AccountService;

import com.projact.service.DuplicateUsernameException;
import com.projact.service.PasswordTooShortException;


public class AccountUi {

  private AccountService as;
  private static Account b = null;

  {
    as = new AccountService();
  }

  public void LogIn() {
    System.out.println("Welcome to Barr Bank");
    System.out.println("Please log in.  Provide username:");
    String username = scanner.nextLine();
    System.out.println("Provide password:");
    String password = scanner.nextLine();
    // At this point we have the username and password the user is trying to log in with.
    Account a = as.getByString(username);
    if (a.getPassword().equals(password)) {
      b = a;
      MainDriver.loggedIn = true;

    } else {
      System.out.println("-------------------------------------------");
      System.out.println("Sorry, wrong password");
      System.out.println("-------------------------------------------");
    }

  }
  
  public void EmployeeLogIn() {
    System.out.println("Welcome to Barr Bank");
    System.out.println("Please log in.  Provide username:");
    String username = scanner.nextLine();
    System.out.println("Provide password:");
    String password = scanner.nextLine();
    // At this point we have the username and password the user is trying to log in with.
    Account a = as.getByString(username);
    if (a.getPassword().equals(password)) {
      b = a;
      MainDriver.loggedIn = true;
      employeeLogIn();
    } else {
      System.out.println("-------------------------------------------");
      System.out.println("Sorry, wrong password");
      System.out.println("-------------------------------------------");
    }

  }

  public void createAccount() {
    System.out.println("Welcome to Barr Bank");
    System.out.println("Lets create an account !");
    System.out.println("What is the first name for the account?");
    String First_name = scanner.nextLine();
    System.out.println("What is the last name for the account");
    String Last_name = scanner.nextLine();
    System.out.println("What username would you like to use ?");
    String username = scanner.nextLine();
    try {
      as.userchecks(username);
    } catch (DuplicateUsernameException e1) {
      System.out.println("-------------------------------------------");
     System.out.println("Sorry, that username is already taken. Please choose another one");
     System.out.println("-------------------------------------------");
     return;
    }   
    
    System.out.println("Please enter a password");
    String password = scanner.nextLine();
    
    System.out.println("What is your initial balance amount ?");
    double balance = Double.parseDouble(scanner.nextLine());
    
    
  try {
    Account a = new Account(username, password);
//    Account a = as.insert(First_name, Last_name, username, password, balance);
  //add newly created account to our registry of Accounts
} catch (PasswordTooShortException e) {
  System.out.println("-------------------------------------------");
  System.out.println("Password too short, please retry with password of 8 more characters");
  System.out.println("-------------------------------------------");
  return;
//  retryCount++;
} catch (DuplicateUsernameException e) {
  //TODO: make suggested username logic instead of retrying.
  System.out.println("-------------------------------------------");
  System.out.println("Username already exists in our system, please retry with another");
  System.out.println("-------------------------------------------");
  return;
}
    Account a = as.insert(First_name, Last_name, username, password, balance);
    b = a;
    MainDriver.loggedIn = true;

//    try {
//      //attempt to create new account:
//      Account account = new Account(username, password);
//      //add newly created account to our registry of Accounts
////      MainDriver.b.add(a);
//      break;
//    } catch (PasswordTooShortException e) {
//      System.out.println("Password too short, please retry with password of 8 more characters");
//      retryCount++;
//    } catch (DuplicateUsernameException e) {
//      //TODO: make suggested username logic instead of retrying.
//      System.out.println("Username already exists in our system, please retry with another");
//      retryCount++;
//    }
//  }
  //for better UX:
//  if(retryCount >=3) {
//    System.out.println("Retries exceeded, exiting account creation.");
//  }
//  
    System.out.println("-------------------------------------------");
    System.out.println("Here is your new account: ");
    System.out.println(a);
    System.out.println("-------------------------------------------");
  }
  
  public void menu() {
    System.out.println("Welcome to Barr Bank");
    System.out.println("Please choose an option");
    // System.out.println("1. Create an account please");
    System.out.println("1. Make a Deposit");
    System.out.println("2. Make a Withdrawal");
    System.out.println("3. View your Balance");
    System.out.println("4. Transfer Money");
    System.out.println(" 0. Exit");
    String chosen = scanner.nextLine();
    switch (chosen) {
      // case 1:
      // createAccount();
      // menu();
      // break;
      case "1":
        makeDeposit();
      
        break;
      case "2":
        makeWithdrawal();
    
        break;
      case "3":
        viewBalance();
        
        break;
      case "4":
        transferMoney();
     
        break;
      case "0":
        MainDriver.loggedIn = false;
        b = null;
      default:
   
    }
  }

  private void transferMoney() {
    System.out.println("How much do you want to transfer?");    
    String input = scanner.nextLine();
    System.out.println("Who would you like to transfer to?"); 
    String Benny = scanner.nextLine();    
  double amount =  Double.parseDouble(input);
  if(amount > b.getBalance()) {
    System.out.println("--------------------------------------------------------");
    System.out.println("Your transfer amount exceeds your balance please enter a different amount.");
    System.out.println("--------------------------------------------------------");
  } else {  
    as.updateWithdrawal(b, amount);
    b.setBalance(b.getBalance() - amount);
    Account target = as.getByString(Benny);
//    as.getByString(Benny); 
//    target = as.getByString(Benny);
    as.updatedeposit(target, amount);
    System.out.println("------------------------------------------- ");
    System.out.println("Transfer complete!");
     System.out.println("Your new balance is " + b.getBalance());
    System.out.println("------------------------------------------- ");
  }
    
  }

  private void viewBalance() {
    System.out.println("------------------------------------------- ");
    System.out.println("Your current balance is " + b.getBalance());
    System.out.println("------------------------------------------- ");
  
}

  private void viewTransactions() {
    // TODO Auto-generated method stub

  }

  public void menu1() {
    System.out.println("Welcome to Barr Bank");
    System.out.println("Please select an option");
    System.out.println("1 to Log into your account");
    System.out.println("2 to Create an account please");
    System.out.println("3 to Log In as an Employee");
    System.out.println("0 to Exit");
    String chosen = scanner.nextLine();
    switch (chosen) {
      case "1":
        LogIn();

        break;
      case "2":
        createAccount();
        
        break;
      case "3":
        EmployeeLogIn();

        break;
      case "0":
System.exit(0);
      default:
//        break;

    }
  }

  private void employeeLogIn() {
    // TODO Auto-generated method stub
    System.out.println("Your are logged into the Employee Portal.");
    System.out.println("What would you like to do? PLease choose an option.");
    System.out.println("1 To view accounts");
    System.out.println("2 To Approve/Delete Accounts");
    System.out.println("0 to Exit");
    String chosen = scanner.nextLine();
    switch (chosen) {
      case "1":
        viewAccount();

        break;
      case "2":
        accountDelete();
        
        break;
      case "0":
System.exit(0);
//      default:
    }
  }

  private void accountDelete() {
    System.out.println("What Account would you like to delete?");
    String delacct = scanner.nextLine();
   as.deleteByString(delacct);
   System.out.println("------------------------------------------- ");
   System.out.println("You have deleted the account that belongs to  " + delacct);
   System.out.println("------------------------------------------- ");
   employeeLogIn();
   
  }

  private void viewAccount() {
    // TODO Auto-generated method stub
    System.out.println("------------------------------------------- ");
    System.out.println("Here are the current accounts in the system ");
    System.out.println(as.getAllAccounts());
    System.out.println("------------------------------------------- ");
    employeeLogIn();
  }

  public void startProgram() {
    while (true) {
      if (!MainDriver.loggedIn) {
        menu1();
      } 
      if(MainDriver.loggedIn ){
        menu();
      }
    }
  }

  public void makeDeposit() {
    System.out.println("How much Do you want to deposit ?");
    String input = scanner.nextLine();
  double amount =  Double.parseDouble(input);
    as.updatedeposit(b, amount);
    b.setBalance(b.getBalance() + amount);
    System.out.println("------------------------------------------- ");
      System.out.println("Your new balance is " + b.getBalance());
    System.out.println("------------------------------------------- ");
  }

  public void makeWithdrawal() {
    System.out.println("How much Do you want to withdraw ?");
    String input = scanner.nextLine();
      
  double amount =  Double.parseDouble(input);
  if(amount > b.getBalance()) {
    System.out.println("--------------------------------------------------------");
    System.out.println("Your withdrawal amount exceeds your balance please enter a different amount.");
    System.out.println("--------------------------------------------------------");
  } else {  
    as.updateWithdrawal(b, amount);
    b.setBalance(b.getBalance() - amount);     

    System.out.println("------------------------------------------- ");
    System.out.println("Your new balance is " + b.getBalance());
    System.out.println("------------------------------------------- ");
  }
  }
  


  // public void menu() {
  // System.out.println("please select an option:");
  // System.out.println("----1 look at all the accounts");
  // System.out.println("----2 make a account");
  // System.out.println("----3 find a account");
  // System.out.println("----4 leave the account");
  // int chosen = scanner.nextInt();
  // switch(chosen) {
  // case 1:
  // viewAccount();
  // menu();
  // break;
  // case 2:
  // createAccount();
  // menu();
  // break;
  // case 3:
  // findAccount();
  // menu();
  // break;
  // case 4:
  // System.exit(0);
  // default:
  // menu();
  // }
  //
  // }

  // public void viewAccount() {
  // System.out.println(as.getAllAccounts());
  // }
  //
  // public void findAccount() {
  // System.out.println("how would you like to find a Account?");
  // System.out.println("----1 by id");
  // System.out.println("----2 by name");
  // int chosen = scanner.nextInt();
  // switch (chosen) {
  // case 1:
  // System.out.println(findById());
  // menu();
  // break;
  // case 2:
  // System.out.println(findByName());
  // menu();
  // break;
  // default:
  // System.out.println("please choose an accurate option");
  // findAccount();
  // break;
  // }
  // }
  //
  // public Account findById() {
  // System.out.println("what is the id of the Account?");
  // int chosen = scanner.nextInt();
  // Account a = as.getbyId(chosen);
  // return a;
  // }
  //
  // public Account findByName() {
  // System.out.println("what is the name of the account?");
  // String chosen = scanner.next();
  // Account a = as.getByName(chosen);
  // return a;
  // }

}
