package com.kyleriedemann.giantbombvideoplayer.Base;

import android.app.Fragment;
import android.os.Bundle;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragment extends Fragment {

    protected CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposables.dispose();
    }
}
