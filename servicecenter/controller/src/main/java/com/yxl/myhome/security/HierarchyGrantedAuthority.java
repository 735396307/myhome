package com.yxl.myhome.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


@Getter
@Setter
public class HierarchyGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 914857922695116453L;

    public HierarchyGrantedAuthority(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    private String hierarchy;
    @Override
    public String getAuthority() {
        return getHierarchy();
    }
}
