package es.uca.iw;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TarjetaCreditoRepository extends JpaRepository<TarjetaCredito,Long> {
    TarjetaCredito findByNumeroTarjeta(String numero);
    List<TarjetaCredito> findByUsuario(Usuario usuario);
}
