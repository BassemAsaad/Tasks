package com.pioneers.service.utils;

import java.util.UUID;

/**
 * A Utility class for strings.
 *
 * @author abdelaziz
 */
public final class StringUtils {

    private StringUtils() {
        throw new AssertionError("Cannot instantiate StringUtils");
    }

    /**
     * Generate a random UUID.
     *
     * @return unique identifier
     */
    public static String generateRandomId() {
        return UUID.randomUUID().toString();
    }

    /**
     * Checks if the string is null or blank.
     *
     * @param str input need to be validated.
     * @return true if the str is null or blank, false otherwise
     */
    public static boolean isNullOrBlank(final String str) {
        return str == null || str.isBlank();
    }
}
