package ir.applicore.offlineonline.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ir.applicore.offlineonline.model.PhotoRepository;

public class MainViewModelFactory implements ViewModelProvider.Factory {
    private PhotoRepository repository;

    public MainViewModelFactory(PhotoRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(repository);
    }
}
