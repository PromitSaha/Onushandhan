package group10.onushandhan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Promit on 10/15/2017.
 */

public class Search_List_Menu extends AppCompatActivity {
    Button bSearcMissingList, bSearcFoundList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchlistmenu);

        bSearcMissingList = (Button)findViewById(R.id.bSearcMissingList);
        bSearcFoundList = (Button)findViewById(R.id.bSearcFoundList);

        bSearcMissingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MissingList_Activity.class);
                startActivity(intent);
            }
        });

        bSearcFoundList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                //startActivity(intent);
            }
        });


    }
}
