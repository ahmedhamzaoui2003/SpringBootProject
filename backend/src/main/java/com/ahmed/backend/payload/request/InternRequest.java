package com.ahmed.backend.payload.request;

import lombok.Data;

@Data
public class InternRequest {
    private String name;
    private String email;
    private String department;
    private Long supervisorId;
}

