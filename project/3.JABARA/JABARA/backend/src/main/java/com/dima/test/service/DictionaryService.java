package com.dima.test.service;

import java.util.Dictionary;
import java.util.List;

import com.dima.test.dto.Follow;

public interface DictionaryService{
	public List<Dictionary> selectDictionaryAll();
	public List<Dictionary> selectDictionaryByName(String word);
}
