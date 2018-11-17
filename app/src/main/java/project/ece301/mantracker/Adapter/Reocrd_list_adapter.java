package project.ece301.mantracker.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.R;




//ListviewAdapter for AddRecordActivity
// template file record_list_adapter.xml
public class Reocrd_list_adapter extends ArrayAdapter {

    private int resourceID;

    public Reocrd_list_adapter(@NonNull Context context, int textViewResourceId, @NonNull List<Record> objects) {
        super(context, textViewResourceId, objects);

        resourceID = textViewResourceId;

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Record record = (Record) getItem(position);
        View view  = LayoutInflater.from(getContext()).inflate(resourceID, null);
        TextView record_title = (TextView) view.findViewById(R.id.record_title);
        TextView record_description  = (TextView) view.findViewById((R.id.record_description));
        TextView record_date = (TextView) view.findViewById(R.id.record_date);
        record_title.setText(record.getTitle());
        record_description.setText(record.getDescription());
        record_date.setText(record.getDate().toString());
//        TODO might add more entries for passing the information to new intend
        return view;
    }


}
