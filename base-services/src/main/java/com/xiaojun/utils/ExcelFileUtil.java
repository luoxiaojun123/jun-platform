package com.xiaojun.utils;

import com.aspose.cells.CheckBox;
import com.aspose.cells.Workbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * https://apireference.aspose.com/java/cells/com.aspose.cells/checkbox
 * @author long.luo
 * @date 2019/3/11 17:57
 */
@Component
public class ExcelFileUtil {

    public void excel() throws Exception {
        String filePath = "file/test.xls";

        InputStream ins = new ClassPathResource(filePath).getInputStream();

        Workbook wb = new Workbook(ins);

        com.aspose.cells.Worksheet worksheet = wb.getWorksheets().get(0);
        com.aspose.cells.Cells cells = worksheet.getCells();

        int index = wb.getWorksheets().get(0).getCheckBoxes().add(2, 1, 20, 100);
        int index2 = wb.getWorksheets().get(0).getCheckBoxes().add(2, 2, 20, 100);
        CheckBox checkBox = worksheet.getCheckBoxes().get(index);
        CheckBox checkBox2 = worksheet.getCheckBoxes().get(index2);
        checkBox.setText("是");
        checkBox.setCheckedValue(1);

        checkBox2.setText("否");
        checkBox2.setCheckedValue(0);

        com.aspose.cells.Cell cell = cells.get("B3");
       // cell.setValue(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

        // Adding a date/time value to the cell
        cell = cells.get("B4");
        cell.setValue("新申请");

        cell = cells.get("A19");
        String str = cell.getStringValue();
        str = str.replace("contactName", "luoxiaojun");
        cell.setValue(str);

        File tempFilePDF = new File("d://test.pdf");
        FileOutputStream fileOS = new FileOutputStream(tempFilePDF);
        wb.save(fileOS, com.aspose.cells.SaveFormat.PDF);
    }
}
