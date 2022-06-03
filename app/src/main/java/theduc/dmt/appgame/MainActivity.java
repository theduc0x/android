package theduc.dmt.appgame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ImageView ivClick;
    private EditText edtStart, edtEnd;
    private Button btCount;
    private TextView tvDisplay;
    public Calendar calendarOne, calendarTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivClick = findViewById(R.id.iv_game);

        ivClick.setEnabled(false);


        ivClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ivClick.isEnabled()) {
                    Intent itGame = new Intent(MainActivity.this, GamePlayActivity.class);
                    startActivity(itGame);
                }
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (Util.TAG == 0) {
            ivClick.setEnabled(false);
        } else {
            ivClick.setEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_info, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.mn_info:
                Intent toLogin = new Intent(this, LoginActivity.class);
                startActivity(toLogin);
                overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
                break;

            case R.id.mn_subject:
                Intent toSubject = new Intent(this, ListSubjectActivity.class);
                startActivity(toSubject);
                break;

            case R.id.mn_calendar:
                showDialogDays();
                break;

            case R.id.mn_anim:
                Intent toAnim = new Intent(this, AnimationAlphaActivity.class);
                startActivity(toAnim);
                break;

            case R.id.mn_anim2:
                Intent toAnim2 = new Intent(this, AnimScaleTransActivity.class);
                startActivity(toAnim2);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    // Show màn hình dialog để tính khoảng cách 2 ngày
    private void showDialogDays() {

        Dialog dlCalendar = new Dialog(this);
        dlCalendar.setContentView(R.layout.dialog_days);


        edtStart = dlCalendar.findViewById(R.id.edt_start);
        edtEnd = dlCalendar.findViewById(R.id.edt_end);
        btCount = dlCalendar.findViewById(R.id.bt_count);
        tvDisplay = dlCalendar.findViewById(R.id.tv_hienthi);

        edtStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startDate();
            }

        });

        edtEnd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showSelectEndDate();
            }

        });

        btCount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int a = (int) (calendarOne.getTimeInMillis() / (1000 * 60 * 60 * 24));
                int b = (int) (calendarTwo.getTimeInMillis() / (1000 * 60 * 60 * 24));

                tvDisplay.setText("Số ngày xa cách là: " + (b - a));

            }
        });

        dlCalendar.show();

    }

    // Nhập ngày 1
    private void startDate() {

        calendarOne = Calendar.getInstance();

        int date = calendarOne.get(Calendar.DATE);
        int month = calendarOne.get(Calendar.MONTH);
        int year = calendarOne.get(Calendar.YEAR);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");

        DatePickerDialog dateOne =
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarOne.set(i, i1, i2);
                        edtStart.setText(simpleDateFormat.format(calendarOne.getTime()) + "");
                    }
                }, year, month, date);

        dateOne.show();
    }

    //Nhập ngày 2
    private void showSelectEndDate() {

        calendarTwo = Calendar.getInstance();

        int date = calendarTwo.get(Calendar.DATE);
        int month = calendarTwo.get(Calendar.MONTH);
        int year = calendarTwo.get(Calendar.YEAR);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");

        DatePickerDialog dateTwo =
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        calendarTwo.set(i, i1, i2);
                        edtEnd.setText(simpleDateFormat.format(calendarTwo.getTime()) + "");
                    }
                }, year, month, date);

        dateTwo.show();
    }
}