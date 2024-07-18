package data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseValidate {
    @SerializedName("nat")
    @Expose
    private Boolean nat;

    public boolean getNat() {
        return nat;
    }

    public void setNat(boolean nat) {
        this.nat = nat;
    }
}
