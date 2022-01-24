package com.example.getjob_andriod;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class postedjobRVadepter extends RecyclerView.Adapter<postedjobRVadepter.ViewHolder> {
    ArrayList<postjobhelper> postJobArrayList ;
    private Context context;

    public postedjobRVadepter(ArrayList<postjobhelper> postJobArrayList, Context context) {
        this.postJobArrayList = postJobArrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public postedjobRVadepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.postedjob_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull postedjobRVadepter.ViewHolder holder, int position) {
        postjobhelper postJob = postJobArrayList.get(position);
        holder.companyLocation.setText(postJob.getCompanyLocation());
        holder.companyName.setText(postJob.getCompanyName());
        holder.jobDes.setText(postJob.getjobDescription());
        holder.jobType.setText(postJob.getjobType());
        holder.payRate.setText(postJob.getpayRate());
        holder.reqQua.setText(postJob.getReqqua());

    }

    @Override
    public int getItemCount() {
        return postJobArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView companyLocation;
        private final TextView companyName;
        private final TextView jobDes;
        private final TextView jobType;
        private final TextView payRate;
        private final TextView reqQua;
        private final TextView shift;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            companyLocation=itemView.findViewById(R.id.companyLocation);
            companyName=itemView.findViewById(R.id.companyName);
            jobDes=itemView.findViewById(R.id.jobDes);
            jobType=itemView.findViewById(R.id.jobType);
            payRate=itemView.findViewById(R.id.payRate);
            reqQua=itemView.findViewById(R.id.reqQua);
            shift=itemView.findViewById(R.id.shift);
        }
    }
}
