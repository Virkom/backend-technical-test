package com.tui.proof.ws.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class Holder {
    private String name;
    private String lastName;
    private String address;
    private String postalCode;
    private String country;
    private String email;
    private List<String> telephones;
}
