package com.example.chatapp2.Model;

public class UserModel {

    private String id;
    private String username;
    private String imageURL;
    private String status;
    private String search;

    public UserModel() {
    }

    public UserModel(String id, String username, String imageurl, String status, String search) {
        this.id = id;
        this.username = username;
        this.imageURL = imageurl;
        this.status = status;
        this.search = search;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageurl() {
        return imageURL;
    }

    public void setImageurl(String imageurl) {
        this.imageURL = imageurl;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
