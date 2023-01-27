package com.example.c196paapplication.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196paapplication.Entity.Term;
import com.example.c196paapplication.R;

import java.util.List;

//Adapter for termList RecyclerView
public class termAdapter extends RecyclerView.Adapter<termAdapter.TermViewHolder> {

    class TermViewHolder extends RecyclerView.ViewHolder{
        private final TextView termItemView;
        //constructor for ViewHolder
        private TermViewHolder(View itemView){
            super(itemView);
            termItemView = itemView.findViewById(R.id.termName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    //getting current selected item.
                    final Term current = mTerms.get(position);
                    Intent intent = new Intent(context, TermDetails.class);
                    //sending Term information to the termDetailedView screen.
                    intent.putExtra("id", current.getTermId());
                    intent.putExtra("name", current.getTermName());
                    intent.putExtra("start", current.getTermStartDate());
                    intent.putExtra("end", current.getTermEndDate());
                    context.startActivity(intent);
                }
            });

        }
    }
    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflator;
    public termAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflator.inflate(R.layout.list_item_term, parent, false);
        return new TermViewHolder(view);
    }

    @Override
    //where you put things on the textView.
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        if(mTerms != null){
            Term current = mTerms.get(position);
            String name = current.getTermName();
            holder.termItemView.setText(name);
        }
        else{
            holder.termItemView.setText("No Term Name");
        }

    }
    public void setTerms(List<Term> terms){
        mTerms = terms;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mTerms.size();
    }
}

/*
    //Term Adapter class
    private Context context;
    private ArrayList<Term> terms;
    private int checkedPosition = 0;

    public termAdapter(Context context, ArrayList<Term> terms){
        this.context = context;
        this.terms = terms;
    }
    public void setTerms(ArrayList<Term> terms){
        this.terms = new ArrayList<>();
        this.terms = terms;
        notifyDataSetChanged();
    }


    //View Holder: termViewHolder
    class termViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private TextView textView2;
        private ImageView imageView;

        public termViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.termId);
            textView2 = itemView.findViewById(R.id.termName);
            imageView = itemView.findViewById(R.id.imageView);

        }
        void bind(final Term term){
            if(checkedPosition == -1){
                imageView.setVisibility(View.GONE);
            }
            else{
                if(checkedPosition == getAdapterPosition()){
                    imageView.setVisibility(View.VISIBLE);
                }
                else{
                    imageView.setVisibility(View.GONE);
                }
            }
            textView.setText(term.getTermId());
            textView2.setText(term.getTermName());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView.setVisibility(View.VISIBLE);
                    if(checkedPosition != getAdapterPosition()){
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                }
            });
        }
    }
    public Term getSelected(){
        if(checkedPosition != 1){
            return terms.get(checkedPosition);
        }
        return null;
    }
    @NonNull
    @Override
    final LayoutInflater nInflator;
    public termViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;

        View view =  nInflator.inflate(R.layout.list_item, parent, false);
        return new termViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull termViewHolder holder, int position) {
        holder.bind(terms.get(position));
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

*/







/*
    private List<Term> nTerms;
    class termViewHolder extends RecyclerView.ViewHolder{
        private final TextView termItemView;
        private final TextView termItemView2;
        private termViewHolder(View itemView){
            super (itemView);
            termItemView.findViewById(R.id.textView);
            termItemView2.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Term current = nTerms.get(position);
                    Intent intent = new Intent(context, termDetailedView.class);
                    intent.putExtra("id", current.getTermId());
                    intent.putExtra("name", current.getTermName());
                }
            });
        }
    }
    private final Context context;
    private final LayoutInflater = nInflater;

    public termAdapter(Context context){
        nInflator = LayoutInflater.from(context);
        this.context = context;
    }
    @NonNull
    @Override
    public termAdapter.termViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = nInflator.inflate(R.layout.list_item, parent, false);
        return new termViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull termAdapter.termViewHolder holder, int position) {
        if(nTerms!= null){
            Term current = nTerms.get(position);
            holder.termItemView.setText(current.getTermName());
            holder.termItemView2.setText(Integer.toString(current.getTermId()));
        }
        else{
            holder.termItemView.setText("No Term Name");
            holder.termItemView2.setText("No Term Id");
        }
    }
    public void setTerms(List<Term> terms){
        nTerms = terms;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return nTerms.size();
    }
*/

//}


