package project.ece301.mantracker.CareProviderHome;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import project.ece301.mantracker.R;

public class PatientListAdapter extends RecyclerView.Adapter<PatientCard> {
    private CareProviderHomePresenter presenter;
    private Context mContext;

    public PatientListAdapter(Context context, CareProviderHomePresenter presenter) {
        this.presenter = presenter;
        this.mContext = context;
    }

    @NonNull
    @Override
    public PatientCard onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CardView view = (CardView) LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.patient_card, null);
        return new PatientCard(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientCard viewHolder, int i) {
        //Example: Render image using Picasso library
// https://stacktips.com/tutorials/android/android-recyclerview-example#23-recyclerview-row-layout

        //Setting text view title
        viewHolder.setPatientId(presenter.getPatientIdByPosition(i));
    }

    @Override
    public int getItemCount() {
        return presenter.getPatientCount();
    }

//    public void setClickListener(CareProviderHomeActivity clickListener) {
//        this.clickListener = clickListener;
//    }
}
