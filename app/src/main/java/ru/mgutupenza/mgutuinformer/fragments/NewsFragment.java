package ru.mgutupenza.mgutuinformer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

        updatePosts();

        return view;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        NewsTask task = new NewsTask(getContext(), swipeRefreshLayout);
        task.execute();
    }

    public void updatePosts(){
        swipeRefreshLayout.setRefreshing(true);
        NewsTask task = new NewsTask(getContext(), swipeRefreshLayout);
        task.execute();
//TODO сделать ожидание завершения таска
        String news = FileIO.openString("News", getContext());

        if (!news.equals("")) {
            VkResponseParser vkResponseParser = new VkResponseParser(news);
            posts.addAll(vkResponseParser.getPosts());
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getActivity().getApplicationContext(), "Проверьте соединение с интернетом", Toast.LENGTH_SHORT).show();
        }
    }
}
