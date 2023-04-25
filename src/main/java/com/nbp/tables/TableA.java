package com.nbp.tables;


import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TableA {

    private String table;
    private String currency;
    private String code;
    private ArrayList<TableADetails> rates;

}
