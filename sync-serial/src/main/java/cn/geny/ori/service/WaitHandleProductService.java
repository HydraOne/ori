package cn.geny.ori.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.geny.ori.mapper.WaitHandleProductMapper;
import cn.geny.ori.model.WaitHandleProduct;

@Service
@DS("master")
public class WaitHandleProductService extends ServiceImpl<WaitHandleProductMapper, WaitHandleProduct> {

}

