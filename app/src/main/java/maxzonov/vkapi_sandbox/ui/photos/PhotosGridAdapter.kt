package maxzonov.vkapi_sandbox.ui.photos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_photo_grid.view.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.photos.Photo

class PhotosGridAdapter(val context: Context, val photos: ArrayList<Photo>) : BaseAdapter() {

    override fun getItem(p0: Int): Any? {
        return null
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return photos.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val photo = photos[position].photoSizes[3].url

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val photoView = inflater.inflate(R.layout.item_photo_grid, null)
        Glide.with(context)
            .load(photo)
            .into(photoView.iv_photo_grid)

        return photoView
    }
}