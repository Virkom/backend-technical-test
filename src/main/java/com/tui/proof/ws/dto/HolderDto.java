package com.tui.proof.ws.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.tui.proof.ws.model.Holder;

@Data
@NoArgsConstructor
public class HolderDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String address;

    @NotEmpty
    private String postalCode;

    @NotEmpty
    private String country;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private List<@NotEmpty String> telephones;

    public HolderDto(Holder holder) {
        this.name = holder.getName();
        this.lastName = holder.getLastName();
        this.address = holder.getAddress();
        this.postalCode = holder.getPostalCode();
        this.country = holder.getCountry();
        this.email = holder.getEmail();
        this.telephones = holder.getTelephones();
    }
}
