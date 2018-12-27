package maxzonov.vkapi_sandbox.ui.wall

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_profile_wall.view.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.wall.WallPost
import maxzonov.vkapi_sandbox.utils.DateFormatter

class WallPostsAdapter(val context: Context, val wallPosts: ArrayList<WallPost>) : RecyclerView.Adapter<WallPostsAdapter.WallPostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallPostsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile_wall, parent, false)
        return WallPostsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return wallPosts.size
    }

    override fun onBindViewHolder(holder: WallPostsViewHolder, position: Int) {
        val wallPost: WallPost = wallPosts[position]
        holder.tvDate.text = DateFormatter.convertDateToDayString(wallPost.date)
        holder.tvText.text = wallPost.text
        holder.tvLike.text = wallPost.likes.likesNumber.toString()
        holder.tvComment.text = wallPost.comments.commentsNumber.toString()
        holder.tvRepost.text = wallPost.reposts.repostsNumber.toString()
        holder.tvName.text = "Константин Константинов"
    }

    class WallPostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ImageView = itemView.iv_wall_avatar
        val tvName: TextView = itemView.tv_wall_item_name
        val tvDate: TextView = itemView.tv_wall_item_date
        val tvText: TextView = itemView.tv_wall_item_text
        val tvLike: TextView = itemView.tv_wall_item_like
        val tvComment: TextView = itemView.tv_wall_item_comment
        val tvRepost: TextView = itemView.tv_wall_item_repost
    }
}