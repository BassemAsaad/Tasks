package com.pioneers.service.utils;

import lombok.NonNull;

// TODO: Create all unit tests for all methods in that class.
public final class NameHelper {

    private NameHelper() {
        throw new AssertionError("Utility class");
    }

    public static String buildFullName(@NonNull final String firstName, @NonNull final String lastName) {
        return firstName.trim() + " " + lastName.trim();
    }
}
