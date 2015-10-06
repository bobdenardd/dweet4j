package dweet4j.model;

import java.util.Date;

/**
 * Dweet - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 10:51
 * @version $Id$
 */
public class Dweet {

    private String content;
    private Date created;
    private String thing;

    public Dweet(String content, Date created, String thing) {
        this.content = content;
        this.created = created;
        this.thing = thing;
    }

    public String getContent() {
        return content;
    }

    public Date getCreated() {
        return created;
    }

    public String getThing() {
        return thing;
    }

    @Override
    public String toString() {
        return "thing: "+ this.thing + " created: " + this.created.toString() + " content: " + this.content;
    }

}
