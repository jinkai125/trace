package com.trace.core;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class AdapterTraceId implements TraceId, Set<TraceId>{

    private final Set<TraceId> traceIds = Collections.synchronizedSet(new HashSet<>());

    AdapterTraceId() {
    	traceIds.add(NativeTraceId.getInstance());
    	try {
    		if (MDCTraceId.getInstance() != null) {
    			traceIds.add(MDCTraceId.getInstance());
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	@Override
	public int size() {
		return traceIds.size();
	}

	@Override
	public boolean isEmpty() {
		return traceIds.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return traceIds.contains(o);
	}

	@Override
	public Iterator<TraceId> iterator() {
		return traceIds.iterator();
	}

	@Override
	public Object[] toArray() {
		return traceIds.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return traceIds.toArray(a);
	}

	@Override
	public boolean add(TraceId e) {
		e.set(get());
		return traceIds.add(e);
	}

	@Override
	public boolean remove(Object o) {
		if (o instanceof TraceId) {
			TraceId t = (TraceId) o;
			t.clean();
		}
		return traceIds.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return traceIds.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends TraceId> c) {
		c.forEach(t->{
			t.set(get());
		});
		return traceIds.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return c.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		for (Object object : c) {
			if (object instanceof TraceId) {
				TraceId t = (TraceId) object;
				t.clean();
			}
		}
		return traceIds.removeAll(c);
	}

	@Override
	public void clear() {
		clean();
		traceIds.clear();
	}
}
