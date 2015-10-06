package dweet4j.client;

/**
 * DweetHelper - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 10:27
 * @version $Id$
 */
public class DweetHelper implements DweetSetup {


    public static String getDweetCreateUrl(String thing) {
        return DWEET_CREATE.replaceAll(DWEET_THING, thing);
    }

    public static String getDweetLatestUrl(String thing) {
        return DWEET_LATEST.replaceAll(DWEET_THING, thing);
    }

    public static String getDweetAllUrl(String thing) {
        return DWEET_ALL.replaceAll(DWEET_THING, thing);
    }

}
