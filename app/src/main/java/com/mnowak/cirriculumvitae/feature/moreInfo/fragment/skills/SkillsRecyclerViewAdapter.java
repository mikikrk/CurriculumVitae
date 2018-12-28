package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.skills;

import android.content.Context;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.mnowak.cirriculumvitae.R;
import com.mnowak.cirriculumvitae.databinding.ItemSkillsBinding;
import com.mnowak.cirriculumvitae.model.SkillsViewModel;
import com.mnowak.cirriculumvitae.widgets.SkillTextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class SkillsRecyclerViewAdapter extends RecyclerView.Adapter<SkillsRecyclerViewAdapter.SkillsViewHolder> {
    
    private ItemSkillsBinding itemBinding;
    private List<SkillsViewModel> skills;

    SkillsRecyclerViewAdapter(List<SkillsViewModel> skills) {
        this.skills = skills;
    }
    
    @NonNull
    @Override
    public SkillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        itemBinding = ItemSkillsBinding.inflate(layoutInflater, parent, false);
        return new SkillsViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsViewHolder holder, int position) {
        SkillsViewModel studiesActivityViewModel = skills.get(position);
        holder.bind(studiesActivityViewModel);
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    @Override
    public void onViewRecycled(@NonNull SkillsViewHolder holder) {
        super.onViewRecycled(holder);
        holder.clear();
    }

    public class SkillsViewHolder extends RecyclerView.ViewHolder {
        private final ItemSkillsBinding itemBinding;
        private final Context context;

        private LinearLayout llSkills;
        private @Dimension int skillsHorizontalGap;
        private @Dimension int skillsVerticalGap;
        private int maxWidth;
        private int boxPadding;
        private SkillsViewModel skillsViewModel;

        public SkillsViewHolder(ItemSkillsBinding itemBinding) {
            super(itemBinding.getRoot());
            this.context = itemView.getContext();
            this.itemBinding = itemBinding;
            llSkills = itemBinding.llSkills;
            readResources();
        }

        private void readResources() {
            skillsHorizontalGap = context.getResources().getDimensionPixelSize(R.dimen.skill_items_horizontal_gap);
            skillsVerticalGap = context.getResources().getDimensionPixelSize(R.dimen.skill_items_vertical_gap);
            boxPadding = context.getResources().getDimensionPixelSize(R.dimen.data_box_padding);
            llSkills.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        }

        private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                if (llSkills.getWidth() > 0) {
                    llSkills.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    maxWidth = llSkills.getWidth() - 2 * boxPadding;
                    if (skillsViewModel != null) {
                        bind(skillsViewModel);
                    }
                }
            }
        };

        public void bind(SkillsViewModel skillsViewModel) {
            this.skillsViewModel = skillsViewModel;
            if (maxWidth > 0) {
                addSkills(skillsViewModel);
                itemBinding.setViewModel(skillsViewModel);
                itemBinding.executePendingBindings();
            }
        }

        private void addSkills(SkillsViewModel skillsViewModel) {
            LinkedList<SkillTextView> skillViews = new LinkedList<>();
            addSkills(skillViews, skillsViewModel.goodLevelSkills, SkillTextView.LevelColor.GOOD);
            addSkills(skillViews, skillsViewModel.mediumLevelSkills, SkillTextView.LevelColor.MEDIUM);
            addSkills(skillViews, skillsViewModel.lowLevelSkills, SkillTextView.LevelColor.LOW);
            appendSkillsToLayout(skillViews);
        }

        private void addSkills(LinkedList<SkillTextView> skillViews, List<String> skills, SkillTextView.LevelColor levelColor) {
            if (skills != null) {
                for (String skill : skills) {
                    SkillTextView skillTextView = new SkillTextView(context, levelColor, skill);
                    skillViews.addLast(skillTextView);
                }
            }
        }

        private void appendSkillsToLayout(LinkedList<SkillTextView> skillViews) {
            while (skillViews.size() > 0) {
                List<SkillTextView> oneLineViews = getOneLineViews(skillViews, maxWidth);
                putViewsIntoLayout(oneLineViews, skillViews.size() == 0);
            }
        }

        private List<SkillTextView> getOneLineViews(LinkedList<SkillTextView> skillViews, int maxWidth) {
            List<SkillTextView> oneLineViews = new ArrayList<>();
            Iterator<SkillTextView> skillIt = skillViews.iterator();
            final int maxCheckAfterMaxReached = 3;
            int checkedAfterMaxReached = 0;
            int measuredWidth = - skillsHorizontalGap;
            while (skillIt.hasNext() && checkedAfterMaxReached < maxCheckAfterMaxReached) {
                SkillTextView skillTextView = skillIt.next();
                int newMeasuredWidth = measuredWidth + skillsHorizontalGap + skillTextView.getMeasuredWidth();
                if (newMeasuredWidth < maxWidth) {
                    oneLineViews.add(skillTextView);
                    skillIt.remove();
                    measuredWidth = newMeasuredWidth;
                } else {
                    checkedAfterMaxReached++;
                }
            }
            return oneLineViews;
        }

        private void putViewsIntoLayout(List<SkillTextView> oneLineViews, boolean lastLine) {
            LinearLayout lineLinearLayout = prepareNewLineLinearLayout(lastLine);
            for (SkillTextView skillTextView : oneLineViews) {
                lineLinearLayout.addView(skillTextView);
            }
            llSkills.addView(lineLinearLayout);
        }

        @NonNull
        private LinearLayout prepareNewLineLinearLayout(boolean lastLine) {
            LinearLayout linearLayout = new LinearLayout(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (!lastLine) {
                layoutParams.bottomMargin = skillsVerticalGap;
            }
            linearLayout.setLayoutParams(layoutParams);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            return linearLayout;
        }


//        private void addEvents(SkillsViewModel skillsViewModel) {
//            for (int i = 0; i < skillsViewModel.events.size(); i++) {
//                SkillsViewModel.EventViewModel eventViewModel = skillsViewModel.events.get(i);
//                eventViewModel.prepare(itemView);
//                LinearLayout linearLayout = chooseGroupForEvent(skillsViewModel, i);
//                bindView(skillsViewModel, eventViewModel, linearLayout);
//            }
//        }
//
//        private void bindView(SkillsViewModel skillsViewModel, SkillsViewModel.EventViewModel eventViewModel, LinearLayout linearLayout) {
//            ViewCheckedEventBinding viewCheckedEventBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_checked_event, linearLayout, true);
//            viewCheckedEventBinding.setStudiesViewModel(skillsViewModel);
//            viewCheckedEventBinding.setEventViewModel(eventViewModel);
//            viewCheckedEventBinding.executePendingBindings();
//        }
//
//        private LinearLayout chooseGroupForEvent(SkillsViewModel skillsViewModel, int position) {
//            if (skillsViewModel.importantEvents > 0 && position >= skillsViewModel.importantEvents) {
//                return llRedundantEvents;
//            } else {
//                return llImportantEvents;
//            }
//        }
//
        public void clear() {
            llSkills.removeAllViews();
            skillsViewModel = null;
        }
    }
}
