package com.example.carousel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.carousel.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private val bannerAdapter = ATAdapter { CarouselView(it) }
    private var position: Int? = RecyclerView.NO_POSITION
    private val snapHelper = LinearSnapHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        bannerAdapter.items = arrayListOf(
            Carousel(1, "https://cooljsonline.com/wp-content/uploads/2020/05/nike-web-banner-main-1.jpg"),
            Carousel(2, "https://cooljsonline.com/wp-content/uploads/2020/05/nike-web-banner-main-1.jpg"),
            Carousel(3, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTYlPBJXagL_UORyEJavpzL7You3VNp24BHrA&usqp=CAU"),
            Carousel(4, "https://omunicipiojoinville.com/wp-content/uploads/2020/03/banner-unimed-joinville.png"),
            Carousel(5, "https://omunicipiojoinville.com/wp-content/uploads/2020/03/banner-unimed-joinville.png"),
        )

        binding?.let {
            it.rvCarousel.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            it.rvCarousel.adapter = bannerAdapter

            it.rvCarousel.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        notifyPositionChanged(recyclerView)
                    }
                }
            })

            addDots(it.dots, bannerAdapter.items.size, 0)
        }
    }

    private fun addDots(container: LinearLayout, size: Int, position: Int) {
        container.removeAllViews()

        Array(size) {
            val textView = TextView(this).apply {
                text = getString(R.string.dotted)
                textSize = 46f
                setTextColor(
                    if (position == it) ContextCompat.getColor(context, android.R.color.black)
                    else ContextCompat.getColor(context, android.R.color.darker_gray)
                )
            }
            container.addView(textView)
        }
    }

    private fun notifyPositionChanged(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager
        val view = snapHelper.findSnapView(layoutManager)
        val position = if (view == null) RecyclerView.NO_POSITION else layoutManager?.getPosition(view)

        val positionChanged = this.position != position
        if (positionChanged) {
            addDots(binding!!.dots, bannerAdapter.items.size, position ?: 0)
        }
        this.position = position

    }
}