package com.thedeveloperworldisyours.mubalooorganisation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thedeveloperworldisyours.mubalooorganisation.R;
import com.thedeveloperworldisyours.mubalooorganisation.models.DummyItem;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by javiergonzalezcabezas on 3/4/15.
 */
public class EntryAdapter extends BaseAdapter {
    private Map<String, DummyItem> mItems;
    private Context mContext;
    private LayoutInflater vi;



    public EntryAdapter(Context context, Map<String, DummyItem> items) {
        this.mItems = items;
        this.mContext=context;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            final DummyItem dummyItem = (DummyItem) mItems.get(Integer.toString(position));

            if (dummyItem != null) {
                if(dummyItem.isSection()){

                    v = vi.inflate(R.layout.fragment_setion_list, null);
                    v.setOnClickListener(null);
                    v.setOnLongClickListener(null);
                    v.setLongClickable(false);
                    final TextView sectionView =
                            (TextView) v.findViewById(R.id.list_item_section_text);
                    sectionView.setText(dummyItem.getName());
                } else {
                    v = vi.inflate(R.layout.fragment_item_list, null);
                    final TextView name =
                            (TextView)v.findViewById(R.id.fragment_item_detail_name_textView);
                    name.setText(dummyItem.getName());
                    final  TextView role = (TextView) v.findViewById(R.id.fragment_item_detail_role_textView);
                    role.setText(dummyItem.getRole());
                    final ImageView imageView = (ImageView) v.findViewById(R.id.fragment_item_detail_imageView);
                    Picasso.with(mContext).load(dummyItem.getProfileImageURL()).into(imageView);
                }
            }
            return v;
        }
    }
