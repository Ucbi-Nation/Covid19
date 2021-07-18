package com.example.covid19

import android.graphics.RectF
import com.robinhood.spark.SparkAdapter

class CovidSparkAdapter(private val dailyData: List<CovidData>) : SparkAdapter() {

    var metric = Metric.POSITIVE
    var daysago = TimeScale.MAX


    override fun getY(index: Int): Float {
        val chosenDayData = dailyData[index]
        return when (metric){
            Metric.POSITIVE -> chosenDayData.negativeIncrease.toFloat()
            Metric.NEGATIVE -> chosenDayData.negativeIncrease.toFloat()
            Metric.DEATH -> chosenDayData.deathIncrease.toFloat()

        }
    }

    override fun getItem(index: Int) = dailyData[index]

    override fun getCount() = dailyData.size

    override fun getDataBounds(): RectF {
        val bounds =  super.getDataBounds()
        if (daysago != TimeScale.MAX){
        bounds.left = count - daysago.numDays.toFloat()
        }
        return bounds
    }
}
