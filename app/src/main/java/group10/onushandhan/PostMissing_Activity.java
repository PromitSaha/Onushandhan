package group10.onushandhan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Promit on 9/19/2017.
 */

public class PostMissing_Activity extends AppCompatActivity {

    private EditText etNameOfMissingPerson, etAgeOfMissingPerson, etHeightOfMissingPerson;
    private EditText etSkinOfMissingPerson, etHairOfMissingPerson, etMissingPlace;
    private EditText etMissingDate, etMark, etContactNumber, etHomeAddress;
    private Button buttonChoose, buttonUpload, bSubmit;
    private ImageView imageView;
    private String imageId;

    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;
    private StorageReference storageReference;
    private DatabaseReference databasePerson;

    private String imageLink;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postmissing);

        etNameOfMissingPerson = (EditText) findViewById(R.id.etNameOfMissingPerson);
        etAgeOfMissingPerson = (EditText) findViewById(R.id.etAgeOfMissingPerson);
        etHeightOfMissingPerson = (EditText) findViewById(R.id.etHeightOfMissingPerson);
        etSkinOfMissingPerson = (EditText) findViewById(R.id.etSkinOfMissingPerson);
        etHairOfMissingPerson = (EditText) findViewById(R.id.etHairOfMissingPerson);
        etMissingPlace = (EditText) findViewById(R.id.etMissingPlace);
        etMissingDate = (EditText) findViewById(R.id.etMissingDate);
        etMark = (EditText) findViewById(R.id.etMark);
        etContactNumber = (EditText) findViewById(R.id.etContactNumber);
        etHomeAddress = (EditText) findViewById(R.id.etHomeAddress);
        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);
        bSubmit = (Button) findViewById(R.id.bSubmit);
        imageView = (ImageView) findViewById(R.id.imageView);

        storageReference = FirebaseStorage.getInstance().getReference("images");
        databasePerson = FirebaseDatabase.getInstance().getReference("person");


        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChosser();
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPerson();
                startActivity(new Intent(PostMissing_Activity.this, Menu_Activity.class));
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void uploadFile() {

        if (filePath != null) {
            final ProgressDialog progressdialog = new ProgressDialog(this);
            progressdialog.setTitle("Uploading ....");
            progressdialog.show();


            String aToZ = "1234567890qwertyuiopasdfghjklzxcvbnm"; // 36 letter.
            String randomStr = generateRandom(aToZ);
            randomStr = randomStr + ".jpg";
            imageId = randomStr;

            StorageReference riversRef = storageReference.child(randomStr);

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressdialog.dismiss();
                            @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            imageLink = downloadUrl.toString();
                            Toast.makeText(getApplicationContext(), "File Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressdialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressdialog.setMessage(((int) progress) + "%Uploading . . .");

                        }
                    })
            ;

        } else {

        }

    }


    private static String generateRandom(String aToZ) {
        Random rand = new Random();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < 17; i++) {
            int randIndex = rand.nextInt(aToZ.length());
            res.append(aToZ.charAt(randIndex));
        }
        return res.toString();
    }

    private void showFileChosser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "select an image"), PICK_IMAGE_REQUEST);
    }

    private void addPerson() {
        Toast.makeText(PostMissing_Activity.this, "in addPerson", Toast.LENGTH_SHORT).show();
        String id = databasePerson.push().getKey();

        String name = etNameOfMissingPerson.getText().toString();
        String age = etAgeOfMissingPerson.getText().toString();
        String height = etHeightOfMissingPerson.getText().toString();
        String skin = etSkinOfMissingPerson.getText().toString();
        String hair = etHairOfMissingPerson.getText().toString();
        String place = etMissingPlace.getText().toString();
        String date = etMissingDate.getText().toString();
        String mark = etMark.getText().toString();
        String contact = etContactNumber.getText().toString();
        String address = etHomeAddress.getText().toString();

        Person person = new Person(id, name, age, height, skin, hair, place, date, mark, contact, address, imageId, imageLink);

        databasePerson.child(id).setValue(person);
    }


}
