package com.example.hocandroid;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hocandroid.Student;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtId, edtName, edtAge, edtGpa;
    private Button btnAdd, btnDelete;
    private TextView txtList;

    private ArrayList<Student> danhSachSinhVien = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtGpa = findViewById(R.id.edtGpa);

        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);

        txtList = findViewById(R.id.txtList);
        btnAdd.setOnClickListener(v -> themSinhVien());
        btnDelete.setOnClickListener(v -> xoaDanhSach());
    }
    private void themSinhVien() {

        String id = edtId.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        String ageText = edtAge.getText().toString().trim();
        String gpaText = edtGpa.getText().toString().trim();
        if (id.isEmpty() || name.isEmpty()
                || ageText.isEmpty() || gpaText.isEmpty()) {

            Toast.makeText(this,
                    "Vui lòng nhập đầy đủ thông tin!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int age;
        double gpa;

        try {
            age = Integer.parseInt(ageText);
            gpa = Double.parseDouble(gpaText);
        } catch (NumberFormatException e) {
            Toast.makeText(this,
                    "Tuổi hoặc GPA không hợp lệ!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (age <= 0) {
            Toast.makeText(this,
                    "Tuổi phải lớn hơn 0!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (gpa < 0 || gpa > 10) {
            Toast.makeText(this,
                    "GPA phải từ 0 đến 10!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Student student = new Student(id, name, age, gpa);

        danhSachSinhVien.add(student);

        hienThiDanhSach();

        edtId.setText("");
        edtName.setText("");
        edtAge.setText("");
        edtGpa.setText("");

        Toast.makeText(this,
                "Thêm sinh viên thành công!",
                Toast.LENGTH_SHORT).show();
    }

    private void hienThiDanhSach() {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < danhSachSinhVien.size(); i++) {

            Student student = danhSachSinhVien.get(i);

            result.append("Sinh viên ").append(i + 1).append("\n");
            result.append(student.displayInfo());
            result.append("\n-------------------------\n");
        }

        txtList.setText(result.toString());
    }
    private void xoaDanhSach() {

        danhSachSinhVien.clear();

        txtList.setText("");

        Toast.makeText(this,
                "Đã xóa danh sách!",
                Toast.LENGTH_SHORT).show();
    }
}