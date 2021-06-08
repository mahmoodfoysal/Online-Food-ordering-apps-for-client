package com.example.dreamland.foodloverclient;

public class Offeritem {
    String offername,offerprice,offerfoodid,offerestaurantname,offerquantity,offerimage;

    public Offeritem(String offername, String offerprice, String offerfoodid, String offerestaurantname, String offerquantity, String offerimage) {
        this.offername = offername;
        this.offerprice = offerprice;
        this.offerfoodid = offerfoodid;
        this.offerestaurantname = offerestaurantname;
        this.offerquantity = offerquantity;
        this.offerimage = offerimage;
    }
    public Offeritem(){

    }

    public String getOffername() {
        return offername;
    }

    public String getOfferprice() {
        return offerprice;
    }

    public String getOfferfoodid() {
        return offerfoodid;
    }

    public String getOfferrestaurantname() {
        return offerestaurantname;
    }

    public String getOfferquantity() {
        return offerquantity;
    }

    public String getOfferimage() {
        return offerimage;
    }
}
