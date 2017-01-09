package com.highbury.internship.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.highbury.internship.R;
import com.highbury.internship.base.BaseActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.easeui.adapter.EaseContactAdapter;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.easeui.widget.EaseSidebar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

/**
 * Created by han on 2017/1/4.
 */

public class PickAtUserActivity extends BaseActivity{
    @BindView(R.id.list)
    ListView listView;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_pick_at_user;
    }

    @Override
    public void setupViews() {
        super.setupViews();
        String groupId = getIntent().getStringExtra("groupId");
        EMGroup group = EMClient.getInstance().groupManager().getGroup(groupId);
        EaseSidebar sidebar = (EaseSidebar) findViewById(com.hyphenate.easeui.R.id.sidebar);
        sidebar.setListView(listView);
        List<String> members = group.getMembers();
        List<EaseUser> userList = new ArrayList<EaseUser>();
        for(String username : members){
            EaseUser user = EaseUserUtils.getUserInfo(username);
            userList.add(user);
        }

        Collections.sort(userList, new Comparator<EaseUser>() {

            @Override
            public int compare(EaseUser lhs, EaseUser rhs) {
                if(lhs.getInitialLetter().equals(rhs.getInitialLetter())){
                    return lhs.getNick().compareTo(rhs.getNick());
                }else{
                    if("#".equals(lhs.getInitialLetter())){
                        return 1;
                    }else if("#".equals(rhs.getInitialLetter())){
                        return -1;
                    }
                    return lhs.getInitialLetter().compareTo(rhs.getInitialLetter());
                }

            }
        });
        final boolean isOwner = EMClient.getInstance().getCurrentUser().equals(group.getOwner());
        if(isOwner) {
            addHeadView();
        }
        listView.setAdapter(new PickUserAdapter(this, 0, userList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(isOwner){
                    if(position != 0) {
                        EaseUser user = (EaseUser) listView.getItemAtPosition(position);
                        if (EMClient.getInstance().getCurrentUser().equals(user.getUsername()))
                            return;
                        setResult(RESULT_OK, new Intent().putExtra("username", user.getUsername()));
                    }else{
                        setResult(RESULT_OK, new Intent().putExtra("username", getString(R.string.all_members)));
                    }
                }else{
                    EaseUser user = (EaseUser) listView.getItemAtPosition(position);
                    if (EMClient.getInstance().getCurrentUser().equals(user.getUsername()))
                        return;
                    setResult(RESULT_OK, new Intent().putExtra("username", user.getUsername()));
                }

                finish();
            }
        });

    }

    private void addHeadView(){
        View view = LayoutInflater.from(this).inflate(R.layout.ease_row_contact, listView, false);
        ImageView avatarView = (ImageView) view.findViewById(R.id.avatar);
        TextView textView = (TextView) view.findViewById(R.id.name);
        textView.setText(getString(R.string.all_members));
        avatarView.setImageResource(R.drawable.ease_groups_icon);
        listView.addHeaderView(view);
    }

    public void back(View view) {
        finish();
    }

    private class PickUserAdapter extends EaseContactAdapter {

        public PickUserAdapter(Context context, int resource, List<EaseUser> objects) {
            super(context, resource, objects);
        }
    }
}
