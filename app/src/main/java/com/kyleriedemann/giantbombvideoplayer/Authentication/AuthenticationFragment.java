package com.kyleriedemann.giantbombvideoplayer.Authentication;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kyleriedemann.giantbombvideoplayer.Base.BaseFragment;
import com.kyleriedemann.giantbombvideoplayer.Base.RxCallback;
import com.kyleriedemann.giantbombvideoplayer.Base.RxSubscriber;
import com.kyleriedemann.giantbombvideoplayer.GiantbombApp;
import com.kyleriedemann.giantbombvideoplayer.Models.Key;
import com.kyleriedemann.giantbombvideoplayer.Network.GiantbombApiClient;
import com.kyleriedemann.giantbombvideoplayer.Network.ServiceGenerator;
import com.kyleriedemann.giantbombvideoplayer.R;
import com.kyleriedemann.giantbombvideoplayer.Utils.PrefManager;

import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by kyle on 4/28/15.
 */
public class AuthenticationFragment extends BaseFragment implements RxCallback<Key> {

    public static final String API_KEY = "API_KEY";
    public static final String FAILED_TO_RETRIEVE_API_KEY = "Failed to retrieve API key";
    public static final String GETTING_YOUR_API_KEY = "Getting your API key...";

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_authentication, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        Button authRequestButton = (Button) getActivity().findViewById(R.id.button_authenticaion);
        authRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText authCodeEditText = (EditText) getActivity().findViewById(R.id.edit_text_auth_code);

                String authCodeText = authCodeEditText.getText().toString().trim();

                authenticate(authCodeText);

                Snackbar.make(view, GETTING_YOUR_API_KEY, Snackbar.LENGTH_LONG).show();
            }
        });

        Button testPrefButton = (Button) getActivity().findViewById(R.id.button_pref_test);
        testPrefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apiKeyTest = PrefManager.with(GiantbombApp.instance()).getString(API_KEY, "No Saved API Key");

                Snackbar.make(view, apiKeyTest, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void authenticate(String authCode){
        GiantbombApiClient client = ServiceGenerator.createService(GiantbombApiClient.class);

        mCompositeSubscription.add(client.getApiKey(authCode, "json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<>(this)));
    }

    @Override
    public void onDataReady(Key data) {
        String apiKey = data.getApiKey();

        PrefManager.with(GiantbombApp.instance()).save(API_KEY, apiKey);
    }

    @Override
    public void onDataError(Throwable e) {
        View view = getView();

        if (view != null)
            Snackbar.make(view,  FAILED_TO_RETRIEVE_API_KEY, Snackbar.LENGTH_LONG).show();
        else
            Toast.makeText(getContext(), FAILED_TO_RETRIEVE_API_KEY, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackStackChanged() {
        super.onBackStackChanged();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_authentication;
    }
}
