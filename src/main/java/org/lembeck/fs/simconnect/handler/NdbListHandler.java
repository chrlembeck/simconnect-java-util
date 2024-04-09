package org.lembeck.fs.simconnect.handler;

import org.lembeck.fs.simconnect.response.RecvNdbListResponse;

@FunctionalInterface
public interface NdbListHandler {

    void hanldeNdbList(RecvNdbListResponse response);
}