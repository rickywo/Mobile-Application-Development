package edu.ricky.madev;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

import java.util.ArrayList;

import edu.ricky.madev.model.MovieEvent;


public class PartyEventActivity extends ActionBarActivity {

    private final static int REQUEST_CONTACTPICKER = 1;
    private MovieEvent currentMovieEvent;
    private RatingBar ratingBar;
    private ImageView imageView;
    private EditText etVenue;
    private EditText etLocation;
    private Button btnInvite;
    private Button btnSave;
    private ListView lvContact;
    private ArrayList<String> listItems=new ArrayList<String>();
    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_event);Bundle extras = getIntent().getExtras();
        ratingBar = (RatingBar) findViewById(R.id.event_ratingBar);
        imageView = (ImageView) findViewById(R.id.event_poster_imgview);
        etVenue = (EditText) findViewById(R.id.event_venue_edittext);
        etLocation = (EditText) findViewById(R.id.event_location_edittext);
        btnInvite = (Button) findViewById(R.id.event_contact_button);
        lvContact = (ListView) findViewById(R.id.lv_contact);
        currentMovieEvent = MainActivity.me.get(MainActivity.currentSelectedMovie);
        etVenue.setText(currentMovieEvent.getTitle());
        etLocation.setText(currentMovieEvent.getLocation());
        //imageView.setImageResource(currentMovieEvent.getImg());
        ratingBar.setRating(currentMovieEvent.getRating());
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        lvContact.setAdapter(adapter);
        addListenerOnRatingBar();
        addListenerOnInviteButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_party_event, menu);
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

    public void addListenerOnRatingBar() {
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                currentMovieEvent.setRating(rating);
            }
        });
    }

    public void addListenerOnSaveButton() {

    }

    public void addListenerOnInviteButton() {
        btnInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    // This intent will fire up the contact picker dialog
                    Intent intent = new Intent(Intent.ACTION_PICK,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                    startActivityForResult(intent, REQUEST_CONTACTPICKER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CONTACTPICKER)
        {
            if(resultCode == RESULT_OK)
            {
                Uri contentUri = data.getData();
                String contactId = contentUri.getLastPathSegment();
                Cursor cursor = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone._ID + "=?",       // < - Note, not CONTACT_ID!
                        new String[]{contactId}, null);
                startManagingCursor(cursor);
                Boolean numbersExist = cursor.moveToFirst();
                int phoneNumberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                int contactIDColumnIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String phoneNumber = "";
                String contactName = "";
                while (numbersExist)
                {
                    phoneNumber = cursor.getString(phoneNumberColumnIndex);
                    contactName = cursor.getString(contactIDColumnIndex);
                    phoneNumber = phoneNumber.trim();
                    contactName = contactName.trim();
                    numbersExist = cursor.moveToNext();
                }
                stopManagingCursor(cursor);
                if (!phoneNumber.equals(""))
                {
                    //setPhoneNumber(phoneNumber);
                    Log.i("MAD", contactName);
                    Log.i("MAD", phoneNumber);
                    addItem(contactName);

                } // phoneNumber != ""
            } // Result Code = RESULT_OK
        } // Request Code = REQUEST_CONTACTPICER
    }	// end function

    private void addItem(String s) {
        listItems.add(s);
        adapter.notifyDataSetChanged();
    }
}
