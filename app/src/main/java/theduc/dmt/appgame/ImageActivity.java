package theduc.dmt.appgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;

public class ImageActivity extends AppCompatActivity {

    GridView gvImage;
    ListImageAdapter adapter;
    ArrayList<Image> listImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Mapping();
        Collections.shuffle(listImage);
        adapter = new ListImageAdapter(this,R.layout.activity_item_grid,listImage);
        gvImage.setAdapter(adapter);

        gvImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent toGamePlay = new Intent();
                toGamePlay.putExtra("EXTRA_ID_IMAGE", listImage.get(i).getIdImage());
                setResult(RESULT_OK,toGamePlay);
                finish();

            }
        });

    }

    private void Mapping(){

        gvImage = findViewById(R.id.gv_list_image);
        listImage = new ArrayList<>();

        listImage.add(new Image(R.drawable.anh1,"anh1"));
        listImage.add(new Image(R.drawable.anh2,"anh2"));
        listImage.add(new Image(R.drawable.anh3,"anh3"));
        listImage.add(new Image(R.drawable.anh4,"anh4"));
        listImage.add(new Image(R.drawable.anh5,"anh5"));
        listImage.add(new Image(R.drawable.anh6,"anh6"));
        listImage.add(new Image(R.drawable.anh7,"anh7"));
        listImage.add(new Image(R.drawable.anh8,"anh8"));
        listImage.add(new Image(R.drawable.anh9,"anh9"));
        listImage.add(new Image(R.drawable.anh10,"anh10"));
        listImage.add(new Image(R.drawable.anh11,"anh11"));
        listImage.add(new Image(R.drawable.anh12,"anh12"));

    }
}