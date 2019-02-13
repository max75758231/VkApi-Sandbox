package maxzonov.vkapi_sandbox.ui.groups

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_group.view.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.groups.Group
import java.lang.Exception

class GroupsAdapter (val context: Context, var groups: ArrayList<Group>) : RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_group, parent, false)
        return GroupsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return groups.size
    }

    override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {

        setPhoto(holder, groups[position].groupPhotoUrl)
        holder.tvName.text = groups[position].groupName
    }

    private fun setPhoto(holder: GroupsViewHolder,url: String) {
        Glide.with(context)
            .load(url)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE)) // Cache only final image
            .apply(RequestOptions.bitmapTransform(RoundedCorners(8)))
            .into(holder.ivPhoto)
    }

    fun updateGroups(groups: ArrayList<Group>) {
        this.groups = groups
        try {
            notifyDataSetChanged()
        } catch (e: Exception) {
            Log.d("myLog", e.message)
        }
    }

    class GroupsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.iv_item_group_photo
        val tvName: TextView = itemView.tv_item_group_name
    }
}