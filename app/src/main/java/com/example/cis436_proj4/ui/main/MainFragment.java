package com.example.cis436_proj4.ui.main;

import androidx.fragment.app.DialogFragment;

import android.app.FragmentManager;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.cis436_proj4.databinding.MainFragmentBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class MainFragment extends DialogFragment {
    public MainFragmentBinding binding;
    private MainViewModel mViewModel;
    private static final String TAG = "MainFragment";

    public static MainFragment newInstance() {

        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater,container,false);

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        Log.d("++","ONCLICKSET");
        return binding.getRoot();
    }


    public void showDog(JSONObject data) throws JSONException {
        String name = data.getString("name").toString();
        String temperament = data.getString("temperament").toString();
        String lifespan = data.getString("life_span").toString();
        binding.tvBreed.setText("Breed: " + name);
        binding.tvTemperament.setText("Temperament: " + temperament);
        binding.tvLifespan.setText("Lifespan: "+ lifespan);

    }

}