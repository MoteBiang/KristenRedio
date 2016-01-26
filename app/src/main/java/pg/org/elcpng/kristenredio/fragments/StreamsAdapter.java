package pg.org.elcpng.kristenredio.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import pg.org.elcpng.kristenredio.R;
import pg.org.elcpng.kristenredio.models.Stream;
import pg.org.elcpng.kristenredio.fragments.StreamsFragment.OnStreamListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link OnStreamListener} and makes a call to the
 * specified {@link OnStreamListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class StreamsAdapter extends RecyclerView.Adapter<StreamsAdapter.ViewHolder> {

    private final List<Stream> list;
    private final OnStreamListener mListener;

    public StreamsAdapter(List<Stream> items, OnStreamListener listener) {
        list = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_streamer, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.notice = list.get(position);
        holder.lbTitleText.setText(list.get(position).getTitle() + "");
        holder.lbFromText.setText(list.get(position).getNoticeFrom());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onStreamListener(holder.notice);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final ImageButton btnThumbnail;
        public final TextView lbTitleText, lbFromText, lbTimeAgo, lbMessageText;
        public Stream notice;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            btnThumbnail = (ImageButton) view.findViewById(R.id.btnThumbnail);
            lbTitleText = (TextView) view.findViewById(R.id.lbTitleText);
            lbFromText = (TextView) view.findViewById(R.id.lbFromText);
            lbTimeAgo = (TextView) view.findViewById(R.id.lbTimeAgo);
            lbMessageText = (TextView) view.findViewById(R.id.lbMessageText);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + lbTitleText.getText() + "'";
        }
    }
}
