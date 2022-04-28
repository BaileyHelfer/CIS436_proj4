package com.example.cis436_proj4.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private List<String> dogList = new ArrayList<String>();
    private MutableLiveData<String> dogBreeds;

    public MutableLiveData<String> getResult() {
        if (dogBreeds == null) {
            dogBreeds = new MutableLiveData<String>();

        }
        return dogBreeds;

    }

    public void setBreeds(ArrayList<String> breeds) {

        for (int i = breeds.size(); i < 0; i--) {
            dogList.add(breeds.get(i));
        }

    }
}