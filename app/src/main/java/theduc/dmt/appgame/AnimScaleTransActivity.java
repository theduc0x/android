package theduc.dmt.appgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimScaleTransActivity extends AppCompatActivity {

    ImageView ivScale,ivTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_scale_trans);

        ivScale = findViewById(R.id.iv_scale);
        ivTrans = findViewById(R.id.iv_translate);


        // AnimationUtils là class có các tiện ảnh để làm việc với ảnh động
        Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        Animation animTrans = AnimationUtils.loadAnimation(this, R.anim.anim_translate);

        ivScale.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animScale);
            }
        });

        ivTrans.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTrans);
            }
        });
    }
}