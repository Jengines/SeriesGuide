package com.battlelancer.seriesguide.settings;

import android.content.Context;
import android.preference.PreferenceManager;

import com.battlelancer.seriesguide.provider.SeriesGuideContract;
import com.battlelancer.seriesguide.settings.ShowsDistillationSettings.ShowsSortOrder;

/**
 * Provides settings used to sort displayed list items in {@link com.battlelancer.seriesguide.ui.ListsFragment}.
 */
public class ListsDistillationSettings {

    public static class ListsSortOrderChangedEvent {
    }

    public static String KEY_SORT_ORDER = "com.battlelancer.seriesguide.lists.sortorder";

    /**
     * Builds an appropriate SQL sort statement for sorting lists.
     */
    public static String getSortQuery(Context context) {
        int sortOrderId = getSortOrderId(context);

        // convert to show sort order id
        switch (sortOrderId) {
            case ListsSortOrder.LATEST_EPISODE_ID:
                sortOrderId = ShowsSortOrder.LATEST_EPISODE_ID;
                break;
            case ListsSortOrder.OLDEST_EPISODE_ID:
                sortOrderId = ShowsSortOrder.OLDEST_EPISODE_ID;
                break;
            case ListsSortOrder.LAST_WATCHED_ID:
                sortOrderId = ShowsSortOrder.LAST_WATCHED_ID;
                break;
            case ListsSortOrder.LEAST_REMAINING_EPISODES_ID:
                sortOrderId = ShowsSortOrder.LEAST_REMAINING_EPISODES_ID;
                break;
            default:
                sortOrderId = ShowsSortOrder.TITLE_ID;
                break;
        }

        String baseQuery = ShowsDistillationSettings.getSortQuery(sortOrderId, false,
                DisplaySettings.isSortOrderIgnoringArticles(context));

        // append sorting by list type
        return baseQuery + "," + SeriesGuideContract.ListItems.SORT_TYPE;
    }

    /**
     * Returns the id as of {@link com.battlelancer.seriesguide.settings.ListsDistillationSettings.ListsSortOrder}
     * of the current list sort order.
     */
    public static int getSortOrderId(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getInt(KEY_SORT_ORDER, ListsSortOrder.TITLE_ALPHABETICAL_ID);
    }

    public interface ListsSortOrder {
        int TITLE_ALPHABETICAL_ID = 0;
        // @deprecated Only supporting alphabetical sort order going forward.
        // int TITLE_REVERSE_ALHPABETICAL_ID = 1;
        int LATEST_EPISODE_ID = 2;
        int OLDEST_EPISODE_ID = 3;
        int LAST_WATCHED_ID = 4;
        int LEAST_REMAINING_EPISODES_ID = 5;
    }
}
