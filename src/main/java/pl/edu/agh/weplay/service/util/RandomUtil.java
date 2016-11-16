package pl.edu.agh.weplay.service.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by P on 18.10.2016.
 */
public final class RandomUtil {

    private static final int DEF_COUNT = 20;

    private RandomUtil() {
    }

    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(DEF_COUNT);
    }

}
