package com.example.pmsu_projekat.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pmsu_projekat.R;

import java.util.List;

import model.Contact;

public class ContactAdapter extends BaseAdapter {
    private Activity activity;
    private List<Contact> dataList;

    public ContactAdapter(Activity activity, List<Contact> aData){
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

    public void add (List<Contact> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View vi = convertView;
        Contact contact = dataList.get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.contacts_list_item, null);

        TextView name = (TextView)vi.findViewById(R.id.name);
        ImageView image = (ImageView)vi.findViewById(R.id.contacts_icon);

        name.setText(contact.getName());

//        if(contact.getAvatar().getPath() != null) {
//            image.setImageResource(contact.getAvatar().getId());
//        }else
//            image.setImageResource(R.drawable.ic_account_circle_black_24dp);
        image.setImageResource(R.drawable.ic_account_circle_black_24dp);

        return vi;

    }

}
