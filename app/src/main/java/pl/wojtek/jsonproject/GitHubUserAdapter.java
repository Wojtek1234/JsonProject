package pl.wojtek.jsonproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by user on 2014-11-09.
 */
public class GitHubUserAdapter extends ArrayAdapter {

    private Activity context;
    private List<GsonActivity.GitHubUser> list;
    public GitHubUserAdapter(Activity context, int resource, List objects) {
        super(context, resource,objects);
        this.context=context;
        this.list=objects;
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        ViewHolder viewHolder;

        if(convertView==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            view= layoutInflater.inflate(R.layout.single_githubuser,null);
            viewHolder=new ViewHolder();
            viewHolder.textView=(TextView)view.findViewById(R.id.userNameTextView);
            viewHolder.imageView=(ImageView)view.findViewById(R.id.avatarImageView);
            view.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }
      final GsonActivity.GitHubUser gitHubUser= this.list.get(position);


        TextView textView=(TextView)viewHolder.textView;
        textView.setText(gitHubUser.name);



        return view;
    }

    private class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }
}
