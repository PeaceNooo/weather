package com.example.search.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SearchService {

    public HashMap<String, List<Map<String, Object>>> search();
}
