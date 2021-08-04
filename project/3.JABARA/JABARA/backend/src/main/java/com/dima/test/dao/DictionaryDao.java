package com.dima.test.dao;

import java.util.Dictionary;
import java.util.List;

import com.dima.test.dto.Follow;

public interface DictionaryDao {
	List<Dictionary> selectDictionaryAll();
	List<Dictionary> selectDictionaryByName(String word);
}
