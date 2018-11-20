package project.ece301.mantracker;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import project.ece301.mantracker.Activity.RecordListActivity;

public class RecordListActivityTest extends ActivityInstrumentationTestCase2<RecordListActivity> {

    private Solo solo;


    public RecordListActivityTest(){
        super(project.ece301.mantracker.Activity.RecordListActivity.class);
    }

    public void setUp() throws Exception {
        this.solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    public void testReocordListActivity(){
        solo.assertCurrentActivity("Wrong Activity" , RecordListActivity.class);
//        solo.clickOnButton("addRecordButton");
//        solo.enterText((EditText) solo.getView(R.id.titleInputBox) , "Record Title") ;
//        solo.enterText((EditText) solo.getView(R.id.commentInputBox) , "Record Comment") ;
//        solo.setDatePicker(0,2016,7,7);
//        solo.clickOnButton("OK");
//        solo.clickOnButton("save");
//        assertTrue(solo.searchText("Record Title"));
//        assertTrue(solo.searchText("Record Comment"));
//        assertTrue(solo.searchText("2016-7-7"));


    }

}
