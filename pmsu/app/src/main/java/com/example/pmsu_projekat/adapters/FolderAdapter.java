package com.example.pmsu_projekat.adapters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pmsu_projekat.R;

import java.util.List;

import model.Folder;

public class FolderAdapter extends BaseAdapter {
    private Activity activity;
    private List<Folder> dataList;

    public FolderAdapter(Activity activity, List<Folder> aData) {
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

    public void add (List<Folder> data) {
        dataList.clear();
        dataList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        Folder folder = dataList.get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.folders_list_item, null);

        TextView name = (TextView)vi.findViewById(R.id.folder_name);
        TextView emailsNumber = (TextView)vi.findViewById(R.id.emails_number);
        ImageView image = (ImageView)vi.findViewById(R.id.folder_icon);

        name.setText(folder.getName());


//        if (folder.getAvatar().getPath() != null){
//            image.setImageResource(folder.getAvatar().getId());
//        }else{
//            image.setImageResource(R.drawable.ic_folder_open_black_24dp);
//        }
        image.setImageResource(R.drawable.ic_folder_open_black_24dp);

        return  vi;
    }
}
