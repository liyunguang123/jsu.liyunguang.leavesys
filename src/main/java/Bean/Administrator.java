package Bean;

//管理员实体类
public class Administrator {
    private int aid;
    private String adminName;
    private String adminPwd;
    //构造方法
    public Administrator(int aid, String adminName, String adminPwd) {
        this.aid = aid;
        this.adminName = adminName;
        this.adminPwd = adminPwd;
    }
    //获得所有参数
    public Object[] getParaments(){
        Object[] param={aid,adminName,adminPwd};
        return param;
    }
    //setter和getter

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public int getAid() {
        return aid;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }
}
