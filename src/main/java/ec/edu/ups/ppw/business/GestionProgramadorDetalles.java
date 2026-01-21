package ec.edu.ups.ppw.business;

import java.util.List;
import ec.edu.ups.ppw.dao.ProgramadorDetallesDAO;
import ec.edu.ups.ppw.model.ProgramadorDetalles;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionProgramadorDetalles {

    @Inject
    private ProgramadorDetallesDAO daoProgramadorDetalles;

    // CREATE
    public void createProgramadorDetalles(ProgramadorDetalles programadorDetalles) {
        daoProgramadorDetalles.insert(programadorDetalles);
    }

    // READ (Todos)
    public List<ProgramadorDetalles> getProgramadorDetalles() {
        return daoProgramadorDetalles.getAll();
    }

    // READ (Uno)
    public ProgramadorDetalles getProgramadorDetallesPorId(int id) {
        return daoProgramadorDetalles.read(id);
    }

    // UPDATE
    public void updateProgramadorDetalles(ProgramadorDetalles programadorDetalles) {
        daoProgramadorDetalles.update(programadorDetalles);
    }

    // DELETE
    public void deleteProgramadorDetalles(int id) {
        daoProgramadorDetalles.delete(id);
    }
}