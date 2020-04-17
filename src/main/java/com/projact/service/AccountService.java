package com.projact.service;

import java.util.List;

import com.projact.dao.DaoContract;
import com.projact.dao.AccountDao;
import com.projact.model.Account;

public class AccountService {
    

    private AccountDao adao;
    
    {
        adao=new AccountDao();
    }
    
    public Account insert(String First_name, String Last_name, String username, String password, double balance) {
        return adao.insert(new Account(0, First_name, Last_name, username, password, balance));
}
    public Account updatedeposit(Account a, double amount) {
      double d = amount + a.getBalance();
      return adao.update(a , d);
    }
    
    public Account updateWithdrawal (Account a, double amount) {
      double d = a.getBalance() - amount;
      return adao.update(a , d);
    }
    
    public List<Account> getAllAccounts(){
      return adao.findAll();
    }
    
    
    
    public Account getByString(String s) {
      return adao.findByString(s);
    }
    
    public boolean userchecks(String s) throws DuplicateUsernameException {
      try{
        adao.findByString(s).getUsername();
      }catch(NullPointerException e) {
        return true;
      }
      
      if(s.equals(adao.findByString(s).getUsername())) {
        throw new DuplicateUsernameException(); 
      }
        
      // (s).equals(s);
      
      return true;
    }
    
    public Account deleteByString(String s) {
      return adao.deleteByString(s);
    }
    
}
