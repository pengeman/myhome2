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
public class BookMark<T> {
    private long id;
    private String name;
    private String url;
    private T data;

    public BookMark(String name, String url){
    this.setName(name);
    this.setUrl(url);
    }
}
