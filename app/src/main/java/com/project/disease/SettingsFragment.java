package com.project.disease;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    public static final String PREFS_NAME = "settingsFile";
    public static final String URL_KEY ="URL";
    public static final String PORT_NUMBER_KEY ="PORT_NUMBER";


    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
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
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        final SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);

        final EditText etURL = (EditText)view.findViewById(R.id.etServerURL);
        Button btnOk = (Button)view.findViewById(R.id.settingsOK);
        Button btnCancel =(Button)view.findViewById(R.id.settingsCancel);
        if(settings.getString(URL_KEY,null)!= null){
           etURL.setText(settings.getString(URL_KEY,null));
        }
        final EditText etPort = (EditText)view.findViewById(R.id.etServerPort);
        if(settings.getString(PORT_NUMBER_KEY,null)!= null){
            etPort.setText(settings.getString(PORT_NUMBER_KEY,null));
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(URL_KEY,etURL.getText().toString());
                editor.putString(PORT_NUMBER_KEY,etPort.getText().toString());
                editor.commit();
                Toast.makeText(getActivity(),settings.getString(URL_KEY,null),Toast.LENGTH_LONG).show();
                getFragmentManager().popBackStack();
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
