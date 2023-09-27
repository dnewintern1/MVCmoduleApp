package com.base.androidroommvvm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.base.androidroommvvm.R;

public class NewMartialArtActivity extends AppCompatActivity {

    public  static   final String NEW_MARTIAL_ART_KEY= ".view.NewMartialArtActivity.GET_BACK_MARTIAL_ART";

    private EditText edtMa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_martial_art);

        edtMa = findViewById(R.id.edtMartialArt);

        Button btnsave= findViewById(R.id.btnSaveMa);
        btnsave.setOnClickListener(view -> {

            Intent getBackIntent = new Intent();
            if(TextUtils.isEmpty(edtMa.getText())){

                setResult(RESULT_CANCELED, getBackIntent);

            }else{
                String faveMa= edtMa.getText().toString();
                getBackIntent.putExtra(NEW_MARTIAL_ART_KEY , faveMa);
                setResult(RESULT_OK, getBackIntent);
            }
            finish();


        });

    }
}