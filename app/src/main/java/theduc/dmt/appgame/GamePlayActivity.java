package theduc.dmt.appgame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GamePlayActivity extends AppCompatActivity {
    public int idImage;
    public int Point = 100;
    private ImageView ivGoc, ivNhan;
    private TextView tvPoint;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        ivGoc = findViewById(R.id.iv_image_one);
        ivNhan = findViewById(R.id.iv_image_two);
        tvPoint = findViewById(R.id.tv_point);

        sharedPreferences = getSharedPreferences("PREF_POINT",MODE_PRIVATE);
        Point = sharedPreferences.getInt("point",100);
        tvPoint.setText(Point + "");


        String name[] = getResources().getStringArray(R.array.array_name);
        Util.listName = new ArrayList<>(Arrays.asList(name));
        Collections.shuffle( Util.listName );
        String nameImage = Util.listName.get(5);

        idImage = getResources().getIdentifier ( nameImage,"drawable",getPackageName() );
        ivGoc.setImageResource(idImage);

        ivNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toImageActivity =
                                new Intent(GamePlayActivity.this,ImageActivity.class);
                startActivityForResult(toImageActivity,Util.REQUEST_CODE);
            }
        });
    }

    // Tạo menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_load,menu);
        return super.onCreateOptionsMenu(menu);

    }

    // Sự kiện CLick vào menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_load:

                ReloadImage();

        }
        return super.onOptionsItemSelected(item);
    }
    // Lấy kết quả trả về từ việc chọn ảnh và so sánh
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        // KIỂM TRA ĐỂ CHẮC CHẮN ĐÓ LÀ DỮ LIỆU ĐÚNG
        
        if ( requestCode == Util.REQUEST_CODE && resultCode == RESULT_OK && data != null ) {

            int idImgClick = data.getIntExtra("EXTRA_ID_IMAGE", 0);
            ivNhan.setImageResource(idImgClick);

            if (idImgClick == idImage) {
                Toast.makeText(this, "Đúng rồi \n Bạn được cộng 10đ", Toast.LENGTH_SHORT).show();
                Point += 10;
                LoadPoint();

                CountDownTimer countDownTimer = new CountDownTimer(2000,100) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        ReloadImage();
                    }
                }.start();

            } else {

                Toast.makeText(this, "Sai rồi \n Bạn bị trừ 5đ", Toast.LENGTH_SHORT).show();
                Point -= 5;
                LoadPoint();

            }

        }
        
        // KIỂM TRA NẾU KHÔNG CHỌN ẢNH
        
        if (requestCode == Util.REQUEST_CODE && resultCode == RESULT_CANCELED ) {

            Toast.makeText(this, "Bạn chưa chọn ảnh?? \n Bạn bị trừ 15đ", Toast.LENGTH_SHORT).show();
            Point -= 15;
            LoadPoint();

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    // RELOAD ảnh hiện tại

    private void ReloadImage(){
        Collections.shuffle(Util.listName);
        String nameImage = Util.listName.get(5);

        idImage =
                getResources().getIdentifier(nameImage,"drawable",getPackageName());
        ivGoc.setImageResource(idImage);
    }

    private void LoadPoint() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("point",Point);
        editor.commit();
        Point = sharedPreferences.getInt("point",100);
        tvPoint.setText(Point + "");
    }
}