package com.example.lanto.bakingtime.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "recipes")
public class Recipe {

    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("ingredients")
    @Expose
    @Ignore
    private List<Ingredient> ingredients = null;

    @ColumnInfo(name = "ingredients")
    private String ingredientsSql;

    @SerializedName("steps")
    @Expose
    @Ignore
    private List<Step> steps = null;

    @ColumnInfo( name = "steps")
    private String stepsSql;

    @SerializedName("servings")
    @Expose
    private Integer servings;

    @SerializedName("image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStepSql(List<Step> steps){
        stepsSql = Converter.convertStepList(steps);
    }

    public void setIngredientSql(List<Ingredient> ingredients){
        ingredientsSql = Converter.convertIngredientList(ingredients);
    }
}
