package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseInfo;
import com.ssafy.happyhouse.model.dto.SidoCodeDTO;

public interface SelectBoxService {

	List<SidoCodeDTO> selectSido();

	List<SidoCodeDTO> selectGugun(String sido);

	List<HouseInfo> selectDong(String gugun);

	List<HouseInfo> selectApt(String dong);

}
