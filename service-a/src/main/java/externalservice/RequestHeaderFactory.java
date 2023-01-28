package externalservice;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

@ApplicationScoped
@Slf4j
public class RequestHeaderFactory implements ClientHeadersFactory {


    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> inHeaders,
                                                 MultivaluedMap<String, String> outHeaders) {
        String version = inHeaders.getFirst("x-version");
        log.info("Version Header: %s", version);
        String traceId = inHeaders.getFirst("x-b3-traceid");
        log.info("Trace Header: %s", traceId);
        MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
        result.add("X-Version", version);
        result.add("X-B3-TraceId", traceId);
        result.add("X-B3-SpanId", inHeaders.getFirst("x-b3-spanid"));
        result.add("X-B3-ParentSpanId", inHeaders.getFirst("x-b3-parentspanid"));
        return result;
    }
}