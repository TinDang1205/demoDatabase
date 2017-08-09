package demodatabase.jp.demodatabase.data;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import demodatabase.jp.demodatabase.entity.Student;

/**
 * Created by SunnyPoint on 8/8/2017.
 */

public class StudentDAO {
    SQLiteDatabase sqLiteDatabase;

    public StudentDAO(Activity activity) {
        sqLiteDatabase = new DbHelper(activity).getWritableDatabase();
    }

    public void insetDataStudent(Student student) {
        //Dùng đối tượng ContentValues để đưa dữ liệu vào bảng. Đối tượng này có các phương thức put (tên cột , dữ liệu)
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", student.getName());
        contentValues.put("GENDER", student.getGender());
        contentValues.put("PICTURE", student.getPicture());
        sqLiteDatabase.insert("student", null, contentValues);
    }

    public void updateDataStudent(Student student) {
        //Dùng đối tượng ContentValues để đưa dữ liệu vào bảng. Đối tượng này có các phương thức put (tên cột , dữ liệu)
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", student.getName());
        contentValues.put("GENDER", student.getGender());
        contentValues.put("PICTURE", student.getPicture());
        //where clause :tùy chọn để áp dụng khi cập nhật. Passing null sẽ cập nhật tất cả các hàng.
        //where Args là một mảng String theo giá trị
        sqLiteDatabase.update("student", contentValues, "ID=?", new String[]{String.valueOf(student.getId())});
    }

    public void deleteDataStudent(int id) {
        sqLiteDatabase.delete("student", "ID=?", new String[]{String.valueOf(id)});
    }


    public Student getStudentById(String id) {
        String query = "SELECT * FROM student WHERE ID=?";
        List<Student> students = getBySql(query, id);
        return students.get(0);
    }

    public List<Student> getAllStudent() {
        String query = "SELECT * FROM student";
        return getBySql(query);
    }


    // đọc dữ liệu dưới database
    public List<Student> getBySql(String sql, String... args) {
        List<Student> list = new ArrayList<>();
        //cursor con trỏ duyệt qua từng dòng trong database
        Cursor cursor = sqLiteDatabase.rawQuery(sql, args);
        while (cursor.moveToNext()) {
            Student student = new Student();
            student.setId(cursor.getInt(cursor.getColumnIndex("ID")));
            student.setName(cursor.getString(cursor.getColumnIndex("NAME")));
            student.setGender(cursor.getString(cursor.getColumnIndex("GENDER")));
            student.setPicture(cursor.getString(cursor.getColumnIndex("PICTURE")));
            list.add(student);
        }
        cursor.close();
        return list;
    }


}
