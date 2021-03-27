package com.hackaton.flags.flagsbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Outpost {
    private int outpost_id;
    private String name;
    private String lat;
    private String lon;
    private String flag;


}
