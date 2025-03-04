package com.cromxt.bucket.models;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MediaObjects {
    private String mediaId;
    private String fileId;
    private Long fileSize;
    private String extension;
    private String accessUrl;
    private Boolean isPublic;
    private String regionId;
    private String clusterId;
    private String bucketId;
}
