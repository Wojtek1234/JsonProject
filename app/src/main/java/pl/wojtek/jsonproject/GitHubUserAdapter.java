package pl.wojtek.jsonproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
        final GsonActivity.GitHubUser gitHubUser= this.list.get(position);
        if(convertView==null){

            LayoutInflater layoutInflater=context.getLayoutInflater();
            view= layoutInflater.inflate(R.layout.single_githubuser,null);
            viewHolder=new ViewHolder();
            viewHolder.textView=(TextView)view.findViewById(R.id.userNameTextView);
            viewHolder.textView.setText(gitHubUser.name);
            viewHolder.imageView=(ImageView)view.findViewById(R.id.avatarImageView);

            view.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }



        viewHolder.textView.setText(gitHubUser.name);
        loadAvatar(viewHolder, gitHubUser);




        return view;
    }

    private void loadAvatar(ViewHolder viewHolder, GsonActivity.GitHubUser gitHubUser) {
        BitmapAsyncDowloader bitmapAsyncDowloader=new BitmapAsyncDowloader(viewHolder.imageView);
        bitmapAsyncDowloader.execute(gitHubUser.avatar_url);
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
            bitmap=getRandomImage(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            this.imageView.setImageBitmap(bitmap);
        }

        private Bitmap getRandomImage(String imageUrl) {
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
