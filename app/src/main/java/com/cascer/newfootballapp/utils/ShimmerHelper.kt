package com.cascer.newfootballapp.utils

import android.view.View
import com.facebook.shimmer.ShimmerFrameLayout

class ShimmerHelper() {

    fun startShimmer(shimmer: ShimmerFrameLayout?) {
        if (shimmer != null) {
            shimmer.visibility = View.VISIBLE
            shimmer.startShimmer()
        }
    }

    fun stopShimmer(shimmer: ShimmerFrameLayout?) {
        if (shimmer != null) {
            shimmer.stopShimmer()
            shimmer.visibility = View.GONE
        }
    }
}