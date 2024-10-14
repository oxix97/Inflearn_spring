package study.spring_proxy.trace.template;

import lombok.RequiredArgsConstructor;
import study.spring_proxy.trace.TraceStatus;
import study.spring_proxy.trace.logtrace.LogTrace;

@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {

    private final LogTrace trace;

    public T execute(String message) {
        TraceStatus status = null;

        try {
            status = trace.begin(message);
            T result = call();
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
