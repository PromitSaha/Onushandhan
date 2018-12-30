package group10.onushandhan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Promit on 9/19/2017.
 */

public class MissingList_Activity extends AppCompatActivity {


    private ListView ListViewPerson;
    List<Person> personList;
    DatabaseReference databasePerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missinglist);

        ListViewPerson = (ListView)findViewById(R.id.listViewPerson);
        personList = new ArrayList<>();
        databasePerson = FirebaseDatabase.getInstance().getReference("person");

    }

    @Override
    protected void onStart() {
        super.onStart();

        databasePerson.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                personList.clear();
                for(DataSnapshot personSnapshot : dataSnapshot.getChildren()){
                    Person person = personSnapshot.getValue(Person.class);

                    personList.add(person);
                }
                PersonList adapter = new PersonList(MissingList_Activity.this, personList);
                ListViewPerson.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
