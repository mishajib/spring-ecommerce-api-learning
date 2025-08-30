package com.mishajib.ecommerce.dtos;

import lombok.Data;


@Data
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
}
