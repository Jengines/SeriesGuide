package com.battlelancer.seriesguide.api;

import android.content.Intent;
import android.content.pm.PackageManager;

/**
 * Helper methods to view shows or episodes within SeriesGuide.
 */
public class Intents {

    public static final String ACTION_VIEW_EPISODE
            = "com.battlelancer.seriesguide.api.action.VIEW_EPISODE";

    public static final String ACTION_VIEW_SHOW
            = "com.battlelancer.seriesguide.api.action.VIEW_SHOW";

    public static final String EXTRA_EPISODE_TVDBID = "episode_tvdbid";

    public static final String EXTRA_SHOW_TVDBID = "show_tvdbid";

    /**
     * Builds an implicit {@link Intent} to view an episode in SeriesGuide. Make
     * sure to check with {@link Intent#resolveActivity(PackageManager)} if
     * SeriesGuide (or another app capable of handling this intent) is available.
     *
     * @param showTvdbId If valid and the episode does not exist, the user will be asked if the show
     *                   should be added to SeriesGuide.
     */
    @SuppressWarnings("unused")
    public static Intent buildViewEpisodeIntent(int showTvdbId, int episodeTvdbId) {
        return new Intent(ACTION_VIEW_EPISODE)
                .putExtra(EXTRA_SHOW_TVDBID, showTvdbId)
                .putExtra(EXTRA_EPISODE_TVDBID, episodeTvdbId)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
    }

    /**
     * Builds an implicit {@link Intent} to view a show in SeriesGuide. Make
     * sure to check with {@link Intent#resolveActivity(PackageManager)} if
     * SeriesGuide (or another app capable of handling this intent) is available.
     * <p>
     * <p> If the show is not added to SeriesGuide, the user will be asked if it should be.
     */
    @SuppressWarnings("unused")
    public static Intent buildViewShowIntent(int showTvdbId) {
        return new Intent(ACTION_VIEW_SHOW)
                .putExtra(EXTRA_SHOW_TVDBID, showTvdbId)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
    }
}
