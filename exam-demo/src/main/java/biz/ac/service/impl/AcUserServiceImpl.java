package biz.ac.service.impl;

import biz.ac.entity.AcUser;
import biz.ac.mapper.AcUserMapper;
import biz.ac.service.AcUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author author
 * @since 2019-07-31
 */
@Service
public class AcUserServiceImpl extends ServiceImpl<AcUserMapper, AcUser> implements AcUserService {

}
