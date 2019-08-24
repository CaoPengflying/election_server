package com.zzc.election_server.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author caopengflying
 * @descript
 * @createTime 2019/8/24 14:25
 */
@Data
public class ExcelStudent extends BaseRowModel {
    @ExcelProperty(value = {"学号"}, index = 0)
    private String studentNo;

    @ExcelProperty(value = "姓名", index = 1)
    private String studentName;

    @ExcelProperty(value = "密码", index = 2)
    private String studentPassword;

    @ExcelProperty(value = "性别", index = 3)
    private String sex;

    @ExcelProperty(value = "身份证号", index = 4)
    private String idCard;
}
