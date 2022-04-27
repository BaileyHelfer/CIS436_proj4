package com.example.cis436_proj4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

//import com.example.cis436_proj4.ui.main.MainFragment;
import com.example.cis436_proj4.ui.main.MainFragment;
import com.example.cis436_proj4.ui.main.dogImageFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements dogImageFragment.dogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.dogFragViewContainer, dogImageFragment.newInstance())
                    .commitNow();
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.mainFragViewContainer, MainFragment.newInstance())
//                    .commitNow();
        }

    }
    public void onDogSelection(JSONObject data) throws JSONException{
//        Fragment main = this.getSupportFragmentManager().findFragmentById(R.id.mainFragViewContainer);
        MainFragment dialog = new MainFragment();
//        dialog.showDog(data);
        dialog.show(getSupportFragmentManager(),"MainFragment");
    }
}