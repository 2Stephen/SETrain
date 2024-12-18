package com.whut.backend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Params {

    String title;
    Integer questionBankId;
    Integer pageNum;
    Integer pageSize;

}
