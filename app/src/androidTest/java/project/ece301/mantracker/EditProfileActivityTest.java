package project.ece301.mantracker;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import project.ece301.mantracker.Activity.MainActivity;
import project.ece301.mantracker.EditProfile.EditProfileActivity;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
public class EditProfileActivityTest {

    @Rule
    public ActivityTestRule<EditProfileActivity> rule = new ActivityTestRule<EditProfileActivity>
            (EditProfileActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            InstrumentationRegistry.getTargetContext();
            Intent intent = new Intent();
            intent.putExtra("Username", "Tim12345");
            return intent;
        }
    };

    @Test
    public void testUIItemsArePresent()  {
        EditProfileActivity activity = rule.getActivity();
        View viewById = activity.findViewById(R.id.usernameTextView);
        assertThat(viewById, notNullValue());
        viewById = activity.findViewById(R.id.emailTextView);
        assertThat(viewById, notNullValue());
        viewById = activity.findViewById(R.id.phoneTextView);
        assertThat(viewById, notNullValue());
    }

    @Test
    public void testUsernameReceived() {
        EditProfileActivity activity = rule.getActivity();
        String username = activity.getUsername();
        assertEquals("Tim12345", username);
    }

    @Test
    public void testUsernameDisplayed() {
        EditProfileActivity activity = rule.getActivity();
        TextView usernameTextview = activity.findViewById(R.id.usernameTextView);
        assertEquals("Tim12345" ,usernameTextview.getText());
    }
}
