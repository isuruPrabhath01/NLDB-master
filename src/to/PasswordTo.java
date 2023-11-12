package to;

public class PasswordTo {
    public static String password;

    public PasswordTo() {
    }

    public PasswordTo(String password) {
        this.password = password;
    }

    public static String getPassword() {
        return password;
    }

    public  void setPassword(String password) {
        this.password = password;
    }
}
