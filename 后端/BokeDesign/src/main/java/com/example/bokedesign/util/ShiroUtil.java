package com.example.bokedesign.util;

import com.example.bokedesign.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil  {
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}
