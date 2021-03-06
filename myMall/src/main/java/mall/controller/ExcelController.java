package mall.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mall.commons.excel.ExcelUtil;
import mall.domain.MemberDto;
import mall.service.ExcelService;

@Controller
public class ExcelController {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ExcelService excelService;
	
	@GetMapping("/excel/total")
	@ResponseBody
	public Map<String, Object>  txtTopage(@RequestParam("path") String txt, Model model) {
		System.out.println("txtTopage");
		System.out.println(txt);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parh",txt);
		System.out.println(map);
		return map;
		
	}
	
	@RequestMapping("/excel/requestDownload/{method}")
	public String excel(HttpServletRequest req, HttpServletResponse res) {
		String restu = null;
		ArrayList test = new ArrayList();
	  try {
		String uri = req.getRequestURI();
		String str2 = uri.replace("/excel/requestDownload/", "");
		req.setAttribute("uri", str2);
		List<Map<String, Object>> datas = new ArrayList<>();
		XSSFSheet sheet = null;
		XSSFCell cell = null;
		XSSFRow row = null;

		
		if (str2.equals("memeberList")) {
			List<MemberDto> list = excelService.selectMemberListAgGrid();

			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> str = new HashMap<>();
					str.put("??????????????????", list.get(i).getUsridx());
					str.put("?????????", list.get(i).getId());
					str.put("??????", list.get(i).getName());
					str.put("?????????", list.get(i).getEmail());
					str.put("?????????", list.get(i).getTel());
					str.put("??????", list.get(i).getGender());
					str.put("?????????", list.get(i).getCreatedtime());
					str.put("????????????", list.get(i).getUserauth());
					
					datas.add(str);
				}
				req.setAttribute("datas", datas);
			}
		}
		
	  	ExcelUtil excelUtil = new ExcelUtil();
		// ?????? ???????????? ?????? 
		 restu = excelUtil.excelDownload(req, res);
		 restu = restu.replace("\\", "/");
		System.out.println(restu);
	  }catch(Exception e) {
	  	e.printStackTrace();
	  }
	  return "redirect:" + restu;
	}

	

	@GetMapping(value = "/excelDownload")
	public void excelDownload(HttpServletResponse response) {
		List<Map<String, Object>> datas = new ArrayList<>();

		Map<String, Object> data1 = new HashMap<>();
		data1.put("id", 1);
		data1.put("name", "kim");

		Map<String, Object> data2 = new HashMap<>();
		data2.put("id", 2);
		data2.put("name", "park");

		datas.add(data1);
		datas.add(data2);

		ExcelUtil excelUtil = new ExcelUtil();

		try {
			excelUtil.createExcelToResponse(datas, String.format("%s-%s", "data", LocalDate.now().toString()),response);
		} catch (IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}
}
