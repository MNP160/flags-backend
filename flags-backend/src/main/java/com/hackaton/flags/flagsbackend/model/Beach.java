package com.hackaton.flags.flagsbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "beaches")
public class Beach {
    @Id
    private String id;
    private String email;
    private String password;
    private String name;
    private String lat;
    private String lon;
    private List<Outpost> outposts;

}
