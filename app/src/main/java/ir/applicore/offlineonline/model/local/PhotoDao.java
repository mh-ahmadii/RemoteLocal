package ir.applicore.offlineonline.model.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ir.applicore.offlineonline.model.Photo;

@Dao
public interface PhotoDao {

    @Query("SELECT * FROM photos")
    LiveData<List<Photo>> getPhotos();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addPhotos(List<Photo> photos);
}
