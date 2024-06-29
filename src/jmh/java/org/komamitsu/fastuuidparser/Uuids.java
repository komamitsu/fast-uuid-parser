package org.komamitsu.fastuuidparser;

import java.util.UUID;

final class Uuids {
    public static final int NUM_OF_UUIDS = 10000;
    public static final String UUIDS[];

    static {
        UUIDS = new String[NUM_OF_UUIDS];
        for (int i = 0; i < NUM_OF_UUIDS; i++) {
            UUIDS[i] = UUID.randomUUID().toString();
        }
    }
}
