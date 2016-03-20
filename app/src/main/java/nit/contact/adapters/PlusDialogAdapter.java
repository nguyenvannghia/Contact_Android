package nit.contact.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import nit.contact.R;

/**
 * Created by NIT Admin on 16/03/2016
 */
public class PlusDialogAdapter extends BaseAdapter {

    private final LayoutInflater mLayoutInflater;
    private final boolean isGrid;

    public PlusDialogAdapter(Context context, boolean isGrid) {
        mLayoutInflater = LayoutInflater.from(context);
        this.isGrid = isGrid;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view = convertView;

        if (view == null) {
            if (isGrid) {
                view = mLayoutInflater.inflate(R.layout.simple_grid_item, parent, false);
            } else {
                view = mLayoutInflater.inflate(R.layout.simple_list_item, parent, false);
            }

            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.text_view);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.image_view);
            view.setTag(viewHolder);
        }
//        else {
//            viewHolder = (ViewHolder) view.getTag();
//        }

        // Todo: complate later
        switch (position) {
            case 0:
//                viewHolder.textView.setText("Google Plus");
//                viewHolder.imageView.setImageResource(R.drawable.ic_google_normal);
                break;
            case 1:
//                viewHolder.textView.setText("Twitter");
//                viewHolder.imageView.setImageResource(R.drawable.icn_twitter_normal);
                break;
            default:
//                viewHolder.textView.setText("Facebook");
//                viewHolder.imageView.setImageResource(R.drawable.ic_facebook_normal);
                break;
        }

        return view;
    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}