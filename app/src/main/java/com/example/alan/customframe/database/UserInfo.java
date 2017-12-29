package com.example.alan.customframe.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Function :
 * Modify Date : 2017/12/29
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

@Entity
public class UserInfo {

    @Id(autoincrement = true)
    private long id = 0;
    private String userName;
    private String userEmail;
    private String userNumber;

    @Generated(hash = 1795568986)
    public UserInfo(long id, String userName, String userEmail, String userNumber) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userNumber = userNumber;
    }
    @Generated(hash = 1279772520)
    public UserInfo() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserEmail() {
        return this.userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserNumber() {
        return this.userNumber;
    }
    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }
}
