package com.example.carousel

import android.view.View.inflate
import android.view.ViewGroup
import com.example.carousel.databinding.CarouselItemBinding
import com.squareup.picasso.Picasso

class CarouselView(viewGroup: ViewGroup) : ATViewHolder<Carousel, CarouselItemBinding>(
    CarouselItemBinding::inflate,
    viewGroup
) {

    override fun bind(item: Carousel) {
        Picasso.get()
            .load(item.imageUrl)
            .into(binding.imgCarousel)
    }

}