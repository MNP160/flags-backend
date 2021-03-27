package com.hackaton.flags.flagsbackend.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlagUpdateRequest implements Serializable {
    private int outpost_id;
    private String flag;

}
