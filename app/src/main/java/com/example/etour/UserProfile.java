package com.example.etour;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class UserProfile extends AppCompatActivity {
    Button  btnLogout;
    ImageView ivProfilePicture;
    TextView tvEmailAddress, tvFirstName, tvSecondName;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    StorageReference storageReference;
    String userID;
    Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            mUri = savedInstanceState.getParcelable("uri");
            ivProfilePicture.setImageURI(mUri);
        }
        setContentView(R.layout.activity_user_profile);
        tvEmailAddress = findViewById(R.id.tvUserEmail);
        tvFirstName = findViewById(R.id.tvUserFirstName);
        tvSecondName = findViewById(R.id.tvUserSecondName);
        ivProfilePicture = findViewById(R.id.ivProfilePhoto);
        //
        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        userID = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getUid();

        DocumentReference documentReference = firestore.collection("CLIENTS").document("userID");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                assert value != null;
                tvEmailAddress.setText(value.getString("Email"));
                tvFirstName.setText(value.getString("First Name"));
                tvSecondName.setText(value.getString("Second Name"));
            }
        });
        //log out user
        btnLogout = findViewById(R.id.btnSignOutUser);
        //
        ivProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,5000);
            }
        });

        //
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOutUser();
                startActivity(new Intent(view.getContext(),SignInActivity.class));
            }
        });
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5000){
            if (resultCode == Activity.RESULT_OK){
                assert data != null;
                Uri imageUri = data.getData();
                mUri = imageUri;
               // ivProfilePicture.setImageURI(imageUri);

                uploadImageToFirebase(imageUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
            final StorageReference fileRef = storageReference.child("profile.jpg");
            fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Picasso.get().load(uri).into(ivProfilePicture
                            );
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UserProfile.this, "Error occurred while uploading the image", Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mUri != null){
            outState.putParcelable("uri", mUri);
        }
    }

    private void signOutUser() {
        firebaseAuth.signOut();
    }
}