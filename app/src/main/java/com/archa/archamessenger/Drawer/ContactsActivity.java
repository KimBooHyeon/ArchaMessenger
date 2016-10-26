package com.archa.archamessenger.Drawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.archa.archamessenger.Adapters.ContactsAdapter;
import com.archa.archamessenger.MainActivity;
import com.archa.archamessenger.Models.Contacts;
import com.archa.archamessenger.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by doosoun on 16. 10. 5..
 */
public class ContactsActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayoutManager layoutManager;
    private MainActivity mainActivity;

    private Button btn_back, btn_contacts_find;
    private EditText et_contacts_find;
    private RecyclerView recycler_contacts;
    private ContactsAdapter adapter_contacts;

    public static List<Contacts> list_contacts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        mainActivity = new MainActivity();
        init();

        layoutManager = new LinearLayoutManager(ContactsActivity.this);
        recycler_contacts = (RecyclerView)findViewById(R.id.recycler_contacts);
        recycler_contacts.setLayoutManager(layoutManager);
        adapter_contacts = new ContactsAdapter(ContactsActivity.this, list_contacts);
        recycler_contacts.setAdapter(adapter_contacts);

        adapter_contacts.notifyDataSetChanged();
    }

    public void init(){
        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        mainActivity = new MainActivity();
        if(list_contacts == null){
            list_contacts = new ArrayList<>();
            for(int i = 0; i < MainActivity.list_contacts.size(); i++)
                list_contacts.add(MainActivity.list_contacts.get(i));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
