package org.karaf.winecellar.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name="Wine.GET_ALL", query="SELECT w FROM Wine w"),
        @NamedQuery(name="Wine.GET_COUNT", query="SELECT COUNT(w) FROM Wine w"),
})
public class Wine extends EntityWithId {

    private String name;

    private String grapes;

    private String country;

    private String region;

    private String year;

    @Lob
    private String description;

    private long imageId;

    public Wine(String name, String grapes, String country, String region, String year, String description, long imageId) {
        this.name = name;
        this.grapes = grapes;
        this.country = country;
        this.region = region;
        this.year = year;
        this.description = description;
        this.imageId = imageId;
    }

    public Wine(String name) {
        this(name, "some grapes", "Poland", "Some region", "2014", "Descrption", 0);
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrapes() {
        return grapes;
    }

    public void setGrapes(String grapes) {
        this.grapes = grapes;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
