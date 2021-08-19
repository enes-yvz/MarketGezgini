package com.example.marketgezgini;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DatabaseReference ref;
    ArrayList<Deal> list ;
    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList<Deal> firstList;
    private FirebaseAuth mAuth;

    public void enrty () {

        firstList = new ArrayList<>();
        Deal deal = new Deal("","Et,Tavuk,Balýk","https://firebasestorage.googleapis.com/v0/b/marketgezgini.appspot.com/o/kirmizi-et-balik-ve-beyaz-et-secme-tuyolari_1693_1.jpg?alt=media&token=49074908-f678-41d2-a1be-a66da4da2797","");
        firstList.add(deal);
        deal = new Deal("","Süt,Kahvaltýlýk","https://firebasestorage.googleapis.com/v0/b/marketgezgini.appspot.com/o/corn-flakes-with-jar-milk-table-simple-breakfast_351987-207.jpg?alt=media&token=ab85b929-b715-476f-be4a-02899dbf2596","");
        firstList.add(deal);
        deal = new Deal("","Gýda,Þekerleme","https://firebasestorage.googleapis.com/v0/b/marketgezgini.appspot.com/o/5dcd6dd853b4d.jpg?alt=media&token=69649f45-cf58-4cc3-9063-a7c4b858dcaf","");
        firstList.add(deal);
        deal = new Deal("","Ýçecekler","https://firebasestorage.googleapis.com/v0/b/marketgezgini.appspot.com/o/AdobeStock_279692163_Editorial_Use_Only-Beverage-FTR-new.jpg?alt=media&token=81875de6-9e2d-4a6e-b2a1-1c68eed3f8eb","");
        firstList.add(deal);
        deal = new Deal("","Deterjan,Temizlik","https://firebasestorage.googleapis.com/v0/b/marketgezgini.appspot.com/o/catalca-kurumsal-toptan-temizlik-malzemeleri-tedarikcisi.jpg?alt=media&token=7428020e-8c5d-4f6d-bdcd-c8579eee0ef2","");
        firstList.add(deal);
        deal = new Deal("","Kaðýt,Kozmetik","https://firebasestorage.googleapis.com/v0/b/marketgezgini.appspot.com/o/makyaj-malzemeleri-isimleri-1000x600.jpg?alt=media&token=525a9575-af69-4fb2-ae75-98ccd325d32e","");
        firstList.add(deal);
        AdapterClass adapterClass = new AdapterClass(firstList,MainActivity.this);
        recyclerView.setAdapter(adapterClass);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.searchView);
        ref = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.RecyclerView);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        if (user != null) {

        } else {

            signInAnonymously();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        if (list==null) {

            enrty();

        }

        if(ref !=null){

            list = new ArrayList<>();

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.exists()){

                        for(DataSnapshot ds:dataSnapshot.getChildren()){

                            list.add(ds.getValue(Deal.class));

                        }

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(MainActivity.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }


        if(searchView!= null){

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }

    }


    private void search(String s){

        ArrayList<Deal> myList = new ArrayList<>();

        for(Deal object :list){

            if(object.getC().toLowerCase().contains(s.toLowerCase())){

                myList.add(object);
            }

        }

        if(s.equals("")) {

            enrty();
        }

        else {

            AdapterClass adapterClass = new AdapterClass(myList,MainActivity.this);
            recyclerView.setAdapter(adapterClass);

        }

    }

    public void filter(String s){

        ArrayList<Deal> myList = new ArrayList<>();

        for(Deal object :list){

            if(object.getB().toLowerCase().contains(s.toLowerCase())){

                myList.add(object);
            }

        }

        AdapterClass adapterClass = new AdapterClass(myList,MainActivity.this);
        recyclerView.setAdapter(adapterClass);

    }


    @Override
    public void onBackPressed() {

        RecyclerView.Adapter adapter =recyclerView.getAdapter();

        int count = adapter.getItemCount();

        if(count==6){

            super.onBackPressed();
        }

        else {

            Log.d("CDA", "onBackPressed Called");
            enrty();
        }

    }

    private void signInAnonymously() {
        mAuth.signInAnonymously().addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {



            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e("MainActivity", "Sign Failed", exception);
                    }
                });
    }


}