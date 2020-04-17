package Testjunit;

import static org.junit.Assert. *;
import org.junit.Test;
import com.projact.dao.AccountDao;
import com.projact.model.Account;
import com.projact.service.AccountService;
import org.junit.Before;
import org.junit.After;

public class TestJunit  {
  private static AccountService at;
  private static AccountDao tdao;
  @Before
  public void setup() {
    at = new AccountService();
    tdao = new AccountDao();
  }
  @Test
  public void withdrawal1000() {
   // assertTrue(true);
   
    String levar = "levar";
    
    Account test =tdao.findByString(levar);
    System.out.println(test.getUsername());
    
   assertTrue( test.getUsername()==("levar"));
  }

}
