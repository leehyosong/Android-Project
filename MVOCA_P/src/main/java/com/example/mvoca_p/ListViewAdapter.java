package com.example.mvoca_p;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ListViewAdapter extends BaseAdapter {

    ArrayList<ListViewItem> listItem = new ArrayList<>();

    public ListViewAdapter() {
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

    @Override
    public Object getItem(int position) {
        return listItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listviewitem, parent, false);

        }

        ImageView icon = (ImageView) convertView.findViewById(R.id.imageView);
        TextView title = (TextView) convertView.findViewById(R.id.mtitle);
        TextView singer = (TextView) convertView.findViewById(R.id.msinger);

        ListViewItem lsitviewitem = (ListViewItem) listItem.get(position);

        icon.setImageDrawable(lsitviewitem.getIcon());
        title.setText(lsitviewitem.getTitle());
        singer.setText(lsitviewitem.getSinger());

        return convertView;
    }

    public void addItem(Drawable icon, String title, String singer) {
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setSinger(singer);

        listItem.add(item);
    }
}
