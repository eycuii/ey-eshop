package com.ey.eshop.common.util;

import cn.hutool.crypto.digest.DigestUtil;
import com.ey.eshop.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
public class SecurityUtil {

    public static String md5(String data) {
        return DigestUtil.md5Hex(data);
    }


    /**
     * 加密
     *
     * @param data   加密对象
     * @param secret 密钥
     */
    public static String encrypt(String data, String secret) {
        try {
            SecretKeySpec key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] byteContent = data.getBytes(StandardCharsets.UTF_8);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception e) {
            log.error("加密异常: {}, 密钥: {}, 加密对象: {}", e.getMessage(), secret, data, e);
            throw new BusinessException("加密异常");
        }
    }

    /**
     * 解密
     *
     * @param data   解密对象
     * @param secret 密钥
     */
    public static String decrypt(String data, String secret) {
        try {
            SecretKeySpec key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] byteContent = Base64.getDecoder().decode(data);
            byte[] result = cipher.doFinal(byteContent);
            return new String(result);
        } catch (Exception e) {
            log.error("解密异常: {}, 密钥: {}, 解密对象: {}", e.getMessage(), secret, data, e);
            throw new BusinessException("解密异常");
        }
    }
}
