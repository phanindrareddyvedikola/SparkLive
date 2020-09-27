package com.litmusworld.sparklivetest;

import android.util.JsonReader;

import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetUserDataRepository {

    private static GetUserDataRepository instance;
    private ArrayList<User> usersList = new ArrayList<>();

    public static GetUserDataRepository getInstance() {
        if (instance == null) {
            instance = new GetUserDataRepository();
        }

        return instance;
    }

    public MutableLiveData<List<User>> getUserData() {

        setUserData();

        MutableLiveData<List<User>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(usersList);
        return listMutableLiveData;
    }

    private void setUserData() {

        String response = Utils.getJsonFromAssets(SparkApplication.getAppContext());

        usersList = parseJson(response);

    }

    private ArrayList<User> parseJson(String response) {

        ArrayList<User> userArrayList = new ArrayList<>();

        try {

            JSONObject objectResponse = new JSONObject(response);

            JSONArray resultsArray = objectResponse.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject jsonObject = resultsArray.getJSONObject(i);
                User user = new User();

                user.setPublishedAt(jsonObject.getString("published_at"));
                user.setThumbnail(jsonObject.getString("thumbnail"));

                JSONObject expertObject = jsonObject.getJSONObject("expert");
                user.setName(expertObject.getString("name"));
                user.setBio(expertObject.getString("bio"));
                user.setProfileImage(expertObject.getString("profile_pic"));

                user.setShortBio(expertObject.getString("short_bio"));
//                user.setId(Integer.parseInt(expertObject.getString("user_id")));

                JSONObject actionCountObject = jsonObject.getJSONObject("action_counts");
                user.setLikesCount(actionCountObject.getInt("like"));
                user.setViewCount(actionCountObject.getInt("web_click"));

                userArrayList.add(user);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return userArrayList;
    }

}
