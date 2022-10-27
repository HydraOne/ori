package cn.geny.ori.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.geny.ori.model.CtSerialMes;
import cn.geny.ori.mapper.CtSerialMesMapper;

@DS("master")
@Service
public class CtSerialMesService extends ServiceImpl<CtSerialMesMapper, CtSerialMes> {

}

