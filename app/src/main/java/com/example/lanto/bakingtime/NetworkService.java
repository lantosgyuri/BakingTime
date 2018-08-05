package com.example.lanto.bakingtime;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.lanto.bakingtime.githubservice.Network;


public class NetworkService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NetworkService() {
        super("NetworkService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
            Log.e("Intent service", "Runs");
            Network.getRecipesAndSaveInDB(getApplicationContext());
    }
}
