package ru.mgutupenza.mgutuinformer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ru.mgutupenza.mgutuinformer.R;
import ru.mgutupenza.mgutuinformer.model.vk.Post;

public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.NewsViewHolder> {

    public static class NewsViewHolder extends RecyclerView.ViewHolder {

        private TextView newsTitle;
        private TextView newsDate;
        private TextView newsText;
        private ImageView newsImg;
        private ImageView authorImg;

        public NewsViewHolder(View itemView) {
            super(itemView);
            newsTitle = (TextView) itemView.findViewById(R.id.news_title);
            newsDate = (TextView) itemView.findViewById(R.id.news_date);
            newsText = (TextView) itemView.findViewById(R.id.news_content);
            newsImg = (ImageView) itemView.findViewById(R.id.news_img);
            authorImg = (ImageView) itemView.findViewById(R.id.news_author_img);
        }
    }

    List<Post> posts;
    Context context;

    public NewsRVAdapter(Context context, List<Post> posts) {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_news, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

        Picasso.with(context)
                .load(posts.get(position).getAuthorImage())
                .into(holder.authorImg);
        holder.newsTitle.setText(String.valueOf(posts.get(position).getAuthor()));
        holder.newsDate.setText(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date(posts.get(position).getDate() * 1000)));
        holder.newsText.setText(posts.get(position).getText().replaceAll("<br>", "\n"));
        Picasso.with(context)
                .load(posts.get(position).getImageUrl())
                .into(holder.newsImg);

    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
