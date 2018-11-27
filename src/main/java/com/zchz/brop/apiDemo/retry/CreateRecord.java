package com.zchz.brop.apiDemo.retry;

import com.zchz.brop.apiDemo.config.ApplicationConfig;
import com.zchz.brop.apiDemo.enums.BROPContractResultcode;
import com.zchz.brop.apiDemo.res.CommonResult;
import com.zchz.brop.apiDemo.res.NTSCRes;
import com.zchz.brop.apiDemo.res.TransactionResult;
import com.zchz.brop.apiDemo.utils.BROPUtils;
import com.zchz.brop.apiDemo.utils.EncryptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * 授时存证合约
 * 写入存证记录,拥有当前存证合约的写入存证记录权限的身份合约用户可以调用。
 */
public class CreateRecord {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateRecord.class);

    // 授时存证代理合约的合约地址
    private static final String ntscContractAddress = ApplicationConfig.registryNTSCProxy;

    // 授时存证合约的合约地址
    private static final String ntscRecordRegistryAddress = ApplicationConfig.registryNTSCAddress;

    // 当前操作人身份合约地址
    private static final String opIdentityAddress = ApplicationConfig.coopAddress;

    // 存证信息
    private static String recordData;

    private static final String hsmUserId = ApplicationConfig.hsmName;

    static {
        try {
            recordData = EncryptionUtil.encryptStr("1ca6574c4dd8749538798af8ac9cb08412d5036f45b338062a1db4ef8fb5008a");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 数据准备
        final String customString = "asdf"; // 自定义字符串
        final String preSeqId = ""; // 前序存证流水号，可为空
        final String seqId = "test" + System.currentTimeMillis(); // 存证流水号，必填，每次写入都需要更新，存证流水号必须唯一，重复提交会失败
        final String bizId = Calendar.getInstance().getTimeInMillis() + ""; // 业务ID
        final String method = "createRecord"; // 合约方法
        final Object[] params = {
                ntscRecordRegistryAddress,
                seqId,
                preSeqId,
                recordData,
                customString,
                opIdentityAddress
        };

        // 发送交易
        String flowNumber = BROPRetryUtils.sendNtscTxWithRetry(ntscContractAddress,
                                                                bizId,
                                                                method,
                                                                params,
                                                                hsmUserId);

        LOGGER.info("flowNumber: {}", flowNumber);
        if (flowNumber == null) {
            LOGGER.error("新增黑名单用户失败,调用中间件失败");
            return;
        }

        String result = AddUserToBlackListWithRetry.searchResult(flowNumber);

        // 如果交易是"无效的"，根据业务需求可以调用重置"无效"交易的接口，再查询
//        if (RetryTxState.INVALID.name().equals(result)) {
//            if (!BROPRetryUtils.resetInvalidTxWithRetry(flowNumber)) {
//                LOGGER.error("新增黑名单用户失败,调用中间件失败");
//            } else {
//                searchResult(flowNumber);
//            }
//        }

        // 如果交易是"交易处理中的"，根据业务需求可以调用重置"交易处理中的"交易的接口，再查询
//        if (RetryTxState.DOTX.name().equals(result)) {
//            if (!BROPRetryUtils.resetUnfinishedTxWithRetry(flowNumber)) {
//                LOGGER.error("新增黑名单用户失败,调用中间件失败");
//            } else {
//                searchResult(flowNumber);
//            }
//        }

    }
}
