package org.dows.ods.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 加密信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoConfig {
    /*加密方式*/
    private String cryptoType;
}
