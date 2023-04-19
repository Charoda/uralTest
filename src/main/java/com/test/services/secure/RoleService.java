package com.test.services.secure;
import com.test.entity.secure.Role;

import java.util.Collection;
import java.util.List;

public interface RoleService {

    Collection<Role> listRoles();

    Role getRole(Long id);

    List<Role> getRolesListById(List<Long> roles);
}