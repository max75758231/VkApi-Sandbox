package maxzonov.vkapi_sandbox.ui.documents

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_documents.*
import maxzonov.vkapi_sandbox.R
import maxzonov.vkapi_sandbox.data.docs.Doc
import maxzonov.vkapi_sandbox.ui.base.BaseActivity

class DocumentsActivity : BaseActivity(), DocumentsView {

    private lateinit var documentsPresenter: DocumentsPresenter

    private var docsAll: ArrayList<Doc> = ArrayList()
    private val docsAudio: ArrayList<Doc> = ArrayList()
    private val docsArchive: ArrayList<Doc> = ArrayList()
    private val docsGif: ArrayList<Doc> = ArrayList()
    private val docsImage: ArrayList<Doc> = ArrayList()
    private val docsText: ArrayList<Doc> = ArrayList()
    private val docsVideo: ArrayList<Doc> = ArrayList()
    private val docsOther: ArrayList<Doc> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documents)

        documentsPresenter = DocumentsPresenter(this, DocumentsInteractor())
        documentsPresenter.getDocsData()

        initChipsClickListeners()
    }

    override fun onDestroy() {
        documentsPresenter.onViewDestroyed()
        super.onDestroy()
    }

    override fun showDataFromServer(docs: ArrayList<Doc>) {
        this.docsAll = docs

        putIndividualData()

        val adapter = DocumentsAdapter(this, docs)
        rv_docs.layoutManager = LinearLayoutManager(this)
        rv_docs.adapter = adapter
    }

    override fun showDataError(errorStr: String) {
        Toast.makeText(this, "Ошибка получения списка документов", Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        pb_docs.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        pb_docs.visibility = View.GONE
    }

    private fun initChipsClickListeners() {
        chip_docs_all.setOnClickListener {
            showIndividualData(docsAll)
        }
        chip_docs_audio.setOnClickListener {
            showIndividualData(docsAudio)
        }
        chip_docs_anims.setOnClickListener {
            showIndividualData(docsGif)
        }
        chip_docs_archives.setOnClickListener {
            showIndividualData(docsArchive)
        }
        chip_docs_images.setOnClickListener {
            showIndividualData(docsImage)
        }
        chip_docs_text.setOnClickListener {
            showIndividualData(docsText)
        }
        chip_docs_video.setOnClickListener {
            showIndividualData(docsVideo)
        }
        chip_docs_others.setOnClickListener {
            showIndividualData(docsOther)
        }
    }

    private fun showIndividualData(docs: ArrayList<Doc>) {
        val adapter = DocumentsAdapter(this, docs)
        rv_docs.layoutManager = LinearLayoutManager(this)
        rv_docs.adapter = adapter
    }

    private fun putIndividualData() {
        for(doc in docsAll) {
            when (doc.type) {
                1 -> docsText.add(doc)
                2 -> docsArchive.add(doc)
                3 -> docsGif.add(doc)
                4 -> docsImage.add(doc)
                5 -> docsAudio.add(doc)
                6 -> docsVideo.add(doc)
                else -> docsOther.add(doc)
            }
        }
    }
}
