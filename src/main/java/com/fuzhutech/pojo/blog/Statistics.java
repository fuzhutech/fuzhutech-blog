package com.fuzhutech.pojo.blog;

import java.util.Date;

public class Statistics {
    private Integer totalPosts;
    private Integer totalAccess;
    private Integer totalComments;

    public Integer getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(Integer totalPosts) {
        this.totalPosts = totalPosts;
    }

    public Integer getTotalAccess() {
        return totalAccess;
    }

    public void setTotalAccess(Integer totalAccess) {
        this.totalAccess = totalAccess;
    }

    public Integer getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
    }
}