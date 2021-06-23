package com.example.skydrinkmob.model;

public class User {
    public int UserID;
    public String UserEmail;
    public String UserPassword;
    public String UserGender;
    public String UserPhoneNumber;
    public int ClusterID;

    public User(int userID, String userEmail, String userPassword, String userGender, String userPhoneNumber, int clusterID) {
        UserID = userID;
        UserEmail = userEmail;
        UserPassword = userPassword;
        UserGender = userGender;
        UserPhoneNumber = userPhoneNumber;
        ClusterID = clusterID;
    }

    public User() {
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserGender() {
        return UserGender;
    }

    public void setUserGender(String userGender) {
        UserGender = userGender;
    }

    public String getUserPhoneNumber() {
        return UserPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        UserPhoneNumber = userPhoneNumber;
    }

    public int getClusterID() {
        return ClusterID;
    }

    public void setClusterID(int clusterID) {
        ClusterID = clusterID;
    }
}
