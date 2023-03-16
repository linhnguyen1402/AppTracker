package com.ndta.apptracker.utils

import android.content.Context
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.ndta.apptracker.R
import com.ndta.apptracker.databinding.MarkerViewBinding
import com.ndta.apptracker.db.Run
import java.text.SimpleDateFormat
import java.util.*

class CustomMarkerView(val runs: List<Run>, context: Context, layoutId: Int) :
    MarkerView(context, layoutId) {

    override fun getOffset(): MPPointF {
        return MPPointF(-width / 2f, -height.toFloat())

    }

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
        //TODO not good for Performance?
        var layoutInflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding: MarkerViewBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.marker_view, this, true)

        if (e == null) {
            return
        }

        val curRunId = e.x.toInt()
        val run = runs[curRunId]

        val calendar = Calendar.getInstance().apply {
            timeInMillis = run.timestamp

        }
        val dateFormat = SimpleDateFormat("dd.MM.yy", Locale.getDefault())
        binding.tvDate.text = dateFormat.format(calendar.time)

        val avgSpeed = "${run.avgSpeedInKMH}km/h"
        binding.tvAvgSpeed.text = avgSpeed

        val distanceInKm = "${run.distanceInMeters / 1000f}km"
        binding.tvDistance.text = distanceInKm

        binding.tvDuration.text = TrackingUtility.getFormattedStopWatchTime(run.timeInMillis)

        val caloriesBurned = "${run.caloriesBurned}kcal"
        binding.tvCaloriesBurned.text = caloriesBurned
    }
}