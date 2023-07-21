package com.challengeBackend.techforb;

import com.challengeBackend.techforb.Security.Controller.Mensaje;
import com.challengeBackend.techforb.Security.Dto.NuevoUsuario;
import com.challengeBackend.techforb.Security.Entity.Role;
import com.challengeBackend.techforb.Security.Entity.Usuario;
import com.challengeBackend.techforb.Security.Enums.RoleNombre;
import com.challengeBackend.techforb.Security.Enums.TipoDeDocumento;
import com.challengeBackend.techforb.Security.Repository.IUsuarioRepository;
import com.challengeBackend.techforb.Security.Service.RoleService;
import com.challengeBackend.techforb.Security.Service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TechforbApplicationTests {

	@Autowired
	private RoleService roleRepository;
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UsuarioService usuarioRepository;

	@Autowired
	IUsuarioRepository iUsuarioRepository;
	@Test
	void crearRolesParaRegister() {
		Role role1 = new Role();
		role1.setRoleNombre(RoleNombre.ROLE_ADMIN);
		roleRepository.save(role1);
		Role role2 = new Role();
		role2.setRoleNombre(RoleNombre.ROLE_USER);
		roleRepository.save(role2);
	}

	@Test
	public void testRegisterController() {
		// test para crear usuario
		NuevoUsuario nuevoUsuario = new NuevoUsuario();
		nuevoUsuario.setTipoDocumento(TipoDeDocumento.DNI);
		nuevoUsuario.setNroDocumento(12345678);
		nuevoUsuario.setPassword("contraseña123");
		Set<String> roles = new HashSet<>();
		roles.add("user");
		roles.add("admin");
		nuevoUsuario.setRoles(roles);

		ResponseEntity<Mensaje> response = restTemplate.postForEntity("/auth/register", nuevoUsuario, Mensaje.class);

		assertThat(response.getBody().getMensaje()).isEqualTo("Usuario guardado");
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

		// Verificar que el usuario se haya guardado en la base de datos
		List<Usuario> usuarios = iUsuarioRepository.findAll();
		assertThat(usuarios, hasSize(1));

		Usuario usuarioGuardado = usuarios.get(0);
		assertThat(usuarioGuardado.getTipoDocumento()).isEqualTo(TipoDeDocumento.DNI);
		assertThat(usuarioGuardado.getNroDocumento()).isEqualTo(12345678);
		assertThat(usuarioGuardado.getRoles(),hasSize(2));
	}

}
