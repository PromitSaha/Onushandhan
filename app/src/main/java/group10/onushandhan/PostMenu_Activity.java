package group10.onushandhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Promit on 8/14/2017.
 */

public class PostMenu_Activity extends AppCompatActivity {


    private Button bReport, bFound;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postmenu);

        bReport = (Button)findViewById(R.id.bReport);
        bFound = (Button)findViewById(R.id.bFound);

        bReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostMenu_Activity.this, PostMissing_Activity.class));
            }
        });
        bFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PostMenu_Activity.this, PostFound_Activity.class));
            }
        });


    }
}
