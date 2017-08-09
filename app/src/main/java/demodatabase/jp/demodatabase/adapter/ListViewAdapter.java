package demodatabase.jp.demodatabase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import demodatabase.jp.demodatabase.R;
import demodatabase.jp.demodatabase.entity.Student;

/**
 * Created by SunnyPoint on 8/9/2017.
 */

public class ListViewAdapter extends BaseAdapter {
    private List<Student> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListViewAdapter(Context context, List<Student> listData) {
        this.context = context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setListData(List<Student> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_list_view, null);
            holder = new ViewHolder();
            holder.imgStudent = (ImageView) convertView.findViewById(R.id.imgStudent);
            holder.txtName = (TextView) convertView.findViewById(R.id.txtName);
            holder.txtGender = (TextView) convertView.findViewById(R.id.txtGender);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Student student = this.listData.get(i);
        holder.txtName.setText(student.getName());
        holder.txtGender.setText(student.getGender());
        holder.imgStudent.setImageResource(context.getResources().getIdentifier(student.getPicture(), "drawable", context.getPackageName()));
        return convertView;
    }

    static class ViewHolder {
        ImageView imgStudent;
        TextView txtName;
        TextView txtGender;
    }
}
