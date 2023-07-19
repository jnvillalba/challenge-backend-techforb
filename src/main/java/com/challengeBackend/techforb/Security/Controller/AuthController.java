package com.challengeBackend.techforb.Security.Controller;

import java.util.*;

import com.challengeBackend.techforb.Security.Dto.JwtDto;
import com.challengeBackend.techforb.Security.Dto.LoginUsuario;
import com.challengeBackend.techforb.Security.Entity.Role;
import com.challengeBackend.techforb.Security.Entity.Usuario;
import com.challengeBackend.techforb.Security.Enums.RoleNombre;
import javax.validation.Valid;
import com.challengeBackend.techforb.Security.Dto.NuevoUsuario;
import com.challengeBackend.techforb.Security.Service.RoleService;
import com.challengeBackend.techforb.Security.Service.UsuarioService;
import com.challengeBackend.techforb.Security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AuthController {
    @Autowired
    PasswordEncoder passwordEnconder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RoleService rolService;
    @Autowired
    JwtProvider jwtprovider;
    
    @PostMapping("/register")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos o documento invalido"), HttpStatus.BAD_REQUEST);
        
        if (usuarioService.existsByNroDocumento(nuevoUsuario.getNroDocumento()))
            return new ResponseEntity(new Mensaje("Ya existe el numero de documento"), HttpStatus.BAD_REQUEST);

        Usuario usuario = new Usuario( nuevoUsuario.getTipoDocumento(),
                nuevoUsuario.getNroDocumento(),
                passwordEnconder.encode(nuevoUsuario.getPassword()));
        
        Set<Role> roles = new HashSet<>();
        roles.add(rolService.getByRoleNombre(RoleNombre.ROLE_USER).get());
        
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRoleNombre(RoleNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        
        return new ResponseEntity(new Mensaje("Usuario guardado"), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            Map<String, String> erroresPorCampo = new HashMap<>();
            for (FieldError fieldError : fieldErrors) {
                erroresPorCampo.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<>(erroresPorCampo, HttpStatus.BAD_REQUEST);
        }
        if (loginUsuario.getNroDocumento() <= 0) {
            return new ResponseEntity<>(new Mensaje("El número de documento debe ser mayor que cero"), HttpStatus.BAD_REQUEST);
        }

        if (loginUsuario.getTipoDocumento() == null) {
            return new ResponseEntity<>(new Mensaje("El tipo de documento no puede estar vacío"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isEmpty(loginUsuario.getPassword())) {
            return new ResponseEntity<>(new Mensaje("La contraseña no puede estar vacía"), HttpStatus.BAD_REQUEST);
        }
        // Realizar otras validaciones .ejemplo, verificar si el usuario existe en el sistema o si las credenciales son válidas

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUsuario.getNroDocumento(), loginUsuario.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtprovider.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

}
