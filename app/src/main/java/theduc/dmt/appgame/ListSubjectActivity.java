package theduc.dmt.appgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ListSubjectActivity extends AppCompatActivity {
    ArrayList<String> listSubject;
    ListView lvSubject;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_subject);

        lvSubject = findViewById(R.id.lv_subject);

        String arrayName[] = getResources().getStringArray(R.array.array_monhoc);
        listSubject = new ArrayList<>(Arrays.asList(arrayName));

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listSubject);

        lvSubject.setAdapter(adapter);

        // Bắt sự kiện hỏi trước khi xóa

        lvSubject.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(
                    AdapterView<?> adapterView,
                    View view, int i, long l) {

                showDialog1(i);

                return false;
            }
        });

    }
    private void showDialog1 (int position) {
        AlertDialog.Builder alertDialog
                = new AlertDialog.Builder(ListSubjectActivity.this);

        alertDialog.setTitle("Thông báo!");
        alertDialog.setIcon(R.drawable.ic_load);
        alertDialog.setMessage("Bạn có chắc chắn muốn xóa môn học này không?");
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listSubject.remove(position);
                adapter.notifyDataSetChanged();
                Log.d("abc",i+"CO");
            }
        });

        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("abc",i+"KHONG");
            }
        });

        alertDialog.show();
    }
}