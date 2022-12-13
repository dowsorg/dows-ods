package org.dows.ods.biz.util;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import org.dows.framework.api.exceptions.BizException;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**加密方式*/
public class CryptoHelper {
    /*key为加密方式名称，value是加密算法*/
    private static final Map<String, Enum> CRYPTO_TYPE_ALGORITHM= new HashMap<>();

    static{
        // 放入摘要加密的算法枚举
        Arrays.stream(DigestAlgorithm.values()).forEach(e->CRYPTO_TYPE_ALGORITHM.put(e.getValue().toLowerCase(),e));
    }

    public static Map<String, Enum> getCryptoTypeAlgorithm() {
        return CRYPTO_TYPE_ALGORITHM;
    }

    /*输入加密算法名称和需要加密的内容*/
    // 没有直接修改 value的值是为了满足之后其他调用时不想修改原引用的需求
    public static String cryptoValue(String cryptoTypeName,Object value){
        if(Objects.isNull(value)){
            throw new BizException("参数异常");
        }
        String valueStr = String.valueOf(value);
        if(!StringUtils.hasText(valueStr)){
            throw new BizException("参数转换成文本内容为空");
        }
        if(!StringUtils.hasText(cryptoTypeName)){
            throw new BizException("加密方式为空");
        }
        if(!CRYPTO_TYPE_ALGORITHM.containsKey(cryptoTypeName.toLowerCase())){
            throw new BizException("不支持该加密方式");
        }
        Digester digester = new Digester((DigestAlgorithm) CRYPTO_TYPE_ALGORITHM.get(cryptoTypeName.toLowerCase()));
        return digester.digestHex(valueStr).toUpperCase();
    }
}
