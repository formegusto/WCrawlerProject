package com.crawler.biz.data.impl;

import java.util.List;

import com.crawler.biz.data.ContainVO;
import com.crawler.biz.data.DataVO;
import com.crawler.biz.data.LengthVO;

public interface DataService {
	//CRUD ����� �޼ҵ� ����
	//DATA ���
	public void insertData(DataVO vo);
	//DATA(List) ���
	public void insertData(List<DataVO> dataList);
	// DATAAll 등록
	public void insertDataAll(DataVO vo);
	// DATAInum 등록
	public void insertDataInum(DataVO vo);
	// DATA(List) 등록
	public void insertDataInum(List<DataVO> dataList);
	// DATAUpdate 
	public void updateData(DataVO vo);
	// DATA 상세 조회
	public DataVO getDataDseq(DataVO vo);
	//Ư�� ũ�ѷ��� DATA ��ȸ
	public List<DataVO> getData(DataVO vo);
	// DATA 상세 조회 return List<String>
	public List<String> getDataStr(DataVO vo);
	//��ü DATA ��ȸ
	public List<DataVO> getDataList(DataVO vo);
	// DATA 특정 데이터 삭제
	public void deleteDataSeq(DataVO vo);
	// DATA 전체 삭제
	public void deleteData(DataVO vo);
	// DATA 조건 삭제(문자열 포함)
	public void deleteDataContain(ContainVO vo);
	// DATA 조건 삭제(문자열 길이)
	public void deleteDataLength(LengthVO vo, String condition);
}
