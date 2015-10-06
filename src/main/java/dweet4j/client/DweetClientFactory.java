package dweet4j.client;

/**
 * DweetClientFactory - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 10:24
 * @version $Id$
 */
public class DweetClientFactory {

    private static DweetClient client = null;

    public static DweetClient getClient() {
        if(client == null) {
            client = new DweetClient();
        }
        return client;
    }

}
