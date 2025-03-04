package com.cromxt.bucket.client;


import com.cromxt.common.crombucket.bucketmanager.UsersBucketInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Profile({"dev", "prod"})
@RequiredArgsConstructor
public class RemoteBucketServerClient implements BucketServerClient {

    private final WebClient webClient;


    @Override
    public Mono<UsersBucketInfo> getBucketInfoByClientId(String clientId) {
        return null;
    }
}
