package org.pengwt.myhome.myhome2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @param
 * @exception
 * @return
 * @version 1.0
 * @author pengweitao 2022/4/11 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {
    private String code;
    private String message;
}
