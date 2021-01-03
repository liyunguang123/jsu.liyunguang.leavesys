package Bean;

import Dao.LeaveTableDao;

//用户实体类
public class User {
    private int uid;
    private String userName;
    private String email;
    private String phone;
    private String userPwd;
    private int sex;

    public User(String userName, String email, String phone, String userPwd, int sex) {
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.userPwd = userPwd;
        this.sex = sex;
    }
    public User(int uid, String userName, String email, String phone, String userPwd, int sex) {
        this.uid = uid;
        this.userName = userName;
        this.email = email;
        this.phone=phone;
        this.userPwd = userPwd;
        this.sex = sex;
    }
    //获取除uid所有属性，用于保存
    public Object[] getParaments() {
        Object[] param= {userName,email,phone,userPwd,sex};
        return param;
    }

    //setter和getter
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone){ this.phone=phone;}

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getUid() {
        return uid;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone(){return phone;}

    public String getUserPwd() {
        return userPwd;
    }

    public int getSex() {
        return sex;
    }
}
