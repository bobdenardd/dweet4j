package dweet4j.client;

import com.google.common.collect.Lists;
import dweet4j.exception.DweetException;
import dweet4j.model.Dweet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * DweetClient - Short description of the class
 *
 * @author Camille
 *         Last: 06/10/2015 10:24
 * @version $Id$
 */
public class DweetClient {

    private static final String SUCCEEDED = "succeeded";
    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private CloseableHttpClient httpClient;

    public DweetClient() {
        this.httpClient = HttpClients.createDefault();
    }

    public Dweet createDweet(String thing, String payload) throws DweetException {
        HttpPost postMethod = new HttpPost(DweetHelper.getDweetCreateUrl(thing));
        postMethod.addHeader("Content-Type", "application/json");
        postMethod.setEntity(new StringEntity(payload, "UTF-8"));
        try (CloseableHttpResponse response = this.httpClient.execute(postMethod)) {
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
            if(succeded(jsonObject)) {
                return singleFromJson(jsonObject);
            }
            return null;
        } catch(JSONException e) {
            throw new DweetException("Could not understand response", e);
        } catch (Exception e) {
            throw new DweetException("Could not create dweet for thing " + thing, e);
        }
    }

    public List<Dweet> getLatestDweet(String thing) throws DweetException {
        HttpGet getMethod = new HttpGet(DweetHelper.getDweetLatestUrl(thing));
        try (CloseableHttpResponse response = this.httpClient.execute(getMethod)) {
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
            if (succeded(jsonObject)) {
                return multipleFromJson(jsonObject);
            }
            return null;
        } catch(JSONException e) {
            throw new DweetException("Could not understand response", e);
        } catch(Exception e) {
            throw new DweetException("Could not get latest dweet for thing " + thing, e);
        } finally {
            getMethod.completed();
        }
    }

    public List<Dweet> getAllDweets(String thing) throws DweetException {
        HttpGet getMethod = new HttpGet(DweetHelper.getDweetAllUrl(thing));
        try (CloseableHttpResponse response = this.httpClient.execute(getMethod)) {
            JSONObject jsonObject = new JSONObject(EntityUtils.toString(response.getEntity()));
            if (succeded(jsonObject)) {
                return multipleFromJson(jsonObject);
            }
            return null;
        } catch(JSONException e) {
            throw new DweetException("Could not understand response", e);
        } catch(Exception e) {
            throw new DweetException("Could not get latest dweet for thing " + thing, e);
        } finally {
            getMethod.completed();
        }
    }

    private boolean succeded(JSONObject response) {
        return SUCCEEDED.equals(response.getString("this"));
    }

    private Dweet singleFromJson(JSONObject response) {
        JSONObject root = response.getJSONObject("with");
        String thing = root.getString("thing");
        String content = root.get("content").toString();
        Date date;
        try {
            date = FORMAT.parse(root.getString("created"));
        } catch(ParseException e) {
            date = null;
        }
        return new Dweet(content, date, thing);
    }

    private List<Dweet> multipleFromJson(JSONObject response) {
        List<Dweet> result = Lists.newArrayList();
        JSONArray root = response.getJSONArray("with");
        for(int i = 0; i < root.length(); i++) {
            String thing = root.getJSONObject(i).getString("thing");
            String content = root.getJSONObject(i).get("content").toString();
            Date date;
            try {
                date = FORMAT.parse(root.getJSONObject(i).getString("created"));
            } catch(ParseException e) {
                date = null;
            }
            result.add(new Dweet(content, date, thing));
        }
        return result;
    }

}
