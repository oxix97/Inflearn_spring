package study.spring_proxy.trace.callback;

public interface TraceCallback <T>{
    T call();
}
