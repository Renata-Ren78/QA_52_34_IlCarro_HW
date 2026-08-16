package utils;

import dto.UserLombok;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

//    public static void main(String[] args) {
//        String firstName = faker.name().firstName();
//        System.out.println(firstName);
//        String lastName = faker.name().lastName();
//        System.out.println(lastName);
//        String email = faker.internet().emailAddress();
//        System.out.println(email);
//    }

    public static UserLombok positiveUser(){
        UserLombok user = UserLombok.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .username(faker.internet().emailAddress())
                .password(PropertiesReader.getProperty
                        ("base.properties","password"))
                .build();
        return user;
    }


}
