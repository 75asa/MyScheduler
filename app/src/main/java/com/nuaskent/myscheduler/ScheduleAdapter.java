package com.nuaskent.myscheduler;

import android.icu.text.SimpleDateFormat;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by nasa on 2018/02/24.
 */

public class ScheduleAdapter extends RealmBaseAdapter<Schedule> {

    private static class ViewHolder {
        TextView date;
        TextView title;
    }

    public ScheduleAdapter(@Nullable OrderedRealmCollection<Schedule> data) {
        super(data);
    }

    // ListViewのセルデータが必要になる度に呼び出され、表示するビューを戻り値とする
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        // ListViewのセルは使い回す為にNull時も考慮
        // e.g. 画面に６行表示できるリストビューがあったとすると、最初に６行文のセル用Viewを用意する為
        // getViewメソッドが第二引数nullで計６回呼ばれる　⇨　画面がスクロールし先頭の１行目が画面枠外に出た場合は？
        // A. 画面から出た行は不要となり、新たに７行目が必要になる、この際先ほどの１行目のセルを７行目に充当する
        // 第二引数には１行目のセルがセットされる
        if (convertView == null) {
            // XMLからViewを作成するLayoutInflater
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    android.R.layout.simple_list_item_2, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.date =
                    (TextView) convertView.findViewById(android.R.id.text1);
            viewHolder.title =
                    (TextView) convertView.findViewById(android.R.id.text2);
            // タグ付けを行い使い回す効率を良くする
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Schedule schedule = adapterData.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String formatDate = sdf.format(schedule.getDate());
        viewHolder.date.setText(formatDate);
        viewHolder.title.setText(schedule.getTitle());
        return convertView;
    }
}
