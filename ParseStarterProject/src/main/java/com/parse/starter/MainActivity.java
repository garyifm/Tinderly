/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

import com.parse.ParseAnalytics;
import com.parse.ParseQuery;

import java.util.List;


public class MainActivity extends ActionBarActivity {

  EditText txt;
  TextView txtV;
  Button btn;
  String message = "";
  int i = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btn = (Button)findViewById(R.id.button);
    txtV = (TextView)findViewById(R.id.textView);
    txt = (EditText)findViewById(R.id.editText);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());


    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        ParseObject testObject = new ParseObject("TestObject");
        message = txt.getText().toString();
        i++;
        testObject.put("message", message);
        testObject.put("count",i);
        testObject.saveInBackground();


        ParseQuery<ParseObject> query  = ParseQuery.getQuery("TestObject");

        query.findInBackground(new FindCallback<ParseObject>() {
          @Override
          public void done(List<ParseObject> parseObjects,ParseException e) {
            if (e == null) {
              for (ParseObject obj: parseObjects) {
                Log.d("blabla:", obj.getString("message") != null ? obj.getString("message") : obj.getString("foo"));
              }

              Log.d("THE OBJECT", "" + parseObjects.size());
              String name = parseObjects.toString();
              Log.d("THE QUERY ", "" + name);


            } else {
              Log.d("ERROR:", "" + e.getMessage());
            }
          }
        });


      }
    });



  }

//sdf

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
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
