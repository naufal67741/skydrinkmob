package com.example.skydrinkmob.model;

public class Drink {
    public int DrinkID;
    public String DrinkName;
    public String DrinkDescription;
    public int DrinkPrice;
    public int DrinkSweetness;
    public int DrinkSpicy;
    public int DrinkMalty;
    public int ClusterID;

    public Drink(int drinkID, String drinkName, String drinkDescription, int drinkPrice, int drinkSweetness, int drinkSpicy, int drinkMalty, int clusterID) {
        DrinkID = drinkID;
        DrinkName = drinkName;
        DrinkDescription = drinkDescription;
        DrinkPrice = drinkPrice;
        DrinkSweetness = drinkSweetness;
        DrinkSpicy = drinkSpicy;
        DrinkMalty = drinkMalty;
        ClusterID = clusterID;
    }

    public Drink() {
    }

    public int getDrinkID() {
        return DrinkID;
    }

    public void setDrinkID(int drinkID) {
        DrinkID = drinkID;
    }

    public String getDrinkName() {
        return DrinkName;
    }

    public void setDrinkName(String drinkName) {
        DrinkName = drinkName;
    }

    public String getDrinkDescription() {
        return DrinkDescription;
    }

    public void setDrinkDescription(String drinkDescription) {
        DrinkDescription = drinkDescription;
    }

    public int getDrinkPrice() {
        return DrinkPrice;
    }

    public void setDrinkPrice(int drinkPrice) {
        DrinkPrice = drinkPrice;
    }

    public int getDrinkSweetness() {
        return DrinkSweetness;
    }

    public void setDrinkSweetness(int drinkSweetness) {
        DrinkSweetness = drinkSweetness;
    }

    public int getDrinkSpicy() {
        return DrinkSpicy;
    }

    public void setDrinkSpicy(int drinkSpicy) {
        DrinkSpicy = drinkSpicy;
    }

    public int getDrinkMalty() {
        return DrinkMalty;
    }

    public void setDrinkMalty(int drinkMalty) {
        DrinkMalty = drinkMalty;
    }

    public int getClusterID() {
        return ClusterID;
    }

    public void setClusterID(int clusterID) {
        ClusterID = clusterID;
    }
}
