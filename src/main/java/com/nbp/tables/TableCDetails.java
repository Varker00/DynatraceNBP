package com.nbp.tables;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
public class TableCDetails {
    private String no;
    private String effectiveDate;
    private double bid;
    private double ask;
}
