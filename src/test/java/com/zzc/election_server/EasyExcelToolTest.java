package com.zzc.election_server;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Font;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Lists;
import com.zzc.election_server.mapper.StudentMapper;
import com.zzc.election_server.model.ExcelStudent;
import com.zzc.election_server.model.Student;
import com.zzc.election_server.model.WriteModel;
import com.zzc.election_server.util.FileUtil;
import com.zzc.election_server.util.ModelTransformUtils;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zzc.election_server.util.DataUtil.*;

/**
 * @author caopengflying
 * @descript
 * @createTime 2019/8/24 13:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElectionServerApplication.class)
@WebAppConfiguration
public class EasyExcelToolTest {
    @Resource
    private StudentMapper studentMapper;
    @Test
    public void  testWriteWithoutHead(){
        try {
            OutputStream outputStream = new FileOutputStream("helloworldmerge.xlsx");
            ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX, true);
            Sheet sheet = new Sheet(1,0, ExcelStudent.class);
            sheet.setSheetName("学生列表");
            List<Student> students = studentMapper.selectAll();
            List list = ModelTransformUtils.exchangeClassList(students, ExcelStudent.class);
            sheet.setAutoWidth(true);
            sheet.setTableStyle(createTableStyle());
            writer.write(list, sheet);
            writer.merge(5,20,1,1);
            writer.finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void writeV2007WithTemplate() throws IOException {
        OutputStream out = new FileOutputStream("2007.xlsx");
        ExcelWriter writer = new ExcelWriter(out,ExcelTypeEnum.XLSX);
        //写第二个sheet sheet2  模型上打有表头的注解，合并单元格
        Sheet sheet2 = new Sheet(1, 1, WriteModel.class, "第一个sheet", null);
        sheet2.setTableStyle(createTableStyle());
        sheet2.setStartRow(20);
        writer.write(createTestListJavaMode(), sheet2);

        writer.finish();
        out.close();

    }

}
