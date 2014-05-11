package com.dove.reader.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.DataSetObservable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dove.common.log.LogTag;
import com.dove.common.log.LogUtils;
import com.dove.lib.oeb.ncx.NCX;
import com.dove.reader.R;
import com.dove.reader.ui.controller.AccountController;
import com.dove.reader.ui.controller.DrawerController;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ReaderActivity extends AbstractReaderActivity
    implements DrawerFragment.NavigationDrawerCallbacks, AccountController, DrawerController {
    private static final String LOG_TAG = LogTag.getLogTag();

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private DrawerFragment mDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    /**
     * Listeners that are interested in changes to the current account.
     */
    private final DataSetObservable mAccountObservable = new DataSetObservable();

    /**
     * Listeners that are interested in changes to the drawer state..
     */
    private final DataSetObservable mDrawerObservable = new DataSetObservable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        mDrawerFragment = (DrawerFragment)
            getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        FragmentStatePagerAdapter f;
        // Set up the drawer.
        mDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        AssetManager assetManager = getAssets();
        Log.d(LOG_TAG, "Begin to read all the file in assets");
        try {
            InputStream tocFile = assetManager.open("toc.ncx");
            NCX ncx = new NCX();
            ncx.onParse(tocFile);
            LogUtils.i(LOG_TAG, ncx.toString());

            File file = new File(Environment.getExternalStorageDirectory(), "ebooks/");
            if (!file.exists()) {
                file.mkdir();
            }
            Log.i(LOG_TAG, file.getAbsolutePath());
            File toc = new File(file, "test.ncx");
            Log.i(LOG_TAG, toc.getAbsolutePath());

            OutputStream outputStream = new FileOutputStream(toc);
            ncx.onSrerialize(outputStream);

        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    private static final int FILE_SELECT_CODE = 0;

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                Intent.createChooser(intent, "Select a File to Upload"),
                FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d(LOG_TAG, "File Uri: " + uri.toString());
                    // Get the path
//                    String path = FileUtils.getPath(this, uri);
//                    Log.d(LOG_TAG, "File Path: " + path);
                    // Get the file instance
                    // File file = new File(path);
                    // Initiate the upload
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        final FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
            .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
            .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.reader, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((ReaderActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    @Override
    public DataSetObservable getAccountObservable() {
        return mAccountObservable;
    }

    @Override
    public DataSetObservable getDrawerObservable() {
        return mDrawerObservable;
    }
}
