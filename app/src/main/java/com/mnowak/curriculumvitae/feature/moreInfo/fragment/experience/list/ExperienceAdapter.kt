package com.mnowak.curriculumvitae.feature.moreInfo.fragment.experience.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.updateMargins
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mnowak.curriculumvitae.databinding.ItemExperienceCompanyBinding
import com.mnowak.curriculumvitae.utils.getExperiencePointerMarginTop

class ExperienceAdapter(

        private val lifecycleOwner: LifecycleOwner

) : ListAdapter<ExperienceItemViewModel, ExperienceAdapter.ExperienceItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemExperienceCompanyBinding.inflate(layoutInflater, parent, false)
        itemBinding.setLifecycleOwner(lifecycleOwner)
        return ExperienceItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ExperienceItemViewHolder, position: Int) {
        val candidateJob = getItem(position)
        holder.bind(candidateJob)
    }

    inner class ExperienceItemViewHolder(

            private val itemBinding: ItemExperienceCompanyBinding

    ) : RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.vCompanyToMarkerTrianglePointer.layoutParams.apply {
                if (this is ViewGroup.MarginLayoutParams) {
                    updateMargins(top = getExperiencePointerMarginTop(itemBinding.root.resources))
                }
            }
        }

        fun bind(experienceItemViewModel: ExperienceItemViewModel) {
            itemBinding.viewModel = experienceItemViewModel
            itemBinding.executePendingBindings()
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ExperienceItemViewModel>() {

    override fun areItemsTheSame(oldItem: ExperienceItemViewModel, newItem: ExperienceItemViewModel): Boolean =
            oldItem.id.value == newItem.id.value

    override fun areContentsTheSame(oldItem: ExperienceItemViewModel, newItem: ExperienceItemViewModel): Boolean =
            oldItem == newItem
}
