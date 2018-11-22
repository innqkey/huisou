package com.huisou.mapper;

import java.util.Map;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.common.MyMapper;
import com.huisou.po.RenewMarkPo;
import com.huisou.vo.RenewMarkVo;

@Repository
public interface RenewMarkMapper extends MyMapper<RenewMarkPo>{

	public List<RenewMarkVo> selectMarkList(Map reMap);
	public List<RenewMarkVo> selectRenewSucList(Map reMap);
}
