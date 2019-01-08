package maxzonov.vkapi_sandbox.ui.wall

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
import kotlinx.android.synthetic.main.item_profile_wall.view.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.groups.Group
import maxzonov.vkapi_sandbox.data.photos.PhotoSize
import maxzonov.vkapi_sandbox.data.wall.WallPhoto
import maxzonov.vkapi_sandbox.data.wall.WallPost
import maxzonov.vkapi_sandbox.data.wall.WallProfile
import maxzonov.vkapi_sandbox.utils.DateFormatter

class WallPostsAdapter(val context: Context, val wallPosts: ArrayList<WallPost>, val profiles: ArrayList<WallProfile>, val groups: ArrayList<Group>) : RecyclerView.Adapter<WallPostsAdapter.WallPostsViewHolder>() {

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
        holder.tvLike.text = wallPost.likes.likesNumber.toString()
        holder.tvComment.text = wallPost.comments.commentsNumber.toString()
        holder.tvRepost.text = wallPost.reposts.repostsNumber.toString()

        if (wallPost.wallRepost != null)
            fillImageAndName(holder, wallPost.wallRepost[0])
        else
            fillImageAndName(holder, wallPost)
    }

    private fun fillImageAndName(holder: WallPostsViewHolder, wallPost: WallPost) {
        var profileAvaUrl = ""
        var profileName = ""
        var profileSurname = ""
        var groupName = ""

        if (wallPost.id >= 0) {
            profiles.forEach {
                if (it.id == wallPost.id) {
                    profileName = it.firstName
                    profileSurname = it.lastName
                    profileAvaUrl = it.photoUrl
                }
            }
            holder.tvName.text = "$profileName $profileSurname"
        } else {
            groups.forEach {
                if (it.groudId == (-1 * wallPost.id)) {
                    groupName = it.groupName
                    profileAvaUrl = it.groupPhotoUrl
                    holder.tvName.text = "$groupName"
                }
            }
        }

        Glide.with(context)
            .load(profileAvaUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(180)))
            .into(holder.ivAvatar)
        if (wallPost.attachments != null && wallPost.attachments.size != 0) {
            run loop@{
                wallPost.attachments.forEach {
                    if (it.type == "photo") {
                        val photoSize: PhotoSize = it.photo.photoSizes[it.photo.photoSizes.size - 1]
                        var url = photoSize.url
                        Glide.with(context)
                            .load(url)
                            .into(holder.ivAttachment)
                        return@loop
                    }
                }
            }
        }
        if (wallPost.text != "")
            holder.tvText.text = wallPost.text
        else
            holder.tvText.visibility = View.GONE
    }

    class WallPostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ImageView = itemView.iv_wall_avatar
        val ivAttachment: ImageView = itemView.iv_wall_item_image
        val tvName: TextView = itemView.tv_wall_item_name
        val tvDate: TextView = itemView.tv_wall_item_date
        val tvText: TextView = itemView.tv_wall_item_text
        val tvLike: TextView = itemView.tv_wall_item_like
        val tvComment: TextView = itemView.tv_wall_item_comment
        val tvRepost: TextView = itemView.tv_wall_item_repost
    }
}
