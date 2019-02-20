package ua.aerobilet.test.suit;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import ua.aerobilet.test.LoginSuccessWithEmailPass;
import ua.aerobilet.test.LoginUnsuccessWithWrongEmailPass;

@RunWith(Suite.class)
@SuiteClasses({
	LoginSuccessWithEmailPass.class, 
	LoginUnsuccessWithWrongEmailPass.class
	})

public class LoginTestAerobiletUa {

}
