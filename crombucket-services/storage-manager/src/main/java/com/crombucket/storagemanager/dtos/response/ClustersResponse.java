package com.crombucket.storagemanager.dtos.response;

import java.time.LocalDate;

public record ClustersResponse(
    String id,
    String clusterCode,
    Long availableSpace,
    LocalDate createdAt) {

}
