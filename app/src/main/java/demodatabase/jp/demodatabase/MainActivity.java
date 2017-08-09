package demodatabase.jp.demodatabase;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import demodatabase.jp.demodatabase.adapter.ListViewAdapter;
import demodatabase.jp.demodatabase.data.ClassesDAO;
import demodatabase.jp.demodatabase.data.StudentDAO;
import demodatabase.jp.demodatabase.entity.Student;

public class MainActivity extends AppCompatActivity {
    private StudentDAO studentDAO;
    private ClassesDAO classesDAO;
    private ListView lvAll;
    private ListViewAdapter listViewAdapter;
    private List<Student> students;
    private Button btnAddStudentMain;
    private Button btnAddClassMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvAll = (ListView) findViewById(R.id.lvAll);
        btnAddStudentMain = (Button) findViewById(R.id.btnAddStudentMain);
        btnAddClassMain = (Button) findViewById(R.id.btnAddClassMain);
        studentDAO = new StudentDAO(this);
        classesDAO = new ClassesDAO(this);
        students = studentDAO.getAllStudent();
        listViewAdapter = new ListViewAdapter(getBaseContext(), students);
        lvAll.setAdapter(listViewAdapter);
        setClickListView();
        btnAddStudentMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.view_add_update_student);

                final EditText edtStudentName = (EditText) dialog.findViewById(R.id.edtStudentName);
                final EditText edtStudentGender = (EditText) dialog.findViewById(R.id.edtStudentGender);
                final EditText edtStudentPicture = (EditText) dialog.findViewById(R.id.edtStudentPicture);
                Button btnAddStudent = (Button) dialog.findViewById(R.id.btnAddStudent);
                btnAddStudent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Student student = new Student();
                        student.setName(edtStudentName.getText().toString());
                        student.setGender(edtStudentGender.getText().toString());
                        student.setPicture(edtStudentPicture.getText().toString());
                        try {
                            studentDAO.insetDataStudent(student);
                            Toast.makeText(getApplicationContext(), "Add Student Success", Toast.LENGTH_SHORT).show();
                            students=studentDAO.getAllStudent();
                            listViewAdapter.setListData(students);
                            dialog.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Add Student Fall", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.show();
            }
        });
    }

    public void setClickListView() {
        lvAll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.view_add_update_student);
                final EditText edtStudentID = (EditText) dialog.findViewById(R.id.edtStudentID);
                final EditText edtStudentName = (EditText) dialog.findViewById(R.id.edtStudentName);
                final EditText edtStudentGender = (EditText) dialog.findViewById(R.id.edtStudentGender);
                final EditText edtStudentPicture = (EditText) dialog.findViewById(R.id.edtStudentPicture);
                Button btnAddStudent = (Button) dialog.findViewById(R.id.btnAddStudent);
                Button btnUpdateStudent = (Button) dialog.findViewById(R.id.btnUpdateStudent);
                Button btnDeleteStudent = (Button) dialog.findViewById(R.id.btnDeleteStudent);

                //hiện view
                btnAddStudent.setVisibility(View.GONE);
                btnUpdateStudent.setVisibility(View.VISIBLE);
                btnDeleteStudent.setVisibility(View.VISIBLE);
                edtStudentID.setVisibility(View.VISIBLE);
                edtStudentID.setEnabled(false);
                //set dữ liệu lên view

                edtStudentID.setText(String.valueOf(students.get(i).getId()));
                edtStudentName.setText(students.get(i).getName());
                edtStudentGender.setText(students.get(i).getGender());
                edtStudentPicture.setText(students.get(i).getPicture());

                btnUpdateStudent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Student student = new Student();
                        student.setId(Integer.parseInt(edtStudentID.getText().toString()));
                        student.setName(edtStudentName.getText().toString());
                        student.setGender(edtStudentGender.getText().toString());
                        student.setPicture(edtStudentPicture.getText().toString());

                        try {
                            studentDAO.updateDataStudent(student);
                            students=studentDAO.getAllStudent();
                            listViewAdapter.setListData(students);
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Update Student Success", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Update Student Fall", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            studentDAO.deleteDataStudent(Integer.parseInt(edtStudentID.getText().toString()));
                            students=studentDAO.getAllStudent();
                            listViewAdapter.setListData(students);
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Delete Student Success", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Delete Student Fall", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.show();
            }
        });

    }
}
