package com.kyleriedemann.giantbombvideoplayer.Authentication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
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

import com.kyleriedemann.giantbombvideoplayer.Models.Key;
import com.kyleriedemann.giantbombvideoplayer.Network.GiantbombApiClient;
import com.kyleriedemann.giantbombvideoplayer.R;
import com.kyleriedemann.giantbombvideoplayer.UI.DefaultFragment;

import butterknife.ButterKnife;
import retrofit.RestAdapter;

/**
 * Created by kyle on 4/28/15.
 */
public class AuthenticationFragment extends DefaultFragment {

    String authCodeText = "";
    String apiKey = "";
    String url = "";

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_authentication, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ButterKnife.inject(this, view);

        Button authRequestButton = (Button) getActivity().findViewById(R.id.button_authenticaion);
        authRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText authCodeEditText = (EditText) getActivity().findViewById(R.id.edit_text_auth_code);

                String authCodeText = authCodeEditText.getText().toString();

                AsyncTest asyncTest = new AsyncTest();
                asyncTest.execute(authCodeText);

                Toast toast = Toast.makeText(getActivity(), "Getting your API key...", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        Button testPrefButton = (Button) getActivity().findViewById(R.id.button_pref_test);
        testPrefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                String apiKeyTest = sharedPref.getString("API_KEY", "No Saved API Key");

                Toast toast = Toast.makeText(getActivity(), apiKeyTest, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    public class AsyncTest extends AsyncTask<String, Void, Boolean> {

        private final String LOG_TAG = AsyncTest.class.getSimpleName();

        @Override
        protected Boolean doInBackground(String... params) {
            RestAdapter restAdapter = GiantbombApiClient.buildRestAdapter(true);
            GiantbombApiClient.Key giantbombApiClient = restAdapter.create(GiantbombApiClient.Key.class);

            Key key = giantbombApiClient.getApiKey(params[0], "json");
            apiKey = key.getApiKey();

            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if(aBoolean) {
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                //need to put in the api key from the model
                editor.putString("API_KEY", apiKey);
                editor.apply();
            }
        }
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
    public boolean shouldDisplayHomeUp() {
        return super.shouldDisplayHomeUp();
    }
}
