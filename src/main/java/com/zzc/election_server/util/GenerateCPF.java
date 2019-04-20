package com.zzc.election_server.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GenerateCPF {
    private static List<String> colnames = new ArrayList<>();
    private static List<String> colTypes = new ArrayList<>();
    private static List<String> colComment = new ArrayList<>();
    private static Boolean importDate = false;


    public static void main(String[] args) throws Exception {

        generateEntity("127.0.0.1", "3306", "count_votes", "root", "root",
//                "tb_student",
//                "studentId",
//                "Student", "com.zzc.election_server.model",

//                "tb_grade",
//                "gradeId",
//                "Grade", "com.zzc.election_server.model",
//
//                "tb_activity",
//                "activityId",
//                "Activity", "com.zzc.election_server.model",

//                "tb_activity_user",
//                "userId",
//                "ActivityUser", "com.zzc.election_server.model",

                "tb_activity_user_select",
                "activityUserSelectId",
                "ActivityUserSelect", "com.zzc.election_server.model",
                "D:/JavaProject/election_server/src/main/java/com\\zzc\\election_server\\model/");
//                 "E:\\election\\src\\main\\java\\com\\zzc\\election_server\\model/");


    }

    public static void generateEntity(String host, String port, String database, String username, String password,
                                      String tableName, String keyId,  String entityName, String basePackage, String
                                              basePath) throws Exception {
        //读取表结构
        initTable(host, port, database, username, password, tableName);
        //生成实体类
        GenerateEntity.generate(entityName, basePackage, basePath, database, tableName, keyId, colnames, colTypes,
                colComment, importDate);

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
