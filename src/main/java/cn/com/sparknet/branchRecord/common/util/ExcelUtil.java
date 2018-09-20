package cn.com.sparknet.branchRecord.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


/**
 * Excel工具
 * @author chenyin
 *
 */
public class ExcelUtil {
    
    /**
     * 
     * @param data数据
     * @param titleList标题集合
     * @param f存放文件路径
     * @param title文件标题
     */
    public void createExcel(List<Map<String,String>> data,List<Map<String,String>> titleList,File f,String title){
        //创建工作薄
        try {
            WritableWorkbook workbook = Workbook.createWorkbook( f );
            // 创建sheet
            WritableSheet sheet = workbook.createSheet( "第1页", 0 );
           // 前两个参数是一个 后两个参数是一个
            sheet.mergeCells( 0, 0, 0, 1 );//合并第一列第一行到第一列第二行单元格
//            sheet.mergeCells( 1, 0, 1, 1 );//合并第二列第一行到第二列第二行单元格
//            sheet.mergeCells( 0, 0, 1, 0 );//合并第一列第一行到第二列第一行单元格
//            sheet.mergeCells( 0, 0, 1, 1 );//合并从第一列第一行到第二列第二行单元格
//            sheet.addCell( new Label( 0, 0, titleList.get( 0 ).toString() ) );
//            int column = 1; 
//            for ( int i = 0; i < len; i++ )
//            {
//                nextTitle = (String) someDate.get( i );
//                sheet.addCell( new Label( column, 0, nextTitle ) );
//                sheet.addCell( new Label( column, 1, title1 ) );
//                sheet.addCell( new Label( column + 1, 1, title2 ) );
//                sheet.mergeCells( column, 0, column + 1, 0 );
//                column += 2;
//            }
//            sheet.addCell( new Label( column, 0, title5 ) );
//            sheet.addCell( new Label( column, 1, title3 ) );
//            sheet.addCell( new Label( column + 1, 1, title4 ) );
//            sheet.mergeCells( column, 0, column + 1, 0 );
//            int column = 0; 
            
//          WritableFont()方法里参数说明： 
//          这个方法算是一个容器，可以放进去好多属性 
//          第一个: TIMES是字体大小，他写的是18 
//          第二个: BOLD是判断是否为斜体,选择true时为斜体 
//          第三个: ARIAL 
//          第四个: UnderlineStyle.NO_UNDERLINE 没有下划线 
//          第五个: jxl.format.Colour.RED 字体颜色是红色的 
            jxl.write.WritableFont wf = new jxl.write.WritableFont(WritableFont.TIMES, 18, WritableFont.BOLD, true,UnderlineStyle.NO_UNDERLINE , Colour.RED); 
            jxl.write.WritableCellFormat wcfF = new jxl.write.WritableCellFormat(wf);
            wcfF.setBackground(Colour.GREY_25_PERCENT);
            //设置标题
            if(titleList.size()>0){
                for(int i = 0; i < titleList.size(); i++ ){
                    sheet.addCell( new Label( i, 0, titleList.get( i ).get( "disTitleName" ).toString() ) );
//                    column++;
                }
            }
            // 遍历数据
            for ( int i = 0; i < data.size(); i++ ){
                Map<String,String> map = data.get( i );
                for(int j = 0; j < titleList.size(); j++ ){
                    String value = map.get( titleList.get( j ).get( "titleName" ).toString())==null?"":map.get( titleList.get( j ).get( "titleName" ).toString()).toString();
                    sheet.addCell( new Label( j, i+1, value ,wcfF) );
                }
            }
            workbook.write();
            workbook.close();
//            FileInputStream fis = new FileInputStream( f );
//            BufferedInputStream fin = new BufferedInputStream( fis );
//            OutputStream out = response.getOutputStream();
//            byte[] b = new byte[20];
//            int length;
//            while ( ( length = fin.read( b ) ) > 0 )
//            {
//                out.write( b, 0, length );
//            }
        }
        catch ( IOException e ) {
            System.err.println( e.getMessage() );
            e.printStackTrace();
        }
        catch(Throwable t){
            System.err.println( t.getMessage() ); 
        }
       
    }
    
