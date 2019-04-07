/*package es.uca.iw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service

public class UsuarioService implements UserDetailsService {

    private UsuarioRepository repo;
    private PasswordEncoder passwordEncoder;
    private MailNotificationService notificationService;

    @Autowired
    public UsuarioService(UsuarioRepository repo, PasswordEncoder passwordEncoder, MailNotificationService notificationService)
    {
        this.repo=repo;
        this.passwordEncoder=passwordEncoder;
        this.notificationService= notificationService;
    }

    public Usuario create(Usuario usuario)
    {
        usuario.setContrasena(passwordEncoder.encode(usuario.getPassword()));
        Usuario usr = repo.save(usuario);

        notificationService.sendMailConfirmUser(usuario);
        return usr;

    }

    public Usuario update(Usuario usuario)
    {
        Usuario usr = repo.save(usuario);
        return usr;
    }

    public Usuario activate(int id)
    {
        Usuario usr = this.findOne(id);
        usr.setEnabled(true);
        this.update(usr);
        return usr;
    }

    public Usuario loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Usuario user= repo.finByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException(username);
        }
        return user;
    }




}
*/