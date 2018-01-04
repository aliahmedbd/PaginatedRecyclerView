package com.aliahmed.me.paginatedrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ali Ahmed on 12/3/16. Email: aliahmedaiub@gmail.com
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected static final int HEADER_VIEW = 0;
    protected static final int ITEM_VIEW = 1;
    protected static final int FOOTER_VIEW = 2;

    protected List<T> items;
    protected OnItemClickListener onItemClickListener;
    protected OnReloadClickListener onReloadClickListener;
    protected boolean isFooterAdded = false;

    public BaseAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case HEADER_VIEW:
                bindHeaderViewHolder(holder, position);
                break;
            case ITEM_VIEW:
                bindItemViewHolder(holder, position);
                break;
            case FOOTER_VIEW:
                bindFooterViewHolder(holder, position);
                break;
            default:
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case HEADER_VIEW:
                viewHolder = createHeaderViewHolder(parent);
                break;
            case ITEM_VIEW:
                viewHolder = createItemViewHolder(parent);
                break;
            case FOOTER_VIEW:
                viewHolder = createFooterViewHolder(parent);
                break;
            default:
                break;
        }
        return viewHolder;
    }

    protected abstract RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent);

    protected abstract RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent);

    protected abstract RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent);

    protected abstract void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder, int position);

    protected abstract void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position);

    protected abstract void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder, int position);

    protected abstract void displayLoadMoreFooter();

    protected abstract void displayErrorFooter();

    protected abstract void addFooter();

    @Override
    public int getItemCount() {
        return items.size();
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public void addItem(T item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void addAllItem(List<T> items) {
        for (T item : items) {
            addItem(item);
        }
    }

    public void remove(T item) {
        int position = items.indexOf(item);
        if (position > -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isFooterAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public boolean isLastPosition(int position) {
        return (position == items.size() - 1);
    }

    public void removeFooter() {
        isFooterAdded = false;

        int position = items.size() - 1;
        T item = getItem(position);

        if (item != null) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void updateFooter(FooterType footerType) {
        switch (footerType) {
            case ERROR:
                displayLoadMoreFooter();
                break;
            case LOAD_MORE:
                displayErrorFooter();
                break;
            default:
                break;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    public interface OnReloadClickListener {
        void onReloadClick();
    }

    enum FooterType {
        LOAD_MORE,
        ERROR
    }
}
