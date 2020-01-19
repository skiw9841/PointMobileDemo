package com.calyx.pointmobiledemo.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("gender") public String gender;
    @SerializedName("name") public Name name;
    @SerializedName("location") public Location location;
    @SerializedName("email") public String email;
    @SerializedName("login") public Login login;
    @SerializedName("phone") public String phone;
    @SerializedName("cell") public String cell;
    @SerializedName( "ssn" ) public String ssn = "101-11-23456";
    @SerializedName( "dateOfBirth" ) public String dateOfBirth = "1980/08/29";
    @SerializedName( "maritalStatus" ) public String maritalStatus = "Married";
    @SerializedName( "bestContact" ) public String bestContact = "Cell Phone";
    @SerializedName( "hPhone" ) public String hPhone = "02)444-1234";
    @SerializedName( "bPhone" ) public String bPhone = "070-1234-5678";
    @SerializedName( "fax" ) public String fax = "02)444-1235";
    public String getAddress() { return location.city + " " + location.state + " " + location.country + " " + location.postcode; }
    @SerializedName( "ownRent" ) public String ownRent= "Own";
    @SerializedName( "experian" ) public String experian = "100,000";
    @SerializedName( "transunion" ) public String transunion = "100,000";
    @SerializedName( "equifax" ) public String equifax = "100,000";
    @SerializedName( "base" ) public String base = "100,000";
    @SerializedName( "overtime" ) public String overtime = "100,000";
    @SerializedName( "bonuses" ) public String bonuses = "100,000";
    @SerializedName( "commissions" ) public String commissions = "100,000";
    @SerializedName( "divInt" ) public String divInt = "100,000";
    @SerializedName( "incomeOther1" ) public String incomeOther1 = "100,000";
    @SerializedName( "netRent" ) public String netRent = "100,000";
    @SerializedName( "incomeOther2" ) public String incomeOther2 = "100,000";
    @SerializedName( "incomeTotal" ) public String incomeTotal = "100,000";
    @SerializedName( "rent" ) public String rent = "100,000";
    @SerializedName( "firstMtg" ) public String firstMtg = "100,000";
    @SerializedName( "otherFin" ) public String otherFin = "100,000";
    @SerializedName( "hazardIns" ) public String hazardIns = "100,000";
    @SerializedName( "taxes" ) public String taxes = "100,000";
    @SerializedName( "mtgIns" ) public String mtgIns = "100,000";
    @SerializedName( "hoaDues" ) public String hoaDues = "100,000";
    @SerializedName( "expenseOther" ) public String expenseOther = "100,000";
    @SerializedName( "expenseTotal" ) public String expenseTotal = "100,000";
    @SerializedName( "totalOtherPayments" ) public String totalOtherPayments = "100,000";

    public String getFullName() { return name.title + "." + name.first + " " + name.last; }


}
