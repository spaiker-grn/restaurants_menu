package spaiker_grn.github.com.restaurants_menu;

import android.content.Intent;
import android.widget.ListView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowLog;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)

public class RestarauntsUnitTest {

    private ActivityController<MainActivity> activityController;
    MainActivity mMainActivity;
    ListView mListView;
    final int mListCount = 3;

    @Before
    public void init() {
        activityController = Robolectric.buildActivity(MainActivity.class);
        ShadowLog.stream = System.out;
    }

    @Test
    public void activityStarted() throws Exception {

        activityController.create();
        activityController.visible();
        activityController.start();
        activityController.resume();

        mMainActivity = activityController.get();

        mListView = (ListView) mMainActivity.findViewById(R.id.main_list);
        final String firstListItem = mMainActivity.getResources().getStringArray(R.array.main_menu)[0];

        assertNotNull("List not found", mListView);
        ShadowLog.d("Child count", mListView.getChildCount() + " ");
        ShadowLog.d("Checking the first child name in adapter ",
                (mListView.getAdapter().getItem(0).toString()));
        assertTrue(mListView.getChildCount() == mListCount);
        assertTrue("Item doesn't exist", firstListItem.equals(mListView.getAdapter().getItem(0).toString()));
    }

    @Test
    public void listItemClick() throws Exception {

        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        ShadowActivity activity = Shadows.shadowOf(mainActivity);
        ListView listView = (ListView) mainActivity.findViewById(R.id.main_list);
        assertTrue(listView.getChildCount() == mListCount);
        Shadows.shadowOf(listView).performItemClick(0);
        Intent startedIntent = activity.getNextStartedActivity();
        assertNotNull(startedIntent);
        assertEquals(startedIntent.getComponent().getClassName(), DrinkCategoryActivity.class.getName());

    }

    @Mock
    Drink mDrink;

    @Test
    public void mockTest() {
        RuntimeException runtimeException = new RuntimeException("Out of bounds");
        mDrink = mock(Drink.class);
        when(mDrink.getName(0)).thenReturn("Coffee");
        when(mDrink.getImageResourceId(0)).thenReturn(R.drawable.coffee);

        doThrow(runtimeException).when(mDrink).getImageResourceId(4);
        //when(mDrink.getImageResourceId(4)).thenReturn(R.drawable.coffee);

        assertEquals("Coffee", mDrink.getName(0));
        assertEquals(R.drawable.coffee, mDrink.getImageResourceId(0));
        assertEquals(R.drawable.coffee, mDrink.getImageResourceId(0));

        verify(mDrink, atLeastOnce()).getName(0);
        verify(mDrink, atLeast(2)).getImageResourceId(0);

    }

    @Test
    public void spyTest() {

        SpyClass spyClass = spy(new SpyClass());

        when(spyClass.setId(2)).thenReturn(false); // setID should be return true;

        assertEquals(false, spyClass.setId(2));

    }

}