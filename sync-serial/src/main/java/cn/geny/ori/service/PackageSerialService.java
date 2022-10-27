package cn.geny.ori.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.geny.ori.model.PackageSerial;
import cn.geny.ori.mapper.PackageSerialMapper;
@DS("master")
@Service
public class PackageSerialService extends ServiceImpl<PackageSerialMapper, PackageSerial> {

}


