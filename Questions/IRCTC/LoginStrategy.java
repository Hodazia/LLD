package IRCTC;

// strategy pattern for logging via different ways
public interface LoginStrategy {
    public boolean login();
}

enum LOGIN_STRATEGY {
    NO_LOGIN,
    USERNAME_PASSWORD,
    EMAIL_OTP,
    THIRDPARTY
}

class EmailOtpLogin implements  LoginStrategy {

    public boolean login()
    {
        System.out.println("Login via Email otp" );
        return true;
    }
}

class ThirdPartyLogin implements  LoginStrategy {

    public boolean login()
    {
        System.out.println("Login via third party" );
        return true;
    }
}

class LoginManager {
    LoginStrategy strategy;

    LoginManager()
    {
        this.strategy = null;
    }

    void setLoginStrategy(int strategy)
    {
        if(strategy == )
    }
}
