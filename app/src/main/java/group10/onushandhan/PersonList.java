package group10.onushandhan;

import android.app.Activity;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URL;
import java.util.List;

/**
 * Created by Promit on 9/19/2017.
 */

public class PersonList extends ArrayAdapter<Person>{

    private Activity context;
    private List<Person> persontList;
    StorageReference storageReference;

    public PersonList(Activity context, List<Person> persontList) {
        super(context, R.layout.list_layout, persontList);
        this.context = context;
        this.persontList = persontList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewFirstLine = (TextView)listViewItem.findViewById(R.id.textViewFirstLine);
        ImageView imageView = (ImageView)listViewItem.findViewById(R.id.imageViewListImage);
        //TextView textViewSecondLine = (TextView)listViewItem.findViewById(R.id.textViewSecondLine);

        //storageReference = FirebaseStorage.getInstance().getReference("images");





        Person person = persontList.get(position);

        textViewFirstLine.setText(person.getEtNameOfMissingPerson());
        Uri temp = Uri.parse(person.getImageLink());

        String url = person.getImageLink();
        Glide.with(getContext()).load(url).into(imageView);
        //imageView.setImageURI("https://firebasestorage.googleapis.com/v0/b/onushandhan-cd19d.appspot.com/o/images%2F90quwu2tb3905m9a1.jpg?alt=media&token=f1ea5f85-5c43-4dda-9454-3ad92627573f");


        //textViewSecondLine.setText(" on " + person.getEtMissingDate() + " From " + person.getEtMissingPlace());

        return listViewItem;
    }
}
