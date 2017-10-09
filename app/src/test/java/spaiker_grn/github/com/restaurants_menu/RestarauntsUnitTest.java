package spaiker_grn.github.com.restaurants_menu;

import android.content.Intent;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)

public class RestarauntsUnitTest {

    private MainActivity mMainActivity;
    private ListView mListView;

    @Before
    public void setup() throws Exception {
        mMainActivity = Robolectric.setupActivity(MainActivity.class);
        assertNotNull("MainActivity not initialised", mMainActivity);
        mListView = (ListView) mMainActivity.findViewById(R.id.main_list);
        ShadowLog.stream = System.out;

    }

    @Test
    public void ActivityStarted() throws Exception {

        final String string = mMainActivity.getResources().getStringArray(R.array.main_menu)[0];
        final int listCount = 3;

        assertNotNull("List not found", mListView);
        assertTrue(mListView.getChildCount() == listCount);
        ShadowLog.d("Checking the first child name in adapter ",
                (mListView.getAdapter().getItem(0).toString()));
        assertTrue("Item doesn't exist",string.equals(mListView.getAdapter().getItem(0).toString()));
    }

    @Test
    public void ListItemClick() throws Exception {

        ShadowActivity activity = Shadows.shadowOf(mMainActivity);
        Shadows.shadowOf(mListView).performItemClick(0);
        Intent startedIntent = activity.getNextStartedActivity();
        assertNotNull(startedIntent);
        assertEquals(startedIntent.getComponent().getClassName(), DrinkCategoryActivity.class.getName());

    }
}