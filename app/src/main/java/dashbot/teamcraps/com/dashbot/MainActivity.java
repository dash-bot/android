package dashbot.teamcraps.com.dashbot;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity  {

    private LinearLayout savings;
    private LinearLayout chequings;

    private HorizontalScrollView horizontalScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        savings = findViewById(R.id.mainPage_savings_ll);
        chequings = findViewById(R.id.mainPage_chequings_ll);

        horizontalScrollView = findViewById(R.id.mainPage_hsv);

        Toolbar myToolbar = findViewById(R.id.mainPage_toolbar);
        setSupportActionBar(myToolbar);

        FloatingActionButton fab= findViewById(R.id.mainPage_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(),ConversationActivity.class));
            }
        });

        // Scrollview support
        savings.getParent().requestChildFocus(savings,savings);
        chequings.getParent().requestChildFocus(chequings,chequings);
        horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_LEFT);

        //Intent intent = new Intent(this, ConversationActivity.class);
        //startActivity(intent);
    }
}
