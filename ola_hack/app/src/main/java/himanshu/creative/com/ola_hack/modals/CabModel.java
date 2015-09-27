package himanshu.creative.com.ola_hack.modals;

import com.google.gson.annotations.SerializedName;

public class CabModel {
    @SerializedName("eta")
    String eta;
    String surcharge;

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }
}
