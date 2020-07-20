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

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public String getDestinationRating() {
        return destinationRating;
    }

    public String getDestinationPrice() {
        return destinationPrice;
    }

}
