package demodatabase.jp.demodatabase.data;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import demodatabase.jp.demodatabase.entity.Classes;

/**
 * Created by SunnyPoint on 8/8/2017.
 */

public class ClassesDAO {
    SQLiteDatabase sqLiteDatabase;

    public ClassesDAO(Activity activity) {
        sqLiteDatabase = new DbHelper(activity).getWritableDatabase();
    }

    public void insetDataClass(Classes classes) {
        //Dùng đối tượng ContentValues để đưa dữ liệu vào bảng. Đối tượng này có các phương thức put (tên cột , dữ liệu)
        ContentValues contentValues = new ContentValues();
        contentValues.put("CLASSNAME", classes.getClassName());
        contentValues.put("STUDENTID", classes.getStudentID());
        sqLiteDatabase.insert("classes", null, contentValues);
    }


    public void updateDataClass(Classes classes) {
        //Dùng đối tượng ContentValues để đưa dữ liệu vào bảng. Đối tượng này có các phương thức put (tên cột , dữ liệu)
        ContentValues contentValues = new ContentValues();
        contentValues.put("CLASSNAME", classes.getClassName());
        contentValues.put("STUDENTID", classes.getStudentID());
        //where clause :tùy chọn để áp dụng khi cập nhật. Passing null sẽ cập nhật tất cả các hàng.
        //where Args là một mảng String theo giá trị
        sqLiteDatabase.update("classes", contentValues, "CLASSID=?", new String[]{String.valueOf(classes.getClassID())});
    }

    public void deleteDataClass(int id) {
        sqLiteDatabase.delete("classes", "CLASSID=?", new String[]{String.valueOf(id)});
    }


    public Classes getClassById(String id) {
        String query = "SELECT * FROM classes WHERE ID=?";
        List<Classes> classes = getBySql(query, id);
        return classes.get(0);
    }

    public List<Classes> getAllClass() {
        String query = "SELECT * FROM classes";
        return getBySql(query);
    }


    // đọc dữ liệu dưới database
    public List<Classes> getBySql(String sql, String... args) {
        List<Classes> list = new ArrayList<>();
        //cursor con trỏ duyệt qua từng dòng trong database
        Cursor cursor = sqLiteDatabase.rawQuery(sql, args);
        while (cursor.moveToNext()) {
            Classes classes = new Classes();
            classes.setClassID(cursor.getInt(cursor.getColumnIndex("CLASSID")));
            classes.setClassName(cursor.getString(cursor.getColumnIndex("CLASSNAME")));
            classes.setStudentID(cursor.getInt(cursor.getColumnIndex("STUDENTID")));
            list.add(classes);
        }
        cursor.close();
        return list;
    }


}
