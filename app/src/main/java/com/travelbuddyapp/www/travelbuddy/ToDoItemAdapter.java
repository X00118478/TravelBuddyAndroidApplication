package com.travelbuddyapp.www.travelbuddy;

import android.content.Context;
import android.widget.ArrayAdapter;

/**
 * Created by AlexMcQuade on 07/02/2018.
 */

public class ToDoItemAdapter extends ArrayAdapter<FillUpData> {

  /**
   * Adapter context
   */
  Context mContext;

  /**
   * Adapter View layout
   */
  int mLayoutResourceId;

  public ToDoItemAdapter(Context context, int layoutResourceId) {
    super(context, layoutResourceId);

    mContext = context;
    mLayoutResourceId = layoutResourceId;
  }
}
