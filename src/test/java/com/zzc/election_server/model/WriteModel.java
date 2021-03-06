package com.zzc.election_server.model;

import com.alibaba.excel.annotation.ExcelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class WriteModel extends BaseWriteModel {



    @ExcelProperty(value = {"表头3"},index = 2)
    private int p3;

    @ExcelProperty(value = {"表头1"},index = 3)
    private long p4;

    @ExcelProperty(value = {"表头5"},index = 4)
    private String p5;

    @ExcelProperty(value = {"表头6"},index = 5)
    private float p6;

    @ExcelProperty(value = {"表头6"},index = 6)
    private BigDecimal p7;


    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public int getP3() {
        return p3;
    }

    public void setP3(int p3) {
        this.p3 = p3;
    }

    public long getP4() {
        return p4;
    }

    public void setP4(long p4) {
        this.p4 = p4;
    }

    public String getP5() {
        return p5;
    }

    public void setP5(String p5) {
        this.p5 = p5;
    }

    public float getP6() {
        return p6;
    }

    public void setP6(float p6) {
        this.p6 = p6;
    }

    public BigDecimal getP7() {
        return p7;
    }

    public void setP7(BigDecimal p7) {
        this.p7 = p7;
    }


    @Override
    public String toString() {
        return "JavaModel1{" +
            "p1='" + p1 + '\'' +
            ", p2='" + p2 + '\'' +
            ", p3=" + p3 +
            ", p4=" + p4 +
            ", p5='" + p5 + '\'' +
            ", p6=" + p6 +
            ", p7=" + p7 +
            '}';
    }
}
