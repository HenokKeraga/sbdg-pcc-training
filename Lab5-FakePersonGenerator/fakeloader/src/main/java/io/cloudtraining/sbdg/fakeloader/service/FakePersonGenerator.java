package io.cloudtraining.sbdg.fakeloader.service;

import com.github.javafaker.Faker;
import io.cloudtraining.sbdg.model.Person;

import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FakePersonGenerator {

    private final Random random;
    private final Faker faker;

    /**
     * Keep track of id's
     */
    AtomicInteger instanceCounter = new AtomicInteger(1);
    AtomicInteger idCounter = new AtomicInteger();

    public FakePersonGenerator() {
        random = new Random(0);
        faker = Faker.instance(random);
    }

    public Set<Person> generateFakePersonRecords(int numberOfPeople) {
        return IntStream.range(0, numberOfPeople)
                .mapToObj(value -> generateFakePerson(faker.letterify("a??")))
                .collect(Collectors.toSet());
    }

    public Person generateFakePerson(String idPrefix) {
        /**
         * Generate Random Birthdate
         */

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        int minAge = faker.number().numberBetween(1, 99);
        int maxAge = faker.number().numberBetween(minAge, 104);

        long from = new GregorianCalendar(currentYear - maxAge, currentMonth, currentDay).getTime().getTime();
        long to = new GregorianCalendar(currentYear - minAge, currentMonth, currentDay).getTime().getTime();

        Date dateOfBirth = faker.date().birthday(minAge, maxAge);

        return Person.newBuilder()
                .id(idPrefix + idCounter.getAndIncrement())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .nickName(faker.book().title())
                .dateOfBirth(dateOfBirth.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate())
                .build();
    }
}
