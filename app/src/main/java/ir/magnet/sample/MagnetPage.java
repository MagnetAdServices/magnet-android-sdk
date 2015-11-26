package ir.magnet.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import ir.magnet.sample.ui.FloatingActionButton;

import ir.magnet.sdk.MagnetAdLoadListener;
import ir.magnet.sdk.MagnetMRectAd;
import ir.magnet.sdk.MagnetMRectSize;
import ir.magnet.sdk.MagnetMobileBannerAd;
import ir.magnet.sdk.MagnetRewardAd;
import ir.magnet.sdk.MagnetRewardListener;
import ir.magnet.sdk.MagnetSDK;

public class MagnetPage extends Fragment implements View.OnClickListener{

    private static final String ARG_POSITION = "position";
    private FloatingActionButton fab;
    private int position;
    private View rootView = null;
    private FrameLayout adLayout;
    private android.widget.Button videoBtn;
    private String SHOW_VIDEO_TEXT = "SHOW VIDEO";

    public static MagnetPage newInstance(int position) {
        MagnetPage magnetPage = new MagnetPage();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        magnetPage.setArguments(bundle);
        return magnetPage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_POSITION);
        switch (position) {
            case 0:
                rootView = inflater.inflate(R.layout.mobile_banner, container, false);
                adLayout = (FrameLayout) rootView.findViewById(R.id.bannerAdFrame);
                fab = (FloatingActionButton) rootView.findViewById(R.id.MobileBannerPageFab);
                break;
            case 1:
                rootView = inflater.inflate(R.layout.mrect, container, false);
                adLayout = (FrameLayout) rootView.findViewById(R.id.mrectrAdFrame);
                fab = (FloatingActionButton) rootView.findViewById(R.id.MrectPageFab);
                break;
            case 2:
                rootView = inflater.inflate(R.layout.interstitial, container, false);
                videoBtn = (android.widget.Button) rootView.findViewById(R.id.videoBtn);
                break;
        }

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        /**
         * Magnet sdk should be initialized at the very beginning of your app.
         * Initialization steps need to be set once in your application's life cycle; but there wouldn't be a problem if they have been called more than once.
         * If you want to release your application please change test mode to false or comment the line.
         * Target restriction restricts advertisement target to stay in your app or it could open an external application ie(Browser, Bazar, Myket and ect). default value is Both.
         */
        MagnetSDK.initialize(getActivity());
        MagnetSDK.getSettings().setTestMode(false);
//        MagnetSDK.getSettings().setTargetRestriction(TargetRestriction.Both);

        if(null != videoBtn){
            videoBtn.setOnClickListener(this);
        }
        if(null != fab){
            fab.setDrawableIcon(getResources().getDrawable(R.drawable.plus));
            fab.setBackgroundColor(getResources().getColor(R.color.colorPink));
            fab.setOnClickListener(this);
        }
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {

        /**
         * In each page's button click event, the corresponding ad will load
         */
        int ViewId = view.getId();
        switch (ViewId) {

            case R.id.MobileBannerPageFab:
                MagnetMobileBannerAd bannerAd = MagnetMobileBannerAd.create(getActivity());
                bannerAd.load("141cec5b4eec418d9c3ee4030a8f3ea1", adLayout);
                break;

            case R.id.MrectPageFab:
                MagnetMRectAd MRectAd = MagnetMRectAd.create(getActivity());
                MRectAd.load("141cec5b4eec418d9c3ee4030a8f3ea1", adLayout, MagnetMRectSize.SIZE_300_250);
                break;

            case R.id.videoBtn:
                /**
                 * This an implementation for a rewarded ad. first click loads the ad, second plays it.
                 */

                MagnetRewardAd rewardAd = MagnetRewardAd.create(getActivity());
                rewardAd.setAdLoadListener(new MagnetAdLoadListener() {
                    @Override
                    public void onReceive() {
                        videoBtn.setText(SHOW_VIDEO_TEXT);
                    }

                    @Override
                    public void onFail(int errorCode, String errorMessage) {
                        /**
                         * Couldn't receive ad, perform fail over logic.
                         */
                    }
                });

                if(videoBtn.getText().toString().equals(SHOW_VIDEO_TEXT)) {
                    rewardAd.show(new MagnetRewardListener() {
                        @Override
                        public void onRewardSuccessful(String verificationToken, String trackingId) {
                            /**
                             *  Give reward to your user.
                             *  You can make sure the reward is come from the magnet server, within an API entering trackingId and verificationCode.
                             *  The API address is: http://magnet.ir/api/verify/conversion?TrackingId={trackingId}&VerificationToken={verificationToken}
                             */

                        }

                        @Override
                        public void onRewardFail() {
                            /**
                             * User did not see the ad completely and can not get reward.
                             * There is no need to implement an specific logic here.
                             */
                        }
                    });
                } else {
                    rewardAd.load("141cec5b4eec418d9c3ee4030a8f3ea1");
                }

        }
    }

}