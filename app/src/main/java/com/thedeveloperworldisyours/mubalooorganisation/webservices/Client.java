package com.thedeveloperworldisyours.mubalooorganisation.webservices;

import com.squareup.okhttp.OkHttpClient;
import com.thedeveloperworldisyours.mubalooorganisation.models.Team;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;

import static com.thedeveloperworldisyours.mubalooorganisation.utils.Constants.*;

/**
 * Created by javiergonzalezcabezas on 3/4/15.
 */
public class Client {
    public interface ClientInterface{
        @GET(GET_URL)
        void getOrganisation(Callback<List<Team>> callback);
    }

    public static ClientInterface initRestAdapter()
    {
        OkHttpClient client = new OkHttpClient();

        return (ClientInterface) new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setEndpoint(URL)
                .build()
                .create(ClientInterface.class);
    }

}
