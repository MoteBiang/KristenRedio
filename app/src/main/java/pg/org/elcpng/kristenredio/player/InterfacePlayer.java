package pg.org.elcpng.kristenredio.player;

import pg.org.elcpng.kristenredio.playerutils.ClsTrack;

public interface InterfacePlayer {
    public void onPluginsLoaded(String plugins);

    public void onFileLoaded(ClsTrack track, double duration, String artist, String title, int position, int albumId);

    public void onProgressChanged(double progress);

    public void onUpdatePlayPause();
}
