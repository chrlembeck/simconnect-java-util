package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvVorListResponse;

@FunctionalInterface
public interface VorListHandler {

    void hanldeVorList(RecvVorListResponse response);
}