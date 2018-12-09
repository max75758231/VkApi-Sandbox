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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documents)

        documentsPresenter = DocumentsPresenter(this, DocumentsInteractor())
        documentsPresenter.getDocsData()
    }

    override fun onDestroy() {
        documentsPresenter.onViewDestroyed()
        super.onDestroy()
    }

    override fun showData(docs: ArrayList<Doc>) {
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
}
