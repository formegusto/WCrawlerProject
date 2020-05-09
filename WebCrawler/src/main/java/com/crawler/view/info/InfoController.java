package com.crawler.view.info;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.impl.DataService;
import com.crawler.biz.info.FrameHaveInfoVO;
import com.crawler.biz.info.FrameVO;
import com.crawler.biz.info.InfoVO;
import com.crawler.biz.info.impl.FrameService;
import com.crawler.biz.info.impl.InfoService;
import com.crawler.biz.tm.TmVO;
import com.crawler.biz.tm.impl.TmService;
import com.crawler.biz.user.UserVO;

import au.com.bytecode.opencsv.CSVReader;

@Controller
public class InfoController {
	@Autowired
	InfoService infoService;
	@Autowired
	DataService dataService;
	@Autowired
	FrameService frameService;
	@Autowired
	TmService tmService;
	
	// 클립보드 처리
	@RequestMapping(value="/clipboardConfirm.do")
	public String clipboardConfirm(@RequestParam String table,
			HttpSession session,
			Model model) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 클립보드 확인 처리");
		
		// 1. dataMap 구성
		String fieldStr = table.split("\n")[0];
		String fieldList[] = fieldStr.split("\\s+");
		Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
		for(String field : fieldList)
			dataMap.put(field, new ArrayList<DataVO>());
		
		String[] dataStr = table.split("\n");
		for(int i=1;i<dataStr.length;i++) {
			dataStr[i] = dataStr[i].replaceAll("(\r\n|\r|\n|\n\r)", " ");
			String[] dataList = dataStr[i].split("\t");
			for(int j=0;j<dataList.length;j++) {
				DataVO dvo = new DataVO();
				dvo.setData(dataList[j]);
				dataMap.get(fieldList[j]).add(dvo);
			}
		}
		
		/* 값 확인
		for(int i=0;i<fieldList.length;i++) {
			List<DataVO> dataList = dataMap.get(fieldList[i]);
			System.out.println("------" + fieldList[i] + "------");
			for(DataVO data : dataList) {
				System.out.println(data.getData());
			}
		}
		*/

		// 2. infoList 구성
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		for(String field : fieldList) {
			InfoVO info = new InfoVO();
			info.setField(field);
			info.setCssQuery("user:clipboard");
			info.setItype("user:clipboard");
			info.setLink("user:clipboard");
			
			infoList.add(info);
		}
		
