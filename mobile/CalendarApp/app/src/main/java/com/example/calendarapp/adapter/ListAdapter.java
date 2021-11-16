package com.example.calendarapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calendarapp.R;
import com.google.api.services.calendar.model.Event;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    private List<Event> items = new ArrayList<>();

    private TextView tvTime;

    private TextView tvTitle;

    private ImageView imageType;

    private Context context;

    public void clear() {

        items.clear();
    }

    public void addItem(Event item) {

        items.add(item);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        context = viewGroup.getContext();

        if (view == null) {

            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.list_item, viewGroup, false);
        }

        tvTime = view.findViewById(R.id.tvTime);
        tvTitle = view.findViewById(R.id.tvTitle);
        imageType = view.findViewById(R.id.imageType);

        Event item = items.get(i);

        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTimeInMillis(item.getStart().getDateTime().getValue());
        CalendarDay eventDay = CalendarDay.from(calendar);

        int hour = calendar.get(java.util.Calendar.HOUR);
        int minute = calendar.get(java.util.Calendar.MINUTE);

        tvTime.setText(String.valueOf(hour) + ":" + String.valueOf(minute));
        String[] title = item.getSummary().split("/");
        tvTitle.setText(title[1]);

        if (title[0].equals("0")) {

            imageType.setImageResource(R.drawable.assignment);
        } else if (title[1].equals("1")) {

            imageType.setImageResource(R.drawable.meeting);
        }

        return view;
    }
}
