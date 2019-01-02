package com.mnowak.curriculumvitae.feature.moreInfo.fragment.studiesActivities.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mnowak.curriculumvitae.R
import com.mnowak.curriculumvitae.databinding.ItemStudiesActivityBinding
import com.mnowak.curriculumvitae.databinding.ViewCheckedEventBinding
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_studies_activity.*

class StudiesActivitiesAdapter(

        private val lifecycleOwner: LifecycleOwner

) : ListAdapter<StudiesActivitiesItemViewModel, StudiesActivitiesAdapter.ActivityViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemStudiesActivityBinding.inflate(layoutInflater, parent, false)
        itemBinding.setLifecycleOwner(lifecycleOwner)
        return ActivityViewHolder(itemBinding, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val studiesActivity = getItem(position)
        holder.bind(studiesActivity)
    }

    override fun onViewRecycled(holder: ActivityViewHolder) {
        super.onViewRecycled(holder)
        holder.clear()
    }

    inner class ActivityViewHolder(

            private val itemBinding: ItemStudiesActivityBinding,
            private val lifecycleOwner: LifecycleOwner

    ) : RecyclerView.ViewHolder(itemBinding.root),
            LayoutContainer {

        override val containerView: View = itemBinding.root

        private val layoutInflater: LayoutInflater = LayoutInflater.from(itemView.context)

        private val context by lazy {
            containerView.context
        }
        private val primaryColor: Int by lazy {
            ContextCompat.getColor(context, R.color.colorTextPrimary)
        }
        private val regularColor: Int by lazy {
            ContextCompat.getColor(context, R.color.colorTextSecondary)
        }
        private val redundantColor: Int by lazy {
            ContextCompat.getColor(context, R.color.colorTextRedundant)
        }

        private var viewModel: StudiesActivitiesItemViewModel? = null

        fun bind(viewModel: StudiesActivitiesItemViewModel) {
            this.viewModel = viewModel
            addEvents(viewModel)
            viewModel.setColors(primaryColor, regularColor, redundantColor)
            itemBinding.viewModel = viewModel
            itemBinding.executePendingBindings()
        }

        private fun addEvents(viewModel: StudiesActivitiesItemViewModel) {
            viewModel.importantEvents.observe(lifecycleOwner, importantEventsObserver)
            viewModel.redundantEvents.observe(lifecycleOwner, redundantEventsObserver)
        }

        private val importantEventsObserver = prepareEventsObserver(llImportantEvents)

        private val redundantEventsObserver = prepareEventsObserver(llRedundantEvents)

        private fun prepareEventsObserver(linearLayout: LinearLayout): Observer<List<StudiesActivitiesItemViewModel.EventViewModel>> =
                Observer { eventsValue ->
                    eventsValue.forEach {
                        bindView(it, linearLayout)
                    }
                }

        private fun bindView(eventViewModel: StudiesActivitiesItemViewModel.EventViewModel, linearLayout: LinearLayout) {
            val viewCheckedEventBinding = DataBindingUtil.inflate<ViewCheckedEventBinding>(layoutInflater, R.layout.view_checked_event, linearLayout, true)
            viewCheckedEventBinding.eventViewModel = eventViewModel
            viewCheckedEventBinding.setLifecycleOwner(lifecycleOwner)
            viewCheckedEventBinding.executePendingBindings()
        }

        fun clear() {
            viewModel?.importantEvents?.removeObserver(importantEventsObserver)
            viewModel?.redundantEvents?.removeObserver(redundantEventsObserver)
            viewModel = null
            llImportantEvents.removeAllViews()
            llRedundantEvents.removeAllViews()
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StudiesActivitiesItemViewModel>() {

    override fun areItemsTheSame(oldItem: StudiesActivitiesItemViewModel, newItem: StudiesActivitiesItemViewModel): Boolean =
            oldItem.id.value == newItem.id.value


    override fun areContentsTheSame(oldItem: StudiesActivitiesItemViewModel, newItem: StudiesActivitiesItemViewModel): Boolean =
            oldItem == newItem
}
