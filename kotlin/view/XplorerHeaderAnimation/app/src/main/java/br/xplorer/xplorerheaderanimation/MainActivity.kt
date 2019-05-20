package br.xplorer.xplorerheaderanimation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView

import br.xplorer.xplorerheaderanimation.activities.Activity2
import br.xplorer.xplorerheaderanimation.activities.BasicActivitySamples
import br.xplorer.xplorerheaderanimation.adapters.AdapterListActivityIntent
import br.xplorer.xplorerheaderanimation.adapters.listener.OnClickItemListener
import br.xplorer.xplorerheaderanimation.models.ActivityIntent
import kotlin.math.abs

class MainActivity : AppCompatActivity(), OnClickItemListener<ActivityIntent> {


    private var collapsedMenu : Menu? = null
    private var appBarExpanded = false
    private lateinit var collapsingToolbar : CollapsingToolbarLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        collapsingToolbar = findViewById(R.id.collapsing_toolbar)
        collapsingToolbar.title = getString(R.string.app_name)




        findViewById<AppBarLayout>(R.id.appbarlayout).addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener {
                _ , verticalOffSet ->
                Log.i("OPTIONS_MENU", "Vertical Offset $verticalOffSet")
                // verticalOffset == 0 appbarlayout esta expandido completamente
                appBarExpanded = abs(verticalOffSet) > 200
                invalidateOptionsMenu()
            }
        )


        val listActivitiesRc = findViewById<RecyclerView>(R.id.activities_list)
        val onClickItemListener : OnClickItemListener<ActivityIntent> = this
        val context = this.applicationContext
        listActivitiesRc.apply {
            layoutManager = LinearLayoutManager(context)
            adapter =  AdapterListActivityIntent(createListActivityIntenties(), onClickItemListener)
            setHasFixedSize(true)
        }


    }

    override fun onResume() {
        super.onResume()
        val imageView : ImageView = findViewById(R.id.image_header)

        imageView.viewTreeObserver.addOnPreDrawListener {
            val bitmap = BitmapHelper.fromImageViewToBitmap(imageView)
            Palette.from(bitmap)
                .generate {
                        palette ->
                    if(palette != null) {
                        collapsingToolbar.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary))
                        collapsingToolbar.setStatusBarScrimColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))
                    }
                }
           true
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.i("OPTIONS_MENU", "CREATE_OPTIONS_MENU")
        collapsedMenu = menu
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {

        Log.i("OPTIONS_MENU", "PREPARE_OPTIONS_MENU")

        if (collapsedMenu != null && (!appBarExpanded || collapsedMenu?.size() != 1)) {
            collapsedMenu?.add("Add")
                ?.setIcon(R.drawable.ic_plus)
                ?.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onClick(data: ActivityIntent, pos: Int) {}

    override fun onClick(data: ActivityIntent) {
        startActivity(data.intent)
    }

    private fun createListActivityIntenties() : List<ActivityIntent> {
        return listOf(
            ActivityIntent(Intent(this, Activity2::class.java)
                , Activity2::class.java.simpleName)
            , ActivityIntent(Intent(this, BasicActivitySamples::class.java)
                , BasicActivitySamples::class.java.simpleName)
        )
    }
}
