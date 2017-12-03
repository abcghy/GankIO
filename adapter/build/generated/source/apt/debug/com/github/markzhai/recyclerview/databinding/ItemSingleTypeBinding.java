package com.github.markzhai.recyclerview.databinding;
import android.databinding.Bindable;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
public abstract class ItemSingleTypeBinding extends ViewDataBinding {
    // variables
    protected com.github.markzhai.recyclerview.Dummy mItem;
    protected com.github.markzhai.recyclerview.Dummy mPresenter;
    protected ItemSingleTypeBinding(@Nullable android.databinding.DataBindingComponent bindingComponent, @Nullable android.view.View root_, int localFieldCount
    ) {
        super(bindingComponent, root_, localFieldCount);
    }
    //getters and abstract setters
    public abstract void setItem(@Nullable com.github.markzhai.recyclerview.Dummy Item);
    @Nullable
    public com.github.markzhai.recyclerview.Dummy getItem() {
        return mItem;
    }
    public abstract void setPresenter(@Nullable com.github.markzhai.recyclerview.Dummy Presenter);
    @Nullable
    public com.github.markzhai.recyclerview.Dummy getPresenter() {
        return mPresenter;
    }
    @NonNull
    public static ItemSingleTypeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemSingleTypeBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ItemSingleTypeBinding bind(@NonNull android.view.View view) {
        return null;
    }
    @NonNull
    public static ItemSingleTypeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ItemSingleTypeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
    @NonNull
    public static ItemSingleTypeBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return null;
    }
}