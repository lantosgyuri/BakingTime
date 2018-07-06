package com.example.lanto.bakingtime.widget;

import android.app.Activity;
import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import com.example.lanto.bakingtime.R;

import static com.example.lanto.bakingtime.widget.BakingTimeWidget.MAXIMUM_POSITION;

public class WidgeIntentService extends IntentService {

    public static final String ACTION_SET_RECIPE =
            "com.example.lanto.bakingtime.action.set_recipe";

    public static final String INT_VALUE_KEY = "int_value_key";
    public static final String PREF_NAME = "pref_name";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public WidgeIntentService() {
        super("WidgetIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null){
            final String action = intent.getAction();
            if (action == ACTION_SET_RECIPE){
                int maxPosition = intent.getIntExtra(MAXIMUM_POSITION, 0);
                setNextRecipeName(maxPosition);
            }
        }
    }

    private void setNextRecipeName(int maximumPosition){
        //first get the last saved position
        SharedPreferences sp = getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        int position = sp.getInt(INT_VALUE_KEY, 0);

        // increase value
        position++;
        if(position >= maximumPosition) position = 0;

        //save current position
        SharedPreferences sharedPrefs = this.getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(INT_VALUE_KEY, position);
        editor.apply();

        //update the widget
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = manager.getAppWidgetIds(new ComponentName(this, BakingTimeWidget.class));
        BakingTimeWidget.updateWidgets(this, manager, appWidgetIds);
        //notify LinearWidgetService to update the ListView
        manager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_list_view);

    }
}
