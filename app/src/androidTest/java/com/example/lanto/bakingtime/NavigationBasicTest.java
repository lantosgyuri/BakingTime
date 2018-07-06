package com.example.lanto.bakingtime;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.example.lanto.bakingtime.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(JUnit4.class)
public class NavigationBasicTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    //check if the recycleviews behave as expected and show the expected values
    @Test
    public void checkLoadedData() {
       // check MainActivity
        onView(withId(R.id.fragment_main_recycler_view))
            .perform(RecyclerViewActions.actionOnItem(
                    hasDescendant(withText("Brownies")),
                    click()));

        //check DetailFragment
        onView(withId(R.id.detail_fragment_recycler))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("Cut and serve.")),
                        click()));

        //check StepFragment
        onView(withId(R.id.step_description)).check(matches(withText("9. Cut and serve.")));
}
}