    /**
     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
     * @param file 读取数据的源Excel
     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
     * @return 读出的Excel中数据的内容
     * @throws FileNotFoundException
     * @throws IOException
     */
    @SuppressWarnings ( "deprecation" )
    public  String[][] getData(File file, int ignoreRows)
           throws FileNotFoundException, IOException {
       List<String[]> result = new ArrayList<String[]>();
       int rowSize = 0;
       BufferedInputStream in = new BufferedInputStream(new FileInputStream(
              file));
       // 打开HSSFWorkbook
       POIFSFileSystem fs = new POIFSFileSystem(in);
       HSSFWorkbook wb = new HSSFWorkbook(fs);
       HSSFCell cell = null;
       for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
           HSSFSheet st = wb.getSheetAt(sheetIndex);
           // 第一行为标题，不取
           System.out.println(st.getLastRowNum() ); 
           for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
              HSSFRow row = st.getRow(rowIndex);
              if (row == null) {
                  continue;
              }
              int tempRowSize = row.getLastCellNum() + 1;
              
              if (tempRowSize > rowSize) {
                  rowSize = tempRowSize;
              }
              String[] values = new String[rowSize];
              Arrays.fill(values, "");
              boolean hasValue = false;     
              for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                  String value = "";
                  cell = row.getCell(columnIndex);
                  if (cell != null) {
                     // 注意：一定要设成这个，否则可能会出现乱码
//                      cell.setEncoding(HSSFCell.ENCODING_UTF_16); 
                     switch (cell.getCellType()) { 
                     case HSSFCell.CELL_TYPE_STRING:
                         value = cell.getStringCellValue();
                         break;
                     case HSSFCell.CELL_TYPE_NUMERIC:
                         if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            Date date = cell.getDateCellValue();
                            if (date != null) {
                                value = new SimpleDateFormat("yyyy-MM-dd")
                                       .format(date);
                            } else {
                                value = "";
                            }
                         } else {
                            value = new DecimalFormat("0").format(cell
                                   .getNumericCellValue());
                         }
                         break;
                     case HSSFCell.CELL_TYPE_FORMULA:
                         // 导入时如果为公式生成的数据则无值
                         if (!cell.getStringCellValue().equals("")) {
                            value = cell.getStringCellValue();
                         } else {
                            value = cell.getNumericCellValue() + "";
                         }
                         break;
                     case HSSFCell.CELL_TYPE_BLANK:
                         break;
                     case HSSFCell.CELL_TYPE_ERROR:
                         value = "";
                         break;
                     case HSSFCell.CELL_TYPE_BOOLEAN:
                         value = (cell.getBooleanCellValue() == true ? "Y"
                                : "N");
                         break;
                     default:
                         value = "";
                     }
                  }
                  if (columnIndex == 0 && value.trim().equals("")) {
                     break;
                  }
                  values[columnIndex] = rightTrim(value);
                  hasValue = true;
              }
              if (hasValue) {
                  result.add(values);
              }
           }
       }
       in.close();
       String[][] returnArray = new String[result.size()][rowSize];
       for (int i = 0; i < returnArray.length; i++) {
           returnArray[i] = (String[]) result.get(i);
       }
       return returnArray;
    }
    
    /***
     * 不需要判断execl版本
     * @param file
     * @param ignoreRows
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public  String[][] getDatas(File file, int ignoreRows)
           throws FileNotFoundException, IOException {
        List<String[]> list = new ArrayList<String[]>();
        int len = 0;
            try {
                FileInputStream is = new FileInputStream(file);
                Workbook wb = Workbook.getWorkbook(is);
                Sheet[] sheets = wb.getSheets();//得到工作表对象数组
                int rsRows = 0;
                for(int k = 0;k< sheets.length;k++){
                    Sheet sheet = sheets[k];
                    if (sheets != null && sheets.length > 0) {
                        if (sheet != null) {
                             int rsColumns = sheet.getColumns();//表格总列数
//                            int rsColumns = 15;
                             rsRows =sheet.getRows();// 表格总行数
                             len +=rsRows;
                            String allValues = "abcd";
                            int rowNum = ignoreRows;
                            // for(int rowNum=1;rowNum<rsRows;rowNum++)
                            while (!allValues.equals("")) {
                                if (rowNum == rsRows) {
                                    break;
                                }
                                allValues = "";
                                String[] m = new String[rsColumns];
                                int j = 0;
                                for (int i = 0; i < rsColumns; i++) {
                                    allValues += sheet.getCell(i, rowNum).getContents()
                                            .toString().trim();
                                }
                                if (allValues.equals("")) {
                                    break;
                                }
                                for (int i = 0; i < rsColumns; i++) {
                                    String v = sheet.getCell(i, rowNum).getContents()
                                    .toString().trim();
                                  /*  if(i == 4){
                                        v = v.replaceAll("】", "】\n");
                                        v = v.replaceAll("。【", "。\n【");
                                    }*/
                                    m[i] = v;
                                    j++;
                                }
                                if (j == rsColumns) {
                                    list.add(m);
                                }
                                rowNum++;
                            }
                        }
                    }
                }
          
