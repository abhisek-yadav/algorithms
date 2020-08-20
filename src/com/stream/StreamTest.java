package com.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class StreamTest {

    /**
     * Given a list of hotels, the method should return the list of cities where there are 3 or more than 3 hotels with the same name.
     *
     * @param hotels
     * @return list of cities
     */
    public static List<String> getCityNames(List<Hotel> hotels) {

        Map<String, Map<String, List<Hotel>>> result = hotels.stream().
                collect(groupingBy(Hotel::getCity, groupingBy(Hotel::getHotelName)));

        List<String> cities = new ArrayList<>();

        result.forEach((city, hotelsWithName) -> {
            hotelsWithName.forEach((name, hotels1) -> {
                if (hotels1.size() >= 3)
                    cities.add(city);
            });
        });

        return cities;
    }

    public static void main(String[] args) {

        List<Hotel> hotels = List.of(
                new Hotel("hotel_1234", "Hilton", "Amsterdam"),
                new Hotel("hotel_1000", "Sheraton", "Buenos Aires"),
                new Hotel("hotel_1001", "Hilton", "Amsterdam"),
                new Hotel("hotel_1002", "Royal Palace", "Bogota"),
                new Hotel("hotel_1003", "Hilton", "Amsterdam"),
                new Hotel("hotel_1004", "Sheraton", "Buenos Aires"),
                new Hotel("hotel_1235", "Sheraton", "Buenos Aires")
        );

        System.out.println(getCityNames(hotels));

        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        List<Integer> n = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> i + j)).collect(toList());

        System.out.println(n);

    }

}
