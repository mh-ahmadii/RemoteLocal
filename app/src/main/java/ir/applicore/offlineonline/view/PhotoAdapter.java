package ir.applicore.offlineonline.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.applicore.offlineonline.R;
import ir.applicore.offlineonline.model.Photo;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
    private List<Photo> photos = new ArrayList<>();

    public PhotoAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        holder.bindPhoto(photos.get(position));
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        TextView titleTv, descriptionTv, idTv;
        ImageView thumbnailIv;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.title_tv);
            descriptionTv = itemView.findViewById(R.id.description_tv);
            idTv = itemView.findViewById(R.id.id_tv);
            thumbnailIv = itemView.findViewById(R.id.thumbnail_iv);
        }

        public void bindPhoto(Photo photo) {
            titleTv.setText(photo.getTitle());
            descriptionTv.setText(photo.getTitle());
            idTv.setText(String.valueOf(photo.getId()));
            Picasso.get().load(photo.getThumbnailUrl()).into(thumbnailIv);
        }
    }

}
