package com.hackaton.flags.flagsbackend.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutpostCreateRequest {
    private String name;
    private String lat;
    private String lon;
    private String flag;
}
