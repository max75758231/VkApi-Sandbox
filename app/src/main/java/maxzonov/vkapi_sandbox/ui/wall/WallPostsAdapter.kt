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
import maxzonov.vkapi_sandbox.data.wall.WallPost
import maxzonov.vkapi_sandbox.data.wall.WallProfile
import maxzonov.vkapi_sandbox.utils.DateFormatter
import maxzonov.vkapi_sandbox.utils.ImageViewFormatter

class WallPostsAdapter(val context: Context, private val wallPosts: ArrayList<WallPost>, val profiles: ArrayList<WallProfile>,
                       val groups: ArrayList<Group>, private val imageViewFormatter: ImageViewFormatter) :
    RecyclerView.Adapter<WallPostsAdapter.WallPostsViewHolder>() {

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

        showGeneralInfo(holder, wallPost)
        showSpecificInformation(holder, wallPost)
    }

    private fun showGeneralInfo(holder: WallPostsViewHolder, wallPost: WallPost) {
        with(holder) {
            tvDate.text = DateFormatter.convertDateToDayString(wallPost.date)
            tvLike.text = wallPost.likes.likesNumber.toString()
            tvComment.text = wallPost.comments.commentsNumber.toString()
            tvRepost.text = wallPost.reposts.repostsNumber.toString()
        }
    }

    private fun showSpecificInformation(holder: WallPostsViewHolder, wallPost: WallPost) {
        if (wallPost.wallRepost != null)
            fillImageAndName(holder, wallPost.wallRepost[0])
        else
            fillImageAndName(holder, wallPost)
    }

    private fun fillImageAndName(holder: WallPostsViewHolder, wallPost: WallPost) {

        showSourceInfo(holder, wallPost)
        showAttachmentsOrHideImageView(holder, wallPost)
        showOrHideText(holder, wallPost)
    }

    private fun showSourceInfo(holder: WallPostsViewHolder, wallPost: WallPost) {
        var sourceAvaUrl = ""

        if (wallPost.id >= 0) {
            sourceAvaUrl = showProfileNameAndGetProfileAva(holder, wallPost)
        } else {
            sourceAvaUrl = showGroupNameAndGetGroupAva(holder, wallPost)
        }

        Glide.with(context)
            .load(sourceAvaUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(180)))
            .into(holder.ivAvatar)
    }

    private fun showProfileNameAndGetProfileAva(holder: WallPostsViewHolder, wallPost: WallPost): String {
        var profileAvaUrl = ""
        profiles.forEach {
            if (it.id == wallPost.id) {
                profileAvaUrl = it.photoUrl
                holder.tvName.text = "${it.firstName} ${it.firstName}"
            }
        }
        return profileAvaUrl
    }

    private fun showGroupNameAndGetGroupAva(holder: WallPostsViewHolder, wallPost: WallPost): String {
        var groupAvaUrl = ""

        groups.forEach {
            if (it.groudId == (-1 * wallPost.id)) {
                groupAvaUrl = it.groupPhotoUrl
                holder.tvName.text = it.groupName
            }
        }

        return groupAvaUrl
    }

    private fun showAttachmentsOrHideImageView(holder: WallPostsViewHolder, wallPost: WallPost) {
        if (wallPost.attachments != null && wallPost.attachments.size != 0) {
            showAttachments(holder, wallPost)
        } else {
            holder.ivAttachment.visibility = View.GONE
        }
    }

    private fun showAttachments(holder: WallPostsViewHolder, wallPost: WallPost) {
        run loop@{
            wallPost.attachments.forEach {
                if (it.type == "photo") {
                    val photoSize: PhotoSize = it.photo.photoSizes[it.photo.photoSizes.size - 1]
                    var url = photoSize.url

                    resizeImageView(holder, photoSize)

                    Glide.with(context)
                        .load(url)
                        .into(holder.ivAttachment)
                    return@loop
                }
            }
        }
    }

    private fun resizeImageView(holder: WallPostsViewHolder, photoSize: PhotoSize) {
        val params: ViewGroup.LayoutParams = holder.ivAttachment.layoutParams
        params.height = imageViewFormatter.getImageViewHeightInDp(photoSize.height, photoSize.width)
        holder.ivAttachment.requestLayout()
    }

    private fun showOrHideText(holder: WallPostsViewHolder, wallPost: WallPost) {
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
