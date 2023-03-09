package org.dows.ods.channel.hnilab;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import org.dows.ods.api.*;
import org.dows.ods.channel.hnilab.client.UrlInterceptor;
import org.dows.ods.channel.hnilab.client.HnilabEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


@RequiredArgsConstructor
//@EnableConfigurationProperties(ChannelProperties.class)
@Configuration
public class HnilabConfig implements ChannelConfig {
    @Getter
    private final ChannelProperties channelProperties;

    private final String channel = "hnilab";

    @Override
    public String getChannelName() {
        return channel;
    }


    public ChannelApi getChannelApi() {
        return new HnilabApi();
    }

    @Bean("testHnlib")
    public HnilabEndpoint testHnilabEndpoint() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new UrlInterceptor());

        OkHttpClient okHttpClient = okHttpClientBuilder.build();
        okHttpClient.dispatcher().setMaxRequestsPerHost(20);
        okHttpClient.dispatcher().setMaxRequests(256);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://testweb.hnilab.com")
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(HnilabEndpoint.class);
    }

    @Bean("prdHnlib")
    public HnilabEndpoint prdHnilabEndpoint() {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(new UrlInterceptor());

        OkHttpClient okHttpClient = okHttpClientBuilder.build();
        okHttpClient.dispatcher().setMaxRequestsPerHost(20);
        okHttpClient.dispatcher().setMaxRequests(256);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.hnilab.com")
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(HnilabEndpoint.class);
    }


}
