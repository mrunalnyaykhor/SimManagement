package com.sim.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class SimManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(SimManagementApplication.class, args);
		List<String> list1 = Arrays.asList("Java", "8");
		List<String> list2 = Arrays.asList("explained", "through", "programs");

		Stream<String> concatStream = Stream.concat(list1.stream(), list2.stream());

		// Concatenated the list1 and list2 by converting them into Stream

		//concatStream.forEach(str -&gt; System.out.print(str + " "));


	}

}
