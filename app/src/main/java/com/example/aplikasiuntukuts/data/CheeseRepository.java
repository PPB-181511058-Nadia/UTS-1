package com.example.aplikasiuntukuts.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CheeseRepository {
    private CheeseDao cheeseDao;
    private LiveData<List<Cheese>> allCheeses;

    CheeseRepository(Application application) {
        SampleDatabase db = SampleDatabase.getDatabase(application);
        cheeseDao = db.cheese();
        allCheeses = cheeseDao.getCheeses();
    }

    LiveData<List<Cheese>> getAllCheeses() {
        return allCheeses;
    }

    void insert(Cheese cheeses) {
        SampleDatabase.databaseWriteExecutor.execute(() -> {
            cheeseDao.insert(cheeses);
        });
    }

    void update(final Cheese cheeses) {
        SampleDatabase.databaseWriteExecutor.execute(() -> {
            cheeseDao.update(cheeses);
        });
    }

    private static class deleteWordAsyncTask extends AsyncTask<Cheese, Void, Void> {
        private CheeseDao mAsyncTaskDao;

        deleteWordAsyncTask(CheeseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Cheese... params) {
            mAsyncTaskDao.deleteCheese (params[0]);
            return null;
        }
    }

    public void deleteCheese(Cheese cheese)  {
        new deleteWordAsyncTask(cheeseDao).execute(cheese);
    }
}
