package com.test.service.gateway.fifter;
import	java.util.Arrays;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<RequestTimeGatewayFilterFactory.config> {

    private static final String REQUEST_TIME_BEGIN = "requestTimeBegin";
    private static final String KEY = "withParams";

    public RequestTimeGatewayFilterFactory() {
        super(config.class);
    }

    @Override
    public GatewayFilter apply(config config) {
        return (exchange,chain) -> {
            exchange.getAttributes().put(REQUEST_TIME_BEGIN,System.currentTimeMillis());
            return chain.filter(exchange).then(
                    Mono.fromRunnable( () -> {
                      Long startTime = exchange.getAttribute(REQUEST_TIME_BEGIN);
                      if(startTime != null){
                          StringBuilder sb = new StringBuilder(exchange.getRequest().getURI().getRawPath())
                                  .append("\t")
                                  .append(":")
                                  .append(System.currentTimeMillis() - startTime)
                                  .append("ms")
                                  .append(",");
                          if(config.isWithParams()){
                              sb.append("params:").append(exchange.getRequest().getQueryParams());
                          }
                          log.info(sb.toString());
                      }
                    })
            );
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(KEY);
    }

    @Data
    public static class config{
        private boolean withParams;
    }

}
