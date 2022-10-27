package cn.geny.ori.mapper;

import cn.geny.ori.model.NewPackageSerial;
import cn.geny.ori.model.OldPackageSerial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface OldPackageSerialMapper extends BaseMapper<OldPackageSerial> {

    @Select("select 物料\n" +
            "from CHERVON_MESEX.FUS_MES_ITEM_SN_V@MESEX.NJ.CHERVON.COM t\n" +
            "where t.创建日期 >= #{createDate} group by 物料")
    List<String> getSpecialDateAfterProduct(Date createDate);
}