package com.project.disease;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.ResultReceiver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {
    static SpecimenInfo specimenInfo;
    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param specimenInfo Parameter 1.
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(SpecimenInfo specimenInfo) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        ResultFragment.specimenInfo = specimenInfo;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =inflater.inflate(R.layout.fragment_photo_preview, container, false);
        view.findViewById(R.id.hideInResult).setVisibility(View.GONE);
        view.findViewById(R.id.showInResult).setVisibility(View.VISIBLE);
        ImageRequestApi imageRequestApi = ImageRequestInstance.getInstance(getActivity());
        Call<ResultResponse> resultResponseCall = imageRequestApi.getResult(specimenInfo.getId(),specimenInfo.getIndex());
        resultResponseCall.enqueue(new Callback<ResultResponse>(){

            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse resultResponse = response.body();
                Picasso.get().load(resultResponse.getOrignalImageUrl()).into((ImageView) view.findViewById(R.id.photoPreview));
                TextView t = view.findViewById(R.id.diseaseText);
                t.setText(resultResponse.getResult());
                Toast.makeText(getActivity(),resultResponse.getResult(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
