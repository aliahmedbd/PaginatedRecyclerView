package com.aliahmed.me.paginatedrecyclerview;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Ali Ahmed on 1/2/2018. Email: aliahmedaiub@gmail.com
 */

public class SimpleDataListAdapter extends BaseAdapter<SimpleDataModel> {

    private FooterViewHolder footerViewHolder;

    SimpleDataListAdapter() {
        super();
    }

    @Override
    public int getItemViewType(int position) {
        return (isLastPosition(position) && isFooterAdded) ? FOOTER_VIEW : ITEM_VIEW;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_list_item_adapter, parent, false);

        final SimpleDataViewHolder dataViewHolder = new SimpleDataViewHolder(v);

        dataViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = dataViewHolder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(adapterPosition, dataViewHolder.itemView);
                    }
                }
            }
        });
        return dataViewHolder;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_adapter_footer, parent, false);
        final FooterViewHolder footerViewHolder = new FooterViewHolder(v);
        footerViewHolder.reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReloadClickListener != null) {
                    onReloadClickListener.onReloadClick();
                }
            }
        });

        return footerViewHolder;
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final SimpleDataViewHolder simpleDataViewHolder = (SimpleDataViewHolder) viewHolder;
        final SimpleDataModel simpleDataModel = getItem(position);
        if (simpleDataModel != null) {
            simpleDataViewHolder.bind(simpleDataModel);
        }

    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        FooterViewHolder holder = (FooterViewHolder) viewHolder;
        footerViewHolder = holder;
    }

    @Override
    protected void displayLoadMoreFooter() {
        if (footerViewHolder != null) {
            footerViewHolder.errorRelativeLayout.setVisibility(View.GONE);
            footerViewHolder.loadingFrameLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void displayErrorFooter() {
        if (footerViewHolder != null) {
            footerViewHolder.loadingFrameLayout.setVisibility(View.GONE);
            footerViewHolder.errorRelativeLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void addFooter() {
        isFooterAdded = true;
        addItem(new SimpleDataModel());
    }


    public static class SimpleDataViewHolder extends RecyclerView.ViewHolder {

        TextView txtName;
        TextView txtMobile;
        TextView txtDept;

        public SimpleDataViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtMobile = (TextView) itemView.findViewById(R.id.txtMobileNumber);
            txtDept = (TextView) itemView.findViewById(R.id.txtDeptName);
        }

        private void bind(SimpleDataModel simpleDataModel) {
            txtName.setText(simpleDataModel.getName());
            txtMobile.setText(simpleDataModel.getMobileNumber());
            txtDept.setText(simpleDataModel.getDept());

            int adapterPosition = getAdapterPosition();
            ViewCompat.setTransitionName(txtName, "My Transition " + adapterPosition);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {

        FrameLayout loadingFrameLayout;
        RelativeLayout errorRelativeLayout;
        ProgressBar loadingImageView;
        Button reloadButton;

        public FooterViewHolder(View itemView) {
            super(itemView);
            loadingFrameLayout = (FrameLayout) itemView.findViewById(R.id.loading_fl);
            errorRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.error_rl);
            loadingImageView = (ProgressBar) itemView.findViewById(R.id.progressLoading);
            reloadButton = (Button) itemView.findViewById(R.id.reload_btn);
        }
    }


}
