package com.abuhanaan.spring_auth.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SuccessResponse extends ApiResponse {

    private Boolean status;
    private Object data;

    public static SuccessResponse buildSuccessResponse(Object data) {
        return SuccessResponse.builder()
                .status(true)
                .data(data)
                .build();
    }
}
