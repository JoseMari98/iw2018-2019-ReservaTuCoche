package es.uca.iw;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaCreditoService {
    private TarjetaCreditoRepository repoTarjeta;
    @Autowired
    private TarjetaCreditoService(TarjetaCreditoRepository repoTarjeta) {
        this.repoTarjeta = repoTarjeta;
    }
    public synchronized TarjetaCredito guardarTarjeta(TarjetaCredito entrada) {
        return repoTarjeta.save(entrada);
    }

    public Optional<TarjetaCredito> buscarIdTarjeta(Long id) {
        return repoTarjeta.findById(id);
    }

    public List<TarjetaCredito> listarTarjeta() {
        return repoTarjeta.findAll();
    }

    public Long contarTarjeta() {
        return repoTarjeta.count();
    }

    public void borrarTarjeta(TarjetaCredito entidad) {
        repoTarjeta.delete(entidad);
    }

    public TarjetaCredito listarPorNumero(String numero) {
        return repoTarjeta.findByNumeroTarjeta(numero);
    }

    public List<TarjetaCredito> listarPorUsuario(Usuario usuario) { return repoTarjeta.findByUsuario(usuario); }
}