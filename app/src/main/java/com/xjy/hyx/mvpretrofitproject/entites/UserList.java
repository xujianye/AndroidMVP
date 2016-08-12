package com.xjy.hyx.mvpretrofitproject.entites;

/**
 * Created by xujianey on 2016/6/22 0022.
 */
public class UserList {

    private String userId;
    private String headPic;
    private String nickName;

    @Override
    public String toString() {
        return "UserList{" +
                "userId='" + userId + '\'' +
                ", headPic='" + headPic + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
}
