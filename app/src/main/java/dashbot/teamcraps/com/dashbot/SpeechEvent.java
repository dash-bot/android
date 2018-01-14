package dashbot.teamcraps.com.dashbot;

/**
 * Created by shaebrown on 13/01/18.
 */

public interface SpeechEvent {
    /**
     * What we want to do with the chat bot's reply
     * @param reply the reply from the chat bot
     */
    void onReply(String reply);

    /**
     * What we want to do with the partial speech result (as you're speaking)
     * @param reply the partial speech string
     */
    void onSpeechPartial(String reply);

    void onSpeechEnd();

}
