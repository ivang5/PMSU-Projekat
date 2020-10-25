package com.example.pmsu_projekat.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pmsu_projekat.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Email;

public class FoldersAdapter extends BaseAdapter {
    private Activity activity;
    private List<Email> dataList;
    private String messageStart;

    private static String pattern = "dd-MM-yyyy HH:mm:ss";

    @SuppressLint("SimpleDateFormat")
    public static String formatDate(Date date) {
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        return formater.format(date);
    }

    public FoldersAdapter(Activity activity, List<Email> aData) {
        this.activity = activity;
        this.dataList = aData;
        //add(aData);
    }

    @Override
    public int getCount() {
        if(dataList != null)
            return dataList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add (List<Email> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        Email email = dataList.get(position);

        if(convertView == null)
            vi = activity.getLayoutInflater().inflate(R.layout.emails_list_item, null);

        TextView from = (TextView)vi.findViewById(R.id.from);
        TextView date = (TextView)vi.findViewById(R.id.date);
        TextView title = (TextView)vi.findViewById(R.id.title);
        TextView message = (TextView)vi.findViewById(R.id.message);
        ImageView image = (ImageView)vi.findViewById(R.id.contact_icon);

        from.setText(email.getFrom().getName());
        date.setText(email.getDate());

        String mess = email.getMessage();
        if (mess.length() > 40) {
            messageStart = mess.substring(0, 40) + "...";
        }else
            messageStart = mess;
        if (email.getUnread()) {
            title.setTypeface(null, Typeface.BOLD_ITALIC);
            message.setTypeface(null, Typeface.BOLD_ITALIC);
            title.setText(email.getTitle());
            message.setText(messageStart);
        }else
            title.setText(email.getTitle());
            message.setText(messageStart);

//        if (email.getAvatar().getPath() != null){
//            image.setImageResource(email.getAvatar().getId());
//        }else {
//            image.setImageResource(R.drawable.ic_account_circle_black_24dp);
//        }
        image.setImageResource(R.drawable.ic_account_circle_black_24dp);

        return  vi;
    }

}