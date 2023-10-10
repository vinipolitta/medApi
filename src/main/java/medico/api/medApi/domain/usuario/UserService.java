package medico.api.medApi.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Usuario createUserAuth(Usuario user) {
        // Criptografar a senha antes de salvar no banco de dados
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Usuario updateUser(Usuario user) {
        // Verifique se a senha foi fornecida na solicitação de atualização
        if (user.getPassword() != null) {
            // Criptografa a nova senha antes de atualizar
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // Se a senha não foi fornecida, mantenha a senha atual no banco de dados
            Usuario existingUser;
            existingUser = userRepository.findById(user.getId()).orElse(null);
            if (existingUser != null) {
                user.setPassword(existingUser.getPassword());
            }
        }

        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public Usuario getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }


}
