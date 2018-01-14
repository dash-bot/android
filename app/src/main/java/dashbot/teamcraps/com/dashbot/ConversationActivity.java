package dashbot.teamcraps.com.dashbot;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ConversationActivity extends AppCompatActivity {
    private RecyclerView mMessageRecycler;
    private MessageListAdapter mMessageAdapter;
    private ChatAppMsgDTO msgDto;
    List<ChatAppMsgDTO> messageList;
    MessageListAdapter chatAppMsgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        // Create the initial data list.
        this.messageList = new ArrayList<ChatAppMsgDTO>();
        this.msgDto = new ChatAppMsgDTO(ChatAppMsgDTO.MSG_TYPE_RECEIVED, "Hello!");
        messageList.add(msgDto);

        mMessageRecycler = findViewById(R.id.reyclerview_message_list);
        mMessageAdapter = new MessageListAdapter(this, messageList);
        mMessageRecycler.setLayoutManager(new LinearLayoutManager(this));

        // Create the data adapter with above data list.
        this.chatAppMsgAdapter = new MessageListAdapter(this, messageList);

        // Set data adapter to RecyclerView.
        mMessageRecycler.setAdapter(chatAppMsgAdapter);

        final EditText msgInputText = findViewById(R.id.edittext_chatbox);

        Button msgSendButton = findViewById(R.id.button_chatbox_send);

        msgSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msgContent = msgInputText.getText().toString();
                if (!TextUtils.isEmpty(msgContent)) {
                    // Add a new sent message to the list.
                    ChatAppMsgDTO msgDto = new ChatAppMsgDTO(ChatAppMsgDTO.MSG_TYPE_SENT, msgContent);
                    messageList.add(msgDto);

                    int newMsgPosition = messageList.size() - 1;

                    // Notify recycler view insert one new data.
                    chatAppMsgAdapter.notifyItemInserted(newMsgPosition);

                    // Scroll RecyclerView to the last message.
                    mMessageRecycler.scrollToPosition(newMsgPosition);

                    // Empty the input edit text box.
                    msgInputText.setText("");
                    ConversationReply task = new ConversationReply();
                    task.execute(msgContent);
                }
            }
        });
    }

    public class ConversationReply extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String[] params) {
            // Add a new sent message to the list.
            String reply;
            try {
                reply = ConversationClient.getReply(params[0]);
            } catch (IOException e) {
                reply = "Web request failed";
            }
            return reply;
        }

        @Override
        protected void onPostExecute(String message) {
            msgDto = new ChatAppMsgDTO(ChatAppMsgDTO.MSG_TYPE_RECEIVED, message);
            messageList.add(msgDto);

            int newMsgPosition = messageList.size() - 1;

            // Notify recycler view insert one new data.
            chatAppMsgAdapter.notifyItemInserted(newMsgPosition);

            // Scroll RecyclerView to the last message.
            mMessageRecycler.scrollToPosition(newMsgPosition);
        }
    }
}
