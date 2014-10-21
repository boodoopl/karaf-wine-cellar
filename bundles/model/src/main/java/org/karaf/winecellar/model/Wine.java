package org.karaf.winecellar.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name="Wine.GET_ALL", query="SELECT w FROM Wine w"),
        @NamedQuery(name="Wine.GET_COUNT", query="SELECT COUNT(w) FROM Wine w"),
})
public class Wine extends EntityWithId {

    private String name;
    private String country;
    private String picture;
    private String description;

    public Wine(String name, String country, String picture, String description) {
        this.name = name;
        this.country = country;
        this.picture = picture;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getPicture() {
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}