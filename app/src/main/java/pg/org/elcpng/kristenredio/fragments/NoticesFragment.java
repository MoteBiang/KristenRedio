package pg.org.elcpng.kristenredio.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.Firebase;

import pg.org.elcpng.kristenredio.R;
import pg.org.elcpng.kristenredio.models.Notice;
import pg.org.elcpng.kristenredio.utils.DatabaseHandler;
import pg.org.elcpng.kristenredio.utils.Utils;

public class NoticesFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnNoticeListener mListener;

    private static final String BASE_URL = Utils.FIREBASE_BASE_URL;
    private Firebase ref;

    private Context context;

    private DatabaseHandler dbh;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NoticesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static NoticesFragment newInstance(int columnCount) {
        NoticesFragment fragment = new NoticesFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_list, container, false);
        context = getActivity();
        dbh = new DatabaseHandler(context);

        // Setup our Firebase ref
        ref = new Firebase(BASE_URL).child("toksave");

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new NoticesAdapter(dbh.GetNoticesList(), mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNoticeListener) {
            mListener = (OnNoticeListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnNoticeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnNoticeListener {
        // TODO: Update argument type and name
        void onNoticeListener(Notice item);
    }
}
