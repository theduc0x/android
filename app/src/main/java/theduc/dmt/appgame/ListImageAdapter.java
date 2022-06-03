package theduc.dmt.appgame;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListImageAdapter extends ArrayAdapter<Image> {
    private Activity context;
    private int idLayout;
    private ArrayList<Image> listImage;


    public ListImageAdapter(Activity context, int idLayout, ArrayList<Image> listImage) {
        super(context, idLayout, listImage);
        this.context = context;
        this.idLayout = idLayout;
        this.listImage = listImage;
    }

    @NonNull
    @Override
    public View getView ( int position, @Nullable View convertView, @NonNull ViewGroup parent ) {

        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idLayout,null);

        Image image = listImage.get(position);

        // Mapping
        ImageView imageView = convertView.findViewById(R.id.iv_image);
        imageView.setImageResource(image.getIdImage());

        Animation animation = AnimationUtils.loadAnimation(context,R.anim.anim_enter);
        convertView.startAnimation(animation);

        return convertView;
    }
}
