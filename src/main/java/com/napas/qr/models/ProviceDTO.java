package com.napas.qr.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProviceDTO {

    private Long id;

    private String provName;

    private Date dateCreated;

    private Date dateModified;
}
