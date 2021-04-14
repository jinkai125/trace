package com.trace.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdapterTraceId implements TraceId {

    private final List<TraceId> traceIds = Collections.synchronizedList(new ArrayList<>());

    public AdapterTraceId() {
        try {
            traceIds.add(new MDCTraceId());
        } catch (Exception e) {
        }
        traceIds.add(TraceId.NATIVE);
    }

    public AdapterTraceId(String key) {
        key.getBytes();
        try {
            traceIds.add(new MDCTraceId(key));
        } catch (Exception e) {
        }
        traceIds.add(TraceId.NATIVE);
    }

    public AdapterTraceId(List<TraceId> traceIds) {
        if (!traceIds.contains(TraceId.NATIVE)) {
            traceIds.add(TraceId.NATIVE);
        }
        if (!traceIds.contains(TraceId.DEFAULT_ADAPTER)) {
            traceIds.add(TraceId.DEFAULT_ADAPTER);
        }
        this.traceIds.addAll(traceIds);
    }

    @Override
    public String get() {
        for (TraceId traceId : traceIds) {
            if (traceId.get() != null) {
                return traceId.get();
            }
        }
        return null;
    }

    @Override
    public void set(String traceId) {
        traceIds.forEach(trace -> {
            trace.set(traceId);
        });
    }

    @Override
    public void clean() {
        traceIds.forEach(trace -> {
            trace.clean();
        });
    }
}
