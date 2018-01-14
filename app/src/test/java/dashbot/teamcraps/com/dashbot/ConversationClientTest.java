package dashbot.teamcraps.com.dashbot;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by shaebrown on 13/01/18.
 */
public class ConversationClientTest {

    @Test
    public void testNoneReply() throws Exception {
        String nonsense = "hahahahahahah";
        String reply = ConversationClient.getReply(nonsense);
        assertEquals("I'm sorry I did not understand your request", reply);
    }
}