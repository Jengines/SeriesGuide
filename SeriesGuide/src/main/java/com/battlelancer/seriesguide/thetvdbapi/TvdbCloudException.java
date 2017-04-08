package com.battlelancer.seriesguide.thetvdbapi;

/**
 * Thrown when a {@link TvdbTools} Cloud operation fails.
 */
class TvdbCloudException extends TvdbException {

    TvdbCloudException(String message, Throwable throwable) {
        super(message, throwable, TvdbException.Service.HEXAGON, false);
    }
}