//          System.out.println(list);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String[][] returnArray = new String[list.size()][len];
            for (int i = 0; i < returnArray.length; i++) {
                returnArray[i] = (String[]) list.get(i);
            }
            return returnArray;
         }
    
    /***
     * 有指定列返回List集合
     * 方法根据实际情况可能有缺点 最好改成定义读取具体哪一sheet页
     * @param file
     * @param ignoreRows
     * @param mm
     * @return
     */
    @SuppressWarnings ( { "rawtypes", "unchecked" } )
    public static List getXlsDatas(File file, int ignoreRows,String[] mm){
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            try {
                FileInputStream is = new FileInputStream(file);
                Workbook wb = Workbook.getWorkbook(is);
                Sheet[] sheets = wb.getSheets();//得到工作表对象数组
                 for(int k =0;k<sheets.length;k++){
                     if (sheets != null && sheets.length > 0) {
                         Sheet sheet = sheets[k];
                         int rsRows = 0;
                         if (sheet != null) {
                             int rsColumns = sheet.getColumns();//表格总列数
//                           int rsColumns = 15;
                            rsRows += sheet.getRows();// 表格总行数
                           String allValues = "abcd";
                           int rowNum = ignoreRows;
                             // for(int rowNum=1;rowNum<rsRows;rowNum++)
                             while (!allValues.equals("")) {
                                 if (rowNum == rsRows) {
                                     break;
                                 }
                                 allValues = "";
                                 Map m = new HashMap();
                                 int j = 0;
                                 for (int i = 0; i < rsColumns; i++) {
                                     allValues += sheet.getCell(i, rowNum).getContents()
                                             .toString().trim();
                                 }
                                 if (allValues.equals("")) {
                                     break;
                                 }
                                 for (int i = 0; i < rsColumns; i++) {
                                     String v = sheet.getCell(i, rowNum).getContents()
                                     .toString().trim();
                                     m.put(mm[i], v);
                                     j++;
                                 }
                                 if (j == rsColumns) {
                                     list.add(m);
                                 }
                                 rowNum++;
                             }
                         }
                     }
                 }
//          System.out.println(list);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return list;
        
        
    }
        
        
    
    /**
     * 去掉字符串右边的空格
     * @param str 要处理的字符串
     * @return 处理后的字符串
     */
     public static String rightTrim(String str) {
       if (str == null) {
           return "";
       }
       int length = str.length();
       for (int i = length - 1; i >= 0; i--) {
           if (str.charAt(i) != 0x20) {
              break;
           }
           length--;
       }
       return str.substring(0, length);
    }

     
     public static String getPostfix(String path) {
                if (path == null || Common.EMPTY.equals(path.trim())) {
                      return Common.EMPTY;
                  }
                 if (path.contains(Common.POINT)) {
                      return path.substring(path.lastIndexOf(Common.POINT) + 1, path.length());
                  }
                  return Common.EMPTY;
              }

}
