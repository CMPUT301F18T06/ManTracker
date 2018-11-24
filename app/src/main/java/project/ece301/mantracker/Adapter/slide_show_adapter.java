package project.ece301.mantracker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import project.ece301.mantracker.Activity.clickedimg;
import project.ece301.mantracker.File.img_cell;
import project.ece301.mantracker.R;

public class slide_show_adapter extends RecyclerView.Adapter<slide_show_adapter.ViewHolder> {

    private ArrayList<img_cell> img_cell_lsit;
    private Context context;

    public  slide_show_adapter(Context context , ArrayList<img_cell> img_cell_list){

        this.context = context;
        this.img_cell_lsit = img_cell_list;



    }



    @NonNull
    @Override
    public slide_show_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewgroup, int i) {

        View view = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.img_cell , viewgroup,false);


        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int i) {
        viewholder.title.setText(img_cell_lsit.get(i).getTitle());
        viewholder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewholder.img.setImageResource(img_cell_lsit.get(i).getImg());
        viewholder.img.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
//                Toast.makeText(context,img_cell_lsit.get(i).getTitle(),Toast.LENGTH_SHORT).show();
//
                Intent gotoclickedimg = new Intent(context, clickedimg.class);
                gotoclickedimg.putExtra("imgid",img_cell_lsit.get(i).getImg().toString());
                gotoclickedimg.putExtra("imgtitle",img_cell_lsit.get(i).getTitle());
                context.startActivity(gotoclickedimg);

            }
        });

    }

    @Override
    public int getItemCount() {
        return img_cell_lsit.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView img;
        private ViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.slide_show_title);
            img = (ImageView) view.findViewById(R.id.slide_show_img);

        }
    }
}
