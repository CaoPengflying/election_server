package com.zzc.election_server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emmet on 17-3-29.
 * desc:
 */
public class GenerateSYS {
    private static List<String> colnames = new ArrayList<>();
    private static List<String> colTypes = new ArrayList<>();
    private static List<String> colComment = new ArrayList<>();
    private static Boolean importDate = false;

    public static void main(String[] args) throws Exception {
//        generate("rm-uf63aru1kh3hmfty5o.mysql.rds.aliyuncs.com","3306","bms","bms_dbuser","B*mS#68sjqp!","t_customer_limit","CifCustomerLimit"," com.zmkj.bms","E:\\workspace\\bnh\\zmkj-bms\\bms-core\\src\\main\\java\\com\\zmkj\\bms");
        //     generateEntity("rm-bp1lx35997eliful6o.mysql.rds.aliyuncs.com", "3306", "mkl_order", "mkl_test", "4BeLdVeRBY",
        //             "pos_identify_result",
        //             "PosIdentifyResult", "com.mclon.facade.service.api.mapper.model", "/private/var/root/Documents/MKL_git/mclon/facade/facade-service-api/src/main/java/com/mclon/facade/service/api/mapper/model/");
//         销售开单
        generateEntity("rm-bp1lx35997eliful6o.mysql.rds.aliyuncs.com", "3306", "mkl_order", "mkl_test", "4BeLdVeRBY",
                "pos_identify_result",
                "identifyId",
                "PosIdentifyResult", "com.mclon.facade.service.api.mapper.model", "/private/var/root/Documents/MKL_git/mclon/facade/facade-service-api/src/main/java/com/mclon/facade/service/api/mapper/model/");
        // 销售开单明细
//        generateEntity("rm-bp1lx35997eliful6o.mysql.rds.aliyuncs.com", "3306", "mkl_order", "mkl_test", "4BeLdVeRBY",
//                "pos_order_detail",
//                "PosOrderDetail", "com.mclon.facade.service.api.mapper.model", "E:/mclonMclonBJ/mclon_slm_server/facade/facade-service-api/src/main/java/com/mclon/facade/service/api/mapper/model/");
//        generateEntity("rm-bp1lx35997eliful6o.mysql.rds.aliyuncs.com", "3306", "mkl_order", "mkl_test", "4BeLdVeRBY",
//                "pos_cash_deduct_use",
//                "PosCashDeductUse", "com.mclon.facade.service.api.mapper.model", "F:/bojundevolop/mclog_mayun/facade/facade-service-api/src/main/java/com/mclon/facade/service/api/mapper/model/");
    }

    public static void generateEntity(String host, String port, String database, String username, String password, String tableName, String primaryKey, String entityName, String basePackage, String basePath) throws Exception {
        //读取表结构
        initTable(host, port, database, username, password, tableName);
        //生成实体类
        GenerateEntity.generate(entityName, basePackage, basePath, database, tableName, primaryKey, colnames, colTypes, colComment, importDate);

    }

    private static void initTable(String host, String port, String database, String username, String password, String tableName) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = String.format("jdbc:mysql://%s:%s/%s", host, port, database);
        Connection conn = DriverManager.getConnection(url, username, password);
        String strsql = "SELECT COLUMN_NAME, DATA_TYPE, COLUMN_KEY, COLUMN_COMMENT  FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '" + database + "' AND TABLE_NAME = '" + tableName + "'"; //读一行记录;
        PreparedStatement pstmt = conn.prepareStatement(strsql);
        ResultSet result = pstmt.executeQuery();
        while (result.next()) {
            colnames.add(result.getString(1));
            colTypes.add(result.getString(2));
            colComment.add(result.getString(4));
            if ("date".equals(result.getString(2)) || "datetime".equals(result.getString(2)) || "timestamp".equals(result.getString(2))) {
                importDate = true;
            }
        }
        if (conn != null) {
            conn.close();
        }
    }
}
