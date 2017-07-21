package com.rongyant.architecturecomponentmaster.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.rongyant.architecturecomponentmaster.model.User;

/**
 * Created by XRY on 2017/7/19.
 */
@Entity(tableName = "users", indices = @Index(value = {"userName"}, unique = true))
public class UserEntity implements User{
    @PrimaryKey(autoGenerate = true)
    private int userId;

    private String userName;
    private String psw;

    public UserEntity() {
    }

    public UserEntity(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.psw = user.getPsw();
    }



    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getPsw() {
        return psw;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null)
            return false;
        return psw != null ? psw.equals(that.psw) : that.psw == null;

    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (psw != null ? psw.hashCode() : 0);
        return result;
    }
}
