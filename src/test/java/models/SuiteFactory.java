package models;

import com.github.javafaker.Faker;
import models.request.suite.post.SuiteRequestModel;

public class SuiteFactory {
    static Faker faker = new Faker();

    public static SuiteRequestModel randomSuite() {
        return SuiteRequestModel.builder()
                .title(faker.name().name())
                .preconditions(faker.animal().name())
                .description(faker.harryPotter().character())
                .build();
    }
}
