/**
 *
 */
package com.app.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ge.med.dragon.rispacs.search.SearchEngineer;
import com.ge.med.dragon.rispacs.search.SearchEngineerOperations;
import com.ge.med.dragon.rispacs.service.ServiceFactory;
import com.ge.med.dragon.rispacs.service.ServiceNameConstants;

/**
 * @project: his_interface_zhanghua
 * @author: zhou huijun
 * @Copyright GE Healthcare Integrated IT Solution
 * @purpose:
 * @version: 1.0
 * @Date: 2013-1-20
 */
public class SearcherUtils {

	private static final String QUERY_STRING_PREFIX = "<?xml version='1.0' encoding='utf-8' ?><fields>";
	private static final String QUERY_COLUMN_NAME_PREFIX = "<column name='";
	private static final String QUERY_COLUMN_VALUE_PREFIX = "' value='";
	private static final String QUERY_COLUMN_VALUE_SUFFIX = "'/>";
	private static final String QUERY_STRING_SUFFIX = "</fields>";

	static Logger logger  =  LoggerFactory.getLogger(SearcherUtils.class);

	public static String[][] query(Map<String, String> params, String[] target) {
		SearchEngineerOperations searcherEngineer = (SearchEngineerOperations) ServiceFactory.getService(ServiceNameConstants.SEARCHENGINEER);
		StringBuilder sb = new StringBuilder(QUERY_STRING_PREFIX);
		Set<Map.Entry<String, String>> entrySet = params.entrySet();
		boolean search = false;
		for (Map.Entry<String, String> entry : entrySet) {
			if (!StringUtils.isEmpty(entry.getKey()) && !StringUtils.isEmpty(entry.getValue())) {
				sb.append(QUERY_COLUMN_NAME_PREFIX).append(entry.getKey()).append(QUERY_COLUMN_VALUE_PREFIX).append(entry.getValue())
						.append(QUERY_COLUMN_VALUE_SUFFIX);
				search = true;
			}
		}
		if (search && target != null && target.length > 0) {
			sb.append(QUERY_STRING_SUFFIX);
			String queryString = sb.toString();
			logger.info("jcorb query string:[" + queryString + "]");
			String[][] result = searcherEngineer.search(queryString, target);
			return result;
		}
		return new String[0][0];
	}

	public static String buildSearchXml(Map<String, String> params) {
		StringBuilder sb = new StringBuilder(QUERY_STRING_PREFIX);
		Set<Map.Entry<String, String>> entrySet = params.entrySet();
		boolean search = false;
		for (Map.Entry<String, String> entry : entrySet) {
			if (!StringUtils.isEmpty(entry.getKey()) && !StringUtils.isEmpty(entry.getValue())) {
				sb.append(QUERY_COLUMN_NAME_PREFIX).append(entry.getKey()).append(QUERY_COLUMN_VALUE_PREFIX).append(entry.getValue())
						.append(QUERY_COLUMN_VALUE_SUFFIX);
				search = true;
			}
		}

		if (search) {
			String queryString = sb.toString();
			logger.info("jcorb query string:[" + queryString + "]");
			return queryString;
		}
		return null;
	}

	public static List<Map<String, String>> queryToMap(Map<String, String> params, String[] target) {
		String[][] result = query(params, target);
		Map<String, String> resultMap = null;
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		if (result != null) {
			for (int k = 0; k < result.length; k++) {
				resultMap = new HashMap<String, String>();
				String[] sigleResult = result[k];
				for (int i = 0; i < target.length; i++) {
					resultMap.put(target[i], sigleResult[i]);
				}
				resultList.add(resultMap);
			}
			return resultList;
		}
		return Collections.emptyList();
	}
}
