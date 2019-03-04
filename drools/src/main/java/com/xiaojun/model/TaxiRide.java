package com.xiaojun.model;

/**
 * @author long.luo
 * @date 2019/3/4 9:54
 */
public class TaxiRide {

    private Boolean isNightSurcharge;

    private Long distanceInMile;

    public boolean getIsNightSurcharge() {
        return isNightSurcharge;
    }

    public void setIsNightSurcharge(Boolean nightSurcharge) {
        isNightSurcharge = nightSurcharge;
    }

    public Long getDistanceInMile() {
        return distanceInMile;
    }

    public void setDistanceInMile(Long distanceInMile) {
        this.distanceInMile = distanceInMile;
    }
}
