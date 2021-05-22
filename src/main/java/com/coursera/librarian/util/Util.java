package com.coursera.librarian.util;

import java.text.DateFormat;
import java.util.Collection;

public final class Util {
    public static final String BASE_URL = "api";
    public static DateFormat DATE_FORMAT = DateFormat.getDateInstance();

    public static boolean isCollectionNotEmpty(Collection<?> tCollection) {
        return tCollection != null && !tCollection.isEmpty();
    }
}
