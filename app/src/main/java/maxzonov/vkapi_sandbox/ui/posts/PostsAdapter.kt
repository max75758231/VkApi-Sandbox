package maxzonov.vkapi_sandbox.ui.posts

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_post.view.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.groups.Group
import maxzonov.vkapi_sandbox.data.photos.PhotoSize
import maxzonov.vkapi_sandbox.data.post.Post
import maxzonov.vkapi_sandbox.data.post.PostProfile
import maxzonov.vkapi_sandbox.utils.DateFormatter
import maxzonov.vkapi_sandbox.utils.ImageViewFormatter
import maxzonov.vkapi_sandbox.utils.TextFormatter

class PostsAdapter(val context: Context, private val wallPosts: ArrayList<Post>, val profiles: ArrayList<PostProfile>,
                   val groups: ArrayList<Group>, private val imageViewFormatter: ImageViewFormatter) :
    RecyclerView.Adapter<PostsAdapter.WallPostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallPostsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return WallPostsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return wallPosts.size
    }

    override fun onBindViewHolder(holder: WallPostsViewHolder, position: Int) {
        val wallPost: Post = wallPosts[position]

        showGeneralInfo(holder, wallPost)
        showSpecificInformation(holder, wallPost)
    }

    private fun showGeneralInfo(holder: WallPostsViewHolder, wallPost: Post) {
        with(holder) {
            tvDate.text = DateFormatter.convertDateToDayString(wallPost.date)
            tvLike.text = wallPost.likes.likesNumber.toString()
            tvComment.text = wallPost.comments.commentsNumber.toString()
            tvRepost.text = wallPost.reposts.repostsNumber.toString()
        }
    }

    private fun showSpecificInformation(holder: WallPostsViewHolder, wallPost: Post) {
        if (wallPost.wallRepost != null) {
            fillImageAndName(holder, wallPost.wallRepost[0])
        }
        else {
            fillImageAndName(holder, wallPost)
            holder.tvReposted.visibility = View.GONE
        }
    }

    private fun fillImageAndName(holder: WallPostsViewHolder, wallPost: Post) {
        showSourceInfo(holder, wallPost)
        showAttachmentsOrHideImageView(holder, wallPost)
        showOrHideText(holder, wallPost)
    }

    private fun showSourceInfo(holder: WallPostsViewHolder, wallPost: Post) {
        var sourceAvaUrl = ""

        sourceAvaUrl = if (wallPost.id >= 0) {
            showProfileNameAndGetProfileAva(holder, wallPost)
        } else {
            showGroupNameAndGetGroupAva(holder, wallPost)
        }

        Glide.with(context)
            .load(sourceAvaUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(180)))
            .into(holder.ivAvatar)
    }

    private fun showProfileNameAndGetProfileAva(holder: WallPostsViewHolder, wallPost: Post): String {
        var profileAvaUrl = ""
        profiles.forEach {
            if (it.id == wallPost.id) {
                profileAvaUrl = it.photoUrl
                holder.tvName.text = "${it.firstName} ${it.lastName}"
            }
        }
        return profileAvaUrl
    }

    private fun showGroupNameAndGetGroupAva(holder: WallPostsViewHolder, wallPost: Post): String {
        var groupAvaUrl = ""

        groups.forEach {
            if (it.groudId == (-1 * wallPost.id)) {
                groupAvaUrl = it.groupPhotoUrl
                holder.tvName.text = it.groupName
            }
        }

        return groupAvaUrl
    }

    private fun showAttachmentsOrHideImageView(holder: WallPostsViewHolder, wallPost: Post) {
        if (wallPost.attachments != null && wallPost.attachments.size != 0) {
            showAttachments(holder, wallPost)
        } else {
            holder.ivAttachment.visibility = View.GONE
        }
    }

    private fun showAttachments(holder: WallPostsViewHolder, wallPost: Post) {
        run loop@{
            wallPost.attachments.forEach {
                if (it.type == "photo") {
                    val photoSize: PhotoSize = it.photo.photoSizes[it.photo.photoSizes.size - 1]
                    val url = photoSize.url

                    resizeImageView(holder, photoSize)

                    Glide.with(context)
                        .load(url)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(12)))
                        .into(holder.ivAttachment)

                    return@loop

                } else if (it.type == "video") {
                    val url = it.video.imagePreviewUrl320
                    holder.ivPlayVideo.visibility = View.VISIBLE

                    Glide.with(context)
                        .load(url)
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(12)))
                        .into(holder.ivAttachment)
                }
            }
        }
    }

    private fun resizeImageView(holder: WallPostsViewHolder, photoSize: PhotoSize) {
        val params: ViewGroup.LayoutParams = holder.layoutImage.layoutParams
        params.height = imageViewFormatter.getImageViewHeightInPx(photoSize.height, photoSize.width)
        holder.ivAttachment.requestLayout()
    }

    private fun showOrHideText(holder: WallPostsViewHolder, wallPost: Post) {
        if (wallPost.text != "") {

            colorHashtagsInText(holder.tvText, TextFormatter.highlightHashtagsInText(wallPost.text))
        } else {
            holder.tvText.visibility = View.GONE
        }
    }

    private fun colorHashtagsInText(textView: TextView, text: String) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        } else {
            textView.text = Html.fromHtml(text)
        }
    }

    class WallPostsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ImageView = itemView.iv_post_avatar
        val ivAttachment: ImageView = itemView.iv_post_item_image
        val ivPlayVideo: ImageView = itemView.iv_post_play_video
        val tvName: TextView = itemView.tv_post_item_name
        val tvDate: TextView = itemView.tv_post_item_date
        val tvText: TextView = itemView.tv_post_item_text
        val tvLike: TextView = itemView.tv_post_item_like
        val tvComment: TextView = itemView.tv_post_item_comment
        val tvRepost: TextView = itemView.tv_post_item_repost
        val tvReposted: TextView = itemView.tv_post_reposted
        val layoutImage: FrameLayout = itemView.framelayout_post_item_image
    }
}
