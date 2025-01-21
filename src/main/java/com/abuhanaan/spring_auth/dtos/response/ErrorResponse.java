package com.abuhanaan.spring_auth.dtos.response;

import com.abuhanaan.spring_auth.models.ErrorCode;

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
public class ErrorResponse extends ApiResponse {

    private Boolean status;
    private ErrorCode error;
    private String message;
}
