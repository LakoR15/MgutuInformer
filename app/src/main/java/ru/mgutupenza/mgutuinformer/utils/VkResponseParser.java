package ru.mgutupenza.mgutuinformer.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ru.mgutupenza.mgutuinformer.model.vk.Post;
import ru.mgutupenza.mgutuinformer.model.vk.json.Group;
import ru.mgutupenza.mgutuinformer.model.vk.json.Profile;
import ru.mgutupenza.mgutuinformer.model.vk.json.Wall;

public class VkResponseParser {

    JSONObject jsonObject;
    JSONObject response;

    public VkResponseParser(String json) {

        try {
            jsonObject = new JSONObject(json);
            response = jsonObject.getJSONObject("response");
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("VKResponseParser", "Error parsing response " + e.getMessage());
        }

    }

    public ArrayList<Post> getPosts() {

        ArrayList<Post> posts = new ArrayList<>();

        try {
            ArrayList<Wall> walls = getWalls();
            for (Wall wall : walls) {
                Post post = new Post();
                post.setId(wall.getId());
                post.setFromId(wall.getFromId());
                post.setDate(wall.getDate());
                post.setText(wall.getText());
                post.setImageUrl(wall.getImage());
                posts.add(post);
            }

            ArrayList<Profile> profiles = getProfiles();
            for (Profile profile : profiles) {
                for (Post post : posts) {
                    if (post.getFromId() == profile.getUid()) {
                        post.setAuthor(profile.getFirstName() + " " + profile.getLastName());
                        post.setAuthorImage(profile.getPhoto());
                    }
                }
            }

            ArrayList<Group> groups = getGroups();
            for (Group group : groups) {
                for (Post post : posts) {
                    if (post.getFromId() == group.getGid()) {
                        post.setAuthor(group.getName());
                        post.setAuthorImage(group.getPhoto());
                    }
                }
            }
            return posts;
        }catch (JSONException e){
            e.printStackTrace();
            Log.e("VKResponseParser", "Error parsing response " + e.getMessage());
        }

        return null;

    }

    public String getImage(JSONObject wall) throws JSONException {

        try {
            JSONArray attachments = wall.getJSONArray("attachments");

            for (int i = 0; i < attachments.length(); i++) {
                JSONObject attachment = attachments.getJSONObject(i);
                if (attachment.getString("type").equals("photo")) {
                    JSONObject photo = attachment.getJSONObject("photo");
                    return photo.getString("src_big");
                }
            }
        } catch (JSONException e) {

        }

        return "no";
    }

    public Wall getWall(JSONArray walls, int position) throws JSONException {

        JSONObject wall = walls.getJSONObject(position);

        return new Wall(wall.getLong("id"), wall.getLong("from_id"), wall.getLong("date"), wall.getString("text"), getImage(wall));
    }

    public Profile getProfile(JSONArray profiles, int position) throws JSONException {

        JSONObject profile = profiles.getJSONObject(position);

        return new Profile(profile.getLong("uid"), profile.getString("first_name"), profile.getString("last_name"), profile.getString("photo_medium_rec"));
    }

    public Group getGroup(JSONArray groups, int position) throws JSONException {

        JSONObject group = groups.getJSONObject(position);

        return new Group(group.getLong("gid"), group.getString("name"), group.getString("photo_medium"));
    }

    public ArrayList<Wall> getWalls() throws JSONException {

        ArrayList<Wall> walls = new ArrayList<>();

        JSONArray wallsJson = response.getJSONArray("wall");
        for (int i = 1; i < wallsJson.length(); i++) {
            walls.add(getWall(wallsJson, i));
        }

        return walls;
    }

    public ArrayList<Profile> getProfiles() throws JSONException {

        ArrayList<Profile> profiles = new ArrayList<>();

        JSONArray profilesJson = response.getJSONArray("profiles");
        for (int i = 0; i < profilesJson.length(); i++) {
            profiles.add(getProfile(profilesJson, i));
        }

        return profiles;
    }

    public ArrayList<Group> getGroups() throws JSONException {

        ArrayList<Group> groups = new ArrayList<>();

        JSONArray groupsJson = response.getJSONArray("groups");
        for (int i = 0; i < groupsJson.length(); i++) {
            groups.add(getGroup(groupsJson, i));
        }

        return groups;
    }
}
