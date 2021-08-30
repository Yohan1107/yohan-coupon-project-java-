package com.example.couponSystem.enums;

import lombok.Getter;

/**
 * Enum categories
 */
@Getter
public enum Category {

    FOOD(1),
    VACATIONS(2),
    ELECTRICITY(3),
    CLEANING_PRODUCTS(4),
    SPORT_ACCESSORIES(5),
    HOME(6),
    CAR_ACCESSORIES(7),
    KIDS(8),
    PETS(9);

    private final int id;

    /**
     * Constructor
     *
     * @param id the id of the category
     */
    Category(int id) {
        this.id = id;
    }


}
