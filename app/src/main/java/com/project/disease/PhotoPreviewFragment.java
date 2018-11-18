package com.project.disease;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PhotoPreviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PhotoPreviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhotoPreviewFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String path;
   // private final String boundary = "apiclient-" + System.currentTimeMillis();
   // private final String mimeType = "multipart/form-data;boundary=" + boundary;
   // private byte[] multipartBody;

    private OnFragmentInteractionListener mListener;

    public PhotoPreviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment PhotoPreviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhotoPreviewFragment newInstance(String param1) {
        PhotoPreviewFragment fragment = new PhotoPreviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            path = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_photo_preview, container, false);
        String url = getActivity().getSharedPreferences(SettingsFragment.PREFS_NAME,Context.MODE_PRIVATE).getString(SettingsFragment.URL_KEY,null);

        final ImageView iPhotoPreview = (ImageView) view.findViewById(R.id.photoPreview);
        iPhotoPreview.setImageURI(Uri.parse(path));

        final Button btnOk = (Button) view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOk.setBackgroundColor(0xFF00FF);
                Toast.makeText(getActivity().getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();

                File myimageFile = new File(path);
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),myimageFile);
                MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("img",myimageFile.getName(),requestBody);
                RequestBody filename = RequestBody.create(MediaType.parse("text/plain"),myimageFile.getName());

                ImageRequestApi getResponse = ImageRequestInstance.getInstance(getActivity());
                Call<ImageListRequestResponse> call =getResponse.uploadFile(fileToUpload,filename);
                Toast.makeText(getActivity().getApplicationContext(),"loading",Toast.LENGTH_SHORT).show();
                call.enqueue(new Callback<ImageListRequestResponse>() {
                    @Override
                    public void onResponse(Call<ImageListRequestResponse> call, Response<ImageListRequestResponse> response) {
                        ImageListRequestResponse serverResponse = response.body();
                        Toast.makeText(getActivity(),"Response",Toast.LENGTH_SHORT);
                        if (serverResponse != null) {
                            SpecimenList specimenList = new SpecimenList();

                            Toast.makeText(getActivity(),"Response Receiver",Toast.LENGTH_SHORT).show();
                            for (ImageListRequestResponse.ImageInfo img:serverResponse.getImageInfos()
                                 ) {

                                SpecimenInfo specimen = new SpecimenInfo();
                                specimen.setId(img.getId());
                                specimen.setIndex(img.getIndex());
                                specimen.setUrl(img.getUrl());
                                specimen.setName("Segment "+ img.getIndex());
                                specimenList.add(specimen);

                                Toast.makeText(getActivity(),specimen.getId(),Toast.LENGTH_SHORT).show();
                            }
                            Fragment frag = SelectionFragment.newInstance(3,specimenList);
                            getFragmentManager().beginTransaction().replace(R.id.fragmentholder,frag).addToBackStack("photoPreview").commit();

                        } else {
                            Toast.makeText(getActivity(),"null",Toast.LENGTH_SHORT).show();
                            assert serverResponse != null;
                            Log.v("Response", serverResponse.toString());

                        }
                    }

                    @Override
                    public void onFailure(Call<ImageListRequestResponse> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(getActivity(),"Failed",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void buildPart(DataOutputStream dataOutputStream, byte[] fileData, String fileName) throws IOException {
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        //dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"img\"; filename=\""
                + fileName + "\"" + lineEnd);
        dataOutputStream.writeBytes(lineEnd);

        ByteArrayInputStream fileInputStream = new ByteArrayInputStream(fileData);
        int bytesAvailable = fileInputStream.available();

        int maxBufferSize = 1024 * 1024;
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
        byte[] buffer = new byte[bufferSize];

        // read file and write it into form...
        int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

        while (bytesRead > 0) {
            dataOutputStream.write(buffer, 0, bufferSize);
            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
        }

        dataOutputStream.writeBytes(lineEnd);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
