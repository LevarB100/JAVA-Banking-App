package com.projact.model;

import java.util.ArrayList;
import java.util.List;
import com.projact.dao.AccountDao;
import com.projact.service.DuplicateUsernameException;
import com.projact.service.PasswordTooShortException;

public class Account {
  
  AccountDao adl = new AccountDao();
  
  private static final int REQUIRED_PASSWORD_LENGTH = 8;

  private int id;
  private String First_name;
  private String Last_name;
  private String username;
  private String password;
  private double balance;
  
  public Account(String username, String password) throws DuplicateUsernameException, PasswordTooShortException {
    //instead of using this.username=username and skipping our setter logic, lets just use it:
    this.setUsername(username);
    this.setPassword(password);
    
  }
  
  /**
   * Returns true if the username and password input matches the username and password for this account
   * Returns false otherwise.
   * @param username
   * @param password
   * @return
   */
  public boolean authenticate(String username, String password) {
    return this.username.equals(username) && this.password.equals(password);
  }

  public String getUsername() {
    return username;
    
  }
    
  /**
   * Attempts to set the username on this Account.
   * 
   * The "throws DuplicateUsernameException" part of the method signature signifies that we won't
   * handle DuplicateUsernameExceptions here.  Instead, calling classes must handle that Exception.
   * @param username
   * @throws DuplicateUsernameException
   */
  public void setUsername(String username) throws DuplicateUsernameException {
    
    List<String> usnmcheck = new ArrayList<String>();
  usnmcheck = adl.findAllUsername();
      for(int i =0; i < usnmcheck.size(); i++) {
        String usnmchecks = usnmcheck.get(i);
        
    if(username.equals(usnmchecks)) {
      throw new DuplicateUsernameException();
    }
      }

    
    this.username = username;
    

    
  }

  public String getPassword() {
    return password;
  }

  /**
   * Attempts to set the password on this Account.
   * 
   * @param password
   */
  public void setPassword(String password) throws PasswordTooShortException {
    if(password.length() < REQUIRED_PASSWORD_LENGTH) {
      throw new PasswordTooShortException();
    }
    this.password = password;
  }
  


  public String getFirst_name() {
    return First_name;
  }

  public void setFirst_name(String first_name) {
    First_name = first_name;
  }

  public String getLast_name() {
    return Last_name;
  }

  public void setLast_name(String last_name) {
    Last_name = last_name;
  }


  

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }
  

  public int getId() {
      return id;
  }

  public void setId(int id) {
      this.id = id;
  }
  
    public Account(int id, String first_name, String last_name, String username, String password,
      double balance) {
    super();
    this.id = id;
    First_name = first_name;
    Last_name = last_name;
    this.username = username;
    this.password = password;
    this.balance = balance;
    
  }
    

  @Override
    public String toString() {
      return "Account [id=" + id + ", First_name=" + First_name + ", Last_name=" + Last_name
          + ", username=" + username + ", password=" + password + ", balance=" + balance + "]";
    }

  public Account() {
      super();
      // TODO Auto-generated constructor stub
  }


}
