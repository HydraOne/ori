package cn.geny.ori.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.geny.ori.mapper.CtSerialProductBatchsnMapper;
import cn.geny.ori.model.CtSerialProductBatchsn;

@DS("slave_1")
//@DS("master")
@Service
public class CtSerialProductBatchsnService extends ServiceImpl<CtSerialProductBatchsnMapper, CtSerialProductBatchsn> {

}





