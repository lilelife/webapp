package com.lile.common.mybits.persistence;

import com.lile.common.mybits.model.BalancePayResource;
import com.lile.common.mybits.model.BalancePayResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BalancePayResourceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table balance_pay_resource
     *
     * @mbggenerated Sat May 12 12:19:32 CST 2018
     */
    int countByExample(BalancePayResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table balance_pay_resource
     *
     * @mbggenerated Sat May 12 12:19:32 CST 2018
     */
    int deleteByExample(BalancePayResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table balance_pay_resource
     *
     * @mbggenerated Sat May 12 12:19:32 CST 2018
     */
    int insert(BalancePayResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table balance_pay_resource
     *
     * @mbggenerated Sat May 12 12:19:32 CST 2018
     */
    int insertSelective(BalancePayResource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table balance_pay_resource
     *
     * @mbggenerated Sat May 12 12:19:32 CST 2018
     */
    List<BalancePayResource> selectByExample(BalancePayResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table balance_pay_resource
     *
     * @mbggenerated Sat May 12 12:19:32 CST 2018
     */
    int updateByExampleSelective(@Param("record") BalancePayResource record, @Param("example") BalancePayResourceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table balance_pay_resource
     *
     * @mbggenerated Sat May 12 12:19:32 CST 2018
     */
    int updateByExample(@Param("record") BalancePayResource record, @Param("example") BalancePayResourceExample example);
}