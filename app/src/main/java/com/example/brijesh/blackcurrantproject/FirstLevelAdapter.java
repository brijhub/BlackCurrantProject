package com.example.brijesh.blackcurrantproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Brijesh on 3/19/2017.
 */

public class FirstLevelAdapter extends RecyclerView.Adapter<FirstLevelAdapter.MyViewHolder> implements DataPassListener{

    private List<ListPozo> list;
    private Context context;
    private Context activity;
    private LayoutInflater inflator;
    public Picasso picasso;
    FirstFragment firstFragment;

    @Override
    public void passData(String data) {
        SecondFragment fragmentB = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(SecondFragment.DATA_RECEIVE, data);
        fragmentB.setArguments(args);
        firstFragment.getFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, fragmentB)
                .commit();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title,information,id;
        public ImageView pics;
        public MyViewHolder(View view) {
            super(view);
            pics=(ImageView) view.findViewById(R.id.image1);
            title = (TextView) view.findViewById(R.id.name);
            information = (TextView) view.findViewById(R.id.description);
            id = (TextView) view.findViewById(R.id.others);

        }
    }


    public FirstLevelAdapter(Context context, List<ListPozo> list, FirstFragment mn) {
        this.activity=context;
        inflator=LayoutInflater.from(context);
        this.list = list;
        picasso=Picasso.with(context);
        this.firstFragment=mn;
    }

    public FirstLevelAdapter(Context context, List<ListPozo> list) {
        this.activity=context;
        inflator=LayoutInflater.from(context);
        this.list = list;
        picasso=Picasso.with(context);
      //  this.firstFragment=mn;
    }

    public FirstLevelAdapter()
    {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.firstlevelcard, parent, false);
       /* final MyViewHolder holder=new MyViewHolder(itemView);
       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context,SecondLevel.class);
                context.startActivity(i);
            }
        });*/

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ListPozo l = list.get(position);
       // Uri uri=Uri.parse(l.getImage());
        context=holder.pics.getContext();
        // holder.pics.setImageResource(Integer.parseInt(l.getImage()));
        picasso.load(l.getImage()).fit().into(holder.pics);
        holder.title.setText(l.getTitle().toString());
        holder.id.setText(l.getId().toString());
       holder.information.setText(l.getDescription().toString());
        // holder.information.setText(l.getInformation());
        final String title1=l.getId();
        holder.pics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               passData(title1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
