package com.changgou.search.service;

import java.util.Map;

public interface SearchService {
    /**
     * 全文检索
     * @param searchMap
     * @return
     * @throws Exception
     */
    public Map search(Map<String, String> searchMap) throws Exception;
}
