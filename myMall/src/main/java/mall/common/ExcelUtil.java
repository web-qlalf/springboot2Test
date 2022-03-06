package mall.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import mall.domain.MemberDto;
import mall.service.ExcelService;

@Controller
public class ExcelUtil {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExcelService excelService;

    private int rowNum = 0;
    
    //File로 만들 경우
    public String createExcelToFile(List<Map<String, Object>> datas, String filepath, String sheetName) throws FileNotFoundException, IOException {
        //workbook = new HSSFWorkbook(); // 엑셀 97 ~ 2003
        //workbook = new XSSFWorkbook(); // 엑셀 2007 버전 이상	

        Workbook workbook = new SXSSFWorkbook(); // 성능 개선 버전
        Sheet sheet = workbook.createSheet(sheetName);

        rowNum = 0;

        createExcel(sheet, datas);
        //"A:/dev64/git-project/springboot2Test/springboot2Test//myMall/src/main/resources/static"
        //C:/Users/JMY/Desktop/
//        String src = "A:\\\\dev64\\\\git-project\\\\springboot2Test\\\\springboot2Test\\\\myMall\\\\src\\\\";
        
        
        String totalPath = "A:\\dev64\\git-project\\springboot2Test\\springboot2Test\\myMall\\src\\text2.xlsx";
        String totalPath2 = "\\text2.xlsx";
        FileOutputStream fos = new FileOutputStream(new File(totalPath));
        workbook.write(fos);
        workbook.close();
        
        return "/excel/total?path=" +  totalPath2;

    }
    
    //HttpServletResponse 경우
    public void createExcelToResponse(List<Map<String, Object>> datas, String filename, HttpServletResponse response) throws IOException {
        Workbook workbook = new SXSSFWorkbook(); // 성능 개선 버전
        Sheet sheet = workbook.createSheet(filename);

        rowNum = 0;

        createExcel(sheet, datas);
        
        // 컨텐츠 타입과 파일명 지정
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", String.format("attachment;filename=%s.xlsx", filename));
        
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    //엑셀 생성
    private void createExcel(Sheet sheet, List<Map<String, Object>> datas) {
    
        //데이터를 한개씩 조회해서 한개의 행으로 만든다.
        for (Map<String, Object> data : datas) {
            //row 생성
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;
            
            //map에 있는 데이터를 한개씩 조회해서 열을 생성한다.
            for (String key : data.keySet()) {
                //cell 생성
                Cell cell = row.createCell(cellNum++);
               	
                //cell에 데이터 삽입
                cell.setCellValue(data.get(key).toString());
            }
        }
    }

	public String excelDownload(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String fileName = (String) req.getAttribute("uri");
		String restu = null;
		 Workbook workbook = new SXSSFWorkbook(); // 성능 개선 버전
	     Sheet sheet = workbook.createSheet(fileName);

	    rowNum = 0;
	    
	    List<Map<String, Object>> datas  = (List<Map<String, Object>>) req.getAttribute("datas");
		if(fileName.equals("memeberList")) {
			restu = createExcelToFile(datas,fileName, fileName);
//			createExcelToResponse(datas, fileName, res);
		}
		return restu;
		
	}

}