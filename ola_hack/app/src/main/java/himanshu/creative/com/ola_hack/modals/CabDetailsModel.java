package himanshu.creative.com.ola_hack.modals;

import com.google.gson.annotations.SerializedName;

public class CabDetailsModel {
    @SerializedName("cabs")
    AvailableCabsModel cabs;
    @SerializedName("ride")
    RideModel ride;

    public AvailableCabsModel getCabs() {
        return cabs;
    }

    public void setCabs(AvailableCabsModel cabs) {
        this.cabs = cabs;
    }

    public RideModel getRide() {
        return ride;
    }

    public void setRide(RideModel ride) {
        this.ride = ride;
    }
}
