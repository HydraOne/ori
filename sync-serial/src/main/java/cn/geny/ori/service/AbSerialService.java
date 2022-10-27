package cn.geny.ori.service;

import cn.geny.ori.mapper.CtSerialMesMapper;
import cn.geny.ori.model.CtSerialMes;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import cn.geny.ori.mapper.AbSerialMapper;
import cn.geny.ori.model.AbSerial;

@DS("master")
@Service
public class AbSerialService  extends ServiceImpl<AbSerialMapper, AbSerial> {
}









