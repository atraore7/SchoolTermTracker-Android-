package com.example.c196paapplication.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196paapplication.Entity.Course;
import com.example.c196paapplication.Entity.Term;
import com.example.c196paapplication.R;

import java.util.List;

//Adapter for CourseList RecyclerView
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    class CourseViewHolder extends RecyclerView.ViewHolder{
        private final TextView courseItemView;
        //constructor for ViewHolder
        private CourseViewHolder(View itemView){
            super(itemView);
            courseItemView = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    //getting current selected item.
                    final Course current = mCourses.get(position);
                    Intent intent = new Intent(context, CourseDetails.class);
                    //sending Course information to the CourseDetails screen.
                    intent.putExtra("id", current.getCourseId());
                    intent.putExtra("courseTitle", current.getCourseTitle());
                    intent.putExtra("start", current.getCourseStartDate());
                    intent.putExtra("end", current.getCourseEndDate());
                    intent.putExtra("status", current.getCourseStatus());
                    intent.putExtra("instructor", current.getCourseInstructorName());
                    intent.putExtra("phone", current.getCourseInstructorPhone());
                    intent.putExtra("email", current.getCourseInstructorEmail());
                    intent.putExtra("termId", current.getCourseTermId());
                    intent.putExtra("notes", current.getCourseNotes());

                    context.startActivity(intent);
                }
            });

        }
    }
    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflator;
    public CourseAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.list_item_course, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    //where you put things on the textView.
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        if(mCourses != null){
            Course current = mCourses.get(position);
            String name = current.getCourseTitle();
            holder.courseItemView.setText(name);
        }
        else{
            holder.courseItemView.setText("No Course Name");
        }

    }
    public void setCourses(List<Course> courses){
        mCourses = courses;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mCourses.size();
    }
}

