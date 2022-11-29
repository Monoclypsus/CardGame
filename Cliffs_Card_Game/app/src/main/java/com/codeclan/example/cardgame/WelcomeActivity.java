package com.codeclan.example.cardgame;

/**
 * Created by user on 28/05/2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    Button enterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onUserEnterButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText)findViewById(R.id.playerNameTxt);
        String name=editText.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        intent.putExtras(bundle);

        if(name.isEmpty())
            return;
        else
            startActivity(intent);

    }
}
