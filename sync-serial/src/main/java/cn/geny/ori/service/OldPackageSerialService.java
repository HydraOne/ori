package cn.geny.ori.service;

import cn.geny.ori.mapper.NewPackageSerialMapper;
import cn.geny.ori.mapper.OldPackageSerialMapper;
import cn.geny.ori.model.NewPackageSerial;
import cn.geny.ori.model.OldPackageSerial;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@DS("slave_1")
@Service
public class OldPackageSerialService extends ServiceImpl<OldPackageSerialMapper, OldPackageSerial> {
    @Autowired
    OldPackageSerialMapper oldPackageSerialMapper;
    public List<String> getSpecialDateAfterProduct(Date createDate){
        return oldPackageSerialMapper.getSpecialDateAfterProduct(createDate);
    }

}

