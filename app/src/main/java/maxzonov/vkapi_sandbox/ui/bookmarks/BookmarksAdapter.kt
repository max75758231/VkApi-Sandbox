package maxzonov.vkapi_sandbox.ui.bookmarks

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_bookmark.view.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.bookmarks.Bookmark
import maxzonov.vkapi_sandbox.data.wall.WallProfile
import maxzonov.vkapi_sandbox.utils.DateFormatter

class BookmarksAdapter(val context: Context, val bookmarks: ArrayList<Bookmark>, val profiles: ArrayList<WallProfile>) :
    RecyclerView.Adapter<BookmarksAdapter.BookmarksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarksViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bookmark, parent, false)
        return BookmarksViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return bookmarks.size
    }

    override fun onBindViewHolder(holder: BookmarksViewHolder, position: Int) {
        val bookmark: Bookmark = bookmarks[position]
        var profileName: String = ""
        var profileSurname: String = ""
        var profileAvaUrl: String = ""
        profiles.forEach {
            if (it.id == bookmark.fromId) {
                profileName = it.firstName
                profileSurname = it.lastName
                profileAvaUrl = it.photoUrl
            }
        }
        holder.tvDate.text = DateFormatter.convertDateToDayString(bookmark.date)
        holder.tvText.text = bookmark.text
        holder.tvLike.text = bookmark.likes.likesNumber.toString()
        holder.tvComment.text = bookmark.comments.commentsNumber.toString()
        holder.tvRepost.text = bookmark.reposts.repostsNumber.toString()
        holder.tvName.text = "$profileName $profileSurname"
        Glide.with(context)
            .load(profileAvaUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(180)))
            .into(holder.ivAvatar)
    }

    class BookmarksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ImageView = itemView.iv_bookmark_avatar
        val tvName: TextView = itemView.tv_bookmark_item_name
        val tvDate: TextView = itemView.tv_bookmark_item_date
        val tvText: TextView = itemView.tv_bookmark_item_text
        val tvLike: TextView = itemView.tv_bookmark_item_like
        val tvComment: TextView = itemView.tv_bookmark_item_comment
        val tvRepost: TextView = itemView.tv_bookmark_item_repost
    }
}
