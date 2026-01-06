package com.codewithtoyin.demo;

import com.codewithtoyin.demo.generics.GenericList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;



@SpringBootApplication
public class DemoApplication {
     public static void main(String[] args) {
         SpringApplication.run(DemoApplication.class, args);
//         var stringList = new GenericList<String>();
//         var iterator = stringList.iterator();
//         stringList.add("Toyin");
//         stringList.add("Apple");
//
//
//
//         while (iterator.hasNext()) {
//             var current = iterator.hasNext();
//             System.out.println(current);
//         }



//         System.out.println(stringList);

//         var numberList = new GenericList<Integer>();
//         numberList.add(1);

//         System.out.println(numberList);

//         Stream.of(stringList, numberList)..forEach(System.out::println);
//         List<String> list = List.of("a", "b", "c");
//         Consumer<String> print = (String item) -> System.out.println(item);
//         Consumer<String> printUpperCase = item -> System.out.println(item.toUpperCase());
//
//         print.accept(list.get(0));
//
//         Supplier<Double> randomNumber = () -> Math.random();
//         var random = randomNumber.get();
//         System.out.println(random);

//         List<String> items = List.of("one", "two", "this", "that");
//         List<String> items2 = List.of("three", "four", "those");

//         Stream.of(items, items2)
//                 .flatMap(c -> c.stream())
//                 .filter(item -> item.startsWith("th"))
//                 .map(String::toUpperCase)
//                 .forEach(System.out::println);

//         items.stream().map(String::toUpperCase).forEach(System.out::println);

//         list.forEach(print.andThen(printUpperCase));

    }

}
