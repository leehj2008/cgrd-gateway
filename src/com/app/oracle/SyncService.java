package com.app.oracle;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.MysqlDao;
@Service
public class SyncService {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	OracleDao oracleDao;
	
	@Autowired
	MysqlDao mysqlDao;
	
	@Transactional(transactionManager="oracleTransactionManager",rollbackFor=Exception.class)
	public void syncData() throws Exception{
		log.debug("synchronizing..");
		List<Map> r = oracleDao.readRequest();
		for(Map row : r){
			mysqlDao.syncData(row);
			String PACSNO  = row.get("PACSNO").toString();
			oracleDao.markRequest(PACSNO);
		}
		log.debug("synchronized..");
		
	}
}
