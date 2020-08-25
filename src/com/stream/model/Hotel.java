package com.stream;

public class Hotel {

    String hotelId;
    String hotelName;
    String city;

    public Hotel(String hotelId, String hotelName, String city) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.city = city;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId='" + hotelId + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
