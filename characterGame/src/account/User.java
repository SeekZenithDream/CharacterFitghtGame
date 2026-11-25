package account;

import java.util.Random;

public class User {
    private  String username;
    private String password;
    private String id;

    private boolean status= true;


    public boolean getStatus() {
        return status;
    }

    public String createid(){
        StringBuilder sb=new StringBuilder("pangda");
        Random r=new Random();
        for (int i = 0; i < 6; i++) {
            int num= r.nextInt(10);
            sb.append(num);
        }
        return  sb.toString();
    }
    public String getId() {
        return id;
    }
    public  String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public void setId(String id) {
        this.id = id;
    }
    public  User(String username, String password) {
        this.username = username;
        this.password = password;
        id=createid();
    }
    public User(){
        id=createid();
    }


}
