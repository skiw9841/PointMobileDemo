package com.calyx.pointmobiledemo.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Location implements Serializable {

    @SerializedName("street1") public String street1 = "street";

    @SerializedName("city") public String city;

    @SerializedName("state") public String state;

    @SerializedName("country") public String country;

    @SerializedName("postcode") public String postcode;

}
