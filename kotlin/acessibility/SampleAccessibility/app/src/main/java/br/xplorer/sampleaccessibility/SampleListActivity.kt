package br.xplorer.sampleaccessibility

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SampleListActivity : AppCompatActivity() {


    class SimpleAdapter(private val list: List<String>) : RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): SimpleViewHolder {
            val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.rc_layout_sample_list, parent, false)
            return SimpleViewHolder(parent)
        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(viewHolder: SimpleViewHolder, position: Int) {
            val item = list[position]
            viewHolder.descriptionItem.text = item
        }

        class SimpleViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
          val descriptionItem = itemView.findViewById<TextView>(R.id.content_text)

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_list)

        val adapter = SimpleAdapter(listOf("Chris", "Luana", "Mary", "Maria", "Dri", "Manda", "Let", "Cat"))
        val rcListNames = findViewById<RecyclerView>(R.id.list_names)
        rcListNames.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            this.adapter = adapter
        }.setHasFixedSize(true)
    }
}
