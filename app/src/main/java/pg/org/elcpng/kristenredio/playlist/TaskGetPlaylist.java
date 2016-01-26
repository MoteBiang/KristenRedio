package pg.org.elcpng.kristenredio.playlist;

import android.app.Activity;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;

import pg.org.elcpng.kristenredio.playerutils.Constants;

/**
 * Created by recoil on 01.06.14.
 */
public class TaskGetPlaylist extends AsyncTask {

    private final WeakReference<Activity> mActivity;
    private final WeakReference<OnTaskGetPlaylist> mOnTaskGetPlaylist;
    private int type;
    private boolean refresh;

    public TaskGetPlaylist(Activity activity, int type, boolean refresh, OnTaskGetPlaylist onTaskGetPlaylist) {
        mActivity = new WeakReference<Activity>(activity);
        mOnTaskGetPlaylist = new WeakReference<OnTaskGetPlaylist>(onTaskGetPlaylist);
        this.type = type;
        this.refresh = refresh;
    }

    @Override
    protected Object doInBackground(Object... params) {
        Activity activity = mActivity.get();
        if (null == activity) {
            return null;
        }
        switch (this.type) {
            case Constants.TYPE_MS:

                return (new MakePlaylistMS(activity, refresh)).getArrTracks();
            case Constants.TYPE_FS:
                return new MakePlaylistFS(activity, refresh).getArrTracks();
            default:
                return new MakePlaylistMS(activity, refresh).getArrTracks();
        }
    }

    protected void onPostExecute(Object result) {
        OnTaskGetPlaylist onTaskGetPlaylist = mOnTaskGetPlaylist.get();
        Activity activity = mActivity.get();
        if (null != onTaskGetPlaylist) {
            onTaskGetPlaylist.OnTaskResult(result);
        }
        if (null != activity) {
            // TODO ((ActPlaylist) activity).setRefreshing(false);
            // TODO ((ActPlaylist) activity).setRefreshActionButtonState();
        }
    }

    public static interface OnTaskGetPlaylist {
        public void OnTaskResult(Object result);
    }
}