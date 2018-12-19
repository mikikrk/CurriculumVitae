package com.example.mnowak.cirriculumvitae.feature.moreInfo.fragment.studiesActivities;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mnowak.cirriculumvitae.R;
import com.example.mnowak.cirriculumvitae.databinding.ItemStudiesActivityBinding;
import com.example.mnowak.cirriculumvitae.databinding.ViewCheckedEventBinding;
import com.example.mnowak.cirriculumvitae.model.StudiesActivityViewModel;

import java.util.List;

public class StudiesActivitiesRecyclerViewAdapter extends RecyclerView.Adapter<StudiesActivitiesRecyclerViewAdapter.ActivityViewHolder> {

    private List<StudiesActivityViewModel> studiesActivityViewModels;
    private ItemStudiesActivityBinding itemBinding;

    StudiesActivitiesRecyclerViewAdapter(List<StudiesActivityViewModel> studiesActivityViewModels) {
        this.studiesActivityViewModels = studiesActivityViewModels;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        itemBinding = ItemStudiesActivityBinding.inflate(layoutInflater, parent, false);
        return new ActivityViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        StudiesActivityViewModel studiesActivityViewModel = studiesActivityViewModels.get(position);
        holder.bind(studiesActivityViewModel);
    }

    @Override
    public int getItemCount() {
        return studiesActivityViewModels.size();
    }

    @Override
    public void onViewRecycled(@NonNull ActivityViewHolder holder) {
        super.onViewRecycled(holder);
        holder.clear();
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {
        private final ItemStudiesActivityBinding itemBinding;
        private final LayoutInflater layoutInflater;

        private LinearLayout llImportantEvents;
        private LinearLayout llRedundantEvents;

        ActivityViewHolder(ItemStudiesActivityBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
            llImportantEvents = itemBinding.llImportantEvents;
            llRedundantEvents = itemBinding.llRedundantEvents;
            layoutInflater = LayoutInflater.from(itemView.getContext());
        }

        public void bind(StudiesActivityViewModel studiesActivitiesViewModel) {
            addEvents(studiesActivitiesViewModel);
            itemBinding.setViewModel(studiesActivitiesViewModel);
            itemBinding.executePendingBindings();
        }

        private void addEvents(StudiesActivityViewModel studiesActivitiesViewModel) {
            for (int i = 0; i < studiesActivitiesViewModel.events.size(); i++) {
                StudiesActivityViewModel.EventViewModel eventViewModel = studiesActivitiesViewModel.events.get(i);
                eventViewModel.prepare(itemView);
                LinearLayout linearLayout = chooseGroupForEvent(studiesActivitiesViewModel, i);
                bindView(studiesActivitiesViewModel, eventViewModel, linearLayout);
            }
        }

        private void bindView(StudiesActivityViewModel studiesActivitiesViewModel, StudiesActivityViewModel.EventViewModel eventViewModel, LinearLayout linearLayout) {
            ViewCheckedEventBinding viewCheckedEventBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_checked_event, linearLayout, true);
            viewCheckedEventBinding.setStudiesViewModel(studiesActivitiesViewModel);
            viewCheckedEventBinding.setEventViewModel(eventViewModel);
            viewCheckedEventBinding.executePendingBindings();
        }

        private LinearLayout chooseGroupForEvent(StudiesActivityViewModel studiesActivitiesViewModel, int position) {
            if (studiesActivitiesViewModel.importantEvents > 0 && position >= studiesActivitiesViewModel.importantEvents) {
                return llRedundantEvents;
            } else {
                return llImportantEvents;
            }
        }

        public void clear() {
            llImportantEvents.removeAllViews();
            llRedundantEvents.removeAllViews();
        }
    }
}
