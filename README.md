# dweet4j

Dweet4j is a minimalistic java client intended as a simple wrapper for the dweet API. If you want to know more about this cool simple API, check out [the dweet.io homepage](https://dweet.io/ "Dweet Homepage").

## Creating a dweet for a given thing
```java
import dweet4j.client.DweetClient;
import dweet4j.client.DweetClientFactory;
import dweet4j.model.Dweet;
import org.json.JSONObject;

// Getting reusable dweet client
DweetClient client = DweetClientFactory.getClient();

// Creating dweet json payload
JSONObject object = new JSONObject();
object.append("test", "test");

// Creating the dweet (the source does not need to be created beforehand)
try {
  Dweet dwwet = client.createDweet("test", object.toString());
} catch(DweetException e) {
  ...  
}
```

## Retrieving latest dweet for a given thing
```java
// Getting reusable dweet client
DweetClient client = DweetClientFactory.getClient();

// Fetching latest dweet for test thing (null if none)
Dweet latestDweet = client.getLatestDweet("test");
```

## Retrieving all the dweets for a given thing (500 max)
```java
// Getting reusable dweet client
DweetClient client = DweetClientFactory.getClient();

// Fetching all dweets for the test thing (empty if none)
for(Dweet dweet : client.getAllDweets("test")) {
  ... do something with dweet ...
}
```
