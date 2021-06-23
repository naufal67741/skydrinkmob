package com.example.skydrinkmob.model;

public class Transaction {
    public int TransactionID;
    public String TransactionDate;
    public int UserID;
    public int TotalPrice;

    public Transaction(int transactionID, String transactionDate, int userID, int totalPrice) {
        TransactionID = transactionID;
        TransactionDate = transactionDate;
        UserID = userID;
        TotalPrice = totalPrice;
    }

    public Transaction() {
    }

    public int getTransactionID() {
        return TransactionID;
    }

    public void setTransactionID(int transactionID) {
        TransactionID = transactionID;
    }

    public String getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        TransactionDate = transactionDate;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }
}
