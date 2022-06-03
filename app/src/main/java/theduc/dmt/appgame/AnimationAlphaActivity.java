package theduc.dmt.appgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimationAlphaActivity extends AppCompatActivity {
    ImageView ivAlpha, ivRotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_alpha);

        ivAlpha = findViewById(R.id.iv_alpha);
        ivRotate = findViewById(R.id.iv_rotate);

        // AnimationUtils là class có các tiện ảnh để làm việc với ảnh động
        Animation animAlpha
                = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);
        Animation animRotate
                = AnimationUtils.loadAnimation(this,R.anim.anim_rotate);

        ivAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animAlpha);
            }
        });

        ivRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animRotate);
            }
        });
    }
}