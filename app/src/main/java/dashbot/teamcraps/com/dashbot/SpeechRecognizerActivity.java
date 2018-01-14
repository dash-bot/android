package dashbot.teamcraps.com.dashbot;

import android.app.Activity;

import com.microsoft.cognitiveservices.speechrecognition.ISpeechRecognitionServerEvents;
import com.microsoft.cognitiveservices.speechrecognition.MicrophoneRecognitionClient;
import com.microsoft.cognitiveservices.speechrecognition.RecognitionResult;
import com.microsoft.cognitiveservices.speechrecognition.SpeechRecognitionMode;
import com.microsoft.cognitiveservices.speechrecognition.SpeechRecognitionServiceFactory;

import java.io.IOException;

/**
 * Created by shaebrown on 13/01/18.
 */

public class SpeechRecognizerActivity extends Activity implements ISpeechRecognitionServerEvents {

    MicrophoneRecognitionClient micClient;
    SpeechEvent responseResult;

    public SpeechRecognizerActivity(SpeechEvent responseResult) {
        this.responseResult = responseResult;
        String subscriptionKey = this.getString(R.string.subscription_key);
        String authUri = this.getString(R.string.authenticationUri);
        this.micClient = SpeechRecognitionServiceFactory.createMicrophoneClient(
                this,
                SpeechRecognitionMode.ShortPhrase,
                "en-us",
                this,
                subscriptionKey
        );
        this.micClient.setAuthenticationUri(authUri);
    }

    /**
     * Start Microphone recording
     */
    public void startRecording() {
        this.micClient.startMicAndRecognition();
    }

    @Override
    public void onPartialResponseReceived(String s) {
        responseResult.onSpeechPartial(s);
    }

    @Override
    public void onFinalResponseReceived(RecognitionResult recognitionResult) {
        // TODO error checking
        this.micClient.endMicAndRecognition();
        this.responseResult.onSpeechEnd();
        String command = recognitionResult.Results[0].DisplayText;
        String reply = null;
        try {
            reply = ConversationClient.getReply(command);
        } catch (IOException e) {
            //TODO error handling
            e.printStackTrace();
        }
        responseResult.onReply(reply);
    }

    @Override
    public void onIntentReceived(String s) {
        // Done through the python server
        throw new UnsupportedOperationException("Luis intent managed through Python server");
    }

    @Override
    public void onError(int i, String s) {
        // TODO
    }

    @Override
    public void onAudioEvent(boolean b) {
        // TODO
    }
}
