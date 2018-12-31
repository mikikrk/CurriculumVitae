package com.mnowak.cirriculumvitae.feature.moreInfo.fragment.studiesActivities;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mnowak.cirriculumvitae.R;
import com.mnowak.cirriculumvitae.databinding.ItemStudiesActivityBinding;
import com.mnowak.cirriculumvitae.databinding.ViewCheckedEventBinding;
import com.mnowak.cirriculumvitae.data.model.StudiesActivity;

import java.util.List;

public class StudiesActivitiesRecyclerViewAdapter extends RecyclerView.Adapter<StudiesActivitiesRecyclerViewAdapter.ActivityViewHolder> {

    private List<StudiesActivity> studiesActivity;
    private ItemStudiesActivityBinding itemBinding;

    StudiesActivitiesRecyclerViewAdapter(List<StudiesActivity> studiesActivity) {
        this.studiesActivity = studiesActivity;
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
        StudiesActivity studiesActivity = this.studiesActivity.get(position);
        holder.bind(studiesActivity);
    }

    @Override
    public int getItemCount() {
        return studiesActivity.size();
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

        public void bind(StudiesActivity studiesActivitiesViewModel) {
            addEvents(studiesActivitiesViewModel);
            itemBinding.setViewModel(studiesActivitiesViewModel);
            itemBinding.executePendingBindings();
        }

        private void addEvents(StudiesActivity studiesActivitiesViewModel) {
            for (int i = 0; i < studiesActivitiesViewModel.getEvents().size(); i++) {
                StudiesActivity.Event event = studiesActivitiesViewModel.getEvents().get(i);
                //TODO prepare view
                LinearLayout linearLayout = chooseGroupForEvent(studiesActivitiesViewModel, i);
                bindView(studiesActivitiesViewModel, event, linearLayout);
            }
        }

        private void bindView(StudiesActivity studiesActivitiesViewModel, StudiesActivity.Event event, LinearLayout linearLayout) {
            ViewCheckedEventBinding viewCheckedEventBinding = DataBindingUtil.inflate(layoutInflater, R.layout.view_checked_event, linearLayout, true);
            viewCheckedEventBinding.setStudiesViewModel(studiesActivitiesViewModel);
            viewCheckedEventBinding.setEventViewModel(event);
            viewCheckedEventBinding.executePendingBindings();
        }

        private LinearLayout chooseGroupForEvent(StudiesActivity studiesActivitiesViewModel, int position) {
            if (studiesActivitiesViewModel.getImportantEvents() > 0 && position >= studiesActivitiesViewModel.getImportantEvents()) {
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
