package org.pengwt.myhome.myhome2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @param
 * @author pengweitao 2022/4/11
 * @exception
 * @return
 * @ version
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User<T> {
    private long id;
    private String name;
    private String pwd;

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }
}
