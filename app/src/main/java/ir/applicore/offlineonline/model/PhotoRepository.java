package ir.applicore.offlineonline.model;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Completable;
import ir.applicore.offlineonline.model.local.PhotoDao;
import ir.applicore.offlineonline.model.remote.ApiService;

public class PhotoRepository {
    private ApiService apiService;
    private PhotoDao photoDao;

    public PhotoRepository(ApiService apiService, PhotoDao photoDao) {
        this.apiService = apiService;
        this.photoDao = photoDao;
    }

    public Completable refreshPhoto() {
        return apiService.getPhotos().doOnSuccess(photos -> photoDao.addPhotos(photos)).ignoreElement();
    }

    public LiveData<List<Photo>> getPhotos() {
        return photoDao.getPhotos();
    }
}
