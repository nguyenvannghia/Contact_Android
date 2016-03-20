package nit.contact.ui.activities

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter
import kotlinx.android.synthetic.main.activity_feed_detail.*
import nit.contact.R
import nit.contact.adapters.MediaFeedDetailViewPagerAdapter
import nit.contact.common.hiddenKeyboard
import nit.contact.ui.activities.FeedDetailActivity.Companion.ViewHolder
import nit.contact.views.HeaderLayout
import java.util.*

/**
 * Created by NIT Admin on 07/03/2016
 */
class FeedDetailActivity : AppCompatActivity(), HeaderLayout.OnClickItemHeaderListener {
    override fun onClickBackFromChildPage() {
        this@FeedDetailActivity.finish()
        overridePendingTransition(R.anim.anim_in_left, R.anim.anim_out_right);
    }

    override fun onClickShowMenu() {

    }

    var mediaFeedDetailViewPagerAdapter: MediaFeedDetailViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_detail)
        createAdapter()

    }

    private fun createAdapter() {
        val content = ArrayList<String>()
        for (i in 0..49) {
            content.add("comment .......  " + i)
        }

        val adapter = object : ParallaxRecyclerAdapter<String>(content) {
            override fun onBindViewHolderImpl(viewHolder: RecyclerView.ViewHolder, adapter: ParallaxRecyclerAdapter<String>, i: Int) {
                //                (viewHolder as Companion.ViewHolder).tvComment.setText(adapter.data[i])
            }

            override fun onCreateViewHolderImpl(viewGroup: ViewGroup, adapter: ParallaxRecyclerAdapter<String>, i: Int): RecyclerView.ViewHolder {
                return ViewHolder(layoutInflater.inflate(R.layout.itemlist_commnet, viewGroup, false))
            }

            override fun getItemCountImpl(adapter: ParallaxRecyclerAdapter<String>): Int {
                return content.size
            }
        }

        headerLayoutFeedDetailActivity.setTitle("CSy Minh Hằng")
        headerLayoutFeedDetailActivity.isShowButtonRight(false)
        headerLayoutFeedDetailActivity.isShowButtonLeft(true)
        headerLayoutFeedDetailActivity.setCallback(this@FeedDetailActivity)
        headerLayoutFeedDetailActivity.isChildPage = true

        adapter.setOnClickEvent { v, position -> Toast.makeText(this@FeedDetailActivity, "You clicked '$position'", Toast.LENGTH_SHORT).show() }
        adapter.setOnParallaxScroll { p0, p1, p2 ->
            var c: Drawable = ContextCompat.getDrawable(this@FeedDetailActivity, R.color.green)
            c.alpha = Math.round(p0 * 255);
            headerLayoutFeedDetailActivity.background = c;
        }

        recyclerViewFeedDetailActivity.layoutManager = LinearLayoutManager(this)
        val header = layoutInflater.inflate(R.layout.custom_header_feeddetail, recyclerViewFeedDetailActivity, false)
        var viewPager: ViewPager = header.findViewById(R.id.viewPager) as ViewPager
        var tvComment: TextView = header.findViewById(R.id.tvComment) as TextView
        mediaFeedDetailViewPagerAdapter = MediaFeedDetailViewPagerAdapter(supportFragmentManager, null)
        viewPager.adapter = mediaFeedDetailViewPagerAdapter
        adapter.setParallaxHeader(header, recyclerViewFeedDetailActivity)
        adapter.data = content
        recyclerViewFeedDetailActivity.adapter = adapter

        tvComment.setOnClickListener {
            edtComment.requestFocus()
            var imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    companion object {
        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var tvComment: TextView

            init {
                tvComment = itemView.findViewById(R.id.tvComment) as TextView
            }
        }
    }

    override fun onPause() {
        super.onPause()
        hiddenKeyboard(this@FeedDetailActivity)
    }

    override fun onBackPressed() {
        onClickBackFromChildPage()
    }

}
