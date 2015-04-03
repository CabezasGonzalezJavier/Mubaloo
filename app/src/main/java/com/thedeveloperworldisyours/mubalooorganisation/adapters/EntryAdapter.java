package com.thedeveloperworldisyours.mubalooorganisation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thedeveloperworldisyours.mubalooorganisation.R;
import com.thedeveloperworldisyours.mubalooorganisation.interfaces.Item;
import com.thedeveloperworldisyours.mubalooorganisation.models.DummyItem;
import com.thedeveloperworldisyours.mubalooorganisation.models.SectionItem;

import java.util.ArrayList;

/**
 * Created by javiergonzalezcabezas on 3/4/15.
 */
public class EntryAdapter extends ArrayAdapter<Item> {
        private ArrayList mItems;
    private Context mContext;
        private LayoutInflater vi;



    public EntryAdapter(Context context, ArrayList items) {
        super(context,0, items);
        this.mItems = items;
        this.mContext=context;
        vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            final Item i = (Item) mItems.get(position);

            if (i != null) {
                if(i.isSection()){
                    SectionItem si = (SectionItem)i;
                    v = vi.inflate(R.layout.fragment_setion_list, null);
                    v.setOnClickListener(null);
                    v.setOnLongClickListener(null);
                    v.setLongClickable(false);
                    final TextView sectionView =
                            (TextView) v.findViewById(R.id.list_item_section_text);
                    sectionView.setText(si.getTitle());
                } else {
                    DummyItem dummyItem = (DummyItem)i;
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
