package com.java.functionalprogramming;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import java.util.function.*;
import java.util.regex.Pattern;

public class FunctionalProgrammingApplication {

    public static void main(String[] args) {
        predicateComposition();
        functionComposition();

		BinaryOperator<String> firstAndLastName = (firstName, lastName) -> firstName + " " + lastName;
		System.out.println(firstAndLastName.apply("Ayush", "Jawanjal"));

        UnaryOperator<String> toUpperCase = String::toUpperCase;
        System.out.println(toUpperCase.apply("nitesh"));

        BiFunction<String, String, String> firstNameLastName =
				(firstName, lastName) -> firstName + " " + lastName;
		System.out.println(firstNameLastName.apply("Nitesh", "Jawanjal"));

		BooleanSupplier booleanSupplier = () -> true;

        emailVerification("null");

		//BiConsumer
		//BiPredicate
		//BooleanSupplier
    }

    private static void functionComposition() {
        Function<Integer, Integer> multiply = num -> num * 2;
        Function<Integer, Integer> add = num -> num + 3;

        Function<Integer, Integer> addThenMultiply = multiply.compose(add);

        System.out.println("first add then multiply :: " + addThenMultiply.apply(2));

        Function<Integer, Integer> multiplyThenAdd = multiply.andThen(add);

        System.out.println("first multiply then add :: " + multiplyThenAdd.apply(2));

    }

    private static void predicateComposition() {
        Predicate<String> startWithA = text -> text.startsWith("A");
        Predicate<String> startWithB = text -> text.startsWith("B");

        Predicate<String> andComposed = startWithA.and(startWithB);

        Predicate<String> orComposed = startWithA.or(startWithB);

        String input = "A hardworking person must relax";
        boolean result = andComposed.test(input);
        System.out.println("and :: " + result);

        result = orComposed.test(input);
        System.out.println("or :: " + result);
    }

    private static void emailVerification(String emailId) {
        Predicate<String> emailFilter = Pattern
                .compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
                .asPredicate();
        Predicate<String> isEmailBlank = email -> email != null && !email.isBlank();

        Predicate<String> isValidEmailId = isEmailBlank.and(emailFilter);
        if(!isValidEmailId.test(emailId)) {
            System.out.println("Invalid email id " + emailId);
        } else {
            System.out.println("Valid email id " + emailId);
        }
        System.out.println("isEmailBlank ::" + isEmailBlank.test(emailId));
        System.out.println("emailFilter ::" + emailFilter.test(emailId));
    }

}

/*class Person {
	public
}*/
