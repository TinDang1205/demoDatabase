package demodatabase.jp.demodatabase.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SunnyPoint on 8/8/2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "demo";
    public static final int DB_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //TODO Cách viết thứ 1
        //Viết câu lênh tạo bản bằng Chuỗi String
        String student = "CREATE TABLE student(" + "ID INTEGER PRIMARY KEY AUTOINCREMENT," + "NAME TEXT," + "GENDER TEXT NOT NULL,"+"PICTURE TEXT)";
        sqLiteDatabase.execSQL(student);
        //thực hiện câu lên ở phía trên
        String classes = "CREATE TABLE classes(" + "CLASSID INTEGER PRIMARY KEY AUTOINCREMENT," + "CLASSNAME TEXT," + "STUDENTID INTEGER NOT NULL REFERENCES student(ID))";
        sqLiteDatabase.execSQL(classes);

        //TODO Cách viết thứ 2
//        sqLiteDatabase.execSQL("CREATE TABLE student(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,GENDER TEXT NOT NULL,PICTURE TEXT);");
//        sqLiteDatabase.execSQL("CREATE TABLE classes(CLASSID INTEGER PRIMARY KEY,CLASSNAME TEXT,STUDENTID INTEGER NOT NULL REFERENCES student(ID));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //TODO Cách viết thứ 1
        //Viết câu lênh tạo bản bằng Chuỗi String
        String student_dropTable = "DROP TABLE IF EXISTS student";
        //thực hiện câu lên ở phía trên
        sqLiteDatabase.execSQL(student_dropTable);

        String classes_dropTable = "DROP TABLE IF EXISTS classes";
        sqLiteDatabase.execSQL(classes_dropTable);

        //TODO Cách viết thứ 2
        //sqLiteDatabase.execSQL("DRO TABLE IF EXISTS student");
       //sqLiteDatabase.execSQL("DRO TABLE IF EXISTS classes");

        onCreate(sqLiteDatabase);
    }
}
