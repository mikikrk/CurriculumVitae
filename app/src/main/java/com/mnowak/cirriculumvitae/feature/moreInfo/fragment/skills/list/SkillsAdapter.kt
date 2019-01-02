package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.skills.list

import android.content.Context
import androidx.annotation.Dimension
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout

import com.mnowak.cirriculumvitae.R
import com.mnowak.cirriculumvitae.databinding.ItemSkillsBinding
import com.mnowak.cirriculumvitae.data.model.SkillsSet
import com.mnowak.cirriculumvitae.widgets.SkillTextView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_skills.*

import java.util.ArrayList
import java.util.LinkedList

class SkillsAdapter(

        private val skills: List<SkillsSet>

) : RecyclerView.Adapter<SkillsAdapter.SkillsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemSkillsBinding.inflate(layoutInflater, parent, false).apply { }
        return SkillsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) {
        val studiesActivityViewModel = skills[position]
        holder.bind(studiesActivityViewModel)
    }

    override fun getItemCount(): Int {
        return skills.size
    }

    override fun onViewRecycled(holder: SkillsViewHolder) {
        super.onViewRecycled(holder)
        holder.clear()
    }

    class SkillsViewHolder(

            private val itemBinding: ItemSkillsBinding

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
        private var skillsSet: SkillsSet? = null

        private val onGlobalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {
                if (llSkills.width > 0) {
                    llSkills.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    maxWidth = llSkills.width - 2 * boxPadding
                    skillsSet?.let {
                        bind(skillsSet)
                    }
                }
            }
        }

        init {
            llSkills.viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
        }

        fun bind(skillsSet: SkillsSet?) {
            this.skillsSet = skillsSet
            if (maxWidth != null) {
                addSkills(skillsSet!!)
                itemBinding.viewModel = skillsSet
                itemBinding.executePendingBindings()
            }
        }

        private fun addSkills(skillsSet: SkillsSet) {
            val skillViews = LinkedList<SkillTextView>()
            addSkills(skillViews, skillsSet.goodLevelSkills, SkillTextView.LevelColor.GOOD)
            addSkills(skillViews, skillsSet.mediumLevelSkills, SkillTextView.LevelColor.MEDIUM)
            addSkills(skillViews, skillsSet.lowLevelSkills, SkillTextView.LevelColor.LOW)
            appendSkillsToLayout(skillViews)
        }

        private fun addSkills(skillViews: LinkedList<SkillTextView>, skills: List<String>?, levelColor: SkillTextView.LevelColor) {
            if (skills != null) {
                for (skill in skills) {
                    val skillTextView = SkillTextView(context, levelColor, skill)
                    skillViews.addLast(skillTextView)
                }
            }
        }

        private fun appendSkillsToLayout(skillViews: LinkedList<SkillTextView>) {
            while (skillViews.size > 0) {
                val oneLineViews = getOneLineViews(skillViews, maxWidth ?: 0)
                putViewsIntoLayout(oneLineViews, skillViews.size == 0)
            }
        }

        private fun getOneLineViews(skillViews: LinkedList<SkillTextView>, maxWidth: Int): List<SkillTextView> {
            val oneLineViews = ArrayList<SkillTextView>()
            val skillIt = skillViews.iterator()
            val maxCheckAfterMaxReached = 3
            var checkedAfterMaxReached = 0
            var measuredWidth = -skillsHorizontalGap
            while (skillIt.hasNext() && checkedAfterMaxReached < maxCheckAfterMaxReached) {
                val skillTextView = skillIt.next()
                val newMeasuredWidth = measuredWidth + skillsHorizontalGap + skillTextView.measuredWidth
                if (newMeasuredWidth < maxWidth) {
                    oneLineViews.add(skillTextView)
                    skillIt.remove()
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
            skillsSet = null
        }
    }
}
