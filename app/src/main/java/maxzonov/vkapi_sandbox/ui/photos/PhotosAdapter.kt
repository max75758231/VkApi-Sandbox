package maxzonov.vkapi_sandbox.ui.photos

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_photo.view.*
import android.view.LayoutInflater
import android.widget.ImageView
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.photos.Photo

class PhotosAdapter (val context: Context, val photos: ArrayList<Photo>) : RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_photo, parent, false)
        return PhotosViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        Glide.with(context)
                .load(photos[position].photoSizes[3].url)
                .into(holder.ivPhoto)
    }

    class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.iv_item_photo
    }
}