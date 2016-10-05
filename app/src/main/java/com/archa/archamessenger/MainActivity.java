package com.archa.archamessenger;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.archa.archamessenger.Drawer.ContactsActivity;
import com.archa.archamessenger.Models.Contacts;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{


    private DrawerLayout dlDrawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private LinearLayout layout_navigation;
    private LinearLayout layout_contacts;

    private Button btn_menu, btn_close_drawer;

    public static List<Contacts> list_contacts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout_navigation = (LinearLayout)findViewById(R.id.layout_navigation);
        dlDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        dlDrawer.setDrawerListener(actionBarDrawerToggle);

        init();
    }

    public void init(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        btn_menu = (Button)findViewById(R.id.btn_menu);
        btn_close_drawer = (Button)findViewById(R.id.btn_close_drawer);
        btn_menu.setOnClickListener(this);
        btn_close_drawer.setOnClickListener(this);

        layout_contacts = (LinearLayout)findViewById(R.id.layout_contacts);
        layout_contacts.setOnClickListener(this);

        if(list_contacts.size() == 0)
            getContactsList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_menu:
                dlDrawer.openDrawer(layout_navigation);
                break;
            case R.id.btn_close_drawer:
                dlDrawer.closeDrawer(layout_navigation);
                break;

            case R.id.layout_contacts:
                Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                startActivity(intent);
                break;
        }
    }

    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void getContactsList(){
        List<Contacts> list_sort = new ArrayList<>();

        String[] arrProjection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };
        String[] arrPhoneProjection = {
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        Cursor cursor = MainActivity.this.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                arrProjection,
                ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1",
                null, null
        );
        while(cursor.moveToNext()){
            String strContactID = cursor.getString(0);

            Cursor cursor1 = MainActivity.this.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    arrPhoneProjection,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + strContactID,
                    null, null
            );
            while(cursor1.moveToNext()){
                list_sort.add(new Contacts(R.drawable.profile, cursor.getString(1), cursor1.getString(0)));
            }

            Map<String, Character> dup = new HashMap<>();
            String hp;
            for(int i = 0; i < list_sort.size(); i++){
                hp = list_sort.get(i).getPersonName();
                if(hp != null && dup.get(hp) == null){
                    dup.put(hp, '1');
                    i++;
                } else{
                    list_sort.remove(i);
                }
            }
            cursor1.close();
        }
        cursor.close();

        for(int i = 0; i < list_sort.size(); i++){
            list_contacts.add(list_sort.get(i));
        }
        Collections.sort(list_contacts, comparator);
    }
    private final static Comparator<Contacts> comparator = new Comparator<Contacts>() {
        private final Collator collator = Collator.getInstance();
        @Override
        public int compare(Contacts object1, Contacts object2) {
            return collator.compare(object1.getPersonName(), object2.getPersonName());
        }
    };
}
