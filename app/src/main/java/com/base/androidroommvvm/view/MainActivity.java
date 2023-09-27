package com.base.androidroommvvm.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.base.androidroommvvm.R;
import com.base.androidroommvvm.room_database.MartialArt;
import com.base.androidroommvvm.vm.MartialArtViewModel;

public class MainActivity extends AppCompatActivity implements ListItemLongClickListner {

    private MartialArtViewModel mMartialArtViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView rv = findViewById(R.id.recyclerview);

        MartialArtListAdapter listAdapter = new MartialArtListAdapter(this, new MartialArtListAdapter.MartialArtDiff());


        rv.setAdapter(listAdapter);

        rv.setLayoutManager(new LinearLayoutManager(this));


        mMartialArtViewModel = new ViewModelProvider(this).get(MartialArtViewModel.class);

        mMartialArtViewModel.getAllMartialArts().observe(this, martialArts -> {

            listAdapter.submitList(martialArts);
        });

        findViewById(R.id.fab).setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, NewMartialArtActivity.class);
            newMartailArtResultActivityLauncher.launch(intent);

        });

    }
    ActivityResultLauncher<Intent> newMartailArtResultActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {

                if(result.getResultCode()== Activity.RESULT_OK){
                    Intent data = result.getData();
                    String favMA = data.getStringExtra(NewMartialArtActivity.NEW_MARTIAL_ART_KEY);

                    mMartialArtViewModel.insertMartialArt(new MartialArt(favMA));
                }

            }
    );

    @Override
    public void listItemLongClicked(MartialArt martialArt) {


        AlertDialog deleteEntry = new AlertDialog.Builder(this)

                .setTitle("Delete entry")

                .setMessage("Are you sure you want to delete this entry?")


                // Specifying a listener allows you to take an action before dismissing the dialog.

                // The dialog is automatically dismissed when a dialog button is clicked.

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        // Continue with delete operation


                        mMartialArtViewModel.deleteMartialArt(martialArt);


                    }

                })


                // A null listener allows the button to dismiss the dialog and take no further action.

                .setNegativeButton(android.R.string.no, null)

                .setIcon(android.R.drawable.radiobutton_off_background)

                .show();


    }

}

