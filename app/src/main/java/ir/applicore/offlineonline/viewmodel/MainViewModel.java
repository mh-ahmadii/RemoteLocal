package ir.applicore.offlineonline.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ir.applicore.offlineonline.model.Photo;
import ir.applicore.offlineonline.model.PhotoRepository;

public class MainViewModel extends ViewModel {
    public MutableLiveData<List<Photo>> photos = new MutableLiveData<>();
    public MutableLiveData<String> error = new MutableLiveData<>();
    private Disposable disposable;
    private PhotoRepository repository;

    public MainViewModel(PhotoRepository repository) {
        this.repository = repository;
        repository.refreshPhoto().subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        error.postValue("خطای نامشخص");
                    }
                });
    }

    public LiveData<List<Photo>> getPhotos() {
        return repository.getPhotos();
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
