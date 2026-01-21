package ec.edu.ups.ppw.business;

import java.util.List;
import ec.edu.ups.ppw.dao.DisponibilidadDAO;
import ec.edu.ups.ppw.model.Disponibilidad;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionDisponibilidad {

    @Inject
    private DisponibilidadDAO daoDisponibilidad;

    public void createDisponibilidad(Disponibilidad disponibilidad) {
        daoDisponibilidad.insert(disponibilidad);
    }

    public List<Disponibilidad> getDisponibilidades() {
        return daoDisponibilidad.getAll();
    }

    public Disponibilidad getDisponibilidadPorId(int id) {
        return daoDisponibilidad.read(id);
    }

    public void updateDisponibilidad(Disponibilidad disponibilidad) {
        daoDisponibilidad.update(disponibilidad);
    }

    public void deleteDisponibilidad(int id) {
        daoDisponibilidad.delete(id);
    }
}