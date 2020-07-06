package com.example.etour;

public class AvailableDestinationsList {
    private String destinationName;
    private String destinationLocation;
    private String destinationRating;
    private String destinationPrice;

    public AvailableDestinationsList(String destinationName, String destinationLocation,
                                     String destinationRating, String destinationPrice) {
        this.destinationName = destinationName;
        this.destinationLocation = destinationLocation;
        this.destinationRating = destinationRating;
        this.destinationPrice = destinationPrice;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String getDestinationRating() {
        return destinationRating;
    }

    public void setDestinationRating(String destinationRating) {
        this.destinationRating = destinationRating;
    }

    public String getDestinationPrice() {
        return destinationPrice;
    }

    public void setDestinationPrice(String destinationPrice) {
        this.destinationPrice = destinationPrice;
    }
}
