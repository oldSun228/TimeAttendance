package cn.seisys.iti.pdd.tcdp.manager.util;

/**
 * 配置表int
 * @author Lightning
 * @date 2014-12-17
 */
public enum ConfigType {
	
	/** 数据分拣最晚时间 */
	SJFJZWSJ(10),
	
	/** 数据审核最晚时间 */
	SJSHZWSJ(5),
	
	/** 数据复核最晚时间 */
	SJFHZWSJ(3),
	
	/** 数据发布到六合一最早时间 */
	SJFBZZSJ(5),
	
	/** 数据审核百分比设置 */
	SJSHBFB(10),
	
	/**复核百分比**/
	FHBFB(10),
	
	/**是否全部复核**/
	SFQBFH(1),
	
	/**是否全部审核 1是 0 否**/ 
	SFQBSH(1),
	
	/** 分拣查询记录条数 */
	FJCXJLTS(100),
	/** 计划分拣数*/
	JHFJS(100),
	/**分拣删除百分比*/
	FJSCBFB(1),
	/**审核删除百分比*/
	SHSCBFB(1),
	/**不合格原因**/
	FHBHGYY(01),
	/**大队每天删除百分比**/
	DDTSCBFB(1),
	/**大队月删除百分比**/
	DDMYSCBFB(1),
	/**删除状态**/
	SJSCZT(1);
	
	
	
	private int defaultValue;
	
	private ConfigType(int defaultValue){
		this.defaultValue = defaultValue;
	}
	
	public int getDefaultValue() {
		return defaultValue;
	}
	
}
