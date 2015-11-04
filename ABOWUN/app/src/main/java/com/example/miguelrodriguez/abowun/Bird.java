package com.example.miguelrodriguez.abowun;

/**
 * Created by Miguel Rodriguez on 31/10/2015.
 */
public class Bird {
    String comunName;
    String scientificName;
    int photoId;
    String inf;

    Bird(String cName, String sName, int photoId, String inf) {
        this.comunName = cName;
        this.scientificName = sName;
        this.photoId = photoId;
        this.inf = inf;
    }
}
