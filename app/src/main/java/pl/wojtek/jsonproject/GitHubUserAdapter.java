package pl.wojtek.jsonproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import pl.wojtek.jsonproject.json.GitHubUser;

/**
 * Created by user on 2014-11-09.
 */
public class GitHubUserAdapter extends ArrayAdapter {

    private Activity context;
    private List<GitHubUser> list;
    public GitHubUserAdapter(Activity context, int resource, List objects) {
        super(context, resource,objects);
        this.context=context;
        this.list=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        ViewHolder viewHolder;
        final GitHubUser gitHubUser=this.list.get(position);
        if(convertView==null){
            LayoutInflater layoutInflater=context.getLayoutInflater();
            view= layoutInflater.inflate(R.layout.single_githubuser,null);
            viewHolder=new ViewHolder();
            viewHolder.textView=(TextView)view.findViewById(R.id.userNameTextView);
            viewHolder.textView.setText(gitHubUser.getName());
            viewHolder.imageView=(ImageView)view.findViewById(R.id.avatarImageView);

            view.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }



        viewHolder.textView.setText(gitHubUser.getName());
        Picasso.with(context).load(gitHubUser.getAvatar_url()).into(viewHolder.imageView);

       // loadAvatar(viewHolder, gitHubUser);





        return view;
    }

    private void loadAvatar(ViewHolder viewHolder, GitHubUser gitHubUser) {
        BitmapAsyncDowloader bitmapAsyncDowloader=new BitmapAsyncDowloader(viewHolder.imageView);
        bitmapAsyncDowloader.execute(gitHubUser.getAvatar_url());

    }

    private class ViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

    private class BitmapAsyncDowloader extends AsyncTask<String,Void,Void>{
        private ImageView imageView;
        private Bitmap bitmap;
        private ProgressDialog progressDialog;

        public BitmapAsyncDowloader(ImageView imageView){
            this.imageView=imageView;
        }


        @Override
        protected Void doInBackground(String... params) {
            bitmap= getAvatarImage(params[0]);
            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {

            Drawable[] layers = new Drawable[2];
            if(imageView.getDrawable() != null)
                layers[0] = imageView.getDrawable();
            else
                layers[0] = new BitmapDrawable(context.getResources(), bitmap);

            layers[1] = new BitmapDrawable(context.getResources(), bitmap);

            TransitionDrawable transitionDrawable = new TransitionDrawable(layers);
            imageView.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(500);

        }

        private Bitmap getAvatarImage(String imageUrl) {
            Bitmap bm = null;


            try {
                InputStream is = new URL(imageUrl).openStream();
                bm = BitmapFactory.decodeStream(is);
                is.close();
            } catch (IOException e) {
                int ASyntActivity = Log.e("MainActivity", "Error: " + e);
            }
            return bm;
        }
    }


}
