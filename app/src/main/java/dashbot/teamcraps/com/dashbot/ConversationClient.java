package dashbot.teamcraps.com.dashbot;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

/**
 * Created by shaebrown on 13/01/18.
 */
public class ConversationClient {
    private static String url = "https://api2.teamcraps.com/";
    public static String getReply(String command) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(url)
                .queryString("command", command)
                .asJson();
        //TODO: test if this works
        return response.getBody().toString();
    }
}
