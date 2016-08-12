package com.xjy.hyx.mvpretrofitproject.entites;

import java.util.List;

/**
 * description:
 * author：xujianye
 * date: 2016/8/11 0011 15:31
 * email：jianyexu@hyx.com
 */
public class Article {

    private String subjectType;
    private String subjectId;
    private String label;
    private String title;
    private String activeCount;
    private String isHolding;
    private String peopleCount;
    private String img;
    private String IMGroupId;
    private List<UserList> userList;

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(String activeCount) {
        this.activeCount = activeCount;
    }

    public String getIsHolding() {
        return isHolding;
    }

    public void setIsHolding(String isHolding) {
        this.isHolding = isHolding;
    }

    public String getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(String peopleCount) {
        this.peopleCount = peopleCount;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIMGroupId() {
        return IMGroupId;
    }

    public void setIMGroupId(String IMGroupId) {
        this.IMGroupId = IMGroupId;
    }

    public List<UserList> getUserList() {
        return userList;
    }

    public void setUserList(List<UserList> userList) {
        this.userList = userList;
    }
}
