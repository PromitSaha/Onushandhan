package group10.onushandhan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu_Activity extends AppCompatActivity {

    Button bsearchlist, bpost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bpost = (Button)findViewById(R.id.bPost);
        bsearchlist = (Button)findViewById(R.id.bSearchList);

        bsearchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Search_List_Menu.class);
                startActivity(intent);
            }
        });

        bpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(intent);
            }
        });


    }
}

