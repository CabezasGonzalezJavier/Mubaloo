package com.thedeveloperworldisyours.mubalooorganisation.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thedeveloperworldisyours.mubalooorganisation.R;
import com.thedeveloperworldisyours.mubalooorganisation.adapters.EntryAdapter;
import com.thedeveloperworldisyours.mubalooorganisation.dummy.DummyContent;
import com.thedeveloperworldisyours.mubalooorganisation.interfaces.Item;
import com.thedeveloperworldisyours.mubalooorganisation.models.DummyItem;
import com.thedeveloperworldisyours.mubalooorganisation.models.Team;
import com.thedeveloperworldisyours.mubalooorganisation.models.SectionItem;
import com.thedeveloperworldisyours.mubalooorganisation.utils.Utils;
import com.thedeveloperworldisyours.mubalooorganisation.webservices.Client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A list fragment representing a list of Items. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link com.thedeveloperworldisyours.mubalooorganisation.fragments.ItemDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class ItemListFragment extends ListFragment {

    /**
     * ArrayList for show list with teams and member
     */
    ArrayList<Item> mItems = new ArrayList<Item>();

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    /**
     * The current activated item position. Only used on tablets.
     */
    private int mActivatedPosition = ListView.INVALID_POSITION;



    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(String id);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(String id) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getInformation();


    }

    /**
     * check if it's online or offline
     */

    public void getInformation() {
        if (Utils.isOnline(getActivity())) {
            getOrganisation();
        } else  if (!Utils.readFromFile(getActivity()).isEmpty()){

            buildListDummy(getJSON());
            // buildList(getJSON());
        } else {
            Toast.makeText(getActivity(), R.string.check_internet, Toast.LENGTH_LONG).show();
        }
    }
    /**
     * Build the list for DummyContent and for show list in screen
     */
    public void buildListDummy(List<Team> organisation) {

        mItems.clear();
        DummyContent.removeItem();
        for (int i=0;i<organisation.size();i++){

            if (organisation.get(i).getFirstName()!=null) {

                mItems.add(new SectionItem(organisation.get(i).getRole()));
                DummyItem dummyItem = new DummyItem(organisation.get(i).getId(), buildName(organisation.get(i).getFirstName(),organisation.get(i).getLastName()),organisation.get(i).getProfileImageURL(),organisation.get(i).getRole());
                mItems.add(dummyItem);
                DummyContent.addItem(dummyItem);
            }else{
                mItems.add(new SectionItem(organisation.get(i).getTeamName()));
            }
            for (int j = 0; j < organisation.get(i).getMembers().size(); j++) {
                DummyItem dummyItem = new DummyItem(organisation.get(i).getId(), buildName(organisation.get(i).getMembers().get(j).getFirstName(),organisation.get(i).getMembers().get(j).getLastName()),organisation.get(i).getMembers().get(j).getProfileImageURL(),organisation.get(i).getMembers().get(j).getRole());
                mItems.add(dummyItem);
                DummyContent.addItem(dummyItem);
            }
        }

        setListAdapter(new EntryAdapter(getActivity(),mItems));


    }

    /**
     * Build the name (firstname and lastname)
     */
    public String buildName(String firstName, String surName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstName);
        stringBuilder.append(" ");
        stringBuilder.append(surName);
        return stringBuilder.toString();
    }
    /**
     * Save Json
     */
    public void saveJSON(List<Team> orgaisationList) throws JSONException {

        JSONArray arrayJSON = new JSONArray();

        for(int i=0;i<orgaisationList.size();i++){
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            JSONObject jsonObject = new JSONObject(prettyGson.toJson(orgaisationList.get(i)));
            arrayJSON.put(jsonObject);
        }
        Utils.writeToFile(arrayJSON,getActivity());
    }
    /**
     * read Json
     */
    public List<Team> getJSON(){
        final Gson gson = new Gson();
        final Type typeList = new TypeToken<List<Team>>(){}.getType();
        final List<Team> organisations =gson.fromJson(Utils.readFromFile(getActivity()), typeList);
        return gson.fromJson(Utils.readFromFile(getActivity()), typeList);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        if(!mItems.get(position).isSection()){
            mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    public void getOrganisation() {
        Callback<List<Team>> callback = new Callback<List<Team>>() {

            @Override
            public void success(List<Team> organisations, Response response) {

                buildListDummy(organisations);
                try {
                    saveJSON(organisations);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError resp) {
                Log.v("failure", String.valueOf(resp.getMessage()));
            }

        };
        Client.initRestAdapter().getOrganisation(callback);
    }
}
