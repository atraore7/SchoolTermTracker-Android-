package com.example.c196paapplication.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196paapplication.Entity.Assessment;
import com.example.c196paapplication.Entity.Course;
import com.example.c196paapplication.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder{
        private final TextView assessmentItemView;
        //private final TextView assessmentItemView2;
        //constructor for ViewHolder
        private AssessmentViewHolder(View itemView){
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.textView);
            //assessmentItemView2 = itemView.findViewById(R.id.textView3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    //getting current selected item.
                    final Assessment current = mAssessments.get(position);
                    Intent intent = new Intent(context, AssessmentDetails.class);
                    //sending Course information to the CourseDetails screen.
                    intent.putExtra("id", current.getAssessmentId());
                    intent.putExtra("name", current.getAssessmentName());
                    intent.putExtra("endDate", current.getAssessmentEndDate());
                    intent.putExtra("type", current.getAssessmentType());
                    intent.putExtra("courseId", current.getAssessmentCourseId());

                    context.startActivity(intent);
                }
            });

        }
    }
    private List<Assessment> mAssessments;
    private final Context context;
    private final LayoutInflater mInflator;
    public AssessmentAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.list_item_assessment, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @Override
    //where you put things on the textView.
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(mAssessments != null){
            Assessment current = mAssessments.get(position);
            String name = current.getAssessmentName();
            String type = current.getAssessmentType();
            holder.assessmentItemView.setText(name + " " + "(" + type + ")");
            //String type = current.getAssessmentType();
            //holder.assessmentItemView2.setText(type);
        }
        else{
            holder.assessmentItemView.setText("No Assessment Name");
        }

    }
    public void setAssessments(List<Assessment> assessments){
        mAssessments = assessments;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mAssessments.size();
    }
}



