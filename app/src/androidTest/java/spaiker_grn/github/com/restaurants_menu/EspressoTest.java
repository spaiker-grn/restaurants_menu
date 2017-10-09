package spaiker_grn.github.com.restaurants_menu;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void espressoTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("Drink"),
                        childAtPosition(
                                withId(R.id.main_list),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("Coffee"),
                        childAtPosition(
                                allOf(withId(android.R.id.list),
                                        withParent(withId(android.R.id.content))),
                                0),
                        isDisplayed()));
        textView.perform(click());

        pressBack();

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("Black tea"),
                        childAtPosition(
                                allOf(withId(android.R.id.list),
                                        withParent(withId(android.R.id.content))),
                                1),
                        isDisplayed()));
        textView2.perform(click());

        pressBack();

        ViewInteraction textView3 = onView(
                allOf(withId(android.R.id.text1), withText("Green tea"),
                        childAtPosition(
                                allOf(withId(android.R.id.list),
                                        withParent(withId(android.R.id.content))),
                                2),
                        isDisplayed()));
        textView3.perform(click());

        pressBack();

        pressBack();

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("Food"),
                        childAtPosition(
                                withId(R.id.main_list),
                                1),
                        isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(android.R.id.text1), withText("Stores"),
                        childAtPosition(
                                withId(R.id.main_list),
                                2),
                        isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(android.R.id.text1), withText("Stores"),
                        childAtPosition(
                                withId(R.id.main_list),
                                2),
                        isDisplayed()));
        appCompatTextView4.perform(click());

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
