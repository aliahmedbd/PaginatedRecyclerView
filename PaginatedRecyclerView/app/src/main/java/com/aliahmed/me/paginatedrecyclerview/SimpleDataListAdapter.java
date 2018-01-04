package com.aliahmed.me.paginatedrecyclerview;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ali Ahmed on 1/2/2018. Email: aliahmedaiub@gmail.com
 */

public class SimpleDataListAdapter extends BaseAdapter<SimpleDataModel> {
    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void displayLoadMoreFooter() {

    }

    @Override
    protected void displayErrorFooter() {

    }

    @Override
    protected void addFooter() {

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

    public static class  FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }


}
