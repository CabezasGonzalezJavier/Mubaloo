package com.thedeveloperworldisyours.mubalooorganisation.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javiergonzalezcabezas on 3/4/15.
 */
public class Team {
    @Expose
    private String id;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private String role;
    @Expose
    private String profileImageURL;
    @Expose
    private String teamName;
    @Expose
    private List<Member> members = new ArrayList<Member>();

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName The firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return The lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName The lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return The role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role The role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return The profileImageURL
     */
    public String getProfileImageURL() {
        return profileImageURL;
    }

    /**
     * @param profileImageURL The profileImageURL
     */
    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    /**
     * @return The teamName
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * @param teamName The teamName
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * @return The members
     */
    public List<Member> getMembers() {
        return members;
    }

    /**
     * @param members The members
     */
    public void setMembers(List<Member> members) {
        this.members = members;
    }

}
