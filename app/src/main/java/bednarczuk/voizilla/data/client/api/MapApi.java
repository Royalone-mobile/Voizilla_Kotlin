package bednarczuk.voizilla.data.client.api;


import java.util.List;
import java.util.Map;

import bednarczuk.voizilla.data.client.model.MapFilters;
import bednarczuk.voizilla.data.client.model.MapSearchResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MapApi {
  /**
   * Słownik krajów
   * Status użytkownika: Niezarejestrowany
   * @return Observable&lt;Map&lt;String, String&gt;&gt;
   */
  @GET("map/countries")
  Observable<Map<String, String>> countryCodes();
    

  /**
   * Pobranie dostęnych filtrów wyszukiwania.
   * Status użytkownika: Niezarejstrowany
   * @return Observable&lt;MapFilters&gt;
   */
  @GET("map/filters")
  Observable<MapFilters> filters();
    

  /**
   * Wyświetlanie obiektów na mapie. Wyświetlanie strefy dozwolonej. Wyświetlanie naszych parkingów. Wyświetlanie określonych typów samochodów
   * Status użytkownika: Niezarejstrowany
   * @param objectType  (optional)
   * @param vehicleType  (optional)
   * @param vehicleModel  (optional)
   * @param vehicleStatus  (optional)
   * @param poiCategory  (optional)
   * @param location  (optional)
   * @return Observable&lt;MapSearchResponse&gt;
   */
  @GET("map")
  Observable<MapSearchResponse> findMapObjects(
          @Query("objectType") List<String> objectType, @Query("vehicleType") List<String> vehicleType, @Query("vehicleModel") List<String> vehicleModel, @Query("vehicleStatus") List<String> vehicleStatus, @Query("poiCategory") List<String> poiCategory, @Query("location") String location
  );

}
