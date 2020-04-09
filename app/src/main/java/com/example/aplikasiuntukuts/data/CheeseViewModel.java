package com.example.aplikasiuntukuts.data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class CheeseViewModel extends AndroidViewModel{

    private CheeseRepository mRepository;
    private LiveData<List<Cheese>> allCheeses;

    public CheeseViewModel (Application application) {
        super(application);
        mRepository = new CheeseRepository(application);
        allCheeses = mRepository.getAllCheeses();
    }

    public LiveData<List<Cheese>> getAllCheeses() { return allCheeses; }

    public void insert(Cheese cheeses) { mRepository.insert(cheeses); }
    public void update(Cheese cheeses) { mRepository.update(cheeses); }
    public void deleteCheese(Cheese cheeses) { mRepository.deleteCheese(cheeses); }

}
