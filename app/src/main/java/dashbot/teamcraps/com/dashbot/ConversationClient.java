package dashbot.teamcraps.com.dashbot;


import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.MultipartContent;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by shaebrown on 13/01/18.
 */
public class ConversationClient {
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static String command_url = "https://api2.teamcraps.com/?command=%s";
    private static String voice_url = "https://api2.teamcraps.com/%s";
    private static HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory();
    public static String getReply(String command) throws IOException {
        String request_url = String.format(command_url, URLEncoder.encode(command, "UTF-8"));
        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(request_url));
        return request.execute().parseAsString();
    }

    /**
     *
     * @param wavFile
     * @return remaining enrollments
     * @throws IOException
     */
    public static int enroll(File wavFile) throws IOException {
        String request_url = String.format(voice_url, "enroll");
        MultipartContent content = new MultipartContent();
        content.setMediaType(new HttpMediaType("multipart/form-data"));

        //TODO figure out how to add wav file as form-data with key 'file'
        return 3;
    }
}