		// 3. 세션에 값 저장
		model.addAttribute("infoList", infoList);
		model.addAttribute("dataMap", dataMap);
		
		
		return "clipboardConfirm.jsp";
	}
	
	// 행위객체 정보 상세 보기
	@RequestMapping(value="/getTmInfo.do")
	public String getTmInfo(InfoVO ivo, DataVO dvo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 상세 보기 기능 처리");
		// 1. DB 연동 처리(info)
		// 2. DB 연동 처리(Data)
		// 3. 세션에 값 저장
		InfoVO info = new InfoVO();
		dvo.setInum(ivo.getSeq());
		info = infoService.getInfo(ivo);
		model.addAttribute("info", info);
		model.addAttribute("dataList", dataService.getData(dvo));
		
		if(info.getItype().equals("css:linklist")) {
			DataVO dvo_2 = new DataVO();
			dvo_2.setInum(Integer.parseInt(info.getLink()));
			
			model.addAttribute("linkList",dataService.getData(dvo_2));
		}
		
		return "getTmInfo.jsp";
	}
	
	// 행위객체 정보 상세 보기
	@RequestMapping(value="/getInfo.do")
	public String getInfo(InfoVO ivo, DataVO dvo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 상세 보기 기능 처리");
		// 1. DB 연동 처리(info)
		// 2. DB 연동 처리(Data)
		// 3. 세션에 값 저장
		InfoVO info = new InfoVO();
		dvo.setInum(ivo.getSeq());
		info = infoService.getInfo(ivo);
		model.addAttribute("info", info);
		model.addAttribute("dataList", dataService.getData(dvo));
		
		if(info.getItype().equals("css:linklist")) {
			DataVO dvo_2 = new DataVO();
			dvo_2.setInum(Integer.parseInt(info.getLink()));
			
			model.addAttribute("linkList",dataService.getData(dvo_2));
		}
		
		return "getInfo.jsp";
	}
	
	// 리스트 보기
	@RequestMapping(value= { "/getInfoList.do" , "/checkInfoList.do" , "/crawlerLList.do" , "/tmObjectConfirm.do" , "/checkVisualList.do"})
	public String getInfoList(InfoVO vo, Model model, 
			HttpSession session , HttpServletRequest request ) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 리스트 보기 기능 처리");
		// 1. 다중 uri 입력으로 입력 한 uri 출력
		String uri = request.getServletPath();
		// 2. DB 연동 처리
		// 3. 세션에 값 저장
		vo.setId(((UserVO)session.getAttribute("user")).getId());
		model.addAttribute("infoList", infoService.getInfoList(vo));
		if(uri.equals("/getInfoList.do")) {
			FrameVO fvo = new FrameVO();
			fvo.setId(((UserVO)session.getAttribute("user")).getId());
			model.addAttribute("frameList", frameService.getFrameList(fvo));
			return "getInfoList.jsp";
		}
		else if(uri.equals("/checkInfoList.do"))
			return "checkInfoList.jsp";
		else if(uri.equals("/crawlerLList.do")) 
			return "crawlerLList.jsp";
		else if(uri.equals("/tmObjectConfirm.do")) {
			TmVO tvo = new TmVO();
			tvo.setId(((UserVO)session.getAttribute("user")).getId());
			model.addAttribute("tmList", tmService.getTmList(tvo));
			return "tmObjectConfirm.jsp";
		}
		else if(uri.equals("/checkVisualList.do")) {
			model.addAttribute("vtype", request.getParameter("vtype"));
			return "checkVisualList.jsp";
		}
		
		return null;
	}
	
	// 크롤러 정보 상세 보기
	@RequestMapping(value="/deleteInfo.do")
	public String getInfo(InfoVO vo, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 삭제 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리(info)
		// 3. DB 연동 처리(Data)
		// 4. 세션에 값 저장
		infoService.deleteInfo(vo);
		return "getInfoList.do";
	}
	
	// csv 정보 미리보기
	@RequestMapping(value="/csvConfirm.do")
	public String csvConfirm(HttpSession session,Model model,
			@RequestParam MultipartFile csv) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] csvConfirm 기능 처리");
		System.out.println(csv.getOriginalFilename());
		
		List<String[]> datas = new ArrayList<String[]>();
		try {
			Reader reader = new InputStreamReader(csv.getInputStream(), "EUC-KR");
			CSVReader csvReader = new CSVReader(reader);
			String[] s;
			while((s = csvReader.readNext()) != null) 
				datas.add(s);
			/* data Check
			for(String[] dat : datas) {
				for(String str : dat) {
					System.out.print(str + ",");
				}
				System.out.println();
			}
			*/
		} catch (IOException e) {
			System.out.println("CSV Error");
			e.printStackTrace();
		}
		
		String csvName_ = csv.getOriginalFilename();
		int idx = csvName_.lastIndexOf(".");
		System.out.println(idx);
		String csvName = csvName_.substring(0, idx);
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
		
		String[] fieldList = datas.get(0);
		for(String field : fieldList) {
			InfoVO info = new InfoVO();
			info.setTitle(csvName + " (" + field + ")");
			info.setField(field);
			infoList.add(info);
		}
		
		datas.remove(0);
		for(InfoVO info : infoList)
			dataMap.put(info.getField(), new ArrayList<DataVO>());
		
		for(String[] data : datas) {
			for(int i=0;i<infoList.size();i++) {
				InfoVO info = infoList.get(i);
				DataVO dvo = new DataVO();
				dvo.setData(data[i]);
				dataMap.get(info.getField()).add(dvo);
			}
		}
		
		/* data Check
		System.out.println(infoList);
		System.out.println(dataMap);
		*/
		model.addAttribute("infoList", infoList);
		model.addAttribute("dataMap", dataMap);
		
		return "csvConfirm.jsp";
	}
	
	// csv 정보 넣기
	@RequestMapping(value="/multiAdd_proc.do")
	public String multiAdd(HttpSession session,Model model,
			HttpServletRequest request,
			@RequestParam(value="ogField", required=true) List<String> ogFieldList,
			@RequestParam(value="title", required=true) List<String> titleList,
			@RequestParam(value="content", required=true) List<String> contentList,
			@RequestParam(value="link", required=true) List<String> linkList,
			@RequestParam(value="field", required=true) List<String> fieldList,
			@RequestParam(value="cssQuery", required=true) List<String> cssList) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] csv 넣는 기능 처리");
		
		for(int i=0;i<ogFieldList.size();i++) {
			// info 삽입
			InfoVO info = new InfoVO();
			info.setTitle(titleList.get(i));
			info.setContent(contentList.get(i));
			info.setLink(linkList.get(i));
			info.setField(fieldList.get(i));
			info.setCssQuery(cssList.get(i));
			info.setId(((UserVO)session.getAttribute("user")).getId());
			info.setItype(linkList.get(0));
			infoService.insertInfo(info);
			
			// 데이터 삽입
			String ogField = ogFieldList.get(i);
			String[] datas = request.getParameterValues(ogField);
			for(String data : datas) {
				DataVO dvo = new DataVO();
				dvo.setData(data);
				dataService.insertData(dvo);
			}
		}
		
		return "getInfoList.do";
	}
	
	// 크롤러 정보 상세 보기
	@RequestMapping(value="/crawlerLLAdd.do")
	public String crawlerLLAdd(InfoVO ivo, Model model, HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 상세 보기 기능 처리");
		// 1. 사용자 입력정보 추출
		// 2. DB 연동 처리(info)
		// 3. DB 연동 처리(Data)
		// 4. 세션에 값 저장
		DataVO dvo = new DataVO();
		dvo.setInum(Integer.parseInt(ivo.getLink()));
		
		model.addAttribute("urlList",dataService.getData(dvo));
		model.addAttribute("info", ivo);

		return "crawlerLLAdd.jsp";
	}
	
	@RequestMapping(value="/getFrame.do")
	public String getBoard(FrameVO vo, HttpSession session,
			Model model) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 프레임 상세 조회 기능 처리");

		// infoList Make
		List<FrameHaveInfoVO> fhiList = frameService.getFHIList(vo);
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		for(FrameHaveInfoVO fhi : fhiList) {
			InfoVO ivo = new InfoVO();
			ivo.setSeq(fhi.getInum());
			infoList.add(infoService.getInfo(ivo));
		}
		
		// dataMap Make
		Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
		for(FrameHaveInfoVO fhi : fhiList) {
			DataVO dvo = new DataVO();
			dvo.setInum(fhi.getInum());
			List<DataVO> dataList = dataService.getData(dvo);
			System.out.println(dataList);
			dataMap.put( fhi.getInum()+"" , dataList);
		}
		
		model.addAttribute("frame", vo);
		model.addAttribute("infoList", infoList);
		model.addAttribute("dataMap", dataMap);
		return "getFrame.jsp";
	}
	
	@RequestMapping(value="/frameConfirm.do")
	public String boardConfirm(@RequestParam String seqList,
			Model model , HttpSession session) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 정보 리스트 여러개 보기 기능 처리");
		
		// 1. infoList 구축
		System.out.println(seqList);
		String[] seqList_ = seqList.split(",");
		List<InfoVO> infoList = new ArrayList<InfoVO>();
		for(String seq : seqList_) {
			InfoVO vo = new InfoVO();
			vo.setSeq(Integer.parseInt(seq));
			infoList.add(infoService.getInfo(vo));
		}
		
		// 2. dataMap 구축
		Map<String, List<DataVO>> dataMap = new HashMap<String, List<DataVO>>();
		for(String seq : seqList_) {
			DataVO dvo = new DataVO();
			dvo.setInum(Integer.parseInt(seq));
			List<DataVO> dataList = dataService.getData(dvo);
			dataMap.put(seq, dataList);
		}
		
		// 3. session에 값 저장
		model.addAttribute("infoList", infoList);
		model.addAttribute("dataMap", dataMap);
		return "frameConfirm.jsp";
	}
	
	@RequestMapping(value="/frameAdd_proc.do")
	public String boardAdd(FrameVO fvo, HttpSession session , 
			@RequestParam(value="inumList", required=true) List<String> inumList) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 게시판 등록 기능 처리");
		
		frameService.insertFrame(fvo);
		
		for(String inum : inumList) {
			FrameHaveInfoVO fhivo = new FrameHaveInfoVO();
			fhivo.setInum(Integer.parseInt(inum));
			frameService.insertFHI(fhivo);
		}
		
		return "getInfoList.do";
	}
	
	@RequestMapping(value="/deleteFrame.do")
	public String deleteBoard(FrameVO vo, HttpSession session, Model model) {
		if(session.getAttribute("user") == null)
			return "topHead.jsp";
		System.out.println("[Spring Service MVC Framework] 프레임 삭제 기능 처리");
		
		frameService.deleteFrame(vo);
		
		return "getInfoList.do";
	}
}
