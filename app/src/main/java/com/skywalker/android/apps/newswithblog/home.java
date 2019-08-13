package com.skywalker.android.apps.newswithblog;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.TimeoutError;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class home extends Fragment {
    private RecyclerView.Adapter adapters;
    private  RecyclerView recyclerView;
    private  List<Posts> newsList = new ArrayList<>();
    private String URL_DATA;
    private RequestQueue reqQue;
    private  long id;


    public home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        URL_DATA = "https://www.simplifiedcoding.net/wp-json/wp/v2/posts/";


        recyclerView = rootView.findViewById(R.id.recycles);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        adapters = new NewsAdapter(newsList, getActivity());


        recyclerView.setAdapter(adapters);
        SetUrl();
        loadUrl();

        return rootView;


    }

    private void loadUrl() {
        JsonArrayRequest stringRequest = new JsonArrayRequest(URL_DATA, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                getValue(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        reqQue = Volley.newRequestQueue(getActivity());
        reqQue.add(stringRequest);


    }

    public void getValue(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {
            Posts userList = new Posts();

            JSONObject json = null;

            try {
                json = array.getJSONObject(i);

                // JsonObject obj  = array.getJSONObject(i);
                userList.setPostImg(json.getString("jetpack_featured_media_url"));
                userList.setPostURL(json.getString("link"));
                JSONObject titlee = json.getJSONObject("title");
                userList.setTitle(titlee.getString("rendered"));

                JSONObject disc = json.getJSONObject("content");
                userList.setDescription(disc.getString("rendered"));







            } catch (Exception e) {
            }
            newsList.add(userList);
        }


        adapters = new NewsAdapter(newsList, getActivity());
        recyclerView.setAdapter(adapters);

    }

    public void SetUrl() {

        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null)
        {
            id = (long) getActivity().getIntent().getLongExtra("idss",410);
            URL_DATA = "https://www.simplifiedcoding.net/wp-json/wp/v2/posts?categories=" + id;
        }





    }

}