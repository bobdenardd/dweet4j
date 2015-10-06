package dweet4j.exception;

/**
 * DweetException - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 10:51
 * @version $Id$
 */
public class DweetException extends Exception {

    public DweetException(String message, Exception e) {
        super(message, e);
    }

}
