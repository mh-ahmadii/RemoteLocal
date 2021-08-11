package ir.applicore.offlineonline.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.applicore.offlineonline.R;
import ir.applicore.offlineonline.model.remote.ApiServiceProvider;
import ir.applicore.offlineonline.model.local.AppDatabase;
import ir.applicore.offlineonline.model.Photo;
import ir.applicore.offlineonline.model.PhotoRepository;
import ir.applicore.offlineonline.viewmodel.MainViewModel;
import ir.applicore.offlineonline.viewmodel.MainViewModelFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainViewModel mainViewModel = new ViewModelProvider(this, new MainViewModelFactory(new PhotoRepository(ApiServiceProvider.getApiService(), AppDatabase.getInstance(getApplicationContext()).getPhotoDao())))
                .get(MainViewModel.class);
        mainViewModel.getPhotos().observe(this, new Observer<List<Photo>>() {
            @Override
            public void onChanged(List<Photo> photos) {
                PhotoAdapter photoAdapter = new PhotoAdapter(photos);
                RecyclerView recyclerView = findViewById(R.id.rv_main);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                recyclerView.setAdapter(photoAdapter);
            }
        });
        mainViewModel.error.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, "خطای نامشخص", Toast.LENGTH_SHORT).show();
            }
        });
    }
}