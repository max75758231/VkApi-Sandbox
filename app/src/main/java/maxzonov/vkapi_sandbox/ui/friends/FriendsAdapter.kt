package maxzonov.vkapi_sandbox.ui.friends

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
import kotlinx.android.synthetic.main.item_friend.view.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.friends.Friend

class FriendsAdapter (val context: Context, val friends: ArrayList<Friend>) : RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
        return FriendsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return friends.size
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        val friend: Friend = friends[position]

        holder.tvName.text = "${friend.firstName} ${friend.lastName}"
        Glide.with(context)
            .load(friend.photoUrl)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(16)))
            .into(holder.ivAvatar)
    }

    class FriendsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivAvatar: ImageView = itemView.iv_item_friend_avatar
        val tvName: TextView = itemView.tv_item_friend_name
    }
}