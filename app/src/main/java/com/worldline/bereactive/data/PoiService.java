package com.worldline.bereactive.data;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Poi service interface
 */
public interface PoiService {

    /**
     * Get the list of POIs.
     */
    @GET("/points")
    Observable<PoiDTO> getPOIList();

    /**
     * Get the detail of a POI by id.
     *
     * @param poiId The POI id to get POI data.
     */
    @GET("/points/{id}")
    Observable<PoiDTO.Poi> getPOIById(@Path("id") int poiId);
}
