package ir.applicore.offlineonline.model.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ir.applicore.offlineonline.model.Photo;

@Database(entities = {Photo.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, "db_photos").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
    //Getter
    public abstract PhotoDao getPhotoDao();
}
