package com.project.disease;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.ResultReceiver;
import android.util.Log;
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
        final View view =inflater.inflate(R.layout.final_result_layout, container, false);
       // view.findViewById(R.id.hideInResult).setVisibility(View.GONE);
        //view.findViewById(R.id.showInResult).setVisibility(View.VISIBLE);
        ImageRequestApi imageRequestApi = ImageRequestInstance.getInstance(getActivity());
        Call<ResultResponse> resultResponseCall = imageRequestApi.getResult(specimenInfo.getId(),specimenInfo.getIndex());
        Toast.makeText(getActivity(),"Fetching result from server, this will take a minute or two",Toast.LENGTH_LONG).show();
        resultResponseCall.enqueue(new Callback<ResultResponse>(){

            @Override
            public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                ResultResponse resultResponse = response.body();
                Picasso.get().load(resultResponse.getOrignalImageUrl()).into((ImageView) view.findViewById(R.id.FinaldiseaseOriginalImage));
                Picasso.get().load(resultResponse.getSegmentUrl()).into((ImageView) view.findViewById(R.id.FinaldiseaseSegmentImage));
                TextView t = view.findViewById(R.id.FinaldiseaseName);
                t.setText(resultResponse.getResult());
                t = view.findViewById(R.id.FinaldiseaseDescription);
                t.setText(resultResponse.getDiseaseDefinition());
                t = view.findViewById(R.id.FinaldiseaseSolution);
                t.setText(resultResponse.getTreatmentSolution());
                t = view.findViewById(R.id.FinaldiseaseAccuracy);
                t.setText(resultResponse.getAccuracy());
                t = view.findViewById(R.id.FinalMean);
                t.setText(resultResponse.getMean());
                t = view.findViewById(R.id.FinalContrast);
                t.setText(resultResponse.getContrast());
                t= view.findViewById(R.id.FinalCorr);
                t.setText(resultResponse.getCorrelation());
                t = view.findViewById(R.id.FinalKurtosis);
                t.setText(resultResponse.getKurtosis());
                t = view.findViewById(R.id.FinalEntropy);
                t.setText(resultResponse.getEntropy());
                t = view.findViewById(R.id.FinalSmoothness);
                t.setText(resultResponse.getSmoothness());
                t = view.findViewById(R.id.FinalVariance);
                t.setText(resultResponse.getVariance());
                t = view.findViewById(R.id.FinalArea);
                t.setText(resultResponse.getArea());
                t = view.findViewById(R.id.FinalEnergy);
                t.setText(resultResponse.getEnergy());
                t = view.findViewById(R.id.FinalIDM);
                t.setText(resultResponse.getIDM());
                t = view.findViewById(R.id.FinalSTD);
                t.setText(resultResponse.getSTD());
                t = view.findViewById(R.id.FinalSkwenes);
                t.setText(resultResponse.getSkweness());
                t = view.findViewById(R.id.FinalHomogeneity);
                t.setText(resultResponse.getHomogeneity());
                t = view.findViewById(R.id.FinalRMS);
                t.setText(resultResponse.getRMS());

                if(resultResponse.serverError!=null){
                    Toast.makeText(getActivity(),"Server Error:"+resultResponse.getServerError(),Toast.LENGTH_LONG).show();
                }else {

                    Toast.makeText(getActivity(), "Result ID:" + resultResponse.getResult(), Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {
                Log.e("Network error",t.toString());
                Toast.makeText(getActivity(),"Failed:"+t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
