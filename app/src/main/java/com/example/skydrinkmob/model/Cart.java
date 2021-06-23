package com.example.skydrinkmob.model;

public class Cart {
    public int CartID;
    public int UserID;
    public int DrinkID;
    public int SubTotal;

    public Cart(int cartID, int userID, int drinkID, int subTotal) {
        CartID = cartID;
        UserID = userID;
        DrinkID = drinkID;
        SubTotal = subTotal;
    }

    public Cart() {
    }

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int cartID) {
        CartID = cartID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getDrinkID() {
        return DrinkID;
    }

    public void setDrinkID(int drinkID) {
        DrinkID = drinkID;
    }

    public int getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(int subTotal) {
        SubTotal = subTotal;
    }
}
