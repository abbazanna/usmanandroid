package com.project.disease;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CropFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CropFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CropFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g
    private OnFragmentInteractionListener mListener;
    final static String ARG_PATH = "path";
    Uri pathUri;
    View fragmentView;
    public CropFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment cropFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CropFragment newInstance(File file) {
        Uri uri;
        CropFragment fragment = new CropFragment();
        Bundle args = new Bundle();
        uri = Uri.fromFile(file);
        args.putString(ARG_PATH,uri.toString());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           // Toast.makeText(getActivity(),getArguments().getString(ARG_PATH),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_crop, container, false);
        CropImageView cropImageView = fragmentView.findViewById(R.id.cropImageView);
        pathUri = Uri.parse(getArguments().getString(ARG_PATH));
        Toast.makeText(getActivity(),pathUri.toString(),Toast.LENGTH_SHORT).show();
        
        //cropImageView.setImageUriAsync(pathUri);

        return fragmentView;
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
