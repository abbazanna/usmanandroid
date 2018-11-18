package com.project.disease;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class SelectionFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount;
    private List<SpecimenInfo> SPECIMENS;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SelectionFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static SelectionFragment newInstance(int columnCount, List<SpecimenInfo> specimens) {
        SelectionFragment fragment = new SelectionFragment();
        fragment.SPECIMENS =specimens;
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_grid_layout, container, false);

        // Set the adapter
      //  if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.grid_data);
            recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),mColumnCount));
            recyclerView.setAdapter(new ImageListAdapter(SPECIMENS, new SpecimenClickedListener() {
                @Override
                public void specimenClick(SpecimenInfo specimenInfo) {
                    Fragment fragment = ResultFragment.newInstance(specimenInfo);
                    getFragmentManager().beginTransaction().replace(R.id.fragmentholder,fragment)
                            .addToBackStack("SelectionFragment").commit();

                }
            }));
       // }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
