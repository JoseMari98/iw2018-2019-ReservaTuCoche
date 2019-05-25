package es.uca.iw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UsuarioService implements UserDetailsService {

    private UsuarioRepository repo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository repo, PasswordEncoder passwordEncoder)
    {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario create(Usuario usuario)
    {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Usuario usr = repo.save(usuario);

        return usr;
    }

    public Usuario update(Usuario usuario)
    {
        Usuario usr = repo.save(usuario);
        return usr;
    }
    public Usuario buscarId(int id) {
        return repo.findById(id);
    }

    public Usuario activate(int id)
    {
        Usuario usr = this.buscarId(id);
        this.update(usr);
        return usr;
    }

    public List<Usuario> findAll() {
        return repo.findAll();
    }

    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Usuario user= repo.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public void delete(Usuario usr) {
        repo.delete(usr);
    }

    public void updateId(Long ida, String username) {
        repo.setId(ida, username);
    }
}


