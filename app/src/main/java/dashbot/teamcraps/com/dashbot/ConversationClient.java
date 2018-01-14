package dashbot.teamcraps.com.dashbot;


import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by shaebrown on 13/01/18.
 */
public class ConversationClient {
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static String url = "https://api2.teamcraps.com/?command=%s";
    private static HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
    public static String getReply(String command) throws IOException {
        String request_url = String.format(url, URLEncoder.encode(command, "UTF-8"));
        System.out.println(request_url);
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(request_url));
        return request.execute().parseAsString();
    }
}
