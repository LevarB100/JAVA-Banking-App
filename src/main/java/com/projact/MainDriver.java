package com.projact;

import java.util.Scanner;
import org.apache.log4j.Logger;
import com.projact.ui.AccountUi;




public class MainDriver {
 
    public static final Logger logger = Logger.getLogger(MainDriver.class);
    public static final Scanner scanner = new Scanner(System.in);
    
    public static boolean loggedIn = false;
   
    
    public static void main(String[] args) {
         
        logger.info("infomration message");
        logger.error("error message");
        AccountUi ui = new AccountUi();
        ui.startProgram();
        
        //ui.menu();
    }

}
