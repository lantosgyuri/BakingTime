package com.example.lanto.bakingtime.widget;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.database.RecipeDatabase;

import java.util.List;

import static com.example.lanto.bakingtime.widget.WidgeIntentService.INT_VALUE_KEY;
import static com.example.lanto.bakingtime.widget.WidgeIntentService.PREF_NAME;

/**
 * Implementation of App Widget functionality.
 */
public class BakingTimeWidget extends AppWidgetProvider {

    public static final String MAXIMUM_POSITION = "max_position";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_time_widget);

        //get last saved recipeName
        List<String> recipeNames = getRecipeNames(context);

        Intent intent = new Intent(context, WidgeIntentService.class);
        intent.setAction(WidgeIntentService.ACTION_SET_RECIPE);
        intent.putExtra(MAXIMUM_POSITION, recipeNames.size());
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // set the next recipe name
        views.setOnClickPendingIntent(R.id.widget_recipe_next_button, pendingIntent);
        Intent layoutServiceIntent = new Intent(context, LinearWidgetService.class);
        views.setRemoteAdapter(R.id.widget_list_view, layoutServiceIntent);

        //get last saved position
        SharedPreferences sp = context.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        int position = sp.getInt(INT_VALUE_KEY, 0);

        views.setTextViewText(R.id.widget_recipe_name, recipeNames.get(position));

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    //helper method to crate a string array from recipe names
    public static List<String> getRecipeNames(Context context){
        final RecipeDatabase db = RecipeDatabase.getsInstance(context);
        return db.recipeDao().loadAllRecipeName();

    }

    public static void updateWidgets(Context context, AppWidgetManager appWidgetManager,
                                     int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


}

