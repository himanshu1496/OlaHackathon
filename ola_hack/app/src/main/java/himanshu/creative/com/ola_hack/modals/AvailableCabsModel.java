package himanshu.creative.com.ola_hack.modals;

import com.google.gson.annotations.SerializedName;

public class AvailableCabsModel {
    @SerializedName("Mini")
    CabModel Mini;
    @SerializedName("Sedan")
    CabModel Sedan;

    public CabModel getMini() {
        return Mini;
    }

    public void setMini(CabModel mini) {
        Mini = mini;
    }

    public CabModel getSedan() {
        return Sedan;
    }

    public void setSedan(CabModel sedan) {
        Sedan = sedan;
    }
}
