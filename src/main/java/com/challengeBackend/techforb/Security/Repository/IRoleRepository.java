/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.challengeBackend.techforb.Security.Repository;


import com.challengeBackend.techforb.Security.Entity.Role;
import com.challengeBackend.techforb.Security.Enums.RoleNombre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleNombre(RoleNombre rolNombre);
    
}
