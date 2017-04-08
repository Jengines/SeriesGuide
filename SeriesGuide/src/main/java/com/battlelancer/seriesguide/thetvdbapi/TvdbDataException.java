package com.battlelancer.seriesguide.thetvdbapi;

/**
 * Thrown when a {@link TvdbTools} data operation fails.
 */
class TvdbDataException extends TvdbException {

    TvdbDataException(String message) {
        this(message, null);
    }

    TvdbDataException(String message, Throwable throwable) {
        super(message, throwable, Service.DATA, false);
    }
}
