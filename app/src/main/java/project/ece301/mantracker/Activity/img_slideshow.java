package project.ece301.mantracker.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import project.ece301.mantracker.Adapter.slide_show_adapter;
import project.ece301.mantracker.File.img_cell;
import project.ece301.mantracker.MedicalProblem.Record;
import project.ece301.mantracker.R;

import static project.ece301.mantracker.File.StoreData.patients;


//TODO we need to combine with other images
public class img_slideshow extends AppCompatActivity {

    private final String imge_title[] = {

            "img1",
            "img2",
            "img3",
            "img4",
            "img5",
            "img6",
            "img7",
            "img8",
            "img9",
            "img10",
    };

    private final Integer imge[] = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            R.drawable.img10,
    };

    ArrayList<String> RecordPhotos = new ArrayList<String>();

    int index,problemIndex;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_slideshow);

        Toolbar slideshow_toolbar = (Toolbar) findViewById(R.id.slideshow_toolbar) ;
        setSupportActionBar(slideshow_toolbar);
        getSupportActionBar().setTitle("Record List Slide Show");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        index = bundle.getInt("USERINDEX");
        problemIndex = bundle.getInt("ProblemIndex");

        RecordPhotos.clear();

        ArrayList<Record> records = patients.get(index).getProblem(problemIndex).getAllRecords();
        for(int i=0;i<records.size();i++){
            ArrayList<String> photos = records.get(i).getPhotoList();
            for(int j=0;j<photos.size();j++){
                RecordPhotos.add(photos.get(j));
            }
        }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_gallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<String> img_cell_list = RecordPhotos;


        slide_show_adapter slide_show_adapter = new slide_show_adapter(getApplicationContext() , img_cell_list);
        recyclerView.setAdapter(slide_show_adapter);



    }


    public ArrayList<img_cell> img_cell_list(){


        ArrayList<img_cell> img_list = new ArrayList<img_cell>();

        for (int i=0 ; i < RecordPhotos.size() ; i++){

            img_cell tem_img_cell = new img_cell();
            tem_img_cell.setTitle(imge_title[i]);
            tem_img_cell.setImg(imge[i]);
            img_list.add(tem_img_cell);

        }

        return img_list;

    }
}
