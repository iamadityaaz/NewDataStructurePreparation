package kvadityaaz.forhitesh.com.newdatastructurepreparation;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

//    public String question;
//    public int i;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    ArrayList<String> quest = new ArrayList<>();
    ArrayList<String> ans = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);



        //url to make request
        String myurl = "https://learncodeonline.in/api/android/datastructure.json";

        JsonObjectRequest myjsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myurl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("response", "is " + response);

                        try {

                            JSONArray jsonArrayofmine = response.getJSONArray("questions");


                            String jsonString = jsonArrayofmine.toString();


                            Log.i("jsonarray", "is " + jsonArrayofmine);


                            for (int i = 0; i < jsonArrayofmine.length(); i++) {
                                //taking quesstion and answer
                                JSONObject jsonObjectinarray = jsonArrayofmine.getJSONObject(i);

                                String question = jsonObjectinarray.getString("question");
                                String answer = jsonObjectinarray.getString("Answer");
// mExampleAdapter = new ExampleAdapter(MainActivity.this, mExampleList);

//
                                quest.add(i, question);

                                ans.add(i, answer);

                            }

                            // Create the adapter that will return a fragment for each of the three
                            // primary sections of the activity.


                            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), quest, ans);

                            // Set up the ViewPager with the sections adapter.
                            mViewPager = (ViewPager) findViewById(R.id.container);
                            mViewPager.setAdapter(mSectionsPagerAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //show snackbar
            }
        }

        );
        mysingleton.getInstance(getApplicationContext()).addToRequestque(myjsonObjectRequest);





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "HAVE A GOOD DAY", Toast.LENGTH_SHORT).show();
            moveTaskToBack(true);
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
        private static final String ARG_SECTION_NUMBER1 = "section_number1";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(String text, String s) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            PlaceholderFragment fragment1 = new PlaceholderFragment();
            Bundle args = new Bundle();
            Bundle args1 = new Bundle();
            args.putString(ARG_SECTION_NUMBER, text);
            args.putString(ARG_SECTION_NUMBER1, s);
            fragment.setArguments(args);
            fragment1.setArguments(args1);
            return fragment;

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            final TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            final TextView answer = (TextView) rootView.findViewById(R.id.section_label2);
            textView.setText(getArguments().getString(ARG_SECTION_NUMBER));
            answer.setText(getArguments().getString(ARG_SECTION_NUMBER1));

            WebView view = (WebView) rootView.findViewById(R.id.adPlay);
            view.loadUrl("file:///android_asset/aaaa.gif");
            view.getSettings().setLoadWithOverviewMode(true);
            view.getSettings().setUseWideViewPort(true);


            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP){
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://learncodeonline.in"));
                        startActivity(browserIntent);
                        return true;
                    }

                    return false;
                }
            });

            FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.floatingActionButton);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, "Question:\n" + textView.getText().toString() +"\n\nAnswer:\n" + answer.getText().toString());
                    Intent ic=Intent.createChooser(intent,"Share this Question and Answer");
                    startActivity(ic);
                }
            });
            return rootView;

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        ArrayList<String> quest = new ArrayList<>();
        ArrayList<String> ans = new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm, ArrayList<String> quest, ArrayList<String> ans) {
            super(fm);
            this.quest = quest;
            this.ans = ans;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(quest.get(position), ans.get(position));
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 6;
        }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "SECTION 1";
//                case 1:
//                    return "SECTION 2";
//                case 2:
//                    return "SECTION 3";
//            }
//            return null;
//        }
    }
}


