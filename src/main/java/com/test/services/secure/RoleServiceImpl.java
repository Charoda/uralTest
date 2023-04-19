package com.test.services.secure;

import com.test.entity.secure.Role;
import com.test.repositories.secure.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleRepository;

    @Autowired
    public RoleServiceImpl(RoleDao roleRepository) {
        this.roleRepository = roleRepository;
    }


    public  Collection<Role> listRoles() {
        return roleRepository.index();
    }

    @Override
    public Role getRole(Long id) {
        return roleRepository.getRole(id);
    }

    @Override
    public List<Role> getRolesListById(List<Long> roles) {
        return roleRepository.getRolesListById(roles);
    }
}