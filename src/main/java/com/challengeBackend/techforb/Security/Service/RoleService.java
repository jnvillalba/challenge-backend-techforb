package com.challengeBackend.techforb.Security.Service;


import com.challengeBackend.techforb.Security.Entity.Role;
import com.challengeBackend.techforb.Security.Enums.RoleNombre;
import com.challengeBackend.techforb.Security.Repository.IRoleRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RoleService {
    
    @Autowired
    IRoleRepository iRoleRepository;
    
    public Optional<Role> getByRoleNombre(RoleNombre rolNombre){
        return iRoleRepository.findByRoleNombre(rolNombre);
    }
    
    public void save(Role rol){
        iRoleRepository.save(rol);
    }


}
