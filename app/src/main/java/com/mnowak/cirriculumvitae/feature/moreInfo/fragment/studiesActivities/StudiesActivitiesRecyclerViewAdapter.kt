package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.studiesActivities

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout

import com.mnowak.cirriculumvitae.R
import com.mnowak.cirriculumvitae.databinding.ItemStudiesActivityBinding
import com.mnowak.cirriculumvitae.databinding.ViewCheckedEventBinding
import com.mnowak.cirriculumvitae.data.model.StudiesActivity

class StudiesActivitiesRecyclerViewAdapter(

        private val studiesActivity: List<StudiesActivity>

) : RecyclerView.Adapter<StudiesActivitiesRecyclerViewAdapter.ActivityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemStudiesActivityBinding.inflate(layoutInflater, parent, false)
        return ActivityViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val studiesActivity = this.studiesActivity[position]
        holder.bind(studiesActivity)
    }

    override fun getItemCount(): Int =
            studiesActivity.size

    override fun onViewRecycled(holder: ActivityViewHolder) {
        super.onViewRecycled(holder)
        holder.clear()
    }

    inner class ActivityViewHolder(

            private val itemBinding: ItemStudiesActivityBinding

    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val layoutInflater: LayoutInflater = LayoutInflater.from(itemView.context)

        private val llImportantEvents: LinearLayout = itemBinding.llImportantEvents
        private val llRedundantEvents: LinearLayout = itemBinding.llRedundantEvents

        fun bind(studiesActivitiesViewModel: StudiesActivity) {
            addEvents(studiesActivitiesViewModel)
            itemBinding.viewModel = studiesActivitiesViewModel
            itemBinding.executePendingBindings()
        }

        private fun addEvents(studiesActivitiesViewModel: StudiesActivity) {
            for (i in 0 until studiesActivitiesViewModel.events.size) {
                val event = studiesActivitiesViewModel.events[i]
                //TODO prepare view
                val linearLayout = chooseGroupForEvent(studiesActivitiesViewModel, i)
                bindView(studiesActivitiesViewModel, event, linearLayout)
            }
        }

        private fun bindView(studiesActivitiesViewModel: StudiesActivity, event: StudiesActivity.Event, linearLayout: LinearLayout) {
            val viewCheckedEventBinding = DataBindingUtil.inflate<ViewCheckedEventBinding>(layoutInflater, R.layout.view_checked_event, linearLayout, true)
            viewCheckedEventBinding.studiesViewModel = studiesActivitiesViewModel
            viewCheckedEventBinding.eventViewModel = event
            viewCheckedEventBinding.executePendingBindings()
        }

        private fun chooseGroupForEvent(studiesActivitiesViewModel: StudiesActivity, position: Int): LinearLayout {
            return if (studiesActivitiesViewModel.importantEvents in 1..position) {
                llRedundantEvents
            } else {
                llImportantEvents
            }
        }

        fun clear() {
            llImportantEvents.removeAllViews()
            llRedundantEvents.removeAllViews()
        }
    }
}
