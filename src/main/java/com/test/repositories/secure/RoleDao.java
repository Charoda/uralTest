package com.test.repositories.secure;

import com.test.entity.secure.Role;

import java.util.Collection;
import java.util.List;

public interface RoleDao {

    List<Role> index();

    Role getRole(Long id);

    List<Role> getRolesListById(List<Long> roles);

}
