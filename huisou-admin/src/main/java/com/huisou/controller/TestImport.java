package com.huisou.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.common.BaseUtil;
import com.common.ResUtils;
@RestController
@RequestMapping( value = "/api/admin/import")
public class TestImport extends BaseController{
    
    protected static Logger logger = LogManager.getLogger(BaseUtil.class.getName());
    
    /**
     * Excel 2003
     */
    private final static String XLS = "xls";
    /**
     * Excel 2007
     */
    private final static String XLSX = "xlsx";
    /**
     * 分隔符
     */
    private final static String SEPARATOR = "|";
    
    /**
     * 由Excel文件的Sheet导出至List
     * 
     * @param file
     * @param sheetNum
     * @return
     */
    public static List<String> exportListFromExcel(File file, int sheetNum)
            throws IOException {
        return exportListFromExcel(new FileInputStream(file),
                FilenameUtils.getExtension(file.getName()), sheetNum);
    }
    /**
     * 由Excel流的Sheet导出至List
     * 
     * @param is
     * @param extensionName
     * @param sheetNum
     * @return
     * @throws IOException
     */
    public static List<String> exportListFromExcel(InputStream is,
            String extensionName, int sheetNum) throws IOException {
 
        Workbook workbook = null;
 
        if (extensionName.toLowerCase().equals(XLS)) {
            workbook = new HSSFWorkbook(is);
        } else if (extensionName.toLowerCase().equals(XLSX)) {
            workbook = new XSSFWorkbook(is);
        } else {
            throw new IOException("不支持的文件类型");
        }
 
        return exportListFromExcel(workbook, sheetNum);
    }
    /**
     * 由指定的Sheet导出至List
     * 
     * @param workbook
     * @param sheetNum
     * @return
     * @throws IOException
     */
    private static List<String> exportListFromExcel(Workbook workbook,
            int sheetNum) {
 
        Sheet sheet = workbook.getSheetAt(sheetNum);
        
        // 解析公式结果
        FormulaEvaluator evaluator = workbook.getCreationHelper()
                .createFormulaEvaluator();
 
        List<String> list = new ArrayList<String>();
 
        int minRowIx = sheet.getFirstRowNum();
        int maxRowIx = sheet.getLastRowNum();
        for (int rowIx = minRowIx; rowIx <= maxRowIx; rowIx++) {
            Row row = sheet.getRow(rowIx);
            if (null == row){
                continue;
            }
            StringBuilder sb = new StringBuilder();
 
            short minColIx = row.getFirstCellNum();
            short maxColIx = row.getLastCellNum();
            for (short colIx = minColIx; colIx <= maxColIx; colIx++) {
                Cell cell = row.getCell(new Integer(colIx));
                CellValue cellValue = evaluator.evaluate(cell);
                if (cellValue == null) {
                    continue;
                }
                // 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
                // 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
                switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    sb.append(cellValue.getBooleanValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    // 这里的日期类型会被转换为数字类型，需要判别后区分处理
                    if (DateUtil.isCellDateFormatted(cell)) {
                        sb.append(cell.getDateCellValue());
                    } else {
                        //把手机号码转换为字符串
                         DecimalFormat df = new DecimalFormat("#");
                        sb.append(df.format(cellValue.getNumberValue()));
                    }
                    break;
                case Cell.CELL_TYPE_STRING:
                    sb.append(cellValue.getStringValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    break;
                case Cell.CELL_TYPE_BLANK:
                    break;
                case Cell.CELL_TYPE_ERROR:
                    break;
                default:
                    break;
                }
            }
            if(rowIx == 0 || StringUtils.isBlank(sb.toString())){
                continue;
            }
            if (rowIx == 0 && !sb.toString().equals("手机号码")){
                list.add("false");
                return list;
            }
            list.add(sb.toString());
        }
        return list;
    }
    
    /**
     * 语音效果统计文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/voiceStatis", method = RequestMethod.POST)
    public Object voiceStatis(MultipartFile file){
        try {
            String fileName = file.getOriginalFilename();
            String extension = fileName.lastIndexOf(".") == -1 ? "" : fileName
                    .substring(fileName.lastIndexOf(".") + 1);
            if ("xls".equals(extension) || "xlsx".equals(extension)){
//                List<String> listS= exportListFromExcel(new File(fileUrl),0);
                List<String> listS = exportListFromExcel(file.getInputStream(),extension,0);
                if (null == listS || listS.size() <= 0){
                    return ResUtils.errRes("-200","上传的文件为空");
                }
                if (null != listS && listS.contains("false")){
                    return ResUtils.errRes("404", "上传的文件格式不正确");
                }
                logger.info("解析手机号列表为");
                for (String string : listS) {
                    logger.info(string);
                }
            } else {
                return ResUtils.errRes("-200","请上传以.xls或者.xlsx结尾的excel文件");
            }
            return ResUtils.okRes();
        } catch (Exception e) {
            e.getMessage();
            return ResUtils.errRes("-200","解析语音效果统计异常");
        }
    }
    
//   /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        String path = "D:\\test.xlsx";
//        try {
//           List<String> listS= exportListFromExcel(new File(path),0);
//            
//            if (null != listS && listS.size() > 0){
//           }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
 
}

