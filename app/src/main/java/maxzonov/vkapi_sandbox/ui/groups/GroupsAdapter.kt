package maxzonov.vkapi_sandbox.ui.groups

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_group.view.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.groups.Group

class GroupsAdapter (val context: Context, val groups: ArrayList<Group>) : RecyclerView.Adapter<GroupsAdapter.GroupsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_group, parent, false)
        return GroupsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return groups.size
    }

    override fun onBindViewHolder(holder: GroupsViewHolder, position: Int) {

        setPhoto(holder, groups[position].groupPhotoUrl);
        holder.tvName.text = groups[position].groupName
    }

    private fun setPhoto(holder: GroupsViewHolder,url: String) {
        Glide.with(context)
                .load(url)
                .into(holder.ivPhoto)
    }

    class GroupsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.iv_item_group_photo
        val tvName: TextView = itemView.tv_item_group_name
    }
}