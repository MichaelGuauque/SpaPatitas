package com.spapatitas.service.implementation;

import com.spapatitas.DTO.PromocionDTO;
import com.spapatitas.persistence.model.Producto;
import com.spapatitas.persistence.model.PromocionProducto;
import com.spapatitas.persistence.repository.ProductoRepository;
import com.spapatitas.persistence.repository.PromocionProductoRepository;
import com.spapatitas.service.interfaces.IPromocionProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromocionProductoService implements IPromocionProductoService {

    @Autowired
    private PromocionProductoRepository promocionProductoRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public PromocionProducto cambiarDTO(PromocionDTO promocionDTO){
        Optional<Producto> optionalProducto = productoRepository.findById(promocionDTO.getCodigo());
        Producto producto;
        PromocionProducto promocionProducto;
        if(optionalProducto.isPresent()){
            producto = optionalProducto.get();
            promocionProducto = new PromocionProducto(producto, promocionDTO.getPorcentajeDescuento(), promocionDTO.isEstado());
            return promocionProducto;
        }
        return new PromocionProducto();

    }

    @Override
    public void save(PromocionProducto promocionProducto) {
        promocionProductoRepository.save(promocionProducto);
    }

    @Override
    public void deshabilitar(Long id) {
        Optional<PromocionProducto> promocion = promocionProductoRepository.findById(id);
        if(promocion.isPresent()) {
            PromocionProducto promocionProducto = promocion.get();
            promocionProducto.setEstado(false);
            promocionProductoRepository.save(promocionProducto);
        }
    }

    @Override
    public void habilitar(Long id) {
        Optional<PromocionProducto> promocion = promocionProductoRepository.findById(id);
        if(promocion.isPresent()) {
            PromocionProducto promocionProducto = promocion.get();
            promocionProducto.setEstado(true);
            promocionProductoRepository.save(promocionProducto);
        }
    }

    @Override
    public List<PromocionProducto> findAll() {
        return (List<PromocionProducto>)promocionProductoRepository.findAll();
    }

    @Override
    public List<PromocionProducto> findAllPromocionesDisponibles() {
        return promocionProductoRepository.findByEstadoTrue();
    }

    @Override
    public Optional<PromocionProducto> findById(Long id) {
        return promocionProductoRepository.findById(id);
    }

    @Override
    public PromocionProducto update(PromocionProducto promocionProducto) {
        return promocionProductoRepository.save(promocionProducto);
    }

}
