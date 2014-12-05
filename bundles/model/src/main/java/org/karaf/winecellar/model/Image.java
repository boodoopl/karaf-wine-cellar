package org.karaf.winecellar.model;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name="Image.GET_ALL", query="SELECT i FROM Image i"),
        @NamedQuery(name="Image.GET_COUNT", query="SELECT COUNT(i) FROM Image i"),
})
public class Image extends EntityWithId {

    @Lob
    private byte[] data;

    public Image(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
