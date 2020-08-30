package com.stream;

import com.stream.model.Hotel;
import com.stream.model.Trader;
import com.stream.model.Transaction;

import java.util.*;

import static java.util.stream.Collectors.*;

public class StreamTest {

    /**
     * Given a list of hotels, the method should return the list of cities where there are 3 or more than 3 hotels with the same name.
     *
     * @param hotels
     * @return list of cities
     */
    public static List<String> getCityNames(List<Hotel> hotels) {

        Map<String, Map<String, Long>> result = hotels
                .stream()
                .collect(groupingBy(Hotel::getCity, groupingBy(Hotel::getHotelName, counting())));

        List<String> cities = new ArrayList<>();

        result.forEach((city, hotelsWithName) -> {
            hotelsWithName.forEach((name, hotelsCount) -> {
                if (hotelsCount >= 3)
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

//        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
//        List<Integer> numbers2 = Arrays.asList(3, 4);

//        List<Integer> n = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> i + j)).collect(toList());
//        System.out.println(n);


        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = List.of(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        /**
         * Find all transactions in the year 2011 and sort them by value (small to high).
         */
        List<Transaction> year2011 = transactions
                .stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(toList());

        year2011.forEach(System.out::println);
        System.out.println();

        /**
         * What are all the unique cities where the traders work?
         */
        List<String> uniqueCities = transactions
                .stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());

        uniqueCities.forEach(System.out::println);
        System.out.println();

        /**
         * Find all traders from Cambridge and sort them by name
         */
        List<Trader> tradersByName = transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .collect(toList());

        tradersByName.forEach(System.out::println);
        System.out.println();

        /**
         * Return a string of all traders’ names sorted alphabetically
         */
        String tradersNameSorted = transactions
                .stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .distinct()
//                .reduce("", (n1, n2) -> n1 + n2);
                .collect(joining());

        System.out.println(tradersNameSorted);
        System.out.println();

        /**
         * Are any traders based in Milan
         */
        Boolean tradersInMilan = transactions
                .stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        System.out.println(tradersInMilan);
        System.out.println();

        /**
         * Find all transactions’ values from the traders living in Cambridge
         */
        List<Integer> transactionValues = transactions
                .stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(toList());

        transactionValues.forEach(System.out::println);
        System.out.println();

        /**
         * What’s the highest value of all the transactions?
         */
        Integer highestValue = transactions
                .stream()
                .map(Transaction::getValue)
                .reduce(Integer.MIN_VALUE, Integer::max);

        System.out.println(highestValue);
        System.out.println();

        /**
         * Find the transaction with the smallest value.
         */
        Optional<Integer> smallestValue = transactions
                .stream()
                .map(Transaction::getValue)
                .min(Integer::compareTo);

        smallestValue.ifPresent(System.out::println);
        System.out.println();
    }

}
