package com.imooc.vat.util;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelExporter<T> {
    //excel文档fields
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    //excel样式fields
    private HSSFFont font;
    private HSSFCellStyle rowStyle;
    private HSSFCellStyle headerStyle;	
    private HSSFCellStyle headerStyleHighlight;
    //excel 文件名称
    private String title;
    //T 的 类型
    private Class<T> entityType;
    //excel中的数据
    private List<T> entities;

    /* 辅助方法，将头字母改成大写 */
    public static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class<T> getEntityType() {
        return entityType;
    }

    public void setEntityType(Class<T> entityType) {
        this.entityType = entityType;
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }

    //导出excel
    public String exportExcel(HttpServletRequest request) throws Exception {
        //初始化excel
        initializeExcel();
        //开始往excel中填数
        buildExcel();
        //创建物理文件
        String fileName = title + "_" + DateUtil.date2string(new Date(), "yyyy-MM-dd_HH-mm-ss") + ".xls";
        String path = request.getSession().getServletContext().getRealPath("/") + "resources/temp/" + fileName;
        FileOutputStream stream = new FileOutputStream(path);
        workbook.write(stream);
        stream.close();
        return fileName;
    }

    public void buildExcel() {
        //创建表头行
        HSSFRow header = sheet.createRow(0);
        Field[] declaredFields = entityType.getDeclaredFields();
        //从第一列开始
        int headerIndex = 0;
        for (Field declaredField : declaredFields) {
            FieldMeta fieldMeta = declaredField.getAnnotation(FieldMeta.class);
            if (fieldMeta != null && fieldMeta.id()) {
                //解决没有注解的字段导致这一列为空为题
                HSSFCell headerCell = header.createCell(headerIndex++);
                if ("yellow".equals(fieldMeta.color())) {
                    headerCell.setCellStyle(headerStyleHighlight);
                } else {
                    headerCell.setCellStyle(headerStyle);
                }
                HSSFRichTextString headerText = new HSSFRichTextString(fieldMeta.name());
                headerCell.setCellValue(headerText);
            }
        }
        //创建数据行
        for (int i = 0; i < entities.size(); i++) {
            HSSFRow rows = sheet.createRow(i + 1);
            Map<String, Object> mapEntity = ObjectUtil.obj2map(entities.get(i));
            //从第一列开始
            int rowIndex = 0;
            for (Field declaredField : declaredFields) {
                String fieldName = org.apache.commons.lang3.StringUtils.uncapitalize(declaredField.getName());
                FieldMeta fieldMeta = declaredField.getAnnotation(FieldMeta.class);
                if (fieldMeta != null && fieldMeta.id()) {
                    HSSFCell cell = rows.createCell(rowIndex++);
                    cell.setCellStyle(rowStyle);
                    String sText = mapEntity.get(fieldName) == null ? null : mapEntity.get(fieldName).toString();
                    HSSFRichTextString text = new HSSFRichTextString(sText);
                    cell.setCellValue(text);
                }
            }
        }
    }

    //初始化excel
    private void initializeExcel() {
        //创建excel workbook
        createWorkbook();
        //设置字体
        setHssfFont();
        //设置3个样式
        setRowStyle();
        setHeaderStyle();
        setHeaderStyleHighlight();
    }

    //创建excel workbook
    private void createWorkbook() {
        // 声明一个工作薄
        workbook = new HSSFWorkbook();
        // 生成一个表格
        sheet = workbook.createSheet("lenovo");
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
    }

    //设置字体
    private void setHssfFont() {
        font = workbook.createFont();
        font.setFontHeightInPoints((short) 10);
        font.setFontName("Arial");
    }

    //设置通用cell 样式
    private HSSFCellStyle createGeneralCellStyle() {
        HSSFCellStyle generalStyle = workbook.createCellStyle();
        generalStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        generalStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        generalStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        generalStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        generalStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        generalStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        generalStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        return generalStyle;
    }

    // 设置行样式
    private void setRowStyle() {
        rowStyle = createGeneralCellStyle();
        // 把字体应用到当前的样式
        rowStyle.setFont(font);
        rowStyle.setFillForegroundColor(HSSFColor.WHITE.index);
    }

    // 设置表头样式
    private void setHeaderStyle() {
        headerStyle = createGeneralCellStyle();
        headerStyle.setFont(font);
        headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headerStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
    }

    // 设置表头样式（高亮黄色）
    private void setHeaderStyleHighlight() {
        headerStyleHighlight = createGeneralCellStyle();
        headerStyleHighlight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headerStyleHighlight.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
    }
}