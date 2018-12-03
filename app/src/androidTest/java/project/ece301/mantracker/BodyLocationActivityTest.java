package project.ece301.mantracker;

import android.Manifest;
import android.app.Activity;
import android.support.test.rule.GrantPermissionRule;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.junit.Rule;

import project.ece301.mantracker.Activity.BodyLocationActivity;



public class BodyLocationActivityTest extends ActivityInstrumentationTestCase2<BodyLocationActivity> {
    private Solo solo;


    public BodyLocationActivityTest(){
        super(project.ece301.mantracker.Activity.BodyLocationActivity.class);
    }

    public void setUp() throws Exception {
        this.solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }
//
    @Rule
    public GrantPermissionRule mRuntimePermissionRule =
            GrantPermissionRule.grant(Manifest
                    .permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);


// The robotium cannot go back from a external intent cannot test the camera and gellary
    public void testBodyLocationActivity_callCamera() throws Exception {
        solo.assertCurrentActivity("Wrong Activity" , BodyLocationActivity.class);
        solo.clickOnView(solo.getView(R.id.camera_BL));
        solo.finishOpenedActivities();
    }



//    public void testBodyLocationActivity_callgallery() throws Exception {
//        solo.assertCurrentActivity("Wrong Activity" , BodyLocationActivity.class);
//        solo.clickOnView(solo.getView(R.id.gallery_BL));
//        solo.finishOpenedActivities();
//    }
//



    public void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
    }


}
