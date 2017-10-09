package spaiker_grn.github.com.restaurants_menu;

import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
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

        final String firstListItem = mMainActivity.getResources().getStringArray(R.array.main_menu)[0];
        final int listCount = 3;

        assertNotNull("List not found", mListView);
        assertTrue(mListView.getChildCount() == listCount);
        ShadowLog.d("Checking the first child name in adapter ",
                (mListView.getAdapter().getItem(0).toString()));
        assertTrue("Item doesn't exist", firstListItem.equals(mListView.getAdapter().getItem(0).toString()));
    }

    @Test
    public void ListItemClick() throws Exception {

        ShadowActivity activity = Shadows.shadowOf(mMainActivity);
        Shadows.shadowOf(mListView).performItemClick(0);
        Intent startedIntent = activity.getNextStartedActivity();
        assertNotNull(startedIntent);
        assertEquals(startedIntent.getComponent().getClassName(), DrinkCategoryActivity.class.getName());

    }



    @Mock
    Drink mDrink;


    @Test
    public void test(){
        RuntimeException runtimeException = new RuntimeException("Out of bounds");
        mDrink = mock(Drink.class);
        when(mDrink.getName(0)).thenReturn("Coffee");
        when(mDrink.getImageResourceId(0)).thenReturn(R.drawable.coffee);

        doThrow(runtimeException).when(mDrink).getImageResourceId(4);
        //when(mDrink.getImageResourceId(4)).thenReturn(R.drawable.coffee);


        assertEquals("Coffee", mDrink.getName(0));
        assertEquals(R.drawable.coffee,mDrink.getImageResourceId(0));
        assertEquals(R.drawable.coffee,mDrink.getImageResourceId(0));



        verify(mDrink, atLeastOnce()).getName(0);
        verify(mDrink, atLeast(2)).getImageResourceId(0);

    }
    @Test
    public void spyTest(){

        SpyClass spyClass = spy(new SpyClass());

        when(spyClass.setId(2)).thenReturn(false); // setID should be return true;

        assertEquals(false, spyClass.setId(2));

    }













}