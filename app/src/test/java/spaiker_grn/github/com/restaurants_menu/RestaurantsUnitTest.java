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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)

public class RestaurantsUnitTest {

    private ActivityController<MainActivity> activityController;
    private final int mListCount = 3;

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

        final MainActivity mainActivity = activityController.get();

        final ListView listView = (ListView) mainActivity.findViewById(R.id.main_list);
        final String firstListItem = mainActivity.getResources().getStringArray(R.array.main_menu)[0];

        assertNotNull("List not found", listView);
        ShadowLog.d("Child count", listView.getChildCount() + " ");
        ShadowLog.d("Checking the first child name in adapter ",
                (listView.getAdapter().getItem(0).toString()));
        assertTrue(listView.getChildCount() == mListCount);
        assertTrue("Item doesn't exist", firstListItem.equals(listView.getAdapter().getItem(0).toString()));
    }

    @Test
    public void listItemClick() throws Exception {

        final MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        final ShadowActivity activity = Shadows.shadowOf(mainActivity);
        final ListView listView = (ListView) mainActivity.findViewById(R.id.main_list);
        assertTrue(listView.getChildCount() == mListCount);
        Shadows.shadowOf(listView).performItemClick(0);
        final Intent startedIntent = activity.getNextStartedActivity();
        assertNotNull(startedIntent);
        assertEquals(startedIntent.getComponent().getClassName(), DrinkCategoryActivity.class.getName());

    }

    @Mock
    Drink mDrink;

    @Test
    public void mockTest() {
        final RuntimeException runtimeException = new RuntimeException("Out of bounds");
        mDrink = mock(Drink.class);

        doReturn("Coffee").when(mDrink).getName(0);
        doReturn(R.drawable.coffee).when(mDrink).getImageResourceId(0);

        doThrow(runtimeException).when(mDrink).getImageResourceId(4);
        //when(mDrink.getImageResourceId(4)).thenReturn(R.drawable.coffee);

        assertEquals("Coffee", mDrink.getName(0));
        assertEquals(R.drawable.coffee, mDrink.getImageResourceId(0));
        assertEquals(R.drawable.coffee, mDrink.getImageResourceId(0));

        verify(mDrink, atLeastOnce()).getName(0);
        verify(mDrink, atLeast(2)).getImageResourceId(0);

    }


}