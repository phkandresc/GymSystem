package service;

import DAO.TipoMembresiaDAO;
import model.TipoMembresia;

import java.util.List;

public class TipoMembresiaService {
    private TipoMembresiaDAO tipoMembresiaDAO;

    public TipoMembresiaService() {
        this.tipoMembresiaDAO = new TipoMembresiaDAO();
    }

    public List<TipoMembresia> obtenerTiposMembresia() throws Exception {
        return tipoMembresiaDAO.obtenerTiposMembresia();
    }
}
