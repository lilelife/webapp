package com.lile.common.mybits.persistence;

import com.lile.common.mybits.model.ZdBalance;
import com.lile.common.mybits.model.ZdBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ZdBalanceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zd_balance
     *
     * @mbggenerated Tue May 08 23:31:42 CST 2018
     */
    int countByExample(ZdBalanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zd_balance
     *
     * @mbggenerated Tue May 08 23:31:42 CST 2018
     */
    int deleteByExample(ZdBalanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zd_balance
     *
     * @mbggenerated Tue May 08 23:31:42 CST 2018
     */
    int insert(ZdBalance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zd_balance
     *
     * @mbggenerated Tue May 08 23:31:42 CST 2018
     */
    int insertSelective(ZdBalance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zd_balance
     *
     * @mbggenerated Tue May 08 23:31:42 CST 2018
     */
    List<ZdBalance> selectByExample(ZdBalanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zd_balance
     *
     * @mbggenerated Tue May 08 23:31:42 CST 2018
     */
    int updateByExampleSelective(@Param("record") ZdBalance record, @Param("example") ZdBalanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zd_balance
     *
     * @mbggenerated Tue May 08 23:31:42 CST 2018
     */
    int updateByExample(@Param("record") ZdBalance record, @Param("example") ZdBalanceExample example);
}