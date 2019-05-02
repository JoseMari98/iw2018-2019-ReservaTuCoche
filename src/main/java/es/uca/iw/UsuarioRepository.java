package es.uca.iw;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


import java.util.Optional;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findById(int id);
    Usuario findByUsername(String user);
}