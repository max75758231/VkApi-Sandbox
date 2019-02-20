package maxzonov.vkapi_sandbox.ui.documents

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_document.view.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.docs.Doc

class DocumentsAdapter (val context: Context, val docs: ArrayList<Doc>) : RecyclerView.Adapter<DocumentsAdapter.DocsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_document, parent, false)
        return DocsViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return docs.size
    }

    override fun onBindViewHolder(holder: DocsViewHolder, position: Int) {
        val doc: Doc = docs[position]

        holder.tvDocName.text = doc.title
        val size = doc.size
        val date = doc.date
        holder.tvDocSizeDate.text = "$size  |  $date"
        holder.ivDocType.setImageDrawable(getDrawableByType(doc.type))
    }

    private fun getDrawableByType(type: Int): Drawable? {
        return when (type) {
            1 -> context.getDrawable(R.drawable.ic_docs_text)
            2 -> context.getDrawable(R.drawable.ic_docs_archive)
            3 -> context.getDrawable(R.drawable.ic_docs_gif)
            4 -> context.getDrawable(R.drawable.ic_docs_image)
            5 -> context.getDrawable(R.drawable.ic_docs_audio)
            6 -> context.getDrawable(R.drawable.ic_docs_video)
            else -> context.getDrawable(R.drawable.ic_docs_other)
        }
    }

    class DocsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivDocType: ImageView = itemView.iv_item_doc
        val tvDocName: TextView = itemView.tv_item_doc_name
        val tvDocSizeDate: TextView = itemView.tv_item_doc_size_and_date
    }
}
