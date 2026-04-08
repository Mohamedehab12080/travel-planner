package com.fawry.travel.planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fawry"})
@EntityScan(basePackages = {
        "com.fawry.user.model.entity",
        "com.fawry.destination.model.entity",
        "com.fawry.wishlist.model.entity"
})
@EnableJpaRepositories(basePackages = {
        "com.fawry.user.repository",
        "com.fawry.destination.repository",
        "com.fawry.wishlist.repository"
})
@EnableScheduling
public class FawryTravelPlannerManagement {

    public static void main(String[] args) {
        SpringApplication.run(FawryTravelPlannerManagement.class, args);
        System.out.println("========================================");
        System.out.println("Travel Destination Planner Started!");
        System.out.println("========================================");
    }
}