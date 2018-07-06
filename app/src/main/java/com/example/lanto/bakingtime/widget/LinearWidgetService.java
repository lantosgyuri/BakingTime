package com.example.lanto.bakingtime.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.lanto.bakingtime.R;
import com.example.lanto.bakingtime.data.Ingredient;
import com.example.lanto.bakingtime.data.Recipe;
import com.example.lanto.bakingtime.database.RecipeDatabase;

import java.util.List;

import static com.example.lanto.bakingtime.widget.WidgeIntentService.INT_VALUE_KEY;
import static com.example.lanto.bakingtime.widget.WidgeIntentService.PREF_NAME;

public class LinearWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new LinearRemoteViewsFactory(this.getApplicationContext());
    }
}

class LinearRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory{

    private final Context mContext;
    private List<Ingredient> mIngredients;

    public LinearRemoteViewsFactory(Context context){
        mContext = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        RecipeDatabase db = RecipeDatabase.getsInstance(mContext);
        // get the position from widget
        SharedPreferences sp = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
        int position = sp.getInt(INT_VALUE_KEY, 0);
        //in the list the position start from 0, but in sql starts from 1
        position++;
        //query the database
        Recipe recipe = db.recipeDao().loadChoosedIngredient(position);
        mIngredients = recipe.getIngredients();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mIngredients == null ? 0 : mIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);
            Ingredient currentIngredient = mIngredients.get(position);
            rv.setTextViewText(R.id.widget_list_item_text, currentIngredient.getIngredient());

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
