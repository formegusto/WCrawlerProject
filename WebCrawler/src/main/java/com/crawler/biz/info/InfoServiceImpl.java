package com.crawler.biz.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crawler.biz.info.impl.InfoDAO;
import com.crawler.biz.info.impl.InfoService;

@Service("infoService")
public class InfoServiceImpl implements InfoService{
	@Autowired
	private InfoDAO infoDAO;
	@Override
	public void insertInfo(InfoVO vo) {
		infoDAO.insertInfo(vo);
	}
	@Override
	public List<InfoVO> getInfoList(InfoVO vo) {
		return infoDAO.getInfoList(vo);
	}
	
}
