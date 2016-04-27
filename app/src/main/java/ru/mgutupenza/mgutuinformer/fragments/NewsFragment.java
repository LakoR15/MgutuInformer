package ru.mgutupenza.mgutuinformer.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.mgutupenza.mgutuinformer.R;
import ru.mgutupenza.mgutuinformer.adapters.NewsRVAdapter;
import ru.mgutupenza.mgutuinformer.model.vk.Post;
import ru.mgutupenza.mgutuinformer.tasks.NewsTask;
import ru.mgutupenza.mgutuinformer.utils.FileIO;
import ru.mgutupenza.mgutuinformer.utils.VkResponseParser;

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "NewsFragment";
    private List<Post> posts = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private NewsRVAdapter adapter;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Новости");

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NewsRVAdapter(getActivity().getApplicationContext(), posts);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_news);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        progressBar = (ProgressBar)view.findViewById(R.id.progress_bar);
        updatePosts();
        return view;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRefresh() {
        updatePosts();
    }

    public void updatePosts() {
        swipeRefreshLayout.setRefreshing(true);
        NewsTask task = new NewsTask();
        task.execute();
    }

    private class NewsTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.vk.com/method/wall.get?domain=mgutupkit&offset=0&count=100&filter=all&extended=1&version=5.42")
                    .build();

            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("NewsTask", "Error availability server " + e.getMessage());
                return "";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!s.equals("")) {
                VkResponseParser vkResponseParser = new VkResponseParser(s);
                posts.addAll(vkResponseParser.getPosts());
                adapter.notifyDataSetChanged();
                FileIO.saveString("News", s, getContext());
            } else {
                VkResponseParser vkResponseParser = new VkResponseParser(FileIO.openString("News", getContext()));
                posts.addAll(vkResponseParser.getPosts());
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity().getApplicationContext(), "Проверьте соединение с интернетом", Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);
        }

    }
}
