package ec.edu.ups.ppw.business;

import java.util.List;
import ec.edu.ups.ppw.dao.AsesoriaDAO;
import ec.edu.ups.ppw.model.Asesoria;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionAsesoria {

    @Inject
    private AsesoriaDAO daoAsesoria;

    public void createAsesoria(Asesoria asesoria) {
        daoAsesoria.insert(asesoria);
    }

    public List<Asesoria> getAsesorias() {
        return daoAsesoria.getAll();
    }

    public Asesoria getAsesoriaPorId(int id) {
        return daoAsesoria.read(id);
    }

    public void updateAsesoria(Asesoria asesoria) {
        daoAsesoria.update(asesoria);
    }

    public void deleteAsesoria(int id) {
        daoAsesoria.delete(id);
    }
}