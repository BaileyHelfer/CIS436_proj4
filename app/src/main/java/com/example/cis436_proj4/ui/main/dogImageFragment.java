package com.example.cis436_proj4.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cis436_proj4.databinding.DogImageFragmentBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dogImageFragment extends Fragment {

    private DogImageViewModel mViewModel;
    private JSONArray content = new JSONArray();
    private DogImageFragmentBinding binding;
    private dogImageFragment.dogListener callback;
    private RequestQueue requestQueue;

    public static dogImageFragment newInstance() {
        return new dogImageFragment();
    }

    public interface dogListener{
        void onDogSelection(JSONObject data) throws JSONException;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (dogImageFragment.dogListener)context;
        }
        catch (ClassCastException e)
        {
            Log.d("Error", String.valueOf(e));
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TEMP", String.valueOf(content.length()));

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DogImageFragmentBinding.inflate(inflater,container,false);
        requestQueue = Volley.newRequestQueue(this.getContext());
        RequestQueue imageRequestQueue = Volley.newRequestQueue(this.getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://api.thedogapi.com/v1/breeds?api_key=%22+ac3e68d7-3459-4bdc-b3ca-9a49b7720d7b",
                (JSONArray) null,
                response -> {
                    for (int i = 0; i < 20; i++) {
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Log.d("Temp", String.valueOf(jsonObject));
                            Log.d("@JSonArray", "onResponse: "
                                    + jsonObject.getString("id") +
                                    " "+jsonObject.getString("name") +
                                    " "+jsonObject.getString("temperament") +
                                    " "+jsonObject.getString("reference_image_id") +
                                    " "+jsonObject.getString("life_span"));
                            content.put(jsonObject);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("**Test", e.toString());
                        }

                    }
                    // Get the first 20 dogs from list.... if we did all of them then list would be way too long.
                    for(int x=0;x<20;x++) {
                        try {

                        JSONObject data = content.getJSONObject(x);
                        JSONObject image = data.getJSONObject("image");
                        Log.d("height", String.valueOf(image.getInt("height")));
                            Log.d("width", String.valueOf(image.getInt("width")));
                        ImageRequest request = new ImageRequest(image.getString("url"),
                                bitmap ->{
                                    ImageView imageInsert = new ImageView(this.getContext());
                                    imageInsert.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            try {
                                                dogSelect(data);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                    imageInsert.setImageBitmap(bitmap);
                                    binding.linearLayout1.addView(imageInsert);
                                },
                                image.getInt("width"),
                                600,
                                ImageView.ScaleType.FIT_CENTER,
                                Bitmap.Config.ARGB_8888,
                                error -> {
                                    Log.d("DisplayFragment",error.toString());
                                });
                        imageRequestQueue.add(request);
                    }
                    catch (JSONException e){

                    }

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });
        requestQueue.add(jsonArrayRequest);

        Log.d("++", String.valueOf(content.length()));


        return binding.getRoot();
    }

    public void dogSelect(JSONObject data) throws JSONException {
        callback.onDogSelection(data);
    }

}