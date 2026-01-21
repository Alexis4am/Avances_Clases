package ec.edu.ups.ppw.business;

import java.util.List;
import ec.edu.ups.ppw.dao.NotificacionDAO;
import ec.edu.ups.ppw.model.Notificacion;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionNotificacion {

    @Inject
    private NotificacionDAO daoNotificacion;

    public void createNotificacion(Notificacion notificacion) {
        daoNotificacion.insert(notificacion);
    }

    public List<Notificacion> getNotificaciones() {
        return daoNotificacion.getAll();
    }

    public Notificacion getNotificacionPorId(int id) {
        return daoNotificacion.read(id);
    }

    public void updateNotificacion(Notificacion notificacion) {
        daoNotificacion.update(notificacion);
    }

    public void deleteNotificacion(int id) {
        daoNotificacion.delete(id);
    }
}