package ir.applicore.offlineonline.model.remote;

import java.util.List;

import io.reactivex.Single;
import ir.applicore.offlineonline.model.Photo;
import retrofit2.http.GET;

public interface ApiService {
    @GET("photos")
    Single<List<Photo>> getPhotos();
}
