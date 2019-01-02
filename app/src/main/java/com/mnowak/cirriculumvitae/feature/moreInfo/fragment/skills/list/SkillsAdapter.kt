package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.skills.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import androidx.annotation.Dimension
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mnowak.cirriculumvitae.R
import com.mnowak.cirriculumvitae.databinding.ItemSkillsBinding
import com.mnowak.cirriculumvitae.widgets.SkillTextView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_skills.*
import java.util.*

class SkillsAdapter(

        private val lifecycleOwner: LifecycleOwner

) : ListAdapter<SkillsSetItemViewModel, SkillsAdapter.SkillsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemSkillsBinding.inflate(layoutInflater, parent, false).apply { }
        itemBinding.setLifecycleOwner(lifecycleOwner)
        return SkillsViewHolder(itemBinding, lifecycleOwner)
    }

    override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) {
        val studiesActivityViewModel = getItem(position)
        holder.bind(studiesActivityViewModel)
    }

    override fun onViewRecycled(holder: SkillsViewHolder) {
        super.onViewRecycled(holder)
        holder.clear()
    }

    class SkillsViewHolder(

            private val itemBinding: ItemSkillsBinding,
            private val lifecycleOwner: LifecycleOwner

    ) : RecyclerView.ViewHolder(itemBinding.root),
            LayoutContainer {

        override val containerView: View = itemView

        private val context: Context = itemView.context

        @Dimension
        private val skillsHorizontalGap = context.resources.getDimensionPixelSize(R.dimen.skill_items_horizontal_gap)
        @Dimension
        private val skillsVerticalGap = context.resources.getDimensionPixelSize(R.dimen.skill_items_vertical_gap)
        @Dimension
        private val boxPadding = context.resources.getDimensionPixelSize(R.dimen.data_box_padding)

        private var maxWidth: Int? = null
        private var viewModel: SkillsSetItemViewModel? = null

        private val onGlobalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {
                if (llSkills.width > 0) {
                    llSkills.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    maxWidth = llSkills.width - 2 * boxPadding
                    viewModel?.let { bind(it) }
                }
            }
        }

        init {
            llSkills.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
        }

        fun bind(viewModel: SkillsSetItemViewModel) {
            this.viewModel = viewModel
            if (maxWidth != null) {
                viewModel.skills.observe(lifecycleOwner, skillsObserver)
                itemBinding.viewModel = viewModel
                itemBinding.executePendingBindings()
            }
        }

        private val skillsObserver = Observer<List<SkillsSetItemViewModel.SkillItemViewModel>> { skillItems ->
            llSkills.removeAllViews()
            val skillViews = mutableListOf<SkillTextView>()
            skillItems.forEach { skillItem ->
                val skillTextView = prepareSkillTextView(skillItem)
                skillViews.add(skillTextView)
            }
            appendSkillsToLayout(skillViews)
        }

        private fun prepareSkillTextView(skillItem: SkillsSetItemViewModel.SkillItemViewModel): SkillTextView {
            val skillTextView = SkillTextView(context)
            val skillNameObserver = prepareSkillNameObserver(skillTextView)
            val skillsLevelObserver = prepareSkillLevelObserver(skillTextView)
            skillTextView.onAttachedToWindow = {
                addSkillObservers(skillItem, skillNameObserver, skillsLevelObserver)
            }
            skillTextView.onDetachedFromWindow = {
                removeSkillObservers(skillItem, skillNameObserver, skillsLevelObserver)
            }
            return skillTextView
        }

        private fun addSkillObservers(skillItem: SkillsSetItemViewModel.SkillItemViewModel, skillNameObserver: Observer<String>, skillsSkillLevelObserver: Observer<SkillsSetItemViewModel.SkillItemViewModel.SkillLevel>) {
            skillItem.name.observe(lifecycleOwner, skillNameObserver)
            skillItem.skillLevel.observe(lifecycleOwner, skillsSkillLevelObserver)
        }

        private fun removeSkillObservers(skillItem: SkillsSetItemViewModel.SkillItemViewModel, skillNameObserver: Observer<String>, skillsSkillLevelObserver: Observer<SkillsSetItemViewModel.SkillItemViewModel.SkillLevel>) {
            skillItem.name.removeObserver(skillNameObserver)
            skillItem.skillLevel.removeObserver(skillsSkillLevelObserver)
        }

        private fun prepareSkillNameObserver(skillTextView: SkillTextView) =
                Observer<String> { skillTextView.text = it }

        private fun prepareSkillLevelObserver(skillTextView: SkillTextView) = Observer<SkillsSetItemViewModel.SkillItemViewModel.SkillLevel> {
            val levelColor = getColorFromType(it)
            skillTextView.setColor(levelColor)
        }

        private fun getColorFromType(skillSkillLevel: SkillsSetItemViewModel.SkillItemViewModel.SkillLevel?): SkillTextView.LevelColor =
                when (skillSkillLevel) {
                    SkillsSetItemViewModel.SkillItemViewModel.SkillLevel.GOOD -> SkillTextView.LevelColor.GOOD
                    SkillsSetItemViewModel.SkillItemViewModel.SkillLevel.MEDIUM -> SkillTextView.LevelColor.MEDIUM
                    SkillsSetItemViewModel.SkillItemViewModel.SkillLevel.LOW -> SkillTextView.LevelColor.LOW
                    else -> SkillTextView.LevelColor.MEDIUM
                }

        private fun appendSkillsToLayout(skillViews: MutableList<SkillTextView>) {
            while (skillViews.size > 0) {
                val oneLineViews = getOneLineViews(skillViews, maxWidth ?: 0)
                putViewsIntoLayout(oneLineViews, skillViews.size == 0)
            }
        }

        private fun getOneLineViews(skillViews: MutableList<SkillTextView>, maxWidth: Int): List<SkillTextView> {
            val oneLineViews = ArrayList<SkillTextView>()
            val skillIterator = skillViews.iterator()
            val maxCheckAfterMaxReached = 3
            var checkedAfterMaxReached = 0
            var measuredWidth = -skillsHorizontalGap * 2
            while (skillIterator.hasNext() && checkedAfterMaxReached < maxCheckAfterMaxReached) {
                val skillTextView = skillIterator.next()
                val newMeasuredWidth = measuredWidth + skillsHorizontalGap + skillTextView.measuredWidth
                if (newMeasuredWidth < maxWidth) {
                    oneLineViews.add(skillTextView)
                    skillIterator.remove()
                    measuredWidth = newMeasuredWidth
                } else {
                    checkedAfterMaxReached++
                }
            }
            return oneLineViews
        }

        private fun putViewsIntoLayout(oneLineViews: List<SkillTextView>, lastLine: Boolean) {
            val lineLinearLayout = prepareNewLineLinearLayout(lastLine)
            for (skillTextView in oneLineViews) {
                lineLinearLayout.addView(skillTextView)
            }
            llSkills.addView(lineLinearLayout)
        }

        private fun prepareNewLineLinearLayout(lastLine: Boolean): LinearLayout {
            val linearLayout = LinearLayout(context)
            val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            if (!lastLine) {
                layoutParams.bottomMargin = skillsVerticalGap
            }
            linearLayout.layoutParams = layoutParams
            linearLayout.orientation = LinearLayout.HORIZONTAL
            return linearLayout
        }

        fun clear() {
            llSkills.removeAllViews()
            viewModel?.skills?.removeObserver(skillsObserver)
            viewModel = null
        }
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SkillsSetItemViewModel>() {

    override fun areItemsTheSame(oldItem: SkillsSetItemViewModel, newItem: SkillsSetItemViewModel): Boolean =
            oldItem.id.value == newItem.id.value

    override fun areContentsTheSame(oldItem: SkillsSetItemViewModel, newItem: SkillsSetItemViewModel): Boolean =
            oldItem == newItem
}
