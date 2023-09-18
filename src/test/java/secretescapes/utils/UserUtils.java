package secretescapes.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class UserUtils {

    public static String getRandomEmail() {
        return RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomAlphabetic(5) + ".com";
    }
}
