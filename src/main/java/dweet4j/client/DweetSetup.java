package dweet4j.client;

/**
 * DweetSetup - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 10:24
 * @version $Id$
 */
public interface DweetSetup {

    String DWEET_ENDPOINT   = "https://dweet.io";
    String DWEET_THING      = "#thing#";

    // Create a dweet for a thing
    String DWEET_CREATE     = DWEET_ENDPOINT + "/dweet/for/" + DWEET_THING;

    // Read the latest dweets for a thing
    String DWEET_LATEST     = DWEET_ENDPOINT + "/get/latest/dweet/for/" + DWEET_THING;

    // Read all the saved dweets (up to 500) for a thing
    String DWEET_ALL        = DWEET_ENDPOINT + "/get/dweets/for/" + DWEET_THING;

    // Listen for dweets from a thing
    String DWEET_LISTEN     = DWEET_ENDPOINT + "/listen/for/dweets/from/" + DWEET_THING;

}
