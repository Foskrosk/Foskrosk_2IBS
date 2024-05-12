package ru.appline.pets.logic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Pet {
    private final String name;
    private final String type;
    private final int age;
}
