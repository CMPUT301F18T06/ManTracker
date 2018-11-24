package project.ece301.mantracker.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import project.ece301.mantracker.Adapter.slide_show_adapter;
import project.ece301.mantracker.File.img_cell;
import project.ece301.mantracker.R;


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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_slideshow);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_gallery);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        ArrayList<img_cell> img_cell_list = img_cell_list();

        slide_show_adapter slide_show_adapter = new slide_show_adapter(getApplicationContext() , img_cell_list);
        recyclerView.setAdapter(slide_show_adapter);



    }


    public ArrayList<img_cell> img_cell_list(){


        ArrayList<img_cell> img_list = new ArrayList<img_cell>();

        for (int i=0 ; i < imge_title.length ; i++){

            img_cell tem_img_cell = new img_cell();
            tem_img_cell.setTitle(imge_title[i]);
            tem_img_cell.setImg(imge[i]);
            img_list.add(tem_img_cell);

        }


        return img_list;


    }
}
