package greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "Users".
 */
public class Users {

    private Long id;
    private String uSex;
    private String uTelphone;
    private String uAge;
    private String uName;

    public Users() {
    }

    public Users(Long id) {
        this.id = id;
    }

    public Users(Long id, String uSex, String uTelphone, String uAge, String uName) {
        this.id = id;
        this.uSex = uSex;
        this.uTelphone = uTelphone;
        this.uAge = uAge;
        this.uName = uName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUSex() {
        return uSex;
    }

    public void setUSex(String uSex) {
        this.uSex = uSex;
    }

    public String getUTelphone() {
        return uTelphone;
    }

    public void setUTelphone(String uTelphone) {
        this.uTelphone = uTelphone;
    }

    public String getUAge() {
        return uAge;
    }

    public void setUAge(String uAge) {
        this.uAge = uAge;
    }

    public String getUName() {
        return uName;
    }

    public void setUName(String uName) {
        this.uName = uName;
    }

}
