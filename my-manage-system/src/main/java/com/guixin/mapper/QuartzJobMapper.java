package com.guixin.mapper;

import com.guixin.pojo.QuartzJob;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * quartz定时任务表 Mapper 接口
 * </p>
 *
 * @author guixin
 * @since 2021-06-11
 */
@Mapper
public interface QuartzJobMapper extends BaseMapper<QuartzJob> {

}
