package com.example.android.androidskeletonapp.ui.enrollment_form;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.androidskeletonapp.R;

import org.apache.commons.lang3.tuple.Triple;
import org.hisp.dhis.android.core.program.ProgramTrackedEntityAttribute;
import org.hisp.dhis.android.core.trackedentity.TrackedEntityAttribute;
import org.hisp.dhis.android.core.trackedentity.TrackedEntityAttributeValue;
import org.hisp.dhis.android.core.trackedentity.TrackedEntityAttributeValueObjectRepository;

import java.util.ArrayList;
import java.util.List;

public class FormAdapter extends RecyclerView.Adapter<FieldHolder> {

    private List<Triple<ProgramTrackedEntityAttribute, TrackedEntityAttribute, TrackedEntityAttributeValueObjectRepository>> fields;

    public FormAdapter() {
        fields = new ArrayList();
    }

    @NonNull
    @Override
    public FieldHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FieldHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_field, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FieldHolder holder, int position) {
        holder.bind(fields.get(position));
    }

    @Override
    public int getItemCount() {
        return fields.size();
    }

    public void updateData(List<Triple<ProgramTrackedEntityAttribute, TrackedEntityAttribute, TrackedEntityAttributeValueObjectRepository>> updates) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return fields.size();
            }

            @Override
            public int getNewListSize() {
                return updates.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return fields.get(oldItemPosition).getMiddle().uid().equals(updates.get(newItemPosition).getMiddle().uid());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return fields.get(oldItemPosition) == updates.get(newItemPosition);
            }
        });

        fields.clear();
        fields.addAll(updates);

        diffResult.dispatchUpdatesTo(this);
    }
}
