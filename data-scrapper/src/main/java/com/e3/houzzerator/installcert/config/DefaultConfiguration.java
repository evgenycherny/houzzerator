package com.e3.houzzerator.installcert.config;

import com.e3.houzzerator.installcert.download.DefaultSubstitutor;
import com.e3.houzzerator.installcert.download.RestScanner;
import com.e3.houzzerator.installcert.download.model.ISubstitutor;
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
    public ISubstitutor getSubstitutor() {
        return new DefaultSubstitutor();
    }
}
