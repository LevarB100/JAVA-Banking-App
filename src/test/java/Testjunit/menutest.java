package Testjunit;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.projact.dao.AccountDao;
import com.projact.model.Account;

public class menutest {

  private static AccountDao tester = new AccountDao();

  private static AccountDao testing = new AccountDao();
  
  
  @Before
  public void setup()throws Exception {
  
  }
  
  
  
  @After
  public void tearDown()throws Exception {
    
  }

  
  @Test
  public void findAllUsernameTest() {
    List<String> testlist = tester.findAllUsername();
    assertNotNull (tester.findAllUsername());
      
  }
  
  @Test
  
  public void findAllTest() {
    List<Account> testinglist = testing.findAll();
    assertNotNull (testing.findAll());
  }
  
  
  
  
  
  
  
  
}
