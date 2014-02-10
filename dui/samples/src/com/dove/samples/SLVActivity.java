
package com.dove.samples;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dove.ui.SwipeableListView;

public class SLVActivity extends Activity {
    SwipeableListView mSwipeableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slv);
        mSwipeableListView = (SwipeableListView) findViewById(android.R.id.list);
        mSwipeableListView.setAdapter(new SwipeableListAdapter());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slv, menu);
        return true;
    }

    class SwipeableListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return "I'm front " + position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = new SListItemView(SLVActivity.this);
                vh.mTextView = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            String text = (String) getItem(position);
            vh.mTextView.setText(text);
            return convertView;
        }

        class ViewHolder {
            TextView mTextView;
        }
    }
}
