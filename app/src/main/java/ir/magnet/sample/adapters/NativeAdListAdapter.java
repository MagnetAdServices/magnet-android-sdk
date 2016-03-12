package ir.magnet.sample.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

import ir.magnet.sample.R;
import ir.magnet.sdk.BadImplementationException;
import ir.magnet.sdk.MagnetNativeContentAd;
import ir.magnet.sdk.MagnetNativeViewBinder;

/**
 * Created by Hesami on 01/03/2016.
 */
public class NativeAdListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity mContext;

    // Provide a suitable constructor (depends on the kind of dataset)
    public NativeAdListAdapter(Activity context) {
        mContext = context;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class AdFrameViewHolder extends RecyclerView.ViewHolder {
        public FrameLayout adLayout;
        public AdFrameViewHolder(View v) {
            super(v);
            adLayout = (FrameLayout) v.findViewById(R.id.nativeAdFrame);
        }
    }

    public class ListItemViewHolder extends RecyclerView.ViewHolder {
        public TextView headlineTextView;
        public TextView descriptionTextView;
        public ImageView advertiserIcon;
        public ImageView advertiseImage;
        public Button callToActionButton;
        public ImageView ad_indicative;

        public ListItemViewHolder(View v) {
            super(v);
            headlineTextView = (TextView) v.findViewById(R.id.native_title);
            descriptionTextView = (TextView) v.findViewById(R.id.native_text);
            advertiserIcon = (ImageView) v.findViewById(R.id.native_icon_image);
            advertiseImage = (ImageView) v.findViewById(R.id.native_main_image);
            callToActionButton = (Button) v.findViewById(R.id.native_cta);
            ad_indicative = (ImageView) v.findViewById(R.id.ad_indicative);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 5 != 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                // create a new view
                View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.native_content, parent, false);
                // set the view's size, margins, paddings and layout parameters
                ListItemViewHolder listHolder = new ListItemViewHolder(v1);
                return listHolder;
            case 2:
                // create a new view
                View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.advertise_frame, parent, false);
                // set the view's size, margins, paddings and layout parameters
                AdFrameViewHolder adHolder = new AdFrameViewHolder(v2);
                return adHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof AdFrameViewHolder) {
            /**
             * Your native ad will be shown here ...
             * Create a MagnetNativeViewBinder object specifying the binding between your layout XML and the ad’s content.
             */

            MagnetNativeViewBinder viewBinder = null;
            try {
                viewBinder = new MagnetNativeViewBinder.Builder()
                        .titleId(R.id.native_title)
                        .descriptionId(R.id.native_text)
                        .iconImageId(R.id.native_icon_image)
                        .imageId(R.id.native_main_image)
                        .callToActionId(R.id.native_cta)
                        .adIndicativeId(R.id.ad_indicative)
                        .build();
            } catch (BadImplementationException e) {
                Log.e("Magnet error", e.toString());
            }
            RelativeLayout adView = (RelativeLayout) mContext.getLayoutInflater().inflate(R.layout.native_content, null);
            MagnetNativeContentAd magnetNativeContentad = MagnetNativeContentAd.create(mContext);
            magnetNativeContentad.buildNativeAdView(adView, viewBinder);
            magnetNativeContentad.load("AdUnitId", ((AdFrameViewHolder) holder).adLayout);

        } else {
            final List<String> titles = Arrays.asList(mContext.getResources().getStringArray(R.array.sample_titles));
            final String description = mContext.getResources().getString(R.string.sample_description);
            ((ListItemViewHolder) holder).descriptionTextView.setText(description);
            ((ListItemViewHolder) holder).ad_indicative.setVisibility(View.GONE);
            int iconResourceId = R.drawable.ic_launcher;
            int imageResourceId = R.drawable.ic_launcher;
            String title = "";
            switch (position % 5) {
                case 1 :
                    iconResourceId = R.drawable.tehran_icon;
                    imageResourceId = R.drawable.tehran_image;
                    title = titles.get(0);
                    break;
                case 2 :
                    iconResourceId = R.drawable.esfehan_icon;
                    imageResourceId = R.drawable.esfehan_image;
                    title = titles.get(1);
                    break;
                case 3 :
                    iconResourceId = R.drawable.sanandaj_icon;
                    imageResourceId = R.drawable.sanandaj_image;
                    title = titles.get(2);
                    break;
                case 4 :
                    iconResourceId = R.drawable.tabriz_icon;
                    imageResourceId = R.drawable.tabriz_image;
                    title = titles.get(3);
                    break;
                default: break;
            }
            Glide.with(mContext).load(iconResourceId).into(((ListItemViewHolder) holder).advertiserIcon);
            Glide.with(mContext).load(imageResourceId).into(((ListItemViewHolder) holder).advertiseImage);
            ((ListItemViewHolder) holder).headlineTextView.setText(title);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 100;
    }
}
