package com.example.myasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    private MyAsyncTask myAsyncTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartAsyncTaskClick(View view) {
        Log.d(TAG, "onStartAsyncTaskClick");
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    public void onCancelClick(View view) {
        Log.d(TAG, "onCancelClick");
        if (myAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            Log.d(TAG, "onCancelClick calling cancel...");
            myAsyncTask.cancel(true);
        }
    }
}

class MyAsyncTask extends AsyncTask {

    private final String TAG = getClass().getSimpleName();
    private int counter = 0;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute");
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        Log.d(TAG, "doInBackground");
        for (int i = 0 ; i < 10 ; i++) {
            counter = i;
            //Log.d(TAG, "doInBackground counter: " + counter);
            publishProgress(counter);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        //Log.d(TAG, "onProgressUpdate counter: " + counter);
        Log.d(TAG, "" + values[0]);
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Log.d(TAG, "onPostExecute counter: " + counter);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.d(TAG, "onCancelled counter: " + counter);
    }
}

