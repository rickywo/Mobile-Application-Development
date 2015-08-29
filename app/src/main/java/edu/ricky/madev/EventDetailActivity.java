package edu.ricky.madev;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.ricky.madev.controller.InviteeArrayAdapter;
import edu.ricky.madev.model.Event;
import edu.ricky.madev.model.Invitee;
import edu.ricky.madev.model.MovieModel;


public class EventDetailActivity extends ActionBarActivity {
    private static final int CONTACT_PICKER_RESULT = 1001;
    /** Called when the activity is first created. */
    TextView eventName;
    TextView eventDate;
    TextView eventTime;
    TextView eventVenue;
    TextView eventLocation;
    ListView inviteesList;
    LinearLayout timePicker, datePicker, venueButton, posButton;
    InviteeArrayAdapter inviteeArrayAdapter;
    Button addInviteeBtn;
    // model
    Event event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        getViews();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            event = (Event) getIntent().getSerializableExtra("event");

            setContent();
            setVenueOnClickListener();
            List<Invitee> il = event.getInvitees();
            il.add(new Invitee("Ricky", "0475412639"));
            il.add(new Invitee("Jackey", "0987654321"));
            Log.i("MAD", "onCreate");
            inviteeArrayAdapter = new InviteeArrayAdapter(this, event.getInvitees());
            inviteesList.setAdapter(inviteeArrayAdapter);
            setListViewHeightBasedOnChildren(inviteesList);
        }
        else {
            showEventNameDialog();
            event = new Event(eventName.getText().toString());
        }
        setContent();
        setVenueOnClickListener();
        List<Invitee> il = event.getInvitees();
        inviteeArrayAdapter = new InviteeArrayAdapter(this, event.getInvitees());
        inviteesList.setAdapter(inviteeArrayAdapter);
        setListViewHeightBasedOnChildren(inviteesList);
        inviteesList.setOnItemClickListener(
                new ListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            /*Intent intent = new Intent(getBaseContext(), EventDetailActivity.class);
                            Event ev = (Event) eventList.getItemAtPosition(position);
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("event", ev);
                            intent.putExtras(bundle);
                            startActivity(intent);*/
                    }
                }
        );

        timePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTimePickerDialog();
                    }
                }
        );

        datePicker.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDatePickerDialog();
                    }
                }
        );

        addInviteeBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                        startActivityForResult(intent, CONTACT_PICKER_RESULT);
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_detail, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CONTACT_PICKER_RESULT:
                    //final EditText phoneInput = (EditText) findViewById(R.id.phoneNumberInput);
                    Cursor cursor = null;
                    String phoneNumber = "", name = "";
                    List<String> allNumbers = new ArrayList<String>();
                    int phoneIdx = 0;
                    int nameIdx = 0;
                    try {
                        Uri result = data.getData();
                        String id = result.getLastPathSegment();
                        cursor = getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + "=?", new String[] { id }, null);
                        phoneIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA);
                        nameIdx = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                        if (cursor.moveToFirst()) {
                            while (cursor.isAfterLast() == false) {
                                phoneNumber = cursor.getString(phoneIdx);
                                name = cursor.getColumnName(nameIdx);
                                allNumbers.add(phoneNumber);
                                cursor.moveToNext();
                            }
                        } else {
                            //no results actions
                        }
                    } catch (Exception e) {
                        //error actions
                    } finally {
                        if (cursor != null) {
                            cursor.close();
                        }

                        final CharSequence[] items = allNumbers.toArray(new String[allNumbers.size()]);
                        AlertDialog.Builder builder = new AlertDialog.Builder(EventDetailActivity.this);
                        builder.setTitle("Choose a number");
                        builder.setItems(items, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                String selectedNumber = items[item].toString();
                                selectedNumber = selectedNumber.replace("-", "");
                               // phoneInput.setText(selectedNumber);
                            }
                        });
                        AlertDialog alert = builder.create();
                        if(allNumbers.size() > 1) {
                            alert.show();
                        } else {
                            String selectedNumber = phoneNumber.toString();
                            selectedNumber = selectedNumber.replace("-", "");
                            //phoneInput.setText(selectedNumber);
                        }

                        if (phoneNumber.length() == 0) {
                            //no numbers found actions
                        }
                    }
                    break;
            }
        } else {
            //activity result error actions
        }
    }

    private void getViews() {
        eventName = (TextView) findViewById(R.id.event_name_textview);
        eventDate = (TextView) findViewById(R.id.event_date_textview);
        eventTime = (TextView) findViewById(R.id.event_time_textview);
        eventVenue = (TextView) findViewById(R.id.event_venue_textview);
        eventLocation = (TextView) findViewById(R.id.event_pos_textview);
        inviteesList = (ListView) findViewById(R.id.invitee_list);
        timePicker = (LinearLayout) findViewById(R.id.timePickerButton);
        datePicker = (LinearLayout) findViewById(R.id.datePickerButton);
        posButton = (LinearLayout) findViewById(R.id.posButton);
        venueButton = (LinearLayout) findViewById(R.id.venueButton);
        addInviteeBtn = (Button) findViewById(R.id.event_invite_btn);
    }

    private void setContent() {
        eventName.setText(event.getName());
        eventDate.setText(event.getPartyDate());
        eventTime.setText(event.getPartyTime());
        eventVenue.setText(event.getPartyVenue());
        eventLocation.setText(event.getLocation());
    }

    /**
     * * Method for Setting the Height of the ListView dynamically.
     * *** Hack to fix the issue of not showing all the items of the ListView
     * *** when placed inside a ScrollView  ***
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        final int HEIGHT_MARGIN_BOTTOM = 20;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ActionBar.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + HEIGHT_MARGIN_BOTTOM;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public void setVenueOnClickListener() {
        // add button listener
        posButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(EventDetailActivity.this);
                View promptsView = li.inflate(R.layout.position_input, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        EventDetailActivity.this);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.position_edittext);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        eventLocation.setText(userInput.getText());
                                        setEvent();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
        venueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(EventDetailActivity.this);
                View promptsView = li.inflate(R.layout.venue_input, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        EventDetailActivity.this);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.venue_edittext);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        eventVenue.setText(userInput.getText());
                                        setEvent();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
    }

    public void showEventNameDialog() {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(EventDetailActivity.this);
        View promptsView = li.inflate(R.layout.eventname_input, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                EventDetailActivity.this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.event_name_edittext);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                eventName.setText(userInput.getText());
                                //createEvent();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void showDatePickerDialog() {
        int mYear, mMonth, mDay;
        // init Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // show date picker dialog
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        eventDate.setText(String.format("%02d.%02d.%04d", dayOfMonth, (monthOfYear + 1), year));
                        setEvent();

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    public void showTimePickerDialog() {
        int mHour, mMinute;
        // init time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // show time picker
        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        eventTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                        setEvent();
                    }
                }, mHour, mMinute, false);
        tpd.show();
    }

    public void setEvent() {
        try {
            MovieDetailActivity.event.setPartyDate(String.format("%s %s", eventDate.getText(), eventTime.getText()));
            MovieDetailActivity.event.setPartyVenue(eventVenue.getText().toString());
            MovieDetailActivity.event.setLocation(eventLocation.getText().toString());
        } catch (ParseException e) {
            Log.i("MAD", e.toString());
        }
    }
}
