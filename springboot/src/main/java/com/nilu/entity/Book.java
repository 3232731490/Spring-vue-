package com.nilu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 倪路
 * Time: 2021/9/25-14:29
 * StuNo: 1910400731
 * Class: 19104221
 * Description:
 */
@TableName("book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String bookname;
    private String publish;
    private Float price;
    //@JsonFormat(pattern = "yyyy-mm-dd" ,timezone = "GMT+8")
    private String datetime;
}
