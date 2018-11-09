package project.ece301.mantracker.Activity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.R;

public class AddRecordActivity extends AppCompatActivity {

    //variables to be used
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private EditText enteredTitle;
    private EditText enteredComment;
    private Button dateButton;
    private TextView dateTextView;
    private int year;
    private int month;
    private int day;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_record);
        dateTextView = findViewById(R.id.date);
        dateButton = findViewById(R.id.dateButton);

        //set click listener. We want to launch a popup when the user taps on the date button
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(AddRecordActivity.this,
                        android.R.style.Theme_Material_Light_Dialog, dateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override //autogenerated
            public void onDateSet(DatePicker view, int newYear, int newMonth, int dayOfMonth) {
                year = newYear;
                month = newMonth + 1; // datepicker is zero indexed so January would be month 0 etc.
                day = dayOfMonth;

                dateString = year + "-" + month + "-" + day;
                dateTextView.setText(dateString);
            }
        };
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        //set the current date as default
        dateTextView.setText(dateFormat.format(new Date()));
    }

    public void saveRecord(View view) {
        //Create a new record that will be passed back to the record list activity
        Record record = new Record();
        //store the entered title and description in the record
        enteredTitle = findViewById(R.id.titleInputBox);
        enteredComment = findViewById(R.id.commentInputBox);
        Date newDate = new GregorianCalendar(year, month - 1, day).getTime();


        //will need to update this as other functionality is required
        record.setDescription(enteredComment.getText().toString());
        record.setTitle(enteredTitle.getText().toString());
        record.setDate(newDate);
        Toast.makeText(this, dateFormat.format(record.getDate()), Toast.LENGTH_LONG).show();
        //Now we need to pass the record back to RecordListActivity


        finish();
    }
}
