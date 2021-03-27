package com.hackaton.flags.flagsbackend.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AuthenticationResponse implements Serializable {
    private String token;
}
