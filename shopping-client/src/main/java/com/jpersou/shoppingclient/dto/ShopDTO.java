package com.jpersou.shoppingclient.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {

    @NotBlank
    private String userIdentifier;
    @NotNull
    private float total;
    @NotNull
    private LocalDateTime date;
    @NotNull
    public List<ItemDTO> items;


}
