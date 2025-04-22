package models;
import java.io.Serializable;


public class User implements Serializable{
    private String userName;
    private int userId;
    private String password;
    private String role;
    /*
    короче нужно при входе в систему спрашивать Имя пользователя, то бишь Логин и пароль если есть аккаунт
    Если нет аккаунта то создать аккаунт, что бы создать нового пользователя и добавить его в базу данных
    и при каждом входе в систему спрашивать логин и пароль,
    Так же будут админские права у одного пользователя с доступом админ
     */
    private static final long serialVersionUID = 1L;
    public User(int userId, String userName, String password, String role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public User(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + userId +
                ", username='" + userName + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

