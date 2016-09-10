package com.e3.houzzerator.datascrapper.config;

import com.e3.houzzerator.datascrapper.download.DefaultSubstitutionHandlerImpl;
import com.e3.houzzerator.datascrapper.download.RestScanner;
import com.e3.houzzerator.datascrapper.download.model.SubstitutionHandler;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:  etshiorny
 * Date:    6/27/16.
 */

@Configuration
public class DefaultConfiguration {

    @Bean
    public HttpClient getHttpClient() {
        return HttpClients.createDefault();
    }

    @Bean
    public RestScanner getRestScanner() {
        return new RestScanner();
    }

    @Bean
    public SubstitutionHandler getSubstitutor() {
        return new DefaultSubstitutionHandlerImpl();
    }
}
