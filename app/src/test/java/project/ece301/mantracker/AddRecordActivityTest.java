package project.ece301.mantracker;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import project.ece301.mantracker.Activity.AddRecordActivity;

public class AddRecordActivityTest extends ActivityInstrumentationTestCase2<AddRecordActivity> {
    private Solo solo;


    public AddRecordActivityTest(){
        super(project.ece301.mantracker.Activity.AddRecordActivity.class);

    }


    public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    public void testAddReocordActivity(){
        solo.assertCurrentActivity("Wrong Activity" , AddRecordActivity.class);
        solo.clickOnButton("addRecordButton");
        solo.enterText((EditText) solo.getView(R.id.titleInputBox) , "Record Title") ;
        solo.enterText((EditText) solo.getView(R.id.commentInputBox) , "Record Comment") ;
        solo.setDatePicker(0,2016,7,7);
        solo.clickOnButton("OK");
        solo.clickOnButton("save");
        assertTrue(solo.searchText("Record Title"));
        assertTrue(solo.searchText("Record Comment"));
        assertTrue(solo.searchText("2016-7-7"));

    }


}
